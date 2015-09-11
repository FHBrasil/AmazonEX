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
 * Classe para objetos do tipo Address utilizada para criação do XML enviada ao ClearSale.
 * 
 * @author Antony
 * @version 1.0
 * @since Release 01 da API
 */
@XmlRootElement(name = "Address")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Address", propOrder =
{ "street", "number", "comp", "county", "city", "state", "country", "zipCode", "reference" }, namespace = "")
public class Address
{

	@XmlElement(name = "Street", required = true)
	private String street;

	@XmlElement(name = "Number", required = true)
	private String number;

	/* complement */
	@XmlElement(name = "Comp", required = false)
	private String comp;

	@XmlElement(name = "County", required = true)
	private String county;

	@XmlElement(name = "City", required = true)
	private String city;

	@XmlElement(name = "State", required = true)
	private String state;

	/* complete name of country */
	@XmlElement(name = "Country", required = false)
	private String country;

	@XmlElement(name = "ZipCode", required = true)
	private Integer zipCode;

	@XmlElement(name = "Reference", required = false)
	private String reference;

	public String getStreet()
	{
		return street;
	}

	/**
	 * Este parâmetro é obrigatório
	 * 
	 * @param street
	 *           - logradouro completo incluindo o tipo (Ex: rua, av, alameda etc)
	 * 
	 */
	public void setStreet(final String street)
	{
		this.street = street;
	}

	public String getNumber()
	{
		return number;
	}

	/**
	 * Este parâmetro é obrigatório
	 * 
	 * @param number
	 *           - número do imóvel
	 * 
	 */
	public void setNumber(final String number)
	{
		this.number = number;
	}

	public String getComp()
	{
		return comp;
	}

	/**
	 * Este parâmetro NÃO é obrigatório
	 * 
	 * @param complemento
	 *           - complemento ao logradouro Ex: casa, apt etc.
	 * 
	 */
	public void setComp(final String comp)
	{
		this.comp = comp;
	}

	public String getCounty()
	{
		return county;
	}

	/**
	 * Este parâmetro é obrigatório
	 * 
	 * @param county
	 *           - Bairro do endereço
	 * 
	 */
	public void setCounty(final String county)
	{
		this.county = county;
	}

	public String getCity()
	{
		return city;
	}

	/**
	 * Este parâmetro é obrigatório
	 * 
	 * @param city
	 *           - Cidade onde localiza-se o endereço
	 * 
	 */
	public void setCity(final String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	/**
	 * Este parâmetro é obrigatório
	 * 
	 * @param state
	 *           - Unidade Federativa UF, com apenas 2 caracteres
	 */
	public void setState(final String state)
	{
		this.state = state;
	}

	public String getCountry()
	{
		return country;
	}

	/**
	 * Este parâmetro NÃO é obrigatório
	 * 
	 * @param country
	 *           - País referente ao endereço, com 2 Caracteres. Seguir a lista definida pela norma ISO 3166-1
	 * 
	 */
	public void setCountry(final String country)
	{
		this.country = country;
	}

	public Integer getZipCode()
	{
		return zipCode;
	}

	/**
	 * Este parâmetro é obrigatório
	 * 
	 * @param zipCode
	 *           - Deve conter 8 caracteres numéricos, incluindo zeros a esquerda Seguir valores válidos de acordo com a
	 *           Empresa Brasileira de Correios e Telégrafos (ECT).
	 * 
	 */
	public void setZipCode(final Integer zipCode)
	{
		this.zipCode = zipCode;
	}

	public String getReference()
	{
		return reference;
	}

	/**
	 * Este parâmetro NÃO é obrigatório
	 * 
	 * @param reference
	 *           - Campo livre.
	 * 
	 */
	public void setReference(final String reference)
	{
		this.reference = reference;
	}

	@Override
	public String toString()
	{
		return "Address{" + "Street='" + street + '\'' + ", Number='" + number + '\'' + ", Comp='" + comp + '\'' + ", County='"
				+ county + '\'' + ", City='" + city + '\'' + ", State='" + state + '\'' + ", Country='" + country + '\''
				+ ", ZipCode=" + zipCode + ", Reference='" + reference + '\'' + '}';
	}
}
