/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *  
 */
package com.pixi.webservices.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.filter.OncePerRequestFilter;

import de.hybris.platform.acceleratorcms.context.ContextInformationLoader;
import de.hybris.platform.cms2.misc.CMSFilter;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.jalo.JaloObjectNoLongerValidException;
import de.hybris.platform.jalo.c2l.LocalizableItem;
import de.hybris.platform.servicelayer.model.AbstractItemModel;
import de.hybris.platform.servicelayer.session.SessionService;


public class PixiSiteFilter extends OncePerRequestFilter implements CMSFilter
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(PixiSiteFilter.class);

	protected static final int MISSING_CMS_SITE_ERROR_STATUS = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
	protected static final String MISSING_CMS_SITE_ERROR_MESSAGE = "Cannot find CMSSite associated with current URL";
	protected static final String INCORRECT_CMS_SITE_CHANNEL_ERROR_MESSAGE = "Matched CMSSite for current URL has unsupported channel";

	private CMSSiteService cmsSiteService;
	private SessionService sessionService;
	private ContextInformationLoader contextInformationLoader;

	@Override
	protected void doFilterInternal(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse,
			final FilterChain filterChain) throws ServletException, IOException
	{
		// process normal request (i.e. normal browser non-cmscockpit request)
		if (processNormalRequest(httpRequest, httpResponse))
		{
			// proceed filters
			filterChain.doFilter(httpRequest, httpResponse);
		}
	}

	/**
	 * Processing normal request (i.e. when user goes directly to that application - not from cmscockpit)
	 * <p/>
	 * <b>Note:</b> <br/>
	 * We preparing application by setting correct:
	 * <ul>
	 * <li>Current Site</li>
	 * <li>Current Catalog Versions</li>
	 * <li>Enabled language fallback</li>
	 * </ul>
	 * 
	 * @see ContextInformationLoader#initializeSiteFromRequest(String)
	 * @see ContextInformationLoader#setCatalogVersions()
	 * @param httpRequest
	 *           current request
	 * @param httpResponse
	 *           the http response
	 * @throws java.io.IOException
	 */
	protected boolean processNormalRequest(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse)
			throws IOException
	{
		final String queryString = httpRequest.getQueryString();
		final String currentRequestURL = httpRequest.getRequestURL().toString();

		//set current site
		CMSSiteModel cmsSiteModel = getCurrentCmsSite();
		if (cmsSiteModel == null || StringUtils.contains(queryString, CLEAR_CMSSITE_PARAM))
		{
			final String absoluteURL = StringUtils.removeEnd(currentRequestURL, "/")
					+ (StringUtils.isBlank(queryString) ? "" : "?" + queryString);

			cmsSiteModel = getContextInformationLoader().initializeSiteFromRequest(absoluteURL);
		}

		if (cmsSiteModel == null)
		{
			// Failed to lookup CMS site
			httpResponse.sendError(MISSING_CMS_SITE_ERROR_STATUS, MISSING_CMS_SITE_ERROR_MESSAGE);
			return false;
		}
		else if (!SiteChannel.B2C.equals(cmsSiteModel.getChannel())) // Restrict to B2C channel
		{
			// CMS site that we looked up was for an unsupported channel
			httpResponse.sendError(MISSING_CMS_SITE_ERROR_STATUS, INCORRECT_CMS_SITE_CHANNEL_ERROR_MESSAGE);
			return false;
		}

		if (!isActiveSite(cmsSiteModel))
		{
			throw new IllegalStateException("Site is not active. Active flag behaviour must be implement for this project.");
		}

		getContextInformationLoader().setCatalogVersions();
		//set fall back language enabled
		setFallbackLanguage(httpRequest, Boolean.TRUE);

		return true;
	}

	protected boolean isActiveSite(final CMSSiteModel site)
	{
		return site.getActive() != null && site.getActive().booleanValue();
	}

	/**
	 * Enables or disables language fall back
	 * <p/>
	 * 
	 * @param httpRequest
	 *           current request
	 * @param enabled
	 *           enabled or disabled
	 */
	protected void setFallbackLanguage(final HttpServletRequest httpRequest, final Boolean enabled)
	{
		final SessionService sessionService = getSessionService();
		if (sessionService != null)
		{
			sessionService.setAttribute(LocalizableItem.LANGUAGE_FALLBACK_ENABLED, enabled);
			sessionService.setAttribute(AbstractItemModel.LANGUAGE_FALLBACK_ENABLED_SERVICE_LAYER, enabled);
		}
	}

	protected CMSSiteService getCmsSiteService()
	{
		return cmsSiteService;
	}

	@Required
	public void setCmsSiteService(final CMSSiteService cmsSiteService)
	{
		this.cmsSiteService = cmsSiteService;
	}

	protected SessionService getSessionService()
	{
		return sessionService;
	}

	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	protected ContextInformationLoader getContextInformationLoader()
	{
		return contextInformationLoader;
	}

	@Required
	public void setContextInformationLoader(final ContextInformationLoader contextInformationLoader)
	{
		this.contextInformationLoader = contextInformationLoader;
	}

	protected CMSSiteModel getCurrentCmsSite()
	{
		try
		{
			return getCmsSiteService().getCurrentSite();
		}
		catch (final JaloObjectNoLongerValidException ignore)
		{
			return null;
		}
	}
}