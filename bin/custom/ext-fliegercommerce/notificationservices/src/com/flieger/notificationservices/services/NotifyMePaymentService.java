/**
 * 
 */
package com.flieger.notificationservices.services;

import de.hybris.platform.core.model.order.OrderModel;

import java.util.Date;
import java.util.Set;

/**
 * Serviços de notificação de pagamento.
 * 
 * @author Vinicius de Souza
 *
 */
public interface NotifyMePaymentService
{	
	/**
	 * Busca todas as ordens a serem notificadas - pagamento ainda não efetuado.
	 * @param days Quantidade de dias para o boleto ou transferência vencer.
	 * @return Set de OrderModel.
	 * @throws Exception Lançada caso ocorra algum problema na execução.
	 */
	Set<OrderModel> findForDays(final int days) throws Exception;
	
	/**
	 * Busca as ordens a serem notificadas por periodo.
	 * @param from Data inicial do periodo de pesquisa.
	 * @param until Data final do periodo de pesquisa.
	 * @return Set de OrderModel.
	 * @throws Exception Lançada caso ocorra algum problema na execução.
	 */
	Set<OrderModel> findForPeriod(final Date from, final Date until) throws Exception;
	
	/**
	 * Pega a data de vencimento do boleto.
	 * @param order OrderModel do boleto.
	 * @return Data de vencimento.
	 * @throws Exception Lançada caso ocorra algum problema na execução.
	 */
	Date getExpirationDate(final OrderModel order) throws Exception;
}