/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fliegersoftware.amazon.payment.populators.impl;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.util.Assert;

import com.amazonservices.mws.offamazonpayments.model.Address;

import de.hybris.platform.commercefacades.i18n.I18NFacade;
import de.hybris.platform.commercefacades.user.data.AddressData;
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
		
		final StringBuilder streetFull = new StringBuilder();
		if(source.getAddressLine1() != null && !source.getAddressLine1().isEmpty())
		{
			streetFull.append(source.getAddressLine1()).append(" ");
		}
		if(source.getAddressLine2() != null && !source.getAddressLine2().isEmpty())
		{
			streetFull.append(source.getAddressLine2()).append(" ");
		}
		if(source.getAddressLine3() != null && !source.getAddressLine3().isEmpty())
		{
			streetFull.append(source.getAddressLine3()).append(" ");
		}
		
		final String patternStr = "[0-9]+";
	    final Pattern pattern = Pattern.compile(patternStr);
	    final String streetFullStr = streetFull.toString();
	    
	    if(streetFullStr != null && !streetFullStr.toString().isEmpty())
	    {
	    	final Matcher matcher = pattern.matcher(streetFullStr);
		    if(matcher.find())
		    {
		    	final int index = matcher.start();
		    	target.setLine1(streetFullStr.substring(0, index));
		    	target.setLine2(streetFullStr.substring(index, matcher.end()));
		    }
	    }
	    
		final String nameFull = source.getName();
		if(nameFull != null && !nameFull.isEmpty())
		{
			target.setFirstName(nameFull.substring(0, nameFull.indexOf(' ')));
			target.setLastName(nameFull.substring(nameFull.indexOf(' ') + 1));
		}
		
		target.setTown(source.getCity());
//		target.setDistrict(source.getDistrict());
		target.setCompanyName(source.getName());
		
		//RegionData region = new RegionData();
		//region.setName(source.getStateOrRegion());
		target.setCountry(getI18NFacade().getCountryForIsocode(source.getCountryCode()));
		//target.setRegion(region);
		target.setPostalCode(source.getPostalCode());
		target.setPhone(source.getPhone());
	}

	protected I18NFacade getI18NFacade() {
		return i18NFacade;
	}
}
