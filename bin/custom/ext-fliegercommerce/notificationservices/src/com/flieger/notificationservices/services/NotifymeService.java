/**
 * 
 */
package com.flieger.notificationservices.services;

import java.util.Set;

import com.flieger.notificationservices.model.NotifymeModel;


/**
 * @author Vinicius de Souza
 * 
 */
public interface NotifymeService
{
	void notifyme(final NotifymeModel model) throws Exception;

	Set<NotifymeModel> findByEmail(final String email) throws Exception;

	Set<NotifymeModel> findByCode(final String code) throws Exception;

	void update(final NotifymeModel model) throws Exception;

	/**
	 * Busca as notificações.
	 * 
	 * @param notified
	 *           Parâmetro notificado.
	 * @return Lista de notificações.
	 */
	Set<NotifymeModel> getAll(boolean notified) throws Exception;
}