/**
 * 
 */
package br.hering.core.order.dao;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.order.daos.OrderDao;

/**
 * 
 */
public interface HeringOrderDao extends OrderDao
{
	OrderStatus findOrderStatusByCode(final String code);
}
