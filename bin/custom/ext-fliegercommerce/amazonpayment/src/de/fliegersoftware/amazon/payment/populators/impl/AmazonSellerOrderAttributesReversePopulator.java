/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fliegersoftware.amazon.payment.populators.impl;


import org.springframework.util.Assert;

import com.amazonservices.mws.offamazonpayments.model.SellerOrderAttributes;

import de.fliegersoftware.amazon.core.data.AmazonSellerOrderAttributesData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 *
 * @author taylor.savegnago
 */
public class AmazonSellerOrderAttributesReversePopulator implements Populator<AmazonSellerOrderAttributesData, SellerOrderAttributes>
{
   
	@Override
	public void populate(AmazonSellerOrderAttributesData source, SellerOrderAttributes target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");
		
		target.setSellerOrderId(source.getSellerOrderId());
		target.setCustomInformation(source.getCustomInformation());
		target.setStoreName(source.getStoreName());
   }
}
