package br.hering.facades.populators.order;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.flieger.payment.data.AdvancePaymentInfoData;

import de.hybris.platform.commercefacades.user.converters.populator.AddressReversePopulator;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.payment.AdvancePaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class AdvancePaymentInfoReversePopulator implements Populator<AdvancePaymentInfoData, AdvancePaymentInfoModel>
{
	private AddressReversePopulator addressReversePopulator;
	@Override
	public void populate(AdvancePaymentInfoData source, AdvancePaymentInfoModel target) throws ConversionException 
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setCode(target.getUser().getUid() + "_" + UUID.randomUUID());

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
