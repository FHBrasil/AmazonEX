/**
 * 
 */
package com.adyen.services.payment;

import de.hybris.platform.payment.commands.result.AuthorizationResult;

/**
 * @author flieger
 *
 */
public class AdyenAuthorizationResult extends AuthorizationResult
{
	private String adyenReference;
	private String adyenUniqueCode;
	private String stantBuy;
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
	 * @return the stantBuy
	 */
	public String getStantBuy()
	{
		return stantBuy;
	}

	/**
	 * @param stantBuy the stantBuy to set
	 */
	public void setStantBuy(String stantBuy)
	{
		this.stantBuy = stantBuy;
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
