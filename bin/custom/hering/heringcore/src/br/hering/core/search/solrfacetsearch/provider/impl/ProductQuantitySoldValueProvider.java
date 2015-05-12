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

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import br.hering.core.model.HeringSizeVariantProductModel;
import br.hering.core.strategies.impl.HeringProductQuantitySoldStrategy;

/**
 * @author franthescollymaneira
 *
 */
public class ProductQuantitySoldValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable
{
	private static final Logger LOG = Logger.getLogger(ProductQuantitySoldValueProvider.class);
	
	private FieldNameProvider fieldNameProvider;
	
	@Resource
	private HeringProductQuantitySoldStrategy heringProductQuantitySoldStrategy;
	
	/* (non-Javadoc)
	 * @see de.hybris.platform.solrfacetsearch.provider.FieldValueProvider#getFieldValues(de.hybris.platform.solrfacetsearch.config.IndexConfig, de.hybris.platform.solrfacetsearch.config.IndexedProperty, java.lang.Object)
	 */
	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty, final Object model) throws FieldValueProviderException
	{
		if(!(model instanceof HeringSizeVariantProductModel)) 
		{
			return Collections.emptyList();
		}
		
		final ProductModel product = getBase(model);
		
		final long quantitySold = heringProductQuantitySoldStrategy.getQuantitySold(product);
		
		final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
		
		final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
		
		for (String fieldName : fieldNames) 
		{
			fieldValues.add(new FieldValue(fieldName, Long.valueOf(quantitySold)));
		}
		
		return fieldValues;
	}

	/**
	 * @param model
	 * @return
	 */
	private final ProductModel getBase(final Object model)
	{
		VariantProductModel v = (VariantProductModel) model;
		v = (VariantProductModel) v.getBaseProduct();

		return v.getBaseProduct();
	}
	
	@Required
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}
}