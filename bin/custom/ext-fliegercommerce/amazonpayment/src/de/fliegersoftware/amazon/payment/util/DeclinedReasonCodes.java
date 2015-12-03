/**
 * 
 */
package de.fliegersoftware.amazon.payment.util;

/**
 * @author douglas.canalli
 * 
 */
public enum DeclinedReasonCodes {

	/**
	 * There were problems with the payment method. You should contact your buyer and have them update their payment
	 * method using the Amazon Payments web site.
	 */
	INVALIDPAYMENTMETHOD,
	/**
	 * Amazon has rejected the authorization. You should only retry the authorization if the order reference is in the
	 * Open state.
	 */
	AMAZONREJECTED,
	/**
	 * Amazon could not process the transaction due to an internal processing error. You should only retry the
	 * authorization if the order reference is in the Open state.
	 */
	PROCESSINGFAILURE,
	/**
	 * Amazon could not process the authorization request in the timeout value specified by you in the TransactionTimeout
	 * request parameter or within 24 hours of the request. You should increase the timeout value in the
	 * TransactionTimeout request parameter.
	 */
	TRANSACTIONTIMEDOUT;
	
	public static DeclinedReasonCodes getValue(String reasonCode) {
		return DeclinedReasonCodes.valueOf(reasonCode.toUpperCase());
	}

}
