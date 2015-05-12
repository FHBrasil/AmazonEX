/**
 *
 */
package com.fliegersoftware.media.strategies.impl;

import de.hybris.platform.commercefacades.product.ImageFormatMapping;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.mediaconversion.enums.ConversionStatus;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.media.MediaContainerService;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.ReflectionUtils;

import com.fliegersoftware.media.events.GalleryChangedEvent;
import com.fliegersoftware.media.strategies.FindMainMediaContainerStrategy;
import com.fliegersoftware.media.strategies.FindMediaContainersStrategy;
import com.fliegersoftware.media.strategies.ProductGalleryChangedListenerStrategy;


/**
 * @author franthescollymaneira
 *
 */
public class DefaultProductGalleryChangedListenerStrategy implements ProductGalleryChangedListenerStrategy
{
	/**
	 * Log4j logger
	 */
	private static final Logger LOG = Logger.getLogger(DefaultProductGalleryChangedListenerStrategy.class);

	/**
	 * Model service
	 */
	private ModelService modelService;

	private Map<String, String> mediasFromMainContainerFormatMapping;

	private Map<String, List<String>> mediasFromAllContainersFormatMapping;

	/**
	 * Strategy used to identify the main {@link MediaContainerModel} related to the updated {@link ProductModel}. <br />
	 * It's necessary because a single {@link ProductModel} may have several {@link MediaContainerModel} and every
	 * project may have it's own way to consider the container the main one.
	 */
	private FindMainMediaContainerStrategy findMainMediaContainerStrategy;

	/**
	 * Strategy used to find all {@link MediaContainerModel} related to the updated {@link ProductModel}
	 */
	private FindMediaContainersStrategy findMediaContainersStrategy;

	/**
	 * Mapping of the image format with the image qualifier
	 */
	private ImageFormatMapping imageFormatMapping;

	/**
	 * Media container service, used to manipulate container
	 */
	private MediaContainerService mediaContainerService;

