/**
 * 
 */
package br.hering.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import de.hybris.platform.variants.model.VariantProductModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import br.hering.core.model.HeringProductModel;
import br.hering.core.model.HeringSizeVariantProductModel;
import br.hering.core.model.HeringStyleVariantProductModel;

/**
 * @author franthescollymaneira
 *
 */
public class HeringFreeTextValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable
{
	private static final long serialVersionUID = 1L;

	private Logger LOG = Logger.getLogger(HeringFreeTextValueProvider.class);
	
	private FieldNameProvider fieldNameProvider;
	
	private CommonI18NService commonI18NService;
	
	@Resource
	private VariantsUtils variantsUtils;
	
	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model) throws FieldValueProviderException
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
		
		if(CollectionUtils.isEmpty(base.getVariants()))
		{
			return Collections.emptyList();
		}
		
		Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
		
		if (indexedProperty.isLocalized()) 
		{
			Collection<LanguageModel> languages = indexConfig.getLanguages();
			for (LanguageModel language : languages) 
			{
				fieldValues.addAll(createFieldValue(base, language, indexedProperty));
			}
		} 
		else 
		{
			fieldValues.addAll(createFieldValue(base, null, indexedProperty));
		}
		
		return fieldValues;
	}

	/**
	 * 
	 * @param base
	 * @param values
	 */
	private void addColorValues(ProductModel product, List<String> values)
	{
		final HeringProductModel base = (HeringProductModel) product;
	
		List<HeringStyleVariantProductModel> availableStyleVariants = variantsUtils.getAvailableStyleVariants(base);
		if(CollectionUtils.isEmpty(availableStyleVariants))
		{
			return;
		}
		
		for(final HeringStyleVariantProductModel style : availableStyleVariants) 
		{
			VariantProductModel size = variantsUtils.firstAvailableChild(style.getVariants());
			values.add(((HeringSizeVariantProductModel) size).getColorFullDescription());
		}
	}
	
	/**
	 * 
	 * @param product
	 * @param values
	 */
	private void addNameValue(ProductModel product, List<String> values)
	{
		values.add(product.getName());
	}
	
	public String removeDuplication(List<String> s) 
	{
		if(CollectionUtils.isEmpty(s))
		{
			return "";
		}
		
		final List<String> list = Arrays.asList(StringUtils.join(s, " ").split(" "));
		
		final LinkedHashSet<String> set = new LinkedHashSet<String>(list);
		
		return set.toString().replaceAll("(^\\[|\\]$)", "").replace(", ", " ");
	}
	
	//
	protected List<FieldValue> createFieldValue(ProductModel product, LanguageModel language, IndexedProperty indexedProperty) 
	{
		if(language != null)
		{
			this.i18nService.setCurrentLocale(this.getCommonI18NService().getLocaleForLanguage(language));
		}
		
		List<FieldValue> fieldValues = new ArrayList<FieldValue>();

		String propertyValue = getPropertyValue(product);
		
		if (StringUtils.isNotBlank(propertyValue)) 
		{
			addFieldValues(fieldValues, indexedProperty, language, propertyValue);
		}

		return fieldValues;
	}

	private String getPropertyValue(ProductModel product) 
	{
		List<String> values = new ArrayList<String>();
		
		addNameValue(product, values);
		addColorValues(product, values);
		
		values.add(product.getCode());
		
		String freeText = removeDuplication(values);
		
		return freeText;
	}

	protected void addFieldValues(List<FieldValue> fieldValues, IndexedProperty indexedProperty, LanguageModel language, Object value) 
	{
		String languageIso = (language == null) ? null : language.getIsocode();
		
		Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty, languageIso);
		
		for (String fieldName : fieldNames) 
		{
			fieldValues.add(new FieldValue(fieldName, value));
		}
	}
	//
	
	@Required
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}
	
	public FieldNameProvider getFieldNameProvider()
	{
		return this.fieldNameProvider;
	}

	public CommonI18NService getCommonI18NService() 
	{
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(CommonI18NService commonI18NService) 
	{
		this.commonI18NService = commonI18NService;
	}
}