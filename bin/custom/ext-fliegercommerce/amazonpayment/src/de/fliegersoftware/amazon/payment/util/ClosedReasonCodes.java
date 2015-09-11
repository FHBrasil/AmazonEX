/**
 * 
 */
package de.fliegersoftware.amazon.payment.util;

/**
 * @author douglas.canalli
 *
 */
public enum ClosedReasonCodes {
	
	/** 
	 * The authorization has been in the Open state for 30 days (two days for Sandbox) 
	 * and you did not submit any captures against it */
	EXPIREDUNUSED,
	/** 
	 * You have already captured the full amount of the authorization 
	 */
	MAXCAPTURESPROCESSED,
	/**
	 * Amazon has closed the authorization objetct due to problems with your account
	 */
	AMAZONCLOSED,
	/**
	 * The order reference was canceled causing all open authorizations to be canceled. 
	 * You can call GetOrderReferenceDetails operation an check the ReasonCode response 
	 * element for the cancelation reason.
	 */
	ORDERREFERENCECANCELED,
	/**
	 * You have explicitly closed the authorization using the CloseAuthorization operation. 
	 * You can specify the reason for the closure in the ClosureReason request parameter
	 */
	SELLERCLOSED,
	/**
	 * Amazon has rejected the authorization. You should only retry the authorization if the order reference is in the
	 * Open state.
	 */
	AMAZONREJECTED,
	/**
	 * There were problems with the payment method. You should contact your buyer and have them update their payment
	 * method using the Amazon Payments web site.
	 */
	INVALIDPAYMENTMETHOD;
	public static ClosedReasonCodes getValue(String reasonCode) {
		return ClosedReasonCodes.valueOf(reasonCode.toUpperCase());
	}
	
}

