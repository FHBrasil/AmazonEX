/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 21/07/2016 18:08:31                         ---
 * ----------------------------------------------------------------
 */
package de.fliegersoftware.amazon.core.jalo;

import de.fliegersoftware.amazon.core.constants.AmazoncoreConstants;
import de.fliegersoftware.amazon.core.jalo.AmazonPaymentPaymentInfo;
import de.fliegersoftware.amazon.core.jalo.AmazonRefund;
import de.fliegersoftware.amazon.core.jalo.config.AmazonConfig;
import de.fliegersoftware.amazon.core.jalo.media.AmazonLog;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import de.hybris.platform.jalo.user.Customer;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.store.BaseStore;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>AmazoncoreManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedAmazoncoreManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put("amazonCustomerId", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.user.Customer", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("amazonConfig", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.store.BaseStore", Collections.unmodifiableMap(tmp));
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.amazonConfig</code> attribute.
	 * @return the amazonConfig
	 */
	public AmazonConfig getAmazonConfig(final SessionContext ctx, final BaseStore item)
	{
		return (AmazonConfig)item.getProperty( ctx, AmazoncoreConstants.Attributes.BaseStore.AMAZONCONFIG);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.amazonConfig</code> attribute.
	 * @return the amazonConfig
	 */
	public AmazonConfig getAmazonConfig(final BaseStore item)
	{
		return getAmazonConfig( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseStore.amazonConfig</code> attribute. 
	 * @param value the amazonConfig
	 */
	public void setAmazonConfig(final SessionContext ctx, final BaseStore item, final AmazonConfig value)
	{
		item.setProperty(ctx, AmazoncoreConstants.Attributes.BaseStore.AMAZONCONFIG,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseStore.amazonConfig</code> attribute. 
	 * @param value the amazonConfig
	 */
	public void setAmazonConfig(final BaseStore item, final AmazonConfig value)
	{
		setAmazonConfig( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.amazonCustomerId</code> attribute.
	 * @return the amazonCustomerId
	 */
	public String getAmazonCustomerId(final SessionContext ctx, final Customer item)
	{
		return (String)item.getProperty( ctx, AmazoncoreConstants.Attributes.Customer.AMAZONCUSTOMERID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.amazonCustomerId</code> attribute.
	 * @return the amazonCustomerId
	 */
	public String getAmazonCustomerId(final Customer item)
	{
		return getAmazonCustomerId( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.amazonCustomerId</code> attribute. 
	 * @param value the amazonCustomerId
	 */
	public void setAmazonCustomerId(final SessionContext ctx, final Customer item, final String value)
	{
		item.setProperty(ctx, AmazoncoreConstants.Attributes.Customer.AMAZONCUSTOMERID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.amazonCustomerId</code> attribute. 
	 * @param value the amazonCustomerId
	 */
	public void setAmazonCustomerId(final Customer item, final String value)
	{
		setAmazonCustomerId( getSession().getSessionContext(), item, value );
	}
	
	public AmazonConfig createAmazonConfig(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( AmazoncoreConstants.TC.AMAZONCONFIG );
			return (AmazonConfig)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating AmazonConfig : "+e.getMessage(), 0 );
		}
	}
	
	public AmazonConfig createAmazonConfig(final Map attributeValues)
	{
		return createAmazonConfig( getSession().getSessionContext(), attributeValues );
	}
	
	public AmazonLog createAmazonLog(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( AmazoncoreConstants.TC.AMAZONLOG );
			return (AmazonLog)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating AmazonLog : "+e.getMessage(), 0 );
		}
	}
	
	public AmazonLog createAmazonLog(final Map attributeValues)
	{
		return createAmazonLog( getSession().getSessionContext(), attributeValues );
	}
	
	public AmazonPaymentPaymentInfo createAmazonPaymentPaymentInfo(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( AmazoncoreConstants.TC.AMAZONPAYMENTPAYMENTINFO );
			return (AmazonPaymentPaymentInfo)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating AmazonPaymentPaymentInfo : "+e.getMessage(), 0 );
		}
	}
	
	public AmazonPaymentPaymentInfo createAmazonPaymentPaymentInfo(final Map attributeValues)
	{
		return createAmazonPaymentPaymentInfo( getSession().getSessionContext(), attributeValues );
	}
	
	public AmazonRefund createAmazonRefund(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( AmazoncoreConstants.TC.AMAZONREFUND );
			return (AmazonRefund)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating AmazonRefund : "+e.getMessage(), 0 );
		}
	}
	
	public AmazonRefund createAmazonRefund(final Map attributeValues)
	{
		return createAmazonRefund( getSession().getSessionContext(), attributeValues );
	}
	
	@Override
	public String getName()
	{
		return AmazoncoreConstants.EXTENSIONNAME;
	}
	
}
