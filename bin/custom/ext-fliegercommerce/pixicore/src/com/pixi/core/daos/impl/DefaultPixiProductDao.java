/**
 *
 */
package com.pixi.core.daos.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pixi.core.daos.PixiProductDao;
import com.pixi.core.model.PixiSyncRecordModel;


/**
 * @author franthescollymaneira
 *
 */
public class DefaultPixiProductDao extends DefaultGenericDao<ProductModel> implements PixiProductDao
{
	/**
	 * @param typecode
	 */
	public DefaultPixiProductDao(final String typecode)
	{
		super(typecode);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.pixi.core.daos.PixiProductDao#findProductsToExport(de.hybris.platform.catalog.model.CatalogVersionModel)
	 */
	@Override
	public List<ProductModel> findProductsToExport(final CatalogVersionModel catalogVersion)
	{
		//TODO falta implementar aqui

		final String query = "SELECT {p:PK} FROM {Product as p} WHERE {p:catalogVersion} = ?version";

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
		searchQuery.addQueryParameter("version", catalogVersion);

		final SearchResult<ProductModel> search = getFlexibleSearchService().search(searchQuery);

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

		final SearchResult<String> search = getFlexibleSearchService().search(searchQuery);

		return search.getResult();
	}

	@Override
	public List<String> findExportedProductsBySessionID(final String sessionID)
	{
		final String query = "SELECT {r:productCode} FROM {PixiSyncRecord AS r} WHERE {r:sessionID} = ?session";

		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("session", sessionID);

		final SearchResult<String> result = getFlexibleSearchService().search(query, params);

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

		return getFlexibleSearchService().searchUnique(fs);
	}
}