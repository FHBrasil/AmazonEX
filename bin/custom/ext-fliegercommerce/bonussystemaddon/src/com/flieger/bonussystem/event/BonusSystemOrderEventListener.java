/**
 * 
 */
package com.flieger.bonussystem.event;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.orderprocessing.events.OrderProcessingEvent;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.flieger.bonussystem.facade.BonusSystemFacade;


/**
 * @author herbert
 * 
 */
public class BonusSystemOrderEventListener extends AbstractEventListener<OrderProcessingEvent>
{

	private static final Logger LOG = Logger.getLogger(BonusSystemOrderEventListener.class);

	private BonusSystemFacade bonusSystemFacade;

	@Override
	protected void onEvent(final OrderProcessingEvent event)
	{
		LOG.info("order event received - order: " + event.getOrderStatus());
		if (OrderStatus.PAYMENT_AUTHORIZED.equals(event.getOrderStatus()))
		{
			bonusSystemFacade.rewardPoints(event.getProcess().getOrder());
		}
	}

	protected BonusSystemFacade getBonusSystemFacade()
	{
		return bonusSystemFacade;
	}

	@Required
	public void setBonusSystemFacade(final BonusSystemFacade bonusSystemFacade)
	{
		this.bonusSystemFacade = bonusSystemFacade;
	}
}