package com.pixi.webservices.converters.populators.order;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.pixi.core.strategies.PixiOrderGetTransportRemarksStrategy;
import com.pixi.webservices.jaxb.order.export.OrderInfo;
import com.pixi.webservices.jaxb.order.export.Remark;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiOrderRemarksPopulator implements Populator<OrderModel, OrderInfo>
{
	private Logger LOG = Logger.getLogger(DefaultPixiOrderRemarksPopulator.class);
	
	@Resource
	private PixiOrderGetTransportRemarksStrategy pixiOrderGetTransportRemarksStrategy;

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
		
		final String remarks = pixiOrderGetTransportRemarksStrategy.getTransportRemarks(order);
		
		if(StringUtils.isNotBlank(remarks))
		{
			return remarks;
		}
		
		return "we have no transport remarks";
	}
	
	private Remark getDiscountsRemark(final OrderModel order) 
	{
		double discounts = order.getTotalDiscounts().doubleValue();
		BigDecimal totalDiscounts = BigDecimal.valueOf(discounts);
		
		String discount = numberFormat.format(totalDiscounts);
		
		return createRemark("discount", discount);
	}

	private Remark getShippingRemark(final OrderModel order) 
	{
		double dc = order.getDeliveryCost().doubleValue();
		double pc = order.getPaymentCost().doubleValue();

		BigDecimal deliveryCost = BigDecimal.valueOf(dc);
		BigDecimal paymentCost =  BigDecimal.valueOf(pc);
		
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

	/**
	 * @return the pixiOrderGetTransportRemarksStrategy
	 */
	public PixiOrderGetTransportRemarksStrategy getPixiOrderGetTransportRemarksStrategy() 
	{
		return pixiOrderGetTransportRemarksStrategy;
	}

	/**
	 * @param pixiOrderGetTransportRemarksStrategy the pixiOrderGetTransportRemarksStrategy to set
	 */
	public void setPixiOrderGetTransportRemarksStrategy(PixiOrderGetTransportRemarksStrategy pixiOrderGetTransportRemarksStrategy) 
	{
		this.pixiOrderGetTransportRemarksStrategy = pixiOrderGetTransportRemarksStrategy;
	}
}