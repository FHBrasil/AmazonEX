/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.clearsale.webserviceclient.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Classe para objetos do tipo Item utilizada para criação do XML enviada ao ClearSale. Para mais detalhes ver o Manual
 * de integração ClearSale
 * 
 * @author Antony
 * @version 1.0
 * @since Release 01 da API
 */
@XmlRootElement(name = "Item")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Item", propOrder =
{ "ID", "name", "itemValue", "qty", "gift", "categoryID", "categoryName" }, namespace = "")
public class Item
{

	@XmlElement(name = "ID", required = true)
	private String ID;

	@XmlElement(name = "Name", required = true)
	private String name;

	@XmlElement(name = "ItemValue", required = true)
	private Double itemValue;

	@XmlElement(name = "Qty", required = true)
	private Integer qty;

	@XmlElement(name = "Gift", required = false)
	private Integer gift;

	@XmlElement(name = "CategoryID", required = false)
	private Integer categoryID;

	@XmlElement(name = "CategoryName", required = false)
	private String categoryName;

	public String getID()
	{
		return ID;
	}

	/**
	 * Este parâmetro é obrigatório
	 * 
	 * @param ID
	 *           - Código do produto
	 * 
	 */
	public void setID(final String ID)
	{
		this.ID = ID;
	}

	public String getName()
	{
		return name;
	}

	/**
	 * Este parâmetro é obrigatório
	 * 
	 * @param name
	 *           - Nome do Produto
	 * 
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

	public Double getItemValue()
	{
		return itemValue;
	}

	/**
	 * Este parâmetro é obrigatório
	 * 
	 * @param itemValue
	 *           - Valor do produto
	 * 
	 */
	public void setItemValue(final Double itemValue)
	{
		this.itemValue = itemValue;
	}

	public Integer getQty()
	{
		return qty;
	}

	/**
	 * Este parâmetro é obrigatório
	 * 
	 * @param qty
	 *           - Quantidade do produto
	 * 
	 */
	public void setQty(final Integer qty)
	{
		this.qty = qty;
	}

	public Integer getGift()
	{
		return gift;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setGift(final Integer gift)
	{
		this.gift = gift;
	}

	public Integer getCategoryID()
	{
		return categoryID;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setCategoryID(final Integer categoryID)
	{
		this.categoryID = categoryID;
	}

	public String getCategoryName()
	{
		return categoryName;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setCategoryName(final String categoryName)
	{
		this.categoryName = categoryName;
	}

	@Override
	public String toString()
	{
		return "Item{" + "ID='" + ID + '\'' + ", Name='" + name + '\'' + ", ItemValue=" + itemValue + ", Qty=" + qty + ", Gift="
				+ gift + ", CategoryID=" + categoryID + ", CategoryName='" + categoryName + '\'' + '}';
	}

}
