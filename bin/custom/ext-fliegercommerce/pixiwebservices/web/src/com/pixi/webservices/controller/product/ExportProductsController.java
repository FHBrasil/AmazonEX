package com.pixi.webservices.controller.product;

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
import com.pixi.webservices.jaxb.product.export.BMECAT;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

@Controller
public class ExportProductsController extends AbstractPixiSecuredController
{
	private static final String ACTION = "export_update";

	@Resource
	private PixiProductService pixiProductService;
	
	@Resource
	private Converter<List<ProductModel>, BMECAT> pixiBMEcatConverter;
	
	@RequestMapping(method = RequestMethod.GET, produces = "text/xml", params="action=" + ACTION)
	public @ResponseBody BMECAT handle(@RequestParam(required = false, defaultValue = "n") final String onlynew, @RequestParam final String session)
	{
		//TODO falta implementar a logica dos artigos que foram alterados, nao esuqecer do onlynew
		List<ProductModel> products = pixiProductService.findProductsToExport();
		
		saveSyncRecord(session, products);
		
		//convert the hybris product model to a webservice bean
		BMECAT bmeCat = pixiBMEcatConverter.convert(products);
		
		return bmeCat;
	}

	private void saveSyncRecord(String session, List<ProductModel> products) 
	{
		if(CollectionUtils.isEmpty(products))
		{
			return;
		}
		
		Iterator<ProductModel> iterator = products.iterator();
		while(iterator.hasNext())
		{
			ProductModel product = iterator.next();
			pixiProductService.saveSyncRecord(product.getCode(), session, null);
		}
	}
}
