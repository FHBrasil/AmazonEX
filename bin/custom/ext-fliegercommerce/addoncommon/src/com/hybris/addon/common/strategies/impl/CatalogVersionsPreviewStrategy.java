package com.hybris.addon.common.strategies.impl;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.addon.common.strategies.PreviewContextInformationLoaderStrategy;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.cms2.model.preview.PreviewDataModel;


/**
 * author: dariusz.malachowski
 */
public class CatalogVersionsPreviewStrategy implements PreviewContextInformationLoaderStrategy
{

	private CatalogVersionService catalogVersionService;

	@Override
	public void initContextFromPreview(final PreviewDataModel preview)
	{
		getCatalogVersionService().setSessionCatalogVersions(preview.getCatalogVersions());
	}

	public CatalogVersionService getCatalogVersionService()
	{
		return catalogVersionService;
	}

	@Required
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}
}
