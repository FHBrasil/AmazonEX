package com.flieger.commerceservices.event;

import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;
import de.hybris.platform.store.BaseStoreModel;

import com.flieger.model.NewsletterSubscriberModel;


public abstract class AbstractCommerceNewsletterSubscriberEvent extends AbstractEvent
{
	private BaseStoreModel baseStore;

	private CMSSiteModel cmsSite;

	private NewsletterSubscriberModel newsletterSubscriber;

	public BaseStoreModel getBaseStore()
	{
		return baseStore;
	}

	public void setBaseStore(final BaseStoreModel baseStore)
	{
		this.baseStore = baseStore;
	}

	public CMSSiteModel getCmsSite()
	{
		return cmsSite;
	}

	public void setCmsSite(final CMSSiteModel cmsSite)
	{
		this.cmsSite = cmsSite;
	}

	public NewsletterSubscriberModel getNewsletterSubscriber()
	{
		return newsletterSubscriber;
	}

	public void setNewsletterSubscriber(final NewsletterSubscriberModel newsletterSubscriber)
	{
		this.newsletterSubscriber = newsletterSubscriber;
	}

}
