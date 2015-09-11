/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 31/03/2015 15:17:19
 * ----------------------------------------------------------------
 *
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.fliegersoftware.media.events;

import java.io.Serializable;

import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;
import java.util.List;

public class GalleryChangedEvent extends AbstractEvent {

	/** <i>Generated property</i> for <code>GalleryChangedEvent.product</code> property defined at extension <code>mediaimportation</code>. */
	private ProductModel product;
	/** <i>Generated property</i> for <code>GalleryChangedEvent.newGalleryImages</code> property defined at extension <code>mediaimportation</code>. */
	private List<MediaContainerModel> newGalleryImages;
	/** <i>Generated property</i> for <code>GalleryChangedEvent.oldGalleryImages</code> property defined at extension <code>mediaimportation</code>. */
	private List<MediaContainerModel> oldGalleryImages;
	
	public GalleryChangedEvent()
	{
		super();
	}

	public GalleryChangedEvent(final Serializable source)
	{
		super(source);
	}
	
		
	public void setProduct(final ProductModel product)
	{
		this.product = product;
	}
	
			
	public ProductModel getProduct() 
	{
		return product;
	}
		
		
	public void setNewGalleryImages(final List<MediaContainerModel> newGalleryImages)
	{
		this.newGalleryImages = newGalleryImages;
	}
	
			
	public List<MediaContainerModel> getNewGalleryImages() 
	{
		return newGalleryImages;
	}
		
		
	public void setOldGalleryImages(final List<MediaContainerModel> oldGalleryImages)
	{
		this.oldGalleryImages = oldGalleryImages;
	}
	
			
	public List<MediaContainerModel> getOldGalleryImages() 
	{
		return oldGalleryImages;
	}
		
}