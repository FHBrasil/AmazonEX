package de.fliegersoftware.amazon.payment.order.strategies.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import de.fliegersoftware.amazon.core.services.AmazonConfigService;
import de.hybris.platform.commerceservices.order.impl.DefaultCommerceDeliveryAddressStrategy;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.AddressModel;

public class AmazonCommerceDeliveryAddressStrategy extends DefaultCommerceDeliveryAddressStrategy {
	
	private AmazonConfigService amazonConfigService;
	
	@Override
	protected boolean isValidDeliveryAddress(CartModel cartModel, AddressModel addressModel) {
		if(getAmazonConfigService().isExcludePackstationDelivery() //
			&& StringUtils.containsIgnoreCase(addressModel.getFirstname(), "packstation"))
			return false;
		return true;
	}

	protected AmazonConfigService getAmazonConfigService() {
		return amazonConfigService;
	}

	@Required
	public void setAmazonConfigService(AmazonConfigService amazonConfigService) {
		this.amazonConfigService = amazonConfigService;
	}

}
