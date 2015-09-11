/**
 * 
 */
package com.hybris.social.facebook.opengraphmine.security.impl;

import de.hybris.platform.acceleratorstorefrontcommons.security.GUIDCookieStrategy;
import de.hybris.platform.commercefacades.customer.CustomerFacade;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.context.request.NativeWebRequest;

import com.hybris.social.facebook.opengraphmine.jalo.user.FacebookLoginToken;
import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.security.FacebookAutoLoginStrategy;


/**
 * @author franthescollymaneira
 * 
 */
public class DefaultFacebookAutoLoginStrategy implements FacebookAutoLoginStrategy
{
	@Resource
	private AuthenticationManager authenticationManager;

	@Resource
	private GUIDCookieStrategy guidCookieStrategy;

	@Resource
	private RememberMeServices rememberMeServices;

	@Resource
	private CustomerFacade customerFacade;

	private final Logger LOG = Logger.getLogger(DefaultFacebookAutoLoginStrategy.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.social.facebook.opengraphmine.security.FacebookAutoLoginStrategy#login(com.hybris.social.facebook.
	 * opengraphmine.model.FacebookUserModel, org.springframework.web.context.request.NativeWebRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void login(final FacebookUserModel facebookUser, final NativeWebRequest nativeRequest,
			final HttpServletResponse response)
	{
		final HttpServletRequest request = nativeRequest.getNativeRequest(HttpServletRequest.class);
		final String uid = facebookUser.getLinkedCustomer().getUid();
		final FacebookLoginToken credentials = new FacebookLoginToken(facebookUser);

		final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(uid, credentials);
		token.setDetails(new WebAuthenticationDetails(request));

		try
		{
			final Authentication authentication = authenticationManager.authenticate(token);

			SecurityContextHolder.getContext().setAuthentication(authentication);

			customerFacade.loginSuccess();
			guidCookieStrategy.setCookie(request, response);
			rememberMeServices.loginSuccess(request, response, token);
		}
		catch (final Exception e)
		{
			SecurityContextHolder.getContext().setAuthentication(null);
			LOG.error("Failure during autoLogin", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.social.facebook.opengraphmine.security.FacebookAutoLoginStrategy#login(com.hybris.social.facebook.
	 * opengraphmine.model.FacebookUserModel, org.springframework.web.context.request.NativeWebRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void login(final FacebookUserModel facebookUser, final HttpServletRequest request, final HttpServletResponse response)
	{
		//		final HttpServletRequest request = nativeRequest.getNativeRequest(HttpServletRequest.class);
		final String uid = facebookUser.getLinkedCustomer().getUid();
		final FacebookLoginToken credentials = new FacebookLoginToken(facebookUser);

		final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(uid, credentials);
		token.setDetails(new WebAuthenticationDetails(request));

		try
		{
			final Authentication authentication = authenticationManager.authenticate(token);

			SecurityContextHolder.getContext().setAuthentication(authentication);

			customerFacade.loginSuccess();
			guidCookieStrategy.setCookie(request, response);
			rememberMeServices.loginSuccess(request, response, token);
		}
		catch (final Exception e)
		{
			SecurityContextHolder.getContext().setAuthentication(null);
			LOG.error("Failure during autoLogin", e);
		}
	}
}