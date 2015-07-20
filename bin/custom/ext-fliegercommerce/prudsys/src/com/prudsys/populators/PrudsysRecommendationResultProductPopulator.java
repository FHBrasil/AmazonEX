/**
 *
 */
package com.prudsys.populators;

import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.math.BigDecimal;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.flieger.recommendation.data.RecommendationResultValueData;


/**
 * @author franthescollymaneira
 *
 */
public class PrudsysRecommendationResultProductPopulator implements Populator<RecommendationResultValueData, ProductData>
{
	private PriceDataFactory priceDataFactory;

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final RecommendationResultValueData source, final ProductData target) throws ConversionException
	{
		Assert.notNull(source);
		Assert.notNull(target);

		target.setCode(this.<String> getValue(source, "uid"));
		target.setName(this.<String> getValue(source, "name"));

		populatProductUrl(source, target);
		populateProductImages(source, target);
		populateProductPrice(source, target);
	}

	protected void populatProductUrl(final RecommendationResultValueData source, final ProductData target)
	{
		target.setUrl(this.<String> getValue(source, "url"));
	}

	protected void populateProductPrice(final RecommendationResultValueData source, final ProductData target)
	{
		final BigDecimal netUnitPrice = this.<BigDecimal> getValue(source, "netUnitPrice");
		target.setPrice(getPriceDataFactory().create(PriceDataType.BUY, netUnitPrice, "EUR"));
	}

	protected void populateProductImages(final RecommendationResultValueData source, final ProductData target)
	{
		final ImageData imageData = new ImageData();
		imageData.setUrl(this.<String> getValue(source, "imageUrl"));

		target.setImages(Collections.singletonList(imageData));
	}

	private <T> T getValue(final RecommendationResultValueData source, final String key)
	{
		return (T) source.getValues().get(key);
	}

	public PriceDataFactory getPriceDataFactory()
	{
		return priceDataFactory;
	}

	@Required
	public void setPriceDataFactory(final PriceDataFactory priceDataFactory)
	{
		this.priceDataFactory = priceDataFactory;
	}
}