/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.hering.facades.populators;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hybris.platform.commercefacades.user.converters.populator.CustomerReversePopulator;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.util.Config;

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
		
		if (Config.getBoolean("fliegercommerce.feature.enable.cpf", false))
		{
			target.setCpfcnpj(source.getCpfcnpj());
		}
		
		target.setRgIe(source.getRgIe());
		target.setUfIe(source.getUfIe());

		target.setDefaultPhoneNumber(source.getDefaultPhoneNumber());
		
		String date = source.getYoungestChildDateOfBirth();	 
		try 
		{
			DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy"); 
			Date dateOfBirth = formatter.parse(date);
			target.setYoungestChildDateOfBirth(dateOfBirth);
		} 
		catch (ParseException e) 
		{
			//e.printStackTrace();
		}
		

	}
}
