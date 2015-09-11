/**
 *
 */
package com.pixi.core.strategies;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;


/**
 * @author franthescollymaneira
 *
 */
public interface PixiOrderGetItemNoteStrategy
{
	/**
	 * @param entry
	 * @return
	 */
	String getItemNoteByOrderEntry(final AbstractOrderEntryModel entry);
}