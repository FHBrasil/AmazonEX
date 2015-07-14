package com.fliegersoftware.newslettersubscription.facades.populators;

import org.springframework.beans.factory.annotation.Required;

import com.fliegersoftware.newslettersubscription.data.SubscriptionTypeData;
import com.fliegersoftware.newslettersubscription.enums.SubscriptionType;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


public class SubscriptionTypeDataToModelPopulator implements Populator<SubscriptionTypeData, SubscriptionType> {

	private EnumerationService enumerationService;
	
	@Override
	public void populate(SubscriptionTypeData source, SubscriptionType target) throws ConversionException {
		
		//como o subscriptiontype é um atributo de newsletter, a unica forma de dar set nele é usando o model do newsletter
		
		final String subscriptionTypeCode = source.getCode();	
		SubscriptionType subscriptionType = getEnumerationService().getEnumerationValue(SubscriptionType.class, subscriptionTypeCode);
		if (subscriptionType != null)
		{
			//target.setSubscriptionType(subscriptionType);
		}
		
		
	}
	
	/**
	 * @return the enumerationService
	 */
	public EnumerationService getEnumerationService()
	{
		return enumerationService;
	}

	
	/**
	 * @param enumerationService the enumerationService to set
	 */
	@Required
	public void setEnumerationService(EnumerationService enumerationService)
	{
		this.enumerationService = enumerationService;
	}

}
