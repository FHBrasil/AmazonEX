/**
 * 
 */
package com.flieger.facades.impl;

import org.apache.log4j.Logger;

import com.flieger.facades.CorreiosFacade;
import com.flieger.strategies.impl.DefaultCorreiosDeliveryModeStrategy;


/**
 * @author Alexandre Santos
 * 
 */
public class DefaultCorreiosFacade implements CorreiosFacade
{
	protected static final Logger LOG = Logger.getLogger(DefaultCorreiosFacade.class);

	private static final String CLASSIFICATION_WEIGHT = "BabyartikelClassification/1.0/40.weight, 94";

	private DefaultCorreiosDeliveryModeStrategy correiosDeliveryModeStrategy;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kpfamily.facades.checkout.correio.CorreiosFacade#calculateDeliveryCosts()
	 */
	@Override
	public void calculateDeliveryCosts()
	{
		correiosDeliveryModeStrategy.getDeliveryCost(null);
	}

	/**
	 * @return the correiosDeliveryModeStrategy
	 */
	public DefaultCorreiosDeliveryModeStrategy getCorreiosDeliveryModeStrategy()
	{
		return correiosDeliveryModeStrategy;
	}

	/**
	 * @param correiosDeliveryModeStrategy
	 *           the correiosDeliveryModeStrategy to set
	 */
	public void setCorreiosDeliveryModeStrategy(final DefaultCorreiosDeliveryModeStrategy correiosDeliveryModeStrategy)
	{
		this.correiosDeliveryModeStrategy = correiosDeliveryModeStrategy;
	}

}
