/**
 * 
 */
package com.flieger.notificationservices.dao.impl;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.flieger.notificationservices.dao.NotifyMePaymentDao;

/**
 * @author Vinicius de Souza
 *
 */
public class DefaultNotifyMePaymentDao extends AbstractItemDao implements NotifyMePaymentDao
{
	
	@Override
	public Set<OrderModel> getOrders(OrderStatus status) throws Exception
	{
		final StringBuilder fs = new StringBuilder("SELECT {PK} FROM {Order} WHERE {status} = ?status");
		//final StringBuilder fs = new StringBuilder("SELECT {PK} FROM {Order} WHERE {code} = '00384001'");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", status);
		
		final FlexibleSearchQuery query = new FlexibleSearchQuery(fs.toString(), params);
		
		SearchResult<OrderModel> result = getFlexibleSearchService().search(query);
		
		return new HashSet<OrderModel>(result.getResult());
	}
}
