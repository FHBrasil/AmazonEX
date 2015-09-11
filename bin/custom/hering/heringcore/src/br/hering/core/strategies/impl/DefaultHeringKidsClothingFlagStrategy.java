/**
 * 
 */
package br.hering.core.strategies.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import de.hybris.platform.classification.ClassificationService;
import de.hybris.platform.classification.features.Feature;
import de.hybris.platform.classification.features.FeatureList;
import de.hybris.platform.core.model.product.ProductModel;

/**
 * @author franthescollymaneira
 *
 */
public class DefaultHeringKidsClothingFlagStrategy implements HeringKidsClothingFlagStrategy
{
	@Resource
	private ClassificationService classificationService;
	
	/* (non-Javadoc)
	 * @see br.hering.core.strategies.impl.HeringKidsClothingFlagStrategy#isKidsClothing(de.hybris.platform.core.model.product.ProductModel)
	 */
	@Override
	public boolean isKidsClothing(final ProductModel product)
	{
		Assert.notNull(product, "product is null");
		
		String grupo = getFeatureValue(product, "Grupo");
		String subgrupo = getFeatureValue(product, "Subgrupo");
		
		if(StringUtils.isBlank(grupo) && StringUtils.isBlank(subgrupo))
		{
			return false;
		}

		boolean isKidsClothing = StringUtils.containsIgnoreCase(grupo, "infantil");
		isKidsClothing |= StringUtils.containsIgnoreCase(subgrupo, "kids");
		isKidsClothing |= isBabyClothing(product);
		
		return isKidsClothing;
	}
	
	/* (non-Javadoc)
	 * @see br.hering.core.strategies.impl.HeringKidsClothingFlagStrategy#isBabyClothing(de.hybris.platform.core.model.product.ProductModel)
	 */
	@Override
	public boolean isBabyClothing(ProductModel product)
	{
		Assert.notNull(product, "product is null");
		
		String subgrupo = getFeatureValue(product, "Subgrupo");
		
		if(StringUtils.isBlank(subgrupo))
		{
			return false;
		}

		boolean isBabyClothing = StringUtils.containsIgnoreCase(subgrupo, "bebêª");
		isBabyClothing |= StringUtils.containsIgnoreCase(subgrupo, "bebe");
		
		return isBabyClothing;
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
}