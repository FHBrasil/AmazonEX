package de.fliegersoftware.amazon.core.impl;

import org.springframework.beans.factory.annotation.Required;

import de.fliegersoftware.amazon.core.AmazonUserService;
import de.fliegersoftware.amazon.core.daos.AmazonUserDao;
import de.fliegersoftware.amazon.core.model.AmazonCustomerModel;


public class DefaultAmazonUserService implements AmazonUserService {

	private AmazonUserDao amazonUserDAO;
	
	@Required
    public void setAmazonUserDAO(final AmazonUserDao amazonUserDAO)
    {
        this.amazonUserDAO = amazonUserDAO;
    }

	@Override
	public AmazonCustomerModel getAmazonCustomer(String customerId) {
		return amazonUserDAO.getAmazonCustomer(customerId);
	}

	@Override
	public boolean isAmazonCustomerExisting(String customerId) {
		return amazonUserDAO.isAmazonCustomerExisting(customerId);
	}

}