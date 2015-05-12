/**
 * 
 */
package com.flieger.facades.converters;

import de.hybris.platform.converters.impl.AbstractPopulatingConverter;

import org.springframework.util.Assert;

import com.flieger.bonussystem.data.BonusSystemLogData;
import com.flieger.model.user.BonusSystemLogModel;


/**
 * 
 * @author franthescollymaneira
 */
public class BonusSystemLogConverter extends AbstractPopulatingConverter<BonusSystemLogModel, BonusSystemLogData>
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.impl.AbstractConverter#createTarget()
	 */
	@Override
	protected BonusSystemLogData createTarget()
	{
		return new BonusSystemLogData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.impl.AbstractPopulatingConverter#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final BonusSystemLogModel source, final BonusSystemLogData target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		final Double points = source.getPoints();

		target.setPoints(points == null ? 0 : points.intValue());
		target.setDate(source.getDate());
		target.setDescription(source.getDescription());
		target.setReference(source.getReference());
		target.setType(source.getType());

		super.populate(source, target);
	}
}
