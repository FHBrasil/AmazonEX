/**
 * 
 */
package com.adyen.services.recurring;

import de.hybris.platform.payment.commands.request.AbstractRequest;

import com.adyen.services.payment.AbstractAdyenRequest;

/**
 * @author flieger
 *
 */
public class AdyenRecurringListDetailsRequest extends AbstractAdyenRequest
{
	private String paymentProvider;
	private String shopperReference;
	private String shopperId;
	/**
	 * @param paymentProvider
	 * @param merchantTransactionCode
	 * @param shopperReference
	 * @param shopperId
	 */
	public AdyenRecurringListDetailsRequest(String paymentProvider, 
			String merchantTransactionCode, String shopperReference, 
			String shopperId, final String merchantAccount)
	{
		super(merchantTransactionCode, merchantAccount);
		this.paymentProvider = paymentProvider;
		this.shopperReference = shopperReference;
		this.shopperId = shopperId;
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
	 * @return the shopperReference
	 */
	public String getShopperReference()
	{
		return shopperReference;
	}

	/**
	 * @param shopperReference the shopperReference to set
	 */
	public void setShopperReference(String shopperReference)
	{
		this.shopperReference = shopperReference;
	}

	/**
	 * @return the shopperId
	 */
	public String getShopperId()
	{
		return shopperId;
	}

	/**
	 * @param shopperId the shopperId to set
	 */
	public void setShopperId(String shopperId)
	{
		this.shopperId = shopperId;
	}

}
