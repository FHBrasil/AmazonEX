/**
 * 
 */
package com.flieger.strategies;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.order.strategies.calculation.FindDeliveryCostStrategy;
import de.hybris.platform.util.PriceValue;


/**
 * @author alexandresantos
 * 
 */
public interface CorreiosDeliveryModeStrategy extends FindDeliveryCostStrategy
{


	@Override
	public PriceValue getDeliveryCost(AbstractOrderModel order);

}
