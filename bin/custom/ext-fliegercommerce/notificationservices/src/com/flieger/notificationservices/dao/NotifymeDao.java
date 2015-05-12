/**
 * 
 */
package com.flieger.notificationservices.dao;

import de.hybris.platform.servicelayer.internal.dao.Dao;

import java.util.Set;

import com.flieger.notificationservices.model.NotifymeModel;


/**
 * @author Vinicius de Souza
 * 
 */
public interface NotifymeDao extends Dao
{

	/**
	 * @param email
	 *           E-mail a ser notificado.
	 * @return Retorna Set das notificação para este e-mail.
	 */
	Set<NotifymeModel> findByEmail(final String email) throws Exception;

	/**
	 * @param notified
	 * @return Retorna todas as notificações.
	 */
	Set<NotifymeModel> getAll(final boolean notified);

	/**
	 * @param model
	 * @throws Exception 
	 */
	void update(final NotifymeModel model) throws Exception;

	/**
	 * @param model
	 */
	void create(final NotifymeModel model) throws Exception;
}