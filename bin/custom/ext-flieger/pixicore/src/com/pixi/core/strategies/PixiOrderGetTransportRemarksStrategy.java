/**
 *
 */
package com.pixi.core.strategies;

import de.hybris.platform.core.model.order.OrderModel;


/**
 * @author franthescollymaneira
 *
 */
public interface PixiOrderGetTransportRemarksStrategy
{
	/**
	 * @param order
	 * @return
	 */
	String getTransportRemarks(final OrderModel order);
}
