/**
 *
 */
package com.fliegersoftware.media.events;

import de.hybris.platform.commercefacades.product.ImageFormatMapping;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.media.MediaContainerService;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.fliegersoftware.media.strategies.FindMainMediaContainerStrategy;


/**
 * @author franthescollymaneira
 *
 */
public class GalleryChangedListener extends AbstractEventListener<GalleryChangedEvent>
{
	private static final Logger LOG = Logger.getLogger(GalleryChangedListener.class);

	private ModelService modelService;

	private FindMainMediaContainerStrategy findMainMediaContainerStrategy;

	private ImageFormatMapping imageFormatMapping;

	private MediaContainerService mediaContainerService;

	private MediaService mediaService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.servicelayer.event.impl.AbstractEventListener#onEvent(de.hybris.platform.servicelayer.event
	 * .events.AbstractEvent)
	 */
	@Override
	protected void onEvent(final GalleryChangedEvent event)
	{
		LOG.debug("Entering event handler");

		final ProductModel product = event.getProduct();
		final MediaContainerModel mainMediaContainer = getFindMainMediaContainerStrategy().getMainMediaContainer(product);

		if (mainMediaContainer == null)
		{
			LOG.debug(String.format("Product %s has no main media container", product.getCode()));
			removeMediasFromProduct(product);
			return;
		}

		LOG.debug(String.format("Product %s media container is: %s", product.getCode(), mainMediaContainer.getQualifier()));

		addMediasToProduct(product, mainMediaContainer);
	}

	/**
	 * @param product
	 * @param mediaContainer
	 */
	private void addMediasToProduct(final ProductModel product, final MediaContainerModel mediaContainer)
	{
		LOG.debug(String.format("Adding medias to product %s using media container: %s", product.getCode(),
				mediaContainer.getQualifier()));

		product.setPicture(getMediaByFormat(mediaContainer, "product"));
		product.setThumbnail(getMediaByFormat(mediaContainer, "thumbnail"));
		//product.setThumbnails(getMediaByFormat(mediaContainer, "thumbnail"));
		//product.setOthers(getMediaByFormat(mediaContainer, "zoom"));
		//product.setNormal(getMediaByFormat(mediaContainer, "product"));
	}

	protected MediaModel getMediaByFormat(final MediaContainerModel mediaContainer, final String imageFormat)
	{
		final String mediaFormatQualifier = getImageFormatMapping().getMediaFormatQualifierForImageFormat(imageFormat);
		if (mediaFormatQualifier == null)
		{
			return null;
		}

		final MediaFormatModel mediaFormat = getMediaService().getFormat(mediaFormatQualifier);
		if (mediaFormat == null)
		{
			return null;
		}

		return getMediaContainerService().getMediaForFormat(mediaContainer, mediaFormat);
	}

	protected void removeMediasFromProduct(final ProductModel product)
	{
		LOG.debug(String.format("Cleaning medias from product %s", product.getCode()));

		product.setPicture(null);
		product.setThumbnail(null);
		product.setThumbnails(null);
		product.setOthers(null);
		product.setNormal(null);
	}

	/**
	 * @return the findMainMediaContainerStrategy
	 */
	public FindMainMediaContainerStrategy getFindMainMediaContainerStrategy()
	{
		return findMainMediaContainerStrategy;
	}

	/**
	 * @param findMainMediaContainerStrategy
	 *           the findMainMediaContainerStrategy to set
	 */
	@Required
	public void setFindMainMediaContainerStrategy(final FindMainMediaContainerStrategy findMainMediaContainerStrategy)
	{
		this.findMainMediaContainerStrategy = findMainMediaContainerStrategy;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the imageFormatMapping
	 */
	public ImageFormatMapping getImageFormatMapping()
	{
		return imageFormatMapping;
	}

	/**
	 * @param imageFormatMapping
	 *           the imageFormatMapping to set
	 */
	@Required
	public void setImageFormatMapping(final ImageFormatMapping imageFormatMapping)
	{
		this.imageFormatMapping = imageFormatMapping;
	}

	/**
	 * @return the mediaContainerService
	 */
	public MediaContainerService getMediaContainerService()
	{
		return mediaContainerService;
	}

	/**
	 * @param mediaContainerService
	 *           the mediaContainerService to set
	 */
	@Required
	public void setMediaContainerService(final MediaContainerService mediaContainerService)
	{
		this.mediaContainerService = mediaContainerService;
	}

	/**
	 * @return the mediaService
	 */
	public MediaService getMediaService()
	{
		return mediaService;
	}

	/**
	 * @param mediaService
	 *           the mediaService to set
	 */
	@Required
	public void setMediaService(final MediaService mediaService)
	{
		this.mediaService = mediaService;
	}
}