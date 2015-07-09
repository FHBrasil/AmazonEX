/**
 *
 */
package com.pixi.core.strategies;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;


/**
 * @author franthescollymaneira
 *
 */
public interface PixiProductGetSuppliersCodeStrategy
{
	List<String> getSuppliersCode(final ProductModel product);
}