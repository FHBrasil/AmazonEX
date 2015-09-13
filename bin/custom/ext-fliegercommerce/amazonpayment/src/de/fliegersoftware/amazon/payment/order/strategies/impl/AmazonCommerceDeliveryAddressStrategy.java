package de.fliegersoftware.amazon.payment.order.strategies.impl;

import de.hybris.platform.commerceservices.order.impl.DefaultCommerceDeliveryAddressStrategy;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.AddressModel;

public class AmazonCommerceDeliveryAddressStrategy extends DefaultCommerceDeliveryAddressStrategy {
	
	@Override
	protected boolean isValidDeliveryAddress(CartModel cartModel, AddressModel addressModel)
	  {
	    return true;
	  }

}
