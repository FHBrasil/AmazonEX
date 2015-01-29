/**
 *
 */
package de.kpfamily.fulfilmentprocess.actions.orderentry;

import de.hybris.platform.basecommerce.enums.OrderEntryStatus;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import de.hybris.platform.task.RetryLaterException;

import org.apache.log4j.Logger;

import de.kpfamily.core.model.process.OrderEntryStatusProcessModel;


/**
 * @author franthescollymaneira
 *
 */
public class UpdateOrderEntryStatusAction extends AbstractSimpleDecisionAction<OrderEntryStatusProcessModel>
{
	private static final Logger LOG = Logger.getLogger(UpdateOrderEntryStatusAction.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.processengine.action.AbstractSimpleDecisionAction#executeAction(de.hybris.platform.processengine
	 * .model.BusinessProcessModel)
	 */
	@Override
	public Transition executeAction(final OrderEntryStatusProcessModel process) throws RetryLaterException, Exception
	{
		LOG.info("Process: " + process.getCode() + " in step " + getClass().getSimpleName());

		final OrderEntryStatus oldStatus = process.getOldStatus();
		final OrderEntryStatus newStatus = process.getNewStatus();

		if (newStatus == null)
		{
			LOG.info("the new status should not be empty");
			return Transition.NOK;
		}

		if (newStatus.equals(oldStatus))
		{
			LOG.info("the status didn't change");
			return Transition.NOK;
		}

		final OrderEntryModel orderEntry = process.getOrderEntry();
		orderEntry.setStatus(newStatus);
		getModelService().save(orderEntry);

		return Transition.OK;
	}
}