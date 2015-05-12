/**
 *
 */
package com.fliegersoftware.media.interceptors;

import static de.hybris.platform.core.model.product.ProductModel.GALLERYIMAGES;
import static de.hybris.platform.servicelayer.model.ModelContextUtils.getItemModelContext;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.model.ItemModelContext;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.fliegersoftware.media.events.GalleryChangedEvent;


/**
 * Interceptor used to handle the change of the galleryImages attribute of the {@link ProductModel} item
 *
 * @author Franthescolly Maneira
 *
 */
public class ProductGalleryImagesInterceptor implements PrepareInterceptor
{
	/**
	 * EventService used to publish the event that handles the change of the galleryImages attribute
	 */
	private EventService eventService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.servicelayer.interceptor.PrepareInterceptor#onPrepare(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onPrepare(final Object object, final InterceptorContext context) throws InterceptorException
	{
		Assert.isInstanceOf(ProductModel.class, object);

		final ProductModel product = (ProductModel) object;

		if (!allowInterception(product, context))
		{
			return;
		}

		removeDuplicatedContainers(product);

		final GalleryChangedEvent event = new GalleryChangedEvent();
		event.setProduct(product);
		event.setNewGalleryImages(product.getGalleryImages());
		event.setOldGalleryImages(getOldGalleryImages(product));

		//triggers an event to handle the attribute change
		getEventService().publishEvent(event);
	}

	/**
	 * @param product
	 */
	private void removeDuplicatedContainers(final ProductModel product)
	{
		final List<MediaContainerModel> originalGalleries = product.getGalleryImages();

		if (CollectionUtils.isNotEmpty(originalGalleries) && originalGalleries.size() > 1)
		{
			final LinkedHashSet<MediaContainerModel> withoutDuplications = new LinkedHashSet<MediaContainerModel>(originalGalleries);

			if (originalGalleries.size() != withoutDuplications.size())
			{
				product.setGalleryImages(new ArrayList<MediaContainerModel>(withoutDuplications));
			}
		}
	}

	/**
	 * Verifies if the interceptor should run or not.<br/>
	 * By default we check if the galleryImages attribute was changed and if the product {@link CatalogVersionModel} is
	 * not the active one.
	 *
	 * @param product
	 *           The product intercepted
	 * @param context
	 *           The interception context
	 * @return true of the interception is allowed, otherwise false
	 */
	protected boolean allowInterception(final ProductModel product, final InterceptorContext context)
	{
		final CatalogVersionModel catalogVersion = product.getCatalogVersion();
		if (catalogVersion != null && BooleanUtils.isTrue(catalogVersion.getActive()))
		{
			return false;
		}

		if (!context.isModified(product, GALLERYIMAGES))
		{
			return false;
		}

		return true;
	}

	/**
	 * Obtain the value of the old galleryImages attribute of the given {@link ProductModel}
	 *
	 * @param product
	 *           The product to look for the old values of the galleryImages attribute
	 * @return a list containing the old {@link MediaModel} items of galleryImages attribute
	 */
	private List<MediaContainerModel> getOldGalleryImages(final ProductModel product)
	{
		final ItemModelContext context = getItemModelContext(product);

		if (context.isNew())
		{
			return null;
		}

		return context.getOriginalValue(GALLERYIMAGES);
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
	@Required
	public void setEventService(final EventService eventService)
	{
		this.eventService = eventService;
	}
}