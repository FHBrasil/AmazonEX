package com.fliegersoftware.newslettersubscription.facades.populators;

import org.springframework.beans.factory.annotation.Required;

import com.fliegersoftware.newslettersubscription.data.SubscriptionTypeData;
import com.fliegersoftware.newslettersubscription.enums.SubscriptionType;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.type.TypeService;

public class SubscriptionTypeModelToDataPopulator implements Populator<SubscriptionType, SubscriptionTypeData>{

	private TypeService typeService;

	protected TypeService getTypeService()
	{
		return typeService;
	}

	@Required
	public void setTypeService(final TypeService typeService)
	{
		this.typeService = typeService;
	}
	
	@Override
	public void populate(SubscriptionType source, SubscriptionTypeData target) throws ConversionException {
		
		target.setCode(source.getCode());
		
		final String subscriptionTypeName =  getTypeService().getEnumerationValue(source).getName();
		target.setName(subscriptionTypeName);
		
		
	}

}
