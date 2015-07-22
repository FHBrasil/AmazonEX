package com.pixi.webservices.converters.populators.order;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.BooleanUtils;
import org.apache.log4j.Logger;

import com.pixi.core.strategies.PixiOrderGetItemNoteStrategy;
import com.pixi.webservices.constants.PixiwebservicesConstants;
import com.pixi.webservices.jaxb.order.export.Order;
import com.pixi.webservices.jaxb.order.export.OrderEntryPrice;
import com.pixi.webservices.jaxb.order.export.OrderItem;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiOrderEntriesPopulator implements Populator<OrderModel, Order>
{
	private Logger LOG = Logger.getLogger(DefaultPixiOrderEntriesPopulator.class);
	
	@Resource
	private PixiOrderGetItemNoteStrategy pixiOrderGetItemNoteStrategy;
	
	@Override
	public void populate(OrderModel source, Order target) throws ConversionException 
	{
		LOG.info("populating");
		
		List<OrderItem> orderitems = target.getORDERITEM();

		for(AbstractOrderEntryModel entry : source.getEntries())
		{
			final OrderItem orderItem = new OrderItem();
			orderItem.setDESCRIPTIONSHORT(entry.getProduct().getName());
			orderItem.setLINEITEMID(entry.getPk().getLongValueAsString());
			orderItem.setORDERUNIT(entry.getUnit().getName());
			orderItem.setQUANTITY(entry.getQuantity().intValue());
			orderItem.setSUPPLIERAID(entry.getProduct().getCode());
			orderItem.setITEMNOTE(getPixiOrderGetItemNoteStrategy().getItemNoteByOrderEntry(entry));
			orderItem.setARTICLEPRICE(createArticlePrice(entry));
			
			orderitems.add(orderItem);
		}
	}

	private OrderEntryPrice createArticlePrice(AbstractOrderEntryModel entry) 
	{
		final OrderEntryPrice articlePrice = new OrderEntryPrice();
		articlePrice.setDISCOUNTPERC(BigDecimal.ZERO);
		articlePrice.setDISCOUNTVALUE(BigDecimal.ZERO);
		articlePrice.setFULLPRICE(getFullPrice(entry));
		articlePrice.setPRICEAMOUNT(getBasePrice(entry));
		articlePrice.setType(PixiwebservicesConstants.Order.PRICE_TYPE);
		
		return articlePrice;
	}

	private BigDecimal getBasePrice(AbstractOrderEntryModel entry) 
	{
		if(BooleanUtils.isTrue(entry.getGiveAway()))
		{
			return BigDecimal.ZERO;
		}

		double price = entry.getBasePrice().doubleValue();
		
		return BigDecimal.valueOf(price);
	}	

	private BigDecimal getFullPrice(AbstractOrderEntryModel entry) 
	{
		if(BooleanUtils.isTrue(entry.getGiveAway()))
		{
			return BigDecimal.ZERO;
		}
		
		double price = Double.valueOf(entry.getTotalPrice().toString());
		return BigDecimal.valueOf(price);
	}

	/**
	 * @return the pixiOrderGetItemNoteStrategy
	 */
	public PixiOrderGetItemNoteStrategy getPixiOrderGetItemNoteStrategy() {
		return pixiOrderGetItemNoteStrategy;
	}

	/**
	 * @param pixiOrderGetItemNoteStrategy the pixiOrderGetItemNoteStrategy to set
	 */
	public void setPixiOrderGetItemNoteStrategy(
			PixiOrderGetItemNoteStrategy pixiOrderGetItemNoteStrategy) {
		this.pixiOrderGetItemNoteStrategy = pixiOrderGetItemNoteStrategy;
	}	
}