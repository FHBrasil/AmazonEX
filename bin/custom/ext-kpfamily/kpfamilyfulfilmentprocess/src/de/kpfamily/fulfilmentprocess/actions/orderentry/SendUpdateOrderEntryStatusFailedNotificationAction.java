/**
 *
 */
package de.kpfamily.fulfilmentprocess.actions.orderentry;

import de.hybris.platform.processengine.action.AbstractProceduralAction;
import de.hybris.platform.task.RetryLaterException;

import org.apache.log4j.Logger;

import de.kpfamily.core.model.process.OrderEntryStatusProcessModel;


/**
 * @author franthescollymaneira
 *
 */
public class SendUpdateOrderEntryStatusFailedNotificationAction extends AbstractProceduralAction<OrderEntryStatusProcessModel>
{
	private static final Logger LOG = Logger.getLogger(SendUpdateOrderEntryStatusFailedNotificationAction.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.processengine.action.AbstractProceduralAction#executeAction(de.hybris.platform.processengine
	 * .model.BusinessProcessModel)
	 */
	@Override
	public void executeAction(final OrderEntryStatusProcessModel process) throws RetryLaterException, Exception
	{
		LOG.info("Process: " + process.getCode() + " in step " + getClass().getSimpleName());
	}
}
