/**
 * 
 */
package br.flieger.exacttarget.job;

import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.util.Config;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.configuration.FileProvider;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import br.flieger.exacttarget.model.job.ExportNewsletterSubscriptionsJobModel;
import br.flieger.exacttarget.wsdl.Util;
import br.flieger.exacttarget.wsdl.api.APIObject;
import br.flieger.exacttarget.wsdl.api.Attribute;
import br.flieger.exacttarget.wsdl.api.CreateOptions;
import br.flieger.exacttarget.wsdl.api.CreateRequest;
import br.flieger.exacttarget.wsdl.api.CreateResponse;
import br.flieger.exacttarget.wsdl.api.CreateResult;
import br.flieger.exacttarget.wsdl.api.DeleteOptions;
import br.flieger.exacttarget.wsdl.api.DeleteRequest;
import br.flieger.exacttarget.wsdl.api.DeleteResponse;
import br.flieger.exacttarget.wsdl.api.DeleteResult;
import br.flieger.exacttarget.wsdl.api.PartnerAPI;
import br.flieger.exacttarget.wsdl.api.PartnerAPILocator;
import br.flieger.exacttarget.wsdl.api.Result;
import br.flieger.exacttarget.wsdl.api.Soap;
import br.flieger.exacttarget.wsdl.api.Subscriber;
import br.flieger.exacttarget.wsdl.api.SubscriberStatus;
import br.flieger.exacttarget.wsdl.api.TriggeredSend;
import br.flieger.exacttarget.wsdl.api.TriggeredSendDefinition;

import com.flieger.model.NewsletterSubscriberModel;


/**
 * CronJob para registro de emails junto a exacttarget.
 * 
 * @author Vinicius de Souza
 * 
 */
public class ExportNewsletterSubscriptionsJobPerformable extends AbstractJobPerformable<ExportNewsletterSubscriptionsJobModel>
{

	private static final Logger LOG = Logger.getLogger(ExportNewsletterSubscriptionsJobPerformable.class.getName());

	//@Resource
	//private NewsletterSubscriptionService newsletterSubscriptionService;

	/**
	 * Objeto comum aos processos da classe.
	 */
	private Soap stub = null;

