/**
 *
 */
package com.paypal.hybris.converters.populators.impl;

import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import org.springframework.beans.factory.annotation.Required;

import br.hering.core.model.order.payment.PaypalPaymentInfoModel;



/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public class PaypalPaymentInfoPopulator implements Populator<PaypalPaymentInfoModel, CCPaymentInfoData>
{
	private Converter<AddressModel, AddressData> addressConverter;

	protected Converter<AddressModel, AddressData> getAddressConverter()
	{
		return addressConverter;
	}

	@Required
	public void setAddressConverter(final Converter<AddressModel, AddressData> addressConverter)
	{
		this.addressConverter = addressConverter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final PaypalPaymentInfoModel source, final CCPaymentInfoData target) throws ConversionException
	{
		target.setId(source.getPk().toString());
		target.setCardType("PAYPAL");

		target.setSubscriptionId(source.getPayer());
		target.setSaved(source.isSaved());

		if (source.getBillingAddress() != null)
		{
			target.setBillingAddress(getAddressConverter().convert(source.getBillingAddress()));
		}
	}
}
