/**
 * 
 */
package com.flieger.strategies.impl;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.order.strategies.calculation.impl.DefaultFindDeliveryCostStrategy;
import de.hybris.platform.util.PriceValue;

import org.apache.log4j.Logger;

import com.flieger.delivery.CorreiosDeliveryService;
import com.flieger.model.CorreiosDeliveryModeModel;
import com.flieger.strategies.CorreiosDeliveryModeStrategy;
import com.flieger.strategies.FindOrderWeightStrategy;


/**
 * @author alexandresantos
 * 
 */
public class DefaultCorreiosDeliveryModeStrategy extends DefaultFindDeliveryCostStrategy implements CorreiosDeliveryModeStrategy
{

	protected static final Logger LOG = Logger.getLogger(DefaultCorreiosDeliveryModeStrategy.class);

	private CorreiosDeliveryService correiosDeliveryService;
	private FindOrderWeightStrategy findOrderWeightStrategy;

	@Override
	public PriceValue getDeliveryCost(final AbstractOrderModel order)
	{
		double peso = 0;
		if (order.getDeliveryMode() instanceof CorreiosDeliveryModeModel)
		{
			peso = findOrderWeightStrategy.calculateWeight(order);

			return correiosDeliveryService.calculateDeliveryCosts(order, peso);
		}

		return super.getDeliveryCost(order);
	}

	/**
	 * @return the correiosDeliveryService
	 */
	public CorreiosDeliveryService getCorreiosDeliveryService()
	{
		return correiosDeliveryService;
	}

	/**
	 * @param correiosDeliveryService
	 *           the correiosDeliveryService to set
	 */
	public void setCorreiosDeliveryService(final CorreiosDeliveryService correiosDeliveryService)
	{
		this.correiosDeliveryService = correiosDeliveryService;
	}

	/**
	 * @return the findOrderWeightStrategy
	 */
	public FindOrderWeightStrategy getfindOrderWeightStrategy()
	{
		return findOrderWeightStrategy;
	}

	/**
	 * @param findOrderWeightStrategy
	 *           the findOrderWeightStrategy to set
	 */
	public void setfindOrderWeightStrategy(final FindOrderWeightStrategy findOrderWeightStrategy)
	{
		this.findOrderWeightStrategy = findOrderWeightStrategy;
	}

}
