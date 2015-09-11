/**
 * 
 */
package br.hering.core.model;

import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

import org.springframework.util.Assert;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author franthescollymaneira
 *
 */
public class ProductColorRGBHandler extends AbstractFeatureValueHandler<String> implements DynamicAttributeHandler<String, HeringSizeVariantProductModel>
{
	/* (non-Javadoc)
	 * @see de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler#get(de.hybris.platform.servicelayer.model.AbstractItemModel)
	 */
	@Override
	public String get(HeringSizeVariantProductModel product)
	{
		Assert.notNull(product);
		
		Object featureValue = getFeatureValue(product, "RGB");

		return featureValue == null ? null : featureValue.toString();
	}

	/* (non-Javadoc)
	 * @see de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler#set(de.hybris.platform.servicelayer.model.AbstractItemModel, java.lang.Object)
	 */
	@Override
	public void set(HeringSizeVariantProductModel product, String value)
	{
		throw new NotImplementedException();
	}
}