/**
 *
 */
package de.kpfamily.services.logistics;

import de.hybris.platform.core.model.order.AbstractOrderModel;


/**
 * @author franthescollymaneira
 *
 */
public interface ExpressDeliveryService
{
	boolean isApplicableForExpressDelivery(AbstractOrderModel abstractOrderModel);
}