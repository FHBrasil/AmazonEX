/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.flieger.clearsale.webserviceclient.responsebeans;

import java.util.Collection;
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
			"orders", "packageStatus"
		},
		namespace = ""
)
public class ClearSaleResponse
{
	@XmlElementWrapper(name = "Orders")
    @XmlElement(name = "Order", required = true)
    private Collection<GeneralOrderStatusResponse> orders;
	
	@XmlElement(name = "PackageStatus", required = true)
    private PackageStatusResponse packageStatus;

	public Collection<GeneralOrderStatusResponse> getOrders()
	{
		return orders;
	}

	public void setOrders(Collection<GeneralOrderStatusResponse> orders)
	{
		this.orders = orders;
	}

	public PackageStatusResponse getPackageStatus()
	{
		return packageStatus;
	}

	public void setPackageStatus(PackageStatusResponse packageStatus)
	{
		this.packageStatus = packageStatus;
	}
	
	@Override
	public String toString()
	{
		return "ClearSale{"
				+ "Orders=" + orders
				+ "PackageStatus=" + packageStatus
				+ '}';
	}
}
