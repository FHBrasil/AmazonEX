/**
 *
 */
package com.flieger.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import javax.annotation.Resource;

import com.flieger.data.NewsletterSubscriberData;
import com.flieger.model.NewsletterSubscriberModel;


/**
 * @author Luiza
 *
 */

public class NewsletterSubscriberReversePopulator implements Populator<NewsletterSubscriberData, NewsletterSubscriberModel>
{

	@Resource
	private BaseStoreService baseStoreService;

	//protected static final Logger LOG = Logger.getLogger(NewsletterSubscriberReversePopulator.class);

	@Override
	public void populate(final NewsletterSubscriberData source, final NewsletterSubscriberModel target) throws ConversionException
	{
		target.setName(source.getName());
		target.setEmail(source.getEmail());
		target.setGender(source.getGender());
		target.setReceive(source.getReceive());

		final String baseStoreUid = source.getBaseStore();

		final BaseStoreModel basestoremodel = baseStoreService.getBaseStoreForUid(baseStoreUid);
		target.setBaseStore(basestoremodel);
	}
}