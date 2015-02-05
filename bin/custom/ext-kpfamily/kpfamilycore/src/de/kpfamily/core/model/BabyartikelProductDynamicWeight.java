/**
 *
 */
package de.kpfamily.core.model;

import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;


/**
 * @author franthescollymaneira
 *
 */
public class BabyartikelProductDynamicWeight implements DynamicAttributeHandler<String, BabyartikelProductModel>
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

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler#set(de.hybris.platform.servicelayer.model
	 * .AbstractItemModel, java.lang.Object)
	 */
	@Override
	public void set(final BabyartikelProductModel product, final String value)
	{
		throw new UnsupportedOperationException();
	}
}