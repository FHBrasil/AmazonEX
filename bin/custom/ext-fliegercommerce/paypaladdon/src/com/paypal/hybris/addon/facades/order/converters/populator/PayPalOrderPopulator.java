package com.paypal.hybris.addon.facades.order.converters.populator;

import de.hybris.platform.commercefacades.order.converters.populator.OrderPopulator;
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import com.paypal.hybris.model.PaypalPaymentInfoModel;


/**
 * @author Dzmitry_Rasolka (EPAM Systems)
 */
public class PayPalOrderPopulator extends OrderPopulator
{

	private Converter<PaypalPaymentInfoModel, CCPaymentInfoData> paypalPaymentInfoConverter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.commercefacades.order.converters.populator.AbstractOrderPopulator#addPaymentInformation(de.
	 * hybris.platform.core.model.order.AbstractOrderModel,
	 * de.hybris.platform.commercefacades.order.data.AbstractOrderData)
	 */
	@Override
	protected void addPaymentInformation(final AbstractOrderModel source, final AbstractOrderData prototype)
	{
		final PaymentInfoModel paymentInfo = source.getPaymentInfo();
		if (paymentInfo instanceof CreditCardPaymentInfoModel)
		{
			final CCPaymentInfoData paymentInfoData = getCreditCardPaymentInfoConverter().convert(
					(CreditCardPaymentInfoModel) paymentInfo);
			prototype.setPaymentInfo(paymentInfoData);
		}
		else if (paymentInfo instanceof PaypalPaymentInfoModel)
		{
			final CCPaymentInfoData paymentInfoData = getPaypalPaymentInfoConverter().convert((PaypalPaymentInfoModel) paymentInfo);
			prototype.setPaymentInfo(paymentInfoData);
		}
	}

	public Converter<PaypalPaymentInfoModel, CCPaymentInfoData> getPaypalPaymentInfoConverter()
	{
		return paypalPaymentInfoConverter;
	}

	public void setPaypalPaymentInfoConverter(final Converter<PaypalPaymentInfoModel, CCPaymentInfoData> paypalPaymentInfoConverter)
	{
		this.paypalPaymentInfoConverter = paypalPaymentInfoConverter;
	}

}
