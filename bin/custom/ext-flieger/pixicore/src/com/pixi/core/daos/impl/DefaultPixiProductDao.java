/**
 *
 */
package com.pixi.core.daos.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Arrays;
import java.util.List;

import com.pixi.core.daos.PixiProductDao;

import de.kpfamily.core.model.BabyartikelProductModel;


/**
 * @author franthescollymaneira
 *
 */
public class DefaultPixiProductDao extends AbstractItemDao implements PixiProductDao
{
	/*
	 * (non-Javadoc)
	 *
	 * @see com.pixi.core.daos.PixiProductDao#findProductsToExport()
	 */
	@Override
	public List<ProductModel> findProductsToExport()
	{
		final String query = "SELECT {PK} FROM {BabyartikelProduct}";

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
		searchQuery.setResultClassList(Arrays.asList(BabyartikelProductModel.class));
		searchQuery.setStart(0);
		searchQuery.setCount(1);
		searchQuery.setNeedTotal(true);

		final SearchResult<ProductModel> search = search(searchQuery);

		return search.getResult();
	}
}