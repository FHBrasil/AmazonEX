package com.hybris.addon.common.strategies.impl;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.addon.common.strategies.PreviewContextInformationLoaderStrategy;

import de.hybris.platform.acceleratorservices.uiexperience.UiExperienceService;
import de.hybris.platform.cms2.model.preview.PreviewDataModel;


/**
 * author: dariusz.malachowski
 */
public class UiExperienceLevelPreviewStrategy implements PreviewContextInformationLoaderStrategy
{

	private UiExperienceService uiExperienceService;

	@Override
	public void initContextFromPreview(final PreviewDataModel preview)
	{
		getUiExperienceService().setDetectedUiExperienceLevel(preview.getUiExperience());
	}

	public UiExperienceService getUiExperienceService()
	{
		return uiExperienceService;
	}

	@Required
	public void setUiExperienceService(final UiExperienceService uiExperienceService)
	{
		this.uiExperienceService = uiExperienceService;
	}
}
