package com.pixi.webservices.converters.populators.order.address;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.PredicateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.pixi.webservices.jaxb.order.export.Address;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiAddressPopulator implements Populator<AddressModel, Address> 
{
	private Logger LOG = Logger.getLogger(DefaultPixiAddressPopulator.class);
	
	@Override
	public void populate(AddressModel source, Address target) throws ConversionException 
	{
		LOG.info("populating");
		
		target.setCHARGEVAT("yes");
		target.setVATID("19%");
		target.setCITY(source.getTown());
		target.setCOUNTRY(source.getCountry().getName());
		target.setEMAIL(source.getEmail());
		target.setFAX(source.getFax());
		target.setNAME(source.getFirstname());
		target.setNAME2(getFullName(source));
		target.setNAME3(source.getLastname());
		target.setPHONE(source.getPhone1());
		target.setADDRESSREMARKS(source.getAppartment());
		target.setSAL(source.getTitle().getName());
		target.setSTREET(getStreetAddress(source));
		target.setZIP(source.getPostalcode());
	}
	
	private String getStreetAddress(AddressModel address) 
	{
		String name = StringUtils.defaultIfBlank(address.getStreetname(), "").trim();
		String number = StringUtils.defaultIfBlank(address.getStreetnumber(), "").trim();
		
		StringBuilder streetAddress = new StringBuilder();
		streetAddress.append(name);
		streetAddress.append(" ");
		streetAddress.append(number);
		
		return streetAddress.toString().trim();
	}

	private String getFullName(AddressModel address) 
	{
		List<String> list = new ArrayList<String>(Arrays.asList(
				StringUtils.defaultIfBlank(address.getFirstname(), null),
				StringUtils.defaultIfBlank(address.getMiddlename(), null),
				StringUtils.defaultIfBlank(address.getMiddlename2(), null)
		));
		
		CollectionUtils.filter(list, PredicateUtils.notNullPredicate());
		
		return StringUtils.join(list, " ");
	}
}
