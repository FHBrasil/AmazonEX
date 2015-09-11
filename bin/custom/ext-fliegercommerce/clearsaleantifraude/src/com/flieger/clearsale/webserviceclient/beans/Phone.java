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
 * Classe para objetos do tipo Phone utilizada para criação do XML enviada ao ClearSale.
 * 
 * @author Antony
 * @version 1.0
 * @since Release 01 da API
 */
@XmlRootElement(name = "Phone")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Phone", propOrder =
{ "type", "DDI", "DDD", "number", "extension" }, namespace = "")
public class Phone
{

	@XmlElement(name = "Type", required = true)
	private PhoneTypeEnum type;

	@XmlElement(name = "DDI", required = false)
	private Integer DDI;

	@XmlElement(name = "DDD", required = true)
	private String DDD;

	@XmlElement(name = "Number", required = true)
	private String number;

	/* ramal */
	@XmlElement(name = "Extension", required = false)
	private String extension;

	public PhoneTypeEnum getType()
	{
		return type;
	}

	/**
	 * Este parâmetro é obrigatório
	 */
	public void setType(final PhoneTypeEnum type)
	{
		this.type = type;
	}

	public Integer getDDI()
	{
		return DDI;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setDDI(final Integer DDI)
	{
		this.DDI = DDI;
	}

	public String getDDD()
	{
		return DDD;
	}

	/**
	 * Este parâmetro é obrigatório
	 * 
	 * @param DDD
	 *           - Deve conter 2 dígitos, excluindo eventuais zeros a esquerda
	 * 
	 * 
	 */
	public void setDDD(final String DDD)
	{
		this.DDD = DDD;
	}

	public String getNumber()
	{
		return number;
	}

	/**
	 * Este parâmetro é obrigatório
	 * 
	 * @param number
	 *           - Deve conter apenas 8 dígitos, excluindo eventuais zeros a esquerda No caso de aparelhos do tipo
	 *           “móvel” dentro do DDD 11, o número deverá possuir 9 dígitos, seguindo a resolução 553 da Agência
	 *           Nacional de Telecomunicações
	 * 
	 * 
	 */
	public void setNumber(final String number)
	{
		this.number = number;
	}

	public String getExtension()
	{
		return extension;
	}

	/**
	 * Este parâmetro NÃO é obrigatório. Para mais detalhes ver o Manual de integração ClearSale
	 * 
	 */
	public void setExtension(final String extension)
	{
		this.extension = extension;
	}

	@Override
	public String toString()
	{
		return "Phone{" + ", Type=" + type + ", DDI=" + DDI + ", DDD=" + DDD + ", Number=" + number + ", Extension='" + extension
				+ '\'' + '}';
	}

}
