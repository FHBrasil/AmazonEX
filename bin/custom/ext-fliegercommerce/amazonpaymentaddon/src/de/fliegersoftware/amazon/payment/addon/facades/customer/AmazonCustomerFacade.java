package de.fliegersoftware.amazon.payment.addon.facades.customer;

import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;

public interface AmazonCustomerFacade extends CustomerFacade {

	public void createGuestUserForAnonymousCheckout(String amazonOrderReferenceId) throws DuplicateUidException;
}