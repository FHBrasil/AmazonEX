package com.flieger.clearsale.webserviceclient.beans;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Antony
 */
@XmlType(name = "StatusEnum")
@XmlEnum
public enum StatusEnumIN
{
	@XmlEnumValue("0")
	ENTRADA_NOVO,

	@XmlEnumValue("9")
	ENTRADA_APROVADO,

	@XmlEnumValue("41")
	ENTRADA_CANCELADO_PELO_CLIENTE,

	@XmlEnumValue("45")
	ENTRADA_REPROVADO;
}
