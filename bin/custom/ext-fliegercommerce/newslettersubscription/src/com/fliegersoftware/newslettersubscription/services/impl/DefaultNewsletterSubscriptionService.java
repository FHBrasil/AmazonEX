/**
 * 
 */
package com.fliegersoftware.newslettersubscription.services.impl;

import de.hybris.platform.store.BaseStoreModel;

import org.springframework.beans.factory.annotation.Required;

import com.fliegersoftware.newslettersubscription.dao.NewsletterSubscriptionDao;
import com.fliegersoftware.newslettersubscription.exceptions.DuplicatedNewsletterSubscriptionException;
import com.fliegersoftware.newslettersubscription.exceptions.NewsletterSubscriptionNotFound;
import com.fliegersoftware.newslettersubscription.model.NewsletterSubscriptionModel;
import com.fliegersoftware.newslettersubscription.services.NewsletterSubscriptionService;

/**
 * @author luiza
 *
 */
public class DefaultNewsletterSubscriptionService implements NewsletterSubscriptionService
{

	private NewsletterSubscriptionDao newsletterSubscriptionDao;

	@Override
	public NewsletterSubscriptionModel subscribe(final NewsletterSubscriptionModel subscriber) throws DuplicatedNewsletterSubscriptionException
	{
		
		if (subscriber != null)
		{			
			return getNewsletterSubscriptionDao().createSubscription(subscriber);
		}
		return null;
				
	}


	@Override
	public NewsletterSubscriptionModel updateSubscription(final NewsletterSubscriptionModel subscriber) throws NewsletterSubscriptionNotFound
	{
		
		if (subscriber != null)
		{
			NewsletterSubscriptionModel existingSubscription = findExistingSubscription(subscriber);
			return getNewsletterSubscriptionDao().updateSubscription(existingSubscription);			
		}
		return null;		
		
	}


	@Override
	public void unsubscribe(final NewsletterSubscriptionModel subscriber) throws NewsletterSubscriptionNotFound
	{
		
		if (subscriber != null)
		{
			NewsletterSubscriptionModel existingSubscription = findExistingSubscription(subscriber);
			getNewsletterSubscriptionDao().removeSubscription(existingSubscription); 				
		}
		
	}


	@Override
	public NewsletterSubscriptionModel findExistingSubscription(final NewsletterSubscriptionModel subscriber)
	{
		if (subscriber != null) 
		{
			final String email = subscriber.getEmail();
			final BaseStoreModel store = subscriber.getStore();
			
			return getNewsletterSubscriptionDao().findSubscriptionByEmailAndStore(email, store);
		}
		return null;
		
	}


	/**
	 * @return the newsletterSubscriptionDao
	 */
	public NewsletterSubscriptionDao getNewsletterSubscriptionDao()
	{
		return newsletterSubscriptionDao;
	}


	/**
	 * @param newsletterSubscriptionDao the newsletterSubscriptionDao to set
	 */
	@Required
	public void setNewsletterSubscriptionDao(NewsletterSubscriptionDao newsletterSubscriptionDao)
	{
		this.newsletterSubscriptionDao = newsletterSubscriptionDao;
	}

}
