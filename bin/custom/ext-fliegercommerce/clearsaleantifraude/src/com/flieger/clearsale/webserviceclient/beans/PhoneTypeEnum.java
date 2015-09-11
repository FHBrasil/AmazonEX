package com.flieger.clearsale.webserviceclient.beans;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Antony
 */
@XmlType(name = "PhoneTypeEnum")
@XmlEnum
public enum PhoneTypeEnum {

	@XmlEnumValue("0")
	NAO_DEFINIDO,
	
	@XmlEnumValue("1")
	RESIDENCIAL,
	
	@XmlEnumValue("2")
	COMERCIAL,
	
	@XmlEnumValue("3")
	RECADOS,
	
	@XmlEnumValue("4")
	COBRANCA,
	
	@XmlEnumValue("5")
	TEMPORARIO,
	
	@XmlEnumValue("6")
	CELULAR
}
