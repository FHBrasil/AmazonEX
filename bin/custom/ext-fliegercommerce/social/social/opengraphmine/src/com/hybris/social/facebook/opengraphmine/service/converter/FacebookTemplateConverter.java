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
package com.hybris.social.facebook.opengraphmine.service.converter;

import de.hybris.platform.converters.impl.AbstractPopulatingConverter;

import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;

import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;


/**
 * @author rmcotton
 * 
 */
public class FacebookTemplateConverter extends AbstractPopulatingConverter<Connection<Facebook>, FacebookUserModel>
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.commerceservices.converter.impl.AbstractConverter#createTarget()
	 */
	@Override
	protected FacebookUserModel createTarget()
	{
		return new FacebookUserModel();
	}



}
