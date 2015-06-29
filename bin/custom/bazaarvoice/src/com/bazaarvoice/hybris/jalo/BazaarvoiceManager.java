package com.bazaarvoice.hybris.jalo;

import com.bazaarvoice.hybris.constants.BazaarvoiceConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class BazaarvoiceManager extends GeneratedBazaarvoiceManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( BazaarvoiceManager.class.getName() );
	
	public static final BazaarvoiceManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (BazaarvoiceManager) em.getExtension(BazaarvoiceConstants.EXTENSIONNAME);
	}
	
}
