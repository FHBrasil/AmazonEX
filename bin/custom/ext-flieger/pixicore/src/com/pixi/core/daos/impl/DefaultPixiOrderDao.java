/**
 *
 */
package com.pixi.core.daos.impl;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Arrays;
import java.util.List;

import com.pixi.core.daos.PixiOrderDao;


/**
 * @author franthescollymaneira
 *
 */
public class DefaultPixiOrderDao extends AbstractItemDao implements PixiOrderDao
{
	/*
	 * (non-Javadoc)
	 *
	 * @see com.pixi.core.daos.PixiOrderDao#findOrdersToExport()
	 */
	@Override
	public List<OrderModel> findOrdersToExport()
	{
		final String query = "SELECT {PK} FROM {Order}";

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
		searchQuery.setResultClassList(Arrays.asList(OrderModel.class));
		searchQuery.setStart(0);
		searchQuery.setCount(1);
		searchQuery.setNeedTotal(true);

		final SearchResult<OrderModel> search = search(searchQuery);

		return search.getResult();
	}
}