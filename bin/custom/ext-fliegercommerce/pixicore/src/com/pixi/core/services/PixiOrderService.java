/**
 *
 */
package com.pixi.core.services;

import java.util.List;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.store.BaseStoreModel;

/**
 * @author franthescollymaneira
 */
public interface PixiOrderService
{
    
    List<OrderModel> findNotExportedOrders();
    
    
    OrderModel getOrderForCodeAndStore(String code, BaseStoreModel store);
}