/**
 * 
 */
package com.fliegersoftware.newslettersubscription.facades;

import com.fliegersoftware.newslettersubscription.data.NewsletterSubscriptionData;
import com.fliegersoftware.newslettersubscription.exceptions.DuplicatedNewsletterSubscriptionException;
import com.fliegersoftware.newslettersubscription.exceptions.NewsletterSubscriptionNotFound;

/**
 * @author luiza
 *
 */
public interface NewsletterSubscriptionFacade
{

	NewsletterSubscriptionData subscribe(final NewsletterSubscriptionData subscription) throws DuplicatedNewsletterSubscriptionException;
	
	NewsletterSubscriptionData updateSubscription(final NewsletterSubscriptionData subscription) throws NewsletterSubscriptionNotFound;
	
	void unsubscribe(final NewsletterSubscriptionData subscription) throws NewsletterSubscriptionNotFound;
	
	String getCurrentBaseStoreCode();
	
}
