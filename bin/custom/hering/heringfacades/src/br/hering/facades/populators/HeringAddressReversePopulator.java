/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.hering.facades.populators;

import de.hybris.platform.commercefacades.user.converters.populator.AddressReversePopulator;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.apache.log4j.Logger;

/**
 * 
 * @author Antony
 */
public class HeringAddressReversePopulator extends AddressReversePopulator
{
	private static final Logger LOG = Logger.getLogger(HeringAddressReversePopulator.class);

	@Override
	public void populate(final AddressData addressData, final AddressModel addressModel) throws ConversionException
	{
		super.populate(addressData, addressModel);
		addressModel.setComplemento(addressData.getComplement());
		addressModel.setPontoDeReferencia(addressData.getReference());
		addressModel.setTipoDeEndereco(addressData.getType());
		addressModel.setPhone1(addressData.getPhone());
		addressModel.setStreetnumber(addressData.getNumber());
		addressModel.setPostalcode(addressData.getPostalCode());
		addressModel.setReceiver(addressData.getReceiver());
		addressModel.setCellphone(addressData.getCelPhone());
		addressModel.setDddCellPhone(addressData.getDddCelPhone());
		addressModel.setDddPhone(addressData.getDddPhone());
		addressModel.setDistrict(addressData.getDistrict());
	}

}
