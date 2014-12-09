package com.pixi.controller.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import xml.product.export.Article;
import xml.product.export.ArticleAddress;
import xml.product.export.ArticleDetails;
import xml.product.export.ArticleFeatures;
import xml.product.export.ArticlePrice;
import xml.product.export.BMECAT;
import xml.product.export.Buyer;
import xml.product.export.Catalog;
import xml.product.export.Feature;
import xml.product.export.Header;
import xml.product.export.Mime;
import xml.product.export.Supplier;

import com.pixi.controller.AbstractPixiController;

@Controller
public class ExportProductsController extends AbstractPixiController
{
	private static final String ACTION = "export_update";

	@RequestMapping(method = RequestMethod.GET, produces = "text/xml", params="action=" + ACTION)
	public @ResponseBody BMECAT handle(@RequestParam final String onlynew, @RequestParam final String session)
	{
		System.out.println("gerando product");

		final ArticleAddress address = new ArticleAddress();
		address.setCITY("city");
		address.setCOUNTRY("country");
		address.setNAME("name");
		address.setNAME2("name 2");
		address.setSTREET("street");
		address.setType("type");
		address.setZIP("zip");

		final Buyer buyer = new Buyer();
		buyer.setADDRESS(address);
		buyer.setBUYERID("buyer id");
		buyer.setBUYERNAME("buyer name");

		final Catalog catalog = new Catalog();
		catalog.setCATALOGID("catalogID");
		catalog.setCATALOGNAME("catalogName");
		catalog.setCATALOGVERSION("version");
		catalog.setCURRENCY("EUR");
		catalog.setDATABASE("database");
		catalog.setDATEEXPORT(new Date());
		catalog.setEXPORTDATE(new Date());
		catalog.setLANGUAGE("de-DE");
		catalog.setSHOPID("shop id");

		final Supplier supplier = new Supplier();
		supplier.setADDRESS(address);
		supplier.setSUPPLIERNAME("supplier name");

		final Header header = new Header();
		header.setBUYER(buyer);
		header.setCATALOG(catalog);
		header.setGENERATORINFO("generator info");
		header.setSUPPLIER(supplier);

		final Collection<Article> articles = new ArrayList<Article>();

		for (int j = 0; j < 3; j++)
		{
			final ArticleDetails details = new ArticleDetails();
			details.setDESCRIPTIONSHORT("short" + j);
			details.setEAN("ean" + j);
			details.setINTERNALITEMNUMBER("itemNumber" + j);
			details.setMANUFACTURERNAME("manufacturer name" + j);
			details.setSEGMENT("segment" + j);
			details.setWEIGHT(BigDecimal.TEN.multiply(BigDecimal.valueOf(j)));

			final ArticleFeatures features = new ArticleFeatures();
			features.setCustomsCountryOfOrigin("country" + j);
			features.setCustomsTariffNumber("tariff number" + j);
			features.setCustomsTariffText("tariff text" + j);
			features.setSerialNumberRequired("false" + j);

			for (int i = 0; i < 3; i++)
			{
				final Feature feature = new Feature();
				feature.setFNAME("name" + (i * j));
				feature.setFVALUE("value" + (i * j));

				features.getFEATURE().add(feature);
			}

			final Mime mime = new Mime();
			mime.setMIMEPURPOSE("purpose" + j);
			mime.setMIMESOURCE("source" + j);
			mime.setMIMETYPE("type" + j);

			final Article article = new Article();
			article.setARTICLEDETAILS(details);
			article.setARTICLEFEATURES(features);
			article.setMIME(mime);
			article.setORDERUNIT("pieces" + j);
			article.setSUPPLIERAID("AID" + j);

			for (int i = 0; i < 2; i++)
			{
				final ArticlePrice price = new ArticlePrice();
				price.setPRICEAMOUNT(BigDecimal.TEN.multiply(BigDecimal.valueOf(i)));
				price.setPRICECURRENCY("EUR");
				price.setPriceType("type");
				price.setSUPPLPRICEAMOUNT(BigDecimal.TEN.multiply(BigDecimal.valueOf(i)));

				article.getARTICLEPRICE().add(price);
			}

			for (int i = 0; i < 3; i++)
			{
				article.getITEMTAG().add("itme tag " + (i * j));
			}

			articles.add(article);
		}

		final BMECAT bmeCat = new BMECAT();
		bmeCat.setHEADER(header);
		bmeCat.setVersion(BigDecimal.ONE);
		bmeCat.getARTICLE().addAll(articles);

		return bmeCat;
	}
}
