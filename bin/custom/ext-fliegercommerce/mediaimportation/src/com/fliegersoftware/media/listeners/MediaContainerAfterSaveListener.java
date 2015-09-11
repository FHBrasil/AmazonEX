package com.fliegersoftware.media.listeners;

import static com.fliegersoftware.media.constants.MediaimportationConstants.LISTENER_ENABLED_KEY;
import static com.fliegersoftware.media.constants.MediaimportationConstants.MEDIACONTAINER_TYPE_CODE;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.tx.AfterSaveEvent;
import de.hybris.platform.tx.AfterSaveListener;
import de.hybris.platform.util.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.BooleanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.fliegersoftware.media.strategies.ConvertMediaContainerMediasStrategy;


/**
 * {@link AfterSaveListener} triggered when a {@link MediaContainerModel} is saved, this listener is responsible of
 * converting the medias related to the saved container.
 *
 * @author Franthescolly Maneira
 *
 */
public class MediaContainerAfterSaveListener implements AfterSaveListener
{
	/**
	 * Model service
	 */
	private ModelService modelService;

	/**
	 * Strategy used to convert the medias of a {@link MediaContainerModel}
	 */
	private ConvertMediaContainerMediasStrategy convertMediaContainerMediasStrategy;

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(MediaContainerAfterSaveListener.class);

	@Override
	public void afterSave(final Collection<AfterSaveEvent> events)
	{
		//skip the execution if the listener is not enabled
		if (!isListenerEnabled())
		{
			return;
		}

		//we need to filter the given event to check if they are really related to mediacontainers
		for (final MediaContainerModel container : filterAndTransform(events))
		{
			//verifies if the container is allowed to run
			if (allowConversionProcess(container))
			{
				//convert all changed mediacontainers
				getConvertMediaContainerMediasStrategy().convertMedias(container);
			}
		}
	}

	/**
	 * Verifies if the listener is allowed to be executed.
	 *
	 * @return true if the listener is allowed to be executed, false otherwise.
	 */
	private boolean isListenerEnabled()
	{
		return Config.getBoolean(LISTENER_ENABLED_KEY, true);
	}

	/**
	 * Verifies if the given container is eligible for converting it's medias, by default it's check if the
	 * {@link CatalogVersionModel} is not active and if the given container has a conversion group.
	 *
	 * @param container
	 *           The container to be verified
	 * @return true if the conversion is allowed, false otherwise.
	 */
	private boolean allowConversionProcess(final MediaContainerModel container)
	{
		final CatalogVersionModel catalogVersion = container.getCatalogVersion();

		final boolean isStagedVersion = catalogVersion != null && BooleanUtils.isFalse(catalogVersion.getActive());
		final boolean hasConversionGroup = container.getConversionGroup() != null;

		return isStagedVersion && hasConversionGroup;
	}

	/**
	 * Filters the given {@link AfterSaveEvent} list to extract the events related to {@link MediaContainerModel} items. <br/>
	 * After filtering the events this method will get the changed {@link MediaContainerModel} items.
	 *
	 * @param raw
	 *           all {@link AfterSaveEvent} events triggered
	 * @return list of changed {@link MediaContainerModel} related to the triggered events, otherwise empty.
	 */
	private Collection<MediaContainerModel> filterAndTransform(final Collection<AfterSaveEvent> raw)
	{
		final Collection<AfterSaveEvent> events = filterEvents(raw);

		if (CollectionUtils.isEmpty(events))
		{
			return Collections.emptyList();
		}

		return CollectionUtils.collect(events, new Transformer()
		{
			@Override
			public Object transform(final Object obj)
			{
				final AfterSaveEvent event = (AfterSaveEvent) obj;

				return getMediaContainer(event);
			}
		});
	}

	/**
	 * Obtains the {@link MediaContainerModel} related to the {@link AfterSaveEvent} event that was triggered
	 *
	 * @param event
	 *           instance of the {@link AfterSaveEvent} that was triggered
	 * @return The media container that was saved
	 */
	private MediaContainerModel getMediaContainer(final AfterSaveEvent event)
	{
		final PK pk = event.getPk();

		final MediaContainerModel container = getModelService().get(pk);

		return container;
	}

	/**
	 * By default Hybris will send all events triggered, so we need to filter them to get only those realated to
	 * {@link MediaContainerModel} items.
	 *
	 * @param events
	 *           All triggered events
	 * @return events related to {@link MediaContainerModel} items.
	 */
	private Collection<AfterSaveEvent> filterEvents(final Collection<AfterSaveEvent> events)
	{
		//we filter only events realted to create and update of mediacontainers
		final int[] eventTypes =
		{ AfterSaveEvent.CREATE, AfterSaveEvent.UPDATE };
		Arrays.sort(eventTypes);

		final List<AfterSaveEvent> filtered = new ArrayList<AfterSaveEvent>(events);

		CollectionUtils.filter(filtered, new Predicate()
		{
			@Override
			public boolean evaluate(final Object obj)
			{
				final AfterSaveEvent event = (AfterSaveEvent) obj;
				final int type = event.getType();
				final PK pk = event.getPk();

				final boolean isDesiredEventType = Arrays.binarySearch(eventTypes, type) >= 0;
				final boolean isMediaContainer = (MEDIACONTAINER_TYPE_CODE == pk.getTypeCode());

				return isDesiredEventType && isMediaContainer;
			}
		});

		return filtered;
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
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the convertMediaContainerMediasStrategy
	 */
	public ConvertMediaContainerMediasStrategy getConvertMediaContainerMediasStrategy()
	{
		return convertMediaContainerMediasStrategy;
	}

	/**
	 * @param convertMediaContainerMediasStrategy
	 *           the convertMediaContainerMediasStrategy to set
	 */
	@Required
	public void setConvertMediaContainerMediasStrategy(
			final ConvertMediaContainerMediasStrategy convertMediaContainerMediasStrategy)
	{
		this.convertMediaContainerMediasStrategy = convertMediaContainerMediasStrategy;
	}
}