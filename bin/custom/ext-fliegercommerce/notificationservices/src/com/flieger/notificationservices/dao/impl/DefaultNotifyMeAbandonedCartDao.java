/**
 * 
 */
package com.flieger.notificationservices.dao.impl;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.flieger.notificationservices.dao.NotifyMeAbandonedCartDao;

/**
 * @author Vinicius de Souza
 *
 */
public class DefaultNotifyMeAbandonedCartDao extends AbstractItemDao implements NotifyMeAbandonedCartDao
{
	@Override
	public Set<CartModel> getCarts(Date from, Date until) throws Exception
	{
		final String fs = "SELECT {PK} FROM {Cart} WHERE {Date} BETWEEN ?from AND ?until";
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("from", from);
		param.put("until", until);
		
		final FlexibleSearchQuery query = new FlexibleSearchQuery(fs, param);
		
		final SearchResult<CartModel> result = getFlexibleSearchService().search(query);
		
		return new HashSet<CartModel>(result.getResult());
	}
}