	@Override
	public PerformResult perform(final ExportNewsletterSubscriptionsJobModel arg)
	{
		try
		{
			stub = Util.initSoap();
			LOG.info("Initializing register subscriber.");
			registerSubscriber();
			LOG.info("Initializing unregister subscriber.");
			unregisterSubscriber();
			LOG.info("Finished.");
		}
		catch (final Exception e)
		{
			LOG.error("Erro ao executar processos na exacttarget.", e);
			return new PerformResult(CronJobResult.ERROR, CronJobStatus.ABORTED);
		}
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	/**
	 * Registra a inscrição de newsletters junto a exacttarget.
	 * 
	 * @throws Exception
	 *            Lançado quando ocorre algum problema de acesso a API ou carregamento de configurações.
	 */
	private void registerSubscriber() throws Exception
	{
		//final List<NewsletterSubscriberModel> subs = newsletterSubscriptionService.getSubscribe(false, true);
		final List<NewsletterSubscriberModel> subs = createMoc();
				
		Subscriber subscriber = null;

		if (CollectionUtils.isNotEmpty(subs))
		{
			LOG.info("QTDE: " + subs.size());

			final Subscriber[] arraySubs = new Subscriber[subs.size()];
			int count = 0;
			
			final TriggeredSendDefinition tsd = new TriggeredSendDefinition();
			tsd.setCustomerKey("NEWLETTER_TRIGGER");

			for (final NewsletterSubscriberModel newsletterSubscriberModel : subs)
			{
				subscriber = new Subscriber();
				subscriber.setEmailAddress(newsletterSubscriberModel.getEmail());
				subscriber.setSubscriberKey(subscriber.getEmailAddress()); // unique identifier				

				//Incluir atributos
				Attribute p1 = new Attribute("EmailAddress", subscriber.getEmailAddress(), null);
				Attribute p2 = new Attribute("Name", subscriber.getEmailAddress(), null);
				Attribute p3 = new Attribute("EmailAddress", newsletterSubscriberModel.getGender().name(), null);
				
				subscriber.setAttributes(new Attribute[]{p1, p2, p3});

				
				arraySubs[count] = subscriber;
				count++;
			}
			
			final TriggeredSend ts = new TriggeredSend();
			ts.setSubscribers(arraySubs);
			ts.setTriggeredSendDefinition(tsd);
			
			CreateRequest createRequest = new CreateRequest();
			
			createRequest.setOptions(new CreateOptions());
			createRequest.setObjects(new APIObject[]{ts});
			
			LOG.info("Enviando apiObjects...");
			CreateResponse createResponse = stub.create(createRequest);

			LOG.info("Validando execução...");
			//Validando execução
			validateRegister(subs, createResponse.getResults());
		}
	}

	/**
	 * @return
	 */
	@Deprecated
	private List<NewsletterSubscriberModel> createMoc()
	{
		List<NewsletterSubscriberModel> list = new LinkedList<NewsletterSubscriberModel>();
		NewsletterSubscriberModel model = modelService.create(NewsletterSubscriberModel.class);
		model.setEmail("vinicius0386@gmail.com");
		model.setGender(Gender.MALE);
		
		list.add(model);
		
		return list;
	}

	/**
	 * Remove o registro de inscrição de newsletters junto a exacttarget.
	 * 
	 * @throws Exception
	 *            Lançado quando ocorre algum problema de acesso a API ou carregamento de configurações.
	 */
	private void unregisterSubscriber() throws Exception
	{
		final List<NewsletterSubscriberModel> subs = null;//newsletterSubscriptionService.getSubscribe(true, false);

		Subscriber subscriber = null;

		if (CollectionUtils.isNotEmpty(subs))
		{
			LOG.info("QTDE: " + subs.size());

			final APIObject[] apiObjects = new APIObject[subs.size()];
			int count = 0;

			for (final NewsletterSubscriberModel newsletterSubscriberModel : subs)
			{
				subscriber = new Subscriber();
				subscriber.setEmailAddress(newsletterSubscriberModel.getEmail());
				subscriber.setSubscriberKey(subscriber.getEmailAddress()); // unique identifier
				subscriber.setStatus(SubscriberStatus.Active);
				subscriber.setCorrelationID(subscriber.getEmailAddress());

				apiObjects[count] = subscriber;
				count++;
			}

			final DeleteRequest deleteRequest = new DeleteRequest(new DeleteOptions(), apiObjects);

			LOG.info("Enviando apiObjects...");
			final DeleteResponse deleteResponse = stub.delete(deleteRequest);

			LOG.info("Validando execução...");
			//Validando execução
			validateUnregister(subs, deleteResponse.getResults(), apiObjects);
		}
	}

	/**
	 * Valida os registros criados na ferramenta.
	 * 
	 * @param subs
	 *           Model que possui o e-mail a ser validado.
	 * @param createResults
	 *           Resultado da execução da API.
	 * @throws Exception
	 *            É lançada quando ocorre um erro na execução do serviço remoto.
	 */
	private void validateRegister(final List<NewsletterSubscriberModel> subs, final CreateResult[] createResults) throws Exception
	{
		Subscriber subscriber = null;

		for (final CreateResult result : createResults)
		{
			subscriber = (Subscriber) result.getObject();
			LOG.info("**************** Resultado :"+ result.getStatusCode() + "::" + result.getStatusMessage());

			for (final NewsletterSubscriberModel model : subs)
			{
				if (model.getEmail().equals(subscriber.getSubscriberKey()))
				{
					switch (result.getStatusCode())
					{
						case "Error":
							LOG.error(result.getStatusMessage());
							break;
						case "OK":
							model.setRegistered(true);
							break;
						default:
							LOG.error("Parâmetro de resultado não contemplado.");
							break;
					}
					//Salva a Model.
					//newsletterSubscriptionService.update(model);
					break;
				}
			}
		}
	}

	/**
	 * Valida os registros deletados na ferramenta.
	 * 
	 * @param subs
	 *           Lista das models a serem validadas.
	 * @param results
	 *           Lista de results a serem comparados.
	 * @param apiObjects
	 *           Objetos enviados.
	 * @throws Exception
	 *            É lançada quando ocorre um erro na execução do serviço remoto.
	 */
	private void validateUnregister(final List<NewsletterSubscriberModel> subs, final DeleteResult[] results,
			final APIObject[] apiObjects) throws Exception
	{
		Subscriber subscriber = null;
		DeleteResult result = null;
		LOG.info("QTDE Result: " + results.length);

		for (int i = 0; i < apiObjects.length; i++)
		{
			subscriber = (Subscriber) apiObjects[i];
			result = results[i];

			for (final NewsletterSubscriberModel model : subs)
			{
				if (model.getEmail().equals(subscriber.getSubscriberKey()))
				{
					switch (result.getStatusCode())
					{
						case "Error":
							LOG.error(result.getStatusMessage());
							break;
						case "OK":
							model.setRegistered(false);
							break;
						default:
							LOG.error("Parâmetro de resultado não contemplado.");
							break;
					}
					//Salva a Model.
					//newsletterSubscriptionService.update(model);
					break;
				}
			}
		}

	}
}