package com.pixi.webservices.controller.product;

import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pixi.webservices.controller.AbstractPixiController;
import com.pixi.webservices.jaxb.response.GlobalResponse;
import com.pixi.webservices.jaxb.stock.request.ImportProductStockRequest;

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
