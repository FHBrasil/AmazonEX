/**
 * 
 */
package com.flieger.dao;

import de.hybris.platform.servicelayer.internal.dao.Dao;

import java.util.List;

import com.flieger.model.NewsletterSubscriberModel;


/**
 * Interface de padronização para acesso DAO.
 * 
 * @author Vinicius de Souza
 * 
 */
public interface NewsletterDao extends Dao
{

	/**
	 * Lista todos os subscribers.
	 * 
	 * @return Retorna um List com todos os subscribers.
	 */
	List<NewsletterSubscriberModel> getAllSubscribe();

	/**
	 * Lista os subscribers a serem manipulados.
	 * 
	 * @param registered
	 *           <code>true</code> para subscribers já registrados.
	 * @param receive
	 *           <code>true</code> para subscribers que concordaram no recebimento de e-mail.
	 * @return Retorna <code>List</code> com os subscribers gravados na base de dados.
	 */
	List<NewsletterSubscriberModel> getSubscribeByRegistered(final boolean registered, final boolean receive);

	/**
	 * Atualiza os subscribers na base de dados.
	 * 
	 * @param model
	 *           Subscriber a ser atualizado.
	 */
	void update(final NewsletterSubscriberModel model);

	/**
	 * Delete uma model do banco.
	 * 
	 * @param model
	 *           Model newsletter a ser deletada.
	 */
	void delete(final NewsletterSubscriberModel model);

	/**
	 * Efetua consulta na base passando o endereço de e-mail como cláusula.
	 * 
	 * @param email
	 *           E-mail da cláusula.
	 * @return Retorna o objeto caso encontrado.
	 */
	NewsletterSubscriberModel findByEmail(final String email);

	/**
	 * @param email
	 *           Email do Registro.
	 * @return
	 */
	List<NewsletterSubscriberModel> getNewsletterSubscriberForEmail(final String email);
}