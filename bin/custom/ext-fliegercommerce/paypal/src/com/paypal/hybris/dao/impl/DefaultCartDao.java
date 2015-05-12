package com.paypal.hybris.dao.impl;


import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.paypal.hybris.dao.PaypalCartDao;


/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
@Component("paypalCartDao")
public class DefaultCartDao implements PaypalCartDao {

@Resource
private FlexibleSearchService flexibleSearchService;


/**
 * @param flexibleSearchService
 *          the flexibleSearchService to set
 */
public void setFlexibleSearchService(
		final FlexibleSearchService flexibleSearchService) {

	this.flexibleSearchService = flexibleSearchService;
}


@Override
public CartModel getCartBySid(final String cartSid) {

	final String query = "select {pk} from {cart as c} where {c.sessionid} = "
			+ "?cartSid order by {c.creationtime} desc";
	final Map<String, Object> params = new HashMap<String, Object>();
	params.put("cartSid", cartSid);

	final SearchResult<CartModel> sResult = flexibleSearchService.search(query,
			params);
	final List<CartModel> carts = sResult.getResult();
	if (carts != null && carts.size() > 0) {
		return carts.get(0);
	} else {
		return null;
	}
}

}
