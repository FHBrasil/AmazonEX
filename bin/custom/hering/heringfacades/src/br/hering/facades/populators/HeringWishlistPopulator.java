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
package br.hering.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;

import java.util.LinkedList;

import javax.annotation.Resource;

import br.hering.core.model.HeringWishlistModel;
import br.hering.facades.product.data.GenderData;
import br.hering.facades.wishlist.data.HeringWishlistData;
import br.hering.facades.wishlist.data.HeringWishlistEntryData;

import de.hybris.platform.commercefacades.user.data.CustomerData;


/**
 * Populates {@link GenderData} with name and code.
 */
public class HeringWishlistPopulator implements Populator<HeringWishlistModel, HeringWishlistData>
{

	private Converter<Wishlist2EntryModel, HeringWishlistEntryData> heringWishlistEntryConverter;
	
	@Resource
	private Converter<CustomerModel, CustomerData> customerConverter;

	/*
	 * protected TypeService getTypeService() { return typeService; }
	 * 
	 * @Required public void setTypeService(final TypeService typeService) { this.typeService = typeService; }
	 */

	//	@Override
	//	public void populate(final Gender source, final GenderData target)
	//	{
	//		target.setCode(source.getCode());
	//		target.setName(getTypeService().getEnumerationValue(source).getName());
	//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final HeringWishlistModel source, final HeringWishlistData target) throws ConversionException
	{
		target.setDescription(source.getDescription());

		final LinkedList<HeringWishlistEntryData> entries = new LinkedList<HeringWishlistEntryData>();

		for (final Wishlist2EntryModel entry : source.getEntries())
		{
			entries.add(heringWishlistEntryConverter.convert(entry));
		}
		target.setEntries(entries);
		target.setName(source.getName());
		target.setPrincipal(source.getDefault());
		target.setPublicName(source.getPublicName());
		target.setUser(customerConverter.convert((CustomerModel) source.getUser()));
	}

	/**
	 * @return the heringWishlistEntryConverter
	 */
	public Converter<Wishlist2EntryModel, HeringWishlistEntryData> getHeringWishlistEntryConverter()
	{
		return heringWishlistEntryConverter;
	}

	/**
	 * @param heringWishlistEntryConverter
	 *           the heringWishlistEntryConverter to set
	 */
	public void setheringWishlistEntryConverter(final Converter<Wishlist2EntryModel, HeringWishlistEntryData> heringWishlistEntryConverter)
	{
		this.heringWishlistEntryConverter = heringWishlistEntryConverter;
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */

}
