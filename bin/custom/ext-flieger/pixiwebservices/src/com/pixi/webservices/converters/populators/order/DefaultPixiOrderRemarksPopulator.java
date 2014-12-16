package com.pixi.webservices.converters.populators.order;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.pixi.webservices.jaxb.order.export.OrderInfo;
import com.pixi.webservices.jaxb.order.export.Remark;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.util.Config;

public class DefaultPixiOrderRemarksPopulator implements Populator<OrderModel, OrderInfo>
{
	private Logger LOG = Logger.getLogger(DefaultPixiOrderRemarksPopulator.class);
	
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
		
		//TODO in the future we need to integrate the express deliver remarks
		target.setTRANSPORTREMARKS("we have no transport remarks");
		
		final List<Remark> remarks = target.getREMARK();
		remarks.add(getDiscountsRemark(source));
		remarks.add(getShippingRemark(source));
		remarks.add(getVendorRemark(source));
		remarks.add(createRemark("VoucherCode", ""));
	}

	private Remark getDiscountsRemark(final OrderModel order) 
	{
		BigDecimal totalDiscounts = new BigDecimal(order.getTotalDiscounts().toString());
		BigDecimal discountGiveAway = new BigDecimal("0");
		
		String discount = numberFormat.format(totalDiscounts.subtract(discountGiveAway));
		
		return createRemark("discount", discount);
	}

	private Remark getShippingRemark(final OrderModel order) 
	{
		BigDecimal deliveryCost = new BigDecimal(order.getDeliveryCost().toString());
		BigDecimal paymentCost =  new BigDecimal(order.getPaymentCost().toString());
		
		String shipping = numberFormat.format(deliveryCost.subtract(paymentCost));
		
		return createRemark("shipping", shipping);
	}

	private Remark createRemark(final String type, final String value) 
	{
		final Remark vendorRemark = new Remark();
		vendorRemark.setType(type);
		vendorRemark.setValue(value);
		
		return vendorRemark;
	}	

	private Remark getVendorRemark(final OrderModel order) 
	{
		String mode = order.getDeliveryMode().getCode();
		
		return createRemark("shippingvendor", getVendor(mode));
	}

	private String getVendor(String mode) 
	{
		if (mode.contains(Config.getParameter("pixiwebservices.shipping.vendor.dhl")))
			return "DHL";
		
		if (mode.equals(Config.getParameter("pixiwebservices.shipping.vendor.ups")))
			return "UPS";
		
		if (mode.equals(Config.getParameter("pixiwebservices.shipping.vendor.dpd")))
			return "DPD";
		
		if (mode.equals(Config.getParameter("pixiwebservices.shipping.vendor.gls")))
			return "GLS";
		
		if (mode.equals(Config.getParameter("pixiwebservices.shipping.vendor.her")))
			return "HER";
		
		if (mode.equals(Config.getParameter("pixiwebservices.shipping.vendor.abh")))
			return "ABH";
		
		if (mode.equals(Config.getParameter("pixiwebservices.shipping.vendor.spe")))
			return "SPE";
		
		return "";
	}
}