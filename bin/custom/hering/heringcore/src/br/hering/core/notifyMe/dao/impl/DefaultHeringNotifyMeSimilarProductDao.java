/**
 * 
 */
package br.hering.core.notifyMe.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.Map;

import br.hering.core.model.HeringProductModel;
import br.hering.core.notifyMe.dao.HeringNotifyMeSimilarProductDao;

/**
 * @author Vinicius de Souza
 *
 */
public class DefaultHeringNotifyMeSimilarProductDao extends AbstractItemDao implements HeringNotifyMeSimilarProductDao
{
	@Override
	public HeringProductModel getProductForCode(String code) throws Exception
	{
		final String fs = "SELECT {PK} FROM {Product} WHERE {code}=?code";
		
		final Map<String, Object> param = new HashMap<String, Object>();
		param.put("code", code);
		
		final FlexibleSearchQuery query = new FlexibleSearchQuery(fs, param);
		
		final SearchResult<HeringProductModel> result = getFlexibleSearchService().search(query);
		
		for (HeringProductModel model : result.getResult())
		{
			return model;
		}
		
		return null;
	}
}