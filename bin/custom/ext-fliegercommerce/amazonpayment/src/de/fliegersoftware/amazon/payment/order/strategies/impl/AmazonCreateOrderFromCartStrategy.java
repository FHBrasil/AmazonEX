package de.fliegersoftware.amazon.payment.order.strategies.impl;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.strategies.impl.DefaultCreateOrderFromCartStrategy;

public class AmazonCreateOrderFromCartStrategy extends DefaultCreateOrderFromCartStrategy {

	@Override
	protected String generateOrderCode(CartModel cart) {
		if(cart.getPreCreatedOrderCode() != null)
			return cart.getPreCreatedOrderCode();
		else
			return super.generateOrderCode(cart);
	}
}