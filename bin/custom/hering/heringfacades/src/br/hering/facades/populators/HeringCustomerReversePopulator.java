/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.hering.facades.populators;

import de.hybris.platform.commercefacades.user.converters.populator.CustomerReversePopulator;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 *
 * @author Vinicius de Souza
 */
public class HeringCustomerReversePopulator extends CustomerReversePopulator
{

	@Override
	public void populate(final CustomerData source, CustomerModel target) throws ConversionException
	{
		super.populate(source, target);
		target.setBirthday(source.getBirthday());
		target.setGender(source.getGender());
		target.setCpfcnpj(source.getCpfcnpj());
		target.setRgIe(source.getRgIe());
		target.setUfIe(source.getUfIe());


	}
}
