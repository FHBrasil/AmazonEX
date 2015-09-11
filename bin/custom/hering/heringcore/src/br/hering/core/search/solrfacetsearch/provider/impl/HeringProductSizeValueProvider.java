/**
 * 
 */
package br.hering.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.OptionalModelPropertyFieldValueProvider;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;

import br.hering.core.util.HeringComparatorFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import br.hering.core.model.HeringProductModel;
import br.hering.core.model.HeringSizeVariantProductModel;

/**
 * @author franthescollymaneira
 *
 */
public class HeringProductSizeValueProvider extends OptionalModelPropertyFieldValueProvider
{
	@Resource
	private VariantsUtils variantsUtils;
	
	/* (non-Javadoc)
	 * @see de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.OptionalModelPropertyFieldValueProvider#getFieldValues(de.hybris.platform.solrfacetsearch.config.IndexConfig, de.hybris.platform.solrfacetsearch.config.IndexedProperty, java.lang.Object)
	 */
	@Override
	public Collection<FieldValue> getFieldValues(IndexConfig indexConfig, IndexedProperty indexedProperty, Object model) throws FieldValueProviderException
	{

		if (model == null)
		{
			throw new IllegalArgumentException("No model given");
		}

		final HeringProductModel base = variantsUtils.getAvailableBaseProduct(model);
		
		if(base == null)
		{
			return Collections.emptyList();
		}
		
		List<HeringSizeVariantProductModel> allSizeVariants = variantsUtils.getAvailableSizeVariants(base);
		
		if(CollectionUtils.isEmpty(allSizeVariants))
		{
			return Collections.emptyList();
		}
		
	
			
		Set<HeringSizeVariantProductModel> set = new TreeSet<HeringSizeVariantProductModel>(HeringComparatorFactory.getComparatorHeringSizeVariantProductModel());
		for(HeringSizeVariantProductModel i : set)
			if(i.getSize() != null)
				set.add(i);
		
		final List<FieldValue> values = new ArrayList<>();

		for(HeringSizeVariantProductModel size : set) 
		{
			Collection<FieldValue> indexed = super.getFieldValues(indexConfig, indexedProperty, size);
			if(CollectionUtils.isNotEmpty(indexed))
			{
				values.addAll(indexed);
			}
		}
		
		return values;
	}
}