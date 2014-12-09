package com.pixi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pixi.dto.SampleResponse;

@Controller
public class InvallidActionController extends AbstractPixiController 
{
	@RequestMapping(method = RequestMethod.GET, produces = "text/xml", params="action={*}")
	@ResponseBody
	public SampleResponse fallbackMethod2(@RequestParam final String action)
	{
		return getResponse(action + " not found 2");
	}
}
