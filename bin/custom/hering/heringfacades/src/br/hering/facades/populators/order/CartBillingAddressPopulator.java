package br.hering.facades.populators.order;

import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author franthescollymaneira
 *
 */
public class CartBillingAddressPopulator implements Populator<AbstractOrderModel, AbstractOrderData> 
{
	private Converter<AddressModel, AddressData> addressConverter;

	/* (non-Javadoc)
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(AbstractOrderModel source, AbstractOrderData target) throws ConversionException 
	{
		final AddressModel billingAddress = source.getPaymentAddress();
		
		if(billingAddress != null) 
		{
			target.setBillingAddress(addressConverter.convert(billingAddress));
		}
		target.setEstimatedDeliveryDays(new Integer(source.getEstimatedDeliveryDays()));
	}

	@Required
	public void setAddressConverter(Converter<AddressModel, AddressData> addressConverter) {
		this.addressConverter = addressConverter;
	}
}
