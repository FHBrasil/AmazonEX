package de.fliegersoftware.amazon.core.facades;

import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;

public interface AmazonCustomerFacade extends CustomerFacade {

	void updateCartWithUserForAnonymousCheckout(String amazonCustomerId, String email, String name) throws DuplicateUidException;

}