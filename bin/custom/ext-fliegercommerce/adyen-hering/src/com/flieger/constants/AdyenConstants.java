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
package com.flieger.constants;

/**
 * Global class for all Adyen constants. You can add global constants for your extension into this class.
 */
public final class AdyenConstants extends GeneratedAdyenConstants
{
	public static final String EXTENSIONNAME = "adyen";
	
	private AdyenConstants()
	{
		//empty to avoid instantiating this constant class
	}

	// implement here constants used by this extension
	public static final String ONLINE_DEBIT_RETURN_URL = "onlineDebit.returnURL";
}
