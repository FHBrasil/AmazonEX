/**
 * 
 */
package com.adyen.services.payment;

import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.PaymentService;
import de.hybris.platform.payment.dto.NewSubscription;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;

import java.math.BigDecimal;
import java.util.Currency;

import com.flieger.payment.model.HeringDebitPaymentInfoModel;

/**
 * @author flieger 
 */
//TODO esta service está funcionando como uma facade
public interface AdyenPaymentService 
extends PaymentService
{
	public static final String DEFAULT_STORE_UID = "dzarm";
	
	PaymentTransactionEntryModel authorizeDebit(	final HeringDebitPaymentInfoModel debitPaymentInfoModel,
			final String paymentProvider, final String merchantAccount);
	
	PaymentTransactionEntryModel authorizeDebitPayment3D(
			final HeringDebitPaymentInfoModel debitPaymentInfoModel);
	
	NewSubscription createSubscription(	final String merchantTransactionCode, 
													final String paymentProvider, 
													final Currency currency,
													final AddressModel paymentAddress, 
													final HeringDebitPaymentInfoModel model) throws AdapterException;
	PaymentTransactionEntryModel authorize(final String merchantTransactionCode, 
			final BigDecimal amount, 
			final Currency currency,
			final AddressModel deliveryAddress, 
			final String subscriptionID, 
			final String paymentProvider,
			final String merchantAccount) throws AdapterException;
	
	PaymentTransactionEntryModel authorize(	final String merchantTransactionCode, 
															final BigDecimal paramBigDecimal,
															final Currency paramCurrency, 
															final AddressModel paramAddressModel, 
															final String paymentProvider,
															final String merchantAccount);
	
}