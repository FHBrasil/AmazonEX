/**
 * 
 */
package br.hering.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.ImageValueProvider;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import br.hering.core.model.HeringProductModel;
import br.hering.core.model.HeringSizeVariantProductModel;
import br.hering.core.model.HeringStyleVariantProductModel;

/**
 * @author herbert
 *
 */
public class HeringImageValueProvider extends ImageValueProvider
{

	@Resource
	private VariantsUtils variantsUtils;

	/**
	 * Provides an image selected by custom rules. Most relevant pictures contains "-C1" within it's URL
	 */
	@Override
	protected MediaModel findMedia(ProductModel product, MediaFormatModel mediaFormat)
	{
		if (product instanceof HeringSizeVariantProductModel)
		{
			// get base product
			HeringSizeVariantProductModel sizeVariant = (HeringSizeVariantProductModel) product;
			HeringStyleVariantProductModel styleVariant = (HeringStyleVariantProductModel) sizeVariant.getBaseProduct();
			HeringProductModel model = (HeringProductModel) styleVariant.getBaseProduct();
			// get available style variants
			Collection<HeringStyleVariantProductModel> variants = variantsUtils.getAvailableStyleVariants(model);
			// search for product pictures with -C1 in url
			for(VariantProductModel variant : variants) {
				List<MediaContainerModel> galleryImages = variant.getGalleryImages();
				if(!CollectionUtils.isEmpty(galleryImages)) {
					for(MediaContainerModel container : galleryImages) {
						MediaModel media = null;
						try {
							media = getMediaContainerService().getMediaForFormat(container, mediaFormat);
						} catch (ModelNotFoundException e) {
							// ignore exception
						}
						if (media != null
								&& (StringUtils.containsIgnoreCase(media.getURL(), "-C1.")
								|| StringUtils.containsIgnoreCase(media.getURL(), "-C2.")
								|| StringUtils.containsIgnoreCase(media.getURL(), "-C3.")
								|| StringUtils.containsIgnoreCase(media.getURL(), "-C4.")
								|| StringUtils.containsIgnoreCase(media.getURL(), "-C5.")
								|| StringUtils.containsIgnoreCase(media.getURL(), "-C6.")
								|| StringUtils.containsIgnoreCase(media.getURL(), "-C7.")
								|| StringUtils.containsIgnoreCase(media.getURL(), "-C9.") 
								|| StringUtils.containsIgnoreCase(media.getURL(), "-C8.") 
								|| StringUtils.containsIgnoreCase(media.getURL(), "-D1.")
								|| StringUtils.containsIgnoreCase(media.getURL(), "-D2.")
								|| StringUtils.containsIgnoreCase(media.getURL(), "-C1-B.")
							)
						){
							return media;
						}
					}
				}
			}
		}
		// if above code didn't work, uses default implementation
		return super.findMedia(product, mediaFormat);
	}
}
