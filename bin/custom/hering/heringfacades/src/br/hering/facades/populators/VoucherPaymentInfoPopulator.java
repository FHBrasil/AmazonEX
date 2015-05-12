/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.hering.facades.populators;


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
public class VoucherPaymentInfoPopulator implements Populator<VoucherPaymentInfoModel, VoucherPaymentInfoData>
{

   private Converter<AddressModel, AddressData> addressConverter;
   private Converter<VoucherPaymentInfoModel, VoucherPaymentInfoData> voucherPaymentInfoConverter;
   
   

   @Override
   public void populate(VoucherPaymentInfoModel source, VoucherPaymentInfoData target) throws ConversionException
   {
      Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");
		
		if (source.getBillingAddress() != null)
		{
			target.setBillingAddress(addressConverter.convert(source.getBillingAddress()));
		}
   }
   

   @Required
   public void setAddressConverter(final Converter<AddressModel, AddressData> addressConverter)
   {
      this.addressConverter = addressConverter;
   }


	/**
	 * @return the voucherPaymentInfoConverter
	 */
	public Converter<VoucherPaymentInfoModel, VoucherPaymentInfoData> getVoucherPaymentInfoConverter()
	{
		return voucherPaymentInfoConverter;
	}


	/**
	 * @param voucherPaymentInfoConverter the voucherPaymentInfoConverter to set
	 */
	
	public void setVoucherPaymentInfoConverter(Converter<VoucherPaymentInfoModel, VoucherPaymentInfoData> voucherPaymentInfoConverter)
	{
		this.voucherPaymentInfoConverter = voucherPaymentInfoConverter;
	}
   
}
