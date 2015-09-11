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
 * Classe para objetos do tipo Order utilizada para criação do XML enviada ao ClearSale.
 * 
 * @author Antony
 * @version 1.0
 * @since Release 01 da API
 */
@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Order", propOrder =
{ "fingerPrint", "ID", "date", "email", "b2b_b2c", "shippingPrice", "TotalItemsValue", "totalOrderValue", "qtyInstallments",
		"deliveryTimeCD", "qtyItems", "qtyPaymentTypes", "IP", "shippingType", "gift", "giftMessage", "obs", "status", "reanalise",
		"origin", "reservationDate", "countryName", "nationality", "productClearSale", "listTypeID", "listID", "billingData",
		"shippingData", "payments", "items" }, namespace = "")
public class Order
{

	@XmlElement(name = "ID", required = true)
	private String ID;

	@XmlElement(name = "FingerPrint", required = true)
	private FingerPrint fingerPrint;

	@XmlElement(name = "Date", required = true)
	private String date;

	@XmlElement(name = "Email", required = true)
	private String email;

	@XmlElement(name = "B2B_B2C", required = false)
	private String b2b_b2c;

	@XmlElement(name = "ShippingPrice", required = false)
	private Double shippingPrice;

	@XmlElement(name = "TotalItems", required = true)
	private Double TotalItemsValue;

	@XmlElement(name = "TotalOrder", required = true)
	private Double totalOrderValue;

	@XmlElement(name = "QtyInstallments", required = false)
	private Integer qtyInstallments;

	@XmlElement(name = "DeliveryTimeCD", required = false)
	private String deliveryTimeCD;

	@XmlElement(name = "QtyItems", required = false)
	private Integer qtyItems;

	@XmlElement(name = "QtyPaymentTypes", required = false)
	private Integer qtyPaymentTypes;

	/* ip buyer */
	@XmlElement(name = "IP", required = false)
	private String IP;

	@XmlElement(name = "ShippingType", required = false)
	private String shippingType;

	@XmlElement(name = "Gift", required = false)
	private Integer gift;

	@XmlElement(name = "GiftMessage", required = false)
	private String giftMessage;

	@XmlElement(name = "Obs", required = false)
	private String obs;

	/* if this field is empty, the default value is "new" */
	@XmlElement(name = "Status", required = false)
	private StatusEnumIN status;

	@XmlElement(name = "Reanalise", required = false)
	private ReanaliseEnum reanalise;

	@XmlElement(name = "Origin", required = false)
	private String origin;

	/* only airline companies */
	@XmlElement(name = "ReservationDate", required = false)
	private String reservationDate;

	/* only requests international analysis */
	@XmlElement(name = "Country", required = false)
	private String countryName;

	/* only requests international analysis */
	@XmlElement(name = "Nationality", required = false)
	private String nationality;

	/*
	 * only requests international analysis, or customers that use more clearsale's products
	 */
	@XmlElement(name = "Product", required = false)
	private ProductEnum productClearSale;

	/* Ex: wishlist */
	@XmlElement(name = "ListTypeID", required = false)
	private ListTypeEnum listTypeID;

	/* id id of list on store, Ex: id wishlist in babyartykel */
	@XmlElement(name = "ListID", required = false)
	private String listID;

	@XmlElement(name = "BillingData", required = false)
	private BillingData billingData;

	@XmlElement(name = "ShippingData", required = false)
	private ShippingData shippingData;

	@XmlElementWrapper(name = "Payments")
	@XmlElement(name = "Payment", required = true)
	private List<Payment> payments;

	@XmlElementWrapper(name = "Items")
	@XmlElement(name = "Item", required = true)
	private List<Item> items;

	public FingerPrint getFingerPrint()
	{
		return fingerPrint;
	}

	/**
	 * Este parâmetro é obrigatório
	 * 
	 */
	public void setFingerPrint(final FingerPrint fingerPrint)
	{
		this.fingerPrint = fingerPrint;
	}

	public String getID()
	{
		return ID;
	}

	/**
	 * Este parâmetro é obrigatório
	 * 
	 * @param ID
	 *           - Código do pedido
	 * 
	 */
	public void setID(final String ID)
	{
		this.ID = ID;
	}

	public String getDate()
	{
		return date;
	}

	/**
	 * Este parâmetro é obrigatório
	 * 
	 * @param date
	 *           - Data do pedido
	 * 
	 */
	public void setDate(final String date)
	{
		this.date = date;
	}

	public String getEmail()
	{
		return email;
	}

	/**
	 * Este parâmetro é obrigatório
	 * 
	 * @param email
	 *           - Email do cliente
	 * 
	 */
	public void setEmail(final String email)
	{
		this.email = email;
	}

