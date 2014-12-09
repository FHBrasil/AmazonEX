package com.pixi.controller.order;

import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import xml.orderstatus.request.ImportOrderStatusRequest;
import xml.response.GlobalResponse;

import com.pixi.controller.AbstractPixiController;

@Controller
public class ImportOrderStatusController extends AbstractPixiController
{
	private static final String ACTION = "import_order_status";

	@RequestMapping(method = RequestMethod.GET, produces = "text/xml", params="action=" + ACTION)
	public @ResponseBody GlobalResponse handle(@RequestParam String data, @RequestParam final String session) throws JAXBException
	{
		ImportOrderStatusRequest request = convert(ImportOrderStatusRequest.class, data);
		
		GlobalResponse response = new GlobalResponse();
		response.setCode("SUCCESS");
		response.setMessage("");
		
		return response;
	}
}