/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.hering.facades.populators;

import de.hybris.platform.commercefacades.user.converters.populator.CustomerPopulator;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import de.hybris.platform.util.Config;


/**
 *
 * @author Vinicius de Souza
 */
public class HeringCustomerPopulator extends CustomerPopulator
{
	

	@Override
	public void populate(final CustomerModel source, final CustomerData target)
	{

		super.populate(source, target);

		target.setGender(source.getGender());
		target.setBirthday(source.getBirthday());
		
		if (Config.getBoolean("fliegercommerce.feature.enable.cpf", false))
		{	
			target.setCpfcnpj(source.getCpfcnpj());
		}
		
		target.setRgIe(source.getRgIe());
		target.setUfIe(source.getUfIe());
		target.setPrimaryKey(source.getPk().getLongValueAsString());
		
		target.setDefaultPhoneNumber(source.getDefaultPhoneNumber());

		
		List<String> orders = new LinkedList<String>();
		
		for (OrderModel model : source.getOrders())
		{
			orders.add(model.getCode());
		}
		//Garante a ordenação
		Collections.sort(orders, Collections.reverseOrder());
		target.setOrders(orders);
	}
}
