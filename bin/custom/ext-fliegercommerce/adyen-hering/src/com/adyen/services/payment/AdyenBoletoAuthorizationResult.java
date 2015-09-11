/**
 * 
 */
package com.adyen.services.payment;

import de.hybris.platform.payment.commands.result.AuthorizationResult;

/**
 * @author flieger
 *
 */
public class AdyenBoletoAuthorizationResult extends AuthorizationResult
{
	private String adyenReference;
	private String adyenUniqueCode;
	private String boletoBancarioUrl;
	private String boletoBancarioNossoNumero;
	private String boletoBancarioExpirationDate;
	private String errorCode;
	private String heringAdyenErrorCode;

	/**
	 * @return the errorCode
	 */
	public String getErrorCode()
	{
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
	}

	/**
	 * @return the boletoBancarioExpirationDate
	 */
	public String getBoletoBancarioExpirationDate()
	{
		return boletoBancarioExpirationDate;
	}

	/**
	 * @param boletoBancarioExpirationDate the boletoBancarioExpirationDate to set
	 */
	public void setBoletoBancarioExpirationDate(String boletoBancarioExpirationDate)
	{
		this.boletoBancarioExpirationDate = boletoBancarioExpirationDate;
	}

	/**
	 * @return the boletoBancarioNossoNumero
	 */
	public String getBoletoBancarioNossoNumero()
	{
		return boletoBancarioNossoNumero;
	}

	/**
	 * @param boletoBancarioNossoNumero the boletoBancarioNossoNumero to set
	 */
	public void setBoletoBancarioNossoNumero(String boletoBancarioNossoNumero)
	{
		this.boletoBancarioNossoNumero = boletoBancarioNossoNumero;
	}

	/**
	 * @return the adyenReference
	 */
	public String getAdyenReference()
	{
		return adyenReference;
	}

	/**
	 * @param adyenReference the adyenReference to set
	 */
	public void setAdyenReference(String adyenReference)
	{
		this.adyenReference = adyenReference;
	}

	/**
	 * @return the adyenUniqueCode
	 */
	public String getAdyenUniqueCode()
	{
		return adyenUniqueCode;
	}

	/**
	 * @param adyenUniqueCode the adyenUniqueCode to set
	 */
	public void setAdyenUniqueCode(String adyenUniqueCode)
	{
		this.adyenUniqueCode = adyenUniqueCode;
	}

	/**
	 * @return the boletoBancarioUrl
	 */
	public String getBoletoBancarioUrl()
	{
		return boletoBancarioUrl;
	}

	/**
	 * @param boletoBancarioUrl the boletoBancarioUrl to set
	 */
	public void setBoletoBancarioUrl(String boletoBancarioUrl)
	{
		this.boletoBancarioUrl = boletoBancarioUrl;
	}

	/**
	 * @return the heringAdyenErrorCode
	 */
	public String getHeringAdyenErrorCode()
	{
		return heringAdyenErrorCode;
	}

	/**
	 * @param heringAdyenErrorCode the heringAdyenErrorCode to set
	 */
	public void setHeringAdyenErrorCode(String heringAdyenErrorCode)
	{
		this.heringAdyenErrorCode = heringAdyenErrorCode;
	}

}
