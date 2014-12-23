/**
 *
 */
package de.kpfamily.core.model;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

import javax.annotation.Resource;

import de.kpfamily.core.constants.KpfamilyCoreConstants;
import de.kpfamily.core.enums.ShippingMethod;
import de.kpfamily.core.util.JaloUtils;


/**
 * @author franthescollymaneira
 *
 */
public class ShippingMethodDynamicInternalCode implements DynamicAttributeHandler<String, BabyartikelProductModel>
{
	@Resource
	private ModelService modelService;

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
		final ShippingMethod shippingMethod = product.getShippingMethod();
		final Item item = modelService.getSource(shippingMethod);

		return JaloUtils.getAttributeSilently(item, KpfamilyCoreConstants.Attributes.ShippingMethod.INTERNALCODE);
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