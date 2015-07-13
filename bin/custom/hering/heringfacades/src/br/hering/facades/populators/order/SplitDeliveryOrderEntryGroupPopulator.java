package br.hering.facades.populators.order;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import br.hering.core.strategies.CheckSplitOrderEntriesEnabledStrategy;
import br.hering.core.strategies.ShippingEstimatesStrategy;

import com.flieger.facades.order.data.ImmediateDeliveryOrderEntryGroupData;
import com.flieger.facades.order.data.LateDeliveryOrderEntryGroupData;
import com.flieger.facades.order.data.SplitDeliveryOrderEntryGroupData;

import de.hybris.platform.acceleratorfacades.order.data.DeliveryOrderEntryGroupData;
import de.hybris.platform.acceleratorfacades.order.populators.DeliveryOrderEntryGroupPopulator;
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class SplitDeliveryOrderEntryGroupPopulator extends DeliveryOrderEntryGroupPopulator 
{
	private static final Logger LOG = Logger.getLogger(SplitDeliveryOrderEntryGroupPopulator.class);
	
	private CheckSplitOrderEntriesEnabledStrategy checkSplitOrderEntriesEnabledStrategy;
	
	private ShippingEstimatesStrategy shippingEstimatesStrategy;
	
	private static final Comparator<OrderEntryData> COMPARATOR_ORDER_ENTRIES = new Comparator<OrderEntryData>() {
		public int compare(final OrderEntryData e1, final OrderEntryData e2) {
			return ObjectUtils.compare(e1.getNamedDeliveryDate(), e2.getNamedDeliveryDate());
		}
	};
	
	private static final Comparator<DeliveryOrderEntryGroupData> COMPARATOR_DELIVERY_GROUPS = new Comparator<DeliveryOrderEntryGroupData>() {
		public int compare(final DeliveryOrderEntryGroupData g1, final DeliveryOrderEntryGroupData g2) {
			return groupPriority(g1).compareTo(groupPriority(g2));
		}
		
		private Integer groupPriority(DeliveryOrderEntryGroupData group) {
			return (group instanceof ImmediateDeliveryOrderEntryGroupData) ? 0 : 1;
		}
	};
	
	@Override
	public void populate(AbstractOrderModel source, AbstractOrderData target) throws ConversionException 
	{
		super.populate(source, target);
		
		if(getCheckSplitOrderEntriesEnabledStrategy().isEnabled())
		{
			sortDeliveryGroups(target);
			updateGroupNamedDeliveryDate(target);
		}
	}

	@Override
	protected void createUpdateShipGroupData(AbstractOrderEntryModel entryModel, AbstractOrderData target) 
	{
		if(!getCheckSplitOrderEntriesEnabledStrategy().isEnabled())
		{
			super.createUpdateShipGroupData(entryModel, target);
			return;
		}
		
		if (entryModel.getDeliveryPointOfService() == null)
		{
			Class<? extends SplitDeliveryOrderEntryGroupData> type = findGroupType(entryModel);

			SplitDeliveryOrderEntryGroupData groupData = getGroupByType(target.getDeliveryOrderGroups(), type);
			
			if(groupData == null)
			{
				groupData = createGroup(type);
				target.getDeliveryOrderGroups().add(groupData);
			}
			
			updateGroupTotalPriceWithTax(entryModel, groupData);
			groupData.getEntries().add(getOrderEntryData(target, entryModel.getEntryNumber()));
		}
	}
	
	private void updateGroupNamedDeliveryDate(final AbstractOrderData target) 
	{
		for(DeliveryOrderEntryGroupData group : target.getDeliveryOrderGroups())
		{
			if(group instanceof SplitDeliveryOrderEntryGroupData)
			{
				Date maxDeliveryDate = getMaxDeliveryDate(group.getEntries());
				((SplitDeliveryOrderEntryGroupData) group).setNamedDeliveryDate(maxDeliveryDate);
			}
		}
	}
	
	private Class<? extends SplitDeliveryOrderEntryGroupData> findGroupType(AbstractOrderEntryModel entryModel) 
	{
		boolean isImmediateDelivery = getShippingEstimatesStrategy().isImmediateShipping(entryModel);

		return isImmediateDelivery ? ImmediateDeliveryOrderEntryGroupData.class : LateDeliveryOrderEntryGroupData.class;
	}
	
	private SplitDeliveryOrderEntryGroupData createGroup(Class<? extends SplitDeliveryOrderEntryGroupData> type) 
	{
		try 
		{
			final SplitDeliveryOrderEntryGroupData groupData = type.newInstance();
			groupData.setEntries(new TreeSet<OrderEntryData>(COMPARATOR_ORDER_ENTRIES));
			
			return groupData;
		} 
		catch (Exception e) 
		{
			LOG.error("Error", e);
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private <T extends SplitDeliveryOrderEntryGroupData> T getGroupByType(List<DeliveryOrderEntryGroupData> groups, Class<T> type) 
	{
		if(CollectionUtils.isEmpty(groups))
		{
			return null;
		}

		for(final DeliveryOrderEntryGroupData group : groups)
		{
			if(group.getClass().isAssignableFrom(type))
			{
				return (T) group;
			}
		}
		
		return null;
	}

	private Date getMaxDeliveryDate(Collection<OrderEntryData> entries) 
	{
		Date max = null;
		
		for(OrderEntryData entry : entries)
		{
			LOG.debug("max: " + max);
			LOG.debug("entry: " + entry.getNamedDeliveryDate());
			if(max == null || entry.getNamedDeliveryDate().after(max))
			{
				LOG.debug("updating max");
				max = entry.getNamedDeliveryDate();
			}
		}
		
		return max;
	}
	
	private void sortDeliveryGroups(AbstractOrderData target) 
	{
		if(CollectionUtils.isNotEmpty(target.getDeliveryOrderGroups()))
		{
			Collections.sort(target.getDeliveryOrderGroups(), COMPARATOR_DELIVERY_GROUPS);
		}
	}

	public CheckSplitOrderEntriesEnabledStrategy getCheckSplitOrderEntriesEnabledStrategy() 
	{
		return checkSplitOrderEntriesEnabledStrategy;
	}

	@Required
	public void setCheckSplitOrderEntriesEnabledStrategy(CheckSplitOrderEntriesEnabledStrategy strategy) 
	{
		this.checkSplitOrderEntriesEnabledStrategy = strategy;
	}

	public ShippingEstimatesStrategy getShippingEstimatesStrategy() 
	{
		return shippingEstimatesStrategy;
	}

	@Required
	public void setShippingEstimatesStrategy(ShippingEstimatesStrategy strategy) 
	{
		this.shippingEstimatesStrategy = strategy;
	}
}