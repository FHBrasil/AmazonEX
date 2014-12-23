package com.pixi.webservices.converters.populators.bmecat.product;

import org.apache.log4j.Logger;

import com.pixi.webservices.jaxb.product.export.Article;
import com.pixi.webservices.jaxb.product.export.ArticleFeatures;
import com.pixi.webservices.jaxb.product.export.Feature;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiBMEcatProductFeaturesPopulator implements Populator<ProductModel, Article>
{
	private Logger LOG = Logger.getLogger(DefaultPixiBMEcatProductFeaturesPopulator.class);
	
	@Override
	public void populate(ProductModel source, Article target) throws ConversionException 
	{
		LOG.info("populating");
		
		final ArticleFeatures features = new ArticleFeatures();
		features.setCustomsCountryOfOrigin("country");
		features.setCustomsTariffNumber("tariff number");
		features.setCustomsTariffText("tariff text");
		features.setSerialNumberRequired("false");

		for (int i = 0; i < 3; i++)
		{
			final Feature feature = new Feature();
			feature.setFNAME("name" + (i * 1));
			feature.setFVALUE("value" + (i * 1));

			features.getFEATURE().add(feature);
		}
		
		target.setARTICLEFEATURES(features);
	}
}