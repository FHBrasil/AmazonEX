package com.flieger.attribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
		Map<String, BonusSystemEntryModel> placeholders = new HashMap<String, BonusSystemEntryModel>();
		for(BonusSystemEntryModel entry : system.getLogEntries()) {
			if(isCartEntry(entry))
				continue;
			if(isPlaceHolder(entry))
				placeholders.put(getCode(entry), entry);
			else {
				placeholders.remove(getCode(entry));
				value += getPoints(entry);
			}
		}
		return value;
	}

	private boolean isCartEntry(BonusSystemEntryModel entry) {
		if(entry.getReference() instanceof CartModel) {
			return true;
		}
		return false;
	}

	private boolean isPlaceHolder(BonusSystemEntryModel entry) {
		return "placeholder".equals(entry.getType());
	}

	private String getCode(BonusSystemEntryModel entry) {
		return entry.getReference() != null ? entry.getReference().getCode() : null;
	}

	private double getPoints(BonusSystemEntryModel entry) {
		return entry.getPoints() != null ? entry.getPoints().doubleValue() : 0;
	}

	@Override
	public void set(BonusSystemModel system, Double value) {
		//
	}

}
