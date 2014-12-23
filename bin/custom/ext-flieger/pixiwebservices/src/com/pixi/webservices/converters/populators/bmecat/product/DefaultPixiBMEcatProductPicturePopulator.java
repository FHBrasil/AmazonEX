package com.pixi.webservices.converters.populators.bmecat.product;

import org.apache.log4j.Logger;

import com.pixi.webservices.jaxb.product.export.Article;
import com.pixi.webservices.jaxb.product.export.Mime;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiBMEcatProductPicturePopulator implements Populator<ProductModel, Article>
{
	private Logger LOG = Logger.getLogger(DefaultPixiBMEcatProductPicturePopulator.class);
	
	@Override
	public void populate(ProductModel source, Article target) throws ConversionException 
	{
		LOG.info("populating");
		
		final Mime mime = new Mime();
		mime.setMIMEPURPOSE("purpose" + 1);
		mime.setMIMESOURCE("source" + 1);
		mime.setMIMETYPE("type" + 1);
		
		target.setMIME(mime);
	}
}