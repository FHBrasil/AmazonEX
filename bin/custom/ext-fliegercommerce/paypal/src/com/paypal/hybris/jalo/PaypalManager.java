package com.paypal.hybris.jalo;

import com.paypal.hybris.constants.PaypalConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class PaypalManager extends GeneratedPaypalManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( PaypalManager.class.getName() );
	
	public static final PaypalManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (PaypalManager) em.getExtension(PaypalConstants.EXTENSIONNAME);
	}
	
}
