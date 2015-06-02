package com.paypal.hybris.reauthorization;

import de.hybris.platform.core.model.order.OrderModel;


/**
 * @author Dzmitry_Rasolka (EPAM Systems)
 */
public interface PayPalOrderReauthorizationService
{

	/**
	 * Check whether it is possible to do reauthorization for order
	 *
	 * @param orderModel
	 * @return is reauthorization possible
	 */
	boolean isReauthorizationPossible(OrderModel orderModel);

	/**
	 * Order reauthorization for specified amount
	 *
	 * @param orderModel
	 * @param amount
	 *           reauthorization amount
	 * @return order model
	 */
	OrderModel reauthorize(OrderModel orderModel, String amount);

}
