/**
 * 
 */
package com.fliegersoftware.newslettersubscription.facades.populators;

import org.springframework.beans.factory.annotation.Required;

import com.fliegersoftware.newslettersubscription.data.NewsletterSubscriptionData;
import com.fliegersoftware.newslettersubscription.data.SubscriptionTypeData;
import com.fliegersoftware.newslettersubscription.enums.SubscriptionType;
import com.fliegersoftware.newslettersubscription.model.NewsletterSubscriptionModel;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

/**
 * @author luiza
 *
 */
public class NewsletterSubscriptionModelToDataPopulator implements Populator<NewsletterSubscriptionModel, NewsletterSubscriptionData>
{

	private Converter<SubscriptionType, SubscriptionTypeData> subscriptionTypeModelToDataConverter;
	

	@Override
	public void populate(NewsletterSubscriptionModel source, NewsletterSubscriptionData target) throws ConversionException
	{
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setEmail(source.getEmail());
		target.setBirthDay(source.getBirthDay());
		
		try {
			final String genderCode = source.getGender().getCode();
			if (genderCode != null)
			{
				target.setGenderCode(genderCode);
			}
		} catch (Exception e1) {
			//e1.printStackTrace();
		}		
		
		try {
			final String titleCode = source.getTitle().getCode(); 		
			if (titleCode != null)
			{
				target.setTitleCode(titleCode);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
			
		final String languageIsoCode = source.getLanguage().getIsocode();
		if (languageIsoCode != null)
		{
			target.setLanguageIsoCode(languageIsoCode);
		}
				
		final String baseStoreId = source.getStore().getUid();
		if (baseStoreId != null)
		{
			target.setStoreCode(baseStoreId);
		}
				
		SubscriptionType subscriptionType = source.getSubscriptionType();
		if (subscriptionType != null)
		{
			SubscriptionTypeData subscriptionTypeData = new SubscriptionTypeData();
			getSubscriptionTypeModelToDataConverter().convert(subscriptionType, subscriptionTypeData);
			target.setSubscriptionType(subscriptionTypeData);
		}
		
	}
	
	
	public Converter<SubscriptionType, SubscriptionTypeData> getSubscriptionTypeModelToDataConverter() {
		return subscriptionTypeModelToDataConverter;
	}

	@Required
	public void setSubscriptionTypeModelToDataConverter(
			Converter<SubscriptionType, SubscriptionTypeData> subscriptionTypeModelToDataConverter) {
		this.subscriptionTypeModelToDataConverter = subscriptionTypeModelToDataConverter;
	}
	
	
}
