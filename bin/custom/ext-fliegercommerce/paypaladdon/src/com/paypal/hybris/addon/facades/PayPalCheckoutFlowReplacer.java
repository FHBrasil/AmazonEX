/**
 *
 */
package com.paypal.hybris.addon.facades;

import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.support.MethodReplacer;

import com.paypal.hybris.model.PaypalPaymentInfoModel;



/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public class PayPalCheckoutFlowReplacer implements MethodReplacer
{

	private Converter<PaypalPaymentInfoModel, CCPaymentInfoData> paypalPaymentInfoConverter;

	/**
	 * @return the paypalPaymentInfoConverter
	 */
	public Converter<PaypalPaymentInfoModel, CCPaymentInfoData> getPaypalPaymentInfoConverter()
	{
		return paypalPaymentInfoConverter;
	}

	/**
	 * @param paypalPaymentInfoConverter
	 *           the paypalPaymentInfoConverter to set
	 */
	public void setPaypalPaymentInfoConverter(final Converter<PaypalPaymentInfoModel, CCPaymentInfoData> paypalPaymentInfoConverter)
	{
		this.paypalPaymentInfoConverter = paypalPaymentInfoConverter;
	}

	private Converter<CreditCardPaymentInfoModel, CCPaymentInfoData> creditCardPaymentInfoConverter;

	protected Converter<CreditCardPaymentInfoModel, CCPaymentInfoData> getCreditCardPaymentInfoConverter()
	{
		return creditCardPaymentInfoConverter;
	}

	@Required
	public void setCreditCardPaymentInfoConverter(
			final Converter<CreditCardPaymentInfoModel, CCPaymentInfoData> creditCardPaymentInfoConverter)
	{
		this.creditCardPaymentInfoConverter = creditCardPaymentInfoConverter;
	}

	private CartService cartService;

	protected <T extends CartService> T getCartService()
	{
		return (T) cartService;
	}

	@Required
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}


	protected CartModel getCart()
	{
		if (getCartService().hasSessionCart())
		{
			return getCartService().getSessionCart();
		}

		return null;
	}

	/*
	 * Replace
	 *
	 * @see org.springframework.beans.factory.support.MethodReplacer#reimplement(java.lang.Object,
	 * java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object reimplement(final Object arg0, final Method arg1, final Object[] arg2) throws Throwable
	{

		final CartModel cart = getCart();
		if (cart != null)
		{
			final PaymentInfoModel paymentInfo = cart.getPaymentInfo();
			if (paymentInfo instanceof PaypalPaymentInfoModel)
			{
				return getPaypalPaymentInfoConverter().convert((PaypalPaymentInfoModel) paymentInfo);
			}
			else if (paymentInfo instanceof CreditCardPaymentInfoModel)
			{
				return getCreditCardPaymentInfoConverter().convert((CreditCardPaymentInfoModel) paymentInfo);
			}
		}

		return null;
	}

}
