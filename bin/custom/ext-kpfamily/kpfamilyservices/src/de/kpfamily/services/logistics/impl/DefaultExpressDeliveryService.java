/**
 *
 */
package de.kpfamily.services.logistics.impl;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.kpfamily.services.logistics.ExpressDeliveryService;


/**
 * @author franthescollymaneira
 *
 */
public class DefaultExpressDeliveryService extends AbstractBusinessService implements ExpressDeliveryService
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
