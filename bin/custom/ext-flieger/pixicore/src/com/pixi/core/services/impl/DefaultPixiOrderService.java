/**
 *
 */
package com.pixi.core.services.impl;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.List;

import javax.annotation.Resource;

import com.pixi.core.daos.PixiOrderDao;
import com.pixi.core.services.PixiOrderService;


/**
 * @author franthescollymaneira
 *
 */
public class DefaultPixiOrderService extends AbstractBusinessService implements PixiOrderService
{
	@Resource
	private PixiOrderDao pixiOrderDao;

	@Resource
	private BaseStoreService baseStoreService;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.pixi.core.services.PixiOrderService#findNotExportedOrders()
	 */
	@Override
	public List<OrderModel> findNotExportedOrders()
	{
		final BaseStoreModel store = baseStoreService.getCurrentBaseStore();

		final List<OrderModel> orders = pixiOrderDao.findOrdersToExport(store);

		return orders;
	}
}