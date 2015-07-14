/**
 * 
 */
package br.hering.facades.populators;

import de.hybris.platform.commercefacades.order.converters.populator.OrderPopulator;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;

import java.util.LinkedList;

/**
 * @author ghayashi
 *
 */
public class HeringOrderPopulator extends OrderPopulator 
{

	@Override
	public void populate(final OrderModel source, final OrderData target)
	{
		super.populate(source, target);
		
		target.setSplitDeliveryEnabled(source.isSplitDeliveryEnabled());
		
		LinkedList<ConsignmentModel> consignments = new LinkedList<>(source.getConsignments());
		if(!consignments.isEmpty())
		{
			target.setTrackingID(consignments.getLast().getTrackingID());
		}
	}
}
