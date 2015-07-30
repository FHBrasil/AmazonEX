/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *  
 */
package de.fliegersoftware.amazon.payment.addon.controllers;

import de.fliegersoftware.amazon.payment.addon.model.AmazonAddressBookComponentModel;
import de.fliegersoftware.amazon.payment.addon.model.AmazonPayButtonComponentModel;
import de.fliegersoftware.amazon.payment.addon.model.AmazonWalletComponentModel;
/**
 */
public interface AmazonpaymentaddonControllerConstants
{
	final String ADDON_PREFIX = "addon:/amazonpaymentaddon/";

	interface Actions {
		interface Cms {
			String _Prefix = "/view/";
			String _Suffix = "Controller";

			String AmazonAddressBookComponent = _Prefix + AmazonAddressBookComponentModel._TYPECODE + _Suffix;
			String AmazonPayButtonComponent = _Prefix + AmazonPayButtonComponentModel._TYPECODE + _Suffix;
			String AmazonWalletComponent = _Prefix + AmazonWalletComponentModel._TYPECODE + _Suffix;
		}
	}

	interface Views {
		interface Pages {
			interface Checkout {
				String AmazonCheckoutPage = ADDON_PREFIX +
						"pages/checkout/amazonCheckout";
			}
		}
	}
}
