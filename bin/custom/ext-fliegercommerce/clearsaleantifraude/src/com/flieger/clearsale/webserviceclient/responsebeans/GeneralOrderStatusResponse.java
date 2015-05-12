/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.clearsale.webserviceclient.responsebeans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Antony
 */
@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "Order",
		propOrder =
		{
			"transactionID", "statusCode", "message", "ID", "statusOrder", "scoreOrder"
		},
		namespace = ""
)
public class GeneralOrderStatusResponse
{

	@XmlElement(name = "TransactionID", required = false)
	private String transactionID;

	@XmlElement(name = "StatusCode", required = false)
	private String statusCode;

	@XmlElement(name = "Message", required = false)
	private String message;

	@XmlElement(name = "ID", required = false)
	private String ID;

	@XmlElement(name = "Status", required = false)
	private StatusEnumOUT statusOrder;

	@XmlElement(name = "Score", required = false)
	private String scoreOrder;

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

	public String getID()
	{
		return ID;
	}

	public void setID(String ID)
	{
		this.ID = ID;
	}

	public StatusEnumOUT getStatusOrder()
	{
		return statusOrder;
	}

	public void setStatusOrder(StatusEnumOUT statusOrder)
	{
		this.statusOrder = statusOrder;
	}

	public String getScoreOrder()
	{
		return scoreOrder;
	}

	public void setScoreOrder(String scoreOrder)
	{
		this.scoreOrder = scoreOrder;
	}

	@Override
	public String toString()
	{
		return "BillingData{"
				+ "TransactionID='" + transactionID + '\''
				+ ", StatusCode='" + statusCode + '\''
				+ ", Message='" + message + '\''
				+ ", ID='" + ID + '\''
				+ ", Status='" + statusOrder + '\''
				+ ", Score='" + scoreOrder + '\''
				+ '}';
	}
}
