/**
 *
 */
package de.kpfamily.core.model;

import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;


/**
 * @author franthescollymaneira
 *
 */
public class BabyartikelProductDynamicLength extends AbstractDynamicAttributeHandler<String, BabyartikelProductModel>
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler#get(de.hybris.platform.servicelayer.model
	 * .AbstractItemModel)
	 */
	@Override
	public String get(final BabyartikelProductModel product)
	{
		//TODO implementar classification system
		return null;
	}
}