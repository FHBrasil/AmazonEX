package de.fliegersoftware.amazon.core.facades;

import de.fliegersoftware.amazon.login.addon.data.AmazonLoginRegisterData;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.model.user.CustomerModel;

public interface AmazonUserFacade extends UserFacade {	
	void register(final AmazonLoginRegisterData registerData) throws DuplicateUidException;
	void update(final AmazonLoginRegisterData registerData);
	boolean isUserExisting(String email);
	CustomerModel getAmazonCustomer(String customerId);
	void deleteAmazonCustomer(CustomerData customerData);
	boolean isAmazonCustomerExisting(String customerId);
	void registerGuestUser(final AmazonLoginRegisterData registerData) throws DuplicateUidException;
	void updateManualAddition(final AmazonLoginRegisterData registerData);
	boolean isAmazonCustomer(String email);
}
