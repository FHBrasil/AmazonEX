/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flieger.payment.commands.request;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.payment.commands.request.AbstractRequest;
import de.hybris.platform.payment.dto.BillingInfo;
import de.hybris.platform.payment.dto.CardInfo;
import java.math.BigDecimal;
import java.util.Currency;

/**
 *
 * @author Antony
 */
public class MundipaggAuthorizationCaptureRequest extends AbstractRequest
{

	private final String subscriptionID;
	private final Currency currency;
	private final BigDecimal totalAmount;
	private final BillingInfo shippingInfo;
	private final String cv2;
	private final String paymentProvider;
	private final CardInfo card;
	private final String mundipaggInstantBuyKey;
	private final String cpfForMundipagg;
	private CartModel cart;

	public MundipaggAuthorizationCaptureRequest(String merchantTransactionCode,
			String subscriptionID, Currency currency, BigDecimal totalAmount,
			BillingInfo shippingInfo, String cv2, String paymentProvider, CardInfo card,
			String mundipaggInstantBuyKey, String cpfForMundipagg, CartModel cart)
	{
		super(merchantTransactionCode);
		this.subscriptionID = subscriptionID;
		this.currency = currency;
		this.totalAmount = totalAmount;
		this.shippingInfo = shippingInfo;
		this.cv2 = cv2;
		this.paymentProvider = paymentProvider;
		this.card = card;
		this.mundipaggInstantBuyKey = null;
		this.cpfForMundipagg = cpfForMundipagg;
		this.cart = cart;
	}

	public MundipaggAuthorizationCaptureRequest(String merchantTransactionCode,
			String subscriptionID, Currency currency, BigDecimal totalAmount, BillingInfo shippingInfo,
			String paymentProvider, CardInfo card, String mundipaggInstantBuyKey, CartModel cart, String cpfForMundipagg)
	{
		super(merchantTransactionCode);
		this.subscriptionID = subscriptionID;
		this.currency = currency;
		this.totalAmount = totalAmount;
		this.shippingInfo = shippingInfo;
		this.cv2 = null;
		this.paymentProvider = paymentProvider;
		this.card = card;
		this.mundipaggInstantBuyKey = mundipaggInstantBuyKey;
		this.cpfForMundipagg = cpfForMundipagg;
		this.cart = cart;
	}

	public String getSubscriptionID()
	{
		return this.subscriptionID;
	}

	public Currency getCurrency()
	{
		return this.currency;
	}

	public BigDecimal getTotalAmount()
	{
		return this.totalAmount;
	}

	public BillingInfo getShippingInfo()
	{
		return this.shippingInfo;
	}

	public String getCv2()
	{
		return this.cv2;
	}

	public String getPaymentProvider()
	{
		return this.paymentProvider;
	}

	public CardInfo getCard()
	{
		return this.card;
	}

	public String getMundipaggInstantBuyKey()
	{
		return mundipaggInstantBuyKey;
	}

	public String getCpfForMundipagg()
	{
		return cpfForMundipagg;
	}

	public CartModel getCart()
	{
		return cart;
	}

	public void setCart(CartModel cart)
	{
		this.cart = cart;
	}

}
