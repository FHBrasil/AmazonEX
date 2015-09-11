package com.flieger.clearsale.webserviceclient.beans;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * ENUM do tipo ListType, apesar de estar na documentação da ClearSale recomenda-se nao preencher este atributo pois
 * isso invalida o XML final enviado
 * 
 * @author Antony
 * @version 1.0
 * @since Release 01 da API
 */
@XmlType(name = "ListTypeEnum")
@XmlEnum
public enum ListTypeEnum
{

	@XmlEnumValue("1")
	Lista_Não_Cadastrada, @XmlEnumValue("2")
	Lista_de_Chá_de_Bebê, @XmlEnumValue("3")
	Lista_de_Casamento, @XmlEnumValue("4")
	Lista_de_Desejos, @XmlEnumValue("5")
	Lista_de_Aniversário, @XmlEnumValue("6")
	Lista_de_Chá_Bar_Chá_de_Panela
}
