/**
 * 
 */
package com.fliegersoftware.newslettersubscription.services;

import com.fliegersoftware.newslettersubscription.exceptions.DuplicatedNewsletterSubscriptionException;
import com.fliegersoftware.newslettersubscription.exceptions.NewsletterSubscriptionNotFound;
import com.fliegersoftware.newslettersubscription.model.NewsletterSubscriptionModel;

/**
 * @author luiza
 *
 */
public interface NewsletterSubscriptionService
{
	
	NewsletterSubscriptionModel subscribe(final NewsletterSubscriptionModel subscriber) throws DuplicatedNewsletterSubscriptionException;
	
	NewsletterSubscriptionModel updateSubscription(final NewsletterSubscriptionModel subscriber) throws NewsletterSubscriptionNotFound;
	
	void unsubscribe(final NewsletterSubscriptionModel subscriber) throws NewsletterSubscriptionNotFound;
	
	NewsletterSubscriptionModel findExistingSubscription(final NewsletterSubscriptionModel subscriber);
	
	
}
