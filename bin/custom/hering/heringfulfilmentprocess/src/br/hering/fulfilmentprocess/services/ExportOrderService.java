/**
 * 
 */
package br.hering.fulfilmentprocess.services;

import de.hybris.platform.core.model.order.OrderModel;

/**
 * @author ghayashi
 * @author ezequiel
 */
public interface ExportOrderService
{
	/**
	 * @param order
	 */
	void exportStatus(OrderModel order);
	
	
	/**
	 * @param order
	 * @return boolean
	 */
	boolean exportOrder(final OrderModel order);

}
