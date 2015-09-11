/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.hering.facades.populators.order;


import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

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
public class BoletoPaymentInfoPopulator implements Populator<BoletoPaymentInfoModel, BoletoPaymentInfoData>
{

   private Converter<AddressModel, AddressData> addressConverter;

   @Override
   public void populate(BoletoPaymentInfoModel source, BoletoPaymentInfoData target) throws ConversionException
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
				target.setCpf(source.getCpf());
			}
		}        
                //target.setId(source.getPk().toString());
		
		target.setSaved(source.isSaved());
		
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
}
