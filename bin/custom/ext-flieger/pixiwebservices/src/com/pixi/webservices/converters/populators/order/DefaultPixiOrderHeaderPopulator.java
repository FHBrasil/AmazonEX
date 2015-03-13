package com.pixi.webservices.converters.populators.order;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.pixi.webservices.constants.PixiwebservicesConstants;
import com.pixi.webservices.jaxb.order.export.Order;
import com.pixi.webservices.jaxb.order.export.OrderHeader;
import com.pixi.webservices.jaxb.order.export.OrderInfo;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class DefaultPixiOrderHeaderPopulator implements Populator<OrderModel, Order>
{
	private Logger LOG = Logger.getLogger(DefaultPixiOrderHeaderPopulator.class);
	
	@Resource
	private Converter<OrderModel, OrderInfo> pixiOrderInfoConverter;
	
	@Override
	public void populate(OrderModel source, Order target) throws ConversionException 
	{
		LOG.info("populating");

		final OrderHeader orderHeader = new OrderHeader();
		orderHeader.setGENERATIONDATE(new Date());
		orderHeader.setGENERATORINFO(PixiwebservicesConstants.Order.GENERATOR_INFO);
		orderHeader.setORDERINFO(pixiOrderInfoConverter.convert(source));

		target.setORDERHEADER(orderHeader);
		target.setTOTALITEMNUM(CollectionUtils.size(source.getEntries()));
		target.setType(PixiwebservicesConstants.Order.TYPE);
		target.setVersion(BigDecimal.valueOf(PixiwebservicesConstants.Order.VERSION));
	}	
}