/**
 * 
 */
package br.hering.core.search.solrfacetsearch.provider.impl;

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
	private Logger LOG = Logger.getLogger(HeringFreeTextValueProvider.class);
	
	private FieldNameProvider fieldNameProvider;
	
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
		
		final List<String> values = new ArrayList<String>();
		
		final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, "pt");

		addNameValue(base, values);
		addColorValues(base, values);
		
		values.add(base.getCode());
		
		String freeText = removeDuplication(values);
		
		Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
		
		for (final String fieldName : fieldNames) 
		{
			fieldValues.add(new FieldValue(fieldName, freeText));
		}
		
		return fieldValues;
	}

	/**
	 * 
	 * @param base
	 * @param values
	 */
	private void addColorValues(HeringProductModel base, List<String> values)
	{
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
	 * @param base
	 * @param values
	 */
	private void addNameValue(HeringProductModel base, List<String> values)
	{
		values.add(base.getName());
	}
	
	@Required
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
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
}