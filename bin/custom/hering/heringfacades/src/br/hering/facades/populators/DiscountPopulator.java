package br.hering.facades.populators;

import static org.apache.commons.lang.BooleanUtils.toBoolean;

import de.hybris.platform.commercefacades.order.price.data.DiscountData;
import de.hybris.platform.commercefacades.storesession.data.CurrencyData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.price.DiscountModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class DiscountPopulator implements Populator<DiscountModel, DiscountData> 
{
	private Converter<CurrencyModel, CurrencyData> currencyConverter;
	
	@Override
	public void populate(DiscountModel source, DiscountData target) throws ConversionException
	{
		target.setAbsolute(toBoolean(source.getAbsolute()));
		target.setCode(source.getCode());
		target.setCurrency(currencyConverter.convert(source.getCurrency()));
		target.setGlobal(toBoolean(source.getGlobal()));
		target.setName(source.getName());
		target.setPriority(source.getPriority());
		target.setValue(source.getValue());
	}

	public Converter<CurrencyModel, CurrencyData> getCurrencyConverter()
	{
		return currencyConverter;
	}

	public void setCurrencyConverter(Converter<CurrencyModel, CurrencyData> currencyConverter)
	{
		this.currencyConverter = currencyConverter;
	}
}