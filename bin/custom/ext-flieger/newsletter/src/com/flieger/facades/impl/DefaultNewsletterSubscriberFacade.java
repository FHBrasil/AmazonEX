/**
 *
 */
package com.flieger.facades.impl;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.flieger.commerceservices.event.NewsletterSubscriptionEvent;
import com.flieger.data.NewNewsletterSubscriptionEvent;
import com.flieger.data.NewsletterSubscriberData;
import com.flieger.facades.NewsletterSubscriberFacade;
import com.flieger.facades.populators.NewsletterSubscriberPopulator;
import com.flieger.facades.populators.NewsletterSubscriberReversePopulator;
import com.flieger.model.NewsletterSubscriberModel;
import com.flieger.services.NewsletterSubscriptionService;


/**
 * @author alexandresantos
 * @author Vinicius de Souza
 * @author Luiza
 */
public class DefaultNewsletterSubscriberFacade implements NewsletterSubscriberFacade
{
	protected static final Logger LOG = Logger.getLogger(DefaultNewsletterSubscriberFacade.class);

	private NewsletterSubscriptionService newsletterSubscriptionService;

	private ModelService modelService;

	@Resource
	private NewsletterSubscriberPopulator newsletterSubscriberPopulator;
	private NewsletterSubscriberReversePopulator newsletterSubscriberReversePopulator;

	@Resource
	private EventService eventService;

	@Resource
	private BaseStoreService baseStoreService;

	@Resource(name = "newsletterSubscriberReverseConverter")
	private Converter<NewsletterSubscriberData, NewsletterSubscriberModel> newsletterSubscriberReverseConverter;


	@Override
	public void subscribeNewsletter(final NewsletterSubscriberData subscriberData) throws DuplicateUidException
	{
		LOG.info("requisicao chegou na facade");
		if (subscriberData.getBaseStore() != null)
		{
			final NewsletterSubscriberModel model = new NewsletterSubscriberModel();
			newsletterSubscriberReverseConverter.convert(subscriberData, model);
			modelService.save(model);

			//Populates the Event
			final NewNewsletterSubscriptionEvent subscriptionEvent = new NewNewsletterSubscriptionEvent();
			subscriptionEvent.setNewsletterSubscriber(model);
			subscriptionEvent.setBaseStore(model.getBaseStore());

			try
			{
				LOG.info("subscription event");
				eventService.publishEvent(subscriptionEvent);
			}
			catch (final Exception e)
			{
				e.printStackTrace();
			}

			return;
		}

	}

	@Override
	public void update(final NewsletterSubscriberData subscriberData) throws Exception
	{
		final NewsletterSubscriberModel model = newsletterSubscriptionService.findByEmail(subscriberData.getEmail());
		if (model != null)
		{
			//It is necessary for old register without baseStore.
			if (StringUtils.isBlank(subscriberData.getBaseStore()))
			{
				subscriberData.setBaseStore("dzarm");
			}

			model.setName(subscriberData.getName());
			model.setReceive(subscriberData.getReceive());
			model.setGender(subscriberData.getGender());
			model.setBaseStore(baseStoreService.getBaseStoreForUid(subscriberData.getBaseStore()));

			final NewsletterSubscriptionEvent event = new NewsletterSubscriptionEvent();

			eventService.publishEvent(event);
			if (true)//TODO implementar retorno do event para identificar sucesso ou falha
			{
				newsletterSubscriptionService.update(model);
			}
		}
		else
		{
			subscribeNewsletter(subscriberData);
		}
	}

	@Override
	public void delete(final NewsletterSubscriberData subscriberData) throws Exception
	{
		final NewsletterSubscriberModel model = newsletterSubscriptionService.findByEmail(subscriberData.getEmail());

		if (model != null)
		{
			subscriberData.setReceive(Boolean.FALSE);
			final NewsletterSubscriptionEvent event = new NewsletterSubscriptionEvent();

			eventService.publishEvent(event);
			if (true)//TODO implementar retorno do event para identificar sucesso ou falha
			{
				LOG.info("Deleting " + model);
				newsletterSubscriptionService.delete(model);
			}
		}
	}

	@Override
	public NewsletterSubscriberData findByEmail(final String email) throws Exception
	{
		NewsletterSubscriberData data = null;
		NewsletterSubscriberModel model = null;

		if (email != null)
		{
			model = newsletterSubscriptionService.findByEmail(email);

			if (model != null)
			{
				data = new NewsletterSubscriberData();
				data.setName(model.getName());
				data.setEmail(email);
				data.setGender(model.getGender());
				data.setReceive(model.getReceive());
			}
		}
		return data;
	}

	/**
	 * @return the newsletterSubscriptionService
	 */
	public NewsletterSubscriptionService getNewsletterSubscriptionService()
	{
		return newsletterSubscriptionService;
	}

	/**
	 * @param newsletterSubscriptionService
	 *           the newsletterSubscriptionService to set
	 */
	public void setNewsletterSubscriptionService(final NewsletterSubscriptionService newsletterSubscriptionService)
	{
		this.newsletterSubscriptionService = newsletterSubscriptionService;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flieger.facades.NewsletterSubscriberFacade#subscribeNewsletter(com.flieger.data.NewsletterSubscriberData,
	 * java.util.List)
	 */
	@Override
	public void subscribeNewsletter(final NewsletterSubscriberData newsletterSubscriber, final List<String> listBaseStore)
			throws DuplicateUidException
	{
		Assert.notNull(listBaseStore, "listBaseStore");

		for (final String baseStore : listBaseStore)
		{
			newsletterSubscriber.setBaseStore(baseStore);
			subscribeNewsletter(newsletterSubscriber);
		}
	}

