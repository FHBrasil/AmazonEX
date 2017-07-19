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
		if (addressModel != null && getAmazonConfigService().isExcludePackstationDelivery()) {
			
			if (StringUtils.containsIgnoreCase(addressModel.getFirstname(), "packstation")) {
				return false;
			}
			
			String[] packstationIdentifiers = {};
			if (getAmazonConfigService().getPackstationIdentifier() != null) {
				packstationIdentifiers = getAmazonConfigService().getPackstationIdentifier().split(";");
			}

			for (String packstationIdentifier : packstationIdentifiers) {
				if (StringUtils.containsIgnoreCase(addressModel.getLine1(), packstationIdentifier)
						|| StringUtils.containsIgnoreCase(addressModel.getLine2(), packstationIdentifier))
					return false;
			}

		}

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
