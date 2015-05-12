package br.hering.fulfilmentprocess.jobs;

import de.hybris.platform.cronjob.jalo.CronJobNotificationTemplateContext;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.ComposedType;

import org.apache.log4j.Logger;

public class ExportOrdersJob extends GeneratedExportOrdersJob
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger( ExportOrdersJob.class.getName() );
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		// business code placed here will be executed before the item is created
		// then create the item
		final Item item = super.createItem( ctx, type, allAttributes );
		// business code placed here will be executed after the item was created
		// and return the item
		return item;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isSendEmailAsPrimitive() {
		return super.isSendEmailAsPrimitive() && !de.hybris.platform.cronjob.enums.CronJobResult.SUCCESS.getCode().equals(super.getResult().getCode());
	}

	@Override
	protected CronJobNotificationTemplateContext getRendererNotificationContext() {
		return new CustomCronJobNotificationContext(this);
	}
}