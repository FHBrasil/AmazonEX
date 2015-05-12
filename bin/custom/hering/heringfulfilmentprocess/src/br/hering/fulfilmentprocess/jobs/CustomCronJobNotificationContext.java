package br.hering.fulfilmentprocess.jobs;

import de.hybris.platform.cronjob.jalo.CronJob;
import de.hybris.platform.cronjob.jalo.DefaultCronJobNotificationTemplateContext;

public class CustomCronJobNotificationContext extends DefaultCronJobNotificationTemplateContext {

	public static final String LOG_TEXT = "logText";

	protected final CronJob job;

	public CustomCronJobNotificationContext(CronJob cronJob) {
		super(cronJob);
		this.job = cronJob;
	}

 	public String getLogText() {
		return (String)job.getSession().getAttribute(LOG_TEXT);
	}
}