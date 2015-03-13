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
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.media.MediaContainerService;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.fliegersoftware.media.strategies.FindMainMediaContainerStrategy;
import com.fliegersoftware.media.strategies.FindMediaContainersStrategy;


/**
 * @author franthescollymaneira
 *
 */
public class GalleryChangedListener extends AbstractEventListener<GalleryChangedEvent>
{
	private static final Logger LOG = Logger.getLogger(GalleryChangedListener.class);

	private ModelService modelService;

	private FindMainMediaContainerStrategy findMainMediaContainerStrategy;

	private FindMediaContainersStrategy findMediaContainersStrategy;

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

		addMediasFromMainContainer(product);

		addMediasFromAllContainers(product);
	}

	protected void addMediasFromMainContainer(final ProductModel product)
	{
		final MediaContainerModel mainMediaContainer = getFindMainMediaContainerStrategy().getMainMediaContainer(product);

		MediaModel pictureMedia = null;
		MediaModel thumbnailMedia = null;

		if (mainMediaContainer != null)
		{
			LOG.debug(String.format("Product %s main media container is: %s", product.getCode(), mainMediaContainer.getQualifier()));

			pictureMedia = getMediaByFormat(mainMediaContainer, "product");
			thumbnailMedia = getMediaByFormat(mainMediaContainer, "thumbnail");
		}
		else
		{
			LOG.debug(String.format("Product %s has no main media container", product.getCode()));
		}

		product.setPicture(pictureMedia);
		product.setThumbnail(thumbnailMedia);
	}

	protected void addMediasFromAllContainers(final ProductModel product)
	{
		final Collection<MediaContainerModel> containers = getFindMediaContainersStrategy().getMediaContainers(product);

		product.setThumbnails(new ArrayList<MediaModel>());
		product.setOthers(new ArrayList<MediaModel>());
		product.setNormal(new ArrayList<MediaModel>());

		if (CollectionUtils.isEmpty(containers))
		{
			LOG.debug(String.format("Product %s has no media containers", product.getCode()));
			return;
		}

		for (final MediaContainerModel mediaContainer : containers)
		{
			addImageInFormat(mediaContainer, "thumbnail", product.getThumbnails());
			addImageInFormat(mediaContainer, "zoom", product.getOthers());
			addImageInFormat(mediaContainer, "product", product.getNormal());
		}
	}

	private void addImageInFormat(final MediaContainerModel container, final String format, final Collection<MediaModel> medias)
	{
		final MediaModel media = getMediaByFormat(container, format);

		CollectionUtils.addIgnoreNull(medias, media);
	}

	private MediaModel getMediaByFormat(final MediaContainerModel mediaContainer, final String imageFormat)
	{
		try
		{
			final String qualifier = getImageFormatMapping().getMediaFormatQualifierForImageFormat(imageFormat);
			if (qualifier == null)
			{
				LOG.debug(String.format("Media format qualifier not found for image format %s", imageFormat));
				return null;
			}

			final MediaFormatModel mediaFormat = getMediaService().getFormat(qualifier);
			if (mediaFormat == null)
			{
				LOG.debug(String.format("MediaFormat not found for qualifier %s", qualifier));
				return null;
			}

			return getMediaContainerService().getMediaForFormat(mediaContainer, mediaFormat);
		}
		catch (final ModelNotFoundException ignore)
		{
			// Ignore
		}

		return null;
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