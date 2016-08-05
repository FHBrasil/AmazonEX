/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 21/07/2016 18:08:31                         ---
 * ----------------------------------------------------------------
 */
package de.fliegersoftware.amazon.core.jalo.config;

import de.fliegersoftware.amazon.core.constants.AmazoncoreConstants;
import de.fliegersoftware.amazon.core.jalo.media.AmazonLog;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.fliegersoftware.amazon.core.jalo.config.AmazonConfig AmazonConfig}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedAmazonConfig extends GenericItem
{
	/** Qualifier of the <code>AmazonConfig.merchantId</code> attribute **/
	public static final String MERCHANTID = "merchantId";
	/** Qualifier of the <code>AmazonConfig.mwsAccessKey</code> attribute **/
	public static final String MWSACCESSKEY = "mwsAccessKey";
	/** Qualifier of the <code>AmazonConfig.mwsSecretKey</code> attribute **/
	public static final String MWSSECRETKEY = "mwsSecretKey";
	/** Qualifier of the <code>AmazonConfig.applicationName</code> attribute **/
	public static final String APPLICATIONNAME = "applicationName";
	/** Qualifier of the <code>AmazonConfig.applicationVersion</code> attribute **/
	public static final String APPLICATIONVERSION = "applicationVersion";
	/** Qualifier of the <code>AmazonConfig.amazonConfigCountry</code> attribute **/
	public static final String AMAZONCONFIGCOUNTRY = "amazonConfigCountry";
	/** Qualifier of the <code>AmazonConfig.testOrderReferenceId</code> attribute **/
	public static final String TESTORDERREFERENCEID = "testOrderReferenceId";
	/** Qualifier of the <code>AmazonConfig.otherCountry</code> attribute **/
	public static final String OTHERCOUNTRY = "otherCountry";
	/** Qualifier of the <code>AmazonConfig.otherCountryCurrency</code> attribute **/
	public static final String OTHERCOUNTRYCURRENCY = "otherCountryCurrency";
	/** Qualifier of the <code>AmazonConfig.clientId</code> attribute **/
	public static final String CLIENTID = "clientId";
	/** Qualifier of the <code>AmazonConfig.enabled</code> attribute **/
	public static final String ENABLED = "enabled";
	/** Qualifier of the <code>AmazonConfig.operationMode</code> attribute **/
	public static final String OPERATIONMODE = "operationMode";
	/** Qualifier of the <code>AmazonConfig.sandboxMode</code> attribute **/
	public static final String SANDBOXMODE = "sandboxMode";
	/** Qualifier of the <code>AmazonConfig.sandboxSimulate</code> attribute **/
	public static final String SANDBOXSIMULATE = "sandboxSimulate";
	/** Qualifier of the <code>AmazonConfig.guestCheckoutStrategy</code> attribute **/
	public static final String GUESTCHECKOUTSTRATEGY = "guestCheckoutStrategy";
	/** Qualifier of the <code>AmazonConfig.hiddenButtonsMode</code> attribute **/
	public static final String HIDDENBUTTONSMODE = "hiddenButtonsMode";
	/** Qualifier of the <code>AmazonConfig.ipWhitelisting</code> attribute **/
	public static final String IPWHITELISTING = "ipWhitelisting";
	/** Qualifier of the <code>AmazonConfig.IPN</code> attribute **/
	public static final String IPN = "IPN";
	/** Qualifier of the <code>AmazonConfig.requestCompleteDeliveryAddress</code> attribute **/
	public static final String REQUESTCOMPLETEDELIVERYADDRESS = "requestCompleteDeliveryAddress";
	/** Qualifier of the <code>AmazonConfig.excludePackstationDelivery</code> attribute **/
	public static final String EXCLUDEPACKSTATIONDELIVERY = "excludePackstationDelivery";
	/** Qualifier of the <code>AmazonConfig.packstationIdentifier</code> attribute **/
	public static final String PACKSTATIONIDENTIFIER = "packstationIdentifier";
	/** Qualifier of the <code>AmazonConfig.accountMatchingStrategy</code> attribute **/
	public static final String ACCOUNTMATCHINGSTRATEGY = "accountMatchingStrategy";
	/** Qualifier of the <code>AmazonConfig.manualAddOnFirstLogin</code> attribute **/
	public static final String MANUALADDONFIRSTLOGIN = "manualAddOnFirstLogin";
	/** Qualifier of the <code>AmazonConfig.checkoutStrategy</code> attribute **/
	public static final String CHECKOUTSTRATEGY = "checkoutStrategy";
	/** Qualifier of the <code>AmazonConfig.normalCheckout</code> attribute **/
	public static final String NORMALCHECKOUT = "normalCheckout";
	/** Qualifier of the <code>AmazonConfig.authorizationMode</code> attribute **/
	public static final String AUTHORIZATIONMODE = "authorizationMode";
	/** Qualifier of the <code>AmazonConfig.captureMode</code> attribute **/
	public static final String CAPTUREMODE = "captureMode";
	/** Qualifier of the <code>AmazonConfig.enableERPMode</code> attribute **/
	public static final String ENABLEERPMODE = "enableERPMode";
	/** Qualifier of the <code>AmazonConfig.ipnReception</code> attribute **/
	public static final String IPNRECEPTION = "ipnReception";
	/** Qualifier of the <code>AmazonConfig.orderStatusOnSuccessfullAuthorization</code> attribute **/
	public static final String ORDERSTATUSONSUCCESSFULLAUTHORIZATION = "orderStatusOnSuccessfullAuthorization";
	/** Qualifier of the <code>AmazonConfig.orderStatusOnSuccessfullShipping</code> attribute **/
	public static final String ORDERSTATUSONSUCCESSFULLSHIPPING = "orderStatusOnSuccessfullShipping";
	/** Qualifier of the <code>AmazonConfig.enableLog</code> attribute **/
	public static final String ENABLELOG = "enableLog";
	/** Qualifier of the <code>AmazonConfig.apiLog</code> attribute **/
	public static final String APILOG = "apiLog";
	/** Qualifier of the <code>AmazonConfig.ipnLog</code> attribute **/
	public static final String IPNLOG = "ipnLog";
	/** Qualifier of the <code>AmazonConfig.amazonLog</code> attribute **/
	public static final String AMAZONLOG = "amazonLog";
	/** Qualifier of the <code>AmazonConfig.payButtonSize</code> attribute **/
	public static final String PAYBUTTONSIZE = "payButtonSize";
	/** Qualifier of the <code>AmazonConfig.payButtonColor</code> attribute **/
	public static final String PAYBUTTONCOLOR = "payButtonColor";
	/** Qualifier of the <code>AmazonConfig.loginButtonSize</code> attribute **/
	public static final String LOGINBUTTONSIZE = "loginButtonSize";
	/** Qualifier of the <code>AmazonConfig.loginButtonColor</code> attribute **/
	public static final String LOGINBUTTONCOLOR = "loginButtonColor";
	/** Qualifier of the <code>AmazonConfig.addressWidgetHeight</code> attribute **/
	public static final String ADDRESSWIDGETHEIGHT = "addressWidgetHeight";
	/** Qualifier of the <code>AmazonConfig.addressWidgetWidth</code> attribute **/
	public static final String ADDRESSWIDGETWIDTH = "addressWidgetWidth";
	/** Qualifier of the <code>AmazonConfig.paymentWidgetHeight</code> attribute **/
	public static final String PAYMENTWIDGETHEIGHT = "paymentWidgetHeight";
	/** Qualifier of the <code>AmazonConfig.paymentWidgetWidth</code> attribute **/
	public static final String PAYMENTWIDGETWIDTH = "paymentWidgetWidth";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(MERCHANTID, AttributeMode.INITIAL);
		tmp.put(MWSACCESSKEY, AttributeMode.INITIAL);
		tmp.put(MWSSECRETKEY, AttributeMode.INITIAL);
		tmp.put(APPLICATIONNAME, AttributeMode.INITIAL);
		tmp.put(APPLICATIONVERSION, AttributeMode.INITIAL);
		tmp.put(AMAZONCONFIGCOUNTRY, AttributeMode.INITIAL);
		tmp.put(TESTORDERREFERENCEID, AttributeMode.INITIAL);
		tmp.put(OTHERCOUNTRY, AttributeMode.INITIAL);
		tmp.put(OTHERCOUNTRYCURRENCY, AttributeMode.INITIAL);
		tmp.put(CLIENTID, AttributeMode.INITIAL);
		tmp.put(ENABLED, AttributeMode.INITIAL);
		tmp.put(OPERATIONMODE, AttributeMode.INITIAL);
		tmp.put(SANDBOXMODE, AttributeMode.INITIAL);
		tmp.put(SANDBOXSIMULATE, AttributeMode.INITIAL);
		tmp.put(GUESTCHECKOUTSTRATEGY, AttributeMode.INITIAL);
		tmp.put(HIDDENBUTTONSMODE, AttributeMode.INITIAL);
		tmp.put(IPWHITELISTING, AttributeMode.INITIAL);
		tmp.put(IPN, AttributeMode.INITIAL);
		tmp.put(REQUESTCOMPLETEDELIVERYADDRESS, AttributeMode.INITIAL);
		tmp.put(EXCLUDEPACKSTATIONDELIVERY, AttributeMode.INITIAL);
		tmp.put(PACKSTATIONIDENTIFIER, AttributeMode.INITIAL);
		tmp.put(ACCOUNTMATCHINGSTRATEGY, AttributeMode.INITIAL);
		tmp.put(MANUALADDONFIRSTLOGIN, AttributeMode.INITIAL);
		tmp.put(CHECKOUTSTRATEGY, AttributeMode.INITIAL);
		tmp.put(NORMALCHECKOUT, AttributeMode.INITIAL);
		tmp.put(AUTHORIZATIONMODE, AttributeMode.INITIAL);
		tmp.put(CAPTUREMODE, AttributeMode.INITIAL);
		tmp.put(ENABLEERPMODE, AttributeMode.INITIAL);
		tmp.put(IPNRECEPTION, AttributeMode.INITIAL);
		tmp.put(ORDERSTATUSONSUCCESSFULLAUTHORIZATION, AttributeMode.INITIAL);
		tmp.put(ORDERSTATUSONSUCCESSFULLSHIPPING, AttributeMode.INITIAL);
		tmp.put(ENABLELOG, AttributeMode.INITIAL);
		tmp.put(APILOG, AttributeMode.INITIAL);
		tmp.put(IPNLOG, AttributeMode.INITIAL);
		tmp.put(AMAZONLOG, AttributeMode.INITIAL);
		tmp.put(PAYBUTTONSIZE, AttributeMode.INITIAL);
		tmp.put(PAYBUTTONCOLOR, AttributeMode.INITIAL);
		tmp.put(LOGINBUTTONSIZE, AttributeMode.INITIAL);
		tmp.put(LOGINBUTTONCOLOR, AttributeMode.INITIAL);
		tmp.put(ADDRESSWIDGETHEIGHT, AttributeMode.INITIAL);
		tmp.put(ADDRESSWIDGETWIDTH, AttributeMode.INITIAL);
		tmp.put(PAYMENTWIDGETHEIGHT, AttributeMode.INITIAL);
		tmp.put(PAYMENTWIDGETWIDTH, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.accountMatchingStrategy</code> attribute.
	 * @return the accountMatchingStrategy - selector to account matching strategy
	 */
	public EnumerationValue getAccountMatchingStrategy(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, ACCOUNTMATCHINGSTRATEGY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.accountMatchingStrategy</code> attribute.
	 * @return the accountMatchingStrategy - selector to account matching strategy
	 */
	public EnumerationValue getAccountMatchingStrategy()
	{
		return getAccountMatchingStrategy( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.accountMatchingStrategy</code> attribute. 
	 * @param value the accountMatchingStrategy - selector to account matching strategy
	 */
	public void setAccountMatchingStrategy(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, ACCOUNTMATCHINGSTRATEGY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.accountMatchingStrategy</code> attribute. 
	 * @param value the accountMatchingStrategy - selector to account matching strategy
	 */
	public void setAccountMatchingStrategy(final EnumerationValue value)
	{
		setAccountMatchingStrategy( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.addressWidgetHeight</code> attribute.
	 * @return the addressWidgetHeight
	 */
	public Integer getAddressWidgetHeight(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, ADDRESSWIDGETHEIGHT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.addressWidgetHeight</code> attribute.
	 * @return the addressWidgetHeight
	 */
	public Integer getAddressWidgetHeight()
	{
		return getAddressWidgetHeight( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.addressWidgetHeight</code> attribute. 
	 * @return the addressWidgetHeight
	 */
	public int getAddressWidgetHeightAsPrimitive(final SessionContext ctx)
	{
		Integer value = getAddressWidgetHeight( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.addressWidgetHeight</code> attribute. 
	 * @return the addressWidgetHeight
	 */
	public int getAddressWidgetHeightAsPrimitive()
	{
		return getAddressWidgetHeightAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.addressWidgetHeight</code> attribute. 
	 * @param value the addressWidgetHeight
	 */
	public void setAddressWidgetHeight(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, ADDRESSWIDGETHEIGHT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.addressWidgetHeight</code> attribute. 
	 * @param value the addressWidgetHeight
	 */
	public void setAddressWidgetHeight(final Integer value)
	{
		setAddressWidgetHeight( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.addressWidgetHeight</code> attribute. 
	 * @param value the addressWidgetHeight
	 */
	public void setAddressWidgetHeight(final SessionContext ctx, final int value)
	{
		setAddressWidgetHeight( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.addressWidgetHeight</code> attribute. 
	 * @param value the addressWidgetHeight
	 */
	public void setAddressWidgetHeight(final int value)
	{
		setAddressWidgetHeight( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.addressWidgetWidth</code> attribute.
	 * @return the addressWidgetWidth
	 */
	public Integer getAddressWidgetWidth(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, ADDRESSWIDGETWIDTH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.addressWidgetWidth</code> attribute.
	 * @return the addressWidgetWidth
	 */
	public Integer getAddressWidgetWidth()
	{
		return getAddressWidgetWidth( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.addressWidgetWidth</code> attribute. 
	 * @return the addressWidgetWidth
	 */
	public int getAddressWidgetWidthAsPrimitive(final SessionContext ctx)
	{
		Integer value = getAddressWidgetWidth( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.addressWidgetWidth</code> attribute. 
	 * @return the addressWidgetWidth
	 */
	public int getAddressWidgetWidthAsPrimitive()
	{
		return getAddressWidgetWidthAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.addressWidgetWidth</code> attribute. 
	 * @param value the addressWidgetWidth
	 */
	public void setAddressWidgetWidth(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, ADDRESSWIDGETWIDTH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.addressWidgetWidth</code> attribute. 
	 * @param value the addressWidgetWidth
	 */
	public void setAddressWidgetWidth(final Integer value)
	{
		setAddressWidgetWidth( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.addressWidgetWidth</code> attribute. 
	 * @param value the addressWidgetWidth
	 */
	public void setAddressWidgetWidth(final SessionContext ctx, final int value)
	{
		setAddressWidgetWidth( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.addressWidgetWidth</code> attribute. 
	 * @param value the addressWidgetWidth
	 */
	public void setAddressWidgetWidth(final int value)
	{
		setAddressWidgetWidth( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.amazonConfigCountry</code> attribute.
	 * @return the amazonConfigCountry - Other Country
	 */
	public EnumerationValue getAmazonConfigCountry(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, AMAZONCONFIGCOUNTRY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.amazonConfigCountry</code> attribute.
	 * @return the amazonConfigCountry - Other Country
	 */
	public EnumerationValue getAmazonConfigCountry()
	{
		return getAmazonConfigCountry( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.amazonConfigCountry</code> attribute. 
	 * @param value the amazonConfigCountry - Other Country
	 */
	public void setAmazonConfigCountry(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, AMAZONCONFIGCOUNTRY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.amazonConfigCountry</code> attribute. 
	 * @param value the amazonConfigCountry - Other Country
	 */
	public void setAmazonConfigCountry(final EnumerationValue value)
	{
		setAmazonConfigCountry( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.amazonLog</code> attribute.
	 * @return the amazonLog - log file
	 */
	public AmazonLog getAmazonLog(final SessionContext ctx)
	{
		return (AmazonLog)getProperty( ctx, AMAZONLOG);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.amazonLog</code> attribute.
	 * @return the amazonLog - log file
	 */
	public AmazonLog getAmazonLog()
	{
		return getAmazonLog( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.amazonLog</code> attribute. 
	 * @param value the amazonLog - log file
	 */
	public void setAmazonLog(final SessionContext ctx, final AmazonLog value)
	{
		setProperty(ctx, AMAZONLOG,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.amazonLog</code> attribute. 
	 * @param value the amazonLog - log file
	 */
	public void setAmazonLog(final AmazonLog value)
	{
		setAmazonLog( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.apiLog</code> attribute.
	 * @return the apiLog - log file
	 */
	public AmazonLog getApiLog(final SessionContext ctx)
	{
		return (AmazonLog)getProperty( ctx, APILOG);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.apiLog</code> attribute.
	 * @return the apiLog - log file
	 */
	public AmazonLog getApiLog()
	{
		return getApiLog( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.apiLog</code> attribute. 
	 * @param value the apiLog - log file
	 */
	public void setApiLog(final SessionContext ctx, final AmazonLog value)
	{
		setProperty(ctx, APILOG,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.apiLog</code> attribute. 
	 * @param value the apiLog - log file
	 */
	public void setApiLog(final AmazonLog value)
	{
		setApiLog( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.applicationName</code> attribute.
	 * @return the applicationName - Application Name
	 */
	public String getApplicationName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, APPLICATIONNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.applicationName</code> attribute.
	 * @return the applicationName - Application Name
	 */
	public String getApplicationName()
	{
		return getApplicationName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.applicationName</code> attribute. 
	 * @param value the applicationName - Application Name
	 */
	public void setApplicationName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, APPLICATIONNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.applicationName</code> attribute. 
	 * @param value the applicationName - Application Name
	 */
	public void setApplicationName(final String value)
	{
		setApplicationName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.applicationVersion</code> attribute.
	 * @return the applicationVersion - Application Version
	 */
	public String getApplicationVersion(final SessionContext ctx)
	{
		return (String)getProperty( ctx, APPLICATIONVERSION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.applicationVersion</code> attribute.
	 * @return the applicationVersion - Application Version
	 */
	public String getApplicationVersion()
	{
		return getApplicationVersion( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.applicationVersion</code> attribute. 
	 * @param value the applicationVersion - Application Version
	 */
	public void setApplicationVersion(final SessionContext ctx, final String value)
	{
		setProperty(ctx, APPLICATIONVERSION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.applicationVersion</code> attribute. 
	 * @param value the applicationVersion - Application Version
	 */
	public void setApplicationVersion(final String value)
	{
		setApplicationVersion( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.authorizationMode</code> attribute.
	 * @return the authorizationMode - chooses the authorization mode
	 */
	public EnumerationValue getAuthorizationMode(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, AUTHORIZATIONMODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.authorizationMode</code> attribute.
	 * @return the authorizationMode - chooses the authorization mode
	 */
	public EnumerationValue getAuthorizationMode()
	{
		return getAuthorizationMode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.authorizationMode</code> attribute. 
	 * @param value the authorizationMode - chooses the authorization mode
	 */
	public void setAuthorizationMode(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, AUTHORIZATIONMODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.authorizationMode</code> attribute. 
	 * @param value the authorizationMode - chooses the authorization mode
	 */
	public void setAuthorizationMode(final EnumerationValue value)
	{
		setAuthorizationMode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.captureMode</code> attribute.
	 * @return the captureMode - choose the capture mode, warning message that whitelisting with amazon payments is required for immediate
	 */
	public EnumerationValue getCaptureMode(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, CAPTUREMODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.captureMode</code> attribute.
	 * @return the captureMode - choose the capture mode, warning message that whitelisting with amazon payments is required for immediate
	 */
	public EnumerationValue getCaptureMode()
	{
		return getCaptureMode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.captureMode</code> attribute. 
	 * @param value the captureMode - choose the capture mode, warning message that whitelisting with amazon payments is required for immediate
	 */
	public void setCaptureMode(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, CAPTUREMODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.captureMode</code> attribute. 
	 * @param value the captureMode - choose the capture mode, warning message that whitelisting with amazon payments is required for immediate
	 */
	public void setCaptureMode(final EnumerationValue value)
	{
		setCaptureMode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.checkoutStrategy</code> attribute.
	 * @return the checkoutStrategy - switch for hide / show Amazon payment button
	 */
	public EnumerationValue getCheckoutStrategy(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, CHECKOUTSTRATEGY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.checkoutStrategy</code> attribute.
	 * @return the checkoutStrategy - switch for hide / show Amazon payment button
	 */
	public EnumerationValue getCheckoutStrategy()
	{
		return getCheckoutStrategy( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.checkoutStrategy</code> attribute. 
	 * @param value the checkoutStrategy - switch for hide / show Amazon payment button
	 */
	public void setCheckoutStrategy(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, CHECKOUTSTRATEGY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.checkoutStrategy</code> attribute. 
	 * @param value the checkoutStrategy - switch for hide / show Amazon payment button
	 */
	public void setCheckoutStrategy(final EnumerationValue value)
	{
		setCheckoutStrategy( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.clientId</code> attribute.
	 * @return the clientId - Other Country
	 */
	public String getClientId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CLIENTID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.clientId</code> attribute.
	 * @return the clientId - Other Country
	 */
	public String getClientId()
	{
		return getClientId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.clientId</code> attribute. 
	 * @param value the clientId - Other Country
	 */
	public void setClientId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CLIENTID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.clientId</code> attribute. 
	 * @param value the clientId - Other Country
	 */
	public void setClientId(final String value)
	{
		setClientId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.enabled</code> attribute.
	 * @return the enabled - switch for enabling / disabling Amazon Payments in general
	 */
	public Boolean isEnabled(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ENABLED);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.enabled</code> attribute.
	 * @return the enabled - switch for enabling / disabling Amazon Payments in general
	 */
	public Boolean isEnabled()
	{
		return isEnabled( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.enabled</code> attribute. 
	 * @return the enabled - switch for enabling / disabling Amazon Payments in general
	 */
	public boolean isEnabledAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isEnabled( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.enabled</code> attribute. 
	 * @return the enabled - switch for enabling / disabling Amazon Payments in general
	 */
	public boolean isEnabledAsPrimitive()
	{
		return isEnabledAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.enabled</code> attribute. 
	 * @param value the enabled - switch for enabling / disabling Amazon Payments in general
	 */
	public void setEnabled(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ENABLED,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.enabled</code> attribute. 
	 * @param value the enabled - switch for enabling / disabling Amazon Payments in general
	 */
	public void setEnabled(final Boolean value)
	{
		setEnabled( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.enabled</code> attribute. 
	 * @param value the enabled - switch for enabling / disabling Amazon Payments in general
	 */
	public void setEnabled(final SessionContext ctx, final boolean value)
	{
		setEnabled( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.enabled</code> attribute. 
	 * @param value the enabled - switch for enabling / disabling Amazon Payments in general
	 */
	public void setEnabled(final boolean value)
	{
		setEnabled( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.enableERPMode</code> attribute.
	 * @return the enableERPMode - erp mode turns off all automated authorization and capture functionality as well as ipn reception and/or polling, and disables any admin ui functionality that may trigger authorizations or captures
	 */
	public Boolean isEnableERPMode(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ENABLEERPMODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.enableERPMode</code> attribute.
	 * @return the enableERPMode - erp mode turns off all automated authorization and capture functionality as well as ipn reception and/or polling, and disables any admin ui functionality that may trigger authorizations or captures
	 */
	public Boolean isEnableERPMode()
	{
		return isEnableERPMode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.enableERPMode</code> attribute. 
	 * @return the enableERPMode - erp mode turns off all automated authorization and capture functionality as well as ipn reception and/or polling, and disables any admin ui functionality that may trigger authorizations or captures
	 */
	public boolean isEnableERPModeAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isEnableERPMode( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.enableERPMode</code> attribute. 
	 * @return the enableERPMode - erp mode turns off all automated authorization and capture functionality as well as ipn reception and/or polling, and disables any admin ui functionality that may trigger authorizations or captures
	 */
	public boolean isEnableERPModeAsPrimitive()
	{
		return isEnableERPModeAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.enableERPMode</code> attribute. 
	 * @param value the enableERPMode - erp mode turns off all automated authorization and capture functionality as well as ipn reception and/or polling, and disables any admin ui functionality that may trigger authorizations or captures
	 */
	public void setEnableERPMode(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ENABLEERPMODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.enableERPMode</code> attribute. 
	 * @param value the enableERPMode - erp mode turns off all automated authorization and capture functionality as well as ipn reception and/or polling, and disables any admin ui functionality that may trigger authorizations or captures
	 */
	public void setEnableERPMode(final Boolean value)
	{
		setEnableERPMode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.enableERPMode</code> attribute. 
	 * @param value the enableERPMode - erp mode turns off all automated authorization and capture functionality as well as ipn reception and/or polling, and disables any admin ui functionality that may trigger authorizations or captures
	 */
	public void setEnableERPMode(final SessionContext ctx, final boolean value)
	{
		setEnableERPMode( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.enableERPMode</code> attribute. 
	 * @param value the enableERPMode - erp mode turns off all automated authorization and capture functionality as well as ipn reception and/or polling, and disables any admin ui functionality that may trigger authorizations or captures
	 */
	public void setEnableERPMode(final boolean value)
	{
		setEnableERPMode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.enableLog</code> attribute.
	 * @return the enableLog - switch for enabling / disabling logging, exceptions should always be logged
	 */
	public Boolean isEnableLog(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ENABLELOG);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.enableLog</code> attribute.
	 * @return the enableLog - switch for enabling / disabling logging, exceptions should always be logged
	 */
	public Boolean isEnableLog()
	{
		return isEnableLog( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.enableLog</code> attribute. 
	 * @return the enableLog - switch for enabling / disabling logging, exceptions should always be logged
	 */
	public boolean isEnableLogAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isEnableLog( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.enableLog</code> attribute. 
	 * @return the enableLog - switch for enabling / disabling logging, exceptions should always be logged
	 */
	public boolean isEnableLogAsPrimitive()
	{
		return isEnableLogAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.enableLog</code> attribute. 
	 * @param value the enableLog - switch for enabling / disabling logging, exceptions should always be logged
	 */
	public void setEnableLog(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ENABLELOG,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.enableLog</code> attribute. 
	 * @param value the enableLog - switch for enabling / disabling logging, exceptions should always be logged
	 */
	public void setEnableLog(final Boolean value)
	{
		setEnableLog( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.enableLog</code> attribute. 
	 * @param value the enableLog - switch for enabling / disabling logging, exceptions should always be logged
	 */
	public void setEnableLog(final SessionContext ctx, final boolean value)
	{
		setEnableLog( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.enableLog</code> attribute. 
	 * @param value the enableLog - switch for enabling / disabling logging, exceptions should always be logged
	 */
	public void setEnableLog(final boolean value)
	{
		setEnableLog( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.excludePackstationDelivery</code> attribute.
	 * @return the excludePackstationDelivery - exclude packstation delivery for DE-centered integrations, Login and Pay mode required
	 */
	public Boolean isExcludePackstationDelivery(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, EXCLUDEPACKSTATIONDELIVERY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.excludePackstationDelivery</code> attribute.
	 * @return the excludePackstationDelivery - exclude packstation delivery for DE-centered integrations, Login and Pay mode required
	 */
	public Boolean isExcludePackstationDelivery()
	{
		return isExcludePackstationDelivery( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.excludePackstationDelivery</code> attribute. 
	 * @return the excludePackstationDelivery - exclude packstation delivery for DE-centered integrations, Login and Pay mode required
	 */
	public boolean isExcludePackstationDeliveryAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isExcludePackstationDelivery( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.excludePackstationDelivery</code> attribute. 
	 * @return the excludePackstationDelivery - exclude packstation delivery for DE-centered integrations, Login and Pay mode required
	 */
	public boolean isExcludePackstationDeliveryAsPrimitive()
	{
		return isExcludePackstationDeliveryAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.excludePackstationDelivery</code> attribute. 
	 * @param value the excludePackstationDelivery - exclude packstation delivery for DE-centered integrations, Login and Pay mode required
	 */
	public void setExcludePackstationDelivery(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, EXCLUDEPACKSTATIONDELIVERY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.excludePackstationDelivery</code> attribute. 
	 * @param value the excludePackstationDelivery - exclude packstation delivery for DE-centered integrations, Login and Pay mode required
	 */
	public void setExcludePackstationDelivery(final Boolean value)
	{
		setExcludePackstationDelivery( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.excludePackstationDelivery</code> attribute. 
	 * @param value the excludePackstationDelivery - exclude packstation delivery for DE-centered integrations, Login and Pay mode required
	 */
	public void setExcludePackstationDelivery(final SessionContext ctx, final boolean value)
	{
		setExcludePackstationDelivery( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.excludePackstationDelivery</code> attribute. 
	 * @param value the excludePackstationDelivery - exclude packstation delivery for DE-centered integrations, Login and Pay mode required
	 */
	public void setExcludePackstationDelivery(final boolean value)
	{
		setExcludePackstationDelivery( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.guestCheckoutStrategy</code> attribute.
	 * @return the guestCheckoutStrategy - switch for Allow Guest Checkout / Force account creation/Login on checkout
	 */
	public EnumerationValue getGuestCheckoutStrategy(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, GUESTCHECKOUTSTRATEGY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.guestCheckoutStrategy</code> attribute.
	 * @return the guestCheckoutStrategy - switch for Allow Guest Checkout / Force account creation/Login on checkout
	 */
	public EnumerationValue getGuestCheckoutStrategy()
	{
		return getGuestCheckoutStrategy( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.guestCheckoutStrategy</code> attribute. 
	 * @param value the guestCheckoutStrategy - switch for Allow Guest Checkout / Force account creation/Login on checkout
	 */
	public void setGuestCheckoutStrategy(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, GUESTCHECKOUTSTRATEGY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.guestCheckoutStrategy</code> attribute. 
	 * @param value the guestCheckoutStrategy - switch for Allow Guest Checkout / Force account creation/Login on checkout
	 */
	public void setGuestCheckoutStrategy(final EnumerationValue value)
	{
		setGuestCheckoutStrategy( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.hiddenButtonsMode</code> attribute.
	 * @return the hiddenButtonsMode - switch for hide / show Amazon payment button
	 */
	public Boolean isHiddenButtonsMode(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, HIDDENBUTTONSMODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.hiddenButtonsMode</code> attribute.
	 * @return the hiddenButtonsMode - switch for hide / show Amazon payment button
	 */
	public Boolean isHiddenButtonsMode()
	{
		return isHiddenButtonsMode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.hiddenButtonsMode</code> attribute. 
	 * @return the hiddenButtonsMode - switch for hide / show Amazon payment button
	 */
	public boolean isHiddenButtonsModeAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isHiddenButtonsMode( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.hiddenButtonsMode</code> attribute. 
	 * @return the hiddenButtonsMode - switch for hide / show Amazon payment button
	 */
	public boolean isHiddenButtonsModeAsPrimitive()
	{
		return isHiddenButtonsModeAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.hiddenButtonsMode</code> attribute. 
	 * @param value the hiddenButtonsMode - switch for hide / show Amazon payment button
	 */
	public void setHiddenButtonsMode(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, HIDDENBUTTONSMODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.hiddenButtonsMode</code> attribute. 
	 * @param value the hiddenButtonsMode - switch for hide / show Amazon payment button
	 */
	public void setHiddenButtonsMode(final Boolean value)
	{
		setHiddenButtonsMode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.hiddenButtonsMode</code> attribute. 
	 * @param value the hiddenButtonsMode - switch for hide / show Amazon payment button
	 */
	public void setHiddenButtonsMode(final SessionContext ctx, final boolean value)
	{
		setHiddenButtonsMode( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.hiddenButtonsMode</code> attribute. 
	 * @param value the hiddenButtonsMode - switch for hide / show Amazon payment button
	 */
	public void setHiddenButtonsMode(final boolean value)
	{
		setHiddenButtonsMode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.IPN</code> attribute.
	 * @return the IPN - switch for hide / show Amazon payment button
	 */
	public String getIPN(final SessionContext ctx)
	{
		return (String)getProperty( ctx, IPN);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.IPN</code> attribute.
	 * @return the IPN - switch for hide / show Amazon payment button
	 */
	public String getIPN()
	{
		return getIPN( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.ipnLog</code> attribute.
	 * @return the ipnLog - log file
	 */
	public AmazonLog getIpnLog(final SessionContext ctx)
	{
		return (AmazonLog)getProperty( ctx, IPNLOG);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.ipnLog</code> attribute.
	 * @return the ipnLog - log file
	 */
	public AmazonLog getIpnLog()
	{
		return getIpnLog( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.ipnLog</code> attribute. 
	 * @param value the ipnLog - log file
	 */
	public void setIpnLog(final SessionContext ctx, final AmazonLog value)
	{
		setProperty(ctx, IPNLOG,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.ipnLog</code> attribute. 
	 * @param value the ipnLog - log file
	 */
	public void setIpnLog(final AmazonLog value)
	{
		setIpnLog( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.ipnReception</code> attribute.
	 * @return the ipnReception - if ipn is supported enable or disable ipn reception, else status updates are retrieved using polling via cronjob
	 */
	public Boolean isIpnReception(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, IPNRECEPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.ipnReception</code> attribute.
	 * @return the ipnReception - if ipn is supported enable or disable ipn reception, else status updates are retrieved using polling via cronjob
	 */
	public Boolean isIpnReception()
	{
		return isIpnReception( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.ipnReception</code> attribute. 
	 * @return the ipnReception - if ipn is supported enable or disable ipn reception, else status updates are retrieved using polling via cronjob
	 */
	public boolean isIpnReceptionAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isIpnReception( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.ipnReception</code> attribute. 
	 * @return the ipnReception - if ipn is supported enable or disable ipn reception, else status updates are retrieved using polling via cronjob
	 */
	public boolean isIpnReceptionAsPrimitive()
	{
		return isIpnReceptionAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.ipnReception</code> attribute. 
	 * @param value the ipnReception - if ipn is supported enable or disable ipn reception, else status updates are retrieved using polling via cronjob
	 */
	public void setIpnReception(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, IPNRECEPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.ipnReception</code> attribute. 
	 * @param value the ipnReception - if ipn is supported enable or disable ipn reception, else status updates are retrieved using polling via cronjob
	 */
	public void setIpnReception(final Boolean value)
	{
		setIpnReception( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.ipnReception</code> attribute. 
	 * @param value the ipnReception - if ipn is supported enable or disable ipn reception, else status updates are retrieved using polling via cronjob
	 */
	public void setIpnReception(final SessionContext ctx, final boolean value)
	{
		setIpnReception( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.ipnReception</code> attribute. 
	 * @param value the ipnReception - if ipn is supported enable or disable ipn reception, else status updates are retrieved using polling via cronjob
	 */
	public void setIpnReception(final boolean value)
	{
		setIpnReception( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.ipWhitelisting</code> attribute.
	 * @return the ipWhitelisting - switch for hide / show Amazon payment button
	 */
	public String getIpWhitelisting(final SessionContext ctx)
	{
		return (String)getProperty( ctx, IPWHITELISTING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.ipWhitelisting</code> attribute.
	 * @return the ipWhitelisting - switch for hide / show Amazon payment button
	 */
	public String getIpWhitelisting()
	{
		return getIpWhitelisting( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.ipWhitelisting</code> attribute. 
	 * @param value the ipWhitelisting - switch for hide / show Amazon payment button
	 */
	public void setIpWhitelisting(final SessionContext ctx, final String value)
	{
		setProperty(ctx, IPWHITELISTING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.ipWhitelisting</code> attribute. 
	 * @param value the ipWhitelisting - switch for hide / show Amazon payment button
	 */
	public void setIpWhitelisting(final String value)
	{
		setIpWhitelisting( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.loginButtonColor</code> attribute.
	 * @return the loginButtonColor
	 */
	public EnumerationValue getLoginButtonColor(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, LOGINBUTTONCOLOR);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.loginButtonColor</code> attribute.
	 * @return the loginButtonColor
	 */
	public EnumerationValue getLoginButtonColor()
	{
		return getLoginButtonColor( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.loginButtonColor</code> attribute. 
	 * @param value the loginButtonColor
	 */
	public void setLoginButtonColor(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, LOGINBUTTONCOLOR,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.loginButtonColor</code> attribute. 
	 * @param value the loginButtonColor
	 */
	public void setLoginButtonColor(final EnumerationValue value)
	{
		setLoginButtonColor( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.loginButtonSize</code> attribute.
	 * @return the loginButtonSize
	 */
	public EnumerationValue getLoginButtonSize(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, LOGINBUTTONSIZE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.loginButtonSize</code> attribute.
	 * @return the loginButtonSize
	 */
	public EnumerationValue getLoginButtonSize()
	{
		return getLoginButtonSize( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.loginButtonSize</code> attribute. 
	 * @param value the loginButtonSize
	 */
	public void setLoginButtonSize(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, LOGINBUTTONSIZE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.loginButtonSize</code> attribute. 
	 * @param value the loginButtonSize
	 */
	public void setLoginButtonSize(final EnumerationValue value)
	{
		setLoginButtonSize( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.manualAddOnFirstLogin</code> attribute.
	 * @return the manualAddOnFirstLogin - Activate manual addition of user information on first login, all mandatory fields that are not provided by amazon should be manually filled by buyer
	 */
	public Boolean isManualAddOnFirstLogin(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, MANUALADDONFIRSTLOGIN);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.manualAddOnFirstLogin</code> attribute.
	 * @return the manualAddOnFirstLogin - Activate manual addition of user information on first login, all mandatory fields that are not provided by amazon should be manually filled by buyer
	 */
	public Boolean isManualAddOnFirstLogin()
	{
		return isManualAddOnFirstLogin( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.manualAddOnFirstLogin</code> attribute. 
	 * @return the manualAddOnFirstLogin - Activate manual addition of user information on first login, all mandatory fields that are not provided by amazon should be manually filled by buyer
	 */
	public boolean isManualAddOnFirstLoginAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isManualAddOnFirstLogin( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.manualAddOnFirstLogin</code> attribute. 
	 * @return the manualAddOnFirstLogin - Activate manual addition of user information on first login, all mandatory fields that are not provided by amazon should be manually filled by buyer
	 */
	public boolean isManualAddOnFirstLoginAsPrimitive()
	{
		return isManualAddOnFirstLoginAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.manualAddOnFirstLogin</code> attribute. 
	 * @param value the manualAddOnFirstLogin - Activate manual addition of user information on first login, all mandatory fields that are not provided by amazon should be manually filled by buyer
	 */
	public void setManualAddOnFirstLogin(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, MANUALADDONFIRSTLOGIN,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.manualAddOnFirstLogin</code> attribute. 
	 * @param value the manualAddOnFirstLogin - Activate manual addition of user information on first login, all mandatory fields that are not provided by amazon should be manually filled by buyer
	 */
	public void setManualAddOnFirstLogin(final Boolean value)
	{
		setManualAddOnFirstLogin( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.manualAddOnFirstLogin</code> attribute. 
	 * @param value the manualAddOnFirstLogin - Activate manual addition of user information on first login, all mandatory fields that are not provided by amazon should be manually filled by buyer
	 */
	public void setManualAddOnFirstLogin(final SessionContext ctx, final boolean value)
	{
		setManualAddOnFirstLogin( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.manualAddOnFirstLogin</code> attribute. 
	 * @param value the manualAddOnFirstLogin - Activate manual addition of user information on first login, all mandatory fields that are not provided by amazon should be manually filled by buyer
	 */
	public void setManualAddOnFirstLogin(final boolean value)
	{
		setManualAddOnFirstLogin( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.merchantId</code> attribute.
	 * @return the merchantId - Merchant ID or Store ID
	 */
	public String getMerchantId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MERCHANTID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.merchantId</code> attribute.
	 * @return the merchantId - Merchant ID or Store ID
	 */
	public String getMerchantId()
	{
		return getMerchantId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.merchantId</code> attribute. 
	 * @param value the merchantId - Merchant ID or Store ID
	 */
	public void setMerchantId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MERCHANTID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.merchantId</code> attribute. 
	 * @param value the merchantId - Merchant ID or Store ID
	 */
	public void setMerchantId(final String value)
	{
		setMerchantId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.mwsAccessKey</code> attribute.
	 * @return the mwsAccessKey - Merchant WebServices Access Key
	 */
	public String getMwsAccessKey(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MWSACCESSKEY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.mwsAccessKey</code> attribute.
	 * @return the mwsAccessKey - Merchant WebServices Access Key
	 */
	public String getMwsAccessKey()
	{
		return getMwsAccessKey( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.mwsAccessKey</code> attribute. 
	 * @param value the mwsAccessKey - Merchant WebServices Access Key
	 */
	public void setMwsAccessKey(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MWSACCESSKEY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.mwsAccessKey</code> attribute. 
	 * @param value the mwsAccessKey - Merchant WebServices Access Key
	 */
	public void setMwsAccessKey(final String value)
	{
		setMwsAccessKey( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.mwsSecretKey</code> attribute.
	 * @return the mwsSecretKey - Merchant WebServices Secret Key
	 */
	public String getMwsSecretKey(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MWSSECRETKEY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.mwsSecretKey</code> attribute.
	 * @return the mwsSecretKey - Merchant WebServices Secret Key
	 */
	public String getMwsSecretKey()
	{
		return getMwsSecretKey( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.mwsSecretKey</code> attribute. 
	 * @param value the mwsSecretKey - Merchant WebServices Secret Key
	 */
	public void setMwsSecretKey(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MWSSECRETKEY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.mwsSecretKey</code> attribute. 
	 * @param value the mwsSecretKey - Merchant WebServices Secret Key
	 */
	public void setMwsSecretKey(final String value)
	{
		setMwsSecretKey( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.normalCheckout</code> attribute.
	 * @return the normalCheckout - switch for enabling / disabling amazon payments as a payment method in the normal checkout process, in addition to being a separate checkout process
	 */
	public Boolean isNormalCheckout(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, NORMALCHECKOUT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.normalCheckout</code> attribute.
	 * @return the normalCheckout - switch for enabling / disabling amazon payments as a payment method in the normal checkout process, in addition to being a separate checkout process
	 */
	public Boolean isNormalCheckout()
	{
		return isNormalCheckout( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.normalCheckout</code> attribute. 
	 * @return the normalCheckout - switch for enabling / disabling amazon payments as a payment method in the normal checkout process, in addition to being a separate checkout process
	 */
	public boolean isNormalCheckoutAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isNormalCheckout( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.normalCheckout</code> attribute. 
	 * @return the normalCheckout - switch for enabling / disabling amazon payments as a payment method in the normal checkout process, in addition to being a separate checkout process
	 */
	public boolean isNormalCheckoutAsPrimitive()
	{
		return isNormalCheckoutAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.normalCheckout</code> attribute. 
	 * @param value the normalCheckout - switch for enabling / disabling amazon payments as a payment method in the normal checkout process, in addition to being a separate checkout process
	 */
	public void setNormalCheckout(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, NORMALCHECKOUT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.normalCheckout</code> attribute. 
	 * @param value the normalCheckout - switch for enabling / disabling amazon payments as a payment method in the normal checkout process, in addition to being a separate checkout process
	 */
	public void setNormalCheckout(final Boolean value)
	{
		setNormalCheckout( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.normalCheckout</code> attribute. 
	 * @param value the normalCheckout - switch for enabling / disabling amazon payments as a payment method in the normal checkout process, in addition to being a separate checkout process
	 */
	public void setNormalCheckout(final SessionContext ctx, final boolean value)
	{
		setNormalCheckout( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.normalCheckout</code> attribute. 
	 * @param value the normalCheckout - switch for enabling / disabling amazon payments as a payment method in the normal checkout process, in addition to being a separate checkout process
	 */
	public void setNormalCheckout(final boolean value)
	{
		setNormalCheckout( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.operationMode</code> attribute.
	 * @return the operationMode - switch for choosing operation mode
	 */
	public EnumerationValue getOperationMode(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, OPERATIONMODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.operationMode</code> attribute.
	 * @return the operationMode - switch for choosing operation mode
	 */
	public EnumerationValue getOperationMode()
	{
		return getOperationMode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.operationMode</code> attribute. 
	 * @param value the operationMode - switch for choosing operation mode
	 */
	public void setOperationMode(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, OPERATIONMODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.operationMode</code> attribute. 
	 * @param value the operationMode - switch for choosing operation mode
	 */
	public void setOperationMode(final EnumerationValue value)
	{
		setOperationMode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.orderStatusOnSuccessfullAuthorization</code> attribute.
	 * @return the orderStatusOnSuccessfullAuthorization - allows the user to selecte or enter a status for a successfully authorized order
	 */
	public String getOrderStatusOnSuccessfullAuthorization(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ORDERSTATUSONSUCCESSFULLAUTHORIZATION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.orderStatusOnSuccessfullAuthorization</code> attribute.
	 * @return the orderStatusOnSuccessfullAuthorization - allows the user to selecte or enter a status for a successfully authorized order
	 */
	public String getOrderStatusOnSuccessfullAuthorization()
	{
		return getOrderStatusOnSuccessfullAuthorization( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.orderStatusOnSuccessfullAuthorization</code> attribute. 
	 * @param value the orderStatusOnSuccessfullAuthorization - allows the user to selecte or enter a status for a successfully authorized order
	 */
	public void setOrderStatusOnSuccessfullAuthorization(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ORDERSTATUSONSUCCESSFULLAUTHORIZATION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.orderStatusOnSuccessfullAuthorization</code> attribute. 
	 * @param value the orderStatusOnSuccessfullAuthorization - allows the user to selecte or enter a status for a successfully authorized order
	 */
	public void setOrderStatusOnSuccessfullAuthorization(final String value)
	{
		setOrderStatusOnSuccessfullAuthorization( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.orderStatusOnSuccessfullShipping</code> attribute.
	 * @return the orderStatusOnSuccessfullShipping - allows the user to select or enter a status for a shipped order. this status triggers the capture event if capture on shipment is selected
	 */
	public String getOrderStatusOnSuccessfullShipping(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ORDERSTATUSONSUCCESSFULLSHIPPING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.orderStatusOnSuccessfullShipping</code> attribute.
	 * @return the orderStatusOnSuccessfullShipping - allows the user to select or enter a status for a shipped order. this status triggers the capture event if capture on shipment is selected
	 */
	public String getOrderStatusOnSuccessfullShipping()
	{
		return getOrderStatusOnSuccessfullShipping( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.orderStatusOnSuccessfullShipping</code> attribute. 
	 * @param value the orderStatusOnSuccessfullShipping - allows the user to select or enter a status for a shipped order. this status triggers the capture event if capture on shipment is selected
	 */
	public void setOrderStatusOnSuccessfullShipping(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ORDERSTATUSONSUCCESSFULLSHIPPING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.orderStatusOnSuccessfullShipping</code> attribute. 
	 * @param value the orderStatusOnSuccessfullShipping - allows the user to select or enter a status for a shipped order. this status triggers the capture event if capture on shipment is selected
	 */
	public void setOrderStatusOnSuccessfullShipping(final String value)
	{
		setOrderStatusOnSuccessfullShipping( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.otherCountry</code> attribute.
	 * @return the otherCountry - Other Country
	 */
	public String getOtherCountry(final SessionContext ctx)
	{
		return (String)getProperty( ctx, OTHERCOUNTRY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.otherCountry</code> attribute.
	 * @return the otherCountry - Other Country
	 */
	public String getOtherCountry()
	{
		return getOtherCountry( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.otherCountry</code> attribute. 
	 * @param value the otherCountry - Other Country
	 */
	public void setOtherCountry(final SessionContext ctx, final String value)
	{
		setProperty(ctx, OTHERCOUNTRY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.otherCountry</code> attribute. 
	 * @param value the otherCountry - Other Country
	 */
	public void setOtherCountry(final String value)
	{
		setOtherCountry( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.otherCountryCurrency</code> attribute.
	 * @return the otherCountryCurrency - Other Country
	 */
	public String getOtherCountryCurrency(final SessionContext ctx)
	{
		return (String)getProperty( ctx, OTHERCOUNTRYCURRENCY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.otherCountryCurrency</code> attribute.
	 * @return the otherCountryCurrency - Other Country
	 */
	public String getOtherCountryCurrency()
	{
		return getOtherCountryCurrency( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.otherCountryCurrency</code> attribute. 
	 * @param value the otherCountryCurrency - Other Country
	 */
	public void setOtherCountryCurrency(final SessionContext ctx, final String value)
	{
		setProperty(ctx, OTHERCOUNTRYCURRENCY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.otherCountryCurrency</code> attribute. 
	 * @param value the otherCountryCurrency - Other Country
	 */
	public void setOtherCountryCurrency(final String value)
	{
		setOtherCountryCurrency( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.packstationIdentifier</code> attribute.
	 * @return the packstationIdentifier - identify if an address is packstation
	 */
	public String getPackstationIdentifier(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PACKSTATIONIDENTIFIER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.packstationIdentifier</code> attribute.
	 * @return the packstationIdentifier - identify if an address is packstation
	 */
	public String getPackstationIdentifier()
	{
		return getPackstationIdentifier( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.packstationIdentifier</code> attribute. 
	 * @param value the packstationIdentifier - identify if an address is packstation
	 */
	public void setPackstationIdentifier(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PACKSTATIONIDENTIFIER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.packstationIdentifier</code> attribute. 
	 * @param value the packstationIdentifier - identify if an address is packstation
	 */
	public void setPackstationIdentifier(final String value)
	{
		setPackstationIdentifier( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.payButtonColor</code> attribute.
	 * @return the payButtonColor
	 */
	public EnumerationValue getPayButtonColor(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, PAYBUTTONCOLOR);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.payButtonColor</code> attribute.
	 * @return the payButtonColor
	 */
	public EnumerationValue getPayButtonColor()
	{
		return getPayButtonColor( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.payButtonColor</code> attribute. 
	 * @param value the payButtonColor
	 */
	public void setPayButtonColor(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, PAYBUTTONCOLOR,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.payButtonColor</code> attribute. 
	 * @param value the payButtonColor
	 */
	public void setPayButtonColor(final EnumerationValue value)
	{
		setPayButtonColor( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.payButtonSize</code> attribute.
	 * @return the payButtonSize
	 */
	public EnumerationValue getPayButtonSize(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, PAYBUTTONSIZE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.payButtonSize</code> attribute.
	 * @return the payButtonSize
	 */
	public EnumerationValue getPayButtonSize()
	{
		return getPayButtonSize( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.payButtonSize</code> attribute. 
	 * @param value the payButtonSize
	 */
	public void setPayButtonSize(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, PAYBUTTONSIZE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.payButtonSize</code> attribute. 
	 * @param value the payButtonSize
	 */
	public void setPayButtonSize(final EnumerationValue value)
	{
		setPayButtonSize( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.paymentWidgetHeight</code> attribute.
	 * @return the paymentWidgetHeight
	 */
	public Integer getPaymentWidgetHeight(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, PAYMENTWIDGETHEIGHT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.paymentWidgetHeight</code> attribute.
	 * @return the paymentWidgetHeight
	 */
	public Integer getPaymentWidgetHeight()
	{
		return getPaymentWidgetHeight( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.paymentWidgetHeight</code> attribute. 
	 * @return the paymentWidgetHeight
	 */
	public int getPaymentWidgetHeightAsPrimitive(final SessionContext ctx)
	{
		Integer value = getPaymentWidgetHeight( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.paymentWidgetHeight</code> attribute. 
	 * @return the paymentWidgetHeight
	 */
	public int getPaymentWidgetHeightAsPrimitive()
	{
		return getPaymentWidgetHeightAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.paymentWidgetHeight</code> attribute. 
	 * @param value the paymentWidgetHeight
	 */
	public void setPaymentWidgetHeight(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, PAYMENTWIDGETHEIGHT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.paymentWidgetHeight</code> attribute. 
	 * @param value the paymentWidgetHeight
	 */
	public void setPaymentWidgetHeight(final Integer value)
	{
		setPaymentWidgetHeight( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.paymentWidgetHeight</code> attribute. 
	 * @param value the paymentWidgetHeight
	 */
	public void setPaymentWidgetHeight(final SessionContext ctx, final int value)
	{
		setPaymentWidgetHeight( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.paymentWidgetHeight</code> attribute. 
	 * @param value the paymentWidgetHeight
	 */
	public void setPaymentWidgetHeight(final int value)
	{
		setPaymentWidgetHeight( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.paymentWidgetWidth</code> attribute.
	 * @return the paymentWidgetWidth
	 */
	public Integer getPaymentWidgetWidth(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, PAYMENTWIDGETWIDTH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.paymentWidgetWidth</code> attribute.
	 * @return the paymentWidgetWidth
	 */
	public Integer getPaymentWidgetWidth()
	{
		return getPaymentWidgetWidth( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.paymentWidgetWidth</code> attribute. 
	 * @return the paymentWidgetWidth
	 */
	public int getPaymentWidgetWidthAsPrimitive(final SessionContext ctx)
	{
		Integer value = getPaymentWidgetWidth( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.paymentWidgetWidth</code> attribute. 
	 * @return the paymentWidgetWidth
	 */
	public int getPaymentWidgetWidthAsPrimitive()
	{
		return getPaymentWidgetWidthAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.paymentWidgetWidth</code> attribute. 
	 * @param value the paymentWidgetWidth
	 */
	public void setPaymentWidgetWidth(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, PAYMENTWIDGETWIDTH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.paymentWidgetWidth</code> attribute. 
	 * @param value the paymentWidgetWidth
	 */
	public void setPaymentWidgetWidth(final Integer value)
	{
		setPaymentWidgetWidth( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.paymentWidgetWidth</code> attribute. 
	 * @param value the paymentWidgetWidth
	 */
	public void setPaymentWidgetWidth(final SessionContext ctx, final int value)
	{
		setPaymentWidgetWidth( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.paymentWidgetWidth</code> attribute. 
	 * @param value the paymentWidgetWidth
	 */
	public void setPaymentWidgetWidth(final int value)
	{
		setPaymentWidgetWidth( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.requestCompleteDeliveryAddress</code> attribute.
	 * @return the requestCompleteDeliveryAddress - request complete delivery address already during checkout (Login and Pay mode required)
	 */
	public Boolean isRequestCompleteDeliveryAddress(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, REQUESTCOMPLETEDELIVERYADDRESS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.requestCompleteDeliveryAddress</code> attribute.
	 * @return the requestCompleteDeliveryAddress - request complete delivery address already during checkout (Login and Pay mode required)
	 */
	public Boolean isRequestCompleteDeliveryAddress()
	{
		return isRequestCompleteDeliveryAddress( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.requestCompleteDeliveryAddress</code> attribute. 
	 * @return the requestCompleteDeliveryAddress - request complete delivery address already during checkout (Login and Pay mode required)
	 */
	public boolean isRequestCompleteDeliveryAddressAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isRequestCompleteDeliveryAddress( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.requestCompleteDeliveryAddress</code> attribute. 
	 * @return the requestCompleteDeliveryAddress - request complete delivery address already during checkout (Login and Pay mode required)
	 */
	public boolean isRequestCompleteDeliveryAddressAsPrimitive()
	{
		return isRequestCompleteDeliveryAddressAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.requestCompleteDeliveryAddress</code> attribute. 
	 * @param value the requestCompleteDeliveryAddress - request complete delivery address already during checkout (Login and Pay mode required)
	 */
	public void setRequestCompleteDeliveryAddress(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, REQUESTCOMPLETEDELIVERYADDRESS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.requestCompleteDeliveryAddress</code> attribute. 
	 * @param value the requestCompleteDeliveryAddress - request complete delivery address already during checkout (Login and Pay mode required)
	 */
	public void setRequestCompleteDeliveryAddress(final Boolean value)
	{
		setRequestCompleteDeliveryAddress( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.requestCompleteDeliveryAddress</code> attribute. 
	 * @param value the requestCompleteDeliveryAddress - request complete delivery address already during checkout (Login and Pay mode required)
	 */
	public void setRequestCompleteDeliveryAddress(final SessionContext ctx, final boolean value)
	{
		setRequestCompleteDeliveryAddress( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.requestCompleteDeliveryAddress</code> attribute. 
	 * @param value the requestCompleteDeliveryAddress - request complete delivery address already during checkout (Login and Pay mode required)
	 */
	public void setRequestCompleteDeliveryAddress(final boolean value)
	{
		setRequestCompleteDeliveryAddress( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.sandboxMode</code> attribute.
	 * @return the sandboxMode - switch for enabling / disabling Amazon Sandbox
	 */
	public Boolean isSandboxMode(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, SANDBOXMODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.sandboxMode</code> attribute.
	 * @return the sandboxMode - switch for enabling / disabling Amazon Sandbox
	 */
	public Boolean isSandboxMode()
	{
		return isSandboxMode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.sandboxMode</code> attribute. 
	 * @return the sandboxMode - switch for enabling / disabling Amazon Sandbox
	 */
	public boolean isSandboxModeAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isSandboxMode( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.sandboxMode</code> attribute. 
	 * @return the sandboxMode - switch for enabling / disabling Amazon Sandbox
	 */
	public boolean isSandboxModeAsPrimitive()
	{
		return isSandboxModeAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.sandboxMode</code> attribute. 
	 * @param value the sandboxMode - switch for enabling / disabling Amazon Sandbox
	 */
	public void setSandboxMode(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, SANDBOXMODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.sandboxMode</code> attribute. 
	 * @param value the sandboxMode - switch for enabling / disabling Amazon Sandbox
	 */
	public void setSandboxMode(final Boolean value)
	{
		setSandboxMode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.sandboxMode</code> attribute. 
	 * @param value the sandboxMode - switch for enabling / disabling Amazon Sandbox
	 */
	public void setSandboxMode(final SessionContext ctx, final boolean value)
	{
		setSandboxMode( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.sandboxMode</code> attribute. 
	 * @param value the sandboxMode - switch for enabling / disabling Amazon Sandbox
	 */
	public void setSandboxMode(final boolean value)
	{
		setSandboxMode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.sandboxSimulate</code> attribute.
	 * @return the sandboxSimulate - switch for enabling / disabling Amazon Sandbox Simulation
	 */
	public Boolean isSandboxSimulate(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, SANDBOXSIMULATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.sandboxSimulate</code> attribute.
	 * @return the sandboxSimulate - switch for enabling / disabling Amazon Sandbox Simulation
	 */
	public Boolean isSandboxSimulate()
	{
		return isSandboxSimulate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.sandboxSimulate</code> attribute. 
	 * @return the sandboxSimulate - switch for enabling / disabling Amazon Sandbox Simulation
	 */
	public boolean isSandboxSimulateAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isSandboxSimulate( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.sandboxSimulate</code> attribute. 
	 * @return the sandboxSimulate - switch for enabling / disabling Amazon Sandbox Simulation
	 */
	public boolean isSandboxSimulateAsPrimitive()
	{
		return isSandboxSimulateAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.sandboxSimulate</code> attribute. 
	 * @param value the sandboxSimulate - switch for enabling / disabling Amazon Sandbox Simulation
	 */
	public void setSandboxSimulate(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, SANDBOXSIMULATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.sandboxSimulate</code> attribute. 
	 * @param value the sandboxSimulate - switch for enabling / disabling Amazon Sandbox Simulation
	 */
	public void setSandboxSimulate(final Boolean value)
	{
		setSandboxSimulate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.sandboxSimulate</code> attribute. 
	 * @param value the sandboxSimulate - switch for enabling / disabling Amazon Sandbox Simulation
	 */
	public void setSandboxSimulate(final SessionContext ctx, final boolean value)
	{
		setSandboxSimulate( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.sandboxSimulate</code> attribute. 
	 * @param value the sandboxSimulate - switch for enabling / disabling Amazon Sandbox Simulation
	 */
	public void setSandboxSimulate(final boolean value)
	{
		setSandboxSimulate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.testOrderReferenceId</code> attribute.
	 * @return the testOrderReferenceId - Order Reference Id for test credentials
	 */
	public String getTestOrderReferenceId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TESTORDERREFERENCEID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonConfig.testOrderReferenceId</code> attribute.
	 * @return the testOrderReferenceId - Order Reference Id for test credentials
	 */
	public String getTestOrderReferenceId()
	{
		return getTestOrderReferenceId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.testOrderReferenceId</code> attribute. 
	 * @param value the testOrderReferenceId - Order Reference Id for test credentials
	 */
	public void setTestOrderReferenceId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TESTORDERREFERENCEID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonConfig.testOrderReferenceId</code> attribute. 
	 * @param value the testOrderReferenceId - Order Reference Id for test credentials
	 */
	public void setTestOrderReferenceId(final String value)
	{
		setTestOrderReferenceId( getSession().getSessionContext(), value );
	}
	
}
