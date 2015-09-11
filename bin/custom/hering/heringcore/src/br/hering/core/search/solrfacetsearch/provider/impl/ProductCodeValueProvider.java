/**
 * 
 */
package br.hering.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.core.model.product.ProductModel;
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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import br.hering.core.model.HeringProductModel;
import br.hering.core.model.HeringSizeVariantProductModel;

/**
 * @author franthescollymaneira
 *
 */
public class ProductCodeValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable
{
	private Logger LOG = Logger.getLogger(ProductCodeValueProvider.class);
	
	private FieldNameProvider fieldNameProvider;
	
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
		
		final List<FieldValue> values = new ArrayList<FieldValue>();
		
		final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);

		addCode(values, fieldNames, base);
		
		for(final VariantProductModel style : allStyleVariants) 
		{
			addCode(values, fieldNames, style);

			if(CollectionUtils.isNotEmpty(style.getVariants()))
			{
				for(final VariantProductModel size : style.getVariants()) 
				{
					addCode(values, fieldNames, size);
				}
			}
		}
		
		return values;
	}
	
	private void addCode(final List<FieldValue> values, final Collection<String> fieldNames, final ProductModel product)
	{
		for (final String fieldName : fieldNames) 
		{
			values.add(new FieldValue(fieldName, product.getCode().toLowerCase()));
		}
	}
	
	@Required
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}
}