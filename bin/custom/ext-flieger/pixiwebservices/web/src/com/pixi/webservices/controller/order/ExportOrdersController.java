package com.pixi.webservices.controller.order;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pixi.core.services.PixiOrderService;
import com.pixi.webservices.controller.AbstractPixiController;
import com.pixi.webservices.jaxb.order.export.Order;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

@Controller
public class ExportOrdersController extends AbstractPixiController 
{
	private static final String ACTION = "export_order";
	
	@Resource
	private Converter<OrderModel, Order> pixiOrderConverter;
	
	@Resource
	private PixiOrderService pixiOrderService;

	@RequestMapping(method = RequestMethod.GET, produces = "text/xml", params="action=" + ACTION)
	public @ResponseBody Order handle(@RequestParam final String session)
	{
		List<OrderModel> orders = pixiOrderService.findNotExportedOrders();
		
		OrderModel orderToExport = orders.iterator().next();
		Order wsOrder = pixiOrderConverter.convert(orderToExport);
	
		return wsOrder;
		
//		System.out.println("gerando order");
//
////		PIXI PAYMENT ADDRESS POPULATOR
//		final Address paymentAddress = new Address();
//		paymentAddress.setADDRESSREMARKS("remarks");
//		paymentAddress.setCHARGEVAT("chargevat");
//		paymentAddress.setCITY("city");
//		paymentAddress.setCOUNTRY("country");
//		paymentAddress.setEMAIL("email@email.de");
//		paymentAddress.setFAX("fax");
//		paymentAddress.setNAME("name");
//		paymentAddress.setNAME2("name 2");
//		paymentAddress.setNAME3("name 3");
//		paymentAddress.setPHONE("phone");
//		paymentAddress.setSAL("sal");
//		paymentAddress.setSTREET("rua");
//		paymentAddress.setVATID("vatid");
//		paymentAddress.setZIP("zip");
//
//		final PartyID paymentPartyId = new PartyID();
//		paymentPartyId.setType("type");
//		paymentPartyId.setValue("value");
//
//		Party paymentParty = new Party();
//		paymentParty.setADDRESS(paymentAddress);
//		paymentParty.setPARTYID(paymentPartyId);
//
////		PIXI DELIVERY ADDRESS POPULATOR
//		final Address deliveryAddress = new Address();
//		deliveryAddress.setADDRESSREMARKS("remarks");
//		deliveryAddress.setCHARGEVAT("chargevat");
//		deliveryAddress.setCITY("city");
//		deliveryAddress.setCOUNTRY("country");
//		deliveryAddress.setEMAIL("email@email.de");
//		deliveryAddress.setFAX("fax");
//		deliveryAddress.setNAME("name");
//		deliveryAddress.setNAME2("name 2");
//		deliveryAddress.setNAME3("name 3");
//		deliveryAddress.setPHONE("phone");
//		deliveryAddress.setSAL("sal");
//		deliveryAddress.setSTREET("rua");
//		deliveryAddress.setVATID("vatid");
//		deliveryAddress.setZIP("zip");
//		
//		final PartyID deliveryPartyId = new PartyID();
//		deliveryPartyId.setType("type");
//		deliveryPartyId.setValue("value");
//		
//		Party deliveryParty = new Party();
//		deliveryParty.setPARTYID(deliveryPartyId);
//
////		PIXI SUPPLIER POPULATOR
//		final Address supplierAddress = new Address();
//		supplierAddress.setADDRESSREMARKS("remarks");
//		supplierAddress.setCHARGEVAT("chargevat");
//		supplierAddress.setCITY("city");
//		supplierAddress.setCOUNTRY("country");
//		supplierAddress.setEMAIL("email@email.de");
//		supplierAddress.setFAX("fax");
//		supplierAddress.setNAME("name");
//		supplierAddress.setNAME2("name 2");
//		supplierAddress.setNAME3("name 3");
//		supplierAddress.setPHONE("phone");
//		supplierAddress.setSAL("sal");
//		supplierAddress.setSTREET("rua");
//		supplierAddress.setVATID("vatid");
//		supplierAddress.setZIP("zip");
//		
//		Party supplierParty  = new Party();
//		supplierParty.setADDRESS(supplierAddress);
//		
//		//PIXI OrderParties populators
//		final OrderParties parties = new OrderParties();
//		parties.setBUYERPARTY(paymentParty);
//		parties.setINVOICEPARTY(paymentParty);
//		parties.setDELIVERYPARTY(deliveryParty);
//		parties.setSUPPLIERPARTY(supplierParty);
//
////		PIXI PAYMENT INFO POPULATOR
//		final Payment payment = new Payment();
//		payment.setPAGAMENTOTEMP("pagamento temporario");
//
////		PIXI ORDER INFO POPULATOR
//		final OrderInfo orderInfo = new OrderInfo();
//		orderInfo.setDATABASE("database");
//		orderInfo.setGIFTMESSAGE("message");
//		orderInfo.setORDERDATE(new Date());
//		orderInfo.setORDERID("1234567890");
//		orderInfo.setORDERPARTIES(parties);
//		orderInfo.setORDERSHIPLOCK("ship lock");
//		orderInfo.setPAYMENT(payment);
//		orderInfo.setPRICECURRENCY("EUR");
//		orderInfo.setSHOPID("shop id");
//		orderInfo.setSHOPNOTE("shop note");
//		
//		
////		PIXI ORDER REMARKS POPULATOR
//		orderInfo.setTRANSPORTREMARKS("transport remarks");
//		for (int i = 0; i < 5; i++)
//		{
//			final Remark remark = new Remark();
//			remark.setType("type" + i);
//			remark.setValue("value" + i);
//			orderInfo.getREMARK().add(remark);
//		}
//
////		PIXI ORDER HEADER POPULATOR
//		final OrderHeader header = new OrderHeader();
//		header.setGENERATIONDATE(new Date());
//		header.setGENERATORINFO("info");
//		header.setORDERINFO(orderInfo);
//
//		final Order order = new Order();
//		order.setORDERHEADER(header);
//		
//		order.setTOTALITEMNUM(1);
//		order.setType("type");
//		order.setVersion(BigDecimal.ONE);
//
////		PIXI ORDER ENTRIES POPULATOR
//		for (int i = 0; i < 5; i++)
//		{
//			final OrderEntryPrice articlePrice = new OrderEntryPrice();
//			articlePrice.setDISCOUNTPERC(BigDecimal.ONE);
//			articlePrice.setDISCOUNTVALUE(BigDecimal.ONE);
//			articlePrice.setFULLPRICE(BigDecimal.TEN);
//			articlePrice.setPRICEAMOUNT(BigDecimal.ZERO);
//			articlePrice.setType("tipo");
//
//			final OrderItem orderItem = new OrderItem();
//			orderItem.setARTICLEPRICE(articlePrice);
//			orderItem.setDESCRIPTIONSHORT("shor description" + i);
//			orderItem.setITEMNOTE("blblabllal" + i);
//			orderItem.setLINEITEMID("1234567890" + i);
//			orderItem.setORDERUNIT("pieces");
//			orderItem.setQUANTITY(1);
//			orderItem.setSUPPLIERAID("supplier aid" + i);
//
//			order.getORDERITEM().add(orderItem);
//		}
//		
////		List<OrderModel> orders = pixiOrderService.findNotExportedOrders();
////		OrderModel orderToExport = orders.iterator().next();
////		Order wsOrder = wsOrderConverter.convert(orderToExport);
//		
//		return order;
	}
}