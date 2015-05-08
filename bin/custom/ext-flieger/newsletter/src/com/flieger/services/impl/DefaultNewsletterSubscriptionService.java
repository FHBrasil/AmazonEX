/**
 *
 */
package com.flieger.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.impl.UniqueAttributesInterceptor;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.flieger.commerceservices.event.AbstractCommerceNewsletterSubscriberEvent;
import com.flieger.commerceservices.event.NewsletterSubscriptionEvent;
import com.flieger.dao.NewsletterDao;
import com.flieger.model.NewsletterSubscriberModel;
import com.flieger.services.NewsletterSubscriptionService;


/**
 * Implementação dos serviços.
 *
 * @author alexandresantos
 * @author Vinicius de Souza
 *
 */
public class DefaultNewsletterSubscriptionService implements NewsletterSubscriptionService
{
	protected static final Logger LOG = Logger.getLogger(DefaultNewsletterSubscriptionService.class);

	private ModelService modelService;

	private EventService eventService;

	private UserService userService;

	/**
	 * Acesso aos dados
	 */
	@Resource
	private NewsletterDao newsletterDao;

	@Override
	public void subscribe(final NewsletterSubscriberModel newsletterSubscriberModel, final boolean sendConfirmationToSubscriber)
			throws Exception
	{
		validateParameterNotNullStandardMessage("newsletterSubscriberModel", newsletterSubscriberModel);
		Assert.hasText(newsletterSubscriberModel.getEmail(), "The field [Email] cannot be empty");

		internalSaveNewsletterSubscriber(newsletterSubscriberModel);

		if (sendConfirmationToSubscriber)
		{
			eventService.publishEvent(initializeEvent(new NewsletterSubscriptionEvent(), newsletterSubscriberModel));
		}
	}

	@Override
	public List<NewsletterSubscriberModel> getAllSubscribe() throws Exception
	{
		final List<NewsletterSubscriberModel> result = newsletterDao.getAllSubscribe();
		return result;
	}

	@Override
	public List<NewsletterSubscriberModel> getSubscribe(final boolean registered, final boolean receive) throws Exception
	{
		//MOC
		//final List<NewsletterSubscriberModel> result = newsletterDao.getAllSubscribe();
		final List<NewsletterSubscriberModel> result = newsletterDao.getSubscribeByRegistered(registered, receive);
		return result;
	}

	@Override
	public void update(final List<NewsletterSubscriberModel> subs) throws Exception
	{
		if (CollectionUtils.isNotEmpty(subs))
		{
			for (final NewsletterSubscriberModel model : subs)
			{
				newsletterDao.update(model);
			}
		}

	}

	@Override
	public void update(final NewsletterSubscriberModel model) throws Exception
	{
		if (model != null)
		{
			newsletterDao.update(model);
		}

	}

	@Override
	public void delete(final NewsletterSubscriberModel model)
	{
		if (model != null)
		{
			newsletterDao.delete(model);
		}
	}

	@Override
	public NewsletterSubscriberModel findByEmail(final String email) throws Exception
	{
		final NewsletterSubscriberModel model = newsletterDao.findByEmail(email);
		return model;
	}

	protected void internalSaveNewsletterSubscriber(NewsletterSubscriberModel subscriberModel) throws Exception
	{
		try
		{
			modelService.save(subscriberModel);
		}
		catch (final ModelSavingException e)
		{
			if (e.getCause() instanceof InterceptorException
					&& ((InterceptorException) e.getCause()).getInterceptor().getClass().equals(UniqueAttributesInterceptor.class))
			{
				throw new Exception(subscriberModel.getEmail(), e);
			}
			else
			{
				throw e;
			}
		}
		catch (final AmbiguousIdentifierException e)
		{
			throw new Exception(subscriberModel.getEmail(), e);
		}
		finally
		{
			subscriberModel = getNewsletterSubscriber(subscriberModel.getEmail()); // get a valid NewsletterSubscriber (either the just created or already existing one)

			//			LOG.info("Checking customer account for id '" + subscriberModel.getEmail() + "'");
			try
			{
				final CustomerModel customerModel = userService.getUserForUID(subscriberModel.getEmail(), CustomerModel.class);
				customerModel.setNewsletterSubscription(subscriberModel);
				modelService.save(customerModel);
				//				LOG.info("Found customer and  successfully linked newslettersubscription");
			}
			catch (final UnknownIdentifierException uie)
			{
				//				LOG.info("No customer found for id '" + subscriberModel.getEmail() + "'");
			}
		}
	}

	protected AbstractCommerceNewsletterSubscriberEvent initializeEvent(final AbstractCommerceNewsletterSubscriberEvent event,
			final NewsletterSubscriberModel newsletterSubscriberModel)
	{
		//		event.setBaseStore(baseStoreSelectorStrategy.getCurrentBaseStore());
		//		event.setCmsSite(cmsSiteService.getCurrentSite());
		event.setNewsletterSubscriber(newsletterSubscriberModel);
		return event;
	}

	private NewsletterSubscriberModel getNewsletterSubscriber(final String email)
	{
		final NewsletterSubscriberModel nsExample = new NewsletterSubscriberModel();
		nsExample.setEmail(email);

		try
		{
			return modelService.getByExample(nsExample);
		}
		catch (final ModelNotFoundException m)
		{
			LOG.warn("No NewsletterSubscriberModel found for email '" + email + "'");
		}
		catch (final AmbiguousIdentifierException a)
		{
			LOG.warn("More than one NewsletterSubscriberModel found for email '" + email + "'");
		}

		return null;
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

	/**
	 * @return the eventService
	 */
	public EventService getEventService()
	{
		return eventService;
	}

	/**
	 * @param eventService
	 *           the eventService to set
	 */
	public void setEventService(final EventService eventService)
	{
		this.eventService = eventService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService
	 *           the userService to set
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * @return the newaletterDao
	 */
	public NewsletterDao getNewsletterDao()
	{
		return newsletterDao;
	}

	/**
	 * @param newaletterDao
	 *           the newaletterDao to set
	 */
	public void setNewsletterDao(final NewsletterDao newaletterDao)
	{
		this.newsletterDao = newaletterDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.flieger.services.NewsletterSubscriptionService#getNewsletterSubscriberForEmail(java.lang.String)
	 */
	@Override
	public List<NewsletterSubscriberModel> getNewsletterSubscriberForEmail(final String email)
	{
		return newsletterDao.getNewsletterSubscriberForEmail(email);
	}
}