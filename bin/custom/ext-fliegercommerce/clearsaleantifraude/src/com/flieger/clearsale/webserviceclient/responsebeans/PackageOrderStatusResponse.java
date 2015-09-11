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
@XmlRootElement(name = "ClearSale")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "ClearSale",
		propOrder =
		{
			"orders"
		},
		namespace = ""
)
public class PackageOrderStatusResponse
{

	@XmlElementWrapper(name = "Orders")
    @XmlElement(name = "Order", required = false)
    private List<GeneralOrderStatusResponse> orders;

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
		return "PackageOrderStatus { "
				+ ", Orders=" + orders
				+ " } ";
	}
}
