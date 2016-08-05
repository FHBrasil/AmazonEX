/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fliegersoftware.amazon.payment.populators.impl;


import org.springframework.util.Assert;

import com.amazonservices.mws.offamazonpayments.model.Address;
import com.amazonservices.mws.offamazonpayments.model.AuthorizationDetails;
import com.amazonservices.mws.offamazonpayments.model.Price;

import de.fliegersoftware.amazon.core.data.AmazonAuthorizationDetailsData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

/**
 *
 * @author taylor.savegnago
 */
public class AmazonAuthorizationDetailsPopulator implements Populator<AuthorizationDetails, AmazonAuthorizationDetailsData>
{
	private Converter<Price, PriceData> amazonPriceConverter;
	private Converter<Address, AddressData> amazonAddressConverter;

	@Override
	public void populate(AuthorizationDetails source, AmazonAuthorizationDetailsData target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");
		
		target.setAmazonAuthorizationId(source.getAmazonAuthorizationId());
		target.setAuthorizationReferenceId(source.getAuthorizationReferenceId());
		
		if (source.getAuthorizationAmount() != null)
		{
			Price priceAuthorizationAmount = source.getAuthorizationAmount();
			target.setAuthorizationAmount(amazonPriceConverter.convert(priceAuthorizationAmount));
		}
		if (source.getAuthorizationBillingAddress() != null)
		{
			Address address = source.getAuthorizationBillingAddress();
			target.setBillingAddress(amazonAddressConverter.convert(address));
		}
   }

	public Converter<Price, PriceData> getAmazonPriceConverter() {
		return amazonPriceConverter;
	}

	public void setAmazonPriceConverter(
			Converter<Price, PriceData> amazonPriceConverter) {
		this.amazonPriceConverter = amazonPriceConverter;
	}

	public Converter<Address, AddressData> getAmazonAddressConverter() {
		return amazonAddressConverter;
	}

	public void setAmazonAddressConverter(Converter<Address, AddressData> amazonAddressConverter) {
		this.amazonAddressConverter = amazonAddressConverter;
	}
}
