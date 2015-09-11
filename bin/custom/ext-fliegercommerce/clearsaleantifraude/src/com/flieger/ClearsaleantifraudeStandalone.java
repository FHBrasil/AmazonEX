/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.flieger;

import de.hybris.platform.core.Registry;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.util.RedeployUtilities;
import de.hybris.platform.util.Utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.flieger.clearsale.webserviceclient.SOAPservices.ClearSaleSOAPServices;
import com.flieger.clearsale.webserviceclient.SOAPservices.impl.DefaultClearSaleSOAPServices;
import com.flieger.clearsale.webserviceclient.beans.Address;
import com.flieger.clearsale.webserviceclient.beans.BillingData;
import com.flieger.clearsale.webserviceclient.beans.CardTypeEnum;
import com.flieger.clearsale.webserviceclient.beans.ClearSale;
import com.flieger.clearsale.webserviceclient.beans.FingerPrint;
import com.flieger.clearsale.webserviceclient.beans.GenderEnum;
import com.flieger.clearsale.webserviceclient.beans.Item;
import com.flieger.clearsale.webserviceclient.beans.Order;
import com.flieger.clearsale.webserviceclient.beans.Payment;
import com.flieger.clearsale.webserviceclient.beans.PaymentTypeEnum;
import com.flieger.clearsale.webserviceclient.beans.PersonTypeEnum;
import com.flieger.clearsale.webserviceclient.beans.Phone;
import com.flieger.clearsale.webserviceclient.beans.PhoneTypeEnum;
import com.flieger.clearsale.webserviceclient.beans.ProductEnum;
import com.flieger.clearsale.webserviceclient.beans.ReanaliseEnum;
import com.flieger.clearsale.webserviceclient.beans.ShippingData;
import com.flieger.clearsale.webserviceclient.beans.StatusEnumIN;
import com.flieger.clearsale.webserviceclient.responsebeans.PackageStatusResponse;



/**
 * Demonstration of how to write a standalone application that can be run directly from within eclipse or from the
 * commandline.<br>
 * To run this from commandline, just use the following command:<br>
 * <code>
 * java -jar bootstrap/bin/ybootstrap.jar "new com.flieger.ClearsaleantifraudeStandalone().run();"
 * </code> From eclipse, just run as Java Application. Note that you maybe need to add all other projects like
 * ext-commerce, ext-pim to the Launch configuration classpath.
 */
public class ClearsaleantifraudeStandalone
{
	/**
	 * Main class to be able to run it directly as a java program.
	 * 
	 * @param args
	 *           the arguments from commandline
	 */
	public static void main(final String[] args)
	{

		final String currDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date(System.currentTimeMillis()));

		final Item item = new Item();
		item.setID("3216");
		item.setCategoryID(1);
		item.setCategoryName("Categoria Banana");
		item.setGift(1);
		item.setQty(3);
		item.setItemValue(32.00);
		item.setName("Banana Item");

		final List<Item> items = new ArrayList<>();
		items.add(item);

		final Address address = new Address();

		address.setCity("Curitiba");
		address.setComp("Casa");
		address.setCountry("BR");
		address.setCounty("Cidade Industrial");
		address.setNumber("123");
		address.setReference("Esquina das camélias");
		address.setState("PR");
		address.setStreet("Rua das Camélias");
		address.setZipCode(12235136);

		final Payment payment = new Payment();

		payment.setAddress(address);
		payment.setAmount(320.00);
		payment.setCardBin("123");
		payment.setCardEndNumber("1234");
		payment.setCardExpirationDate("12/2013");
		payment.setCardNumber("4444555544445555");
		payment.setCardType(CardTypeEnum.AMERICAN_EXPRESS);
		payment.setCurrency(986);
		payment.setDate(currDate);
		payment.setInterest(0.00);
		payment.setInterestValue(0.00);
		payment.setLegalDocument("41580676936");
		payment.setName("João Barbacena");
		payment.setNsu("12345678998764321");
		payment.setPaymentTypeID(PaymentTypeEnum.CARTAO_DE_CREDITO);
		payment.setQtyInstallments(1);
		payment.setSequential(1);

		final List<Payment> payments = new ArrayList<>();
		payments.add(payment);

