/**
 * 
 */
package com.flieger.newsletter.job;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import br.flieger.exacttarget.services.ExacttargetSyncNewsletterService;

import com.flieger.data.NewsletterSubscriberData;
import com.flieger.model.NewsletterSubscriberModel;
import com.flieger.model.newsletter.job.SyncNewsletterJobModel;
import com.flieger.services.NewsletterSubscriptionService;


/**
 * @author Vinicius de Souza
 * 
 */
public class SyncNewsletterJobPerformable extends AbstractJobPerformable<SyncNewsletterJobModel>
{
	@Resource
	private ExacttargetSyncNewsletterService exacttargetSyncNewsletterService;

	@Resource
	private NewsletterSubscriptionService newsletterSubscriptionService;

	private static final Logger LOG = Logger.getLogger(SyncNewsletterJobPerformable.class.getName());

	@Override
	public PerformResult perform(final SyncNewsletterJobModel arg0)
	{
		LOG.info("CHAMOU CRONJOB");
		try
		{
			List<NewsletterSubscriberModel> list = null;

			list = newsletterSubscriptionService.getSubscribe(true, true);
			/*
			 * list = new ArrayList<NewsletterSubscriberModel>(); final NewsletterSubscriberModel moc = new
			 * NewsletterSubscriberModel(); moc.setName("Teste Cron"); moc.setEmail("testecron@teste.com");
			 * moc.setGender(Gender.MALE); list.add(moc);
			 */


			final List<NewsletterSubscriberData> listData = new LinkedList<NewsletterSubscriberData>();
			for (final NewsletterSubscriberModel model : list)
			{
				final NewsletterSubscriberData data = new NewsletterSubscriberData();
				data.setName(model.getName());
				data.setEmail(model.getEmail());
				data.setGender(model.getGender());

				listData.add(data);
			}

			exacttargetSyncNewsletterService.syncNewsletter(listData);
		}
		catch (final Exception e)
		{
			LOG.error(e);
			return new PerformResult(CronJobResult.ERROR, CronJobStatus.FINISHED);
		}
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}
}