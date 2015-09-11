/**
 * 
 */
package br.hering.core.cms.component.slot;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.contents.contentslot.ContentSlotModel;
import de.hybris.platform.cms2.servicelayer.services.CMSContentSlotService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.Collection;

/**
 * @author ezequiel
 * @since
 */
public interface HeringCMSContentSlotService
extends CMSContentSlotService
{
	ContentSlotModel getContentSlotForId(final String id,
			final Collection<CatalogVersionModel> catalogVersionCollection)
					throws AmbiguousIdentifierException, UnknownIdentifierException;
}
