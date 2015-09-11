package com.paypal.hybris.facade.order.converters.populator;

import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.acceleratorfacades.order.data.DeliveryOrderEntryGroupData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;

/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class PayPalDeliveryOrderGroupPopulator extends PayPalOrderEntryGroupPopulator
{
	@Override
	public void populate(final AbstractOrderModel source, final AbstractOrderData target) throws ConversionException
	{
		super.populate(source, target);

		if (target.getDeliveryOrderGroups() == null)
		{
			target.setDeliveryOrderGroups(new ArrayList<DeliveryOrderEntryGroupData>());
		}

		for (final AbstractOrderEntryModel entryModel : source.getEntries())
		{
			createUpdateShipGroupData(entryModel, target);
		}
		target.setDeliveryItemsQuantity(Long.valueOf(sumDeliveryItemsQuantity(source)));
	}

	protected void createUpdateShipGroupData(final AbstractOrderEntryModel entryModel, final AbstractOrderData target)
	{
		if (entryModel.getDeliveryPointOfService() == null)
		{
			DeliveryOrderEntryGroupData groupData = null;
			if (CollectionUtils.isNotEmpty(target.getDeliveryOrderGroups()))
			{
				groupData = target.getDeliveryOrderGroups().iterator().next();
			}
			if (groupData == null)
			{
				groupData = new DeliveryOrderEntryGroupData();
				groupData.setEntries(new ArrayList<OrderEntryData>());
				target.getDeliveryOrderGroups().add(groupData);
			}

			updateGroupTotalPriceWithTax(entryModel, groupData);
			updateGroupTotalPrice(entryModel, groupData);
			updateTotalTax(entryModel, groupData);
			groupData.getEntries().add(getOrderEntryData(target, entryModel.getEntryNumber()));
		}
	}

}
