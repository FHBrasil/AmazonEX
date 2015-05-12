package com.paypal.hybris.cronjobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.session.SessionService;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.paypal.hybris.config.PaypalConfigManager;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.dao.PaypalOperationDao;
import com.paypal.hybris.model.PaypalOperationModel;
import com.paypal.hybris.model.PaypalOperationsCleanerCronJobModel;

/**
 * @author Christina Romashchenko
 * @author Valentyn Markovych
 * 
 */
@Component("paypalOperationsCleaner")
@Scope("tenant")
public class PaypalOperationsCleanerJobPerformable extends
		AbstractJobPerformable<PaypalOperationsCleanerCronJobModel> {

	private static final Logger LOG = Logger
			.getLogger(PaypalOperationsCleanerJobPerformable.class.getName());

	@Resource
	private PaypalOperationDao paypalOperationDao;

	@Resource
	private PaypalConfigManager paypalConfigManager;

	private static final int MULTIPLIER = 60 * 60 * 1000; // Hours to
															// milliseconds

	@Override
	public PerformResult perform(
			final PaypalOperationsCleanerCronJobModel cronJob) {

		LOG.info("**********************************");
		LOG.info("PaypalOperationsCleanerJobPerformable process...");
		LOG.info("**********************************");
		final List<PaypalOperationModel> resultList = paypalOperationDao
				.getOperations();
		removeExpiredOperations(resultList);
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	private void removeExpiredOperations(final List<PaypalOperationModel> list) {

		final long lifeTime = getLifetime();
		final long now = new Date().getTime();
		LOG.info("Removing of PaypalOperations:");
		for (final PaypalOperationModel model : list) {
			if (now - model.getCreationtime().getTime() > lifeTime) {
				modelService.remove(model);
			}
		}
	}

	/**
	 * 
	 * @return PayPal operation lifetime, in milliseconds
	 */
	private long getLifetime() {

		return Long.parseLong(paypalConfigManager
				.getProperty(PaypalConstants.PAYPAL_OPERATION_LIFETIME))
				* MULTIPLIER;
	}

	@Override
	@Autowired(required = true)
	public void setModelService(final ModelService modelService) {

		this.modelService = modelService;
	}

	@Override
	@Autowired(required = true)
	public void setFlexibleSearchService(
			final FlexibleSearchService flexibleSearchService) {

		this.flexibleSearchService = flexibleSearchService;
	}

	@Override
	@Autowired(required = true)
	public void setSessionService(final SessionService sessionService) {

		this.sessionService = sessionService;
	}

}