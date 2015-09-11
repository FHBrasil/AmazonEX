/**
 * 
 */
package com.flieger.notificationservices.dao;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;

import java.util.Set;

/**
 * @author Vinicius de Souza
 *
 */
public interface NotifyMePaymentDao
{
	Set<OrderModel> getOrders(OrderStatus status) throws Exception;
}