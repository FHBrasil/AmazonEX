package de.fliegersoftware.amazon.core;

import de.fliegersoftware.amazon.core.model.AmazonCustomerModel;

public interface AmazonUserService {	
	AmazonCustomerModel getAmazonCustomer(String customerId);	
	boolean isAmazonCustomerExisting(String customerId);
}
