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
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Location;
import org.springframework.social.facebook.api.Page;

import com.hybris.social.facebook.opengraphmine.model.FacebookLocationModel;
import com.hybris.social.facebook.opengraphmine.model.FacebookPageModel;


/**
 * @author rmcotton
 * 
 */
public class FacebookPagePopulator implements Populator<Connection<Facebook>, FacebookPageModel>
{
	private Converter<Location, FacebookLocationModel> locationConverter;
	private List<Populator<Page, FacebookPageModel>> pagePopulators;


	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.commerceservices.converter.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final Connection<Facebook> source, final FacebookPageModel target) throws ConversionException
	{
		assert target.getId() != null : "target page must have an id";

		final Page page = source.getApi().pageOperations().getPage(target.getId());
		populateGeneral(source, page, target);
		populateLinks(source, page, target);
		populateMetrics(source, page, target);
		if (page.getLocation() != null)
		{
			if (target.getLocation() != null)
			{
				getLocationConverter().convert(page.getLocation(), target.getLocation());
			}
			else
			{
				target.setLocation(getLocationConverter().convert(page.getLocation()));
			}
		}

		if (getPagePopulators() != null)
		{
			for (final Populator<Page, FacebookPageModel> populator : getPagePopulators())
			{
				populator.populate(page, target);
			}
		}

	}

	protected void populateGeneral(final Connection<Facebook> connection, final Page page, final FacebookPageModel target)
	{
		target.setCategory(page.getCategory());
		target.setName(page.getName());
		target.setDescription(page.getDescription());
	}

	protected void populateLinks(final Connection<Facebook> connection, final Page page, final FacebookPageModel target)
	{
		if (StringUtils.isNotBlank(page.getLink()))
		{
			target.setLink(page.getLink());
		}
		target.setWebsiteLink(page.getWebsite());
	}

	protected void populateMetrics(final Connection<Facebook> connection, final Page page, final FacebookPageModel target)
	{
		target.setCheckins(Integer.valueOf(page.getCheckins()));
		target.setLikes(Integer.valueOf(page.getLikes()));
		target.setTalkingAboutCount(Integer.valueOf(page.getTalkingAboutCount()));
	}


	/**
	 * @param locationConverter
	 *           the locationConverter to set
	 */
	@Required
	public void setLocationConverter(final Converter<Location, FacebookLocationModel> locationConverter)
	{
		this.locationConverter = locationConverter;
	}

	/**
	 * @return the locationConverter
	 */
	public Converter<Location, FacebookLocationModel> getLocationConverter()
	{
		return locationConverter;
	}

	/**
	 * @param pagePopulators
	 *           the pagePopulators to set
	 */
	@Required
	public void setPagePopulators(final List<Populator<Page, FacebookPageModel>> pagePopulators)
	{
		this.pagePopulators = pagePopulators;
	}

	/**
	 * @return the pagePopulators
	 */
	public List<Populator<Page, FacebookPageModel>> getPagePopulators()
	{
		return pagePopulators;
	}


}
