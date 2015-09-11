package com.flieger.carrier.order.strategies.calculation.impl;

import de.hybris.platform.basecommerce.jalo.DefaultMultiAddressDeliveryCostsStrategy;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.order.strategies.calculation.FindDeliveryCostStrategy;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.util.PriceValue;

import javax.annotation.Resource;


/**
 * @author franthescollymaneira
 * 
 */
public class DefaultJaloCarrierFindDeliveryCostStrategy extends DefaultMultiAddressDeliveryCostsStrategy
{
	@Resource
	private ModelService modelService;
	
	@Resource
	FindDeliveryCostStrategy findDeliveryCostStrategy;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.basecommerce.jalo.DefaultMultiAddressDeliveryCostsStrategy#findDeliveryCosts(de.hybris.platform
	 * .jalo.SessionContext, de.hybris.platform.jalo.order.AbstractOrder)
	 */
	@Override
	public PriceValue findDeliveryCosts(SessionContext ctx, AbstractOrder orderJalo)
	{
		final AbstractOrderModel order = getOrderModelFromJalo(orderJalo);
		
		return findDeliveryCostStrategy.getDeliveryCost(order);
	}	

	/**
	 * @param orderJalo
	 * @return
	 */
	private AbstractOrderModel getOrderModelFromJalo(AbstractOrder orderJalo)
	{
		return modelService.get(orderJalo.getPK());
	}
}