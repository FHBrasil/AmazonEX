/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.clearsale.webserviceclient.SOAPservices.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;

import com.flieger.clearsale.utils.config.ServiceConfig;
import com.flieger.clearsale.utils.marshalers.MarshalToXML;
import com.flieger.clearsale.utils.marshalers.UnmarshalToObject;
import com.flieger.clearsale.webservice.Service;
import com.flieger.clearsale.webservice.TransactionStatusCbk;
import com.flieger.clearsale.webservice.paymentupdate.PaymentIntegration;
import com.flieger.clearsale.webservice.paymentupdate.StatusPedido;
import com.flieger.clearsale.webserviceclient.SOAPservices.ClearSaleSOAPServices;
import com.flieger.clearsale.webserviceclient.beans.ClearSale;
import com.flieger.clearsale.webserviceclient.beans.Order;
import com.flieger.clearsale.webserviceclient.responsebeans.ClearSaleResponse;
import com.flieger.clearsale.webserviceclient.responsebeans.GeneralOrderStatusResponse;
import com.flieger.clearsale.webserviceclient.responsebeans.PackageStatusResponse;


/**
 * 
 * @author Antony
 */
public class DefaultClearSaleSOAPServices implements ClearSaleSOAPServices
{	
	private static final String CLEARSALEANTIFRAUDE_ENTITYID = "clearsaleantifraude.entityid";

	private Service clearsaleWebService;
	private PaymentIntegration clearsaleWebServicePaymentUpdate;
	private final String entityId;
	private static final String DEFAULT_STORE_UID = "dzarm";
	
	public DefaultClearSaleSOAPServices(String baseStoreUid)
	{
		if(StringUtils.isBlank(baseStoreUid)){
			baseStoreUid = DEFAULT_STORE_UID;
		}
		this.entityId = ServiceConfig.getParameter(CLEARSALEANTIFRAUDE_ENTITYID + "." + baseStoreUid);
	}
	
	@Override
	public String checkOrderStatus(final String pedidoIDCliente)
	{
		final com.flieger.clearsale.webservice.Service service = getClearsaleWebService();
		final com.flieger.clearsale.webservice.ServiceSoap port = service.getServiceSoap12();
		return port.checkOrderStatus(entityId, pedidoIDCliente);
	}

	@Override
	public String getAnalystComments(final String orderID, final boolean getAll)
	{
		final com.flieger.clearsale.webservice.Service service = getClearsaleWebService();
		final com.flieger.clearsale.webservice.ServiceSoap port = service.getServiceSoap12();
		return port.getAnalystComments(entityId, orderID, getAll);
	}

	@Override
	public ClearSaleResponse getOrderStatus(final String orderID)
	{
		final com.flieger.clearsale.webservice.Service service = getClearsaleWebService();
		final com.flieger.clearsale.webservice.ServiceSoap port = service.getServiceSoap12();

		final ClearSaleResponse clearsaleResponse = (ClearSaleResponse) UnmarshalToObject.unmarshal(ClearSaleResponse.class,
				port.getOrderStatus(entityId, orderID));

		return clearsaleResponse;

	}

	@Override
	public ClearSaleResponse getOrdersStatus(final List<Order> orders)
	{
		final com.flieger.clearsale.webservice.Service service = getClearsaleWebService();
		final com.flieger.clearsale.webservice.ServiceSoap port = service.getServiceSoap12();

		final ClearSale clearsale = new ClearSale();

		clearsale.setOrders(orders);

		String xml = null;
		try
		{
			xml = MarshalToXML.marshal(clearsale);
		}
		catch (final JAXBException ex)
		{
			Logger.getLogger(DefaultClearSaleSOAPServices.class.getName()).log(Level.SEVERE, null, ex);
		}

		final ClearSaleResponse clearsaleResponse = (ClearSaleResponse) UnmarshalToObject.unmarshal(ClearSaleResponse.class,
				port.getOrdersStatus(entityId, xml));

		return clearsaleResponse;
	}

	@Override
	public PackageStatusResponse getPackageStatus(final String packageID)
	{
		final com.flieger.clearsale.webservice.Service service = getClearsaleWebService();
		final com.flieger.clearsale.webservice.ServiceSoap port = service.getServiceSoap12();

		final String response = port.getPackageStatus(entityId, packageID);
		final PackageStatusResponse pkg = (PackageStatusResponse) UnmarshalToObject
				.unmarshal(PackageStatusResponse.class, response);

		return pkg;
	}

	@Override
	public String getQuizURL(final String orderID)
	{
		final com.flieger.clearsale.webservice.Service service = getClearsaleWebService();
		final com.flieger.clearsale.webservice.ServiceSoap port = service.getServiceSoap12();
		return port.getQuizURL(entityId, orderID);
	}

	@Override
	public String getReturnAnalysis()
	{
		final com.flieger.clearsale.webservice.Service service = getClearsaleWebService();
		final com.flieger.clearsale.webservice.ServiceSoap port = service.getServiceSoap12();
		return port.getReturnAnalysis(entityId);
	}

