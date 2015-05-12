/**
 * 
 */
package com.flieger.notificationservices.dao;

import de.hybris.platform.core.model.order.CartModel;

import java.util.Date;
import java.util.Set;

/**
 * @author Vinicius de Souza
 *
 */
public interface NotifyMeAbandonedCartDao
{
	/**
	 * Busca os Carrinhos Abandonados.
	 * @param from Data inicial da pesquisa.
	 * @param until Data final da pesquisa.
	 * @return Coleção de Carrinhos abandonados por período.
	 * @throws Exception É lançado caso ocorra um erro na execução.
	 */
	Set<CartModel> getCarts(final Date from, final Date until) throws Exception;
}