package com.flieger.clearsale.webserviceclient.beans;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Antony
 */
@XmlType(name = "CollectionDataTypeEnum")
@XmlEnum
public enum PersonTypeEnum {

	@XmlEnumValue("1")
	PESSOA_FISICA,
	
	@XmlEnumValue("2")
	PESSOA_JURIDICA
	
}
