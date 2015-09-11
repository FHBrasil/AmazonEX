/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flieger.clearsale.webserviceclient.responsebeans;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Antony
 */
@XmlRootElement(name = "PackageStatus")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "PackageStatus",
		propOrder =
		{
			"transactionID", "statusCode", "message", "orders"
		},
		namespace = ""
)
public class PackageStatusResponse
{

	@XmlElement(name = "TransactionID", required = false)
	private String transactionID;

	@XmlElement(name = "StatusCode", required = false)
	private String statusCode;

	@XmlElement(name = "Message", required = false)
	private String message;
	
	@XmlElementWrapper(name = "Orders")
    @XmlElement(name = "Order", required = false)
    private List<GeneralOrderStatusResponse> orders;

	public String getTransactionID()
	{
		return transactionID;
	}

	public void setTransactionID(String transactionID)
	{
		this.transactionID = transactionID;
	}

	public String getStatusCode()
	{
		return statusCode;
	}

	public void setStatusCode(String statusCode)
	{
		this.statusCode = statusCode;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public List<GeneralOrderStatusResponse> getOrders()
	{
		if(this.orders == null){
			this.orders = new ArrayList<>();
		}
		return orders;
	}

	public void setOrders(List<GeneralOrderStatusResponse> orders)
	{
		this.orders = orders;
	}

	@Override
	public String toString()
	{
		return "PackageStatus{"
				+ "TransactionID='" + transactionID + '\''
				+ ", StatusCode='" + statusCode + '\''
				+ ", Message='" + message + '\''
				+ ", Orders=" + orders
				+ '}';
	}
}
