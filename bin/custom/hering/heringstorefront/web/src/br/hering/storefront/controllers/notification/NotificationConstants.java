/**
 * 
 */
package br.hering.storefront.controllers.notification;

/**
 * @author flieger
 *
 */
public class NotificationConstants
{
	public static final String test_address = "https://pal-test.adyen.com/pal/servlet/soap/Payment";
	public static final String live_address = "https://pal-live.adyen.com/pal/servlet/soap/Payment";

	public static final String recurring_test_address = "https://pal-test.adyen.com/pal/servlet/soap/Recurring";
	public static final String recurring_live_address = "https://pal-test.adyen.com/pal/servlet/soap/Recurring";

	public static final String merchant_account = "HeringCOM";
	public static final String ws_user = "ws@Company.Hering";
	public static final String pwd_user = "!T3q<DY#!Nb}1Z^8YNd9D/A]^";
	

	/*
	 * LIVE - boolean (true/false) indicating if the notification originated
	 * from the LIVE or TEST payment systems.
	 */
	public static final String LIVE = "live";
	public static final boolean LIVE_TRUE = true;
	public static final boolean LIVE_FALSE = false;
	/*
	 * The event type of the notification Normal Payment Events: AUTHORISATION.
	 * Modification Payment Events: CANCELLATION. REFUND. CANCEL_OR_REFUND.
	 * CAPTURE. REFUNDED_REVERSED. Please note that the success field in a
	 * REFUNDED_REVERSED notification will always be set to false.
	 * CAPTURE_FAILED. REFUND_FAILED. Dispute Events: REQUEST_FOR_INFORMATION.
	 * NOTIFICATION_OF_CHARGEBACK. ADVICE_OF_DEBIT. CHARGEBACK.
	 * CHARGEBACK_REVERSED. Other Events: REPORT_AVAILABLE. For more information
	 * please refer to the Adyen Reporting Manual.
	 */
	public static final String EVENT_CODE = "eventCode";
	public static final String EVENT_PENDING = "PENDING";
	public static final String EVENT_AUTHORISATION = "AUTHORISATION";
	public static final String EVENT_CANCELLATION = "CANCELLATION";
	public static final String EVENT_REFUND = "REFUND";
	public static final String EVENT_CANCEL_OR_REFUND = "CANCEL_OR_REFUND";
	public static final String EVENT_CAPTURE = "CAPTURE";
	public static final String EVENT_REFUNDED_REVERSED = "REFUNDED_REVERSED";
	public static final String EVENT_CAPTURE_FAILED = "CAPTURE_FAILED";
	public static final String EVENT_REFUND_FAILED = "REFUND_FAILED";
	public static final String EVENT_REQUEST_FOR_INFORMATION = "REQUEST_FOR_INFORMATION";
	public static final String EVENT_NOTIFICATION_OF_CHARGEBACK = "NOTIFICATION_OF_CHARGEBACK";
	public static final String EVENT_ADVICE_OF_DEBIT = "ADVICE_OF_DEBIT";
	public static final String EVENT_CHARGEBACK = "CHARGEBACK";
	public static final String EVENT_CHARGEBACK_REVERSED = "CHARGEBACK_REVERSED";
	
	/*
	 * The unique reference that Adyen assigned to the payment or modification.
	 */
	public static final String PSP_REFERENCE = "pspReference";
	/*
	 * If this is a notification for a modification request this will be the
	 * pspReference that was originally assigned to the authorisation, for a
	 * payment it will be blank.
	 */
	public static final String ORIGINAL_REFERENCE = "originalReference";
	/*
	 * This is the reference you assigned to the original payment.
	 */
	public static final String MERCHANT_REFERENCE = "merchantReference";
	/*
	 * The merchant Account the payment or modi0cation was processed with.
	 */
	public static final String MERCHANTE_ACCOUNT_CODE = "merchantAccountCode";
	/*
	 * The time the event was generated.
	 */
	public static final String EVENT_DATE = "eventDate";
	/*
	 * Whether or not the event succeeded (boolean true/false).
	 */
	public static final String SUCCESS = "success";
	/*
	 * The payment method used, this is only populated for an AUTHORISATION.
	 * e.g. visa, mc, ideal, elv, wallie, etc.
	 */
	public static final String PAYMENT_METHOD = "paymentMethod";
	/*
	 * This field displays the modification operations supported by this payment
	 * as a list of strings, this is only populated for AUTHORISATION
	 * notifications. The operations will inform you whether you need to capture
	 * the payment (if you don't have auto-capture set up), whether you can
	 * cancel the payment (before capture) or if you can refund the payment
	 * (after it has been captured). Values include: CAPTURE, REFUND, CANCEL. 
	 * For HTTP POST notifications, the operations are sent as a single
	 * comma-separated string.
	 */
	public static final String OPERATIONS = "operations";
	public static final String OPERATIONS_CAPTURE = "CAPTURE";
	public static final String OPERATIONS_REFUND = "REFUND";
	public static final String OPERATIONS_CANCEL = "CANCEL";
	/*
	 * Text field with information depending on whether the result is successful
	 * or not. For AUTHORISATION events with the success 0eld set to true and a
	 * payment method of visa, mc or amex this 0eld contains the authorisation
	 * code, the last 4 digits of the card, and the expiry date in the following
	 * format: 
	 * 6 digit Authorisation Code:Last 4 digits:Expiry Date (e.g.
	 * 874574:1935:11/2012). 
	 * When the success 0eld is set to false it gives a
	 * reason as to why it was refused. For REPORT_AVAILABLE it contains the URL
	 * where the report can be downloaded from.
	 */
	public static final String REASON = "reason";
	/*
	 * The amount, if applicable, associated with the payment or modi0cation.
	 * This consists of a currencyCode and a value which is the amount in minor
	 * units. For HTTP POST noti0cations, you will receive the currency and
	 * value as parameters.
	 */
	public static final String AMOUNT = "amount";
	public static final String CURRENCY = "currency";
	public static final String VALUE = "value";
	
	/*
	 * Adyen will send a PENDING notification once the Boleto transaction is 
	 * created in the Adyen system. We will return the
	 * additionalData.acquirerReference, in the notification, you may want 
	 * to store this data as it is the Boleto's "Nosso Numero" or ID at the
	 *	bank.
	 *	Adyen will send an AUTHORISATION notification once we have received 
	 * confirmation from the bank that the Boleto has been paid.
	 */
	public static final String BOLETO_NOSSONUMERO = "additionalData.acquirerReference";
}
