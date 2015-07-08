/**
 * 
 */
package com.flieger.facades.converters;

import java.util.Iterator;

import de.hybris.platform.converters.impl.AbstractPopulatingConverter;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.util.Assert;

import com.flieger.bonussystem.data.BonusSystemEntryData;
import com.flieger.model.user.BonusSystemEntryModel;


/**
 * 
 * @author franthescollymaneira
 */
public class BonusSystemEntryConverter extends AbstractPopulatingConverter<BonusSystemEntryModel, BonusSystemEntryData>
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.impl.AbstractConverter#createTarget()
	 */
	@Override
	protected BonusSystemEntryData createTarget()
	{
		return new BonusSystemEntryData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.impl.AbstractPopulatingConverter#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final BonusSystemEntryModel source, final BonusSystemEntryData target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		final Double points = source.getPoints();

		target.setPoints(points == null ? 0 : points.intValue());
		target.setDate(source.getDate());
//		Integer days = source.getBonusSystem().getConfiguration().getPointsExpirationInDays();
//		if(days != null)
//		target.setExpirationDate(DateUtils.addDays(source.getDate(), days.intValue()));
		target.setDescription(source.getDescription());
		target.setReference(source.getReference());
		target.setReferenceType(getReferenceType(source));
		target.setType(source.getType());

		super.populate(source, target);
	}

	private String getReferenceType(BonusSystemEntryModel entry) {
		if(entry.getAppliedDiscount() != null //
				&& entry.getAppliedDiscount().getOrders() != null) {
			Iterator<AbstractOrderModel> iterator = entry.getAppliedDiscount().getOrders().iterator();
			if(iterator.hasNext() && iterator.next() instanceof CartModel)
				return "Cart";
		}

		return "Order";
	}
}
