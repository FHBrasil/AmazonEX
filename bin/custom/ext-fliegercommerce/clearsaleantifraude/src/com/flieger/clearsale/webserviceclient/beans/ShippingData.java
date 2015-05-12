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
 *
 * @author Antony
 */
@XmlRootElement(name = "ShippingData")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "ShippingData",
		propOrder =
		{
			"ID", "type", "legalDocument1", "legalDocument2", "name", "birthDate", "email", "gender",
			"address", "phones"
		},
		namespace = ""
)
public class ShippingData
{

	@XmlElement(name = "ID", required = true)
	private String ID;
	
	/*physical or legal*/
	@XmlElement(name = "Type", required = true)
	private PersonTypeEnum type;
	
	/*CPF / CNPJ*/
	@XmlElement(name = "LegalDocument1", required = true)
	private String legalDocument1;
	
	/*RG ou inscrição estadual*/
	@XmlElement(name = "LegalDocument2", required = false)
	private String legalDocument2;
	
	/*client name*/
	@XmlElement(name = "Name", required = true)
	private String name;
	
	@XmlElement(name = "BirthDate", required = false)
	private String birthDate;
	
	@XmlElement(name = "Email", required = false)
	private String email;
	
	@XmlElement(name = "Gender", required = false)
	private GenderEnum gender;
	
	@XmlElement(name = "Address", required = true)
	private Address address;
	
	@XmlElementWrapper(name = "Phones")
    @XmlElement(name = "Phone")
    private List<Phone> phones;

	public String getID()
	{
		return ID;
	}

	/**
	 * Este parâmetro é obrigatório
	 *
	 * @param ID - Código do cliente Ex: UID
	 *
	 */
	public void setID(String ID)
	{
		this.ID = ID;
	}

	public PersonTypeEnum getType()
	{
		return type;
	}

	/**
	 * Este parâmetro é obrigatório
	 *
	 * @param type - Tipo pessoa Fisica ou Juridica
	 *
	 */
	public void setType(PersonTypeEnum type)
	{
		this.type = type;
	}

	public String getLegalDocument1()
	{
		return legalDocument1;
	}

	/**
	 * Este parâmetro é obrigatório
	 *
	 * @param legalDocument1 - CPF, 11 caracteres numéricos válido pelo módulo
	 * 11, incluindo zeros a esquerda ou CNPJ 14 caracteres numéricos válido
	 * pelo módulo 11, incluindo zeros a esquerda
	 */
	public void setLegalDocument1(String legalDocument1)
	{
		this.legalDocument1 = legalDocument1;
	}

	public String getLegalDocument2()
	{
		return legalDocument2;
	}

	/**
	 * Este parâmetro Não é obrigatório
	 *
	 * @param legalDocument2 - Para informações consultar o manual de integração
	 * ClearSale e o Manual de Padroes de dados ClearSale
	 */
	public void setLegalDocument2(String legalDocument2)
	{
		this.legalDocument2 = legalDocument2;
	}

	public String getName()
	{
		return name;
	}

	/**
	 * Este parâmetro é obrigatório
	 *
	 * @param name - Nome para cobrança
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	public String getBirthDate()
	{
		return birthDate;
	}

	/**
	 * Este parâmetro Não é obrigatório
	 *
	 * @param birthDate - Para informações consultar o manual de integração
	 * ClearSale e o Manual de Padroes de dados ClearSale
	 */
	public void setBirthDate(String birthDate)
	{
		this.birthDate = birthDate;
	}

	public String getEmail()
	{
		return email;
	}

	/**
	 * Este parâmetro Não é obrigatório
	 *
	 * @param email - Para informações consultar o manual de integração
	 * ClearSale e o Manual de Padroes de dados ClearSale
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	public GenderEnum getGender()
	{
		return gender;
	}

	/**
	 * Este parâmetro Não é obrigatório
	 *
	 * @param gender - Para informações consultar o manual de integração
	 * ClearSale e o Manual de Padroes de dados ClearSale
	 */
	public void setGender(GenderEnum gender)
	{
		this.gender = gender;
	}

	public Address getAddress()
	{
		return address;
	}

	/**
	 * Este parâmetro é obrigatório
	 *
	 * @param Address - Instancia da entidade Address
	 */
	public void setAddress(Address address)
	{
		this.address = address;
	}

	public List<Phone> getPhones()
	{
		if (this.phones == null)
		{
			this.phones = new ArrayList<>();
		}
		return phones;
	}

	/**
	 * Este parâmetro é obrigatório
	 *
	 * @param phones - Lista de instancias da entidade Phone
	 */
	public void setPhones(List<Phone> phones)
	{
		this.phones = phones;
	}

	@Override
	public String toString()
	{
		return "ShippingData{"
				+ "ID='" + ID + '\''
				+ ", Type=" + type
				+ ", LegalDocument1='" + legalDocument1 + '\''
				+ ", LegalDocument2='" + legalDocument2 + '\''
				+ ", Name='" + name + '\''
				+ ", BirthDate=" + birthDate
				+ ", Email='" + email + '\''
				+ ", Gender='" + gender + '\''
				+ ", Address=" + address
				+ ", Phones=" + phones
				+ '}';
	}
}
