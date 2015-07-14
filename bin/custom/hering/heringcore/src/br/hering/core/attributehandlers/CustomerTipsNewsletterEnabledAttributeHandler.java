package br.hering.core.attributehandlers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.fliegersoftware.newslettersubscription.data.NewsletterSubscriptionData;
import com.fliegersoftware.newslettersubscription.data.SubscriptionTypeData;
import com.fliegersoftware.newslettersubscription.enums.SubscriptionType;
import com.fliegersoftware.newslettersubscription.exceptions.DuplicatedNewsletterSubscriptionException;
import com.fliegersoftware.newslettersubscription.exceptions.NewsletterSubscriptionNotFound;
import com.fliegersoftware.newslettersubscription.facades.NewsletterSubscriptionFacade;
import com.fliegersoftware.newslettersubscription.services.NewsletterSubscriptionService;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

public class CustomerTipsNewsletterEnabledAttributeHandler implements DynamicAttributeHandler<Boolean, CustomerModel>{

	
	private NewsletterSubscriptionFacade newsletterSubscriptionFacade;
	
	private NewsletterSubscriptionService newsletterSubscriptionService;

	private Converter<CustomerModel, CustomerData> customerConverter;
	
	private Converter<SubscriptionType, SubscriptionTypeData> subscriptionTypeModelToDataConverter;
	
	private static final Logger LOG = Logger.getLogger(CustomerTipsNewsletterEnabledAttributeHandler.class);

	
	
	public Converter<SubscriptionType, SubscriptionTypeData> getSubscriptionTypeModelToDataConverter() {
		return subscriptionTypeModelToDataConverter;
	}

	@Required
	public void setSubscriptionTypeModelToDataConverter(
			Converter<SubscriptionType, SubscriptionTypeData> subscriptionTypeModelToDataConverter) {
		this.subscriptionTypeModelToDataConverter = subscriptionTypeModelToDataConverter;
	}

	public Converter<CustomerModel, CustomerData> getCustomerConverter() {
		return customerConverter;
	}

	@Required
	public void setCustomerConverter(Converter<CustomerModel, CustomerData> customerConverter) {
		this.customerConverter = customerConverter;
	}

	public NewsletterSubscriptionFacade getNewsletterSubscriptionFacade() {
		return newsletterSubscriptionFacade;
	}

	@Required
	public void setNewsletterSubscriptionFacade(NewsletterSubscriptionFacade newsletterSubscriptionFacade) {
		this.newsletterSubscriptionFacade = newsletterSubscriptionFacade;
	}
	
	public NewsletterSubscriptionService getNewsletterSubscriptionService() {
		return newsletterSubscriptionService;
	}

	@Required
	public void setNewsletterSubscriptionService(NewsletterSubscriptionService newsletterSubscriptionService) {
		this.newsletterSubscriptionService = newsletterSubscriptionService;
	}

	
	@Override
	public Boolean get(CustomerModel customerModel) {

		//procura se ja existe uma subscription
		//se customermodel.getTips()... 
		
		return null;
	}

	@Override
	public void set(CustomerModel customerModel, Boolean tipsNewsletterEnabled) {
				
		final SubscriptionType subscriptionType = SubscriptionType.TIPS_NEWSLETTER;	
		SubscriptionTypeData subscriptionTypeData = new SubscriptionTypeData();
		getSubscriptionTypeModelToDataConverter().convert(subscriptionType, subscriptionTypeData);
		
		CustomerData customerData = new CustomerData();
		getCustomerConverter().convert(customerModel, customerData);
		
		NewsletterSubscriptionData subscription = new NewsletterSubscriptionData();
		subscription.setFirstName(customerData.getFirstName());
		subscription.setLastName(customerData.getLastName());
		subscription.setEmail(customerData.getUid());
		subscription.setGenderCode(customerData.getGender().getCode());
		subscription.setTitleCode("Mr");
		subscription.setCustomer(customerData);
		subscription.setLanguageIsoCode(customerData.getLanguage().getIsocode());
		subscription.setStoreCode(getNewsletterSubscriptionFacade().getCurrentBaseStoreCode());
		subscription.setSubscriptionType(subscriptionTypeData);
		
						
		//create subscription of type TIPS_NEWSLETTER
		if (tipsNewsletterEnabled)
		{	
			try 
			{
				getNewsletterSubscriptionFacade().subscribe(subscription);
			} 
			catch (DuplicatedNewsletterSubscriptionException e) 
			{
				//e.printStackTrace();
			}
		}
		//delete subscription of type TIPS_NEWSLETTER
		else
		{
			try 
			{
				getNewsletterSubscriptionFacade().unsubscribe(subscription);
			} 
			catch (NewsletterSubscriptionNotFound e) 
			{
				//e.printStackTrace();
			}
		}
		
		
	}


}
