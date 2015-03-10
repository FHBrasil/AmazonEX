/**
 *
 */
package com.fliegersoftware.media.strategies;

import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.Collection;


/**
 * @author franthescollymaneira
 *
 */
public interface FindMediaContainersStrategy
{
	Collection<MediaContainerModel> getMediaContainers(ProductModel product);
}