package de.fliegersoftware.amazon.core.daos;

import de.hybris.platform.core.model.user.CustomerModel;

public interface AmazonUserDao {
	CustomerModel getAmazonCustomer(String customerId);
	boolean isAmazonCustomerExisting(String customerId);
	boolean isAmazonCustomer(String email);
}
