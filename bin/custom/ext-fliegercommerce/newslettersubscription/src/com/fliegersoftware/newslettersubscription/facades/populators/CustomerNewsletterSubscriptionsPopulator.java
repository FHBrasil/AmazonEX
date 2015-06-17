/**
 * 
 */
package com.fliegersoftware.newslettersubscription.facades.populators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.fliegersoftware.newslettersubscription.data.NewsletterSubscriptionData;
import com.fliegersoftware.newslettersubscription.model.NewsletterSubscriptionModel;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

/**
 * @author luiza
 *
 */
public class CustomerNewsletterSubscriptionsPopulator implements Populator<CustomerModel, CustomerData>
{

	private Converter<NewsletterSubscriptionModel, NewsletterSubscriptionData> newsletterSubscriptionModelToDataConverter;	
	
	@Override
	public void populate(CustomerModel source, CustomerData target) throws ConversionException
	{

		final List <NewsletterSubscriptionData> newsletterSubscriptionDataList = new ArrayList<NewsletterSubscriptionData>();
			
		Collection<NewsletterSubscriptionModel> newsletterSubscriptionModelCollection = source.getNewsletterSubscriptions(); 
		
	    for (NewsletterSubscriptionModel newsletterSubscriptionModel : newsletterSubscriptionModelCollection) { 
	   	
	   	final NewsletterSubscriptionData newsletterSubscriptionData = new NewsletterSubscriptionData();
	   	getNewsletterSubscriptionModelToDataConverter().convert(newsletterSubscriptionModel, newsletterSubscriptionData);   	
	   	newsletterSubscriptionDataList.add(newsletterSubscriptionData);
	   	
	   }  		
			   
		target.setNewsletterSubscriptions(newsletterSubscriptionDataList);

	}


	/**
	 * @return the newsletterSubscriptionModelToDataConverter
	 */
	public Converter<NewsletterSubscriptionModel, NewsletterSubscriptionData> getNewsletterSubscriptionModelToDataConverter()
	{
		return newsletterSubscriptionModelToDataConverter;
	}



	/**
	 * @param newsletterSubscriptionModelToDataConverter the newsletterSubscriptionModelToDataConverter to set
	 */
	@Required
	public void setNewsletterSubscriptionModelToDataConverter(Converter<NewsletterSubscriptionModel, NewsletterSubscriptionData> newsletterSubscriptionModelToDataConverter)
	{
		this.newsletterSubscriptionModelToDataConverter = newsletterSubscriptionModelToDataConverter;
	}
}
