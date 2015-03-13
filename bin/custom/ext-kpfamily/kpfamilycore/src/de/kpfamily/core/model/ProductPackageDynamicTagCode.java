/**
 *
 */
package de.kpfamily.core.model;

import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;


/**
 * @author franthescollymaneira
 *
 */
public class ProductPackageDynamicTagCode extends AbstractDynamicAttributeHandler<String, ProductPackageModel>
{
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler#get(de.hybris.platform.servicelayer
	 * .model.AbstractItemModel)
	 */
	@Override
	public String get(final ProductPackageModel model)
	{
		//TODO implementar aqui
		return "hardcoded";
	}
}
