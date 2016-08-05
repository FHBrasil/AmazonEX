/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 21/07/2016 18:08:31                         ---
 * ----------------------------------------------------------------
 */
package de.fliegersoftware.amazon.payment.jalo;

import de.fliegersoftware.amazon.payment.constants.AmazonpaymentConstants;
import de.fliegersoftware.amazon.payment.jalo.AmazonBaseCronJob;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.Cart;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>AmazonpaymentManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedAmazonpaymentManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put("preCreatedOrderCode", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.order.Cart", Collections.unmodifiableMap(tmp));
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
	
	public AmazonBaseCronJob createAmazonBaseCronJob(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( AmazonpaymentConstants.TC.AMAZONBASECRONJOB );
			return (AmazonBaseCronJob)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating AmazonBaseCronJob : "+e.getMessage(), 0 );
		}
	}
	
	public AmazonBaseCronJob createAmazonBaseCronJob(final Map attributeValues)
	{
		return createAmazonBaseCronJob( getSession().getSessionContext(), attributeValues );
	}
	
	@Override
	public String getName()
	{
		return AmazonpaymentConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Cart.preCreatedOrderCode</code> attribute.
	 * @return the preCreatedOrderCode - Carts may have an order pre-created and then reused on placeOrder
	 */
	public String getPreCreatedOrderCode(final SessionContext ctx, final Cart item)
	{
		return (String)item.getProperty( ctx, AmazonpaymentConstants.Attributes.Cart.PRECREATEDORDERCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Cart.preCreatedOrderCode</code> attribute.
	 * @return the preCreatedOrderCode - Carts may have an order pre-created and then reused on placeOrder
	 */
	public String getPreCreatedOrderCode(final Cart item)
	{
		return getPreCreatedOrderCode( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Cart.preCreatedOrderCode</code> attribute. 
	 * @param value the preCreatedOrderCode - Carts may have an order pre-created and then reused on placeOrder
	 */
	public void setPreCreatedOrderCode(final SessionContext ctx, final Cart item, final String value)
	{
		item.setProperty(ctx, AmazonpaymentConstants.Attributes.Cart.PRECREATEDORDERCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Cart.preCreatedOrderCode</code> attribute. 
	 * @param value the preCreatedOrderCode - Carts may have an order pre-created and then reused on placeOrder
	 */
	public void setPreCreatedOrderCode(final Cart item, final String value)
	{
		setPreCreatedOrderCode( getSession().getSessionContext(), item, value );
	}
	
}
