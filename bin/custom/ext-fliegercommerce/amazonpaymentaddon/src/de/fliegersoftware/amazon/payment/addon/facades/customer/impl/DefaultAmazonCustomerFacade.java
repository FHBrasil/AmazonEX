package de.fliegersoftware.amazon.payment.addon.facades.customer.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;
import de.fliegersoftware.amazon.payment.addon.facades.customer.AmazonCustomerFacade;
import de.fliegersoftware.amazon.payment.services.MWSAmazonPaymentService;
import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.core.model.user.CustomerModel;

public class DefaultAmazonCustomerFacade extends DefaultCustomerFacade implements AmazonCustomerFacade {

	@Override
	public void createGuestUserForAnonymousCheckout(String amazonOrderReferenceId, String email, String name) throws DuplicateUidException {
		validateParameterNotNullStandardMessage("email", email);
		final CustomerModel guestCustomer = getModelService().create(CustomerModel.class);
		final String guid = generateGUID();

		//takes care of localizing the name based on the site language
//		guestCustomer.setUid(amazonOrderReferenceId + "|" + email);
		guestCustomer.setUid(guid + "|" + email);
		guestCustomer.setName(name);
		guestCustomer.setType(CustomerType.valueOf(CustomerType.GUEST.getCode()));
		guestCustomer.setSessionLanguage(getCommonI18NService().getCurrentLanguage());
		guestCustomer.setSessionCurrency(getCommonI18NService().getCurrentCurrency());

//		getCustomerAccountService().registerGuestForAnonymousCheckout(guestCustomer, amazonOrderReferenceId);
		getCustomerAccountService().registerGuestForAnonymousCheckout(guestCustomer, guid);
		updateCartWithGuestForAnonymousCheckout(getCustomerConverter().convert(guestCustomer));
	}
}