	@Override
	public TransactionStatusCbk orderChargeBack(final String xml, final String note)
	{
		final com.flieger.clearsale.webservice.Service service = getClearsaleWebService();
		final com.flieger.clearsale.webservice.ServiceSoap port = service.getServiceSoap12();
		return port.orderChargeBack(entityId, xml, note);
	}

	@Override
	public TransactionStatusCbk orderChargeBackByNsu(final int cartaoBandeira, final String nsu, final String conciliadorCode)
	{
		final com.flieger.clearsale.webservice.Service service = getClearsaleWebService();
		final com.flieger.clearsale.webservice.ServiceSoap port = service.getServiceSoap12();
		return port.orderChargeBackByNsu(entityId, cartaoBandeira, nsu, conciliadorCode);
	}

	@Override
	public PackageStatusResponse sendOrders(final ClearSale obj)
	{
		final com.flieger.clearsale.webservice.Service service = getClearsaleWebService();
		final com.flieger.clearsale.webservice.ServiceSoap port = service.getServiceSoap12();

		String xml = null;
		try
		{
			xml = MarshalToXML.marshal(obj);
		}
		catch (final JAXBException ex)
		{
			Logger.getLogger(DefaultClearSaleSOAPServices.class.getName()).log(Level.SEVERE, null, ex);
		}

		final String response = port.sendOrders(entityId, xml);
		final PackageStatusResponse pkg = (PackageStatusResponse) UnmarshalToObject
				.unmarshal(PackageStatusResponse.class, response);

		return pkg;
	}

	@Override
	public String sendOrders2(final String pedidos)
	{
		final com.flieger.clearsale.webservice.Service service = getClearsaleWebService();
		final com.flieger.clearsale.webservice.ServiceSoap port = service.getServiceSoap12();
		return port.sendOrders2(entityId, pedidos);
	}

	@Override
	public String setOrderAsReturned(final String orderID)
	{
		final com.flieger.clearsale.webservice.Service service = getClearsaleWebService();
		final com.flieger.clearsale.webservice.ServiceSoap port = service.getServiceSoap12();
		return port.setOrderAsReturned(entityId, orderID);
	}

	@Override
	public String submitInfo(final String xmlDados)
	{
		final com.flieger.clearsale.webservice.Service service = getClearsaleWebService();
		final com.flieger.clearsale.webservice.ServiceSoap port = service.getServiceSoap12();
		return port.submitInfo(entityId, xmlDados);
	}

	@Override
	public String sendPaymentNsu(final String orderID, final String nsu, final int cardType, final String cardBin,
			final String cardEndNumber)
	{
		final com.flieger.clearsale.webservice.paymentupdate.PaymentIntegration service = getClearsaleWebServicePaymentUpdate();
		final com.flieger.clearsale.webservice.paymentupdate.PaymentIntegrationSoap port = service.getPaymentIntegrationSoap12();
		return port.sendPaymentNsu(entityId, orderID, nsu, cardType, cardBin, cardEndNumber);
	}

	@Override
	public GeneralOrderStatusResponse updateOrderStatus(final String orderID, final String strStatusPedido)
	{
		final com.flieger.clearsale.webservice.paymentupdate.PaymentIntegration service = getClearsaleWebServicePaymentUpdate();
		final com.flieger.clearsale.webservice.paymentupdate.PaymentIntegrationSoap port = service.getPaymentIntegrationSoap12();

		final GeneralOrderStatusResponse response = (GeneralOrderStatusResponse) UnmarshalToObject.unmarshal(
				GeneralOrderStatusResponse.class, port.updateOrderStatus(entityId, orderID, strStatusPedido));

		return response;

	}

	@Override
	public GeneralOrderStatusResponse updateOrderStatusID(final String orderID, final StatusPedido statusPedido)
	{
		final com.flieger.clearsale.webservice.paymentupdate.PaymentIntegration service = getClearsaleWebServicePaymentUpdate();
		final com.flieger.clearsale.webservice.paymentupdate.PaymentIntegrationSoap port = service.getPaymentIntegrationSoap12();

		final GeneralOrderStatusResponse response = (GeneralOrderStatusResponse) UnmarshalToObject.unmarshal(
				GeneralOrderStatusResponse.class, port.updateOrderStatusID(entityId, orderID, statusPedido));

		return response;
	}

	public Service getClearsaleWebService()
	{
		if (clearsaleWebService == null)
		{
			clearsaleWebService = new Service();
		}
		return clearsaleWebService;
	}

	public void setClearsaleWebService(final Service clearsaleWebService)
	{
		this.clearsaleWebService = clearsaleWebService;
	}

	public PaymentIntegration getClearsaleWebServicePaymentUpdate()
	{
		if (clearsaleWebServicePaymentUpdate == null)
		{
			clearsaleWebServicePaymentUpdate = new PaymentIntegration();
		}
		return clearsaleWebServicePaymentUpdate;
	}

	public void setClearsaleWebServicePaymentUpdate(final PaymentIntegration clearsaleWebServicePaymentUpdate)
	{
		this.clearsaleWebServicePaymentUpdate = clearsaleWebServicePaymentUpdate;
	}

}
