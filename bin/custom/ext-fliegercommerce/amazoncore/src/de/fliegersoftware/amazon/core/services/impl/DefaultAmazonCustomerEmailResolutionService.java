package de.fliegersoftware.amazon.core.services.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;

import de.hybris.platform.commerceservices.customer.CustomerEmailResolutionService;
import de.hybris.platform.commerceservices.customer.impl.DefaultCustomerEmailResolutionService;
import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.util.mail.MailUtils;


public class DefaultAmazonCustomerEmailResolutionService extends DefaultCustomerEmailResolutionService implements CustomerEmailResolutionService {
	private static final Logger LOG = Logger.getLogger(DefaultAmazonCustomerEmailResolutionService.class);
	
	protected String validateAndProcessEmailForCustomer(CustomerModel customerModel) {
		ServicesUtil.validateParameterNotNullStandardMessage("customerModel",
				customerModel);

		String email = (CustomerType.GUEST.equals(customerModel.getType()) || (customerModel.getUid().contains("|") && StringUtils.isNotEmpty(customerModel.getAmazonCustomerId()))) ? StringUtils
				.substringAfter(customerModel.getUid(), "|") : customerModel
				.getUid();
				try {
					MailUtils.validateEmailAddress(email, "customer email");
					return email;
				} catch (EmailException e) {
					LOG.info("Given uid is not appropriate email [" + email
							+ "]. cause: " + e.getMessage());
				}
				return null;
	}

}