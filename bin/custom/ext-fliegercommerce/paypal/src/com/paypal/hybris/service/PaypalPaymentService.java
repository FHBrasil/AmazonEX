package com.paypal.hybris.service;

import com.ebay.api.*;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public interface PaypalPaymentService
{

	/**
	 * Configures PayPal to receive the forthcoming payment.
	 *
	 * @param request
	 *           setExpressCheckout service call request object.
	 */
	SetExpressCheckoutResponseType setExpressCheckout(SetExpressCheckoutRequestType request);


	/**
	 * Gets ExpressCheckout customer details after he/she accepted a payment.
	 *
	 * @param request
	 *           getExpressCheckoutDetails service call request object.
	 */
	GetExpressCheckoutDetailsResponseType getExpressCheckoutDetails(GetExpressCheckoutDetailsRequestType request);


	/**
	 * Executes payment, that was created earlier and bounded to current session.
	 *
	 * @param request
	 *           doExpressCheckoutPayment service call request object
	 */
	DoExpressCheckoutPaymentResponseType doExpressCheckoutPayment(DoExpressCheckoutPaymentRequestType request);

	TransactionSearchResponseType transactionSearch(TransactionSearchRequestType request);


	/**
	 * Executes full transaction refund.
	 *
	 * @param request
	 *           refundTransaction service call request object.
	 */
	RefundTransactionResponseType refundTransaction(RefundTransactionRequestType request);


	DoAuthorizationResponseType doAuthorization(DoAuthorizationRequestType request);


	DoReauthorizationResponseType doReauthorization(DoReauthorizationRequestType request);


	GetTransactionDetailsResponseType getTransactionDetails(GetTransactionDetailsRequestType request);


	DoVoidResponseType doVoid(DoVoidRequestType request);


	DoCaptureResponseType doCapture(DoCaptureRequestType request);

	DoReferenceTransactionResponseType doReferenceTransaction(DoReferenceTransactionRequestType request);

}
