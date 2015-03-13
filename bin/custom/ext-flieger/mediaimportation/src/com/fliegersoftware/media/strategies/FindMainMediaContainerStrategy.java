/**
 *
 */
package com.fliegersoftware.media.strategies;

import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.product.ProductModel;


/**
 * @author franthescollymaneira
 *
 */
public interface FindMainMediaContainerStrategy
{
	MediaContainerModel getMainMediaContainer(ProductModel product);
}