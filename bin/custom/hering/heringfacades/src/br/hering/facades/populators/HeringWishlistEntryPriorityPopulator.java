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
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.wishlist2.enums.Wishlist2EntryPriority;

import org.springframework.beans.factory.annotation.Required;

import br.hering.facades.product.data.GenderData;
import br.hering.facades.wishlist.data.HeringWishlistEntryPriorityData;


/**
 * Populates {@link GenderData} with name and code.
 */
public class HeringWishlistEntryPriorityPopulator implements Populator<Wishlist2EntryPriority, HeringWishlistEntryPriorityData>
{
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final Wishlist2EntryPriority source, final HeringWishlistEntryPriorityData target) throws ConversionException
	{
		target.setCode(source.getCode());
		target.setName(getTypeService().getEnumerationValue(source).getName());

	}


}