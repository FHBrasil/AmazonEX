/**
 * 
 */
package br.hering.facades.product.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.product.ImageFormatMapping;
import de.hybris.platform.commercefacades.product.converters.populator.ProductGalleryImagesPopulator;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.ImageDataType;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.store.BaseStoreModel;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;


/**
 * @author herbert
 * 
 */
public class HeringProductGalleryImagePopulator<SOURCE extends ProductModel, TARGET extends ProductData> extends
		ProductGalleryImagesPopulator<SOURCE, TARGET>
{

	Map<String, ImageFormatMapping> imageFormatMappingMap;

	/**
	 * @return the imageFormatMappingMap
	 */
	protected Map<String, ImageFormatMapping> getImageFormatMappingMap()
	{
		return imageFormatMappingMap;
	}

	/**
	 * @param imageFormatMappingMap the imageFormatMappingMap to set
	 */
	@Required
	public void setImageFormatMappingMap(Map<String, ImageFormatMapping> imageFormatMappingMap)
	{
		this.imageFormatMappingMap = imageFormatMappingMap;
	}

	@Override
	protected void addImagesInFormats(MediaContainerModel mediaContainer, ImageDataType imageType, int galleryIndex,
			List<ImageData> list)
	{
		BaseSiteModel baseSite = null;
		for(BaseStoreModel store : mediaContainer.getCatalogVersion().getCatalog().getBaseStores())
		{
			for(BaseSiteModel site : store.getCmsSites())
			{
				baseSite = site;
			}
		}
		final ImageFormatMapping imageFormatMapping = getImageFormatMappingMap().get(baseSite.getUid());
		new HeringProductImagePopulatorHelper(getImageFormats(), imageFormatMapping, getMediaService(),
				getMediaContainerService(), getImageConverter()).addImagesInFormats(mediaContainer, imageType, galleryIndex,
				list);
	}

	
}