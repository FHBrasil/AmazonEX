/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 21/07/2016 18:08:31                         ---
 * ----------------------------------------------------------------
 */
package de.fliegersoftware.amazon.core.jalo;

import de.fliegersoftware.amazon.core.constants.AmazoncoreConstants;
import de.fliegersoftware.amazon.core.jalo.AmazonPaymentPaymentInfo;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem AmazonRefund}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedAmazonRefund extends GenericItem
{
	/** Qualifier of the <code>AmazonRefund.amazonRefundId</code> attribute **/
	public static final String AMAZONREFUNDID = "amazonRefundId";
	/** Qualifier of the <code>AmazonRefund.refundReferenceId</code> attribute **/
	public static final String REFUNDREFERENCEID = "refundReferenceId";
	/** Qualifier of the <code>AmazonRefund.refundAmount</code> attribute **/
	public static final String REFUNDAMOUNT = "refundAmount";
	/** Qualifier of the <code>AmazonRefund.refundStatus</code> attribute **/
	public static final String REFUNDSTATUS = "refundStatus";
	/** Qualifier of the <code>AmazonRefund.refundReasonCode</code> attribute **/
	public static final String REFUNDREASONCODE = "refundReasonCode";
	/** Qualifier of the <code>AmazonRefund.amazonPaymentPaymentInfo</code> attribute **/
	public static final String AMAZONPAYMENTPAYMENTINFO = "amazonPaymentPaymentInfo";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n AMAZONPAYMENTPAYMENTINFO's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedAmazonRefund> AMAZONPAYMENTPAYMENTINFOHANDLER = new BidirectionalOneToManyHandler<GeneratedAmazonRefund>(
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
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(AMAZONREFUNDID, AttributeMode.INITIAL);
		tmp.put(REFUNDREFERENCEID, AttributeMode.INITIAL);
		tmp.put(REFUNDAMOUNT, AttributeMode.INITIAL);
		tmp.put(REFUNDSTATUS, AttributeMode.INITIAL);
		tmp.put(REFUNDREASONCODE, AttributeMode.INITIAL);
		tmp.put(AMAZONPAYMENTPAYMENTINFO, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonRefund.amazonPaymentPaymentInfo</code> attribute.
	 * @return the amazonPaymentPaymentInfo
	 */
	public AmazonPaymentPaymentInfo getAmazonPaymentPaymentInfo(final SessionContext ctx)
	{
		return (AmazonPaymentPaymentInfo)getProperty( ctx, AMAZONPAYMENTPAYMENTINFO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonRefund.amazonPaymentPaymentInfo</code> attribute.
	 * @return the amazonPaymentPaymentInfo
	 */
	public AmazonPaymentPaymentInfo getAmazonPaymentPaymentInfo()
	{
		return getAmazonPaymentPaymentInfo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonRefund.amazonPaymentPaymentInfo</code> attribute. 
	 * @param value the amazonPaymentPaymentInfo
	 */
	public void setAmazonPaymentPaymentInfo(final SessionContext ctx, final AmazonPaymentPaymentInfo value)
	{
		AMAZONPAYMENTPAYMENTINFOHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonRefund.amazonPaymentPaymentInfo</code> attribute. 
	 * @param value the amazonPaymentPaymentInfo
	 */
	public void setAmazonPaymentPaymentInfo(final AmazonPaymentPaymentInfo value)
	{
		setAmazonPaymentPaymentInfo( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonRefund.amazonRefundId</code> attribute.
	 * @return the amazonRefundId - Order Reference Id for Amazon operations
	 */
	public String getAmazonRefundId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, AMAZONREFUNDID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonRefund.amazonRefundId</code> attribute.
	 * @return the amazonRefundId - Order Reference Id for Amazon operations
	 */
	public String getAmazonRefundId()
	{
		return getAmazonRefundId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonRefund.amazonRefundId</code> attribute. 
	 * @param value the amazonRefundId - Order Reference Id for Amazon operations
	 */
	public void setAmazonRefundId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, AMAZONREFUNDID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonRefund.amazonRefundId</code> attribute. 
	 * @param value the amazonRefundId - Order Reference Id for Amazon operations
	 */
	public void setAmazonRefundId(final String value)
	{
		setAmazonRefundId( getSession().getSessionContext(), value );
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		AMAZONPAYMENTPAYMENTINFOHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonRefund.refundAmount</code> attribute.
	 * @return the refundAmount - refundAmount for Amazon operations
	 */
	public Double getRefundAmount(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, REFUNDAMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonRefund.refundAmount</code> attribute.
	 * @return the refundAmount - refundAmount for Amazon operations
	 */
	public Double getRefundAmount()
	{
		return getRefundAmount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonRefund.refundAmount</code> attribute. 
	 * @return the refundAmount - refundAmount for Amazon operations
	 */
	public double getRefundAmountAsPrimitive(final SessionContext ctx)
	{
		Double value = getRefundAmount( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonRefund.refundAmount</code> attribute. 
	 * @return the refundAmount - refundAmount for Amazon operations
	 */
	public double getRefundAmountAsPrimitive()
	{
		return getRefundAmountAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonRefund.refundAmount</code> attribute. 
	 * @param value the refundAmount - refundAmount for Amazon operations
	 */
	public void setRefundAmount(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, REFUNDAMOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonRefund.refundAmount</code> attribute. 
	 * @param value the refundAmount - refundAmount for Amazon operations
	 */
	public void setRefundAmount(final Double value)
	{
		setRefundAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonRefund.refundAmount</code> attribute. 
	 * @param value the refundAmount - refundAmount for Amazon operations
	 */
	public void setRefundAmount(final SessionContext ctx, final double value)
	{
		setRefundAmount( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonRefund.refundAmount</code> attribute. 
	 * @param value the refundAmount - refundAmount for Amazon operations
	 */
	public void setRefundAmount(final double value)
	{
		setRefundAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonRefund.refundReasonCode</code> attribute.
	 * @return the refundReasonCode - refundReasonCode for Amazon operations
	 */
	public String getRefundReasonCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, REFUNDREASONCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonRefund.refundReasonCode</code> attribute.
	 * @return the refundReasonCode - refundReasonCode for Amazon operations
	 */
	public String getRefundReasonCode()
	{
		return getRefundReasonCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonRefund.refundReasonCode</code> attribute. 
	 * @param value the refundReasonCode - refundReasonCode for Amazon operations
	 */
	public void setRefundReasonCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, REFUNDREASONCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonRefund.refundReasonCode</code> attribute. 
	 * @param value the refundReasonCode - refundReasonCode for Amazon operations
	 */
	public void setRefundReasonCode(final String value)
	{
		setRefundReasonCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonRefund.refundReferenceId</code> attribute.
	 * @return the refundReferenceId - refundReferenceId for Amazon operations
	 */
	public String getRefundReferenceId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, REFUNDREFERENCEID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonRefund.refundReferenceId</code> attribute.
	 * @return the refundReferenceId - refundReferenceId for Amazon operations
	 */
	public String getRefundReferenceId()
	{
		return getRefundReferenceId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonRefund.refundReferenceId</code> attribute. 
	 * @param value the refundReferenceId - refundReferenceId for Amazon operations
	 */
	public void setRefundReferenceId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, REFUNDREFERENCEID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonRefund.refundReferenceId</code> attribute. 
	 * @param value the refundReferenceId - refundReferenceId for Amazon operations
	 */
	public void setRefundReferenceId(final String value)
	{
		setRefundReferenceId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonRefund.refundStatus</code> attribute.
	 * @return the refundStatus - refundStatus for Amazon operations
	 */
	public String getRefundStatus(final SessionContext ctx)
	{
		return (String)getProperty( ctx, REFUNDSTATUS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonRefund.refundStatus</code> attribute.
	 * @return the refundStatus - refundStatus for Amazon operations
	 */
	public String getRefundStatus()
	{
		return getRefundStatus( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonRefund.refundStatus</code> attribute. 
	 * @param value the refundStatus - refundStatus for Amazon operations
	 */
	public void setRefundStatus(final SessionContext ctx, final String value)
	{
		setProperty(ctx, REFUNDSTATUS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonRefund.refundStatus</code> attribute. 
	 * @param value the refundStatus - refundStatus for Amazon operations
	 */
	public void setRefundStatus(final String value)
	{
		setRefundStatus( getSession().getSessionContext(), value );
	}
	
}
