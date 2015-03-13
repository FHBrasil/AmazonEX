/**
 *
 */
package com.fliegersoftware.media.strategies.impl;

import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.fliegersoftware.media.strategies.FindMainMediaContainerStrategy;
import com.fliegersoftware.media.strategies.FindMediaContainersStrategy;


/**
 * @author franthescollymaneira
 *
 */
public class DefaultFindMainMediaContainerStrategy implements FindMainMediaContainerStrategy
{
	private FindMediaContainersStrategy findMediaContainersStrategy;

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
		final Collection<MediaContainerModel> containers = getFindMediaContainersStrategy().getMediaContainers(product);

		if (CollectionUtils.isEmpty(containers))
		{
			return null;
		}

		return (MediaContainerModel) CollectionUtils.get(containers, 0);
	}

	/**
	 * @return the findMediaContainersStrategy
	 */
	public FindMediaContainersStrategy getFindMediaContainersStrategy()
	{
		return findMediaContainersStrategy;
	}

	/**
	 * @param findMediaContainersStrategy
	 *           the findMediaContainersStrategy to set
	 */
	@Required
	public void setFindMediaContainersStrategy(final FindMediaContainersStrategy findMediaContainersStrategy)
	{
		this.findMediaContainersStrategy = findMediaContainersStrategy;
	}
}