/**
 * 
 */
package com.flieger.notificationservices.services;

import de.hybris.platform.core.model.order.CartModel;

import java.util.Date;
import java.util.Set;

/**
 * @author Vinicius de Souza
 *
 */
public interface NotifyMeAbandonedCartService
{
	Set<CartModel> getAbandonedCarts(final int days) throws Exception;
	
	Set<CartModel> getAbandonedCarts(final Date from, final Date until) throws Exception;
}