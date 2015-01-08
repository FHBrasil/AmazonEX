/**
 *
 */
package de.kpfamily.services.logistics.impl;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.kpfamily.services.logistics.ExpressDeliveryService;


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
	 * de.kpfamily.services.logistics.ExpressDeliveryService#isApplicableForExpressDelivery(de.hybris.platform.core.model
	 * .order.AbstractOrderModel)
	 */
	@Override
	public boolean isApplicableForExpressDelivery(final AbstractOrderModel abstractOrderModel)
	{
		return true;
	}
}
