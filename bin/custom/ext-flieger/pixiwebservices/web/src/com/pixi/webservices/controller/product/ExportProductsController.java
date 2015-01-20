package com.pixi.webservices.controller.product;

import java.util.List;

import javax.annotation.Resource;

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
	public @ResponseBody BMECAT handle(@RequestParam final String onlynew, @RequestParam final String session)
	{
		List<ProductModel> products = pixiProductService.findProductsToExport();
		
		//TODO falta tratamento de erro
		BMECAT bmeCat = pixiBMEcatConverter.convert(products);
		
		return bmeCat;
	}
}
