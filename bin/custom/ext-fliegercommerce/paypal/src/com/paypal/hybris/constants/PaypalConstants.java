/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.paypal.hybris.constants;



/**
 * Global class for all Paypal constants. You can add global constants for your extension into this class.
 */
public final class PaypalConstants extends GeneratedPaypalConstants
{
	public static final String EXTENSIONNAME = "paypal";

	private PaypalConstants()
	{
		//empty to avoid instantiating this constant class
	}

	// implement here constants used by this extension

	// Web parameters
	public static final String PARAM_TOKEN = "token";
	public static final String PARAM_PAYER_ID = "payerId";
	public static final String PARAM_SHIPPING = "amountShipping";
	public static final String PARAM_SUBTOTAL = "amountSubtotal";
	public static final String PARAM_SHIPPING_DISCOUNT = "shippingDiscount";
	public static final String PARAM_HANDLING_TOTAL = "handlingTotal";
	public static final String PARAM_INSURANCE_TOTAL = "insuranceTotal";
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

	public static final String PARAM_RETURN_URL = "successReturnUrl";
	public static final String PARAM_CANCEL_RETURN_URL = "cancelReturnUrl";

	// Settings
	public static final String SETT_ENDPOINT = "paypal.endpoint";
	public static final String SETT_SIGNATURE = "paypal.signature";
	public static final String SETT_PASSWORD = "paypal.password";
	public static final String SETT_USERNAME = "paypal.username";

	public static final String PAYMENT_ACTION = "paypal.payment.action";
	public static final String SOLUTION_TYPE = "paypal.solution.type";

	public static final String REQUIRE_BILLING_ADDRESS = "paypal.requireBillingAddress";
	public static final String REQUIRE_BILLING_ADDRESS_OPTION_ON = "1";
	public static final String REQUIRE_BILLING_ADDRESS_OPTION_OFF = "0";

	public static final String HEADER_IMAGE = "paypal.headerImageUrl";
	public static final String PAYFLOW_COLOR = "paypal.payflowColor";

	public static final String IN_CONTEXT_CHECKOUT_ENABLED = "paypal.incontext.checkout.enabled";
	public static final String IN_CONTEXT_CHECKOUT_REDIRECT_URL = "paypal.incontext.redirectUrl";

	public static final String SETT_REDIRECT_URL_DESKTOP = "paypal.redirectUrl.desktop";
	public static final String SETT_REDIRECT_URL_MOBILE = "paypal.redirectUrl.mobile";
	public static final String SETT_REDIRECT_REPEAT_ORDER_URL_DESKTOP = "paypal.redirectUrl.repeat.order.desktop";
	public static final String SETT_REDIRECT_REPEAT_ORDER_URL_MOBILE = "paypal.redirectUrl.repeat.order.mobile";

	public static final String PAYPAL_SELLER_EMAIL = "paypal.seller.email";

	public static final String PAYPAL_GUEST_REDIRECT = "paypal.guest.redirect";

	// Commands statuses
	public static final String STATUS_SUCCESS = "Success";
	public static final String STATUS_SUCCESS_WITH_WARNINGS = "SuccessWithWarning";
	public static final String STATUS_ACCEPTED = "ACCEPTED";
	public static final String STATUS_ERROR = "Error";
	public static final String STATUS_FAILURE = "Failure";

	public static final String ORDER_PAYMENT_ACTION_NAME = "Order";
	public static final String SALE_PAYMENT_ACTION_NAME = "Sale";
	public static final String AUTHORIZATION_PAYMENT_ACTION_NAME = "Authorization";

	public static final String MARK_SOLUTION_TYPE_NAME = "Mark";

	public static final String DEFAULT_PAYMENT_ACTION_NAME = SALE_PAYMENT_ACTION_NAME;
	public static final String DEFAULT_SOLUTION_TYPE_NAME = MARK_SOLUTION_TYPE_NAME;
	public static final String SOLE_SOLUTION_TYPE_NAME = "Sole";

	// SOAP API constants
	public static final String SOAP_API_VERSION = "119.0";
	public static final String ADDRESS_OVERRIDE_OPTION_ON = "1";
	public static final String ADDRESS_OVERRIDE_OPTION_OFF = "0";
	public static final String S2S_ADDRESS_NAME_PREFIX = "S2S";
	public static final char ADDRESS_NAME_SEPARATOR = ' ';
	public static final char PAYMENT_REQUEST_ID_SEPARATOR = '_';
	public static final String ERROR_MESSAGE_CODE_SEPARATOR = ": ";
	public static final String ERROR_MESSAGE_SHORT_MESSAGE_SEPARATOR = ". ";
	public static final int PAYMENT_REQUEST_ID_DEFAULT_DELIVERY_NUM = 0;
	public static final Double DEFAULT_AMOUNT_VALUE = 0.0;

	// Other
	public static final String PAYPAL_FLOW_COOKIE_NAME = "paypal";
	public static final String PAYPAL_DISABLE_BUTTON = "disableButton";
	public static final String PAYPAL_HIDE_PAYPAL_BUTTON_COOKIE_NAME = "offPaypalButton";
	public static final String BUTTON_SOURCE = "Hybris_Ecom_EPAM";
	public static final String BUTTON_SOURCE_CREDIT = "Hybris_Ecom_Credit";
	public static final String CLEANER_JOB_CODE = "paypalCleanerJob";
	public static final String PAYPAL_ERROR_MESSAGE = "paypalErrorMessage";
	public static final String PAYPAL_RESULT_ACCEPTED = "accepted";
	public static final String IS_PAYPAL_CREDIT = "isPaypalCredit";
	public static final String PAYPAL_PAYMENT_INFO_CODE = "PAYPAL PAYMENT";
	public static final String PAYPAL_CREDIT_PAYMENT_INFO_CODE = "PAYPAL CREDIT PAYMENT";
	public static final String TRANSACTION_SEARCH_RESULT = "TRANSACTION_SEARCH_RESULT";
	public static final String NO_SHIPPING_DOES_NOT_DISPLAY = "1";
	public static final String ALLOW_NOTE_OPTION_ON = "1";
	public static final String ALLOW_NOTE_OPTION_OFF = "0";
	public static final String DISCOUNT = "Discount";
	public static final String ORDER_DISCOUNT = "Order Discount";
	public static final String THOUSAND_SEPARATOR = ",";



	// Processed error codes
	public static final String ERROR_CODE_10486 = "10486";


	public static final String PAYMENT_PROVIDER_NAME = "PayPal";

	// Funding options
	public static final String BML = "BML"; // PayPal Credit
	public static final String CREDIT_CARD = "CreditCard";

	// Reference transactions flag
	public static final String USE_REFERENCE_TRANSCATION = "paypal.useReferenceTransaction";
	public static final String SINGLE_BILLING_AGREEMENT = "MerchantInitiatedBillingSingleAgreement";
}
