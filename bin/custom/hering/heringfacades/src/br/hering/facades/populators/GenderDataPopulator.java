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
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.servicelayer.type.TypeService;

import org.springframework.beans.factory.annotation.Required;

import br.hering.facades.product.data.GenderData;

/**
 * Populates {@link GenderData} with name and code.
 */
public class GenderDataPopulator implements Populator<Gender, GenderData>
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

	@Override
	public void populate(final Gender source, final GenderData target)
	{
		target.setCode(source.getCode());
		target.setName(getTypeService().getEnumerationValue(source).getName());
		
		if(target.getName() == null)
		{	
			switch (target.getCode()) {
				case "MALE":
					target.setName("Masculino");
				break;
				case "FEMALE":
					target.setName("Feminino");
				break;
				case "UNISEX":
					target.setName("Unisex");
				break;
				case "UNDEFINED":
					target.setName("Indefinido");
				break;
				default:
					target.setName("null");
	
				}
		}
	}
}