	/**
	 * Media service, used to manipulate medias
	 */
	private MediaService mediaService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fliegersoftware.media.strategies.ProductGalleryChangedStrategy#execute(com.fliegersoftware.media.events.
	 * GalleryChangedEvent)
	 */
	@Override
	public void execute(final GalleryChangedEvent event)
	{
		final ProductModel product = event.getProduct();

		addMediasFromMainContainer(product);

		addMediasFromAllContainers(product);
	}

	/**
	 * This method is used to setup the picture and thumbnail attributes of the {@link ProductModel}.<br/>
	 * Their values are retrieved from the main {@link MediaContainerModel} obtained by using the strategy
	 * {@link FindMainMediaContainerStrategy}. <br/>
	 * The {@link ImageFormatMapping} is used to identify the right {@link MediaModel} by it's format.
	 *
	 * @param product
	 *           The {@link ProductModel} to update the {@link MediaModel} attributes
	 */
	protected void addMediasFromMainContainer(final ProductModel product)
	{
		//First of all we need to retrieve the main media container, because the picture and thumbnail attributes are
		//used by hmc, cockpit, etc.. so we need to obtain them from some main container among all other product containers.
		final MediaContainerModel mainMediaContainer = getFindMainMediaContainerStrategy().getMainMediaContainer(product);

		if (mainMediaContainer == null)
		{
			LOG.debug(String.format("Product %s has no main media container", product.getCode()));
			return;
		}

		LOG.debug(String.format("Product %s main media container is: %s", product.getCode(), mainMediaContainer.getQualifier()));

		//we need to check if the container is eligible for using, so the method below has the rules to do such validations.
		if (!isContainerReadyToUse(mainMediaContainer))
		{
			LOG.debug(String.format("Container %s is not ready to be used ", mainMediaContainer.getQualifier()));
			return;
		}

		final Set<Entry<String, String>> entrySet = getMediasFromMainContainerFormatMapping().entrySet();

		for (final Entry<String, String> entry : entrySet)
		{
			final MediaModel mediaFormat = getMediaByMediaFormat(mainMediaContainer, getMediaFormatByImageFormat(entry.getValue()));

			callSetMethod(product, entry.getKey(), mediaFormat, MediaModel.class);
		}
	}

	/**
	 * Used to setup the thumbnails, others and normal attributes of the {@link ProductModel}.<br />
	 * Their values are retrieved from all {@link MediaContainerModel} items related to the given {@link ProductModel},
	 * to obtain all the containers we use the strategy {@link FindMediaContainersStrategy}.<br />
	 * Those attributes are of type {@link List} of {@link MediaModel}, so we gather all {@link MediaModel} from all
	 * {@link MediaContainerModel} using the {@link ImageFormatMapping} to filter the {@link MediaModel} items by their
	 * format.<br/>
	 *
	 * @param product
	 *           The {@link ProductModel} to update the {@link MediaModel} attributes
	 */
	protected void addMediasFromAllContainers(final ProductModel product)
	{
		//first we obtain all containers using the strategy below
		final Collection<MediaContainerModel> containers = getFindMediaContainersStrategy().getMediaContainers(product);

		if (CollectionUtils.isEmpty(containers))
		{
			LOG.debug(String.format("Product %s has no media containers", product.getCode()));
			return;
		}

		//we need to check if the containers are eligible for using, so we use the filterContainers method to run
		//the containers list and validate them
		final Collection<MediaContainerModel> filteredContainers = filterContainers(containers);

		final Set<Entry<String, List<String>>> entrySet = getMediasFromAllContainersFormatMapping().entrySet();

		for (final Entry<String, List<String>> entry : entrySet)
		{
			final String attribute = entry.getKey();
			final List<String> imageFormats = entry.getValue();

			final List<MediaModel> collectedMedias = new ArrayList<MediaModel>();

			for (final MediaContainerModel mediaContainer : filteredContainers)
			{
				collectMedias(collectedMedias, mediaContainer, imageFormats);
			}

			callSetMethod(product, attribute, collectedMedias, Collection.class);
		}
	}

	/**
	 *
	 * @param medias
	 * @param container
	 * @param imageFormats
	 */
	private void collectMedias(final List<MediaModel> medias, final MediaContainerModel container, final List<String> imageFormats)
	{
		for (final String imageFormat : imageFormats)
		{
			final String mediaFormat = getMediaFormatByImageFormat(imageFormat);
			final MediaModel media = getMediaByMediaFormat(container, mediaFormat);
			medias.add(media);
		}
	}

	//Alterado visibilidade
	public void callSetMethod(final ProductModel product, final String attribute, final Object value, final Class<?> paramClass)
	{
		final String methodName = "set" + StringUtils.capitalize(attribute);

		final Method method = ReflectionUtils.findMethod(ProductModel.class, methodName, paramClass);

		ReflectionUtils.invokeMethod(method, product, value);
	}

	/**
	 * Used to remove all the containers that are not eligible to use
	 *
	 * @param containers
	 *           {@link MediaContainerModel} list that will be filtered
	 * @return All valid {@link MediaContainerModel} from the original list
	 */
	protected Collection<MediaContainerModel> filterContainers(final Collection<MediaContainerModel> containers)
	{
		final List<MediaContainerModel> list = new ArrayList<>(containers);

		CollectionUtils.filter(list, new Predicate()
		{
			@Override
			public boolean evaluate(final Object obj)
			{
				final MediaContainerModel container = (MediaContainerModel) obj;

				return isContainerReadyToUse(container);
			}
		});

		return list;
	}

	/**
	 * This method validates if the given {@link MediaContainerModel} is valid to use
	 *
	 * @param container
	 *           The {@link MediaContainerModel} to be validated
	 * @return
	 */
	protected boolean isContainerReadyToUse(final MediaContainerModel container)
	{
		final ConversionStatus conversionStatus = container.getConversionStatus();

		return conversionStatus.equals(ConversionStatus.CONVERTED);
	}

	/**
	 * This method searches for a {@link MediaModel} inside the given {@link MediaContainerModel} by the informed
	 * imageFormat, using the {@link ImageFormatMapping}
	 *
	 * @param mediaContainer
	 *           the container that contains the medias to search for
	 * @param mediaFormat
	 *           the mediaFormat to filter the medias of the container
	 * @return The found {@link MediaModel} or null if not found.
	 */
	protected MediaModel getMediaByMediaFormat(final MediaContainerModel mediaContainer, final String mediaFormat)
	{
		try
		{
			final MediaFormatModel format = getMediaService().getFormat(mediaFormat);
			if (mediaFormat == null)
			{
				LOG.debug(String.format("MediaFormat not found for qualifier %s", mediaFormat));
				return null;
			}

			return getMediaContainerService().getMediaForFormat(mediaContainer, format);
		}
		catch (final ModelNotFoundException ignore)
		{
			// Ignore
		}

		return null;
	}

	/**
	 *
	 * @param imageFormat
	 * @return
	 */
	protected String getMediaFormatByImageFormat(final String imageFormat)
	{
		final String mediaFormat = getImageFormatMapping().getMediaFormatQualifierForImageFormat(imageFormat);

		if (mediaFormat == null)
		{
			LOG.debug(String.format("Media format qualifier not found for image format %s", imageFormat));
			return null;
		}

		return mediaFormat;
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

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.fliegersoftware.media.strategies.ProductGalleryChangedListenerStrategy#getMediasFromMainContainerFormatMapping
	 * ()
	 */
	@Override
	public Map<String, String> getMediasFromMainContainerFormatMapping()
	{
		return mediasFromMainContainerFormatMapping;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.fliegersoftware.media.strategies.ProductGalleryChangedListenerStrategy#setMediasFromMainContainerFormatMapping
	 * (java.util.Map)
	 */
	@Override
	@Required
	public void setMediasFromMainContainerFormatMapping(final Map<String, String> mediasFromMainContainerFormatMapping)
	{
		this.mediasFromMainContainerFormatMapping = mediasFromMainContainerFormatMapping;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.fliegersoftware.media.strategies.ProductGalleryChangedListenerStrategy#getMediasFromAllContainersFormatMapping
	 * ()
	 */
	@Override
	public Map<String, List<String>> getMediasFromAllContainersFormatMapping()
	{
		return mediasFromAllContainersFormatMapping;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.fliegersoftware.media.strategies.ProductGalleryChangedListenerStrategy#setMediasFromAllContainersFormatMapping
	 * (java.util.Map)
	 */
	@Override
	@Required
	public void setMediasFromAllContainersFormatMapping(final Map<String, List<String>> mediasFromAllContainersFormatMapping)
	{
		this.mediasFromAllContainersFormatMapping = mediasFromAllContainersFormatMapping;
	}
}