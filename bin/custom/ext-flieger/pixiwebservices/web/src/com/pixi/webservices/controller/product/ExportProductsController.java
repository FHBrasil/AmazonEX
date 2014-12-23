package com.pixi.webservices.controller.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pixi.core.services.PixiProductService;
import com.pixi.webservices.controller.AbstractPixiController;
import com.pixi.webservices.jaxb.product.export.Article;
import com.pixi.webservices.jaxb.product.export.BMECAT;

import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.site.BaseSiteService;

@Controller
public class ExportProductsController extends AbstractPixiController
{
	private static final String ACTION = "export_update";

	@Resource
	private PixiProductService pixiProductService;
	
	@Resource
	private Converter<List<ProductModel>, BMECAT> pixiBMEcatConverter;
	
	@Resource
	private BaseSiteService siteService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "text/xml", params="action=" + ACTION)
	public @ResponseBody BMECAT handle(@RequestParam final String onlynew, @RequestParam final String session)
	{
		siteService.setCurrentBaseSite("babyartikel", true);
		
		List<ProductModel> products = pixiProductService.findProductsToExport();
		
		BMECAT bmeCat = pixiBMEcatConverter.convert(products);
		
		return bmeCat;
		
//		System.out.println("gerando product");
//
////		PIXI BMECAT BUYER POPULATOR
//		final ArticleAddress address = new ArticleAddress();
//		address.setCITY("city");
//		address.setCOUNTRY("country");
//		address.setNAME("name");
//		address.setNAME2("name 2");
//		address.setSTREET("street");
//		address.setType("type");
//		address.setZIP("zip");
//
//		final Buyer buyer = new Buyer();
//		buyer.setADDRESS(address);
//		buyer.setBUYERID("buyer id");
//		buyer.setBUYERNAME("buyer name");
//
////		PIXI BMECAT SUPPLIER POPULATOR
//		final ArticleAddress supplierAddress = new ArticleAddress();
//		supplierAddress.setCITY("city");
//		supplierAddress.setCOUNTRY("country");
//		supplierAddress.setNAME("name");
//		supplierAddress.setNAME2("name 2");
//		supplierAddress.setSTREET("street");
//		supplierAddress.setType("type");
//		supplierAddress.setZIP("zip");
//
//		final Supplier supplier = new Supplier();
//		supplier.setADDRESS(supplierAddress);
//		supplier.setSUPPLIERNAME("supplier name");
//		
////		PIXI BMECAT CATALOG POPULATOR
//		final Catalog catalog = new Catalog();
//		catalog.setCATALOGID("catalogID");
//		catalog.setCATALOGNAME("catalogName");
//		catalog.setCATALOGVERSION("version");
//		catalog.setCURRENCY("EUR");
//		catalog.setDATABASE("database");
//		catalog.setDATEEXPORT(new Date());
//		catalog.setEXPORTDATE(new Date());
//		catalog.setLANGUAGE("de-DE");
//		catalog.setSHOPID("shop id");
//
////		PIXI BMECAT HEADER POPULATOR
//		final Header header = new Header();
//		header.setBUYER(buyer);
//		header.setCATALOG(catalog);
//		header.setGENERATORINFO("generator info");
//		header.setSUPPLIER(supplier);
//
//		final Collection<Article> articles = new ArrayList<Article>();
//
//		for (int j = 0; j < 3; j++)
//		{
////			PIXI PRODUCT DETAILS POPULATOR
//			final ArticleDetails details = new ArticleDetails();
//			details.setDESCRIPTIONSHORT("short" + j);
//			details.setEAN("ean" + j);
//			details.setINTERNALITEMNUMBER("itemNumber" + j);
//			details.setMANUFACTURERNAME("manufacturer name" + j);
//			details.setSEGMENT("segment" + j);
//			details.setWEIGHT(BigDecimal.TEN.multiply(BigDecimal.valueOf(j)));
//
////			PIXI PRODUCT FEATURES POPULATOR
//			final ArticleFeatures features = new ArticleFeatures();
//			features.setCustomsCountryOfOrigin("country" + j);
//			features.setCustomsTariffNumber("tariff number" + j);
//			features.setCustomsTariffText("tariff text" + j);
//			features.setSerialNumberRequired("false" + j);
//
//			for (int i = 0; i < 3; i++)
//			{
//				final Feature feature = new Feature();
//				feature.setFNAME("name" + (i * j));
//				feature.setFVALUE("value" + (i * j));
//
//				features.getFEATURE().add(feature);
//			}
//
////			PIXI PRODUCT PICTURE POPULATOR
//			final Mime mime = new Mime();
//			mime.setMIMEPURPOSE("purpose" + j);
//			mime.setMIMESOURCE("source" + j);
//			mime.setMIMETYPE("type" + j);
//
////			PIXI PRODUCT POPULATOR
//			final Article article = new Article();
//			article.setARTICLEDETAILS(details);
//			article.setARTICLEFEATURES(features);
//			article.setMIME(mime);
//			article.setORDERUNIT("pieces" + j);
//			article.setSUPPLIERAID("AID" + j);
//
////			PIXI PRODUCT PRICE POPULATOR
//			for (int i = 0; i < 2; i++)
//			{
//				final ArticlePrice price = new ArticlePrice();
//				price.setPRICEAMOUNT(BigDecimal.TEN.multiply(BigDecimal.valueOf(i)));
//				price.setPRICECURRENCY("EUR");
//				price.setPriceType("type");
//				price.setSUPPLPRICEAMOUNT(BigDecimal.TEN.multiply(BigDecimal.valueOf(i)));
//
//				article.getARTICLEPRICE().add(price);
//			}
//
////			PIXI PRODUCT TAG POPULATOR
//			for (int i = 0; i < 3; i++)
//			{
//				article.getITEMTAG().add("itme tag " + (i * j));
//			}
//
//			articles.add(article);
//		}
//
////		PIXI BMECAT POPULATOR
//		final BMECAT bmeCat = new BMECAT();
//		bmeCat.setHEADER(header);
//		bmeCat.setVersion(BigDecimal.ONE);
//		bmeCat.getARTICLE().addAll(articles);
//
//		return bmeCat;
	}
}
