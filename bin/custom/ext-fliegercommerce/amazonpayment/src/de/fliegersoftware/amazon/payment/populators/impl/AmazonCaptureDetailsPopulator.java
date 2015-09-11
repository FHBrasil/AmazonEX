/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fliegersoftware.amazon.payment.populators.impl;


import org.springframework.util.Assert;

import com.amazonservices.mws.offamazonpayments.model.Address;
import com.amazonservices.mws.offamazonpayments.model.CaptureDetails;
import com.amazonservices.mws.offamazonpayments.model.OrderReferenceDetails;
import com.amazonservices.mws.offamazonpayments.model.Price;

import de.fliegersoftware.amazon.core.data.AmazonCaptureDetailsData;
import de.fliegersoftware.amazon.core.data.AmazonOrderReferenceDetailsData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

/**
 *
 * @author taylor.savegnago
 */
public class AmazonCaptureDetailsPopulator implements Populator<CaptureDetails, AmazonCaptureDetailsData>
{
   private Converter<Price, PriceData> amazonPriceConverter;
   
	@Override
	public void populate(CaptureDetails source, AmazonCaptureDetailsData target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");
		
		target.setAmazonCaptureId(source.getAmazonCaptureId());
		target.setCaptureReferenceId(source.getCaptureReferenceId());
		
		if (source.getCaptureAmount() != null)
		{
			Price priceCaptureAmount = source.getCaptureAmount();
			target.setCaptureAmount(amazonPriceConverter.convert(priceCaptureAmount));
		}
   }

	public Converter<Price, PriceData> getAmazonPriceConverter() {
		return amazonPriceConverter;
	}

	public void setAmazonPriceConverter(
			Converter<Price, PriceData> amazonPriceConverter) {
		this.amazonPriceConverter = amazonPriceConverter;
	}
	
}
