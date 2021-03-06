/**
 *
 */
package de.fliegersoftware.amazon.payment.populators.impl;

import de.fliegersoftware.amazon.core.model.AmazonPaymentPaymentInfoModel;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author taylor.savegnago
 *
 */
public class AmazonPaymentPaymentInfoPopulator implements Populator<AmazonPaymentPaymentInfoModel, CCPaymentInfoData>
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
	public void populate(final AmazonPaymentPaymentInfoModel source, final CCPaymentInfoData target) throws ConversionException
	{
		target.setId(source.getPk().toString());
		target.setCardType("AMAZON");

		target.setSubscriptionId(source.getAmazonOrderReferenceId());
		target.setSaved(source.isSaved());

		if (source.getBillingAddress() != null)
		{
			target.setBillingAddress(getAddressConverter().convert(source.getBillingAddress()));
		}
	}
}
