/**
 * 
 */
package com.flieger.notificationservices.services.impl;

import de.hybris.platform.core.model.order.CartModel;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.flieger.notificationservices.dao.NotifyMeAbandonedCartDao;
import com.flieger.notificationservices.services.NotifyMeAbandonedCartService;

/**
 * @author Vinicius de Souza
 *
 */
public class DefaultNotifyMeAbandonedCartService extends AbstractNotifyMe implements NotifyMeAbandonedCartService
{
	@Resource
	private NotifyMeAbandonedCartDao notifyMeAbandonedCartDao;
	
	@Override
	public Set<CartModel> getAbandonedCarts(int days) throws Exception
	{
		final Date[] dates = createDatePast(days);
		
		return getAbandonedCarts(dates[0], dates[1]);
	}

	@Override
	public Set<CartModel> getAbandonedCarts(Date from, Date until) throws Exception
	{
		Set<CartModel> result = new HashSet<CartModel>();
		
		for (CartModel cartModel : notifyMeAbandonedCartDao.getCarts(from, until))
		{
			if(CollectionUtils.isNotEmpty(cartModel.getEntries()))
			{
				result.add(cartModel);
			}
		}
		
		return result;
	}
}