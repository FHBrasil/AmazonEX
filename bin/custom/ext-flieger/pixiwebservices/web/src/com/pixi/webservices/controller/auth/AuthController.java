package com.pixi.webservices.controller.auth;

import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pixi.webservices.constants.PixiwebservicesConstants;
import com.pixi.webservices.controller.AbstractPixiController;
import com.pixi.webservices.jaxb.auth.AuthenticationResponse;


@Controller
public class AuthController extends AbstractPixiController
{
	private static final String ACTION = "session_start";
	
	@Resource
	private PixiTokenUtils pixiTokenUtils;
	
	@Resource
	private AuthenticationManager pixiAuthManager;
	
	@RequestMapping(method = RequestMethod.GET, produces = "text/xml", params="action=" + ACTION)
	@ResponseBody
	public AuthenticationResponse testXMLParam(HttpServletRequest request, @RequestParam final String user,
			@RequestParam final String pass)
	{
		try 
		{
			if (validateCredentials(request, user, pass))
			{
				return getAuthResponse(pixiTokenUtils.generateToken(user));
			}
		} 
		catch (Exception e) 
		{
			LOG.error("Error during authentication", e);
		}

		return getErrorResponse();
	}

	private boolean validateCredentials(HttpServletRequest request, final String user, final String pass)
	{
		UsernamePasswordAuthenticationToken authentication =  new UsernamePasswordAuthenticationToken(user, pass);
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
		
		Authentication authenticate = pixiAuthManager.authenticate(authentication);
		
		return authenticate != null && authenticate.isAuthenticated() && hasRole(PixiwebservicesConstants.Pixi.GUEST_ROLE, authenticate);
	}

	private boolean hasRole(String role, Authentication authenticate) 
	{
		Collection<? extends GrantedAuthority> authorities = authenticate.getAuthorities();
		if(CollectionUtils.isNotEmpty(authorities))
		{
			for(GrantedAuthority a : authorities)
			{
				if(a.getAuthority().equals(role))
				{
					return true;
				}
			}
		}
		
		return false;
	}

	private AuthenticationResponse getAuthResponse(final String token)
	{
		final AuthenticationResponse response = new AuthenticationResponse();
		response.setSessionID(token);
		response.setSTATUS("Success");

		return response;
	}

	private AuthenticationResponse getErrorResponse()
	{
		final AuthenticationResponse response = new AuthenticationResponse();
		response.setSTATUS("Error");
		response.setDESCRIPTION("Incorrect Credentials");

		return response;
	}
}