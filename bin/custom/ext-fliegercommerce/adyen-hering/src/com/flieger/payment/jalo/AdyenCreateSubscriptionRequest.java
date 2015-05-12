/**
 * 
 */
package com.flieger.payment.jalo;

import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.jalo.order.payment.PaymentInfo;
import de.hybris.platform.payment.dto.BillingInfo;
import de.hybris.platform.payment.dto.CardInfo;

import java.util.Currency;

/**
 * 
 */
public class AdyenCreateSubscriptionRequest extends de.hybris.platform.payment.commands.request.CreateSubscriptionRequest
{
	private PaymentInfoModel paymentInfoModel;
	
	/**
	 * @param merchantTransactionCode
	 * @param billingInfo
	 * @param currency
	 * @param card
	 * @param requestId
	 * @param requestToken
	 * @param paymentProvider
	 */
	public AdyenCreateSubscriptionRequest(	String merchantTransactionCode, 
														BillingInfo billingInfo, 
														Currency currency, 
														CardInfo card,
														String requestId, 
														String requestToken, 
														String paymentProvider, 
														PaymentInfoModel paymentInfoModel)
	{
		super(merchantTransactionCode, billingInfo, currency, card, requestId, requestToken, paymentProvider);
		this.paymentInfoModel = paymentInfoModel;
	}
}
