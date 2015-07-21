package com.pixi.webservices.controller.product;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pixi.core.services.PixiProductService;
import com.pixi.webservices.controller.AbstractPixiSecuredController;
import com.pixi.webservices.jaxb.stock.response.ImportProductStockResponse;

@Controller
public class ResetExportStatusController extends AbstractPixiSecuredController
{
	private static final String ACTION = "reset_export_status";

	@Resource
	private PixiProductService pixiProductService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "text/xml", params="action=" + ACTION)
	public @ResponseBody ImportProductStockResponse handle(@RequestParam final long date, @RequestParam final String session)
	{
		final Date exportDate = calculateExportDate(date);
		final Date confirmationDate = calculateConfirmationDate(date);

		final List<String> products = getProductsToExport(session, exportDate);
		
		boolean success = setExportDate(products, exportDate, confirmationDate);
		
		if(success)
		{
			getPixiProductService().releaseProductsFromSession(session);
		}
		
		return getResponse(session, exportDate, success);
	}

	private List<String> getProductsToExport(final String session, final Date exportDate) 
	{
		if(exportDate == null)
		{
			return getPixiProductService().findAllProducts();
		}
		
		return getPixiProductService().findExportedProductsBySessionID(session);
	}

	private boolean setExportDate(final List<String> products, final Date exportDate, final Date confirmationDate) 
	{
		if(CollectionUtils.isEmpty(products))
		{
			return false;
		}
		
		final Iterator<String> iterator = products.iterator();
		
		while(iterator.hasNext())
		{
			getPixiProductService().saveExportConfirmationDate(iterator.next(), exportDate, confirmationDate);
		}
		
		return true;
	}

	private Date calculateExportDate(long date) 
	{
		if(date <= 0)
		{
			return null;
		}
		
		return new Date(date * 1000);
	}
	
	private Date calculateConfirmationDate(long date) 
	{
		if(date <= 0)
		{
			return null;
		}
		
		return new Date(System.currentTimeMillis() + 10000); 
	}
	
	private ImportProductStockResponse getResponse(final String session, final Date exportDate, boolean success) 
	{
		final String description = success ? "The date was changed" : "No articles found for confirmation";
		
		final ImportProductStockResponse response = new ImportProductStockResponse();
		response.setDESCRIPTION(description);
		response.setEXPORTDATE(exportDate);
		response.setSessionID(session);
		response.setSTATUS("SUCCESS");
		
		return response;
	}

	/**
	 * @return the pixiProductService
	 */
	public PixiProductService getPixiProductService() 
	{
		return pixiProductService;
	}
}