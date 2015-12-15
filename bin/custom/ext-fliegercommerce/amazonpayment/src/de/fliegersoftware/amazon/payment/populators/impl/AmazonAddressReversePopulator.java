/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.fliegersoftware.amazon.payment.populators.impl;

import de.hybris.platform.commercefacades.user.converters.populator.AddressReversePopulator;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.apache.log4j.Logger;

/**
 * 
 * @author taylor.savegnago
 */
public class AmazonAddressReversePopulator extends AddressReversePopulator
{
	private static final Logger LOG = Logger.getLogger(AmazonAddressReversePopulator.class);

	@Override
	public void populate(final AddressData addressData, final AddressModel addressModel) throws ConversionException
	{
		super.populate(addressData, addressModel);
		addressModel.setPobox(addressData.getPobox());
	}

}
