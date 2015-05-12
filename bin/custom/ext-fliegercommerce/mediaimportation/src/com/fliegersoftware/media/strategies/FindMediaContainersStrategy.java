/**
 *
 */
package com.fliegersoftware.media.strategies;

import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.Collection;
import java.util.List;


/**
 * Strategy used to find all {@link MediaContainerModel} items related to a {@link ProductModel}
 *
 * @author Franthescolly Maneira
 *
 */
public interface FindMediaContainersStrategy
{
	/**
	 * Finds all {@link MediaContainerModel} related to the given {@link ProductModel}
	 * 
	 * @param product
	 *           The product to look for all {@link MediaContainerModel} items
	 * @return a {@link List} of {@link MediaContainerModel} items related to the given {@link ProductModel}
	 */
	Collection<MediaContainerModel> getMediaContainers(final ProductModel product);
}