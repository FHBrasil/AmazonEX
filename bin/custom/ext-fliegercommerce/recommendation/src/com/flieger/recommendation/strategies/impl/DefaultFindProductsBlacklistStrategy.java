/**
 *
 */
package com.flieger.recommendation.strategies.impl;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.Collections;
import java.util.List;

import com.flieger.recommendation.strategies.FindProductsBlacklistStrategy;


/**
 * @author franthescollymaneira
 *
 */
public class DefaultFindProductsBlacklistStrategy implements FindProductsBlacklistStrategy
{
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.flieger.recommendation.strategies.FindProductsBlacklistStrategy#findBlacklistByProduct(de.hybris.platform.
	 * core.model.product.ProductModel)
	 */
	@Override
	public List<ProductModel> findBlacklistByProduct(final ProductModel product)
	{
		return Collections.emptyList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flieger.recommendation.strategies.FindProductsBlacklistStrategy#findBlacklist()
	 */
	@Override
	public List<ProductModel> findBlacklist()
	{
		return Collections.emptyList();
	}
}