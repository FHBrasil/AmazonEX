/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 21/07/2016 18:08:31                         ---
 * ----------------------------------------------------------------
 */
package de.fliegersoftware.amazon.core.jalo;

import de.fliegersoftware.amazon.core.constants.AmazoncoreConstants;
import de.fliegersoftware.amazon.core.jalo.AmazonRefund;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.payment.PaymentInfo;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.util.OneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.order.payment.PaymentInfo AmazonPaymentPaymentInfo}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedAmazonPaymentPaymentInfo extends PaymentInfo
{
	/** Qualifier of the <code>AmazonPaymentPaymentInfo.amazonOrderReferenceId</code> attribute **/
	public static final String AMAZONORDERREFERENCEID = "amazonOrderReferenceId";
	/** Qualifier of the <code>AmazonPaymentPaymentInfo.amazonOrderStatus</code> attribute **/
	public static final String AMAZONORDERSTATUS = "amazonOrderStatus";
	/** Qualifier of the <code>AmazonPaymentPaymentInfo.amazonOrderReasonCode</code> attribute **/
	public static final String AMAZONORDERREASONCODE = "amazonOrderReasonCode";
	/** Qualifier of the <code>AmazonPaymentPaymentInfo.amazonOrderAmount</code> attribute **/
	public static final String AMAZONORDERAMOUNT = "amazonOrderAmount";
	/** Qualifier of the <code>AmazonPaymentPaymentInfo.amazonLastAuthorizationId</code> attribute **/
	public static final String AMAZONLASTAUTHORIZATIONID = "amazonLastAuthorizationId";
	/** Qualifier of the <code>AmazonPaymentPaymentInfo.amazonAuthorizationStatus</code> attribute **/
	public static final String AMAZONAUTHORIZATIONSTATUS = "amazonAuthorizationStatus";
	/** Qualifier of the <code>AmazonPaymentPaymentInfo.amazonAuthorizationReasonCode</code> attribute **/
	public static final String AMAZONAUTHORIZATIONREASONCODE = "amazonAuthorizationReasonCode";
	/** Qualifier of the <code>AmazonPaymentPaymentInfo.amazonCaptureId</code> attribute **/
	public static final String AMAZONCAPTUREID = "amazonCaptureId";
	/** Qualifier of the <code>AmazonPaymentPaymentInfo.amazonCaptureStatus</code> attribute **/
	public static final String AMAZONCAPTURESTATUS = "amazonCaptureStatus";
	/** Qualifier of the <code>AmazonPaymentPaymentInfo.amazonCaptureReasonCode</code> attribute **/
	public static final String AMAZONCAPTUREREASONCODE = "amazonCaptureReasonCode";
	/** Qualifier of the <code>AmazonPaymentPaymentInfo.amazonCaptureRefundedAmount</code> attribute **/
	public static final String AMAZONCAPTUREREFUNDEDAMOUNT = "amazonCaptureRefundedAmount";
	/** Qualifier of the <code>AmazonPaymentPaymentInfo.refundRequestAmount</code> attribute **/
	public static final String REFUNDREQUESTAMOUNT = "refundRequestAmount";
	/** Qualifier of the <code>AmazonPaymentPaymentInfo.currencyRefundRequestAmount</code> attribute **/
	public static final String CURRENCYREFUNDREQUESTAMOUNT = "currencyRefundRequestAmount";
	/** Qualifier of the <code>AmazonPaymentPaymentInfo.refund</code> attribute **/
	public static final String REFUND = "refund";
	/**
	* {@link OneToManyHandler} for handling 1:n REFUND's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<AmazonRefund> REFUNDHANDLER = new OneToManyHandler<AmazonRefund>(
	AmazoncoreConstants.TC.AMAZONREFUND,
	false,
	"amazonPaymentPaymentInfo",
	null,
	false,
	true,
	CollectionType.LIST
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(PaymentInfo.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(AMAZONORDERREFERENCEID, AttributeMode.INITIAL);
		tmp.put(AMAZONORDERSTATUS, AttributeMode.INITIAL);
		tmp.put(AMAZONORDERREASONCODE, AttributeMode.INITIAL);
		tmp.put(AMAZONORDERAMOUNT, AttributeMode.INITIAL);
		tmp.put(AMAZONLASTAUTHORIZATIONID, AttributeMode.INITIAL);
		tmp.put(AMAZONAUTHORIZATIONSTATUS, AttributeMode.INITIAL);
		tmp.put(AMAZONAUTHORIZATIONREASONCODE, AttributeMode.INITIAL);
		tmp.put(AMAZONCAPTUREID, AttributeMode.INITIAL);
		tmp.put(AMAZONCAPTURESTATUS, AttributeMode.INITIAL);
		tmp.put(AMAZONCAPTUREREASONCODE, AttributeMode.INITIAL);
		tmp.put(AMAZONCAPTUREREFUNDEDAMOUNT, AttributeMode.INITIAL);
		tmp.put(REFUNDREQUESTAMOUNT, AttributeMode.INITIAL);
		tmp.put(CURRENCYREFUNDREQUESTAMOUNT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonAuthorizationReasonCode</code> attribute.
	 * @return the amazonAuthorizationReasonCode - Authorization Reason Code
	 */
	public String getAmazonAuthorizationReasonCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, AMAZONAUTHORIZATIONREASONCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonAuthorizationReasonCode</code> attribute.
	 * @return the amazonAuthorizationReasonCode - Authorization Reason Code
	 */
	public String getAmazonAuthorizationReasonCode()
	{
		return getAmazonAuthorizationReasonCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonAuthorizationReasonCode</code> attribute. 
	 * @param value the amazonAuthorizationReasonCode - Authorization Reason Code
	 */
	public void setAmazonAuthorizationReasonCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, AMAZONAUTHORIZATIONREASONCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonAuthorizationReasonCode</code> attribute. 
	 * @param value the amazonAuthorizationReasonCode - Authorization Reason Code
	 */
	public void setAmazonAuthorizationReasonCode(final String value)
	{
		setAmazonAuthorizationReasonCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonAuthorizationStatus</code> attribute.
	 * @return the amazonAuthorizationStatus - Authorization Reference Id for Amazon operations
	 */
	public String getAmazonAuthorizationStatus(final SessionContext ctx)
	{
		return (String)getProperty( ctx, AMAZONAUTHORIZATIONSTATUS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonAuthorizationStatus</code> attribute.
	 * @return the amazonAuthorizationStatus - Authorization Reference Id for Amazon operations
	 */
	public String getAmazonAuthorizationStatus()
	{
		return getAmazonAuthorizationStatus( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonAuthorizationStatus</code> attribute. 
	 * @param value the amazonAuthorizationStatus - Authorization Reference Id for Amazon operations
	 */
	public void setAmazonAuthorizationStatus(final SessionContext ctx, final String value)
	{
		setProperty(ctx, AMAZONAUTHORIZATIONSTATUS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonAuthorizationStatus</code> attribute. 
	 * @param value the amazonAuthorizationStatus - Authorization Reference Id for Amazon operations
	 */
	public void setAmazonAuthorizationStatus(final String value)
	{
		setAmazonAuthorizationStatus( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonCaptureId</code> attribute.
	 * @return the amazonCaptureId - Capture Id for Amazon operations
	 */
	public String getAmazonCaptureId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, AMAZONCAPTUREID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonCaptureId</code> attribute.
	 * @return the amazonCaptureId - Capture Id for Amazon operations
	 */
	public String getAmazonCaptureId()
	{
		return getAmazonCaptureId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonCaptureId</code> attribute. 
	 * @param value the amazonCaptureId - Capture Id for Amazon operations
	 */
	public void setAmazonCaptureId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, AMAZONCAPTUREID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonCaptureId</code> attribute. 
	 * @param value the amazonCaptureId - Capture Id for Amazon operations
	 */
	public void setAmazonCaptureId(final String value)
	{
		setAmazonCaptureId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonCaptureReasonCode</code> attribute.
	 * @return the amazonCaptureReasonCode - Capture Status for Amazon operations
	 */
	public String getAmazonCaptureReasonCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, AMAZONCAPTUREREASONCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonCaptureReasonCode</code> attribute.
	 * @return the amazonCaptureReasonCode - Capture Status for Amazon operations
	 */
	public String getAmazonCaptureReasonCode()
	{
		return getAmazonCaptureReasonCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonCaptureReasonCode</code> attribute. 
	 * @param value the amazonCaptureReasonCode - Capture Status for Amazon operations
	 */
	public void setAmazonCaptureReasonCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, AMAZONCAPTUREREASONCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonCaptureReasonCode</code> attribute. 
	 * @param value the amazonCaptureReasonCode - Capture Status for Amazon operations
	 */
	public void setAmazonCaptureReasonCode(final String value)
	{
		setAmazonCaptureReasonCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonCaptureRefundedAmount</code> attribute.
	 * @return the amazonCaptureRefundedAmount - Refunded amount
	 */
	public Double getAmazonCaptureRefundedAmount(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, AMAZONCAPTUREREFUNDEDAMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonCaptureRefundedAmount</code> attribute.
	 * @return the amazonCaptureRefundedAmount - Refunded amount
	 */
	public Double getAmazonCaptureRefundedAmount()
	{
		return getAmazonCaptureRefundedAmount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonCaptureRefundedAmount</code> attribute. 
	 * @return the amazonCaptureRefundedAmount - Refunded amount
	 */
	public double getAmazonCaptureRefundedAmountAsPrimitive(final SessionContext ctx)
	{
		Double value = getAmazonCaptureRefundedAmount( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonCaptureRefundedAmount</code> attribute. 
	 * @return the amazonCaptureRefundedAmount - Refunded amount
	 */
	public double getAmazonCaptureRefundedAmountAsPrimitive()
	{
		return getAmazonCaptureRefundedAmountAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonCaptureRefundedAmount</code> attribute. 
	 * @param value the amazonCaptureRefundedAmount - Refunded amount
	 */
	public void setAmazonCaptureRefundedAmount(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, AMAZONCAPTUREREFUNDEDAMOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonCaptureRefundedAmount</code> attribute. 
	 * @param value the amazonCaptureRefundedAmount - Refunded amount
	 */
	public void setAmazonCaptureRefundedAmount(final Double value)
	{
		setAmazonCaptureRefundedAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonCaptureRefundedAmount</code> attribute. 
	 * @param value the amazonCaptureRefundedAmount - Refunded amount
	 */
	public void setAmazonCaptureRefundedAmount(final SessionContext ctx, final double value)
	{
		setAmazonCaptureRefundedAmount( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonCaptureRefundedAmount</code> attribute. 
	 * @param value the amazonCaptureRefundedAmount - Refunded amount
	 */
	public void setAmazonCaptureRefundedAmount(final double value)
	{
		setAmazonCaptureRefundedAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonCaptureStatus</code> attribute.
	 * @return the amazonCaptureStatus - Capture Status for Amazon operations
	 */
	public String getAmazonCaptureStatus(final SessionContext ctx)
	{
		return (String)getProperty( ctx, AMAZONCAPTURESTATUS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonCaptureStatus</code> attribute.
	 * @return the amazonCaptureStatus - Capture Status for Amazon operations
	 */
	public String getAmazonCaptureStatus()
	{
		return getAmazonCaptureStatus( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonCaptureStatus</code> attribute. 
	 * @param value the amazonCaptureStatus - Capture Status for Amazon operations
	 */
	public void setAmazonCaptureStatus(final SessionContext ctx, final String value)
	{
		setProperty(ctx, AMAZONCAPTURESTATUS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonCaptureStatus</code> attribute. 
	 * @param value the amazonCaptureStatus - Capture Status for Amazon operations
	 */
	public void setAmazonCaptureStatus(final String value)
	{
		setAmazonCaptureStatus( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonLastAuthorizationId</code> attribute.
	 * @return the amazonLastAuthorizationId - Last Authorization Id for Amazon operations
	 */
	public String getAmazonLastAuthorizationId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, AMAZONLASTAUTHORIZATIONID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonLastAuthorizationId</code> attribute.
	 * @return the amazonLastAuthorizationId - Last Authorization Id for Amazon operations
	 */
	public String getAmazonLastAuthorizationId()
	{
		return getAmazonLastAuthorizationId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonLastAuthorizationId</code> attribute. 
	 * @param value the amazonLastAuthorizationId - Last Authorization Id for Amazon operations
	 */
	public void setAmazonLastAuthorizationId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, AMAZONLASTAUTHORIZATIONID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonLastAuthorizationId</code> attribute. 
	 * @param value the amazonLastAuthorizationId - Last Authorization Id for Amazon operations
	 */
	public void setAmazonLastAuthorizationId(final String value)
	{
		setAmazonLastAuthorizationId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonOrderAmount</code> attribute.
	 * @return the amazonOrderAmount - Order Amount for Amazon operations
	 */
	public Double getAmazonOrderAmount(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, AMAZONORDERAMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonOrderAmount</code> attribute.
	 * @return the amazonOrderAmount - Order Amount for Amazon operations
	 */
	public Double getAmazonOrderAmount()
	{
		return getAmazonOrderAmount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonOrderAmount</code> attribute. 
	 * @return the amazonOrderAmount - Order Amount for Amazon operations
	 */
	public double getAmazonOrderAmountAsPrimitive(final SessionContext ctx)
	{
		Double value = getAmazonOrderAmount( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonOrderAmount</code> attribute. 
	 * @return the amazonOrderAmount - Order Amount for Amazon operations
	 */
	public double getAmazonOrderAmountAsPrimitive()
	{
		return getAmazonOrderAmountAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonOrderAmount</code> attribute. 
	 * @param value the amazonOrderAmount - Order Amount for Amazon operations
	 */
	public void setAmazonOrderAmount(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, AMAZONORDERAMOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonOrderAmount</code> attribute. 
	 * @param value the amazonOrderAmount - Order Amount for Amazon operations
	 */
	public void setAmazonOrderAmount(final Double value)
	{
		setAmazonOrderAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonOrderAmount</code> attribute. 
	 * @param value the amazonOrderAmount - Order Amount for Amazon operations
	 */
	public void setAmazonOrderAmount(final SessionContext ctx, final double value)
	{
		setAmazonOrderAmount( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonOrderAmount</code> attribute. 
	 * @param value the amazonOrderAmount - Order Amount for Amazon operations
	 */
	public void setAmazonOrderAmount(final double value)
	{
		setAmazonOrderAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonOrderReasonCode</code> attribute.
	 * @return the amazonOrderReasonCode - Order Reason Code for Amazon operations
	 */
	public String getAmazonOrderReasonCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, AMAZONORDERREASONCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonOrderReasonCode</code> attribute.
	 * @return the amazonOrderReasonCode - Order Reason Code for Amazon operations
	 */
	public String getAmazonOrderReasonCode()
	{
		return getAmazonOrderReasonCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonOrderReasonCode</code> attribute. 
	 * @param value the amazonOrderReasonCode - Order Reason Code for Amazon operations
	 */
	public void setAmazonOrderReasonCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, AMAZONORDERREASONCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonOrderReasonCode</code> attribute. 
	 * @param value the amazonOrderReasonCode - Order Reason Code for Amazon operations
	 */
	public void setAmazonOrderReasonCode(final String value)
	{
		setAmazonOrderReasonCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonOrderReferenceId</code> attribute.
	 * @return the amazonOrderReferenceId - Order Reference Id for Amazon operations
	 */
	public String getAmazonOrderReferenceId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, AMAZONORDERREFERENCEID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonOrderReferenceId</code> attribute.
	 * @return the amazonOrderReferenceId - Order Reference Id for Amazon operations
	 */
	public String getAmazonOrderReferenceId()
	{
		return getAmazonOrderReferenceId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonOrderReferenceId</code> attribute. 
	 * @param value the amazonOrderReferenceId - Order Reference Id for Amazon operations
	 */
	public void setAmazonOrderReferenceId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, AMAZONORDERREFERENCEID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonOrderReferenceId</code> attribute. 
	 * @param value the amazonOrderReferenceId - Order Reference Id for Amazon operations
	 */
	public void setAmazonOrderReferenceId(final String value)
	{
		setAmazonOrderReferenceId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonOrderStatus</code> attribute.
	 * @return the amazonOrderStatus - Order Status for Amazon operations
	 */
	public String getAmazonOrderStatus(final SessionContext ctx)
	{
		return (String)getProperty( ctx, AMAZONORDERSTATUS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.amazonOrderStatus</code> attribute.
	 * @return the amazonOrderStatus - Order Status for Amazon operations
	 */
	public String getAmazonOrderStatus()
	{
		return getAmazonOrderStatus( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonOrderStatus</code> attribute. 
	 * @param value the amazonOrderStatus - Order Status for Amazon operations
	 */
	public void setAmazonOrderStatus(final SessionContext ctx, final String value)
	{
		setProperty(ctx, AMAZONORDERSTATUS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.amazonOrderStatus</code> attribute. 
	 * @param value the amazonOrderStatus - Order Status for Amazon operations
	 */
	public void setAmazonOrderStatus(final String value)
	{
		setAmazonOrderStatus( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.currencyRefundRequestAmount</code> attribute.
	 * @return the currencyRefundRequestAmount - currency to amazon operations
	 */
	public String getCurrencyRefundRequestAmount(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CURRENCYREFUNDREQUESTAMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.currencyRefundRequestAmount</code> attribute.
	 * @return the currencyRefundRequestAmount - currency to amazon operations
	 */
	public String getCurrencyRefundRequestAmount()
	{
		return getCurrencyRefundRequestAmount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.currencyRefundRequestAmount</code> attribute. 
	 * @param value the currencyRefundRequestAmount - currency to amazon operations
	 */
	public void setCurrencyRefundRequestAmount(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CURRENCYREFUNDREQUESTAMOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.currencyRefundRequestAmount</code> attribute. 
	 * @param value the currencyRefundRequestAmount - currency to amazon operations
	 */
	public void setCurrencyRefundRequestAmount(final String value)
	{
		setCurrencyRefundRequestAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.refund</code> attribute.
	 * @return the refund
	 */
	public List<AmazonRefund> getRefund(final SessionContext ctx)
	{
		return (List<AmazonRefund>)REFUNDHANDLER.getValues( ctx, this );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.refund</code> attribute.
	 * @return the refund
	 */
	public List<AmazonRefund> getRefund()
	{
		return getRefund( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.refund</code> attribute. 
	 * @param value the refund
	 */
	public void setRefund(final SessionContext ctx, final List<AmazonRefund> value)
	{
		REFUNDHANDLER.setValues( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.refund</code> attribute. 
	 * @param value the refund
	 */
	public void setRefund(final List<AmazonRefund> value)
	{
		setRefund( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to refund. 
	 * @param value the item to add to refund
	 */
	public void addToRefund(final SessionContext ctx, final AmazonRefund value)
	{
		REFUNDHANDLER.addValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to refund. 
	 * @param value the item to add to refund
	 */
	public void addToRefund(final AmazonRefund value)
	{
		addToRefund( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from refund. 
	 * @param value the item to remove from refund
	 */
	public void removeFromRefund(final SessionContext ctx, final AmazonRefund value)
	{
		REFUNDHANDLER.removeValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from refund. 
	 * @param value the item to remove from refund
	 */
	public void removeFromRefund(final AmazonRefund value)
	{
		removeFromRefund( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.refundRequestAmount</code> attribute.
	 * @return the refundRequestAmount - Refund Amount operations
	 */
	public Double getRefundRequestAmount(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, REFUNDREQUESTAMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.refundRequestAmount</code> attribute.
	 * @return the refundRequestAmount - Refund Amount operations
	 */
	public Double getRefundRequestAmount()
	{
		return getRefundRequestAmount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.refundRequestAmount</code> attribute. 
	 * @return the refundRequestAmount - Refund Amount operations
	 */
	public double getRefundRequestAmountAsPrimitive(final SessionContext ctx)
	{
		Double value = getRefundRequestAmount( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonPaymentPaymentInfo.refundRequestAmount</code> attribute. 
	 * @return the refundRequestAmount - Refund Amount operations
	 */
	public double getRefundRequestAmountAsPrimitive()
	{
		return getRefundRequestAmountAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.refundRequestAmount</code> attribute. 
	 * @param value the refundRequestAmount - Refund Amount operations
	 */
	public void setRefundRequestAmount(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, REFUNDREQUESTAMOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.refundRequestAmount</code> attribute. 
	 * @param value the refundRequestAmount - Refund Amount operations
	 */
	public void setRefundRequestAmount(final Double value)
	{
		setRefundRequestAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.refundRequestAmount</code> attribute. 
	 * @param value the refundRequestAmount - Refund Amount operations
	 */
	public void setRefundRequestAmount(final SessionContext ctx, final double value)
	{
		setRefundRequestAmount( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonPaymentPaymentInfo.refundRequestAmount</code> attribute. 
	 * @param value the refundRequestAmount - Refund Amount operations
	 */
	public void setRefundRequestAmount(final double value)
	{
		setRefundRequestAmount( getSession().getSessionContext(), value );
	}
	
}
