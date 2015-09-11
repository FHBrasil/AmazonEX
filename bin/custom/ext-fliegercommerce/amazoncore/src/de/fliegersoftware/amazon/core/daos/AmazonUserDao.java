package de.fliegersoftware.amazon.core.daos;

import de.fliegersoftware.amazon.core.model.AmazonCustomerModel;

public interface AmazonUserDao {
	AmazonCustomerModel getAmazonCustomer(String customerId);
	boolean isAmazonCustomerExisting(String customerId);
}