		final Phone phone = new Phone();
		phone.setDDD("41");
		phone.setDDI(555);
		phone.setExtension("222");
		phone.setNumber("33332222");
		phone.setType(PhoneTypeEnum.CELULAR);

		final List<Phone> phones = new ArrayList<>();
		phones.add(phone);

		final ShippingData shippingData = new ShippingData();
		shippingData.setAddress(address);
		shippingData.setBirthDate(currDate);
		shippingData.setEmail("joao@joao.com.br");
		shippingData.setGender(GenderEnum.MASCULINO);
		shippingData.setID("12345");
		shippingData.setLegalDocument1("41580676936");
		shippingData.setLegalDocument2("95632458");
		shippingData.setName("João Barbacena");
		shippingData.setType(PersonTypeEnum.PESSOA_FISICA);
		shippingData.setPhones(phones);

		final BillingData billingData = new BillingData();
		billingData.setAddress(address);
		billingData.setBirthDate(shippingData.getBirthDate());
		billingData.setEmail("joao@joao.com.br");
		billingData.setGender(GenderEnum.MASCULINO);
		billingData.setID("12345");
		billingData.setLegalDocument1("41580676936");
		billingData.setLegalDocument2("95632458");
		billingData.setName("João Barbacena");
		billingData.setType(PersonTypeEnum.PESSOA_FISICA);
		billingData.setPhones(phones);

		final FingerPrint fingerPrint = new FingerPrint();
		//	fingerPrint.setSessionID(SessionIDCreator.createSessionID());

		final Order order = new Order();
		order.setB2b_b2c("B2B");
		order.setBillingData(billingData);
		order.setCountryName("BR");
		order.setDate(currDate);
		order.setEmail("joao@joao.com.br");
		order.setDeliveryTimeCD("2");
		order.setFingerPrint(fingerPrint);
		order.setGift(0);
		order.setGiftMessage("Bananas");
		order.setID("1234567");
		order.setIP("192.168.1.1");
		//		//order.setShippingType("bads");***
		order.setItems(items);
		//		order.setListID("123456");***
		//		order.setListTypeID(ListTypeEnum.Lista_Não_Cadastrada);***
		order.setNationality("BReira");
		order.setObs("Nada");
		order.setOrigin("BR");
		order.setPayments(payments);
		order.setProductClearSale(ProductEnum.AClearSale);
		order.setQtyInstallments(1);
		order.setQtyItems(1);
		order.setQtyPaymentTypes(1);
		order.setReanalise(ReanaliseEnum.NAO);
		//		order.setReservationDate(currDate);
		order.setShippingData(shippingData);
		order.setShippingPrice(100.00);
		order.setStatus(StatusEnumIN.ENTRADA_NOVO);
		order.setTotalItemsValue(320.00);
		order.setTotalOrderValue(420.00);
		System.out.println("passou");

		final List<Order> orders = new ArrayList<>();
		orders.add(order);

		final ClearSale clearSale = new ClearSale();
		clearSale.setOrders(orders);

		//		final ClearSaleSOAPServices service = new DefaultClearSaleService();
		final ClearSaleSOAPServices service = new DefaultClearSaleSOAPServices("dzarm");
		//		String xmlRequisição = "";
		//		try
		//		{
		//			xmlRequisição = MarshalToXML.marshal(clearSale);
		//		}
		//		catch (final JAXBException ex)
		//		{
		//			Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
		//		}



		//		final String validatorDir = "/home/flieger/Documents/aurumFull/clearsaleclient/src/de/com/fliegersoftware/clearsale/utils/schema1.xsd";

		//		SendOrdersXMLValidator.validator(validatorDir, clearSale);

		final PackageStatusResponse retorno = service.sendOrders(clearSale);

		//		final PackageStatusResponse pkg = (PackageStatusResponse) UnmarshalToObject.unmarshal(PackageStatusResponse.class, retorno);

		System.out.print(retorno);

		RedeployUtilities.shutdown();

		new ClearsaleantifraudeStandalone().run();
	}

	public void run()
	{
		Registry.activateStandaloneMode();
		Registry.activateMasterTenant();

		final JaloSession jaloSession = JaloSession.getCurrentSession();
		System.out.println("Session ID: " + jaloSession.getSessionID()); //NOPMD
		System.out.println("User: " + jaloSession.getUser()); //NOPMD
		Utilities.printAppInfo();


	}
}
