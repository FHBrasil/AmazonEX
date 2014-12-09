package com.pixi.controller.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pixi.controller.AbstractPixiController;
import com.pixi.dto.SampleResponse;

@Controller
public class ImportOrderStatusController extends AbstractPixiController
{
	private static final String ACTION = "import_order_status";

	@RequestMapping(method = RequestMethod.GET, produces = "text/xml", params="action=" + ACTION)
	public @ResponseBody SampleResponse handle()
	{
		return getResponse(ACTION);
	}
}
