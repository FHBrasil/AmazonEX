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
import com.flieger.bonussystem.data.BonusSystemLogData;
import com.flieger.model.user.BonusSystemLogModel;
import com.flieger.model.user.BonusSystemModel;


/**
 * 
 * @author franthescollymaneira
 */
public class BonusSystemConverter extends AbstractPopulatingConverter<BonusSystemModel, BonusSystemData>
{
	private Converter<BonusSystemLogModel, BonusSystemLogData> bonusSystemLogConverter;

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

		final Double points = source.getPoints();
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
		if (source.getLog() == null)
		{
			return;
		}

		final List<BonusSystemLogData> log = new ArrayList<BonusSystemLogData>();

		for (final BonusSystemLogModel logModel : source.getLog())
		{
			log.add(bonusSystemLogConverter.convert(logModel));
		}

		target.setLog(log);
	}

	/**
	 * @return the bonusSystemLogConverter
	 */
	public Converter<BonusSystemLogModel, BonusSystemLogData> getBonusSystemLogConverter()
	{
		return bonusSystemLogConverter;
	}

	/**
	 * @param bonusSystemLogConverter
	 *           the bonusSystemLogConverter to set
	 */
	public void setBonusSystemLogConverter(final Converter<BonusSystemLogModel, BonusSystemLogData> bonusSystemLogConverter)
	{
		this.bonusSystemLogConverter = bonusSystemLogConverter;
	}
}
