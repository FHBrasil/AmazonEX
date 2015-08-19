package de.fliegersoftware.amazon.payment.addon.facades.customer.impl;

import de.fliegersoftware.amazon.payment.addon.facades.customer.AmazonCustomerFacade;
import de.fliegersoftware.amazon.payment.services.MWSAmazonPaymentService;
import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;

public class DefaultAmazonCustomerFacade extends DefaultCustomerFacade implements AmazonCustomerFacade {

	@Override
	public void createGuestUserForAnonymousCheckout(String amazonOrderReferenceId) throws DuplicateUidException {
		super.createGuestUserForAnonymousCheckout(amazonOrderReferenceId, "Amazon Guest");
	}
}