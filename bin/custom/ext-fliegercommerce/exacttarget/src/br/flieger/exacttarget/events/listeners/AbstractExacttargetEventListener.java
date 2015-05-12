/**
 * 
 */
package br.flieger.exacttarget.events.listeners;

import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.util.Config;

import org.apache.log4j.Logger;

import br.flieger.exacttarget.events.AbstractExacttargetEvent;

/**
 * @author Vinicius de Souza
 *
 */
public abstract class AbstractExacttargetEventListener<T extends AbstractExacttargetEvent> extends AbstractEventListener<T>
{
	private final Logger LOG = Logger.getLogger(AbstractExacttargetEventListener.class);
	
	private final boolean FLAG_SEND_EMAIL = Config.getParameter("exacttarget.flag-send").equalsIgnoreCase("true");
	
	protected boolean isSendEmail() {
		return FLAG_SEND_EMAIL;
	}
	
	/**
	 * No override this method.
	 */
	@Override
	protected void onEvent(T event)
	{		
		switch (Config.getParameter("exacttarget.flag-send"))
		{
			case "true":
				shooting(event);
				break;
			case "false":
				LOG.warn("Send e-mail disable!");
				break;
			default:
				LOG.error("Invalid param to exacttarget.flag-send - "+Config.getParameter("exacttarget.flag-send"));
				break;
		}
	}
	
	/**
	 * Shooting send e-mail.
	 * @param event AbstractExacttargetEvent
	 */
	abstract protected void shooting(T event);
}