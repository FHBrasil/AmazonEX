/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.clearsale.webserviceclient.SOAPservices;

import com.flieger.clearsale.webservice.TransactionStatusCbk;
import com.flieger.clearsale.webserviceclient.beans.ClearSale;
import com.flieger.clearsale.webserviceclient.beans.Order;
import com.flieger.clearsale.webserviceclient.responsebeans.ClearSaleResponse;
import com.flieger.clearsale.webserviceclient.responsebeans.GeneralOrderStatusResponse;
import com.flieger.clearsale.webserviceclient.responsebeans.PackageStatusResponse;
import java.util.List;

/**
 *
 * @author Antony
 */
public interface ClearSaleSOAPServices
{

	String checkOrderStatus(String pedidoIDCliente);

	String getAnalystComments(String orderID, boolean getAll);

	ClearSaleResponse getOrderStatus(String orderID);
 
	ClearSaleResponse getOrdersStatus(List<Order> orders);

	PackageStatusResponse getPackageStatus(String packageID);

	String getQuizURL(String orderID);

	String getReturnAnalysis();

	TransactionStatusCbk orderChargeBack(String xml, String note);

	TransactionStatusCbk orderChargeBackByNsu(int cartaoBandeira, String nsu, String conciliadorCode);

	PackageStatusResponse sendOrders(ClearSale obj);

	String sendOrders2(String pedidos);

	String setOrderAsReturned(String orderID);

	String submitInfo(String xmlDados);

	String sendPaymentNsu(String orderID, String nsu, int cardType, String cardBin, String cardEndNumber);

	GeneralOrderStatusResponse updateOrderStatus(String orderID, String strStatusPedido);
	
	GeneralOrderStatusResponse updateOrderStatusID(String orderID, com.flieger.clearsale.webservice.paymentupdate.StatusPedido statusPedido);
	
}
