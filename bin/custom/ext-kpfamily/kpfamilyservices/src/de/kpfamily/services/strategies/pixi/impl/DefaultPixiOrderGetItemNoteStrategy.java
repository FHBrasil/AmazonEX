/**
 * 
 */
package de.kpfamily.services.strategies.pixi.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.pixi.core.strategies.PixiOrderGetItemNoteStrategy;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.kpfamily.core.model.BabyartikelProductModel;

/**
 * @author franthescollymaneira
 *
 */
public class DefaultPixiOrderGetItemNoteStrategy implements PixiOrderGetItemNoteStrategy 
{
	private static final String NOTES_SEPARATOR = "-";

	/* (non-Javadoc)
	 * @see com.pixi.core.strategies.PixiOrderGetItemNoteStrategy#getItemNoteByOrderEntry(de.hybris.platform.core.model.order.AbstractOrderEntryModel)
	 */
	@Override
	public String getItemNoteByOrderEntry(final AbstractOrderEntryModel entry) 
	{
		Assert.notNull(entry);
		Assert.notNull(entry.getProduct());
		Assert.isInstanceOf(BabyartikelProductModel.class, entry.getProduct());
	
		final BabyartikelProductModel product = (BabyartikelProductModel) entry.getProduct();
		
		final List<String> notes = new ArrayList<String>();
		
		notes.add(product.getCode());
		notes.add(product.getArticleStateInternalCode());

		if(product.getLogisticData() != null)
		{
			notes.add(product.getLogisticData().getShippingMethodInternalCode());
		}
		
		return concatAllNotes(notes);
	}
	
	private String concatAllNotes(final Collection<String> notes)
	{
		return StringUtils.join(notes, NOTES_SEPARATOR);
	}
}