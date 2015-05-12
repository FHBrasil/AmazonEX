/**
 * 
 */
package com.paypal.hybris.cronjobs;

import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.cronjob.model.TriggerModel;
import de.hybris.platform.servicelayer.cronjob.CronJobService;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.internal.model.ServicelayerJobModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.paypal.hybris.config.PaypalConfigManager;
import com.paypal.hybris.config.PaypalConfigPropertiesInitializer;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.model.PaypalOperationsCleanerCronJobModel;

/**
 * @author Christina Romashchenko
 * @author Valentyn Markovych, Gorilla
 * 
 */
@Component
@Scope("tenant")
@SystemSetup(extension = PaypalConstants.EXTENSIONNAME)
public class PaypalOperationsCleanerTrigger {

	private static final Logger LOG = Logger
			.getLogger(PaypalOperationsCleanerTrigger.class);

	@Resource
	private CronJobService cronJobService;

	@Resource
	private ModelService modelService;

	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Resource
	private PaypalConfigPropertiesInitializer paypalConfigPropertiesInitializer;

	@Resource
	private PaypalConfigManager paypalConfigManager;

	@SuppressWarnings("unchecked")
	List<ServicelayerJobModel> servicelayerJobModelList = Collections.EMPTY_LIST;
	ServicelayerJobModel servicelayerJobModel = null;
	PaypalOperationsCleanerCronJobModel cronJobModel = null;

	@SystemSetup(type = Type.PROJECT, process = Process.ALL)
	public void setTrigger(final SystemSetupContext context) {

		// Ensure PaypalConfigManager is initialized first
		try {
			paypalConfigPropertiesInitializer.createProjectData(context);
		} catch (final Exception e1) {
			LOG.error("PaypalOperationsCleaner cant init PayPalConfigManager. Trying to use existing instance of PaypalConfigManager.");
			e1.printStackTrace();
		}

		final ServicelayerJobModel sjm = new ServicelayerJobModel();
		sjm.setSpringId("paypalOperationsCleaner");
		try {
			servicelayerJobModel = flexibleSearchService.getModelByExample(sjm);
		} catch (final ModelNotFoundException e) {
			servicelayerJobModel = modelService
					.create(ServicelayerJobModel.class);
			servicelayerJobModel.setSpringId("paypalOperationsCleaner");
			servicelayerJobModel.setCode("paypalOperationsCleaner");
			modelService.save(servicelayerJobModel);
		}
		// cronJobModel =
		// modelService.create(PaypalOperationsCleanerCronJobModel.class);
		cronJobModel = new PaypalOperationsCleanerCronJobModel();
		cronJobModel.setCode(PaypalConstants.CLEANER_JOB_CODE);
		try {
			cronJobModel = flexibleSearchService
					.getModelByExample(cronJobModel);
		} catch (final ModelNotFoundException e) {
			cronJobModel.setJob(servicelayerJobModel);// if the model is new
														// then we set new
														// jobModel
		}
		cronJobModel.setActive(Boolean.TRUE);

		modelService.save(cronJobModel);

		final TriggerModel triggerModel = modelService
				.create(TriggerModel.class);
		triggerModel.setActive(Boolean.TRUE);
		final String cronExpression = paypalConfigManager
				.getProperty("cronExpression");
		LOG.info("cronExpression is " + cronExpression);
		triggerModel.setCronExpression(cronExpression);
		triggerModel.setCronJob(cronJobModel);
		modelService.save(triggerModel);
	}

}
