/**
 * 
 */
package com.flieger.facades.converters;

import de.hybris.platform.converters.impl.AbstractPopulatingConverter;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.flieger.bonussystem.data.BonusSystemData;
import com.flieger.bonussystem.data.BonusSystemEntryData;
import com.flieger.model.user.BonusSystemEntryModel;
import com.flieger.model.user.BonusSystemModel;


/**
 * 
 * @author franthescollymaneira
 */
public class BonusSystemConverter extends AbstractPopulatingConverter<BonusSystemModel, BonusSystemData>
{
	private Converter<BonusSystemEntryModel, BonusSystemEntryData> bonusSystemEntryConverter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.impl.AbstractConverter#createTarget()
	 */
	@Override
	protected BonusSystemData createTarget()
	{
		return new BonusSystemData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.impl.AbstractPopulatingConverter#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final BonusSystemModel source, final BonusSystemData target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		final Double points = source.getAvailablePoints();
		target.setPoints(points == null ? 0 : points.intValue());

		addLogEntries(source, target);

		super.populate(source, target);
	}

	/**
	 * @param source
	 * @param target
	 */
	private void addLogEntries(final BonusSystemModel source, final BonusSystemData target)
	{
		if (source.getLogEntries() == null)
		{
			return;
		}

		final List<BonusSystemEntryData> entries = new ArrayList<BonusSystemEntryData>();

		for (final BonusSystemEntryModel logModel : source.getLogEntries())
		{
			entries.add(bonusSystemEntryConverter.convert(logModel));
		}

		target.setBookedEntries(entries);
		target.setEntries(entries);
	}

	/**
	 * @return the bonusSystemLogConverter
	 */
	public Converter<BonusSystemEntryModel, BonusSystemEntryData> getBonusSystemEntryConverter()
	{
		return bonusSystemEntryConverter;
	}

	/**
	 * @param bonusSystemEntryConverter
	 *           the bonusSystemLogConverter to set
	 */
	public void setBonusSystemEntryConverter(final Converter<BonusSystemEntryModel, BonusSystemEntryData> bonusSystemEntryConverter)
	{
		this.bonusSystemEntryConverter = bonusSystemEntryConverter;
	}
}
