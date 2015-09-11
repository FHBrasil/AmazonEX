/**
 * 
 */
package com.adyen.services.payment;

import de.hybris.platform.payment.commands.request.AbstractRequest;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author flieger
 *
 */
public class AdyenCaptureRequest extends AbstractRequest
{
	private String paymentProvider;
	private String merchantAccount;
	private Currency currency;
	private BigDecimal value;
	private String reference;
	private String authorizationCode;
	
	/**
	 * @param paymentProvider
	 * @param merchantTransactionCode
	 * @param merchantAccount
	 * @param currency
	 * @param value
	 * @param reference
 	 * @param authorizationCode
	 */
	public AdyenCaptureRequest(final String paymentProvider, 
			final String merchantTransactionCode, 
			final String merchantAccount, 
			final Currency currency, 
			final BigDecimal value,
			final String reference, 
			final String authorizationCode)
	{
		super(merchantTransactionCode);
		this.paymentProvider = paymentProvider;
		this.merchantAccount = merchantAccount;
		this.currency = currency;
		this.value = value;
		this.reference = reference;
		this.authorizationCode = authorizationCode;
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

	/**
	 * @return the currency
	 */
	public Currency getCurrency()
	{
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}

	/**
	 * @return the reference
	 */
	public String getReference()
	{
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference)
	{
		this.reference = reference;
	}

	/**
	 * @return the value
	 */
	public BigDecimal getValue()
	{
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(BigDecimal value)
	{
		this.value = value;
	}

	/**
	 * @return the paymentProvider
	 */
	public String getPaymentProvider()
	{
		return paymentProvider;
	}

	/**
	 * @param paymentProvider the paymentProvider to set
	 */
	public void setPaymentProvider(String paymentProvider)
	{
		this.paymentProvider = paymentProvider;
	}

	/**
	 * @return the authorizationCode
	 */
	public String getAuthorizationCode()
	{
		return authorizationCode;
	}

	/**
	 * @param authorizationCode the authorizationCode to set
	 */
	public void setAuthorizationCode(String authorizationCode)
	{
		this.authorizationCode = authorizationCode;
	}

}
