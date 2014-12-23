/**
 *
 */
package com.pixi.core.services;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;


/**
 * @author franthescollymaneira
 *
 */
public interface PixiProductService
{
	List<ProductModel> findProductsToExport();
}
