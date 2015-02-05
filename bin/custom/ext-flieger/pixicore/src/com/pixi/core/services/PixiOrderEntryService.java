/**
 *
 */
package com.pixi.core.services;

import de.hybris.platform.core.model.order.OrderEntryModel;


/**
 * @author franthescollymaneira
 *
 */
public interface PixiOrderEntryService
{
	void updateOrderEntryStatus(OrderEntryModel entry, String pixiStatusCode);
}