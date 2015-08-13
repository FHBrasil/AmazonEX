package de.fliegersoftware.amazon.core.facades;

import de.fliegersoftware.amazon.core.model.AmazonCustomerModel;
import de.fliegersoftware.amazon.login.addon.data.AmazonLoginRegisterData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;

public interface AmazonUserFacade {	
	void register(final AmazonLoginRegisterData registerData) throws DuplicateUidException;
	void update(AmazonLoginRegisterData registerData);
	boolean isUserExisting(String email);
	AmazonCustomerModel getAmazonCustomer(String customerId);
	boolean isAmazonCustomerExisting(String customerId);
}
