/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 21/07/2016 18:08:31                         ---
 * ----------------------------------------------------------------
 */
package de.fliegersoftware.amazon.payment.jalo;

import de.fliegersoftware.amazon.payment.constants.AmazonpaymentConstants;
import de.hybris.platform.basecommerce.jalo.site.BaseSite;
import de.hybris.platform.cronjob.jalo.CronJob;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.fliegersoftware.amazon.payment.jalo.AmazonBaseCronJob AmazonBaseCronJob}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedAmazonBaseCronJob extends CronJob
{
	/** Qualifier of the <code>AmazonBaseCronJob.site</code> attribute **/
	public static final String SITE = "site";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CronJob.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(SITE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonBaseCronJob.site</code> attribute.
	 * @return the site - Session's Site to consider for configuration
	 */
	public BaseSite getSite(final SessionContext ctx)
	{
		return (BaseSite)getProperty( ctx, SITE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AmazonBaseCronJob.site</code> attribute.
	 * @return the site - Session's Site to consider for configuration
	 */
	public BaseSite getSite()
	{
		return getSite( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonBaseCronJob.site</code> attribute. 
	 * @param value the site - Session's Site to consider for configuration
	 */
	public void setSite(final SessionContext ctx, final BaseSite value)
	{
		setProperty(ctx, SITE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AmazonBaseCronJob.site</code> attribute. 
	 * @param value the site - Session's Site to consider for configuration
	 */
	public void setSite(final BaseSite value)
	{
		setSite( getSession().getSessionContext(), value );
	}
	
}
