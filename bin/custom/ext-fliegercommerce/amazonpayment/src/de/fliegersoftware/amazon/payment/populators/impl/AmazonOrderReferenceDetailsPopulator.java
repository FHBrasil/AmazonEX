/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fliegersoftware.amazon.payment.populators.impl;


import org.springframework.util.Assert;

import com.amazonservices.mws.offamazonpayments.model.Address;
import com.amazonservices.mws.offamazonpayments.model.OrderReferenceDetails;

import de.fliegersoftware.amazon.core.data.AmazonOrderReferenceDetailsData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

/**
 *
 * @author taylor.savegnago
 */
public class AmazonOrderReferenceDetailsPopulator implements Populator<OrderReferenceDetails, AmazonOrderReferenceDetailsData>
{
   private Converter<Address, AddressData> amazonAddressConverter;
   
	@Override
	public void populate(OrderReferenceDetails source, AmazonOrderReferenceDetailsData target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");
		
		target.setAmazonOrderReferenceId(source.getAmazonOrderReferenceId());
		
		if (source.getOrderReferenceStatus() != null) {
			target.setAmazonOrderReferenceStatus(source.getOrderReferenceStatus().getState());
		}

		if (source.getSellerOrderAttributes() != null) {
			target.setSellerOrderId(source.getSellerOrderAttributes().getSellerOrderId());
		}
		
		if (source.getDestination() != null)
		{
			Address address = source.getDestination().getPhysicalDestination();
			target.setAddressData(amazonAddressConverter.convert(address));
		}
   }

	public Converter<Address, AddressData> getAmazonAddressConverter() {
		return amazonAddressConverter;
	}

	public void setAmazonAddressConverter(Converter<Address, AddressData> amazonAddressConverter) {
		this.amazonAddressConverter = amazonAddressConverter;
	}

	
}
