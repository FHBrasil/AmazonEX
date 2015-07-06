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
public interface PixiProductGetItemTagsStrategy
{
	/**
	 * @param source
	 * @return
	 */
	List<String> getItemTagsByProduct(final ProductModel product);

}