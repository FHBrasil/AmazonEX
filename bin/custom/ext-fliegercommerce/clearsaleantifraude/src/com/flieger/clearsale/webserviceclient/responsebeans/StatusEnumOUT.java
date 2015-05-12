package com.flieger.clearsale.webserviceclient.responsebeans;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Antony
 */
@XmlType(name = "StatusEnum")
@XmlEnum
public enum StatusEnumOUT
{

	// STATUS DE SAIDA

	@XmlEnumValue("APA")
	APROVACAO_AUTOMATICA,

	@XmlEnumValue("APM")
	APROVACAO_MANUAL,

	@XmlEnumValue("RPM")
	REPROVADO_SEM_SUSPEITA,

	@XmlEnumValue("AMA")
	ANALISE_MANUAL,

	@XmlEnumValue("ERR")
	ERRO,

	@XmlEnumValue("NVO")
	NOVO,

	@XmlEnumValue("SUS")
	SUSPENSAO_MANUAL,

	@XmlEnumValue("CAN")
	CANCELADO_PELO_CLIENTE,

	@XmlEnumValue("FRD")
	FRAUDE_CONFIRMADA,

	@XmlEnumValue("RPA")
	REPROVACAO_AUTOMATICA,

	@XmlEnumValue("PGR")
	PAGAMENTO_REPROVADO,

	@XmlEnumValue("PGA")
	PAGAMENTO_APROVADO,

}
