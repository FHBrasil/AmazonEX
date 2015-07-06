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
public interface PixiProductGetWeightStrategy
{
	BigDecimal getWeight(final ProductModel product);
}