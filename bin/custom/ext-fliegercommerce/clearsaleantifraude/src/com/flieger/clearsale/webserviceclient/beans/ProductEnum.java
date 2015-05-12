package com.flieger.clearsale.webserviceclient.beans;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Antony
 */
@XmlType(name = "ProductEnum")
@XmlEnum
public enum ProductEnum {
	
	@XmlEnumValue("-1")
	Outros,
	@XmlEnumValue("1")
	AClearSale,
	@XmlEnumValue("2")
	MClearSale,
	@XmlEnumValue("3")
	TClearSale,
	@XmlEnumValue("4")
	TGClearSale,
	@XmlEnumValue("5")
	THClearSale,
	@XmlEnumValue("6")
	TGLightClearSale,
	@XmlEnumValue("7")
	TGFullClearSale,
	@XmlEnumValue("8")
	TMonitorado,
	@XmlEnumValue("9")
	Score_de_Fraude,
	@XmlEnumValue("10")
	ClearID,
	@XmlEnumValue("11")
	Análise_Internacional
	
}
