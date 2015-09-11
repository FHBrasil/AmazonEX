/**
 * 
 */
package com.flieger.notificationservices.facades;

import com.flieger.notificationservices.data.NotifymeData;


/**
 * @author Vinicius de Souza
 * 
 */
public interface NotifymeFacade
{
	/**
	 * Registra o notifyme.
	 * 
	 * @param data
	 *           NotifymeData da controller.
	 * @throws Exception
	 */
	void notifyme(final NotifymeData data) throws Exception;

	/**
	 * Busca uma notifyme ja cadastrada.
	 * 
	 * @param email
	 *           Endere de e-mail cadastrado para notificação.
	 * @param code
	 *           Código SKU do produto.
	 * @return Retorna objeto data cadastrado.
	 * @throws Exception
	 */
	NotifymeData find(final String email, final String code) throws Exception;
}