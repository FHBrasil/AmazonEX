/**
 * 
 */
package de.fliegersoftware.amazon.payment.services;

import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.PaymentService;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author taylor.savegnago 
 */
public interface AmazonPaymentService extends PaymentService
{
	PaymentTransactionEntryModel authorize(final String merchantTransactionCode, 
			final BigDecimal amount, 
			final Currency currency,
			final String paymentProvider,
			final String merchantAccount) throws AdapterException;
}