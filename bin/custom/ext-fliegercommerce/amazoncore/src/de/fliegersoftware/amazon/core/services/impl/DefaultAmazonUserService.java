package de.fliegersoftware.amazon.core.services.impl;

import org.springframework.beans.factory.annotation.Required;

import de.fliegersoftware.amazon.core.daos.AmazonUserDao;
import de.fliegersoftware.amazon.core.services.AmazonUserService;
import de.hybris.platform.core.model.user.CustomerModel;


public class DefaultAmazonUserService implements AmazonUserService {

	private AmazonUserDao amazonUserDAO;
	
	@Required
    public void setAmazonUserDAO(final AmazonUserDao amazonUserDAO)
    {
        this.amazonUserDAO = amazonUserDAO;
    }

	@Override
	public CustomerModel getAmazonCustomer(String customerId) {
		return amazonUserDAO.getAmazonCustomer(customerId);
	}

	@Override
	public boolean isAmazonCustomerExisting(String customerId) {
		return amazonUserDAO.isAmazonCustomerExisting(customerId);
	}

	@Override
	public boolean isAmazonCustomer(String email) {
		return amazonUserDAO.isAmazonCustomer(email);
	}

}