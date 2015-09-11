/**
 * 
 */
package br.hering.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.classification.ClassificationService;
import de.hybris.platform.classification.features.Feature;
import de.hybris.platform.classification.features.FeatureList;
import de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.OptionalModelPropertyFieldValueProvider;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.collections.CollectionUtils;

import de.hybris.platform.util.Config;

import br.hering.core.model.HeringProductModel;
import br.hering.core.model.HeringSizeVariantProductModel;
import br.hering.core.model.HeringStyleVariantProductModel;


/**
 * @author Wagner Ribeiro
 *
 */
public class HeringShowCaseVariantsProvider extends OptionalModelPropertyFieldValueProvider
{
	@Resource
	protected ClassificationService classificationService;

	@Resource
	private VariantsUtils variantsUtils;

	@Override
	public Collection<FieldValue> getFieldValues(IndexConfig indexConfig, IndexedProperty indexedProperty, Object model)
			throws FieldValueProviderException
	{
		if (model == null)
		{
			throw new IllegalArgumentException("No model given");
		}

		final HeringProductModel base = variantsUtils.getAvailableBaseProduct(model);
		if (base == null)
		{
			return Collections.emptyList();
		}

		Collection<HeringStyleVariantProductModel> styleVariants = variantsUtils.getAvailableStyleVariants(base);
		if (CollectionUtils.isEmpty(styleVariants))
		{
			return Collections.emptyList();
		}

		Collection<String> productVariants = new ArrayList<String>();
		for (HeringStyleVariantProductModel style : styleVariants)
		{
			List<HeringSizeVariantProductModel> sizes = variantsUtils.getAvailableSizeVariants(style);
			if (CollectionUtils.isNotEmpty(sizes))
			{
				StringBuilder variantLine = new StringBuilder();
				variantLine.append( createVariantLineColor(style) )
					.append(":").append( createVariantLineSizes(sizes) );
				productVariants.add(variantLine.toString());
			}
			else
			{
				LOG.error("Error on get product sizes");
				LOG.error(style.getCode());
				return Collections.emptyList();
			}
		}

		final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty, null);
		final List<FieldValue> values = new ArrayList<FieldValue>();
		for (String variant : productVariants)
		{
			for (final String fieldName : fieldNames)
			{
				values.add(new FieldValue(fieldName, variant));
			}
		}

		return values;
	}

	private String createVariantLineColor(HeringStyleVariantProductModel style)
	{
		final HeringSizeVariantProductModel size = 
				(HeringSizeVariantProductModel) variantsUtils.firstAvailableChild(style.getVariants());
		
		StringBuilder sanitizedColor = new StringBuilder();
		
		sanitizedColor.append(getSanitizedColor(size));
		if (sanitizedColor.toString().isEmpty())
		{
			LOG.error("RGB vazio code: " + size.getCode());
		}
		return style.getCode().replace("_", "").concat(";").concat(sanitizedColor.toString())
				.concat(";").concat(size.getColorBasicDescription());
	}

	private String createVariantLineSizes(List<HeringSizeVariantProductModel> sizes)
	{
		Collection<String> variants = new ArrayList<String>();
		for (HeringSizeVariantProductModel size : sizes)
		{
			variants.add(size.getCode() + "-" + size.getSize());
		}
		StringBuilder finalVariantString = new StringBuilder();
		boolean isFirstValue = true;
		for (String variant : variants)
		{
			if (isFirstValue)
			{
				finalVariantString.append(variant);
				isFirstValue = false;
			}
			else
			{
				finalVariantString.append(";").append(variant);
			}
		}
		return finalVariantString.toString();
	}

	private String getSanitizedColor(final Object model)
	{
		String rawValue = getFeatureValue((ProductModel) model, "RGB");

		if (StringUtils.isBlank(rawValue))
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

		if (value == null)
		{
			return null;
		}

		return (T) value;
	}
}