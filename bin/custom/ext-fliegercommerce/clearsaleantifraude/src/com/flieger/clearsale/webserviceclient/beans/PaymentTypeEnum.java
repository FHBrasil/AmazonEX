package com.flieger.clearsale.webserviceclient.beans;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Antony
 */
@XmlType(name = "PaymentTypeEnum")
@XmlEnum
public enum PaymentTypeEnum {
	
	@XmlEnumValue("1")
	CARTAO_DE_CREDITO,
	
	@XmlEnumValue("2")
	BOLETO_BANCARIO,
	
	@XmlEnumValue("3")
	DEBITO_BANCARIO,
	
	@XmlEnumValue("4")
	DEBITO_BANCARIO_DINHEIRO,
	
	@XmlEnumValue("5")
	DEBITO_BANCARIO_CHEQUE,
	
	@XmlEnumValue("6")
	TRANSFERENCIA_BANCARIA,
	
	@XmlEnumValue("7")
	SEDEX_A_COBRAR,
	
	@XmlEnumValue("8")
	CHEQUE,
	
	@XmlEnumValue("9")
	DINHEIRO,
	
	@XmlEnumValue("10")
	FINANCIAMENTO,
	
	@XmlEnumValue("11")
	FATURA,
	
	@XmlEnumValue("12")
	CUPOM,
	
	@XmlEnumValue("13")
	MULTICHEQUE,
	
	@XmlEnumValue("14")
	OUTROS
	
}
