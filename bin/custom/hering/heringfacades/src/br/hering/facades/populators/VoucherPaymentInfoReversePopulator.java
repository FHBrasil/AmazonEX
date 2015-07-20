/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.hering.facades.populators;

import de.hybris.platform.commercefacades.user.converters.populator.AddressReversePopulator;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import br.hering.core.model.order.payment.VoucherPaymentInfoModel;
import br.hering.facades.order.data.VoucherPaymentInfoData;


/**
 * 
 * @author flieger
 */
public class VoucherPaymentInfoReversePopulator implements Populator<VoucherPaymentInfoData, VoucherPaymentInfoModel>
{

	private AddressReversePopulator addressReversePopulator;
	
	 private Converter<VoucherPaymentInfoData, VoucherPaymentInfoModel> voucherPaymentInfoConverter;

	@Override
	public void populate(VoucherPaymentInfoData source, VoucherPaymentInfoModel target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

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
			target.setVoucher(source.getVoucherCode());
		}
	}

	@Required
	public void setAddressReversePopulator(final AddressReversePopulator addressReversePopulator)
	{
		this.addressReversePopulator = addressReversePopulator;
	}

	/**
	 * @return the voucherPaymentInfoConverter
	 */
	public Converter<VoucherPaymentInfoData, VoucherPaymentInfoModel> getVoucherPaymentInfoConverter()
	{
		return voucherPaymentInfoConverter;
	}

	/**
	 * @param voucherPaymentInfoConverter the voucherPaymentInfoConverter to set
	 */	
	public void setVoucherPaymentInfoConverter(Converter<VoucherPaymentInfoData, VoucherPaymentInfoModel> voucherPaymentInfoConverter)
	{
		this.voucherPaymentInfoConverter = voucherPaymentInfoConverter;
	}

}
