package de.fliegersoftware.amazon.core.services;

import de.hybris.platform.core.model.user.CustomerModel;

public interface AmazonUserService {	
	CustomerModel getAmazonCustomer(String customerId);	
	boolean isAmazonCustomerExisting(String customerId);
}
