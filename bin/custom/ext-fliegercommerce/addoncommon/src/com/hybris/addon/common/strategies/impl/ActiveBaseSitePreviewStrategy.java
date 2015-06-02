package com.hybris.addon.common.strategies.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.addon.common.strategies.PreviewContextInformationLoaderStrategy;

import de.hybris.platform.cms2.model.preview.PreviewDataModel;
import de.hybris.platform.site.BaseSiteService;


/**
 * author: dariusz.malachowski
 */
public class ActiveBaseSitePreviewStrategy implements PreviewContextInformationLoaderStrategy
{
	private static final Logger LOG = Logger.getLogger(ActiveBaseSitePreviewStrategy.class);
	private BaseSiteService baseSiteService;

	@Override
	public void initContextFromPreview(final PreviewDataModel preview)
	{
		final BaseSiteService baseSiteService = getBaseSiteService();
		if (preview.getActiveSite() == null)
		{
			LOG.warn("Could not set active site. Reason: No active site was selected!");
		}
		else
		{
			baseSiteService.setCurrentBaseSite(preview.getActiveSite(), true);
		}
	}

	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}
}
