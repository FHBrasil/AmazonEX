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
package de.fliegersoftware.amazon.payment.constants;

/**
 * Global class for all Amazonpayment constants. You can add global constants for your extension into this class.
 */
public final class AmazonpaymentConstants extends GeneratedAmazonpaymentConstants
{
	public static final String EXTENSIONNAME = "amazonpayment";
	public static final String PAYMENT_PROVIDER_NAME = "Amazon";
	public static final String PAYMENT_MODE_CODE = "amazon";

	private AmazonpaymentConstants()
	{
		//empty to avoid instantiating this constant class
	}

	// implement here constants used by this extension
	public static final String SYNCHRONIOUS_CHECKOUT_CONFIG = "amazonpayment.authorization.synchronouscheckout";
	public static final String CHARGE_ON_ORDER_CONFIG = "amazonpayment.authorization.chargeonorder";
	public static final String SANDBOX_MODE_CONFIG = "amazonpayment.authorization.sandboxmode";
}
