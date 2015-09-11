/**
 *
 */
package com.pixi.core.daos;

import java.util.List;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.internal.dao.Dao;
import de.hybris.platform.store.BaseStoreModel;

/**
 * @author franthescollymaneira
 */
public interface PixiOrderDao extends Dao
{
    
    List<OrderModel> findOrdersToExport(BaseStoreModel store);
    
    
    OrderModel getOrderForCodeAndStore(String code, BaseStoreModel store);
}