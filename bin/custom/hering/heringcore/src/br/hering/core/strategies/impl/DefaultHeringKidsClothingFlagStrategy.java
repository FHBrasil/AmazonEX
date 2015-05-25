/**
 * 
 */
package br.hering.core.strategies.impl;

import de.hybris.platform.classification.ClassificationService;
import de.hybris.platform.classification.features.Feature;
import de.hybris.platform.classification.features.FeatureList;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

/**
 * @author franthescollymaneira
 *
 */
public class DefaultHeringKidsClothingFlagStrategy implements HeringKidsClothingFlagStrategy
{
	@Resource
	private ClassificationService classificationService;
	
	@Resource
	protected CommonI18NService commonI18NService;
	
	/* (non-Javadoc)
	 * @see br.hering.core.strategies.impl.HeringKidsClothingFlagStrategy#isKidsClothing(de.hybris.platform.core.model.product.ProductModel)
	 */
	@Override
	public boolean isKidsClothing(final ProductModel product)
	{
		Assert.notNull(product, "product is null");
		
		JaloSession.getCurrentSession().setUser(UserManager.getInstance().getAdminEmployee());
		//TODO FIX LANGUAGE
		commonI18NService.setCurrentLanguage(commonI18NService.getLanguage("pt"));
		commonI18NService.setCurrentCurrency(commonI18NService.getCurrency("BRL"));
		
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
		
		JaloSession.getCurrentSession().setUser(UserManager.getInstance().getAdminEmployee());
		//TODO FIX LANGUAGE
		commonI18NService.setCurrentLanguage(commonI18NService.getLanguage("pt"));
		commonI18NService.setCurrentCurrency(commonI18NService.getCurrency("BRL"));
		
		String subgrupo = getFeatureValue(product, "Subgrupo");
		
		if(StringUtils.isBlank(subgrupo))
		{
			return false;
		}

		boolean isBabyClothing = StringUtils.containsIgnoreCase(subgrupo, "bebê");
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