package com.pixi.webservices.converters.populators.order;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.pixi.webservices.jaxb.order.export.Address;
import com.pixi.webservices.jaxb.order.export.OrderInfo;
import com.pixi.webservices.jaxb.order.export.OrderParties;
import com.pixi.webservices.jaxb.order.export.Party;
import com.pixi.webservices.jaxb.order.export.PartyID;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.util.Config;

public class DefaultPixiOrderInfoPopulator implements Populator<OrderModel, OrderInfo>
{
	private Logger LOG = Logger.getLogger(DefaultPixiOrderInfoPopulator.class);
	
	@Resource
	private Converter<AddressModel, Address> pixiAddressConverter;
	
	@Override
	public void populate(OrderModel source, OrderInfo target) throws ConversionException 
	{
		LOG.info("populating");
		
		target.setDATABASE(Config.getParameter("pixiwebservices.order.export.database"));
		target.setGIFTMESSAGE("");
		target.setORDERDATE(source.getDate());
		target.setORDERID(source.getCode());
		target.setORDERSHIPLOCK("N");
		target.setPRICECURRENCY(source.getCurrency().getIsocode());
		target.setSHOPID(Config.getParameter("pixiwebservices.pixi.shop.id"));
		target.setORDERPARTIES(getOrderParties(source));
	}

	private OrderParties getOrderParties(final OrderModel source) 
	{
		CustomerModel customer = (CustomerModel) source.getUser();
		Party paymentParty = getPartyFromAddress(source.getPaymentAddress(), customer);
		Party deliveryParty = getPartyFromAddress(source.getDeliveryAddress(), customer);

		OrderParties orderParties = new OrderParties();
		orderParties.setBUYERPARTY(paymentParty);
		orderParties.setINVOICEPARTY(paymentParty);
		orderParties.setDELIVERYPARTY(deliveryParty);
		orderParties.setSUPPLIERPARTY(deliveryParty);
		
		return orderParties;
	}

	private Party getPartyFromAddress(final AddressModel address, final CustomerModel owner) 
	{
		final PartyID paymentPartyId = new PartyID();
		paymentPartyId.setType(Config.getParameter("pixiwebservices.order.address.party.type"));
		paymentPartyId.setValue(owner.getCustomerID());
		
		final Party paymentParty = new Party();
		paymentParty.setADDRESS(pixiAddressConverter.convert(address));
		paymentParty.setPARTYID(paymentPartyId);
		
		return paymentParty;
	}
}