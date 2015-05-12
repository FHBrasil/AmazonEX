/**
 * 
 */
package com.flieger.notificationservices.job;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.event.EventService;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import br.flieger.exacttarget.events.NotifyMePaymentEvent;

import com.flieger.notificationservices.model.job.NotifyMePaymentJobModel;
import com.flieger.notificationservices.services.NotifyMePaymentService;

/**
 * @author Vinicius de Souza
 *
 */
public class NotifyMePaymentJobPerformable extends AbstractJobPerformable<NotifyMePaymentJobModel>
{
	private int days;
	private boolean isContinuousNotifications;
	private String siteUrl;
	
	@Resource
	private NotifyMePaymentService notifyMePaymentService;
	
	@Resource
	private EventService eventService;
	
	private final Logger LOG = Logger.getLogger(NotifyMePaymentJobPerformable.class.getName());
	
	@Override
	public PerformResult perform(NotifyMePaymentJobModel jobModel)
	{
		LOG.info("Started process...");
		//Carrega qtde dias;
		days = getDays(jobModel);
		//Carrega notificação continua;
		isContinuousNotifications = getContinuousNotifications(jobModel);
		siteUrl = getSiteUrl(jobModel);
		
		
		if(days <= 0)
		{
			LOG.error("Illegal Parameter 'days'="+days);
			return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
		}
		
		try 
		{
			execute();
		} 
		catch(Exception e)
		{
			LOG.error("Error during execution.", e);
			return new PerformResult(CronJobResult.ERROR, CronJobStatus.FINISHED);
		}
		
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	/**
	 * @throws Exception 
	 * 
	 */
	private void execute() throws Exception
	{
		//Carregando OrderModel
		Set<OrderModel> orders = notifyMePaymentService.findForDays(days);
		
		if(orders != null && orders.size() > 0)
		{
			final Map<String, Set<OrderModel>> mapOrders = new HashMap<>();
			
			for (OrderModel orderModel : orders)
			{
				String baseStore = orderModel.getStore().getUid();
				if(!mapOrders.keySet().contains(baseStore))
				{
					final Set<OrderModel> setOrders = new HashSet<>();
					mapOrders.put(baseStore, setOrders);
				}
				
				mapOrders.get(baseStore).add(orderModel);
			}
			
			for (String baseStore : mapOrders.keySet())
			{
				final NotifyMePaymentEvent event = new NotifyMePaymentEvent(mapOrders.get(baseStore));
				event.setBaseStore(baseStore);
	   		eventService.publishEvent(event);
			}
		}
	}
	
	private String getSiteUrl(CronJobModel cronjob)
	{
		if(cronjob instanceof NotifyMePaymentJobModel)
		{
			return ((NotifyMePaymentJobModel) cronjob).getSiteUrl();
		}
		return null;
	}

	/**
	 * @param jobModel
	 * @return
	 */
	private boolean getContinuousNotifications(CronJobModel cronjob)
	{
		if(cronjob instanceof NotifyMePaymentJobModel)
		{
			return ((NotifyMePaymentJobModel) cronjob).isContinuousNotifications();
		}
		return false;
	}

	/**
	 * @return
	 */
	private int getDays(CronJobModel cronjob)
	{
		if(cronjob instanceof NotifyMePaymentJobModel)
		{
			return ((NotifyMePaymentJobModel) cronjob).getDays();
		}
		return -1;
	}
}
