/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.hering.core.payment.dto;

import de.hybris.platform.payment.dto.CardInfo;

/**
 *
 * @author Antony
 */
public class KpCardInfo extends CardInfo
{

	private String installments;

	public String getInstallments()
	{
		return installments;
	}

	public void setInstallments(String installments)
	{
		this.installments = installments;
	}

	public void copy(KpCardInfo orig)
	{
		super.copy(orig);

		this.installments= orig.installments;
	}
}

