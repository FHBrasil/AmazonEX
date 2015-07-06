/**
 *
 */
package com.pixi.core.strategies;

import de.hybris.platform.core.model.product.ProductModel;


/**
 * @author franthescollymaneira
 *
 */
public interface PixiProductGetMinimumStockLevelStrategy
{
	int getMinimumStockLevel(ProductModel product);
}