package de.fliegersoftware.amazon.core.facades.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;
import de.fliegersoftware.amazon.core.facades.AmazonCustomerFacade;
import de.fliegersoftware.amazon.core.facades.AmazonUserFacade;
import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.model.user.CustomerModel;

public class DefaultAmazonCustomerFacade extends DefaultCustomerFacade implements AmazonCustomerFacade {
	
	private AmazonUserFacade amazonUserFacade;
	
	@Override
	public void updateCartWithUserForAnonymousCheckout(String amazonCustomerId, String email, String name) throws DuplicateUidException {
		validateParameterNotNullStandardMessage("email", email);
		validateParameterNotNullStandardMessage("amazonCustomerId", amazonCustomerId);
		
		if (amazonUserFacade.isAmazonCustomerExisting(amazonCustomerId)) {
			
			CustomerModel amazonCustomerModel = amazonUserFacade.getAmazonCustomer(amazonCustomerId);
			getCustomerAccountService().registerGuestForAnonymousCheckout(amazonCustomerModel, amazonCustomerModel.getUid());
			updateCartWithGuestForAnonymousCheckout(getCustomerConverter().convert(amazonCustomerModel));
		} else {
			super.createGuestUserForAnonymousCheckout(email, name);
		}
	}

	public AmazonUserFacade getAmazonUserFacade() {
		return amazonUserFacade;
	}

	public void setAmazonUserFacade(AmazonUserFacade amazonUserFacade) {
		this.amazonUserFacade = amazonUserFacade;
	}

}