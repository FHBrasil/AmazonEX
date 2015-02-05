/**
 *
 */
package com.pixi.core.daos;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.internal.dao.Dao;

import java.util.List;

import com.pixi.core.model.PixiSyncRecordModel;


/**
 * @author franthescollymaneira
 *
 */
public interface PixiProductDao extends Dao
{
	List<String> getAllProductsCode(final CatalogVersionModel catalogVersion);

	List<ProductModel> findProductsToExport(final CatalogVersionModel catalogVersion);

	List<String> findExportedProductsBySessionID(final String sessionID);

	PixiSyncRecordModel findSyncRecord(final String productCode);
}