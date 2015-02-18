/**
 *
 */
package de.kpfamily.core.model;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;

import javax.annotation.Resource;

import de.kpfamily.core.constants.KpfamilyCoreConstants;
import de.kpfamily.core.enums.ShippingMethod;
import de.kpfamily.core.util.JaloUtils;


/**
 * @author franthescollymaneira
 *
 */
public class ProductLogisticDataDynamicShippingMethodInternalCode extends
		AbstractDynamicAttributeHandler<String, ProductLogisticDataModel>
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
	public String get(final ProductLogisticDataModel logisticData)
	{
		final ShippingMethod shippingMethod = logisticData.getShippingMethod();
		final Item item = modelService.getSource(shippingMethod);

		return JaloUtils.getAttributeSilently(item, KpfamilyCoreConstants.Attributes.ShippingMethod.INTERNALCODE);
	}
}