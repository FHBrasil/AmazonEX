/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fliegersoftware.amazon.payment.populators.impl;


import org.springframework.util.Assert;

import com.amazonservices.mws.offamazonpayments.model.Price;

import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 *
 * @author taylor.savegnago
 */
public class AmazonPriceReversePopulator implements Populator<PriceData, Price>
{
   
	@Override
	public void populate(PriceData source, Price target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");
		
		target.setCurrencyCode(source.getCurrencyIso());
		if (source.getValue() != null) {
			target.setAmount(String.valueOf(source.getValue()));
		}
   }
}
