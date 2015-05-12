/**
 * 
 */
package br.flieger.exacttarget.services;

import java.util.List;

import com.flieger.data.NewsletterSubscriberData;

/**
 * Responsável pela sincronização entre base x exact.
 * 
 * @author Vinicius de Souza
 *
 */
public interface ExacttargetSyncNewsletterService
{
	void syncNewsletter(List<NewsletterSubscriberData> listNewsletters) throws Exception;
}