/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.flieger.event.listener;

import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.store.BaseStoreModel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.flieger.data.NewNewsletterSubscriptionEvent;
import com.flieger.model.NewsletterSubscriberModel;


/**
 *
 * Listener to newsletter subscription
 *
 * @author Luiza
 *
 */
public class NewNewsletterSubscriptionListener extends AbstractEventListener<NewNewsletterSubscriptionEvent>
{

	static final private Logger LOG = Logger.getLogger(NewNewsletterSubscriptionListener.class);
	@Autowired
	ModelService modelService;

	@Override
	protected void onEvent(final NewNewsletterSubscriptionEvent event)
	{

		try
		{
			LOG.info("Entering event handler");

			final NewsletterSubscriberModel news = modelService.create(NewsletterSubscriberModel.class);
			final NewsletterSubscriberModel subscriber = event.getNewsletterSubscriber();
			final BaseStoreModel baseStore = event.getBaseStore();

			final String name = subscriber.getName();
			final String email = subscriber.getEmail();
			final Gender gender = subscriber.getGender();
			final Boolean receive = subscriber.getReceive();

			news.setName(name);
			news.setEmail(email);
			news.setGender(gender);
			news.setReceive(receive);
			news.setBaseStore(baseStore);

			modelService.save(news);
			LOG.info("Subscriber: " + name + " E-mail: " + email + " Gender: " + gender);
			LOG.info("Leaving event handler");
		}

		catch (final Exception e)
		{
			e.printStackTrace();
		}


	}


}
