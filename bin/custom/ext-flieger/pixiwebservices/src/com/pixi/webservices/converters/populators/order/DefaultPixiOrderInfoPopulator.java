package com.pixi.webservices.converters.populators.order;

import org.apache.log4j.Logger;

import com.pixi.webservices.jaxb.order.export.Order;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiOrderInfoPopulator implements Populator<OrderModel, Order>
{
	private Logger LOG = Logger.getLogger(DefaultPixiOrderInfoPopulator.class);
	@Override
	public void populate(OrderModel source, Order target) throws ConversionException 
	{
		LOG.info("populating");
	}
}
