package com.pixi.webservices.converters.populators.order;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.pixi.core.strategies.PixiOrderGetShopNoteStrategy;
import com.pixi.webservices.jaxb.order.export.OrderInfo;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiOrderShopNotePopulator implements Populator<OrderModel, OrderInfo> 
{
	private Logger LOG = Logger.getLogger(DefaultPixiOrderShopNotePopulator.class);

	@Resource
	private PixiOrderGetShopNoteStrategy pixiOrderGetShopNoteStrategy;
	
	@Override
	public void populate(final OrderModel source, final OrderInfo target) throws ConversionException 
	{
		LOG.info("populating");
		
		final String shopNote = getPixiOrderGetShopNoteStrategy().getShopNote(source);
		
		target.setSHOPNOTE(shopNote);
	}

	/**
	 * @return the pixiOrderGetShopNoteStrategy
	 */
	public PixiOrderGetShopNoteStrategy getPixiOrderGetShopNoteStrategy() 
	{
		return pixiOrderGetShopNoteStrategy;
	}

	/**
	 * @param pixiOrderGetShopNoteStrategy the pixiOrderGetShopNoteStrategy to set
	 */
	public void setPixiOrderGetShopNoteStrategy(final PixiOrderGetShopNoteStrategy pixiOrderGetShopNoteStrategy) 
	{
		this.pixiOrderGetShopNoteStrategy = pixiOrderGetShopNoteStrategy;
	}
}