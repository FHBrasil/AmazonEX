package br.hering.core.order.impl;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.delivery.DeliveryCostsStrategy;
import de.hybris.platform.order.strategies.calculation.FindDeliveryCostStrategy;
import de.hybris.platform.order.strategies.calculation.impl.DefaultFindDeliveryCostStrategy;
import de.hybris.platform.util.PriceValue;

public class FliegerCommerceFindDeliveryCostStrategy extends DefaultFindDeliveryCostStrategy implements FindDeliveryCostStrategy, DeliveryCostsStrategy
{
	private static final Logger LOG = Logger.getLogger(FliegerCommerceFindDeliveryCostStrategy.class);
	
	@Override
	/**
	 * Find delivery costs for Model layer
	 */
	public PriceValue getDeliveryCost(final AbstractOrderModel order) 
	{
		final PriceValue value = super.getDeliveryCost(order);
		
		LOG.debug(String.format("Order %s splitDeliveryEnabled: %s ", order.getCode(), order.isSplitDeliveryEnabled()));

		if(!order.isSplitDeliveryEnabled())
		{
			return value;
		}
		
		final BigDecimal originalValue = BigDecimal.valueOf(value.getValue());
		final BigDecimal extraCost = BigDecimal.valueOf(3.95); //TODO tem que verificar isso aqui
		final BigDecimal newCost = originalValue.add(extraCost);
		
		final PriceValue newPriceValue = createPriceValue(order, newCost);

		LOG.debug(String.format("Original value %s", value));
		LOG.debug(String.format("New value %s", newPriceValue));
		
		return newPriceValue;
	}
	
	@Override
	/**
	 * Find delivery costs for Jalo layer
	 */
	public PriceValue findDeliveryCosts(final SessionContext ctx, final AbstractOrder orderJalo) 
	{
		final AbstractOrderModel order = getModelService().get(orderJalo.getPK());
		
		return this.getDeliveryCost(order);
	}	

	private PriceValue createPriceValue(final AbstractOrderModel order, final BigDecimal newCost) 
	{
		final String currency = order.getCurrency().getIsocode();
		final boolean net = order.getNet().booleanValue();
		
		return new PriceValue(currency, newCost.doubleValue(), net);
	}
}