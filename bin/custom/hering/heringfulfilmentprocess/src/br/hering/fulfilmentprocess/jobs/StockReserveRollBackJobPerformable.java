package br.hering.fulfilmentprocess.jobs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import br.hering.fulfilmentprocess.model.jobs.StockReserveRollBackJobModel;

import com.flieger.payment.model.BoletoPaymentInfoModel;

import de.hybris.platform.basecommerce.enums.StockLevelUpdateType;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.enums.ExportStatus;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.stock.exception.InsufficientStockLevelException;
import de.hybris.platform.stock.model.StockLevelHistoryEntryModel;
import de.hybris.platform.util.Config;

/**
 * @author ghayashi
 *
 *	Job para voltar os produtos reservados para pedidos efetuados com cartão de crédito
 *	Caso a propertie ... for true a job passará por todos os pedidos em aberto 
 *	(que não tiverem o status como: COMPLETED, CANCELLED, ESTORNO, DISPATCHED, DELIVERD e INVOICE, para CARTÃO DE CRÉDITO)
 *	e reservará o produto. 
 */
public class StockReserveRollBackJobPerformable extends
		AbstractJobPerformable<StockReserveRollBackJobModel> {
	private Logger LOG = Logger
			.getLogger(StockReserveRollBackJobPerformable.class);

	@Resource
	private CustomerAccountService customerAccountService;

	@Resource
	private StockService stockService;

	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Resource
	private WarehouseService warehouseService;

	@Override
	public PerformResult perform(StockReserveRollBackJobModel job) {
		LOG.info("Inicio da job StockReserveRollBack");
		
		boolean success = true;
		
		rollbackCreditCard(job);
		
		if(Config.getParameter("heringfulfilmentprocess.stock.reserved.rollback.job.update.all.open.orders").equalsIgnoreCase("true")){
			rollBackAllOpenOrders(job);
		}
		
		LOG.info("Fim da job StockReserveRollBack");
		if (success) {
			return new PerformResult(CronJobResult.SUCCESS,
					CronJobStatus.FINISHED);
		}

		return new PerformResult(CronJobResult.ERROR, CronJobStatus.FINISHED);
	}
	
	private boolean rollBackAllOpenOrders(StockReserveRollBackJobModel job){
		boolean success = true;
		List<AbstractOrderEntryModel> list = getOrderEntryForAllOpenOrders();
		LOG.info(list.size() + " produtos para ajuste de reserved ");
		
		for (AbstractOrderEntryModel entry : list) {
			final WarehouseModel warehouse = warehouseService.getWarehouseForCode("hering_estoque_default");
			updateStockLevel(entry.getProduct(), entry.getQuantity().intValue(), warehouse, entry.getOrder().getCode());
		}
		
		return success; 
	}
	
	private void updateStockLevel(ProductModel product, int qty, WarehouseModel warehouse, String code){
		final List<StockLevelHistoryEntryModel> historyEntries = new ArrayList<StockLevelHistoryEntryModel>();
		String comment = "Ajuste de Reservas para Pedidos em Aberto - Pedidos: ";
		
		try
		{
			stockService.reserve(product, warehouse, qty, comment + code);
		}
		catch (InsufficientStockLevelException e)
		{
			LOG.error("Error reserving stock:" + " order: " + code + " product: " + product.getCode() + " qty: " + qty, e);
			
			StockLevelModel stockLevel = stockService.getStockLevel(product, warehouse);
			historyEntries.addAll(stockLevel.getStockLevelHistoryEntries());
			stockLevel.setReserved(stockLevel.getReserved() + qty);

			final StockLevelHistoryEntryModel entry = createHistoryEntry(stockLevel, stockLevel.getAvailable(), StockLevelUpdateType.WAREHOUSE, comment);
			historyEntries.add(entry);
			
			stockLevel.setStockLevelHistoryEntries(historyEntries);

			modelService.save(stockLevel);
			modelService.refresh(stockLevel);
		}
	}
	
	private StockLevelHistoryEntryModel createHistoryEntry(final StockLevelModel stockLevel, final int actual,
			final StockLevelUpdateType updateType, final String comment)
	{

		final StockLevelHistoryEntryModel historyEntry = modelService.create(StockLevelHistoryEntryModel.class);
		historyEntry.setStockLevel(stockLevel);
		historyEntry.setActual(actual);
		historyEntry.setUpdateType(updateType);
		historyEntry.setComment(comment);
		historyEntry.setUpdateDate(new Date());

		try
		{
			modelService.save(historyEntry);
		}
		catch (final Exception e)
		{
			LOG.error("error", e);
		}
		return historyEntry;
	}

	private boolean rollbackCreditCard(StockReserveRollBackJobModel job){
		boolean success = true;
		final WarehouseModel warehouse = warehouseService
				.getWarehouseForCode("hering_estoque_default");
		List<AbstractOrderEntryModel> list = getOrderEntryForCreditCard(job.getFromDate(),
				job.getToDate(), job.getStatusFilter());
		LOG.info(list.size() + " produtos para rollback");
		
		for (AbstractOrderEntryModel entry : list) {
			if (entry.getOrder().getPaymentInfo() instanceof CreditCardPaymentInfoModel) {
				try {
					LOG.info("Order: " + entry.getOrder().getCode()
							+ " product: " + entry.getProduct().getCode()
							+ " reserved: " + entry.getQuantity());
					AbstractOrderModel order = entry.getOrder();
					order.setExportStatus(ExportStatus.NOTAUTHORIZED);
					modelService.save(order);

					if (!job.isUpdateAllToNotAuth()) {
						stockService.release(entry.getProduct(), warehouse,
								entry.getQuantity().intValue(),
								" released - CreditCard Cancelled - by order: "
										+ order.getCode());
					}
				} catch (Throwable e) {
					success = false;
					LOG.error("error: "
							+ (entry.getOrder() != null ? entry.getOrder()
									.getCode() : "" + "product: "
									+ entry.getProduct().getCode()), e);
				}
			}
		}
		return success;
	}
	
	private List<AbstractOrderEntryModel> getOrderEntryForCreditCard(Date from, Date to,
			Collection<OrderStatus> statusFilter) {
		StringBuilder sql = new StringBuilder(
				"select	{oe.pk} "
						+ "from {AbstractOrderEntry as oe "
						+ "join Order as o on {oe.order} = {o.pk}"
						+ "join Product as p on {p.pk} = {oe.product}"
						+ "join PaymentMode as pm on {pm.pk} = {o.paymentMode}}"
						+ "where	{o.status} in (?statusFilter)"
						+ "and upper({pm.code}) = 'CREDITCARD'"
						+ "and ({o.exportStatus} = ?notExported or {o.exportStatus} is null)");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("statusFilter", statusFilter);
		params.put("notExported", ExportStatus.NOTEXPORTED);

		if (from != null && to != null) {
			sql.append(" AND {o.creationtime} BETWEEN ?fromDate AND ?toDate");
			params.put("fromDate", from);
			params.put("toDate", to);
		} else if (from != null) {
			sql.append(" AND {o.creationtime} >= ?fromDate");
			params.put("fromDate", from);
		} else if (to != null) {
			sql.append(" AND {o.creationtime} <= ?toDate");
			params.put("toDate", to);
		}

		return flexibleSearchService.<AbstractOrderEntryModel> search(
				sql.toString(), params).getResult();
	}
	
	private List<AbstractOrderEntryModel> getOrderEntryForAllOpenOrders() {
		StringBuilder sql = new StringBuilder(
				"select tbl.pk from ("
					+ "{{ "
						+ "select {ao.pk} as pk "
						+ " from {AbstractOrderEntry as ao "
									+ "join Order as o on {o.pk} = {ao.order} "
						            + "join OrderStatus as os on {o.status} = {os.pk} "
						            + "join PaymentMode as pm on {pm.pk} = {o.paymentMode}} "
						     + " where upper({pm.code}) = 'CREDITCARD' "
						     + "		and {os.code} not in ('CANCELLED', 'DISPATCHED', 'DELIVERED', "
                             + "'ESTORNO', 'INVOICE', 'SUSPENDED', 'PAYMENT_NOT_AUTHORIZED', 'PAYMENT_NOT_CAPTURED', 'PAYMENT_CAPTURED') "
						  + "}}"
					+ " union "
					+ "{{"
						+ "select {ao.pk} as pk "
						  + " from {AbstractOrderEntry as ao "
									+ "join Order as o on {o.pk} = {ao.order} "
						            + "join OrderStatus as os on {o.status} = {os.pk} "
						            + "join PaymentMode as pm on {pm.pk} = {o.paymentMode}} "
						            + " where upper({pm.code}) = 'BOLETO' "
						            + " and {os.code} = 'WAITING_PAYMENT_NOTIFICATION' "
		            + "}} "
		            + " union "
		            + "{{"
			            + "select {ao.pk} as pk "
			              + "from {AbstractOrderEntry as ao "
			              			+ "join Order as o on {o.pk} = {ao.order} "
			                        + "join OrderStatus as os on {o.status} = {os.pk} "
			              			+ "join PaymentMode as pm on {pm.pk} = {o.paymentMode}} "
			              + "where upper({pm.code}) = 'VOUCHER' "
			              		+ "and {os.code} = 'CHECKED_VALID' "
              		+ "}}"
					+ ") tbl"
				);
		
		Map<String, Object> params = new HashMap<String, Object>();


		return flexibleSearchService.<AbstractOrderEntryModel> search(
				sql.toString(), params).getResult();
	}
}