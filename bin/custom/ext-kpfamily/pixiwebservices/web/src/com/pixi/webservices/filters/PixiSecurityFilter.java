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

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pixi.webservices.controller.auth.PixiTokenUtils;

import de.hybris.platform.cms2.misc.CMSFilter;


public class PixiSecurityFilter extends OncePerRequestFilter implements CMSFilter
{
	private static final Logger LOG = Logger.getLogger(PixiSecurityFilter.class);

	@Resource
	private AuthenticationManager pixiAuthManager;
	
	@Resource
	private PixiTokenUtils pixiTokenUtils;
	
	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain chain) throws ServletException, IOException
	{
        if(StringUtils.isNotBlank(request.getParameter("session"))) 
        {
            final String token = request.getParameter("session");
            
			try 
			{
				if(pixiTokenUtils.validateToken(token)) 
				{
					User u = pixiTokenUtils.getTokenUser();
					
					UsernamePasswordAuthenticationToken authentication =  new UsernamePasswordAuthenticationToken(u.getUsername(), u.getPassword());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
					
					Authentication authenticate = pixiAuthManager.authenticate(authentication);
					
					SecurityContextHolder.getContext().setAuthentication(authenticate);   
				}
			} 
			catch (final Exception e) 
			{
				LOG.info("Failed to validate token", e);
			}
        }
        
        chain.doFilter(request, response);
	}
}