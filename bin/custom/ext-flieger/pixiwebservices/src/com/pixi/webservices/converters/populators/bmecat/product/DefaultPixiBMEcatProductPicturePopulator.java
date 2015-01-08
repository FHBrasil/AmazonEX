package com.pixi.webservices.converters.populators.bmecat.product;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.pixi.webservices.constants.PixiwebservicesConstants;
import com.pixi.webservices.jaxb.product.export.Article;
import com.pixi.webservices.jaxb.product.export.Mime;

import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.ImageDataType;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiBMEcatProductPicturePopulator implements Populator<ProductModel, Article>
{
	private Logger LOG = Logger.getLogger(DefaultPixiBMEcatProductPicturePopulator.class);
	
	@Resource
	private Populator<ProductModel, ProductData> productPrimaryImagePopulator;
	
	@Override
	public void populate(ProductModel source, Article target) throws ConversionException 
	{
		LOG.info("populating");
		
		final Mime mime = new Mime();
		mime.setMIMEPURPOSE("normal");
		mime.setMIMETYPE("image/jpeg");
		
		mime.setMIMESOURCE(getMimeSource(source));
		target.setMIME(mime);
	}

	private String getMimeSource(final ProductModel source) 
	{
		final ProductData data = new ProductData();
		productPrimaryImagePopulator.populate(source, data);
		
		final ImageData imageData = getPrimaryImageForProductAndFormat(data, PixiwebservicesConstants.Product.MIME_SOURCE_FORMAT);

		return imageData == null ? "" : "http://88.198.78.166" + imageData.getUrl();
	}
	
	/**
	 * Function to get a primary Image for a Product in a specific format
	 * 
	 * @param product
	 *           the product
	 * @param format
	 *           the desired format
	 * @return the image
	 */
	private ImageData getPrimaryImageForProductAndFormat(final ProductData product, final String format)
	{
		if (product != null && format != null)
		{
			final Collection<ImageData> images = product.getImages();
			if (images != null && !images.isEmpty())
			{
				for (final ImageData image : images)
				{
					if (ImageDataType.PRIMARY.equals(image.getImageType()) && format.equals(image.getFormat()))
					{
						return image;
					}
				}
			}
		}
		return null;
	}
}