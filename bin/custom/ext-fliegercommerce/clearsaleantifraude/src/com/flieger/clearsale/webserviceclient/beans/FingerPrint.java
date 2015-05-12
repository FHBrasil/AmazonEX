/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.clearsale.webserviceclient.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Classe para objetos do tipo FingerPrint utilizada para criação do XML enviada
 * ao ClearSale. Para mais detalhes ver o Manual de integração ClearSale
 *
 * @author Antony
 * @version 1.0
 * @since Release 01 da API
 */
@XmlRootElement(name = "FingerPrint")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "FingerPrint",
		propOrder =
		{
			"sessionID"
		},
		namespace = ""
)
public class FingerPrint
{

	@XmlElement(name = "SessionID", required = true)
	private String sessionID;

	public String getSessionID()
	{
		return sessionID;
	}

	/**
	 * Este parâmetro é obrigatório
	 *
	 * @param sessionID - Identificador da sessão do usuário
	 */
	public void setSessionID(String sessionID)
	{
		this.sessionID = sessionID;
	}

	@Override
	public String toString()
	{
		return "FingerPrint{" + "SessionID='" + sessionID + '\'' + '}';
	}

}
