/**
 * 
 */
package com.paypal.hybris.config;

import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.paypal.hybris.constants.PaypalConstants;

/**
 * @author christina romashchenko
 * 
 */
@Component
@Scope("tenant")
@SystemSetup(extension = PaypalConstants.EXTENSIONNAME)
public class PaypalConfigPropertiesInitializer {

	private static final Logger LOG = Logger
			.getLogger(PaypalConfigPropertiesInitializer.class);

	private static final String CONFIG_IGNORE = "ignore";
	private static final String CONFIG_UPDATE = "update";
	private static final String CONFIG_REWRITE = "rewrite";

	private static final String CONFIG_CONTEXT_NAME = "updateConfigProperties";

	@Resource
	private PaypalConfigManager paypalConfigManager;

	@SystemSetupParameterMethod
	public List<SystemSetupParameter> getSystemSetupParameters() {

		final List<SystemSetupParameter> params = new ArrayList<SystemSetupParameter>();
		final SystemSetupParameter config = new SystemSetupParameter(
				CONFIG_CONTEXT_NAME);
		config.setLabel("Update/rewrite paypal config properties : ");
		config.addValues(new String[] { CONFIG_UPDATE, CONFIG_REWRITE,
				CONFIG_IGNORE });
		params.add(config);

		return params;
	}

	@SystemSetup(type = Type.PROJECT, process = Process.ALL)
	public void createProjectData(final SystemSetupContext context)
			throws Exception {

		final String option = context
				.getParameter(PaypalConstants.EXTENSIONNAME + "_"
						+ CONFIG_CONTEXT_NAME);
		if (option == null) {
			LOG.info("PROCESS: "
					+ (context.getProcess().isInit() ? "INIT" : "UPDATE")
					+ " EXTENSION: " + context.getExtensionName()
					+ " FAILURE: incorrectly selected options!");
			return;
		}
		LOG.info("PROCESS: "
				+ (context.getProcess().isInit() ? "INIT" : "UPDATE")
				+ " EXTENSION: " + context.getExtensionName() + " OPTION:"
				+ option);

		if (option.equals(CONFIG_UPDATE)) {
			paypalConfigManager.updateDbProperties();
		} else if (option.equals(CONFIG_REWRITE)) {
			paypalConfigManager.rewriteDbProperties();
		}
	}

}
