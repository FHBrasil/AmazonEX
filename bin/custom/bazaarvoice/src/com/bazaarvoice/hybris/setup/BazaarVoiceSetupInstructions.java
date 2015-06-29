package com.bazaarvoice.hybris.setup;

import com.bazaarvoice.hybris.constants.BazaarvoiceConstants;
import de.hybris.platform.acceleratorservices.setup.AbstractSystemSetup;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;

import java.util.List;

/**
 * Created by artlaber on 3/18/14.
 */
@SystemSetup(extension = BazaarvoiceConstants.EXTENSIONNAME)
public class BazaarVoiceSetupInstructions extends AbstractSystemSetup {
	/**
	 * This method will be called by system creator during initialization and system update. Be sure that this method can
	 * be called repeatedly.
	 *
	 * @param context
	 *           the context provides the selected parameters and values
	 */

	@SystemSetup(type = SystemSetup.Type.PROJECT, process = SystemSetup.Process.ALL)
	public void createEssentialData(final SystemSetupContext context)
	{
		logInfo(context, "Importing /bazaarvoice/import/bazaarvoice_data.impex");
		importImpexFile(context, "/bazaarvoice/import/bazaarvoice_data.impex");
	}

	@Override
	public List<SystemSetupParameter> getInitializationOptions() {
		return null;
	}
}