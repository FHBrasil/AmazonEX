/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fliegersoftware.amazon.payment.populators.impl;


import org.springframework.util.Assert;

import com.amazonservices.mws.offamazonpayments.model.RefundDetails;
import com.amazonservices.mws.offamazonpayments.model.Price;

import de.fliegersoftware.amazon.core.data.AmazonRefundDetailsData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

/**
 *
 * @author taylor.savegnago
 */
public class AmazonRefundDetailsPopulator implements Populator<RefundDetails, AmazonRefundDetailsData>
{
   private Converter<Price, PriceData> amazonPriceConverter;
   
	@Override
	public void populate(RefundDetails source, AmazonRefundDetailsData target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");
		
		target.setAmazonRefundId(source.getAmazonRefundId());
		target.setRefundReferenceId(source.getRefundReferenceId());
		
		if (source.getRefundAmount() != null)
		{
			Price priceRefundAmount = source.getRefundAmount();
			target.setRefundAmount(amazonPriceConverter.convert(priceRefundAmount));
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
