/**
 *
 */
package com.fliegersoftware.media.interceptors;

import static de.hybris.platform.core.model.product.ProductModel.GALLERYIMAGES;
import static de.hybris.platform.servicelayer.model.ModelContextUtils.getItemModelContext;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.model.ItemModelContext;

import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.fliegersoftware.media.events.GalleryChangedEvent;


/**
 * @author franthescollymaneira
 *
 */
public class ProductGalleryImagesInterceptor implements PrepareInterceptor<ProductModel>
{
	private static final Logger LOG = Logger.getLogger(ProductGalleryImagesInterceptor.class);

	private EventService eventService;

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.servicelayer.interceptor.PrepareInterceptor#onPrepare(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onPrepare(final ProductModel product, final InterceptorContext context) throws InterceptorException
	{
		if (!allowInterception(product, context))
		{
			LOG.debug(String.format("Product %s interception is not allowed, consult server log for details", product.getCode()));
			return;
		}

		final GalleryChangedEvent event = new GalleryChangedEvent();
		event.setProduct(product);
		event.setNewGalleryImages(product.getGalleryImages());
		event.setOldGalleryImages(getOldGalleryImages(product));

		getEventService().publishEvent(event);
	}

	protected boolean allowInterception(final ProductModel product, final InterceptorContext context)
	{
		final CatalogVersionModel catalogVersion = product.getCatalogVersion();
		if (catalogVersion != null && BooleanUtils.isTrue(catalogVersion.getActive()))
		{
			LOG.debug(String.format("Product %s catalogVersion is active, expected not active catalogVersion", product.getCode()));
			return false;
		}

		if (!context.isModified(product, GALLERYIMAGES))
		{
			LOG.debug(String.format("Product %s galleryImages is not modified", product.getCode()));
			return false;
		}

		return true;
	}

	/**
	 * @param product
	 * @return
	 */
	private List<MediaContainerModel> getOldGalleryImages(final ProductModel product)
	{
		final ItemModelContext context = getItemModelContext(product);

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