/**
 *
 */
package de.kpfamily.fulfilmentprocess.actions.orderentry;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import de.hybris.platform.task.RetryLaterException;

import org.apache.log4j.Logger;

import de.kpfamily.core.model.process.OrderEntryStatusProcessModel;


/**
 * @author franthescollymaneira
 *
 */
public class UpdateOrderStatusAction extends AbstractSimpleDecisionAction<OrderEntryStatusProcessModel>
{
	private static final Logger LOG = Logger.getLogger(UpdateOrderStatusAction.class);

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

		//TODO chamar service que atualiza status do pedido geral
		try
		{
			final OrderModel order = process.getOrderEntry().getOrder();
			LOG.info("FAKE - updating order: " + order.getCode());
			return Transition.OK;
		}
		catch (final Exception e)
		{
			LOG.error(e);
			return Transition.NOK;
		}

	}
}
