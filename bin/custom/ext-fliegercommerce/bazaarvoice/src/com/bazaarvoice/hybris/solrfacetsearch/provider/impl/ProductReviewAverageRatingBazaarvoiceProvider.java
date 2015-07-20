package com.bazaarvoice.hybris.solrfacetsearch.provider.impl;

import java.util.ArrayList;
import java.util.Collection;

import de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.ProductReviewAverageRatingValueProvider;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;

import java.util.List;

public class ProductReviewAverageRatingBazaarvoiceProvider extends ProductReviewAverageRatingValueProvider {

	@Override
	public Collection<FieldValue> getFieldValues(IndexConfig indexConfig, IndexedProperty indexedProperty, Object model) throws FieldValueProviderException {
		if (model instanceof ProductModel){
			ProductModel product = (ProductModel) model;
			Collection<FieldValue> fieldValues =  new ArrayList<FieldValue>();
			
			if (indexedProperty.isLocalized()){
				Collection<LanguageModel> languages = indexConfig.getLanguages();
				for(LanguageModel language : languages){
					fieldValues.addAll(createFieldValue(product, language, indexedProperty));
				}
			}else{
				fieldValues.addAll(createFieldValue(product, null, indexedProperty));
			}
			return fieldValues;
		}
		throw new FieldValueProviderException("Cannot evaluate rating of non-product item");
	}
	
	protected List<FieldValue> createFieldValue(ProductModel product, LanguageModel language, IndexedProperty indexedProperty){
		
		Double productRating = product.getReviewsAverageRating() == 0.0D ? null : product.getReviewsAverageRating();
		List<FieldValue> fieldValues = new ArrayList<FieldValue>();
	   
	    if (productRating != null)
	    {
	      addFieldValues(fieldValues, indexedProperty, language, productRating);
	    }

	    return fieldValues;
	}

	protected void addFieldValues(List<FieldValue> fieldValues, IndexedProperty indexedProperty, LanguageModel language, Object value)
	{
		Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty, 
			      language == null ? null : language.getIsocode());
		for (String fieldName :fieldNames){
			fieldValues.add(new FieldValue(fieldName, value));
		}
	}
}
