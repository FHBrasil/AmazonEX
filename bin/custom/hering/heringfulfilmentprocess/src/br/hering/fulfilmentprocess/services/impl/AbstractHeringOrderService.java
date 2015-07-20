/**
 * 
 */
package br.hering.fulfilmentprocess.services.impl;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;

import br.hering.fulfilmentprocess.services.ExportOrderService;

/**
 * @author ghayashi
 * @author ezequiel
 *
 */
public abstract class AbstractHeringOrderService extends AbstractBusinessService implements ExportOrderService
{

	/* (non-Javadoc)
	 * @see br.hering.webservices.services.ExportOrderService#exportStatus(de.hybris.platform.core.model.order.OrderModel)
	 */
	@Override
	public void exportStatus(OrderModel order)
	{
		// YTODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see br.hering.webservices.services.ExportOrderService#exportOrder(de.hybris.platform.core.model.order.OrderModel)
	 */
	@Override
	public boolean exportOrder(OrderModel order)
	{
		return false;
	}
}
