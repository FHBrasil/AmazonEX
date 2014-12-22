/**
 *
 */
package de.kpfamily.core.jalo;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.security.JaloSecurityException;


/**
 * @author franthescollymaneira
 *
 */
public class JaloUtils
{
	public static synchronized <T> T getAttributeSilently(final Item item, final String qualifier)
	{
		try
		{
			return (T) item.getAttribute(qualifier);
		}
		catch (JaloInvalidParameterException | JaloSecurityException e)
		{
			// shut up :)
		}

		return null;
	}
}