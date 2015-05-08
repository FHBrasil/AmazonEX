/**
 *
 */
package com.flieger.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.store.services.BaseStoreService;

import javax.annotation.Resource;

import com.flieger.data.NewsletterSubscriberData;
import com.flieger.model.NewsletterSubscriberModel;


/**
 * @author Vinicius de Souza
 *
 */
public class NewsletterSubscriberPopulator implements Populator<NewsletterSubscriberModel, NewsletterSubscriberData>
{
	@Resource
	private BaseStoreService baseStoreService;

	@Override
	public void populate(final NewsletterSubscriberModel source, final NewsletterSubscriberData target) throws ConversionException
	{
		target.setName(source.getName());
		target.setEmail(source.getEmail());
		target.setGender(source.getGender());
		target.setReceive(source.getReceive());

		final String baseStoreId = source.getBaseStore() == null ? baseStoreService.getCurrentBaseStore().getUid() : source
				.getBaseStore().getUid();
		target.setBaseStore(baseStoreId);
	}
}
