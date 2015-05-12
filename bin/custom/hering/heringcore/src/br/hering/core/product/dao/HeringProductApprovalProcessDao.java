/**
 * 
 */
package br.hering.core.product.dao;

import de.hybris.platform.catalog.model.CatalogVersionModel;

import java.util.Collection;

import br.hering.core.model.HeringProductModel;
import br.hering.core.model.HeringStyleVariantProductModel;
import br.hering.core.product.dao.impl.SearchResultPage;

/**
 * @author franthescollymaneira
 *
 */
public interface HeringProductApprovalProcessDao
{
	SearchResultPage<HeringStyleVariantProductModel> findStyleVariantsToDeactivate(final Collection<CatalogVersionModel> versions, int count);
	
	SearchResultPage<HeringProductModel> findBaseProductsToDeactivate(final Collection<CatalogVersionModel> versions, int count);
	
	SearchResultPage<HeringStyleVariantProductModel> findStyleVariantsToActivate(final Collection<CatalogVersionModel> versions, int count);
}