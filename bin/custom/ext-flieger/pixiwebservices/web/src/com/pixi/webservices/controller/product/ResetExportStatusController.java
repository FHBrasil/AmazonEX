package com.pixi.webservices.controller.product;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pixi.webservices.controller.AbstractPixiController;
import com.pixi.webservices.jaxb.stock.response.ImportProductStockResponse;

@Controller
public class ResetExportStatusController extends AbstractPixiController
{
	private static final String ACTION = "reset_export_status";

	@RequestMapping(method = RequestMethod.GET, produces = "text/xml", params="action=" + ACTION)
	public @ResponseBody ImportProductStockResponse handle(@RequestParam final Long date, @RequestParam final String session)
	{
		
		ImportProductStockResponse response = new ImportProductStockResponse();
		response.setDESCRIPTION("description");
		response.setEXPORTDATE(new Date((date) * 1000));
		response.setSessionID(session);
		response.setSTATUS("success");
		
		return response;
	}
}