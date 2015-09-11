/**
 * 
 */
package com.adyen.services.payment;

import de.hybris.platform.payment.commands.request.AbstractRequest;
import de.hybris.platform.util.Config;

import org.apache.commons.lang.StringUtils;

import com.flieger.main.Credentials;

/**
 * 
 */
public class AbstractAdyenRequest extends AbstractRequest
{
	private static final String DEFAULT_STORE_UID = "dzarm";
	private String merchantAccount;
	/**
	 * @param merchantTransactionCode
	 */
	protected AbstractAdyenRequest(final String merchantTransactionCode,
			final String merchantAccount)
	{
		super(merchantTransactionCode);
		
		this.merchantAccount = merchantAccount;
		if(StringUtils.isBlank(this.merchantAccount))
		{
			this.merchantAccount = Config.getParameter(Credentials.MERCHANT_ACCOUNT + "." + DEFAULT_STORE_UID);
		}
	}
	/**
	 * @return the merchantAccount
	 */
	public String getMerchantAccount()
	{
		return merchantAccount;
	}
	/**
	 * @param merchantAccount the merchantAccount to set
	 */
	public void setMerchantAccount(String merchantAccount)
	{
		this.merchantAccount = merchantAccount;
	}
}
