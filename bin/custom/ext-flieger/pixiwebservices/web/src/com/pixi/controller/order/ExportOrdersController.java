package com.pixi.controller.order;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

import com.pixi.controller.AbstractPixiController;

@Controller
public class ExportOrdersController extends AbstractPixiController 
{
	private static final String ACTION = "export_order";

	@RequestMapping(method = RequestMethod.GET, produces = "text/xml", params="action=" + ACTION)
	public @ResponseBody Order handle(@RequestParam final String session)
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
}