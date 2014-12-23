package com.pixi.webservices.converters.populators.order;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.pixi.webservices.jaxb.order.export.OrderInfo;
import com.pixi.webservices.jaxb.order.export.Remark;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.kpfamily.services.delivery.ExpressDeliveryService;

public class DefaultPixiOrderRemarksPopulator implements Populator<OrderModel, OrderInfo>
{
	private Logger LOG = Logger.getLogger(DefaultPixiOrderRemarksPopulator.class);
	
	@Resource
	private ExpressDeliveryService expressDeliveryService;
	
	private final NumberFormat numberFormat;
	
	{
		numberFormat = NumberFormat.getNumberInstance(Locale.US);
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setMinimumFractionDigits(2);	
	}
	
	@Override
	public void populate(final OrderModel source, final OrderInfo target) throws ConversionException 
	{
		LOG.info("populating");
		
		target.setTRANSPORTREMARKS(getTransportRemarks(source));
		
		final List<Remark> remarks = target.getREMARK();
		remarks.add(getDiscountsRemark(source));
		remarks.add(getShippingRemark(source));
		remarks.add(getShippingVendorRemark(source));
		remarks.add(createRemark("VoucherCode", ""));
	}

	private String getTransportRemarks(final OrderModel order) 
	{
		if(order == null) 
		{
			return "we have no transport remarks";
		}
		
		if(expressDeliveryService.isApplicableForExpressDelivery(order)) 
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			
			String formatedDate = dateFormat.format(getExpressDeliverySuitableDate(order));
			
			return "DHL;;;|||" + formatedDate;
		}

		return "we have no transport remarks";
	}
	
	private Date getExpressDeliverySuitableDate(final OrderModel order) 
	{
		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 15);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MILLISECOND, 00);
		
		if(order.getDate().after(calendar.getTime())) {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return calendar.getTime();
	}

	private Remark getDiscountsRemark(final OrderModel order) 
	{
		BigDecimal totalDiscounts = new BigDecimal(order.getTotalDiscounts().toString());
		
		String discount = numberFormat.format(totalDiscounts);
		
		return createRemark("discount", discount);
	}

	private Remark getShippingRemark(final OrderModel order) 
	{
		BigDecimal deliveryCost = new BigDecimal(order.getDeliveryCost().toString());
		BigDecimal paymentCost =  new BigDecimal(order.getPaymentCost().toString());
		
		String shipping = numberFormat.format(deliveryCost.add(paymentCost));
		
		return createRemark("shipping", shipping);
	}

	private Remark createRemark(final String type, final String value) 
	{
		final Remark vendorRemark = new Remark();
		vendorRemark.setType(type);
		vendorRemark.setValue(value);
		
		return vendorRemark;
	}	

	private Remark getShippingVendorRemark(final OrderModel order) 
	{
		String mode = StringUtils.defaultIfBlank(order.getDeliveryMode().getCode(), "");
		
		return createRemark("shippingvendor", mode.toUpperCase());
	}
}