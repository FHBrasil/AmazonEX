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
package com.hybris.social.facebook.opengraphmine.controllers.pages;


import de.hybris.platform.acceleratorservices.urlresolver.SiteBaseUrlResolutionService;
import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.GenericSearchConstants.LOG;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.connect.web.ConnectSupport;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import com.hybris.social.facebook.common.model.FacebookApplicationModel;
import com.hybris.social.facebook.common.service.FacebookApplicationService;
import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.security.FacebookAutoLoginStrategy;
import com.hybris.social.facebook.opengraphmine.service.FacebookAuthenticationService;
import com.hybris.social.facebook.opengraphmine.service.FacebookEventService;
import com.hybris.social.facebook.opengraphmine.service.exception.FacebookServiceException;


/**
 * Controller for facebook OAuth2 authentication
 * 
 * (Adapted Spring Social Connect Controller)
 * 
 * @author franciszek.bieg
 * @author rmcotton
 */
@Controller
@RequestMapping("/facebook")
public class FacebookConnectController extends AbstractAddOnPageController
{
	private static Logger LOG = Logger.getLogger(FacebookConnectController.class.getName());

	@Autowired
	private UserService userService;

	@Autowired
	private FacebookApplicationService facebookApplicationService;

	@Autowired
	private SiteBaseUrlResolutionService siteBaseUrlResolutionService;

	@Autowired
	private BaseSiteService baseSiteService;

	@Autowired
	FacebookAuthenticationService hybrisFacebookAuthenticationService;

	@Autowired
	FacebookEventService facebookEventService;

	private final ConnectSupport webSupport = new ConnectSupport();

	//XXX FB-HRG
	@Resource
	private FacebookAutoLoginStrategy facebookAutoLoginStrategy;

	/**
	 * Process a connect form submission by commencing the process of establishing a connection to the provider on behalf
	 * of the member. For OAuth1, fetches a new request token from the provider, temporarily stores it in the session,
	 * then redirects the member to the provider's site for authorization. For OAuth2, redirects the user to the
	 * provider's site for authorization.
	 */
	@ResponseBody
	@RequestMapping(value = "/connect", method = RequestMethod.POST)
	public String connect(final NativeWebRequest request)
	{
		final FacebookApplicationModel currentApplication = facebookApplicationService.getCurrentApplication();

		if (currentApplication == null)
		{
			LOG.error("No facebook application. Check facebook application configuration");
			return "";
		}
		final FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(currentApplication.getApplicationId()
				.toString(), currentApplication.getApplicationSecret());

		final MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();

		final BaseSiteModel currentBaseSite = getBaseSiteService().getCurrentBaseSite();
		final String websiteUrlForSite = siteBaseUrlResolutionService.getWebsiteUrlForSite(currentBaseSite, true, "");
		webSupport.setApplicationUrl(websiteUrlForSite);
		try
		{
			return webSupport.buildOAuthUrl(connectionFactory, request, parameters);
		}
		catch (final Exception e)
		{
			request.setAttribute(PROVIDER_ERROR_ATTRIBUTE, e, RequestAttributes.SCOPE_SESSION);
			return "";
		}
	}

