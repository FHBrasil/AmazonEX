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
package com.hybris.social.facebook.opengraphmine.process.actions;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import com.hybris.social.facebook.opengraphmine.model.FacebookConnectionModel;
import com.hybris.social.facebook.opengraphmine.model.process.FacebookSynchronizationProcessModel;


/**
 * @author rmcotton
 * @author fbieg
 * 
 */
public class FacebookSyncAction extends AbstractPluggableFacebookSyncAction<Connection<Facebook>>
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.social.jalo.facebook.process.actions.AbstractPluggableFacebookSyncAction#getSource(de.hybris
	 * .platform.social.model.facebook.process.FacebookSynchronisationProcessModel)
	 */

	@Override
	protected Connection<Facebook> getSource(final FacebookSynchronizationProcessModel processModel)
	{
		final FacebookConnectionModel con = processModel.getConnection();
		final ConnectionData connectionData = new ConnectionData(con.getProviderId(), con.getProviderUserId(),
				con.getDisplayName(), con.getProfileUrl(), con.getImageUrl(), con.getAccessToken(), con.getSecret(),
				con.getRefreshToken(), con.getExpireTime());

		final FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(processModel.getApplicationId()
				.toString(), processModel.getApplicationSecret());

		return connectionFactory.createConnection(connectionData);
	}
}
