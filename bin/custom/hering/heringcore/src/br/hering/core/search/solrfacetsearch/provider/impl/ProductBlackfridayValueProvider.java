/**
 * 
 */
package br.hering.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.classification.ClassificationService;
import de.hybris.platform.classification.features.Feature;
import de.hybris.platform.classification.features.FeatureList;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.user.UserManager;
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

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import br.hering.core.model.HeringSizeVariantProductModel;

/**
 * @author Vinicius de Souza
 * @author franthescollymaneira
 *
 */
public class ProductBlackfridayValueProvider extends AbstractPropertyFieldValueProvider implements Serializable, FieldValueProvider
{
	private FieldNameProvider fieldNameProvider;

	private CommonI18NService commonI18NService;
	
	@Resource
	private ClassificationService classificationService;
	
	@Override
	public Collection<FieldValue> getFieldValues(IndexConfig arg0, IndexedProperty indexedProperty, Object model)
			throws FieldValueProviderException
	{
		Collection<FieldValue> values = Collections.emptyList();
		
		if(model instanceof HeringSizeVariantProductModel)
		{
			JaloSession.getCurrentSession().setUser(UserManager.getInstance().getAdminEmployee());

			//TODO FIX LANGUAGE
			getCommonI18NService().setCurrentLanguage(getCommonI18NService().getLanguage("pt"));
			getCommonI18NService().setCurrentCurrency(getCommonI18NService().getCurrency("BRL"));
			
			final HeringSizeVariantProductModel product = (HeringSizeVariantProductModel) model;
			String tag = getFeatureValue(product, "Tag");
			if(validTag(tag))
			{
   			values = new ArrayList<FieldValue>();
   			
   			Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
   			for (String fieldName : fieldNames) 
   			{
   				values.add(new FieldValue(fieldName, Boolean.TRUE));
   			}
			}
		}
		
		return values;
	}
	
	private boolean validTag(String tag) {
		return !StringUtils.isBlank(tag) 
				&& (StringUtils.containsIgnoreCase(tag, "blackfriday_2014_dzarm") || 
					 StringUtils.containsIgnoreCase(tag, "blackfriday2_2014_hering"));
	}

	protected <T> T getFeatureValue(final ProductModel product, final String featureName)
	{
		final FeatureList featureList = classificationService.getFeatures(product);

		final Feature feature = featureList.getFeatureByName(featureName);
		
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