/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fliegersoftware.amazon.payment.populators.impl;


import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.amazonservices.mws.offamazonpayments.model.Price;

import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 *
 * @author taylor.savegnago
 */
public class AmazonPricePopulator implements Populator<Price, PriceData>
{
   
	@Override
	public void populate(Price source, PriceData target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");
		
		target.setCurrencyIso(source.getCurrencyCode());
		if (StringUtils.isNotEmpty(source.getAmount())) {
			target.setValue(BigDecimal.valueOf(Double.parseDouble(source.getAmount())));
		}
   }
}