	public String getB2b_b2c()
	{
		return b2b_b2c;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setB2b_b2c(final String b2b_b2c)
	{
		this.b2b_b2c = b2b_b2c;
	}

	public Double getShippingPrice()
	{
		return shippingPrice;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setShippingPrice(final Double shippingPrice)
	{
		this.shippingPrice = shippingPrice;
	}

	public Double getTotalItemsValue()
	{
		return TotalItemsValue;
	}

	/**
	 * Este parâmetro é obrigatório
	 * 
	 * @param TotalItemsValue
	 *           - Valor total da soma dos items enviados
	 * 
	 */
	public void setTotalItemsValue(final Double TotalItemsValue)
	{
		this.TotalItemsValue = TotalItemsValue;
	}

	public Double getTotalOrderValue()
	{
		return totalOrderValue;
	}

	/**
	 * Este parâmetro é obrigatório
	 * 
	 * @param totalOrderValue
	 *           - Valor final do pedido, incluindo frete e outros possiveis encargos
	 * 
	 */
	public void setTotalOrderValue(final Double totalOrderValue)
	{
		this.totalOrderValue = totalOrderValue;
	}

	public Integer getQtyInstallments()
	{
		return qtyInstallments;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setQtyInstallments(final Integer qtyInstallments)
	{
		this.qtyInstallments = qtyInstallments;
	}

	public String getDeliveryTimeCD()
	{
		return deliveryTimeCD;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setDeliveryTimeCD(final String deliveryTimeCD)
	{
		this.deliveryTimeCD = deliveryTimeCD;
	}

	public Integer getQtyItems()
	{
		return qtyItems;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setQtyItems(final Integer qtyItems)
	{
		this.qtyItems = qtyItems;
	}

	public Integer getQtyPaymentTypes()
	{
		return qtyPaymentTypes;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setQtyPaymentTypes(final Integer qtyPaymentTypes)
	{
		this.qtyPaymentTypes = qtyPaymentTypes;
	}

	public String getIP()
	{
		return IP;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setIP(final String IP)
	{
		this.IP = IP;
	}

	public String getShippingType()
	{
		return shippingType;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setShippingType(final String shippingType)
	{
		this.shippingType = shippingType;
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

	public String getGiftMessage()
	{
		return giftMessage;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setGiftMessage(final String giftMessage)
	{
		this.giftMessage = giftMessage;
	}

	public String getObs()
	{
		return obs;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setObs(final String obs)
	{
		this.obs = obs;
	}

	public StatusEnumIN getStatus()
	{
		return status;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 * @param status
	 *           - caso o campo esteja vazio o valor padrao é NOVO
	 */
	public void setStatus(final StatusEnumIN status)
	{
		this.status = status;
	}

	public ReanaliseEnum getReanalise()
	{
		return reanalise;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setReanalise(final ReanaliseEnum reanalise)
	{
		this.reanalise = reanalise;
	}

	public String getOrigin()
	{
		return origin;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setOrigin(final String origin)
	{
		this.origin = origin;
	}

	public String getReservationDate()
	{
		return reservationDate;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale - APENAS EMPRESAS DE
	 * VIAGENS
	 * 
	 */
	public void setReservationDate(final String reservationDate)
	{
		this.reservationDate = reservationDate;
	}

	public String getCountryName()
	{
		return countryName;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setCountryName(final String countryName)
	{
		this.countryName = countryName;
	}

	public String getNationality()
	{
		return nationality;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setNationality(final String nationality)
	{
		this.nationality = nationality;
	}

	public ProductEnum getProductClearSale()
	{
		return productClearSale;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale - Apenas Empresas que
	 * utilizam mais de um produto ClearSale
	 * 
	 */
	public void setProductClearSale(final ProductEnum productClearSale)
	{
		this.productClearSale = productClearSale;
	}

	public ListTypeEnum getListTypeID()
	{
		return listTypeID;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. NÃO DEVE SER USADO POIS INVALIDA O XML
	 * 
	 */
	public void setListTypeID(final ListTypeEnum listTypeID)
	{
		this.listTypeID = listTypeID;
	}

	public String getListID()
	{
		return listID;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. NÃO DEVE SER USADO POIS INVALIDA O XML
	 * 
	 */
	public void setListID(final String listID)
	{
		this.listID = listID;
	}

	public BillingData getBillingData()
	{
		return billingData;
	}

	public void setBillingData(final BillingData billingData)
	{
		this.billingData = billingData;
	}

	public ShippingData getShippingData()
	{
		return shippingData;
	}

	public void setShippingData(final ShippingData shippingData)
	{
		this.shippingData = shippingData;
	}

	public List<Payment> getPayments()
	{
		if (this.payments == null)
		{
			this.payments = new ArrayList<>();
		}
		return payments;
	}

	public void setPayments(final List<Payment> payments)
	{
		this.payments = payments;
	}

	public List<Item> getItems()
	{
		if (this.items == null)
		{
			this.items = new ArrayList<>();
		}
		return items;
	}

	public void setItems(final List<Item> items)
	{
		this.items = items;
	}

	@Override
	public String toString()
	{
		return "Order{" + "ID=" + fingerPrint + "FingerPrint='" + ID + '\'' + ", Date=" + date + ", Email='" + email + '\''
				+ ", B2B_B2C='" + b2b_b2c + '\'' + ", ShippingPrice=" + shippingPrice + ", TotalItems=" + TotalItemsValue
				+ ", TotalOrder=" + totalOrderValue + ", QtyInstallments=" + qtyInstallments + ", DeliveryTimeCD='" + deliveryTimeCD
				+ '\'' + ", QtyItems=" + qtyItems + ", QtyPaymentTypes=" + qtyPaymentTypes + ", IP=" + IP + ", ShippingType='"
				+ shippingType + '\'' + ", Gift=" + gift + ", GiftMessage='" + giftMessage + '\'' + ", Obs='" + obs + '\''
				+ ", Status=" + status + ", Reanalise=" + reanalise + ", Origin='" + origin + '\'' + ", ReservationDate="
				+ reservationDate + ", Country='" + countryName + '\'' + ", Nationality='" + nationality + '\'' + ", Product="
				+ productClearSale + ", ListTypeID=" + listTypeID + ", ListID='" + listID + '\'' + ", BillingData=" + billingData
				+ ", ShippingData=" + shippingData + ", Payments=" + payments + ", Items=" + items + '}';
	}
}
