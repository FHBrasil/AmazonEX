package com.pixi.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;

import xml.auth.AuthenticationResponse;

import com.pixi.dto.SampleResponse;

@RequestMapping("/pixi")
public abstract class AbstractPixiController 
{
	/**
	 * @param action
	 * @return
	 */
	protected SampleResponse getResponse(final String action)
	{
		final SampleResponse sr = new SampleResponse();
		sr.setAction(this.getClass().getSimpleName() + " - " + action);
		sr.setDate(new Date());
		sr.setUid(UUID.randomUUID().toString());

		return sr;
	}
	
	protected AuthenticationResponse getUnknownActionResponse()
	{
		final AuthenticationResponse response = new AuthenticationResponse();
		response.setSTATUS("Error");
		response.setDESCRIPTION("unknown action defined");

		return response;
	}
}
