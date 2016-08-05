/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 21/07/2016 18:08:31                         ---
 * ----------------------------------------------------------------
 */
package de.fliegersoftware.amazon.payment.addon.jalo;

import de.fliegersoftware.amazon.payment.addon.constants.AmazonpaymentaddonConstants;
import de.fliegersoftware.amazon.payment.addon.jalo.AmazonAddressBookComponent;
import de.fliegersoftware.amazon.payment.addon.jalo.AmazonPayButtonComponent;
import de.fliegersoftware.amazon.payment.addon.jalo.AmazonWalletComponent;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>AmazonpaymentaddonManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedAmazonpaymentaddonManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
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
	
	public AmazonAddressBookComponent createAmazonAddressBookComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( AmazonpaymentaddonConstants.TC.AMAZONADDRESSBOOKCOMPONENT );
			return (AmazonAddressBookComponent)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating AmazonAddressBookComponent : "+e.getMessage(), 0 );
		}
	}
	
	public AmazonAddressBookComponent createAmazonAddressBookComponent(final Map attributeValues)
	{
		return createAmazonAddressBookComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public AmazonPayButtonComponent createAmazonPayButtonComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( AmazonpaymentaddonConstants.TC.AMAZONPAYBUTTONCOMPONENT );
			return (AmazonPayButtonComponent)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating AmazonPayButtonComponent : "+e.getMessage(), 0 );
		}
	}
	
	public AmazonPayButtonComponent createAmazonPayButtonComponent(final Map attributeValues)
	{
		return createAmazonPayButtonComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public AmazonWalletComponent createAmazonWalletComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( AmazonpaymentaddonConstants.TC.AMAZONWALLETCOMPONENT );
			return (AmazonWalletComponent)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating AmazonWalletComponent : "+e.getMessage(), 0 );
		}
	}
	
	public AmazonWalletComponent createAmazonWalletComponent(final Map attributeValues)
	{
		return createAmazonWalletComponent( getSession().getSessionContext(), attributeValues );
	}
	
	@Override
	public String getName()
	{
		return AmazonpaymentaddonConstants.EXTENSIONNAME;
	}
	
}
