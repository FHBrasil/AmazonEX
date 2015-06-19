/**
 * 
 */
package com.fliegersoftware.newslettersubscription.facades.populators;

import com.fliegersoftware.newslettersubscription.data.NewsletterSubscriptionData;
import com.fliegersoftware.newslettersubscription.model.NewsletterSubscriptionModel;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 * @author luiza
 *
 */
public class NewsletterSubscriptionModelToDataPopulator implements Populator<NewsletterSubscriptionModel, NewsletterSubscriptionData>
{

	@Override
	public void populate(NewsletterSubscriptionModel source, NewsletterSubscriptionData target) throws ConversionException
	{
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setEmail(source.getEmail());
		
		final String genderCode = source.getGender().getCode();
		target.setGenderCode(genderCode);
		
		final String titleCode = source.getTitle().getCode();
		target.setTitleCode(titleCode);
		
		final String languageIsoCode = source.getLanguage().getIsocode();
		target.setLanguageIsoCode(languageIsoCode);
				
		final String baseStoreId = source.getStore().getUid();
		target.setStoreCode(baseStoreId);
		
	}
}
