package com.flieger.attribute;

import java.util.Iterator;

import org.apache.commons.collections.CollectionUtils;

import com.flieger.model.user.BonusSystemEntryModel;
import com.flieger.model.user.BonusSystemModel;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

public class BonusSystemAvailablePointsAttributeHandler implements DynamicAttributeHandler<Double, BonusSystemModel> {

	@Override
	public Double get(BonusSystemModel system) {
		double value = 0;
		for(BonusSystemEntryModel entry : system.getLogEntries()) {
			if(isCartEntry(entry))
				continue;
			value += entry.getPoints();
		}
		return value;
	}

	private boolean isCartEntry(BonusSystemEntryModel entry) {
		if(entry.getAppliedDiscount() != null //
				&& entry.getAppliedDiscount().getOrders() != null) {
			Iterator<AbstractOrderModel> iterator = entry.getAppliedDiscount().getOrders().iterator();
			if(iterator.hasNext())
				return iterator.next() instanceof CartModel;
		}
		return false;
	}

	@Override
	public void set(BonusSystemModel system, Double value) {
		//
	}

}
