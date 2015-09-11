package com.flieger.facades.populators;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import com.flieger.bonussystem.data.BonusSystemData;
import com.flieger.model.user.BonusSystemModel;
import com.flieger.services.BonusSystemService;


public class CustomerBonusSystemPopulator implements Populator<CustomerModel, CustomerData>
{

	private BonusSystemService bonusSystemService;

	private Converter<BonusSystemModel, BonusSystemData> bonusSystemConverter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final CustomerModel source, final CustomerData target) throws ConversionException
	{
		final BonusSystemModel bonusSystem = source.getBonusSystem();

		if (bonusSystem != null)
		{
			target.setBonusSystem(bonusSystemConverter.convert(bonusSystem));
		}
	}

	public Converter<BonusSystemModel, BonusSystemData> getBonusSystemConverter()
	{
		return bonusSystemConverter;
	}

	public void setBonusSystemConverter(final Converter<BonusSystemModel, BonusSystemData> bonusSystemConverter)
	{
		this.bonusSystemConverter = bonusSystemConverter;
	}

	public BonusSystemService getBonusSystemService()
	{
		return bonusSystemService;
	}

	public void setBonusSystemService(final BonusSystemService bonusSystemService)
	{
		this.bonusSystemService = bonusSystemService;
	}
}