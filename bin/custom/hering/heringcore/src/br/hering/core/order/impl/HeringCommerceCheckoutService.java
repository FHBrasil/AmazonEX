/**
 * 
 */
package br.hering.core.order.impl;

import de.hybris.platform.commerceservices.order.CommerceCheckoutService;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;

import com.flieger.payment.model.HeringDebitPaymentInfoModel;

/**
 * @author franthescollymaneira
 */
public interface HeringCommerceCheckoutService extends CommerceCheckoutService
{
	boolean setBillingAddress(CartModel cartModel, AddressModel addressModel);
	
	void setPaymentMode(CartModel cartModel, PaymentModeModel paymentModeModel);
	
	PaymentTransactionEntryModel authorizeDebitPayment(final CartModel cartModel,
			final String paymentProvider);
	
	PaymentTransactionEntryModel authorizeDebitPayment3D(
			final HeringDebitPaymentInfoModel debitPaymentInfoModel);
	
	OrderStatus getOrderStatusByCode(final String code);
}