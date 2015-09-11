/**
 * 
 */
package br.hering.core.cms.component.service.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.navigation.CMSNavigationNodeModel;
import de.hybris.platform.cms2.servicelayer.services.impl.DefaultCMSNavigationService;

import java.util.Collection;
import java.util.List;

import br.hering.core.cms.component.service.HeringCMSNavigationService;

/**
 * @author ezequiel
 * @since 
 */
public class DefaultHeringCMSNavigationService
extends DefaultCMSNavigationService
implements HeringCMSNavigationService
{

	/* (non-Javadoc)
	 * @see br.hering.core.cms.component.slot.HeringCMSNavigationService#getNavigationNodeForId(java.lang.String, java.util.Collection)
	 */
	@Override
	public CMSNavigationNodeModel getNavigationNodeForId(final String id, 
			final Collection<CatalogVersionModel> catalogCollection)
			throws CMSItemNotFoundException
	{
		List node = 
				this.cmsNavigationDao.findNavigationNodesById(id, catalogCollection);
		if (node.isEmpty())
		{
			throw new CMSItemNotFoundException("No NavigationNode with id [" + id + "] found.");
		}

		return ((CMSNavigationNodeModel)node.iterator().next());
	}
}
