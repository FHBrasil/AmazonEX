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
package com.hybris.social.facebook.opengraphmine.service.impl;

import de.hybris.platform.servicelayer.event.EventService;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;

import com.hybris.social.facebook.common.model.FacebookApplicationModel;
import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.process.event.FacebookLikeEvent;
import com.hybris.social.facebook.opengraphmine.process.event.FacebookProfileSyncEvent;
import com.hybris.social.facebook.opengraphmine.service.FacebookEventService;


/**
 * @author rmcotton
 * 
 */
public class DefaultFacebookEventService implements FacebookEventService
{

	private EventService eventService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.social.facebook.opengraphmine.service.FacebookEventService#syncProfile(org.springframework.social.connect
	 * .Connection, com.hybris.social.facebook.common.model.FacebookApplicationModel,
	 * com.hybris.social.facebook.opengraphmine.model.FacebookUserModel)
	 */
	@Override
	public void syncProfile(final Connection<Facebook> connection, final FacebookApplicationModel currentApp,
			final FacebookUserModel facebookUser, final boolean force)
	{
		getEventService().publishEvent(
				new FacebookProfileSyncEvent(connection.createData(), facebookUser, currentApp.getApplicationId(), currentApp
						.getApplicationSecret(), force));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.social.facebook.opengraphmine.service.FacebookEventService#like(org.springframework.social.connect.
	 * Connection, com.hybris.social.facebook.common.model.FacebookApplicationModel, java.lang.String)
	 */
	@Override
	public void like(final Connection<Facebook> connection, final FacebookApplicationModel currentApp,
			final FacebookUserModel facebookUser, final String url)
	{
		getEventService().publishEvent(
				new FacebookLikeEvent(connection.createData(), facebookUser, currentApp.getApplicationId(), currentApp
						.getApplicationSecret(), FacebookLikeEvent.TYPE.LIKE));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.social.facebook.opengraphmine.service.FacebookEventService#like(org.springframework.social.connect.
	 * Connection, com.hybris.social.facebook.common.model.FacebookApplicationModel, java.lang.String)
	 */
	@Override
	public void unlike(final Connection<Facebook> connection, final FacebookApplicationModel currentApp,
			final FacebookUserModel facebookUser, final String url)
	{
		getEventService().publishEvent(
				new FacebookLikeEvent(connection.createData(), facebookUser, currentApp.getApplicationId(), currentApp
						.getApplicationSecret(), FacebookLikeEvent.TYPE.UNLIKE));

	}


	/**
	 * @param eventService
	 *           the eventService to set
	 */
	@Required
	public void setEventService(final EventService eventService)
	{
		this.eventService = eventService;
	}


	/**
	 * @return the eventService
	 */
	public EventService getEventService()
	{
		return eventService;
	}



}
