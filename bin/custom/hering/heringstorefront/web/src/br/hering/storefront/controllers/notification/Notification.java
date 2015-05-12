/**
 * 
 */
package br.hering.storefront.controllers.notification;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author flieger
 *
 */
public class Notification
{
	private Boolean success;
	private Date eventDate;
	private String reason;
	private String originalReference;
	private String merchantReference;
	private String currency;
	private String pspReference;
	private String merchantAccountCode;
	private BigDecimal value;
	private String operations;
	private String paymentMethod;
	private Boolean live;
	private String nossoNumero;
	
	/**
	 * @return the nossoNumero
	 */
	public String getNossoNumero()
	{
		return nossoNumero;
	}
	/**
	 * @param nossoNumero the nossoNumero to set
	 */
	public void setNossoNumero(String nossoNumero)
	{
		this.nossoNumero = nossoNumero;
	}
	/**
	 * @return the eventDate
	 */
	public Date getEventDate()
	{
		return eventDate;
	}
	/**
	 * @param eventDate the eventDate to set
	 */
	public void setEventDate(Date eventDate)
	{
		this.eventDate = eventDate;
	}
	/**
	 * @return the reason
	 */
	public String getReason()
	{
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason)
	{
		this.reason = reason;
	}
	/**
	 * @return the originalReference
	 */
	public String getOriginalReference()
	{
		return originalReference;
	}
	/**
	 * @param originalReference the originalReference to set
	 */
	public void setOriginalReference(String originalReference)
	{
		this.originalReference = originalReference;
	}
	/**
	 * @return the merchantReference
	 */
	public String getMerchantReference()
	{
		return merchantReference;
	}
	/**
	 * @param merchantReference the merchantReference to set
	 */
	public void setMerchantReference(String merchantReference)
	{
		this.merchantReference = merchantReference;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency()
	{
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency)
	{
		this.currency = currency;
	}
	/**
	 * @return the pspReference
	 */
	public String getPspReference()
	{
		return pspReference;
	}
	/**
	 * @param pspReference the pspReference to set
	 */
	public void setPspReference(String pspReference)
	{
		this.pspReference = pspReference;
	}
	/**
	 * @return the merchantAccountCode
	 */
	public String getMerchantAccountCode()
	{
		return merchantAccountCode;
	}
	/**
	 * @param merchantAccountCode the merchantAccountCode to set
	 */
	public void setMerchantAccountCode(String merchantAccountCode)
	{
		this.merchantAccountCode = merchantAccountCode;
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
	 * @return the operations
	 */
	public String getOperations()
	{
		return operations;
	}
	/**
	 * @param operations the operations to set
	 */
	public void setOperations(String operations)
	{
		this.operations = operations;
	}
	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod()
	{
		return paymentMethod;
	}
	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod)
	{
		this.paymentMethod = paymentMethod;
	}
	/**
	 * @return the live
	 */
	public Boolean getLive()
	{
		return live;
	}
	/**
	 * @param live the live to set
	 */
	public void setLive(Boolean live)
	{
		this.live = live;
	}
	/**
	 * @return the success
	 */
	public Boolean getSuccess()
	{
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(Boolean success)
	{
		this.success = success;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Notification [success=" + success + ", eventDate=" + eventDate + ", reason=" + reason + ", originalReference="
				+ originalReference + ", merchantReference=" + merchantReference + ", currency=" + currency + ", pspReference="
				+ pspReference + ", merchantAccountCode=" + merchantAccountCode + ", value=" + value + ", operations=" + operations
				+ ", paymentMethod=" + paymentMethod + ", live=" + live + ", nossoNumero=" + nossoNumero + "]";
	}
	
}
