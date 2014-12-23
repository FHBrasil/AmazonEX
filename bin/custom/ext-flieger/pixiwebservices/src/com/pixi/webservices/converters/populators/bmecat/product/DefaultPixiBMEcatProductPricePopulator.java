package com.pixi.webservices.converters.populators.bmecat.product;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.pixi.webservices.jaxb.product.export.Article;
import com.pixi.webservices.jaxb.product.export.ArticlePrice;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiBMEcatProductPricePopulator implements Populator<ProductModel, Article>
{
	private Logger LOG = Logger.getLogger(DefaultPixiBMEcatProductPricePopulator.class);
	
	@Override
	public void populate(ProductModel source, Article target) throws ConversionException 
	{
		LOG.info("populating");
		
		final ArticlePrice price = new ArticlePrice();
		price.setPRICEAMOUNT(BigDecimal.TEN.multiply(BigDecimal.valueOf(1)));
		price.setPRICECURRENCY("EUR");
		price.setPriceType("type");
		price.setSUPPLPRICEAMOUNT(BigDecimal.TEN.multiply(BigDecimal.valueOf(1)));

		target.getARTICLEPRICE().add(price);
	}
}