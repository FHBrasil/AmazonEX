package br.hering.core.attributehandlers;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.fliegersoftware.newslettersubscription.data.NewsletterSubscriptionData;
import com.fliegersoftware.newslettersubscription.data.SubscriptionTypeData;
import com.fliegersoftware.newslettersubscription.enums.SubscriptionType;
import com.fliegersoftware.newslettersubscription.exceptions.DuplicatedNewsletterSubscriptionException;
import com.fliegersoftware.newslettersubscription.exceptions.NewsletterSubscriptionNotFound;
import com.fliegersoftware.newslettersubscription.model.NewsletterSubscriptionModel;
import com.fliegersoftware.newslettersubscription.services.NewsletterSubscriptionService;

import de.hybris.platform.commerceservices.strategies.CustomerNameStrategy;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.services.BaseStoreService;


public class CustomerReviewShoppingExperienceEnabledAttributeHandler implements DynamicAttributeHandler<Boolean, CustomerModel> {

	private NewsletterSubscriptionService newsletterSubscriptionService;
	
	private BaseStoreService baseStoreService;
	
	private UserService userService;

	private CustomerNameStrategy customerNameStrategy;


	@Override
	public Boolean get(CustomerModel customerModel) {
		
		Collection<NewsletterSubscriptionModel> customerSubscriptions = customerModel.getNewsletterSubscriptions();
		
		if (CollectionUtils.isNotEmpty(customerSubscriptions))				
		{
			for (NewsletterSubscriptionModel subscription : customerSubscriptions)
			{
				SubscriptionType type = subscription.getSubscriptionType();
				if (type == SubscriptionType.REVIEW_SHOPPING_EXPERIENCE)
				{
					return true;
				}
			}
		}

		return false;
		
	}

	@Override
	public void set(CustomerModel customerModel, Boolean reviewShoppingExperienceEnabled) {
		
		NewsletterSubscriptionModel subscription = new NewsletterSubscriptionModel();
		
		final String[] names = customerNameStrategy.splitName(customerModel.getName());
		
		subscription.setFirstName(names[0]);
		subscription.setLastName(names[1]);
		subscription.setEmail(customerModel.getUid());
		subscription.setGender(customerModel.getGender());
		if (customerModel.getTitle() != null)
		{
			subscription.setTitle(customerModel.getTitle());
		}	
		subscription.setLanguage(customerModel.getSessionLanguage());
		subscription.setStore(getBaseStoreService().getCurrentBaseStore());		
		final SubscriptionType subscriptionType = SubscriptionType.REVIEW_SHOPPING_EXPERIENCE;
		subscription.setSubscriptionType(subscriptionType);
		subscription.setCustomer(customerModel);
										
		if (reviewShoppingExperienceEnabled)
		{	
			try 
			{
				getNewsletterSubscriptionService().subscribe(subscription);
			} 
			catch (DuplicatedNewsletterSubscriptionException e) 
			{
				//e.printStackTrace();
			}
		}
		else
		{
			try 
			{
				getNewsletterSubscriptionService().unsubscribe(subscription);
			} 
			catch (NewsletterSubscriptionNotFound e) 
			{
				//e.printStackTrace();
			}
		}
		
	}

	
	public BaseStoreService getBaseStoreService() {
		return baseStoreService;
	}

	@Required
	public void setBaseStoreService(BaseStoreService baseStoreService) {
		this.baseStoreService = baseStoreService;
	}
	
	public CustomerNameStrategy getCustomerNameStrategy() {
		return customerNameStrategy;
	}

	@Required
	public void setCustomerNameStrategy(CustomerNameStrategy customerNameStrategy) {
		this.customerNameStrategy = customerNameStrategy;
	}
	
	public UserService getUserService() {
		return userService;
	}

	@Required
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public NewsletterSubscriptionService getNewsletterSubscriptionService() {
		return newsletterSubscriptionService;
	}

	@Required
	public void setNewsletterSubscriptionService(NewsletterSubscriptionService newsletterSubscriptionService) {
		this.newsletterSubscriptionService = newsletterSubscriptionService;
	}
	
}
