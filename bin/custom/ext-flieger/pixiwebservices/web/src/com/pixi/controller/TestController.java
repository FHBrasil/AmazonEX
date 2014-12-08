package com.pixi.controller;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import xml.factory.JaxbContextFactory;
import xml.order.export.Address;
import xml.order.export.Order;
import xml.order.export.OrderEntryPrice;
import xml.order.export.OrderHeader;
import xml.order.export.OrderInfo;
import xml.order.export.OrderItem;
import xml.order.export.OrderParties;
import xml.order.export.Party;
import xml.order.export.PartyID;
import xml.order.export.Payment;
import xml.order.export.Remark;
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

import com.pixi.dto.SampleRequest;
import com.pixi.dto.SampleResponse;


@Controller
public class TestController
{
	@Resource
	private JaxbContextFactory jaxbContextFactory;

	@RequestMapping(value = "/import", method = RequestMethod.GET, produces = "text/xml")
	@ResponseBody
	public SampleResponse testXMLParam(@RequestParam final String action, @RequestParam final String data)
	{
		System.out.println("data: " + data);

		try
		{

			final JAXBContext jaxbContext = jaxbContextFactory.createJaxbContext(SampleRequest.class);
			System.out.println("jaxbContext: " + jaxbContext.getClass());
			final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			final SampleRequest request = (SampleRequest) jaxbUnmarshaller.unmarshal(new StringReader(data));

			System.out.println(request);
		}
		catch (final JAXBException e)
		{
			e.printStackTrace();
		}


		final SampleResponse sr = getResponse(action);

		return sr;
	}

	@RequestMapping(value = "/import/order", method = RequestMethod.GET, produces = "text/xml")
	@ResponseBody
	public Order testOrder()
	{
		System.out.println("gerando order");

		final Address address = new Address();
		address.setADDRESSREMARKS("remarks");
		address.setCHARGEVAT("chargevat");
		address.setCITY("city");
		address.setCOUNTRY("country");
		address.setEMAIL("email@email.de");
		address.setFAX("fax");
		address.setNAME("name");
		address.setNAME2("name 2");
		address.setNAME3("name 3");
		address.setPHONE("phone");
		address.setSAL("sal");
		address.setSTREET("rua");
		address.setVATID("vatid");
		address.setZIP("zip");

		final PartyID partyID = new PartyID();
		partyID.setType("type");
		partyID.setValue("value");

		Party party = new Party();
		party.setADDRESS(address);
		party.setPARTYID(partyID);

		final OrderParties parties = new OrderParties();
		parties.setBUYERPARTY(party);
		parties.setINVOICEPARTY(party);

		party = new Party();
		party.setADDRESS(address);
		parties.setDELIVERYPARTY(party);

		party = new Party();
		party.setPARTYID(partyID);
		parties.setSUPPLIERPARTY(party);

		final Payment payment = new Payment();
		payment.setPAGAMENTOTEMP("pagamento temporario");

		final OrderInfo orderInfo = new OrderInfo();
		orderInfo.setDATABASE("database");
		orderInfo.setGIFTMESSAGE("message");
		orderInfo.setORDERDATE(new Date());
		orderInfo.setORDERID("1234567890");
		orderInfo.setORDERPARTIES(parties);
		orderInfo.setORDERSHIPLOCK("ship lock");
		orderInfo.setPAYMENT(payment);
		orderInfo.setPRICECURRENCY("EUR");
		orderInfo.setSHOPID("shop id");
		orderInfo.setSHOPNOTE("shop note");
		orderInfo.setTRANSPORTREMARKS("transport remarks");

		for (int i = 0; i < 5; i++)
		{
			final Remark remark = new Remark();
			remark.setType("type" + i);
			remark.setValue("value" + i);
			orderInfo.getREMARK().add(remark);
		}

		final OrderHeader header = new OrderHeader();
		header.setGENERATIONDATE(new Date());
		header.setGENERATORINFO("info");
		header.setORDERINFO(orderInfo);

		final Order order = new Order();
		order.setORDERHEADER(header);
		order.setTOTALITEMNUM(1);
		order.setType("type");
		order.setVersion(new BigDecimal("1.0"));

		for (int i = 0; i < 5; i++)
		{
			final OrderEntryPrice articlePrice = new OrderEntryPrice();
			articlePrice.setDISCOUNTPERC(BigDecimal.ONE);
			articlePrice.setDISCOUNTVALUE(BigDecimal.ONE);
			articlePrice.setFULLPRICE(BigDecimal.TEN);
			articlePrice.setPRICEAMOUNT(BigDecimal.ZERO);
			articlePrice.setType("tipo");

			final OrderItem orderItem = new OrderItem();
			orderItem.setARTICLEPRICE(articlePrice);
			orderItem.setDESCRIPTIONSHORT("shor description" + i);
			orderItem.setITEMNOTE("blblabllal" + i);
			orderItem.setLINEITEMID("1234567890" + i);
			orderItem.setORDERUNIT("pieces");
			orderItem.setQUANTITY(1);
			orderItem.setSUPPLIERAID("supplier aid" + i);

			order.getORDERITEM().add(orderItem);
		}

		return order;
	}

	@RequestMapping(value = "/import/product", method = RequestMethod.GET, produces = "text/xml")
	@ResponseBody
	public BMECAT testProduct()
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

	/**
	 * @param action
	 * @return
	 */
	private SampleResponse getResponse(final String action)
	{
		final SampleResponse sr = new SampleResponse();
		sr.setAction(action);
		sr.setDate(new Date());
		sr.setUid(UUID.randomUUID().toString());

		return sr;
	}
}