/**
 *
 */
package com.fliegersoftware.media.daos;

import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.mediaconversion.conversion.MediaConversionServiceDao;

import java.util.List;


/**
 * Dao to retrieve {@link MediaContainerModel} related data
 *
 * @author Franthescolly Maneira
 *
 */
public interface MediaImportationDao extends MediaConversionServiceDao
{
	/**
	 * Returns a list of {@link ProductModel} containing the given {@link MediaContainerModel} in the galleryImages
	 * attribute
	 *
	 * @param mediaContainer
	 *           The {@link MediaContainerModel} referenced by the {@link ProductModel} galleryImages attribute
	 * @return a list with the {@link ProductModel} items related to this {@link MediaContainerModel}
	 */
	List<ProductModel> findProductsUsingMediaContainer(final MediaContainerModel mediaContainer);
}