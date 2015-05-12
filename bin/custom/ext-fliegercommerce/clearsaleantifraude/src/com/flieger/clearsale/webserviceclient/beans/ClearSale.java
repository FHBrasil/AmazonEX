/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.clearsale.webserviceclient.beans;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Classe para objetos do tipo ClearSale utilizada para criação do XML enviada
 * ao ClearSale. Esta classe agrega todos os objetos a serem enviados
 *
 * @author Antony
 * @version 1.0
 * @since Release 01 da API
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
public class ClearSale
{

	@XmlElementWrapper(name = "Orders")
	@XmlElement(name = "Order", required = true)
	private List<Order> orders;

	public List<Order> getOrders()
	{
		if (this.orders == null)
		{
			this.orders = new ArrayList<>();
		}
		return orders;
	}

	/**
	 * Este parâmetro é obrigatório
	 *
	 * @param orders - Lista de instancias da entidade Order a serem verificadas
	 * pela ClearSale
	 */
	public void setOrders(List<Order> orders)
	{
		this.orders = orders;
	}

	@Override
	public String toString()
	{
		return "ClearSale{"
				+ "Orders=" + orders
				+ '}';
	}
}
