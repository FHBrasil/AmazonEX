/**
 *
 */
package com.flieger.recommendation.strategies;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;


/**
 * @author franthescollymaneira
 *
 */
public interface FindProductsBlacklistStrategy
{
	List<ProductModel> findBlacklistByProduct(ProductModel product);

	List<ProductModel> findBlacklist();
}
