package com.flieger.clearsale.webserviceclient.beans;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * ENUM do tipo Gender, contem os valores para sexo, para mais detalhes ver o
 * manual de integração ClearSale
 *
 * @author Antony
 * @version 1.0
 * @since Release 01 da API
 */
@XmlType(name = "GenreEnum")
@XmlEnum
public enum GenderEnum
{

	@XmlEnumValue("M")
	MASCULINO,
	@XmlEnumValue("F")
	FEMININO

}
