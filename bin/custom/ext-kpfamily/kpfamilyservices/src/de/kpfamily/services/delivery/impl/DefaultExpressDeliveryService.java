/**
 *
 */
package de.kpfamily.services.delivery.impl;

import de.hybris.platform.core.model.order.AbstractOrderModel;

import de.kpfamily.services.delivery.ExpressDeliveryService;


/**
 * @author franthescollymaneira
 *
 */
public class DefaultExpressDeliveryService implements ExpressDeliveryService
{
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.kpfamily.services.delivery.ExpressDeliveryService#isApplicableForExpressDelivery(de.hybris.platform.core.model
	 * .order.AbstractOrderModel)
	 */
	@Override
	public boolean isApplicableForExpressDelivery(final AbstractOrderModel abstractOrderModel)
	{
		return true;
	}
}
