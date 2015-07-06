/**
 *
 */
package com.fliegersoftware.media.strategies;

import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.product.ProductModel;


/**
 * Finds the main {@link MediaContainerModel} among all other {@link MediaContainerModel} items related to a product.
 *
 * @author Franthescolly Maneira
 */
public interface FindMainMediaContainerStrategy
{
	/**
	 * Filter all containers related to the given {@link ProductModel} and return the main one.
	 *
	 * @param product
	 *           the {@link ProductModel} used to find the main {@link MediaContainerModel}
	 * @return The main {@link MediaContainerModel}
	 */
	MediaContainerModel getMainMediaContainer(ProductModel product);
}