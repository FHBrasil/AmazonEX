/**
 *
 */
package de.kpfamily.services.delivery;

import de.hybris.platform.core.model.order.AbstractOrderModel;


/**
 * @author franthescollymaneira
 *
 */
public interface ExpressDeliveryService
{
	boolean isApplicableForExpressDelivery(AbstractOrderModel abstractOrderModel);
}