/**
 * 
 */
package com.adyen.services.payment;

import com.adyen.services.common.Amount;

/**
 * @author franthescollymaneira
 *
 */
public class AdyenDebitAuthorizationResult extends AdyenAuthorizationResult
{	
	private String adyenReference;
	private String adyenUniqueCode;
	private String errorCode;
	
	private String issuerUrl;
	private String md;

	private String paRequest;
	private String termUrl;
	
   private String resultCode;
   private Amount amount;
   
   private String mpiImplementationType;
	
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
	 * @return the issuerUrl
	 */
	public String getIssuerUrl()
	{
		return issuerUrl;
	}

	/**
	 * @param issuerUrl the issuerUrl to set
	 */
	public void setIssuerUrl(String issuerUrl)
	{
		this.issuerUrl = issuerUrl;
	}

	/**
	 * @return the md
	 */
	public String getMd()
	{
		return md;
	}

	/**
	 * @param md the md to set
	 */
	public void setMd(String md)
	{
		this.md = md;
	}

	/**
	 * @return the resultCode
	 */
	public String getResultCode()
	{
		return resultCode;
	}

	/**
	 * @param resultCode the resultCode to set
	 */
	public void setResultCode(String resultCode)
	{
		this.resultCode = resultCode;
	}

	/**
	 * @return the amount
	 */
	public Amount getAmount()
	{
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Amount amount)
	{
		this.amount = amount;
	}

	/**
	 * @return the paRequest
	 */
	public String getPaRequest()
	{
		return paRequest;
	}

	/**
	 * @param paRequest the paRequest to set
	 */
	public void setPaRequest(String paRequest)
	{
		this.paRequest = paRequest;
	}

	/**
	 * @return the termUrl
	 */
	public String getTermUrl()
	{
		return termUrl;
	}

	/**
	 * @param termUrl the termUrl to set
	 */
	public void setTermUrl(String termUrl)
	{
		this.termUrl = termUrl;
	}

	public String getMpiImplementationType()
	{
		return mpiImplementationType;
	}

	public void setMpiImplementationType(String mpiImplementationType)
	{
		this.mpiImplementationType = mpiImplementationType;
	}
}