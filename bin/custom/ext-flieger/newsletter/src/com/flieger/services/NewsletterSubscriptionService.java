/**
 *
 */
package com.flieger.services;

import java.util.List;
import com.flieger.model.NewsletterSubscriberModel;


/**
 * Interface de serviços de Newsletters.
 *
 * @author alexandresantos
 * @author Vinicius de Souza
 *
 */
public interface NewsletterSubscriptionService
{
	public void subscribe(final NewsletterSubscriberModel newsletterSubscriberModel, final boolean sendConfirmationToSubscriber)
			throws Exception;

	/**
	 * Serviço que busca todos os subscribes.
	 *
	 * @return Retorna <code>List</code> com os subscribes.
	 * @throws Exception
	 *            Ocorre há um erro de execução do serviço.
	 */
	public List<NewsletterSubscriberModel> getAllSubscribe() throws Exception;

	/**
	 * Serviço que busca os subscribes por parâmetro.
	 *
	 * @param registered
	 *           Flag que define se os subscribes devem ser os já registrados ou não.
	 * @param receive
	 *           Flag que define se os subscribes devem ser os permitiram o envio de e-mails ou não.
	 * @return Retorna <code>List</code> com os subscribes.
	 * @throws Exception
	 *            Ocorre há um erro de execução do serviço.
	 */
	public List<NewsletterSubscriberModel> getSubscribe(final boolean registered, final boolean receive) throws Exception;

	/**
	 * Atualiza os dados de uma lista de Models.
	 *
	 * @param subs
	 *           <code>List</code> com as Models a serem atualizadas.
	 * @throws Exception
	 *            Ocorre quando há um erro de execução do serviço.
	 */
	public void update(List<NewsletterSubscriberModel> subs) throws Exception;

	/**
	 * Atualiza os dados de uma Model.
	 *
	 * @param model
	 *           Model a ser atualizada.
	 * @throws Exception
	 *            Ocorre quando há um erro de execução do serviço.
	 */
	public void update(NewsletterSubscriberModel model) throws Exception;

	/**
	 * Deleta o registro de uma model.
	 *
	 * @param model
	 *           Model a ser deletada.
	 * @throws Exception
	 *            Ocorre quando há um erro de execução do serviço.
	 */
	public void delete(NewsletterSubscriberModel model);

	/**
	 * Busca a Model por email.
	 *
	 * @param email
	 *           Endereço de e-mail da model.
	 * @return Retorna o objeto caso exista.
	 * @throws Exception
	 *            Ocorre quando há um erro de execução do serviço.
	 */
	public NewsletterSubscriberModel findByEmail(final String email) throws Exception;

	/**
	 * @param email
	 *           Endeeço de e-mail da Newsletter.
	 * @return
	 */
	public List<NewsletterSubscriberModel> getNewsletterSubscriberForEmail(String email);
}
