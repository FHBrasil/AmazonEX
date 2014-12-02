package de.flieger.controller;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import xml.auth.AuthenticationResponse;


@Controller
public class AuthController
{
	@RequestMapping(value = "/pixi", method = RequestMethod.GET, produces = "text/xml")
	@ResponseBody
	public AuthenticationResponse testXMLParam(@RequestParam final String action, @RequestParam final String user,
			@RequestParam final String pass)
	{
		if (!StringUtils.equals(action, "session_start"))
		{
			return getUnknownActionResponse();
		}

		if (validateCredentials(user, pass))
		{
			return getAuthResponse();
		}

		return getErrorResponse();
	}

	private boolean validateCredentials(final String user, final String pass)
	{
		return StringUtils.equals(user, "pixi") && StringUtils.equals(pass, "1234");
	}

	private AuthenticationResponse getAuthResponse()
	{
		final AuthenticationResponse response = new AuthenticationResponse();
		response.setSessionID(UUID.randomUUID().toString());
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

	private AuthenticationResponse getUnknownActionResponse()
	{
		final AuthenticationResponse response = new AuthenticationResponse();
		response.setSTATUS("Error");
		response.setDESCRIPTION("unknown action defined");

		return response;
	}
}