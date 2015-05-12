/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 * 
 */
package com.hybris.social.facebook.opengraphmine.service.converter.populator;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.ImageType;

import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;


/**
 * @author rmcotton
 * 
 */
public class FacebookMediaPopulator implements Populator<Connection<Facebook>, FacebookUserModel>
{

	private MediaService mediaService;
	private ModelService modelService;
	private CatalogVersionService catalogVersionService;

	private final String mediaCatalogId = "Default";
	private final String mediaCatalogVersion = "Staged";

	private boolean updateOnlyWhenNull = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.commerceservices.converter.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final Connection<Facebook> source, final FacebookUserModel target) throws ConversionException
	{
		processNormalImage(source, target);
		processSmallImage(source, target);
	}

	protected void processNormalImage(final Connection<Facebook> source, final FacebookUserModel target)
	{
		MediaModel profilePicture = target.getProfilePicture();

		if (isUpdateOnlyWhenNull() && profilePicture != null)
		{
			return;
		}

		final byte[] rawimage = source.getApi().userOperations().getUserProfileImage();
		if (rawimage == null)
		{
			target.setProfilePicture(null);
			return;
		}

		if (profilePicture == null)
		{
			profilePicture = getModelService().create(MediaModel.class);
			profilePicture.setCode(target.getUid() + "-facebook-profilepic-" + System.currentTimeMillis());
			profilePicture.setCatalogVersion(getCatalogVersionService().getCatalogVersion(getMediaCatalogId(),
					getMediaCatalogVersion()));
			target.setProfilePicture(profilePicture);
			getModelService().save(profilePicture);
		}
		else
		{
			final byte[] currentMedia = getMediaService().getDataFromMedia(profilePicture);
			if (currentMedia != null && Arrays.equals(rawimage, currentMedia))
			{
				// same image
				return;
			}
		}

		profilePicture.setAltText(target.getName());
		getMediaService().setDataForMedia(profilePicture, rawimage);
		getModelService().save(profilePicture);
	}

	protected void processSmallImage(final Connection<Facebook> source, final FacebookUserModel target)
	{
		MediaModel profilePicture = target.getSmallProfilePicture();

		if (isUpdateOnlyWhenNull() && profilePicture != null)
		{
			return;
		}

		final byte[] rawimage = source.getApi().userOperations().getUserProfileImage(ImageType.SQUARE);
		if (rawimage == null)
		{
			target.setSmallProfilePicture(null);
			return;
		}

		if (profilePicture == null)
		{
			profilePicture = getModelService().create(MediaModel.class);
			profilePicture.setCode(target.getUid() + "-facebook-smallprofilepic-" + System.currentTimeMillis());
			profilePicture.setCatalogVersion(getCatalogVersionService().getCatalogVersion(getMediaCatalogId(),
					getMediaCatalogVersion()));
			target.setSmallProfilePicture(profilePicture);
			getModelService().save(profilePicture);
		}
		else
		{
			final byte[] currentMedia = getMediaService().getDataFromMedia(profilePicture);
			if (currentMedia != null && Arrays.equals(rawimage, currentMedia))
			{
				// same image
				return;
			}
		}

		profilePicture.setAltText(target.getName());
		getMediaService().setDataForMedia(profilePicture, rawimage);
		getModelService().save(profilePicture);
	}

	@Required
	public void setMediaService(final MediaService mediaService)
	{
		this.mediaService = mediaService;
	}


	public MediaService getMediaService()
	{
		return this.mediaService;
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
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param catalogVersionService
	 *           the catalogVersionService to set
	 */
	@Required
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}

	/**
	 * @return the catalogVersionService
	 */
	public CatalogVersionService getCatalogVersionService()
	{
		return catalogVersionService;
	}

	/**
	 * @return the mediaCatalogId
	 */
	public String getMediaCatalogId()
	{
		return mediaCatalogId;
	}

	/**
	 * @return the mediaCatalogVersion
	 */
	public String getMediaCatalogVersion()
	{
		return mediaCatalogVersion;
	}

	/**
	 * @param updateOnlyIfNull
	 *           the updateOnlyIfNull to set
	 */
	public void setUpdateOnlyWhenNull(final boolean updateOnlyIfNull)
	{
		this.updateOnlyWhenNull = updateOnlyIfNull;
	}

	/**
	 * @return the updateOnlyIfNull
	 */
	public boolean isUpdateOnlyWhenNull()
	{
		return updateOnlyWhenNull;
	}

}
