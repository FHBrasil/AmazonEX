/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.hering.facades.populators.order;

import de.hybris.platform.commercefacades.user.converters.populator.AddressReversePopulator;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.flieger.payment.data.BoletoPaymentInfoData;
import com.flieger.payment.model.BoletoPaymentInfoModel;

import de.hybris.platform.util.Config;

/**
 * 
 * @author flieger
 */
public class BoletoPaymentInfoReversePopulator implements Populator<BoletoPaymentInfoData, BoletoPaymentInfoModel>
{

	private AddressReversePopulator addressReversePopulator;

	@Override
	public void populate(BoletoPaymentInfoData source, BoletoPaymentInfoModel target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		if (StringUtils.isNotBlank(source.getDaysToAddInBoletoExpirationDate()))
		{
			target.setDaysToAddInBoletoExpirationDate(source.getDaysToAddInBoletoExpirationDate());
		}
		if (StringUtils.isNotBlank(source.getNossoNumero()))
		{
			target.setNossoNumero(source.getNossoNumero());
		}
		if (StringUtils.isNotBlank(source.getTransactionReference()))
		{
			target.setTransactionReference(source.getTransactionReference());
		}
		
		if (Config.getBoolean("fliegercommerce.feature.enable.cpf", false))
		{
		if (StringUtils.isNotBlank(source.getCpf()))
			{
				target.setTransactionReference(source.getCpf());
			}
		}
		
		target.setCode(target.getUser().getUid() + "_" + UUID.randomUUID());
		//target.setId(source.getPk().toString());

		target.setSaved(source.getSaved().booleanValue());

		final AddressData billingAddressData = source.getBillingAddress();
		AddressModel billingAddressModel = target.getBillingAddress();
		if (billingAddressData != null)
		{
			if (billingAddressModel == null)
			{
				billingAddressModel = new AddressModel();
			}

			addressReversePopulator.populate(billingAddressData, billingAddressModel);
			billingAddressModel.setOwner(target);
			target.setBillingAddress(billingAddressModel);
		}
	}

	@Required
	public void setAddressReversePopulator(final AddressReversePopulator addressReversePopulator)
	{
		this.addressReversePopulator = addressReversePopulator;
	}
}
