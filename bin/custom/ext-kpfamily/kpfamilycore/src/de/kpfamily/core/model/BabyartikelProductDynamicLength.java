/**
 *
 */
package de.kpfamily.core.model;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;


/**
 * @author franthescollymaneira
 *
 */
public class BabyartikelProductDynamicLength extends AbstractDynamicAttributeHandler<String, ProductModel>
{
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler#get(de.hybris.platform.servicelayer.model
	 * .AbstractItemModel)
	 */
	@Override
	public String get(final ProductModel product)
	{
		//TODO implementar classification system
		return null;
	}
}