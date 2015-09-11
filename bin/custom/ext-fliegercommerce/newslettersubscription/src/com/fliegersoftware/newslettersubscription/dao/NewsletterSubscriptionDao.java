/**
 *
 */
package com.fliegersoftware.newslettersubscription.dao;

import de.hybris.platform.servicelayer.internal.dao.Dao;
import de.hybris.platform.store.BaseStoreModel;

import java.util.List;

import com.fliegersoftware.newslettersubscription.enums.SubscriptionType;
import com.fliegersoftware.newslettersubscription.exceptions.DuplicatedNewsletterSubscriptionException;
import com.fliegersoftware.newslettersubscription.exceptions.NewsletterSubscriptionNotFound;
import com.fliegersoftware.newslettersubscription.model.NewsletterSubscriptionModel;


/**
 * @author luiza
 *
 */
public interface NewsletterSubscriptionDao extends Dao
{

	NewsletterSubscriptionModel createSubscription(final NewsletterSubscriptionModel subscriber) throws DuplicatedNewsletterSubscriptionException;
	
	NewsletterSubscriptionModel updateSubscription(final NewsletterSubscriptionModel subscriber) throws NewsletterSubscriptionNotFound;
	
	void removeSubscription(final NewsletterSubscriptionModel subscriber) throws NewsletterSubscriptionNotFound;
	
	List<NewsletterSubscriptionModel> findSubscriptionsByStore(final BaseStoreModel store);

	NewsletterSubscriptionModel findSubscriptionByEmailAndStoreAndType(final String email, final BaseStoreModel store, final SubscriptionType subscriptionType);
	

}
