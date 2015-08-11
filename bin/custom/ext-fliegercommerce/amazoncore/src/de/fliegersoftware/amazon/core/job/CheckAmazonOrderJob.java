package de.fliegersoftware.amazon.core.job;

import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import org.apache.log4j.Logger;

public class CheckAmazonOrderJob extends AbstractJobPerformable<CronJobModel>
{
	private final static Logger LOG = Logger.getLogger( CheckAmazonOrderJob.class.getName() );

	@Override
	public PerformResult perform(CronJobModel cronJob) {
		LOG.info("Starting CheckAmazonOrderJob");
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
