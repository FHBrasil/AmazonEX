/**
 *
 */
package com.pixi.core.daos;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.internal.dao.Dao;

import java.util.List;


/**
 * @author franthescollymaneira
 *
 */
public interface PixiOrderDao extends Dao
{
	List<OrderModel> findOrdersToExport();
}