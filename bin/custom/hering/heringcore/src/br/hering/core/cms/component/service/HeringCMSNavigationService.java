/**
 * 
 */
package br.hering.core.cms.component.service;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.navigation.CMSNavigationNodeModel;
import de.hybris.platform.cms2.servicelayer.services.CMSNavigationService;

import java.util.Collection;

/**
 * @author ezequiel
 * @since  
 */
public interface HeringCMSNavigationService
extends CMSNavigationService
{
	CMSNavigationNodeModel getNavigationNodeForId(String id, 
			Collection<CatalogVersionModel> catalogCollection)
			    throws CMSItemNotFoundException;

}
