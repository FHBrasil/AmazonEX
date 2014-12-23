package com.pixi.webservices.controller.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pixi.webservices.controller.AbstractPixiController;
import com.pixi.webservices.jaxb.orderstatus.response.ImportOrderStatusResponse;

@Controller
public class ConfirmOrderController extends AbstractPixiController
{
	private static final String ACTION = "confirm_order";

	@RequestMapping(method = RequestMethod.GET, produces = "text/xml", params="action=" + ACTION)
	public @ResponseBody ImportOrderStatusResponse handle(@RequestParam final String orderId, @RequestParam final String session)
	{
		ImportOrderStatusResponse response = new ImportOrderStatusResponse();
		response.setDESCRIPTION("");
		response.setORDERID(orderId);
		response.setSessionID(session);
		response.setSTATUS("SUCCESS");
		
		return response;
	}
}