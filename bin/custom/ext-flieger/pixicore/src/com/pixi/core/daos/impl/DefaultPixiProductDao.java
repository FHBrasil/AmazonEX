/**
 *
 */
package com.pixi.core.daos.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pixi.core.daos.PixiProductDao;
import com.pixi.core.model.PixiSyncRecordModel;

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
	 * @see com.pixi.core.daos.PixiProductDao#findProductsToExport(de.hybris.platform.catalog.model.CatalogVersionModel)
	 */
	@Override
	public List<ProductModel> findProductsToExport(final CatalogVersionModel catalogVersion)
	{
		//TODO falta implementar aqui

		final String query = "SELECT {p:PK} FROM {BabyartikelProduct as p} WHERE {p:catalogVersion} = ?version";

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
		searchQuery.setResultClassList(Arrays.asList(BabyartikelProductModel.class));
		searchQuery.setStart(0);
		searchQuery.setCount(1);
		searchQuery.setNeedTotal(true);

		searchQuery.addQueryParameter("version", catalogVersion);

		final SearchResult<ProductModel> search = search(searchQuery);

		return search.getResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pixi.core.daos.PixiProductDao#getAllProductsCode(de.hybris.platform.catalog.model.CatalogVersionModel)
	 */
	@Override
	public List<String> getAllProductsCode(final CatalogVersionModel catalogVersion)
	{
		final String query = "SELECT DISTINCT {p:code} FROM {Product as p} WHERE {p:catalogVersion} = ?version";

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
		searchQuery.addQueryParameter("version", catalogVersion);

		final SearchResult<String> search = search(searchQuery);

		return search.getResult();
	}

	@Override
	public List<String> findExportedProductsBySessionID(final String sessionID)
	{
		final String query = "SELECT {r:productCode} FROM {PixiSyncRecord AS r} WHERE {r:sessionID} = ?session";

		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("session", sessionID);

		final SearchResult<String> result = search(query, params);

		return result.getResult();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.pixi.core.daos.PixiProductDao#findSyncRecord(java.lang.String)
	 */
	@Override
	public PixiSyncRecordModel findSyncRecord(final String productCode)
	{
		final String query = "SELECT {r:PK} FROM {PixiSyncRecord as r} WHERE {r:productCode} = ?code";

		final FlexibleSearchQuery fs = new FlexibleSearchQuery(query);
		fs.addQueryParameter("code", productCode);

		return searchUnique(fs);
	}
}