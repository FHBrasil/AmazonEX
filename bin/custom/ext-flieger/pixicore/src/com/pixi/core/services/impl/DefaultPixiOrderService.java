/**
 *
 */
package com.pixi.core.services.impl;

import de.hybris.platform.core.model.order.OrderModel;

import java.util.List;

import javax.annotation.Resource;

import com.pixi.core.daos.PixiOrderDao;
import com.pixi.core.services.PixiOrderService;


/**
 * @author franthescollymaneira
 *
 */
public class DefaultPixiOrderService implements PixiOrderService
{
	@Resource
	private PixiOrderDao pixiOrderDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pixi.core.services.PixiOrderService#findNotExportedOrders()
	 */
	@Override
	public List<OrderModel> findNotExportedOrders()
	{
		return pixiOrderDao.findOrdersToExport();
	}
}