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
package de.fliegersoftware.amazon.login.addon.controllers;

import de.fliegersoftware.amazon.login.addon.model.AmazonLoginComponentModel;

/**
 */
public interface AmazonloginaddonControllerConstants
{
	final String ADDON_PREFIX = "addon:/amazonloginaddon/";

	interface Actions {
		interface Cms {
			String _Prefix = "/view/";
			String _Suffix = "Controller";

			String AmazonLoginComponent = _Prefix + AmazonLoginComponentModel._TYPECODE + _Suffix;
		}
	}

	interface Views {
		interface Pages {
			String AmazonConfirmAccountPage = ADDON_PREFIX
					+ "pages/amazonConfirmAccountPage";
		}
	}
}
