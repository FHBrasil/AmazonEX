/**
 *
 */
package com.fliegersoftware.media.strategies.impl;

import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.fliegersoftware.media.strategies.FindMainMediaContainerStrategy;


/**
 * @author franthescollymaneira
 *
 */
public class DefaultFindMainMediaContainerStrategy implements FindMainMediaContainerStrategy
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fliegersoftware.media.strategies.FindMainMediaContainerStrategy#getMainMediaContainer(de.hybris.platform.core
	 * .model.product.ProductModel)
	 */
	@Override
	public MediaContainerModel getMainMediaContainer(final ProductModel product)
	{
		final List<MediaContainerModel> galleryImages = product.getGalleryImages();

		if (CollectionUtils.isEmpty(galleryImages))
		{
			return null;
		}

		return galleryImages.get(0);
	}
}