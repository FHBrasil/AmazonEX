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
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import br.hering.core.model.HeringSizeVariantProductModel;


public class ProductSalesFlagValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable
{
	private FieldNameProvider fieldNameProvider;

	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object model) throws FieldValueProviderException
	{
		if(!(model instanceof HeringSizeVariantProductModel)) 
		{
			return Collections.emptyList();
		}
		
		final HeringSizeVariantProductModel product = (HeringSizeVariantProductModel) model;

		BigDecimal oldPrice = product.getOldPrice();
		
		if(oldPrice == null || oldPrice.doubleValue() <= 0) 
		{
			return Collections.emptyList();
		}
		
		if(CollectionUtils.isEmpty(product.getEurope1Prices())) 
		{
			return Collections.emptyList();
		}
		
		Double currentPrice = product.getEurope1Prices().iterator().next().getPrice();
		
		if(currentPrice.doubleValue() >= oldPrice.doubleValue()) 
		{
			return Collections.emptyList();
		}

		final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
		
		Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
		for (String fieldName : fieldNames) 
		{
			fieldValues.add(new FieldValue(fieldName, Boolean.TRUE));
		}
		
		return fieldValues;
	}

	protected List<FieldValue> createFieldValue(final Gender gender, final IndexedProperty indexedProperty)
	{
		final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
		final Object value = gender.getCode();
		final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
		for (final String fieldName : fieldNames)
		{
			fieldValues.add(new FieldValue(fieldName, value));
		}
		return fieldValues;
	}

	@Required
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}
}