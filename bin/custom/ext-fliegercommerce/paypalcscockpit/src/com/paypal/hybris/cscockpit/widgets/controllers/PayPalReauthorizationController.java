/**
 *
 */
package com.paypal.hybris.cscockpit.widgets.controllers;

import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.widgets.controllers.WidgetController;

import org.zkoss.zul.Textbox;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public interface PayPalReauthorizationController extends WidgetController
{
	TypedObject getOrder();

	/**
	 * @param container
	 * @return
	 */
	TypedObject createOrderReauthorizationRequest(Textbox container);
}
