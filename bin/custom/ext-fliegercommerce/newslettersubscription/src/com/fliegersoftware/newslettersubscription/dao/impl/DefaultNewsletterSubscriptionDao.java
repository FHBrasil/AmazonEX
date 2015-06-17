/**
 * 
 */
package com.fliegersoftware.newslettersubscription.dao.impl;

import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.store.BaseStoreModel;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.fliegersoftware.newslettersubscription.dao.NewsletterSubscriptionDao;
import com.fliegersoftware.newslettersubscription.exceptions.DuplicatedNewsletterSubscriptionException;
import com.fliegersoftware.newslettersubscription.exceptions.NewsletterSubscriptionNotFound;
import com.fliegersoftware.newslettersubscription.model.NewsletterSubscriptionModel;

/**
 * @author luiza
 *
 */
public class DefaultNewsletterSubscriptionDao implements NewsletterSubscriptionDao
{
	
	private ModelService modelService;
	
	private FlexibleSearchService flexibleSearchService;
	
		
	@Override
	public NewsletterSubscriptionModel createSubscription(final NewsletterSubscriptionModel subscriber) throws DuplicatedNewsletterSubscriptionException
	{
	
		if (getModelService().isNew(subscriber))
		{
			getModelService().save(subscriber);
			getModelService().refresh(subscriber);
			return subscriber;
		}
		return null;
		
	}

	@Override
	public NewsletterSubscriptionModel updateSubscription(final NewsletterSubscriptionModel subscriber) throws NewsletterSubscriptionNotFound
	{
		if (! getModelService().isNew(subscriber))
		{
			getModelService().save(subscriber);
			getModelService().refresh(subscriber);
			return subscriber;
		}
		return null;		
		
	}
	
	@Override
	public void removeSubscription(final NewsletterSubscriptionModel subscriber) throws NewsletterSubscriptionNotFound
	{

		if (! getModelService().isNew(subscriber))
		{
			getModelService().remove(subscriber);

		}
		
	}

	@Override
	public List<NewsletterSubscriptionModel> findSubscriptionsByStore(final BaseStoreModel store)
	{

		final String queryString = //
		        "SELECT {p:PK}" //
		                + "FROM {NewsletterSubscription AS p} "//
		                + "WHERE " + "{p:store}=?store ";
		
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("store", store);

        return getFlexibleSearchService().<NewsletterSubscriptionModel> search(query).getResult();
		 	
	}


	@Override
	public NewsletterSubscriptionModel findSubscriptionByEmailAndStore(final String email, final BaseStoreModel store)
	{
		
		final String queryString = //
		        "SELECT {p:PK}" //
		                + "FROM {NewsletterSubscription AS p} "//
		                + "WHERE " + "{p:store}=?store " //
		                + "AND " + "{p:email}=?email";
		 
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("store", store);
        query.addQueryParameter("email", email);

      return getFlexibleSearchService().<NewsletterSubscriptionModel> search(query).getResult().get(0);
    
      
	}

	
	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * @param flexibleSearchService the flexibleSearchService to set
	 */
	@Required
	public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}
	
}
