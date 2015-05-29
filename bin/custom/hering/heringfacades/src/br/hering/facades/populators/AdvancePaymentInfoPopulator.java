package br.hering.facades.populators;

import java.util.Currency;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.flieger.payment.data.AdvancePaymentInfoData;

import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.payment.AdvancePaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class AdvancePaymentInfoPopulator implements Populator<AdvancePaymentInfoModel, AdvancePaymentInfoData>
{
   private Converter<AddressModel, AddressData> addressConverter;
   
   private Converter<CustomerModel, CustomerData> customerConverter;
   
	@Override
	public void populate(AdvancePaymentInfoModel source, AdvancePaymentInfoData target)
			throws ConversionException 
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");
		
		if (source.getBillingAddress() != null)
		{
			target.setBillingAddress(getAddressConverter().convert(source.getBillingAddress()));
		}
		
		if (source.getUser() != null)
		{
			target.setCustomerData(getCustomerConverter().convert((CustomerModel)source.getUser()));
		}
		
		target.setPK(source.getPk().toString());
		target.setCode(source.getCode());
	}

	public Converter<AddressModel, AddressData> getAddressConverter() {
		return addressConverter;
	}

	@Required
	public void setAddressConverter(Converter<AddressModel, AddressData> addressConverter) {
		this.addressConverter = addressConverter;
	}

	public Converter<CustomerModel, CustomerData> getCustomerConverter() {
		return customerConverter;
	}

	@Required
	public void setCustomerConverter(Converter<CustomerModel, CustomerData> customerConverter) {
		this.customerConverter = customerConverter;
	}
}