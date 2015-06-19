/**
 * 
 */
package com.fliegersoftware.newslettersubscription.facades.impl;

import de.hybris.platform.commerceservices.i18n.CommerceCommonI18NService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.store.services.BaseStoreService;
//import net.sf.antcontrib.inifile.IniFileTask.Get;

import org.springframework.beans.factory.annotation.Required;

import com.fliegersoftware.newslettersubscription.data.NewsletterSubscriptionData;
import com.fliegersoftware.newslettersubscription.exceptions.DuplicatedNewsletterSubscriptionException;
import com.fliegersoftware.newslettersubscription.exceptions.NewsletterSubscriptionNotFound;
import com.fliegersoftware.newslettersubscription.facades.NewsletterSubscriptionFacade;
import com.fliegersoftware.newslettersubscription.model.NewsletterSubscriptionModel;
import com.fliegersoftware.newslettersubscription.services.NewsletterSubscriptionService;



/**
 * @author luiza
 *
 */
public class DefaultNewsletterSubscriptionFacade implements NewsletterSubscriptionFacade
{

	private NewsletterSubscriptionService newsletterSubscriptionService;
	
	private Converter<NewsletterSubscriptionData, NewsletterSubscriptionModel> newsletterSubscriptionDataToModelConverter;

	private BaseStoreService baseStoreService;
	
	private CommerceCommonI18NService commerceCommonI18NService;
	
		

	@Override
	public NewsletterSubscriptionData subscribe(final NewsletterSubscriptionData subscription) throws DuplicatedNewsletterSubscriptionException
	{	
				
		final NewsletterSubscriptionModel model = new NewsletterSubscriptionModel();
		getNewsletterSubscriptionDataToModelConverter().convert(subscription, model);
		getNewsletterSubscriptionService().subscribe(model);
		return subscription;
		
	}


	@Override
	public NewsletterSubscriptionData updateSubscription(final NewsletterSubscriptionData subscription) throws NewsletterSubscriptionNotFound
	{

		final NewsletterSubscriptionModel model = new NewsletterSubscriptionModel();
		getNewsletterSubscriptionDataToModelConverter().convert(subscription, model);	
		getNewsletterSubscriptionService().updateSubscription(model);
		return subscription;
				
	}


	@Override
	public void unsubscribe(final NewsletterSubscriptionData subscription) throws NewsletterSubscriptionNotFound
	{

		final NewsletterSubscriptionModel model = new NewsletterSubscriptionModel();
		getNewsletterSubscriptionDataToModelConverter().convert(subscription, model);	
		getNewsletterSubscriptionService().unsubscribe(model);
		
	}

	
	/**
	 * @return the current storeCode
	 */
	public String getCurrentBaseStoreCode()
	{
		final String storeCode = getBaseStoreService().getCurrentBaseStore().getUid();
		
		return storeCode;			
	
	}
	

	/**
	 * @return the newsletterSubscriptionService
	 */
	public NewsletterSubscriptionService getNewsletterSubscriptionService()
	{
		return newsletterSubscriptionService;
	}


	/**
	 * @param newsletterSubscriptionService the newsletterSubscriptionService to set
	 */
	@Required
	public void setNewsletterSubscriptionService(NewsletterSubscriptionService newsletterSubscriptionService)
	{
		this.newsletterSubscriptionService = newsletterSubscriptionService;
	}


	/**
	 * @return the newsletterSubscriptionDataToModelConverter
	 */
	public Converter<NewsletterSubscriptionData, NewsletterSubscriptionModel> getNewsletterSubscriptionDataToModelConverter()
	{
		return newsletterSubscriptionDataToModelConverter;
	}


	/**
	 * @param newsletterSubscriptionDataToModelConverter the newsletterSubscriptionDataToModelConverter to set
	 */
	@Required
	public void setNewsletterSubscriptionDataToModelConverter(Converter<NewsletterSubscriptionData, NewsletterSubscriptionModel> newsletterSubscriptionDataToModelConverter)
	{
		this.newsletterSubscriptionDataToModelConverter = newsletterSubscriptionDataToModelConverter;
	}

	/**
	 * @return the baseStoreService
	 */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}


	/**
	 * @param baseStoreService the baseStoreService to set
	 */
	@Required
	public void setBaseStoreService(BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}


	/**
	 * @return the commerceCommonI18NService
	 */
	public CommerceCommonI18NService getCommerceCommonI18NService()
	{
		return commerceCommonI18NService;
	}


	/**
	 * @param commerceCommonI18NService the commerceCommonI18NService to set
	 */
	@Required
	public void setCommerceCommonI18NService(CommerceCommonI18NService commerceCommonI18NService)
	{
		this.commerceCommonI18NService = commerceCommonI18NService;
	}

	
}
