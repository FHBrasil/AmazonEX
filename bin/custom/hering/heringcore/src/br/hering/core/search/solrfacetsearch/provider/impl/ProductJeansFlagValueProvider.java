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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import br.hering.core.model.HeringSizeVariantProductModel;
import de.hybris.platform.classification.ClassificationService;
import de.hybris.platform.classification.features.Feature;
import de.hybris.platform.classification.features.FeatureList;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import de.hybris.platform.store.services.BaseStoreService;


public class ProductJeansFlagValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable
{
	private FieldNameProvider fieldNameProvider;
	
	@Resource
	private ClassificationService classificationService;
	
	private CommonI18NService commonI18NService;
	
	@Resource
	protected BaseStoreService baseStoreService;

	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object model) throws FieldValueProviderException
	{
		if(!(model instanceof HeringSizeVariantProductModel)) 
		{
			return Collections.emptyList();
		}
		
		final HeringSizeVariantProductModel product = (HeringSizeVariantProductModel) model;
		
		String tag = getFeatureValue(product, "Tag");
		
		if(StringUtils.isBlank(tag))
		{
			return Collections.emptyList();
		}
		
		if(!StringUtils.containsIgnoreCase(tag, "jeans"))
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

	protected <T> T getFeatureValue(final ProductModel product, final String featureName)
	{
		final FeatureList featureList = classificationService.getFeatures(product);

		final Feature feature = featureList.getFeatureByName(featureName);

//		System.out.println(product.getCode());
//		if(CollectionUtils.isNotEmpty(featureList.getFeatures())) 
//		{
//			for(Feature f: featureList.getFeatures())
//			{
//				System.out.println(f.getName());
//			}
//		}
		
		if (feature == null)
		{
			return null;
		}
		
		Object value = feature.getValue().getValue();
		
		if(value == null)
		{
			return null;
		}
		
		return (T) value;
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
