package de.fliegersoftware.amazon.payment.services;

import de.hybris.platform.commerceservices.order.CommerceCheckoutService;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;

import java.math.BigDecimal;

/**
 * @author taylor.savegnago
 *
 */
public interface AmazonCommerceCheckoutService extends CommerceCheckoutService
{
	
	PaymentTransactionEntryModel authorizeAmazonPayment(final CartModel cartModel, 
			final String paymentProvider);
	
	PaymentTransactionEntryModel authorizeAmazonPaymentAmount(final CartModel cartModel,
			final String paymentProvider,
			BigDecimal amount);
}
