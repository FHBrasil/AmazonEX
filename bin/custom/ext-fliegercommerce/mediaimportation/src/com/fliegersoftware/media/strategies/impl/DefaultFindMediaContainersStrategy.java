/**
 *
 */
package com.fliegersoftware.media.strategies.impl;

import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.Collection;

import org.springframework.util.Assert;

import com.fliegersoftware.media.strategies.FindMediaContainersStrategy;


/**
 * Default implementation of the {@link FindMediaContainersStrategy}. <br />
 *
 * @author Franthescolly Maneira
 *
 */
public class DefaultFindMediaContainersStrategy implements FindMediaContainersStrategy
{
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.fliegersoftware.media.strategies.FindMediaContainersStrategy#getMediaContainers(de.hybris.platform.core.model
	 * .product.ProductModel)
	 */
	@Override
	public Collection<MediaContainerModel> getMediaContainers(final ProductModel product)
	{
		Assert.notNull(product, "Product is null");

		return product.getGalleryImages();
	}
}