package de.kpfamily.services.daos.product;

import java.util.List;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.internal.dao.Dao;

/**
 * 
 * @author franthescollymaneira
 *
 */
public interface SerialVoucherProductDao extends Dao 
{
	/**
	 * 
	 * @param order
	 * @return
	 */
	List<String> findSoldSerialVoucherCodesByOrder(OrderModel order);
}