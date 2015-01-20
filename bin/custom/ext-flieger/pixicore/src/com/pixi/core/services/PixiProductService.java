/**
 *
 */
package com.pixi.core.services;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.Date;
import java.util.List;


/**
 * @author franthescollymaneira
 *
 */
public interface PixiProductService
{
	List<ProductModel> findProductsToExport();

	List<String> findExportedProductsBySessionID(final String sessionID);

	List<String> findAllProducts();

	void saveSyncRecord(String productCode, String sessionID, Date exportDate);

	void releaseProductsFromSession(String session);

	void saveExportConfirmationDate(String next, Date exportDate, Date confirmationDate);
}
