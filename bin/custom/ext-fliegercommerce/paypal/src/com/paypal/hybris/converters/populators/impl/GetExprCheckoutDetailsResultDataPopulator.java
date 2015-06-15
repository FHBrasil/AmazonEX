package com.paypal.hybris.converters.populators.impl;

import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.RegionData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.ebay.api.AddressType;
import com.ebay.api.GetExpressCheckoutDetailsResponseDetailsType;
import com.ebay.api.GetExpressCheckoutDetailsResponseType;
import com.ebay.api.PayerInfoType;
import com.paypal.hybris.data.GetExpressCheckoutDetailsResultData;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class GetExprCheckoutDetailsResultDataPopulator implements
		Populator<GetExpressCheckoutDetailsResponseType, GetExpressCheckoutDetailsResultData>
{
	/**
	 * Populate the target instance with values from the source instance.
	 *
	 * @param response
	 *           the source object
	 * @param resultData
	 *           the target to fill
	 * @throws de.hybris.platform.servicelayer.dto.converter.ConversionException
	 *            if an error occurs
	 */
	@Override
	public void populate(final GetExpressCheckoutDetailsResponseType response, final GetExpressCheckoutDetailsResultData resultData)
			throws ConversionException
	{
		final GetExpressCheckoutDetailsResponseDetailsType details = response.getGetExpressCheckoutDetailsResponseDetails();

		if (details != null)
		{
			final PayerInfoType payerInfo = details.getPayerInfo();
			if (payerInfo != null)
			{
				resultData.setPayerId(payerInfo.getPayerID());
				resultData.setPayer(payerInfo.getPayer());
				resultData.setPayerFirstName(payerInfo.getPayerName().getFirstName());
				resultData.setPayerLastName(payerInfo.getPayerName().getLastName());
				final AddressType address = details.getBillingAddress();
				if (address != null)
				{
					final AddressData billingAddress = new AddressData();
					final CountryData country = new CountryData();
					country.setName(address.getCountryName());
					country.setIsocode(address.getCountry().value());
					billingAddress.setCountry(country);
					final RegionData region = new RegionData();
					if (StringUtils.isNotBlank(address.getStateOrProvince()))
					{
						region.setIsocode(address.getCountry().value() + "-" + address.getStateOrProvince());
						billingAddress.setRegion(region);
					}
					billingAddress.setId(address.getAddressID());
					billingAddress.setTown(address.getCityName());
					billingAddress.setPhone(address.getPhone());
					billingAddress.setPostalCode(address.getPostalCode());
					billingAddress.setLine1(address.getStreet1());
					billingAddress.setLine2(address.getStreet2());
					billingAddress.setEmail(payerInfo.getPayer());
					billingAddress.setFirstName(payerInfo.getPayerName().getFirstName());
					billingAddress.setLastName(payerInfo.getPayerName().getLastName());
					billingAddress.setBillingAddress(true);
					billingAddress.setShippingAddress(false);
					billingAddress.setVisibleInAddressBook(false);
					resultData.setBillingAddress(billingAddress);
				}
			}

			if (CollectionUtils.isNotEmpty(details.getPaymentDetails()))
			{
				// TODO: get delivery address here
				final AddressType addressType = details.getPaymentDetails().get(0).getShipToAddress();
				if (addressType != null)
				{
					resultData.setAddressName(addressType.getName());
					resultData.setAddressId(addressType.getAddressID());
					resultData.setLine1(addressType.getStreet1());
					resultData.setLine2(addressType.getStreet2());
					resultData.setTown(addressType.getCityName());
					resultData.setPostalCode(addressType.getPostalCode());
					resultData.setStateOrProvince(addressType.getStateOrProvince());
					if (addressType.getCountry() != null)
					{
						resultData.setCountryIsoCode(addressType.getCountry().value());
					}
				}
			}
		}
	}
}
