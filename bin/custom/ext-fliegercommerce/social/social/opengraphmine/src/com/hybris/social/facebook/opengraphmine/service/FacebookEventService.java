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
package com.hybris.social.facebook.opengraphmine.service;

import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;

import com.hybris.social.facebook.common.model.FacebookApplicationModel;
import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;


/**
 * @author rmcotton
 * 
 */
public interface FacebookEventService
{
	void syncProfile(Connection<Facebook> connection, FacebookApplicationModel currentApp, FacebookUserModel facebookUser,
			boolean force);

	void like(Connection<Facebook> connection, FacebookApplicationModel currentApp, FacebookUserModel facebookUser,
			final String url);

	void unlike(Connection<Facebook> connection, FacebookApplicationModel currentApp, FacebookUserModel facebookUser,
			final String url);
}
