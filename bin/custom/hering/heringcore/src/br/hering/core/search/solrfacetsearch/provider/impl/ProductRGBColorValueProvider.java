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
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import br.hering.core.model.HeringProductModel;
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
import de.hybris.platform.util.Config;
import de.hybris.platform.variants.model.VariantProductModel;


public class ProductRGBColorValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable
{
	private FieldNameProvider fieldNameProvider;
	
	@Resource
	protected ClassificationService classificationService;
	
	private CommonI18NService commonI18NService;
	
	@Resource
	protected BaseStoreService baseStoreService;
	
	@Resource
	private VariantsUtils variantsUtils;
	
	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object model) throws FieldValueProviderException
	{
		if(!(model instanceof HeringSizeVariantProductModel)) 
		{
			return Collections.emptyList();
		}
		
		final HeringProductModel base = variantsUtils.getAvailableBaseProduct(model);
		
		if(base == null)
		{
			return Collections.emptyList();
		}
		
		Collection<VariantProductModel> allStyleVariants = base.getVariants();
		
		if(CollectionUtils.isEmpty(allStyleVariants))
		{
			return Collections.emptyList();
		}
		
		Set<String> rgbList = new TreeSet<String>();
		
		for(final VariantProductModel style : allStyleVariants) 
		{
			final VariantProductModel size = variantsUtils.firstAvailableChild(style.getVariants());
			
			if(size == null)
			{
				continue;
			}
			
			final String rgb = getSanitizedColor(size);
			if(StringUtils.isBlank(rgb))
			{
				continue;
			}
			
			rgbList.add(rgb);
			
		}

		final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
		final List<FieldValue> values = new ArrayList<FieldValue>();
		for(String rgb : rgbList)
		{
			for (final String fieldName : fieldNames) 
			{
				values.add(new FieldValue(fieldName, rgb));
			}
		}
		
		return values;
	}

	/**
	 * @param model
	 * @return
	 */
	private String getSanitizedColor(final Object model)
	{
		String rawValue = getFeatureValue((ProductModel) model, "RGB");
		
		if(StringUtils.isBlank(rawValue))
		{
			return null;
		}
		
		String keyPrefix = "color.match.";
		
		String key = keyPrefix + rawValue.replaceAll("#", "");
		
		return Config.getString(key, rawValue);
	}
	
	protected <T> T getFeatureValue(final ProductModel product, final String featureName)
	{
		final FeatureList featureList = classificationService.getFeatures(product);

		final Feature feature = featureList.getFeatureByName(featureName);

		if (feature == null || feature.getValue() == null)
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