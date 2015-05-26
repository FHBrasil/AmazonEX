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
package br.hering.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;

import br.hering.core.model.HeringProductModel;
import br.hering.core.model.HeringSizeVariantProductModel;
import br.hering.core.model.HeringStyleVariantProductModel;
import br.hering.core.strategies.impl.HeringKidsClothingFlagStrategy;


public class GenderValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable
{
	protected static final String KIDS_GENDER_PREFIX = "kids.";

	protected static final String BABY_GENDER_PREFIX = "baby.";

	private FieldNameProvider fieldNameProvider;
	
	private CommonI18NService commonI18NService;
	
	@Resource
	private HeringKidsClothingFlagStrategy heringKidsClothingFlagStrategy;

	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object model) throws FieldValueProviderException
	{
		final HeringProductModel heringModel = getHeringProductModel(model);
		if (heringModel == null)
		{
			return Collections.emptyList();
		}

		final List<Gender> genders = heringModel.getGenders();

		if (genders != null && !genders.isEmpty())
		{
			final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
			for (final Gender gender : genders)
			{
				String value = getValue((ProductModel) model, gender);
				fieldValues.addAll(createFieldValue(value, indexedProperty));
			}
			return fieldValues;
		}
		else
		{
			return Collections.emptyList();
		}
	}

	/**
	 * 
	 * @param product
	 * @param gender
	 * @return
	 */
	private String getValue(final ProductModel product, final Gender gender)
	{
		final String genderCode = gender.getCode();

		if(heringKidsClothingFlagStrategy.isBabyClothing(product)) {
			return BABY_GENDER_PREFIX + genderCode;
		}

		if(heringKidsClothingFlagStrategy.isKidsClothing(product))
		{
			return KIDS_GENDER_PREFIX + genderCode;
		}
		
		return genderCode;
	}

	protected List<FieldValue> createFieldValue(final String value, final IndexedProperty indexedProperty)
	{
		final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
		final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
		for (final String fieldName : fieldNames)
		{
			fieldValues.add(new FieldValue(fieldName, value));
		}
		return fieldValues;
	}

	protected HeringProductModel getHeringProductModel(Object model)
	{

		if (model instanceof HeringSizeVariantProductModel)
		{
			final HeringSizeVariantProductModel sizeModel = (HeringSizeVariantProductModel) model;
			model = sizeModel.getBaseProduct();
		}

		if (model instanceof HeringStyleVariantProductModel)
		{
			final HeringStyleVariantProductModel styleModel = (HeringStyleVariantProductModel) model;
			model = styleModel.getBaseProduct();
		}

		if (model instanceof HeringProductModel)
		{
			return (HeringProductModel) model;
		}
		else
		{
			return null;
		}
	}

	@Required
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}

	public CommonI18NService getCommonI18NService() {
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(CommonI18NService commonI18NService) {
		this.commonI18NService = commonI18NService;
	}
}