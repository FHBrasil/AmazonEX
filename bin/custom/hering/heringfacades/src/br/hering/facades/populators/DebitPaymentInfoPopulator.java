/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.hering.facades.populators;


import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Currency;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.flieger.payment.data.HeringDebitPaymentInfoData;
import com.flieger.payment.model.HeringDebitPaymentInfoModel;

/**
 *
 * @author flieger
 */
public class DebitPaymentInfoPopulator implements Populator<HeringDebitPaymentInfoModel, HeringDebitPaymentInfoData>
{
   private Converter<AddressModel, AddressData> addressConverter;
   
   private Converter<CustomerModel, CustomerData> customerConverter;
   
	@Override
	public void populate(HeringDebitPaymentInfoModel source, HeringDebitPaymentInfoData target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");
		
		if (source.getBillingAddress() != null)
		{
			target.setBillingAddress(addressConverter.convert(source.getBillingAddress()));
		}
		
		if (source.getUser() != null)
		{
			target.setCustomerData(getCustomerConverter().convert((CustomerModel)source.getUser()));
		}

		target.setPK(source.getPk().toString());
		target.setSubscriptionId(source.getSubscriptionId());
		target.setAccountNumber(source.getAccountNumber());
		target.setBaOwner(source.getBaOwner());
		target.setBank(source.getBank());
		target.setBankIDNumber(source.getBankIDNumber());
		target.setCode(source.getCode());
		target.setIssuerUrl(source.getIssuerUrl());
		target.setReturnUrl(source.getReturnUrl());
		target.setMd(source.getMd());
		target.setPaRequest(source.getPaRequest());
		target.setMerchantTransactionCode(source.getMerchantTransactionCode());
		
		if(source.getCurrency() != null){
			target.setCurrency(Currency.getInstance(source.getCurrency()));	
		}
		target.setAmount(source.getAmount());
		
		target.setCardAccountHolderName(source.getCardAccountHolderName());
		target.setCardCvNumber(source.getCardCvNumber());
		target.setCardExpirationMonth(source.getCardExpirationMonth());
		target.setCardExpirationYear(source.getCardExpirationYear());
		target.setMpiImplementationType(source.getMpiImplementationType());
		target.setShopperIP(source.getShopperIP());
		
		target.setAccept(source.getAccept());
		target.setUserAgent(source.getUserAgent());
   }

   @Required
   public void setAddressConverter(final Converter<AddressModel, AddressData> addressConverter)
   {
      this.addressConverter = addressConverter;
   }

	/**
	 * @return the customerConverter
	 */
	public Converter<CustomerModel, CustomerData> getCustomerConverter()
	{
		return customerConverter;
	}

	/**
	 * @param customerConverter the customerConverter to set
	 */
	@Required
	public void setCustomerConverter(Converter<CustomerModel, CustomerData> customerConverter)
	{
		this.customerConverter = customerConverter;
	}
}
