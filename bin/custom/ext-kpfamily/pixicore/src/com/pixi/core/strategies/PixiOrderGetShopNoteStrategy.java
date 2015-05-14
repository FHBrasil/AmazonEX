/**
 *
 */
package com.pixi.core.strategies;

import de.hybris.platform.core.model.order.OrderModel;


/**
 * @author franthescollymaneira
 *
 */
public interface PixiOrderGetShopNoteStrategy
{
	String getShopNote(OrderModel order);
}