/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.hering.facades.populators;

import de.hybris.platform.commercefacades.user.converters.populator.AddressPopulator;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.core.model.user.AddressModel;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.hering.core.enums.TipoDeEndereco;


/**
 * 
 * @author Antony
 */
public class HeringAddressPopulator extends AddressPopulator
{
	private final Logger LOG = Logger.getLogger(getClass());
	@Override
	public void populate(final AddressModel source, final AddressData target)
	{
		super.populate(source, target);
		if (StringUtils.isNotBlank(source.getPhone1()))
		{
			target.setPhone(source.getPhone1());
		}
		if (StringUtils.isNotBlank(source.getCellphone()))
		{
			target.setCelPhone(source.getCellphone());
		}
		if (StringUtils.isNotBlank(source.getDddPhone()))
		{
			target.setDddPhone(source.getDddPhone());
		}
		if (StringUtils.isNotBlank(source.getDddCellPhone()))
		{
			target.setDddCelPhone(source.getDddCellPhone());
		}
		if (source.getComplemento() != null)
		{
			target.setComplement(source.getComplemento());
		}
		if (source.getPontoDeReferencia() != null)
		{
			target.setReference(source.getPontoDeReferencia());
		}
		if (source.getStreetnumber() != null)
		{
			target.setNumber(source.getStreetnumber());
		}
		if (source.getReceiver() != null)
		{
			target.setReceiver(source.getReceiver());
		}
		if (source.getPostalcode() != null)
		{
			target.setPostalCode(source.getPostalcode());
		}
		if(source.getTipoDeEndereco() != null) {
			target.setType(source.getTipoDeEndereco());
		}
		if(source.getDistrict() != null)
		{
			target.setDistrict(source.getDistrict());
		}			
	}
}
