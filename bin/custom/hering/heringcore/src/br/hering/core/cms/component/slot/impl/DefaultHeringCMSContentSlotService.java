/**
 * 
 */
package br.hering.core.cms.component.slot.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.contents.contentslot.ContentSlotModel;
import de.hybris.platform.cms2.servicelayer.services.impl.DefaultCMSContentSlotService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.Collection;
import java.util.List;

import br.hering.core.cms.component.slot.HeringCMSContentSlotService;

/**
 * @author ezequiel
 * @since 
 */
public class DefaultHeringCMSContentSlotService
extends DefaultCMSContentSlotService
implements HeringCMSContentSlotService
{
	@Override
	public ContentSlotModel getContentSlotForId(final String id,
			final Collection<CatalogVersionModel> catalogVersionCollection)
					throws AmbiguousIdentifierException, UnknownIdentifierException
	{
		List contentSlots = 
				this.cmsContentSlotDao.findContentSlotsByIdAndCatalogVersions(id,
						catalogVersionCollection);
		if (contentSlots.isEmpty())
		{
			  throw new UnknownIdentifierException("No contentSlot with id [" + id + "] found.");
		}
		if (contentSlots.size() > 1)
		{
			throw new AmbiguousIdentifierException("Content slot id '" + id 
					+ "' is not unique, " + contentSlots.size() 
					+ " content slots found!");
		}
		return ((ContentSlotModel)contentSlots.get(0));
	}
}
