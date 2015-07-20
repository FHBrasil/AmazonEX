package br.hering.fulfilmentprocess.actions.order;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import br.hering.fulfilmentprocess.services.impl.DefaultExportOrderService;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.orderhistory.model.OrderHistoryEntryModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.task.RetryLaterException;

public class OrderConfirmationAction extends AbstractOrderAction<OrderProcessModel>{
	
	
	@Resource
	private DefaultExportOrderService heringWSOrderService;
	

	public enum Transition
	{
		OK, NOK;

		public static Set<String> getStringValues()
		{
			final Set<String> res = new HashSet<String>();
			for (final Transition transitions : Transition.values())
			{
				res.add(transitions.toString());
			}
			return res;
		}
	}

	@Override
	public Set<String> getTransitions()
	{
		return Transition.getStringValues();
	}

	@Override
	public final String execute(final OrderProcessModel process) throws RetryLaterException, Exception
	{
		return executeAction(process).toString();
	}

	protected Transition executeAction(OrderProcessModel process)
	{
		
		ServicesUtil.validateParameterNotNull(process, "Process cannot be null");
		OrderModel order = process.getOrder();
//		heringWSOrderService.exportOrder(order);
		return Transition.OK;
	}
}
