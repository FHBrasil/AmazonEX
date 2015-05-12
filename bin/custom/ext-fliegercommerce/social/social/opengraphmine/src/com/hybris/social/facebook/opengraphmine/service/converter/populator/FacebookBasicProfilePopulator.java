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


import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;

import com.hybris.social.facebook.opengraphmine.constants.OpengraphmineConstants;
import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;


/**
 * @author rmcotton
 * 
 */
public class FacebookBasicProfilePopulator implements Populator<Connection<Facebook>, FacebookUserModel>
{
	private static final Logger LOG = Logger.getLogger(FacebookBasicProfilePopulator.class);

	/**
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.servicelayer.dto.converter.Converter#convert(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final Connection<Facebook> source, final FacebookUserModel prototype) throws ConversionException
	{

		final FacebookProfile profile = source.getApi().userOperations().getUserProfile();

		prototype.setUid(profile.getId());
		prototype.setFacebookUpdatedTime(profile.getUpdatedTime());
		prototype.setProfileLink(profile.getLink());

		updateEmail(profile, prototype);
		updateLocale(profile, prototype);
		updateName(profile, prototype);
		updateGender(profile, prototype);
		updateBirthday(profile, prototype);
	}


	protected void updateName(final FacebookProfile source, final FacebookUserModel prototype)
	{
		prototype.setName(source.getName());
		prototype.setFirstname(source.getFirstName());
		prototype.setLastname(source.getLastName());
	}

	protected void updateGender(final FacebookProfile source, final FacebookUserModel prototype)
	{
		if (StringUtils.isBlank(source.getGender()))
		{
			prototype.setGender(null);
		}
		else
		{
			if (StringUtils.equalsIgnoreCase("MALE", source.getGender()))
			{
				prototype.setGender(Gender.MALE);
			}
			else if (StringUtils.equalsIgnoreCase("FEMALE", source.getGender()))
			{
				prototype.setGender(Gender.FEMALE);
			}
			else
			{
				LOG.warn("unable to map gender value [" + source.getGender() + "] to a Gender");
				prototype.setGender(null);
			}

		}
	}

	protected void updateBirthday(final FacebookProfile profile, final FacebookUserModel userModel)
	{
		final String birthdayStr = profile.getBirthday();
		Date birthdate = null;

		if (StringUtils.isNotBlank(birthdayStr))
		{
			final SimpleDateFormat dateformatter = new SimpleDateFormat(OpengraphmineConstants.FB_BORTHDAY_DATE_FORMAT);

			try
			{
				birthdate = dateformatter.parse(birthdayStr);
			}
			catch (final ParseException e)
			{
				LOG.warn("unable to parse birthday received from facebook  [" + birthdayStr + "] to a java.util.Date", e);
			}
		}

		userModel.setBirthday(birthdate);
	}

	protected void updateEmail(final FacebookProfile source, final FacebookUserModel prototype)
	{
		prototype.setEmail(source.getEmail());
	}

	protected void updateLocale(final FacebookProfile source, final FacebookUserModel prototype)
	{
		if (source.getLocale() != null)
		{
			prototype.setLocale(source.getLocale().toString());
		}
		else
		{
			prototype.setLocale(null);
		}
	}


}
