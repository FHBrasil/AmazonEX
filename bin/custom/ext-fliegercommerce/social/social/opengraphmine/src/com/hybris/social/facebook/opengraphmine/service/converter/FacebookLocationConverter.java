/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 * 
 */
package com.hybris.social.facebook.opengraphmine.service.converter;

import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.social.facebook.api.Location;

import com.hybris.social.facebook.opengraphmine.model.FacebookLocationModel;


/**
 * @author rmcotton
 * 
 */
public class FacebookLocationConverter implements Converter<Location, FacebookLocationModel>
{

	private ModelService modelService;

	/**
	 * @see de.hybris.platform.servicelayer.dto.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public FacebookLocationModel convert(final Location source) throws ConversionException
	{
		return getModelService().create(FacebookLocationModel.class);
	}

	/**
	 * @see de.hybris.platform.servicelayer.dto.converter.Converter#convert(java.lang.Object, java.lang.Object)
	 */
	@Override
	public FacebookLocationModel convert(final Location source, final FacebookLocationModel prototype) throws ConversionException
	{
		if (source.getLongitude() != 0.0d)
		{
			prototype.setLongitude(Double.valueOf(source.getLongitude()));
		}
		if (source.getLatitude() != 0.0d)
		{
			prototype.setLatitude(Double.valueOf(source.getLatitude()));
		}
		getModelService().save(prototype);
		prototype.setAddress(convertAddress(source, prototype));
		getModelService().save(prototype);
		return prototype;
	}

	protected AddressModel convertAddress(final Location source, final FacebookLocationModel prototype)
	{
		final AddressModel address;
		if (prototype.getAddress() == null)
		{
			address = getModelService().create(AddressModel.class);
			address.setOwner(prototype);
		}
		else
		{
			address = prototype.getAddress();
		}

		address.setTown(source.getCity());
		address.setPostalcode(source.getZip());
		address.setStreetname(source.getStreet());
		address.setDistrict(source.getState()); // should really map to a region but facebook uses loose text
		address.setRemarks(source.getCountry()); // A hack since we should map to a country but facebook uses loose text
		getModelService().save(address);
		return address;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

}
