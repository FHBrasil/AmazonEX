/**
 * 
 */
package br.hering.core.search.solrfacetsearch.provider.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
import de.hybris.platform.variants.model.VariantProductModel;

/**
 * @author Vinicius de Souza
 *
 */
public class ProductDescricaoCorValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider,
		Serializable
{
	@Resource
	protected ClassificationService classificationService;
	
	private CommonI18NService commonI18NService;
	
	@Resource
	protected BaseStoreService baseStoreService;
	
	@Resource
	private VariantsUtils variantsUtils;
	
	private FieldNameProvider fieldNameProvider;
	
	@Override
	public Collection<FieldValue> getFieldValues(IndexConfig indexConfig, IndexedProperty indexedProperty, Object model) throws FieldValueProviderException
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
		
		for(final VariantProductModel style : allStyleVariants) 
		{
			final VariantProductModel size = variantsUtils.firstAvailableChild(style.getVariants());
			
			if(size == null)
			{
				continue;
			}
			
			final String descrColor = getFeatureValue((ProductModel) model, "Descrição cor");
			if(StringUtils.isBlank(descrColor))
			{
				continue;
			}
			
			for (final String fieldName : fieldNames) 
			{
				values.add(new FieldValue(fieldName, descrColor));
			}
		}
		
		return values;
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