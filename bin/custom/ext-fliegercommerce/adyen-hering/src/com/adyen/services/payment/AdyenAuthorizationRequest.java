/**
 * 
 */
package com.adyen.services.payment;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.payment.dto.CardInfo;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author flieger
 *
 */
public class AdyenAuthorizationRequest extends AbstractAdyenRequest
{
	private String stantBuyKey;
	private String userUid;
	private String paymentProvider;
	private String buyerEmail;
	private Currency currency;
	private BigDecimal value;
	private String reference;
	private CardInfo card;
	private CartModel cart;
	private Short installments;
	
	/**
	 * @param stantBuyKey
	 * @param userUid
	 * @param paymentProvider
	 * @param merchantTransactionCode
	 * @param merchantAccount
	 * @param currency
	 * @param value
	 * @param reference
	 * @param card
	 * @param cart
 	 * @param buyerEmail
 	 * @param installments
	 */
	public AdyenAuthorizationRequest(final String stantBuyKey, 
			final String userUid, 
			final String paymentProvider, 
			final String merchantTransactionCode, 
			final String merchantAccount, 
			final Currency currency, 
			final BigDecimal value,
			final String reference, 
			final CardInfo card, 
			final CartModel cart, 
			final String buyerEmail, 
			final Short installments)
	{
		super(merchantTransactionCode, merchantAccount);
		this.stantBuyKey = stantBuyKey;
		this.userUid = userUid;
		this.paymentProvider = paymentProvider;
		this.currency = currency;
		this.value = value;
		this.reference = reference;
		this.card = card;
		this.cart = cart;
		this.buyerEmail = buyerEmail;
		this.installments = installments;
	}

	/**
	 * @return the stantBuyKey
	 */
	public String getStantBuyKey()
	{
		return stantBuyKey;
	}

	/**
	 * @param stantBuyKey the stantBuyKey to set
	 */
	public void setStantBuyKey(String stantBuyKey)
	{
		this.stantBuyKey = stantBuyKey;
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
	 * @return the card
	 */
	public CardInfo getCard()
	{
		return card;
	}

	/**
	 * @param card the card to set
	 */
	public void setCard(CardInfo card)
	{
		this.card = card;
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
	 * @return the buyerEmail
	 */
	public String getBuyerEmail()
	{
		return buyerEmail;
	}

	/**
	 * @param buyerEmail the buyerEmail to set
	 */
	public void setBuyerEmail(String buyerEmail)
	{
		this.buyerEmail = buyerEmail;
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
	 * @return the userUid
	 */
	public String getUserUid()
	{
		return userUid;
	}

	/**
	 * @param userUid the userUid to set
	 */
	public void setUserUid(String userUid)
	{
		this.userUid = userUid;
	}

	/**
	 * @return the installments
	 */
	public Short getInstallments()
	{
		return installments;
	}

	/**
	 * @param installments the installments to set
	 */
	public void setInstallments(Short installments)
	{
		this.installments = installments;
	}
}
