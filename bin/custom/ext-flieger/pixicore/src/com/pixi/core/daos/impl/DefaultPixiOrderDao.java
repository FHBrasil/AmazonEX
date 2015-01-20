/**
 *
 */
package com.pixi.core.daos.impl;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Arrays;
import java.util.List;

import com.pixi.core.daos.PixiOrderDao;


/**
 * @author franthescollymaneira
 *
 */
public class DefaultPixiOrderDao extends DefaultGenericDao<OrderModel> implements PixiOrderDao
{
	/**
	 * @param typecode
	 */
	public DefaultPixiOrderDao(final String typecode)
	{
		super(typecode);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.pixi.core.daos.PixiOrderDao#findOrdersToExport(de.hybris.platform.store.BaseStoreModel)
	 */
	@Override
	public List<OrderModel> findOrdersToExport(final BaseStoreModel store)
	{
		//TODO falta implementar filtro de order para exportacao
		final String query = "SELECT {PK} FROM {Order}";

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
		searchQuery.setResultClassList(Arrays.asList(OrderModel.class));
		searchQuery.setStart(0);
		searchQuery.setCount(1);
		searchQuery.setNeedTotal(true);

		final SearchResult<OrderModel> search = getFlexibleSearchService().search(searchQuery);

		return search.getResult();
	}
}