	@Override
	public List<NewsletterSubscriberData> getNewsletterSubscriberForCustomer(final CustomerData customerData)
	{
		final List<NewsletterSubscriberData> result = new LinkedList<NewsletterSubscriberData>();

		final List<NewsletterSubscriberModel> models = newsletterSubscriptionService.getNewsletterSubscriberForEmail(customerData
				.getUid());

		for (final NewsletterSubscriberModel newsletterSubscriberModel : models)
		{
			final NewsletterSubscriberData data = new NewsletterSubscriberData();
			newsletterSubscriberPopulator.populate(newsletterSubscriberModel, data);
			result.add(data);
		}

		return result;
	}

	@Override
	public void update(final NewsletterSubscriberData subscriberData, final List<String> listBaseStore) throws Exception
	{
		List<NewsletterSubscriberModel> models = newsletterSubscriptionService.getNewsletterSubscriberForEmail(subscriberData
				.getEmail());

		for (final String baseStore : listBaseStore)
		{
			boolean exists = false;

			for (final NewsletterSubscriberModel model : models)
			{
				if (model.getBaseStore() == null)
				{
					model.setBaseStore(baseStoreService.getCurrentBaseStore());
				}
				if (baseStore.equals(model.getBaseStore().getUid()))
				{
					exists = true;
					if (!model.getReceive().booleanValue())
					{
						model.setReceive(Boolean.TRUE);

						subscriberData.setBaseStore(baseStore);
						newsletterSubscriberPopulator.populate(model, subscriberData);
						final NewsletterSubscriptionEvent event = new NewsletterSubscriptionEvent();

						eventService.publishEvent(event);
						if (true)//TODO implementar retorno do event para identificar sucesso ou falha
						{
							modelService.save(model);
							newsletterSubscriptionService.update(model);
						}

						break;
					}
				}
			}

			if (!exists)
			{
				subscriberData.setBaseStore(baseStore);
				subscribeNewsletter(subscriberData);
			}
		}

		models = newsletterSubscriptionService.getNewsletterSubscriberForEmail(subscriberData.getEmail());
		LOG.info("Verificar remove de news: " + models.size() + " -- " + listBaseStore.toString());
		for (final NewsletterSubscriberModel model : models)
		{
			LOG.info("##" + model.getEmail() + "/" + model.getBaseStore() + "/" + model.getReceive() + "/" + model.getRegistered());
			if (model.getReceive().booleanValue()
					&& (model.getBaseStore() == null || !listBaseStore.contains(model.getBaseStore().getUid())))
			{
				LOG.info("Remover email '" + model.getEmail() + "'");
				model.setReceive(Boolean.FALSE);

				newsletterSubscriberPopulator.populate(model, subscriberData);

				final NewsletterSubscriptionEvent event = new NewsletterSubscriptionEvent();

				eventService.publishEvent(event);

				if (true || model.getBaseStore() == null)
				{
					modelService.remove(model);
				}
				else
				{
					modelService.save(model);
				}
			}
		}
	}

	@Override
	public void changeEmail(final String oldEmail, final String newEmail) throws Exception
	{
		final List<NewsletterSubscriberModel> listOldModel = newsletterSubscriptionService
				.getNewsletterSubscriberForEmail(oldEmail);

		final List<NewsletterSubscriberModel> listNewModel = newsletterSubscriptionService
				.getNewsletterSubscriberForEmail(newEmail);

		NewsletterSubscriberData newsletterData = null;

		o: for (final NewsletterSubscriberModel oldModel : listOldModel)
		{
			for (final NewsletterSubscriberModel newModel : listNewModel)
			{
				if (oldModel.getBaseStore().getUid().equals(newModel.getBaseStore().getUid()))
				{
					newsletterData = new NewsletterSubscriberData();
					newsletterSubscriberPopulator.populate(newModel, newsletterData);
					newsletterData.setReceive(Boolean.FALSE);

					if (shootEvent(newsletterData))
					{
						modelService.remove(newModel);
					}
				}
			}

			newsletterData = new NewsletterSubscriberData();
			newsletterSubscriberPopulator.populate(oldModel, newsletterData);
			newsletterData.setReceive(Boolean.FALSE);

			shootEvent(newsletterData);
			oldModel.setEmail(newEmail);

			if (oldModel.getReceive().booleanValue())
			{
				newsletterData = new NewsletterSubscriberData();
				newsletterSubscriberPopulator.populate(oldModel, newsletterData);
				newsletterData.setReceive(Boolean.TRUE);

				oldModel.setRegistered(new Boolean(shootEvent(newsletterData)));
			}

			modelService.save(oldModel);
		}
	}

	/**
	 * @param subscriberData
	 */
	private boolean shootEvent(final NewsletterSubscriberData subscriberData)
	{
		final NewsletterSubscriptionEvent event = new NewsletterSubscriptionEvent();

		eventService.publishEvent(event);

		if (!true)//TODO implementar retorno do event para identificar sucesso ou falha
		{
			//	LOG.info("RESULTADO NEWS::" + event.isResultOk() + " " + event.getMsgRequest());
		}

		return true;//TODO implementar retorno do event para identificar sucesso ou falha
	}
}