	/**
	 * Process the authorization callback from an OAuth 2 service provider. Called after the user authorizes the
	 * connection, generally done by having he or she click "Allow" in their web browser at the provider's site. On
	 * authorization verification, connects the user's local account to the account they hold at the service provider.
	 */
	@RequestMapping(value = "/connect", method = RequestMethod.GET, params = "code")
	public String oauth2Callback(final NativeWebRequest request, final HttpServletResponse response, final Model model)
	{
		try
		{
			final FacebookApplicationModel currentApplication = facebookApplicationService.getCurrentApplication();

			final OAuth2ConnectionFactory<Facebook> connectionFactory = new FacebookConnectionFactory(currentApplication
					.getApplicationId().toString(), currentApplication.getApplicationSecret());
			final Connection<Facebook> connection = (Connection<Facebook>) webSupport.completeConnection(connectionFactory, request);

			hybrisFacebookAuthenticationService.authenticateHybrisUser(connection);

			//XXX FB-HRG
			final FacebookUserModel currentFacebookUser = hybrisFacebookAuthenticationService.getCurrentFacebookUser();
			if (currentFacebookUser != null)
			{
				final CustomerModel linkedCustomer = currentFacebookUser.getLinkedCustomer();
				if (linkedCustomer != null)
				{
					facebookAutoLoginStrategy.login(currentFacebookUser, request, response);
					model.addAttribute("redirUrl", "/store/my-account");
				}
				else
				{
					model.addAttribute("redirUrl", "/store/facebook/profile-facebook");
				}
			}
		}
		catch (final FacebookServiceException e)
		{
			LOG.error(e.getMessage());
		}
		catch (final Exception e)
		{
			request.setAttribute(PROVIDER_ERROR_ATTRIBUTE, e, RequestAttributes.SCOPE_SESSION);
			LOG.warn("Exception while handling OAuth2 callback (" + e.getMessage() + "). ");
		}
		return "addon:/opengraphmine/pages/facebookconnected";
	}

	@RequestMapping(value = "/connect", method = RequestMethod.GET, params = "error")
	public String oauth2FailedCallback(final NativeWebRequest request)
	{
		return "addon:/opengraphmine/pages/facebookfailed";
	}

	/**
	 * Remove a single provider connection associated with a user account. The user has decided they no longer wish to
	 * use the service provider account from this application. Note: requires {@link HiddenHttpMethodFilter} to be
	 * registered with the '_method' request parameter set to 'DELETE' to convert web browser POSTs to DELETE requests.
	 */
	@ResponseBody
	@RequestMapping(value = "/disconnect", method = RequestMethod.POST)
	public String removeConnection(final Model model, final HttpServletRequest request)
	{
		hybrisFacebookAuthenticationService.logout();

		return "";
	}


	@RequestMapping(value = "/like", method = RequestMethod.GET)
	public void like(@RequestParam(value = "accesstoken", required = true) final String accessToken,
			@RequestParam(value = "url", required = true) final String url)

	{
		final FacebookApplicationModel currentApplication = facebookApplicationService.getCurrentApplication();
		final OAuth2ConnectionFactory<Facebook> connectionFactory = new FacebookConnectionFactory(currentApplication
				.getApplicationId().toString(), currentApplication.getApplicationSecret());
		final Connection<Facebook> connection = connectionFactory.createConnection(new AccessGrant(accessToken));
		final FacebookUserModel facebookUser = hybrisFacebookAuthenticationService.getOrCreateFacebookUserForConnection(connection);
		facebookEventService.like(connection, currentApplication, facebookUser, url);
	}

	@RequestMapping(value = "/unlike", method = RequestMethod.GET)
	public void unlike(@RequestParam(value = "accesstoken", required = true) final String accessToken,
			@RequestParam(value = "url", required = true) final String url)

	{
		final FacebookApplicationModel currentApplication = facebookApplicationService.getCurrentApplication();
		final OAuth2ConnectionFactory<Facebook> connectionFactory = new FacebookConnectionFactory(currentApplication
				.getApplicationId().toString(), currentApplication.getApplicationSecret());
		final Connection<Facebook> connection = connectionFactory.createConnection(new AccessGrant(accessToken));
		final FacebookUserModel facebookUser = hybrisFacebookAuthenticationService.getOrCreateFacebookUserForConnection(connection);
		facebookEventService.unlike(connection, currentApplication, facebookUser, url);
	}



	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(final Model model, final NativeWebRequest request)
	{
		try
		{
			hybrisFacebookAuthenticationService.authenticateHybrisUser(null);
		}
		catch (final FacebookServiceException e)
		{
			LOG.error(e.getMessage());
		}
		return REDIRECT_PREFIX + "/";
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}


	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}


	private static final String PROVIDER_ERROR_ATTRIBUTE = "social.provider.error";
}
