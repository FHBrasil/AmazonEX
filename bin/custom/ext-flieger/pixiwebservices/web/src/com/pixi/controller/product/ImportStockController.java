package com.pixi.controller.product;

import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import xml.response.GlobalResponse;
import xml.stock.request.ImportProductStockRequest;

import com.pixi.controller.AbstractPixiController;

@Controller
public class ImportStockController extends AbstractPixiController
{
	private static final String ACTION = "import_stock";

	@RequestMapping(method = RequestMethod.GET, produces = "text/xml", params="action=" + ACTION)
	public @ResponseBody GlobalResponse handle(@RequestParam final String data, @RequestParam final String session) throws JAXBException
	{
		ImportProductStockRequest request = convert(ImportProductStockRequest.class, data);
		
		GlobalResponse response = new GlobalResponse();
		response.setCode("OK");
		response.setMessage("");
		
		return response;
	}
}
