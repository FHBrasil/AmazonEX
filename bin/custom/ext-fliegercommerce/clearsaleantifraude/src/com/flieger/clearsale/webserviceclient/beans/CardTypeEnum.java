package com.flieger.clearsale.webserviceclient.beans;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * ENUM do tipo CardType contém as bandeiras de cartões de crédito
 * discriminadas no manual de integração
 *
 * @author Antony
 * @version 1.0
 * @since Release 01 da API
 */
@XmlType(name = "CardTypeEnum")
@XmlEnum
public enum CardTypeEnum
{

	@XmlEnumValue("1")
	DINERS,
	@XmlEnumValue("2")
	MASTERCARD,
	@XmlEnumValue("3")
	VISA,
	@XmlEnumValue("4")
	OUTROS,
	@XmlEnumValue("5")
	AMERICAN_EXPRESS,
	@XmlEnumValue("6")
	HIPERCARD,
	@XmlEnumValue("7")
	AURA

}
