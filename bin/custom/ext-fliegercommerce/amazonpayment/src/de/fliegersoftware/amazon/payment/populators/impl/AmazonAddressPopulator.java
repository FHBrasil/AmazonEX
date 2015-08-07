/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fliegersoftware.amazon.payment.populators.impl;


import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.amazonservices.mws.offamazonpayments.model.Address;

import de.hybris.platform.commercefacades.i18n.I18NFacade;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 *
 * @author taylor.savegnago
 */
public class AmazonAddressPopulator implements Populator<Address, AddressData>
{
	@Resource(name = "i18NFacade")
	private I18NFacade i18NFacade;

	@Override
	public void populate(Address source, AddressData target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");
		
		String line1 = source.getAddressLine1();
		String line2 = source.getAddressLine2();
		String line3 = source.getAddressLine3();
		boolean l2Empty = StringUtils.isEmpty(line2);
		boolean l3Empty = StringUtils.isEmpty(line3);
		
		target.setLine1(line1);
		target.setLine2(
				(l2Empty ? "" : line2)
				+ (l2Empty || l3Empty ? "" : " ")
				+ (l3Empty ? "" : line3));
		target.setFirstName(source.getName());
		target.setTown(source.getCity());
		target.setDistrict(source.getDistrict());
		target.setCompanyName(source.getName());
		
		//RegionData region = new RegionData();
		//region.setName(source.getStateOrRegion());
		target.setCountry(getI18NFacade().getCountryForIsocode(source.getCountryCode()));
		//target.setRegion(region);
		target.setPostalCode(source.getPostalCode());
		
		CountryData country = new CountryData();
		country.setIsocode(source.getCountryCode());
		target.setCountry(country);
		target.setPhone(source.getPhone());
		
	}

	protected I18NFacade getI18NFacade() {
		return i18NFacade;
	}
}
