/**
 *
 */
package com.fliegersoftware.media.strategies.impl;

import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.mediaconversion.MediaConversionService;
import de.hybris.platform.mediaconversion.enums.ConversionStatus;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.fliegersoftware.media.strategies.ConvertMediaContainerMediasStrategy;


/**
 * Default implementation of the {@link ConvertMediaContainerMediasStrategy}
 * 
 * @author Franthescolly Maneira
 *
 */
public class DefaultConvertMediaContainerMediasStrategy implements ConvertMediaContainerMediasStrategy
{
	private static final Logger LOG = Logger.getLogger(DefaultConvertMediaContainerMediasStrategy.class);

	private MediaConversionService mediaConversionService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.fliegersoftware.media.strategies.ConvertMediaContainerMediasStrategy#convertMedias(de.hybris.platform.core
	 * .model.media.MediaContainerModel)
	 */
	@Override
	public void convertMedias(final MediaContainerModel container)
	{
		if (ConversionStatus.CONVERTED.equals(container.getConversionStatus()))
		{
			return;
		}

		try
		{
			LOG.debug(String.format("Starting medias conversion process of MediaContainer %s", container.getQualifier()));
			getMediaConversionService().convertMedias(container);
		}
		catch (final Exception e)
		{
			LOG.error(String.format("Error converting medias of MediaContainer %s", container.getQualifier()));

			throw e;
		}
		finally
		{
			LOG.debug(String.format("Finished medias conversion process of MediaContainer %s", container.getQualifier()));
		}
	}

	/**
	 * @return the mediaConversionService
	 */
	public MediaConversionService getMediaConversionService()
	{
		return mediaConversionService;
	}

	/**
	 * @param mediaConversionService
	 *           the mediaConversionService to set
	 */
	@Required
	public void setMediaConversionService(final MediaConversionService mediaConversionService)
	{
		this.mediaConversionService = mediaConversionService;
	}
}