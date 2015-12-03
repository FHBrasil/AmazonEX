/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *  
 */
package de.fliegersoftware.amazon.payment.populators.impl;

import org.springframework.util.Assert;

import com.amazonservices.mws.offamazonpayments.model.OrderReferenceAttributes;
import com.amazonservices.mws.offamazonpayments.model.OrderTotal;
import com.amazonservices.mws.offamazonpayments.model.SellerOrderAttributes;

import de.fliegersoftware.amazon.core.data.AmazonOrderReferenceAttributesData;
import de.fliegersoftware.amazon.core.data.AmazonSellerOrderAttributesData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;


/**
 * @author taylor.savegnago
 * 
 */
public class AmazonOrderReferenceAttributesReversePopulator implements Populator<AmazonOrderReferenceAttributesData, OrderReferenceAttributes>
{
	private Converter<PriceData, OrderTotal> amazonOrderTotalReverseConverter;
	
	private Converter<AmazonSellerOrderAttributesData, SellerOrderAttributes> amazonSellerOrderAttributesReverseConverter;

	@Override
	public void populate(final AmazonOrderReferenceAttributesData source, final OrderReferenceAttributes target)
			throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");
		
		target.setPlatformId(source.getPlatformId());
		target.setSellerNote(source.getSellerNote());
		
		if (source.getOrderTotal() != null) {
			target.setOrderTotal(amazonOrderTotalReverseConverter.convert(source.getOrderTotal()));
		}
		
		if (source.getSellerOrderAttributes() != null) {
			target.setSellerOrderAttributes(amazonSellerOrderAttributesReverseConverter.convert(source.getSellerOrderAttributes()));
		}
	}

	public Converter<PriceData, OrderTotal> getAmazonOrderTotalReverseConverter() {
		return amazonOrderTotalReverseConverter;
	}

	public void setAmazonOrderTotalReverseConverter(Converter<PriceData, OrderTotal> amazonOrderTotalReverseConverter) {
		this.amazonOrderTotalReverseConverter = amazonOrderTotalReverseConverter;
	}

	public Converter<AmazonSellerOrderAttributesData, SellerOrderAttributes> getAmazonSellerOrderAttributesReverseConverter() {
		return amazonSellerOrderAttributesReverseConverter;
	}

	public void setAmazonSellerOrderAttributesReverseConverter(
			Converter<AmazonSellerOrderAttributesData, SellerOrderAttributes> amazonSellerOrderAttributesReverseConverter) {
		this.amazonSellerOrderAttributesReverseConverter = amazonSellerOrderAttributesReverseConverter;
	}
	
}
