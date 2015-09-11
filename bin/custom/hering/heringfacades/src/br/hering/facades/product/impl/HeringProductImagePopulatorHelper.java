/**
 * 
 */
package br.hering.facades.product.impl;

import de.hybris.platform.commercefacades.product.ImageFormatMapping;
import de.hybris.platform.commercefacades.product.converters.populator.AbstractProductImagePopulator;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.ImageDataType;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.media.MediaContainerService;
import de.hybris.platform.servicelayer.media.MediaService;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * Helper class for shared code
 * @author herbert
 *
 */
public class HeringProductImagePopulatorHelper
{

	private List<String> imageFormats;
	private ImageFormatMapping imageFormatMapping;
	private MediaService mediaService;
	private MediaContainerService mediaContainerService;
	private Converter<MediaModel, ImageData> imageConverter;
	
	private static final Logger LOG = Logger.getLogger(HeringProductImagePopulatorHelper.class);

	/**
	 * @param imageFormats
	 * @param imageFormatMapping 
	 * @param mediaService 
	 * @param mediaContainerService 
	 * @param imageConverter 
	 */
	public HeringProductImagePopulatorHelper(List<String> imageFormats, ImageFormatMapping imageFormatMapping, MediaService mediaService, MediaContainerService mediaContainerService, Converter<MediaModel,ImageData> imageConverter)
	{
		this.imageFormats = imageFormats;
		this.imageFormatMapping = imageFormatMapping;
		this.mediaService = mediaService;
		this.mediaContainerService = mediaContainerService;
		this.imageConverter = imageConverter;
	}

	/**
	 * @return the imageFormats
	 */
	private List<String> getImageFormats()
	{
		return imageFormats;
	}

	/**
	 * @return the imageFormatMapping
	 */
	public ImageFormatMapping getImageFormatMapping()
	{
		return imageFormatMapping;
	}

	/**
	 * @return the mediaService
	 */
	public MediaService getMediaService()
	{
		return mediaService;
	}

	/**
	 * @return the mediaContainerService
	 */
	public MediaContainerService getMediaContainerService()
	{
		return mediaContainerService;
	}

	/**
	 * @return the converter
	 */
	public Converter<MediaModel, ImageData> getImageConverter()
	{
		return imageConverter;
	}

	/**
	 * @param mediaContainer
	 * @param imageType
	 * @param galleryIndex
	 * @param list
	 */
	public <SOURCE extends ProductModel, TARGET extends ProductData> void addImagesInFormats(MediaContainerModel mediaContainer, ImageDataType imageType, int galleryIndex,
			List<ImageData> list)
	{
      	try{	
				for (final String imageFormat : getImageFormats())
      		{
      			final String mediaFormatQualifier = getImageFormatMapping().getMediaFormatQualifierForImageFormat(imageFormat);
      			if (mediaFormatQualifier != null)
      			{
      				final MediaFormatModel mediaFormat = getMediaService().getFormat(mediaFormatQualifier);
      				if (mediaFormat != null)
      				{
      					/*
      					LOG.info("MEDIA_CONTAINER: " + mediaContainer.getName() + "/" + mediaContainer.getItemtype() + "/"
      							+ mediaContainer.getQualifier());
      					LOG.info("MEDIA_FORMAT: " + mediaFormat.getName() + "/" + mediaFormat.getItemtype() + "/"
      							+ mediaFormat.getQualifier());
      					*/
      					final MediaModel media = getMediaContainerService().getMediaForFormat(mediaContainer, mediaFormat);
      
      					if (media != null)
      					{
      						final ImageData imageData = getImageConverter().convert(media);
      						imageData.setFormat(imageFormat);
      						imageData.setImageType(imageType);
      						if (ImageDataType.GALLERY.equals(imageType))
      						{
      							imageData.setGalleryIndex(Integer.valueOf(galleryIndex));
      						}
      						list.add(imageData);
      					}
      				}
      			}
      		}
      	}
      	catch(Exception e){
      		LOG.error("MEDIA FORMAT", e);
      	}
	}
}