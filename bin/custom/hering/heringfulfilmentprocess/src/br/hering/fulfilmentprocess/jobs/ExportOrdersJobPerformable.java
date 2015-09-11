package br.hering.fulfilmentprocess.jobs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import br.hering.fulfilmentprocess.model.jobs.ExportOrdersJobModel;
import br.hering.fulfilmentprocess.services.impl.DefaultExportOrderService;

import de.hybris.platform.core.enums.ExportStatus;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.stock.StockService;

public class ExportOrdersJobPerformable 
extends AbstractJobPerformable<ExportOrdersJobModel> 
{
	private static Logger LOG = Logger.getLogger(ExportOrdersJobPerformable.class);

	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = 
			new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	@Resource
	private DefaultExportOrderService heringWSOrderService;

	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Resource
	private WarehouseService warehouseService;

	@Resource
	private StockService stockService;

	@Override
	public PerformResult perform(ExportOrdersJobModel job) {
		JobLogListener logs = new JobLogListener();
		logs.startListening();
		try {
			Assert.notNull(job);
			
			CronJobResult result = CronJobResult.SUCCESS;

			final List<OrderModel> ordersToExport = 
					findOrdersToExport(job.getFromDate(), job.getToDate(), 
							job.getStatusCreditCardFilter(), 
							job.getStatusBoletoFilter(), job.getStatusVoucherFilter());

			final List<OrderModel> olderThan24Hours = new ArrayList<OrderModel>();
			
			if (CollectionUtils.isEmpty(ordersToExport)) {
				LOG.info("nothing to export");
				return new PerformResult(CronJobResult.SUCCESS,
						CronJobStatus.FINISHED);
			}

			LOG.info(ordersToExport.size() + " orders to export");

			if (!exportOrders(ordersToExport, olderThan24Hours)) {
				result = CronJobResult.FAILURE;
			}
			
			if(!olderThan24Hours.isEmpty())
			{
				result = CronJobResult.FAILURE;
				final StringBuffer buffer = new StringBuffer();
				for (final OrderModel orderModel : olderThan24Hours)
				{
					buffer.append(
							String.format(
									"order:{code:%s, date:%s} is older than 24 hours and has not been exported yet\n", 
									orderModel.getCode(),
									SIMPLE_DATE_FORMAT.format(orderModel.getDate())));
				}
				LOG.info(buffer.toString());
			}
			return new PerformResult(result, CronJobStatus.FINISHED);
		} finally {
			logs.stopListening();
			sessionService.getCurrentSession().setAttribute(CustomCronJobNotificationContext.LOG_TEXT, logs.getLogs());
		}
	}

	private boolean exportOrders(final List<OrderModel> ordersToExport,
			final List<OrderModel> olderOrdersList) 
	{
		Assert.notEmpty(ordersToExport);
		Assert.notNull(ordersToExport);

		boolean success = true;
			
		final Date minus24HoursDate = new Date((new Date()).getTime() - 86400000);
		
		for (OrderModel order : ordersToExport) {
			try {
				if(order.getDate().before(minus24HoursDate)){
					olderOrdersList.add(order);
				}
				success &= heringWSOrderService.exportOrder(order);
			} catch (Throwable e) {
				success = false;
				LOG.error("error: " + (order != null ? order.getCode() : ""), e);
			}
		}
		return success;
	}

	private List<OrderModel> findOrdersToExport(Date from, Date to,
			Collection<OrderStatus> statusCreditCardFilter,
			Collection<OrderStatus> statusBoletoFilter,
			Collection<OrderStatus> statusVoucherFilter)
	{
		Assert.notEmpty(statusCreditCardFilter);
		Assert.notEmpty(statusBoletoFilter);
		Assert.notEmpty(statusVoucherFilter);

		if (from != null && to != null) {
			Assert.isTrue(to.equals(from) || to.after(from),
					"date range is invalid");
		}

		StringBuilder sql = new StringBuilder(
				"select tbl.pk "
						+ "from ("
						+ "{{"
							+ "select	{o.pk} as pk, "
									+ "	{o.date} as data "
							+ "from {Order as o "
									+ "join PaymentMode as pm on {pm.pk} = {o.paymentMode}} "
							+ "where upper({pm.code}) = 'CREDITCARD' "
											+ "and {o.status}  in (?statusCreditCard) "
											+ "and ({o.exportStatus} is null "
												+ "or {o.exportStatus} = ?notExported) "
						+ "}} "
						+ "union "
						+ "{{ "
							+ "select	{o.pk} as pk, "
									+ "	{o.date} as data "
							+ "from {Order as o "
									+ "join PaymentMode as pm on {pm.pk} = {o.paymentMode}} "
							+ "where upper({pm.code}) = 'BOLETO' "
									+ "and {o.status} =  (?statusBoleto)"
									+ "and ({o.exportStatus} is null "
										+ "or {o.exportStatus} = ?notExported) "
						+ "}} "
						+ "union "
						+ "{{ "
							+ "select	{o.pk} as pk, "
									+ "	{o.date} as data "
							+ "from {Order as o "
									+ "join PaymentMode as pm on {pm.pk} = {o.paymentMode}} "
							+ "where upper({pm.code}) = 'VOUCHER' "
									+ "and {o.status} = (?statusVoucher) "
									+ "and ({o.exportStatus} is null "
										+ "or {o.exportStatus} = ?notExported) "
						+ "}} " + ") tbl where 1=1 ");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("statusCreditCard", statusCreditCardFilter);
		params.put("statusBoleto", statusBoletoFilter);
		params.put("statusVoucher", statusVoucherFilter);
		params.put("notExported", ExportStatus.NOTEXPORTED);

		if (from != null && to != null) {
			sql.append(" AND tbl.data BETWEEN ?fromDate AND ?toDate");
			params.put("fromDate", from);
			params.put("toDate", to);

		} else if (from != null) {
			sql.append(" AND tbl.data >= ?fromDate");
			params.put("fromDate", from);
		} else if (to != null) {
			sql.append(" AND tbl.data <= ?toDate");
			params.put("toDate", to);
		}

		return flexibleSearchService
				.<OrderModel> search(sql.toString(), params).getResult();
	}
}