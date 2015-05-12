/*
 * [y] hybris Platform
 * 
 * Copyright (c) 2000-2012 hybris AG All rights reserved.
 * 
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the license
 * agreement you entered into with hybris.
 */
package com.paypal.hybris.constants;


/**
 * Global class for all Paypal constants. You can add global constants for your
 * extension into this class.
 */
@SuppressWarnings("deprecation")
public final class PaypalConstants extends GeneratedPaypalConstants {

public static final String EXTENSIONNAME = "paypal";
public static final String PAYMENT_PROVIDER_NAME = "PayPal";
public static final String PAYMENT_MODE_CODE = "paypal";

// Session keys
public static final String SESS_KEY_PAYMENT = "payment";
public static final String SESS_KEY_SET_EXPRESS_CHECKOUT = "SetExpressCheckout";
public static final String SESS_KEY_GET_EXPRESS_CHECKOUT_DETAILS = "GetExpressCheckoutDetails";
public static final String SESS_KEY_REFUND_TRANSACTION = "RefundTransaction";
public static final String SESS_KEY_GET_TRANSACTION_DETAILS = "GetTransactionDetails";
public static final String SESS_KEY_TRANSACTION_SEARCH = "TransactionSearch";
public static final String SESS_KEY_DO_EXPRESS_CHECKOUT_PAYMENT = "DoExpressCheckoutPayment";
public static final String SESS_KEY_STORE_SID = "storeSessionId";

// SOAP API constants
public static final String SOAP_API_VERSION = "98.0";

// Web parameters
public static final String PARAM_TOKEN = "token";
public static final String PARAM_PAYER_ID = "payerId";
public static final String PARAM_SHIPPING = "amountShipping";
public static final String PARAM_SUBTOTAL = "amountSubtotal";
public static final String PARAM_TOTAL_HOP = "amount";
public static final String PARAM_TAX = "amountTax";
public static final String PARAM_CURRENCY = "amountCurrency";
public static final String PARAM_CURRENCY_HOP = "currency";
public static final String PARAM_SOLUTION_TYPE = "solutionType";
public static final String PARAM_TRANSACTION_ID = "transactionId";
public static final String PARAM_START_DATE = "startDate";
public static final String PARAM_END_DATE = "endDate";
public static final String PARAM_PAYER_EMAIL = "payerEmail";
public static final String PARAM_RECEIVER_EMAIL = "receiverEmail";
public static final String PARAM_RECEIPT_ID = "receiptId";
public static final String PARAM_INVOICE_ID = "invoiceId";
public static final String PARAM_CARD_NUMBER = "cardNumber";
public static final String PARAM_PAYER_NAME = "payerName";
public static final String PARAM_AUCTION_ITEM_NUMBER = "auctionItemNumber";
public static final String PARAM_TRANSACTION_CLASS = "transactionClass";
public static final String PARAM_STATUS = "status";
public static final String PARAM_AMOUNT = "amount";
public static final String PARAM_PROFILE_ID = "profileId";
public static final String PARAM_PAYMENT_ACTION = "paymentAction";
public static final String PARAM_AUTHORIZATION_ID = "authorizationId";
public static final String PARAM_NOTE = "note";
public static final String PARAM_MSG_SUB_ID = "msgSubId";
public static final String PARAM_SID = "paypalSessionId";
public static final String PARAM_IS_TEST = "isTest";
public static final String PARAM_STORE_SID = "storeSessionId";

public static final String PARAM_COMPLETE_TYPE = "completeType";
public static final String PARAM_SOFT_DESCRIPTOR = "softDescriptor";
public static final String PARAM_MERCHANT_STORE_DETAILS = "merchantStoreDetails";
public static final String PARAM_STORE_ID = "storeID";
public static final String PARAM_TERMINAL_ID = "terminalId";

public static final String PARAM_ITEM_NAME = "itemName";
public static final String PARAM_ITEM_NUMBER = "itemNumber";
public static final String PARAM_ITEM_DESCRIPTION = "itemDescription";

/**
 * Should be the same as in paypal.properties inside return and cancel URLs
 */
public static final String PARAM_PAYPAL_RESULT = "result";

// Settings
public static final String SETT_ENDPOINT = "endpoint";
public static final String SETT_SIGNATURE = "signature";
public static final String SETT_PASSWORD = "password";
public static final String SETT_USERNAME = "username";

public static final String SETT_RETURN_URL_SINGLE_DESKTOP = "returnUrl.single.desktop";
public static final String SETT_RETURN_URL_SINGLE_MOBILE = "returnUrl.single.mobile";
public static final String SETT_RETURN_URL_MULTI_DESKTOP = "returnUrl.multi.desktop";
public static final String SETT_RETURN_URL_MULTI_MOBILE = "returnUrl.multi.mobile";

public static final String SETT_CANCEL_URL_SINGLE_DESKTOP = "cancelUrl.single.desktop";
public static final String SETT_CANCEL_URL_SINGLE_MOBILE = "cancelUrl.single.mobile";
public static final String SETT_CANCEL_URL_MULTI_DESKTOP = "cancelUrl.multi.desktop";
public static final String SETT_CANCEL_URL_MULTI_MOBILE = "cancelUrl.multi.mobile";

public static final String SETT_REDIRECT_URL_DESKTOP = "redirectUrl.desktop";
public static final String SETT_REDIRECT_URL_MOBILE = "redirectUrl.mobile";

public static final String PAYPAL_OPERATION_LIFETIME = "lifetime";
public static final String PAYPAL_OPERATION_PERSIST = "operation.persist";


public static final String TEST_RETURN_URL = "test.returnUrl";
public static final String TEST_CANCEL_URL = "test.cancelUrl";

// Commands statuses
public static final String STATUS_SUCCESS = "SUCCESS";
public static final String STATUS_ACCEPTED = "ACCEPTED";
public static final String STATUS_ERROR = "ERROR";
public static final String STATUS_FAILURE = "FAILURE";

// Other
public static final String PAYPAL_FLOW_COOKIE_NAME = "paypal";
public static final String PAYPAL_DISABLE_BUTTON = "disableButton";
public static final String PAYPAL_HIDE_PAYPAL_BUTTON_COOKIE_NAME = "offPaypalButton";
public static final String BUTTON_SOURCE = "GorillaGroup_SP_HybrisEC";
public static final String CLEANER_JOB_CODE = "paypalCleanerJob";
public static final String PAYPAL_ERROR_MESSAGE = "paypalErrorMessage";
public static final String PAYPAL_RESULT_ACCEPTED = "accepted";


private PaypalConstants() {

}

}
