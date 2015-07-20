/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.hering.facades.populators;

import de.hybris.platform.commercefacades.user.converters.populator.AddressReversePopulator;
import de.hybris.platform.commercefacades.user.converters.populator.CustomerReversePopulator;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.flieger.payment.data.HeringDebitPaymentInfoData;
import com.flieger.payment.model.HeringDebitPaymentInfoModel;

/**
 * 
 * @author flieger
 */
public class DebitPaymentInfoReversePopulator implements Populator<HeringDebitPaymentInfoData, HeringDebitPaymentInfoModel>
{
	private AddressReversePopulator addressReversePopulator;

	private ModelService modelService;
	
	private CustomerReversePopulator customerReversePopulator;
   
	@Override
	public void populate(HeringDebitPaymentInfoData source, HeringDebitPaymentInfoModel target) throws ConversionException
	{	
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		final AddressData billingAddressData = source.getBillingAddress();
		AddressModel billingAddressModel = target.getBillingAddress();
		if (billingAddressData != null)
		{
			if (billingAddressModel == null)
			{
				billingAddressModel = (AddressModel) getModelService().create(AddressModel.class);
			}
			addressReversePopulator.populate(billingAddressData, billingAddressModel);
			billingAddressModel.setOwner(target);
			target.setBillingAddress(billingAddressModel);
		}
		
		final CustomerData customerData = source.getCustomerData();
		CustomerModel customerModel = (CustomerModel)target.getUser();
		if (customerData != null)
		{
			if(customerModel == null)
			{
				customerModel = getModelService().create(CustomerModel.class);
			}
			customerReversePopulator.populate(customerData, customerModel);
			target.setUser(customerModel);
		}
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
			target.setCurrency(source.getCurrency().getCurrencyCode());
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
	public void setAddressReversePopulator(final AddressReversePopulator addressReversePopulator)
	{
		this.addressReversePopulator = addressReversePopulator;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService the modelService to set
	 */
	@Required
	public void setModelService(ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the customerReversePopulator
	 */
	public CustomerReversePopulator getCustomerReversePopulator()
	{
		return customerReversePopulator;
	}

	/**
	 * @param customerReversePopulator the customerReversePopulator to set
	 */
	@Required
	public void setCustomerReversePopulator(CustomerReversePopulator customerReversePopulator)
	{
		this.customerReversePopulator = customerReversePopulator;
	}
}
