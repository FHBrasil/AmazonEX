/**
 * 
 */
package com.adyen.services.payment;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.AddressModel;

import java.util.Currency;

/**
 * @author flieger
 *
 */
public class AdyenBoletoAuthorizationRequest extends AbstractAdyenRequest
{
	private String paymentProvider;
	private String reference;
	private AddressModel address;
	private CartModel cart;
	private String cpf;
	private Currency currency;
	
	/**
	 * @param paymentProvider
	 * @param newEntryCode
	 * @param originalPspReference
	 * @param billingAddress
	 * @param cartModel
	 * @param cpf
	 * @param currency
	 */
	public AdyenBoletoAuthorizationRequest(String paymentProvider, String newEntryCode, String originalPspReference,
			AddressModel billingAddress, CartModel cartModel, String cpf, 
			Currency currency, final String merchantAccount)
	{
		super(newEntryCode, merchantAccount);
		this.paymentProvider = paymentProvider;
		this.reference = originalPspReference;
		this.address = billingAddress;
		this.cart = cartModel;
		this.cpf = cpf;
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
	 * @return the cart
	 */
	public CartModel getCart()
	{
		return cart;
	}

	/**
	 * @param cart the cart to set
	 */
	public void setCart(CartModel cart)
	{
		this.cart = cart;
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
	 * @return the address
	 */
	public AddressModel getAddress()
	{
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(AddressModel address)
	{
		this.address = address;
	}

	/**
	 * @return the cpf
	 */
	public String getCpf()
	{
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf)
	{
		this.cpf = cpf;
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
}
