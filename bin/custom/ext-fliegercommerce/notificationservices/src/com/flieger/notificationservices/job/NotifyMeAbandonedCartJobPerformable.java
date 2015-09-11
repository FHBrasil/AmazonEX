/**
 * 
 */
package com.flieger.notificationservices.job;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.event.EventService;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import br.flieger.exacttarget.events.NotifyMeAbandonedCartEvent;

import com.flieger.notificationservices.model.job.NotifyMeAbandonedCartJobModel;
import com.flieger.notificationservices.services.NotifyMeAbandonedCartService;

/**
 * @author Vinicius de Souza
 *
 */
public class NotifyMeAbandonedCartJobPerformable extends AbstractJobPerformable<NotifyMeAbandonedCartJobModel>
{
	@Resource
	private NotifyMeAbandonedCartService notifyMeAbandonedCartService;
	
	@Resource
	private EventService eventService;
	
	private int days;
	
	private final Logger LOG = Logger.getLogger(NotifyMeAbandonedCartJobPerformable.class.getName());

	@Override
	public PerformResult perform(NotifyMeAbandonedCartJobModel jobModel)
	{
		final long startTime = System.currentTimeMillis();
		try
		{
			//Carrega qtde dias;
			days = getDays(jobModel);

			if(days <= 0)
			{
				LOG.error("Illegal Parameter 'days'="+days);
				return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
			}
			
			final Set<CartModel> carts = new HashSet<>();
			final Set<String> emails = new HashSet<>();
			
			for (CartModel cartModel : notifyMeAbandonedCartService.getAbandonedCarts(days))
			{
				final String email = cartModel.getUser().getUid();
				if(email.compareTo("anonymous") != 0 && !emails.contains(email))
				{
					carts.add(cartModel);
					emails.add(email);
				}
			}
			
			final NotifyMeAbandonedCartEvent event = new NotifyMeAbandonedCartEvent(carts);
			eventService.publishEvent(event);
		}
		catch (Exception e)
		{
			LOG.error("Error during execution.", e);
			return new PerformResult(CronJobResult.ERROR, CronJobStatus.FINISHED);
		} finally
		{
			LOG.info("Cronjob has finished in "+(System.currentTimeMillis()-startTime)+"ms");
		}

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	/**
	 * @param jobModel
	 * @return Parâmetro de dias da Cronjob.
	 */
	private int getDays(CronJobModel jobModel)
	{
		if(jobModel instanceof NotifyMeAbandonedCartJobModel)
		{
			return ((NotifyMeAbandonedCartJobModel) jobModel).getDays();
		}
		return -1;
	}
}