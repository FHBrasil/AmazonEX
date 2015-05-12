package com.flieger.carrier.services.strategies.impl;

import de.hybris.platform.commerceservices.strategies.impl.DefaultDeliveryModeLookupStrategy;
import de.hybris.platform.core.model.order.AbstractOrderModel;

import java.util.List;

import javax.annotation.Resource;

import com.flieger.carrier.dao.impl.DefaultCarrierDeliveryModeDAO;
import com.flieger.carrier.services.CarrierDeliveryService;
import com.flieger.carrier.services.strategies.FindPostalCodeStrategy;


/**
 * The Class DefaultCarrierDeliveryModeLookupStrategy.
 * 
 * @author franthescollymaneira
 */
public class DefaultCarrierDeliveryModeLookupStrategy extends DefaultDeliveryModeLookupStrategy
{
	@Resource
	private CarrierDeliveryService deliveryService;
	
	@Resource
	private FindPostalCodeStrategy findPostalCodeStrategy;
	
	/*
	 * (non-Javadoc)
	 * @see de.hybris.platform.commerceservices.strategies.impl.DefaultDeliveryModeLookupStrategy#getSelectableDeliveryModesForOrder(de.hybris.platform.core.model.order.AbstractOrderModel)
	 */
	@Override
	public List getSelectableDeliveryModesForOrder(final AbstractOrderModel order)
	{
		final String postalCode = findPostalCodeStrategy.findPostalCode(order);
		final double amount = order.getSubtotal().doubleValue();
		final double weight = deliveryService.calculateTotalWeight(order);
		
		return getCarrierDao().findDeliveryModes(postalCode, weight, amount);
	}
	
	/**
	 * @return
	 */
	private DefaultCarrierDeliveryModeDAO getCarrierDao()
	{
		return (DefaultCarrierDeliveryModeDAO) getCountryZoneDeliveryModeDao();
	}
}