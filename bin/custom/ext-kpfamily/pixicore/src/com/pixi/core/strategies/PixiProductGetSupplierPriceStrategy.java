/**
 *
 */
package com.pixi.core.strategies;

import de.hybris.platform.core.model.product.ProductModel;

import java.math.BigDecimal;


/**
 * @author franthescollymaneira
 *
 */
public interface PixiProductGetSupplierPriceStrategy
{
	/**
	 * @param source
	 * @return
	 */
	BigDecimal getSupplierPrice(final ProductModel product);
}