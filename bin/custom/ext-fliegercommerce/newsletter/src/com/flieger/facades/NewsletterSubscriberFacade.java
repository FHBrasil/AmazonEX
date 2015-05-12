/**
 * 
 */
package com.flieger.facades;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;

import java.util.Date;
import java.util.List;

import com.flieger.data.NewsletterSubscriberData;


/**
 * @author alexandresantos
 * @author Vinicius de Souza
 * 
 */
public interface NewsletterSubscriberFacade
{
	void subscribeNewsletter(final NewsletterSubscriberData subscriberData) throws DuplicateUidException;

	void subscribeNewsletter(final NewsletterSubscriberData subscriberData, final boolean sendConfirmationToSubscriber,
			final Date subscriptionTime) throws DuplicateUidException;

	/**
	 * Atualiza registro em base.
	 * 
	 * @param subscriberData
	 *           DataModel a ser atualizado.
	 * @throws Exception
	 *            É lançanda quando ocorre um erro de execução.
	 */
	@Deprecated
	void update(final NewsletterSubscriberData subscriberData) throws Exception;

	void update(final NewsletterSubscriberData subscriberData, List<String> listBaseStore) throws Exception;

	/**
	 * Deleta um registro em base.
	 * 
	 * @param subscriberData
	 *           a ser deletado.
	 * @throws Exception
	 *            É lançanda quando ocorre um erro de execução.
	 */
	void delete(final NewsletterSubscriberData subscriberData) throws Exception;

	/**
	 * Busca a <code>NewsletterSubscriberData</code> caso o e-mail ja tenha sido cadastrado.
	 * 
	 * @param email
	 *           Endereço de e-mail pesquisado.
	 * @return Retorna objeto <code>NewsletterSubscriberData</code> caso exista.
	 * @throws Exception
	 *            É lançanda quando ocorre um erro de execução.
	 */
	NewsletterSubscriberData findByEmail(String email) throws Exception;

	/**
	 * @param newsletterSubscriber
	 * @param listBaseStore
	 * @throws DuplicateUidException
	 */
	void subscribeNewsletter(NewsletterSubscriberData newsletterSubscriber, List<String> listBaseStore)
			throws DuplicateUidException;

	/**
	 * Busca as Newsletters de um Customer.
	 * 
	 * @param customerData
	 *           Usuário do Sistema
	 * @return Lista de Newsletters.
	 */
	List<NewsletterSubscriberData> getNewsletterSubscriberForCustomer(CustomerData customerData);

	/**
	 * Efetua a troca dos e-mails nas newsletters cadastradas.
	 * 
	 * @param oldEmail
	 *           Email antigo.
	 * @param newEmail
	 *           Email novo.
	 * @throws Exception
	 */
	void changeEmail(final String oldEmail, final String newEmail) throws Exception;
}
