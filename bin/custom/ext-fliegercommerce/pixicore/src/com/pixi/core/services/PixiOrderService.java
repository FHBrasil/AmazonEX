/**
 *
 */
package com.pixi.core.services;

import de.hybris.platform.core.model.order.OrderModel;

import java.util.List;


/**
 * @author franthescollymaneira
 *
 */
public interface PixiOrderService
{
	List<OrderModel> findNotExportedOrders();
}