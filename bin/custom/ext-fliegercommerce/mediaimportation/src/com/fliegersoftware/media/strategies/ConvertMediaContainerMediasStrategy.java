/**
 *
 */
package com.fliegersoftware.media.strategies;

import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaModel;


/**
 * Strategy used to convert the {@link MediaModel} items of a {@link MediaContainerModel}
 *
 * @author Franthescolly Maneira
 *
 */
public interface ConvertMediaContainerMediasStrategy
{
	/**
	 * Converts the {@link MediaModel} items of the given container
	 *
	 * @param container
	 *           The {@link MediaContainerModel} to convert the medias.
	 */
	void convertMedias(final MediaContainerModel container);
}