/**
 * 
 */
package com.flieger.carrier.services.strategies.impl;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.session.SessionService;

import javax.annotation.Resource;

import com.flieger.carrier.constants.CarrierConstants;
import com.flieger.carrier.services.strategies.FindPostalCodeStrategy;

/**
 * @author franthescollymaneira
 *
 */
public class DefaultFindPostalCodeStrategy implements FindPostalCodeStrategy
{
	@Resource
	private SessionService sessionService;
	
	/* (non-Javadoc)
	 * @see br.hering.core.strategies.FindPostalCodeStrategy#findPostalCode(de.hybris.platform.core.model.order.AbstractOrderModel)
	 */
	@Override
	public String findPostalCode(AbstractOrderModel order)
	{
		if(order.getDeliveryAddress() != null)
		{
			return order.getDeliveryAddress().getPostalcode();
		}
		
		return sessionService.getAttribute(CarrierConstants.SESSION_ATTR_POSTALCODE);
	}
}