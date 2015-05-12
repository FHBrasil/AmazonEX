package com.flieger.clearsale.webserviceclient.beans;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Antony
 */
@XmlType(name = "ReanaliseEnum")
@XmlEnum
public enum ReanaliseEnum {

	@XmlEnumValue("1")
	SIM,
	
	@XmlEnumValue("0")
	NAO;
	
}
