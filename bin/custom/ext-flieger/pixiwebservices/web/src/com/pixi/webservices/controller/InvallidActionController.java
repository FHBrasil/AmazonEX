package com.pixi.webservices.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pixi.webservices.dto.SampleResponse;

@Controller
public class InvallidActionController extends AbstractPixiController
{
	@RequestMapping(method = RequestMethod.GET, produces = "text/xml", params="action={*}")
	@ResponseBody
	public SampleResponse fallbackMethod2(@RequestParam final String action)
	{
		final SampleResponse sr = new SampleResponse();
		sr.setAction(this.getClass().getSimpleName() + " - " + action + " not found 2");
		sr.setDate(new Date());
		sr.setUid(UUID.randomUUID().toString());
		
		return sr;
	}
}
