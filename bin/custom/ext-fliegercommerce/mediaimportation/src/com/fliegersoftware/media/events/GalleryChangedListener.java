/**
 *
 */
package com.fliegersoftware.media.events;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;

import org.springframework.beans.factory.annotation.Required;

import com.fliegersoftware.media.strategies.ProductGalleryChangedListenerStrategy;


/**
 * Event listener responsible for taking care of the updates occurred at the galleryImages attribute of the
 * {@link ProductModel} type
 *
 * @author Franthescolly Maneira
 *
 */
public class GalleryChangedListener extends AbstractEventListener<GalleryChangedEvent>
{
	private ProductGalleryChangedListenerStrategy productGalleryChangedListenerStrategy;
	
	/* (non-Javadoc)
	 * @see de.hybris.platform.servicelayer.event.impl.AbstractEventListener#onEvent(de.hybris.platform.servicelayer.event.events.AbstractEvent)
	 */
	@Override
	protected void onEvent(GalleryChangedEvent event)
	{
		getProductGalleryChangedListenerStrategy().execute(event);
	}

	/**
	 * @return the productGalleryChangedListenerStrategy
	 */
	public ProductGalleryChangedListenerStrategy getProductGalleryChangedListenerStrategy()
	{
		return productGalleryChangedListenerStrategy;
	}

	/**
	 * @param productGalleryChangedListenerStrategy the productGalleryChangedListenerStrategy to set
	 */
	@Required
	public void setProductGalleryChangedListenerStrategy(ProductGalleryChangedListenerStrategy productGalleryChangedListenerStrategy)
	{
		this.productGalleryChangedListenerStrategy = productGalleryChangedListenerStrategy;
	}

}