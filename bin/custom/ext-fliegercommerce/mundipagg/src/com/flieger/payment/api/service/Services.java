/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flieger.payment.api.service;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.Dispatch;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Service;
import java.io.StringReader;
import com.flieger.payment.api.data.schemas.CreateOrderRequest;
import com.flieger.payment.api.data.schemas.CreateOrderResponse;
import com.flieger.payment.api.data.schemas.GetInstantBuyDataRequest;
import com.flieger.payment.api.data.schemas.GetInstantBuyDataResponse;
import com.flieger.payment.api.data.schemas.ManageOrderRequest;
import com.flieger.payment.api.data.schemas.ManageOrderResponse;
import com.flieger.payment.api.data.schemas.QueryOrderRequest;
import com.flieger.payment.api.data.schemas.QueryOrderResponse;
import com.flieger.payment.api.data.schemas.RetryOrderRequest;
import com.flieger.payment.api.data.schemas.RetryOrderResponse;
import com.flieger.payment.api.tempuri.MundiPaggService;
import com.flieger.payment.api.tempuri.MundiPaggService_Service;

/**
 *
 * @author flieger
 */
public class Services 
{

	
	public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest)
	{
		
		MundiPaggService_Service service = new MundiPaggService_Service();
		MundiPaggService port = service.getBasicHttp();
		return port.createOrder(createOrderRequest);
		
	}

	
	public ManageOrderResponse manageOrder(ManageOrderRequest manageOrderRequest)
	{
		MundiPaggService_Service service = new MundiPaggService_Service();
		MundiPaggService port = service.getBasicHttp();
		return port.manageOrder(manageOrderRequest);
	}

	
	public QueryOrderResponse queryOrder(QueryOrderRequest queryOrderRequest)
	{
		MundiPaggService_Service service = new MundiPaggService_Service();
		MundiPaggService port = service.getBasicHttp();
		return port.queryOrder(queryOrderRequest);
	}

	
	public GetInstantBuyDataResponse getInstantBuyData(GetInstantBuyDataRequest queryCreditCardDataRequest)
	{
		MundiPaggService_Service service = new MundiPaggService_Service();
		MundiPaggService port = service.getBasicHttp();
		return port.getInstantBuyData(queryCreditCardDataRequest);
	}

	
	public RetryOrderResponse retryOrder(RetryOrderRequest manualRetryRequest)
	{
		MundiPaggService_Service service = new MundiPaggService_Service();
		MundiPaggService port = service.getBasicHttp();
		return port.retryOrder(manualRetryRequest);
	}

	
}
