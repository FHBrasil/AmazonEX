package de.fliegersoftware.amazon.payment.order.strategies.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.strategies.impl.DefaultCreateOrderFromCartStrategy;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

public class AmazonCreateOrderFromCartStrategy extends DefaultCreateOrderFromCartStrategy {

	private FlexibleSearchService flexibleSearchService;

	@Override
	public OrderModel createOrderFromCart(CartModel cart)
			throws InvalidCartException {
		final StringBuilder query = new StringBuilder();
		query.append("select {o.pk} ") //
				.append("from {order as o join cart as c on {c.preCreatedOrder} = {o.pk}} ")
				.append("where {c.pk} = ?cart");
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("cart", cart);
		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query.toString(), queryParams);
		SearchResult<OrderModel> result = flexibleSearchService.search(searchQuery);
		if(result.getCount() == 1) {
			return result.getResult().get(0);
		} else {
			return super.createOrderFromCart(cart);
		}
	}

	protected FlexibleSearchService getFlexibleSearchService() {
		return flexibleSearchService;
	}

	@Required
	public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
		this.flexibleSearchService = flexibleSearchService;
	}
}