/**
 * 
 */
package br.hering.core.order.dao.impl;


import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.order.daos.impl.DefaultOrderDao;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.Map;

import br.hering.core.order.dao.HeringOrderDao;

/**
 * 
 */
public class DefaultHeringOrderDao extends DefaultOrderDao implements HeringOrderDao
{

	/* (non-Javadoc)
	 * @see br.hering.core.order.dao.HeringOrderDao#getOrderStatusByCode(java.lang.String)
	 */
	@Override
	public OrderStatus findOrderStatusByCode(String code)
	{
		validateParameterNotNull(code, "code must not be null!");
		
		final Map queryParams = new HashMap();
		queryParams.put("code", code);
		
		SearchResult result = getFlexibleSearchService().search("SELECT * FROM {OrderStatus} WHERE {code} = ?code",
				queryParams);
		
		if(result.getCount() > 0){
			try
			{
				OrderStatus o1 = (OrderStatus)result.getResult().get(0);
				System.out.println(o1);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				br.hering.core.constants.GeneratedHeringCoreConstants.Enumerations.OrderStatus o1 = (br.hering.core.constants.GeneratedHeringCoreConstants.Enumerations.OrderStatus)result.getResult().get(0);
				System.out.println(o1);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				de.hybris.platform.basecommerce.constants.GeneratedBasecommerceConstants.Enumerations.OrderStatus o1 = (de.hybris.platform.basecommerce.constants.GeneratedBasecommerceConstants.Enumerations.OrderStatus)result.getResult().get(0);
				System.out.println(o1);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		
		return ((result.getCount() > 0) ? (OrderStatus) result.getResult().get(0) : null);
	}

}
