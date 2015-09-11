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
 * Classe para objetos do tipo Payment utilizada para criação do XML enviada ao ClearSale.
 * 
 * @author Antony
 * @version 1.0
 * @since Release 01 da API
 */
@XmlRootElement(name = "Payment")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Payment", propOrder =
{ "sequential", "date", "amount", "paymentTypeID", "qtyInstallments", "interest", "interestValue", "cardNumber", "cardBin",
		"cardEndNumber", "cardType", "cardExpirationDate", "name", "legalDocument", "address", "nsu", "currency" }, namespace = "")
public class Payment
{

	@XmlElement(name = "Sequential", required = false)
	private Integer sequential;

	@XmlElement(name = "Date", required = true)
	private String date;

	@XmlElement(name = "Amount", required = true)
	private Double amount;

	@XmlElement(name = "PaymentTypeID", required = true)
	private PaymentTypeEnum paymentTypeID;

	/* number of installments */
	@XmlElement(name = "QtyInstallments", required = false)
	private Integer qtyInstallments;

	/* tax */
	@XmlElement(name = "Interest", required = false)
	private Double interest;

	/* interest value */
	@XmlElement(name = "InterestValue", required = false)
	private Double interestValue;

	@XmlElement(name = "CardNumber", required = false)
	private String cardNumber;

	@XmlElement(name = "CardBin", required = false)
	private String cardBin;

	@XmlElement(name = "CardEndNumber", required = false)
	private String cardEndNumber;

	@XmlElement(name = "CardType", required = false)
	private CardTypeEnum cardType;

	@XmlElement(name = "CardExpirationDate", required = false)
	private String cardExpirationDate;

	/* billing name */
	@XmlElement(name = "Name", required = false)
	private String name;

	@XmlElement(name = "LegalDocument", required = false)
	private String legalDocument;

	@XmlElement(name = "Address", required = false)
	private Address address;

	/* identification number of transaction */
	@XmlElement(name = "Nsu", required = false)
	private String nsu;

	@XmlElement(name = "Currency", required = false)
	private Integer currency;

	public Integer getSequential()
	{
		return sequential;
	}

	public void setSequential(final Integer sequential)
	{
		this.sequential = sequential;
	}

	public String getDate()
	{
		return date;
	}

	/**
	 * Este parâmetro é obrigatório
	 * 
	 * @param date
	 *           - Data do pagamento
	 * 
	 */
	public void setDate(final String date)
	{
		this.date = date;
	}

	public Double getAmount()
	{
		return amount;
	}

	/**
	 * Este parâmetro é obrigatório
	 * 
	 * @param amount
	 *           - Valor do pagamento
	 * 
	 */
	public void setAmount(final Double amount)
	{
		this.amount = amount;
	}

	public PaymentTypeEnum getPaymentTypeID()
	{
		return paymentTypeID;
	}

	/**
	 * Este parâmetro é obrigatório
	 * 
	 * @param paymentTypeID
	 *           - id do tipo de pagamento
	 * 
	 */
	public void setPaymentTypeID(final PaymentTypeEnum paymentTypeID)
	{
		this.paymentTypeID = paymentTypeID;
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

	public Double getInterest()
	{
		return interest;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setInterest(final Double interest)
	{
		this.interest = interest;
	}

	public Double getInterestValue()
	{
		return interestValue;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setInterestValue(final Double interestValue)
	{
		this.interestValue = interestValue;
	}

	public String getCardNumber()
	{
		return cardNumber;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setCardNumber(final String cardNumber)
	{
		this.cardNumber = cardNumber;
	}

	public String getCardBin()
	{
		return cardBin;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setCardBin(final String cardBin)
	{
		this.cardBin = cardBin;
	}

	public String getCardEndNumber()
	{
		return cardEndNumber;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setCardEndNumber(final String cardEndNumber)
	{
		this.cardEndNumber = cardEndNumber;
	}

	public CardTypeEnum getCardType()
	{
		return cardType;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setCardType(final CardTypeEnum cardType)
	{
		this.cardType = cardType;
	}

	public String getCardExpirationDate()
	{
		return cardExpirationDate;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setCardExpirationDate(final String cardExpirationDate)
	{
		this.cardExpirationDate = cardExpirationDate;
	}

	public String getName()
	{
		return name;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

	public String getLegalDocument()
	{
		return legalDocument;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setLegalDocument(final String legalDocument)
	{
		this.legalDocument = legalDocument;
	}

	public Address getAddress()
	{
		return address;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setAddress(final Address address)
	{
		this.address = address;
	}

	public String getNsu()
	{
		return nsu;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setNsu(final String nsu)
	{
		this.nsu = nsu;
	}

	public Integer getCurrency()
	{
		return currency;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setCurrency(final Integer currency)
	{
		this.currency = currency;
	}

	@Override
	public String toString()
	{
		return "Payment{" + "Sequential=" + sequential + ", Date=" + date + ", Amount=" + amount + ", PaymentTypeID="
				+ paymentTypeID + ", QtyInstallments=" + qtyInstallments + ", Interest=" + interest + ", InterestValue="
				+ interestValue + ", CardNumber='" + cardNumber + '\'' + ", CardBin='" + cardBin + '\'' + ", CardEndNumber='"
				+ cardEndNumber + '\'' + ", CardType='" + cardType + '\'' + ", CardExpirationDate='" + cardExpirationDate + '\''
				+ ", Name='" + name + '\'' + ", LegalDocument='" + legalDocument + '\'' + ", Address=" + address + ", Nsu='" + nsu
				+ '\'' + ", Currency=" + currency + '}';
	}

}
