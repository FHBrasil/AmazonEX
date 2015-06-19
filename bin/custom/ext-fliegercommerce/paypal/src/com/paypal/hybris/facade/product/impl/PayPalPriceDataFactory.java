package com.paypal.hybris.facade.product.impl;

import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commercefacades.product.impl.DefaultPriceDataFactory;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class PayPalPriceDataFactory extends DefaultPriceDataFactory
{
	private final ConcurrentMap<String, NumberFormat> currencyFormats = new ConcurrentHashMap<>();

	@Override
	public PriceData create(final PriceDataType priceType, final BigDecimal value, final CurrencyModel currency)
	{
		Assert.notNull(priceType, "Parameter priceType cannot be null.");
		Assert.notNull(value, "Parameter value cannot be null.");
		Assert.notNull(currency, "Parameter currency cannot be null.");

		final PriceData priceData = createPriceData();

		priceData.setPriceType(priceType);
		priceData.setValue(value);
		priceData.setCurrencyIso(currency.getIsocode());
		priceData.setFormattedValue(formatPrice(value, currency, true));
		priceData.setFormattedPriceWithoutCurrencySymbol(formatPrice(value, currency, false));

		return priceData;
	}

	protected String formatPrice(final BigDecimal value, final CurrencyModel currency, final boolean addCurrencySymbol)
	{
		final LanguageModel currentLanguage = getCommonI18NService().getCurrentLanguage();
		Locale locale = getCommerceCommonI18NService().getLocaleForLanguage(currentLanguage);
		if (locale == null)
		{
			// Fallback to session locale
			locale = getI18NService().getCurrentLocale();
		}

		final NumberFormat currencyFormat = createCurrencyFormat(locale, currency, addCurrencySymbol);
		return currencyFormat.format(value);
	}

	/**
	 * @param locale
	 * @param currency
	 * @return A clone of {@link NumberFormat} from the instance in the local cache, if the cache does not contain an
	 *         instance of a NumberFormat for a given locale and currency one would be added.
	 */
	protected NumberFormat createCurrencyFormat(final Locale locale, final CurrencyModel currency, final boolean addCurrencySymbol)
	{
		final String keySuffix = addCurrencySymbol ? "_cs" : StringUtils.EMPTY;
		final String key = locale.getISO3Country() + "_" + currency.getIsocode() + keySuffix;

		NumberFormat numberFormat = currencyFormats.get(key);
		if (numberFormat == null)
		{
			final NumberFormat currencyFormat = createNumberFormat(locale, currency, addCurrencySymbol);
			numberFormat = currencyFormats.putIfAbsent(key, currencyFormat);
			if (numberFormat == null)
			{
				numberFormat = currencyFormat;
			}
		}
		// don't allow multiple references
		return (NumberFormat) numberFormat.clone();
	}

	protected NumberFormat createNumberFormat(final Locale locale, final CurrencyModel currency, final boolean addCurrencySymbol)
	{
		DecimalFormat currencyFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(locale);

		if (!addCurrencySymbol)
		{
			currencyFormat = recreateFormatWithoutCurrencySymbrol(currencyFormat);
		}

		adjustDigits(currencyFormat, currency);
		adjustSymbol(currencyFormat, currency);
		return currencyFormat;
	}

	private DecimalFormat recreateFormatWithoutCurrencySymbrol(DecimalFormat currencyFormat)
	{
		String pattern = currencyFormat.toPattern();
		String newPattern = pattern.replace("\u00A4", StringUtils.EMPTY).trim();
		DecimalFormat newFormat = new DecimalFormat(newPattern);
		return newFormat;
	}

	/**
	 * Adjusts {@link DecimalFormat}'s symbol according to given {@link CurrencyModel}.
	 */
	/*protected DecimalFormat adjustSymbol(final DecimalFormat format, final CurrencyModel currencyModel, final boolean addCurrencySymbol)
	{
		final String symbol = currencyModel.getSymbol();
		if (symbol != null && addCurrencySymbol)
		{
			final DecimalFormatSymbols symbols = format.getDecimalFormatSymbols(); // does cloning
			final String iso = currencyModel.getIsocode();
			boolean changed = false;
			if (!iso.equalsIgnoreCase(symbols.getInternationalCurrencySymbol()))
			{
				symbols.setInternationalCurrencySymbol(iso);
				changed = true;
			}
			if (!symbol.equals(symbols.getCurrencySymbol()))
			{
				symbols.setCurrencySymbol(symbol);
				changed = true;
			}
			if (changed)
			{
				format.setDecimalFormatSymbols(symbols);
			}
		}
		else

		return format;
	}*/
}
