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
public class ProductWeightHandler extends AbstractFeatureValueHandler<Double> implements DynamicAttributeHandler<Double, HeringSizeVariantProductModel>
{
	/* (non-Javadoc)
	 * @see de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler#get(de.hybris.platform.servicelayer.model.AbstractItemModel)
	 */
	@Override
	public Double get(HeringSizeVariantProductModel product)
	{
		Assert.notNull(product);
		
		Object featureValue = getFeatureValue(product, "Peso");
		
		return featureValue == null ? Double.valueOf(0) : Double.valueOf(featureValue.toString());
	}

	/* (non-Javadoc)
	 * @see de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler#set(de.hybris.platform.servicelayer.model.AbstractItemModel, java.lang.Object)
	 */
	@Override
	public void set(HeringSizeVariantProductModel product, Double value)
	{
		throw new NotImplementedException();
	}
}