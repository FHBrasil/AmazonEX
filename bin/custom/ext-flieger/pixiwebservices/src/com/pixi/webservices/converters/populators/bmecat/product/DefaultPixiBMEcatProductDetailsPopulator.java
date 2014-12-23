package com.pixi.webservices.converters.populators.bmecat.product;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.pixi.webservices.jaxb.product.export.Article;
import com.pixi.webservices.jaxb.product.export.ArticleDetails;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiBMEcatProductDetailsPopulator implements Populator<ProductModel, Article>
{
	private Logger LOG = Logger.getLogger(DefaultPixiBMEcatProductDetailsPopulator.class);
	
	@Override
	public void populate(ProductModel source, Article target) throws ConversionException 
	{
		LOG.info("populating");
		
		final ArticleDetails details = new ArticleDetails();
		details.setDESCRIPTIONSHORT("short");
		details.setEAN("ean");
		details.setINTERNALITEMNUMBER("itemNumber");
		details.setMANUFACTURERNAME("manufacturer name");
		details.setSEGMENT("segment");
		details.setWEIGHT(BigDecimal.TEN.multiply(BigDecimal.valueOf(1)));
		
		target.setARTICLEDETAILS(details);
	}
}