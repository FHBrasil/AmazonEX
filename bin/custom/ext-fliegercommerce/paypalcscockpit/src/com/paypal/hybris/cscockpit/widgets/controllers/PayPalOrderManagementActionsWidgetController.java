package com.paypal.hybris.cscockpit.widgets.controllers;

import de.hybris.platform.cscockpit.widgets.controllers.OrderManagementActionsWidgetController;


/**
 * @author Dzmitry_Rasolka (EPAM Systems)
 */
public interface PayPalOrderManagementActionsWidgetController extends OrderManagementActionsWidgetController
{

	/**
	 * Check whether it is possible to do reauthorization
	 *
	 * @return is reauthorization possible
	 */
	boolean isReauthorizationPossible();

}
