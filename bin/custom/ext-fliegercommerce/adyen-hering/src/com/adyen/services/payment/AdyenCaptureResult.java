/**
 * 
 */
package com.adyen.services.payment;

import de.hybris.platform.payment.commands.result.CaptureResult;

/**
 * @author flieger
 *
 */
public class AdyenCaptureResult extends CaptureResult
{
	private String adyenReference;
	private String adyenUniqueCode;

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
	
}
