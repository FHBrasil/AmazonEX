/**
 *
 */
package com.fliegersoftware.media.services.impl;

import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.mediaconversion.conversion.DefaultMediaConversionService;

import org.apache.log4j.Logger;


/**
 * @author franthescollymaneira
 *
 */
public class MediaImportationMediaConversionService extends DefaultMediaConversionService
{
	private static final Logger LOG = Logger.getLogger(MediaImportationMediaConversionService.class);

	@Override
	protected MediaModel selectMasterFromUnsavedContainer(final MediaContainerModel model)
	{
		MediaModel master = null;
		int masterMatches = 0;
		if (model.getMedias() != null)
		{
			for (final MediaModel media : model.getMedias())
			{
				if ((media.getOriginal() != null) || (media.getOriginalDataPK() != null))
				{
					continue;
				}

				if (master == null)
				{
					master = media;
				}

				masterMatches++;
			}
		}

		if (master == null)
		{
			LOG.debug("No master media found in unsaved container '" + model + "'.");
		}

		if (masterMatches > 1)
		{
			LOG.debug(String.format("MediaContainer %s has %s possible Master media items, returning the first one",
					model.getQualifier(), String.valueOf(masterMatches)));
		}

		return master;
	}
}