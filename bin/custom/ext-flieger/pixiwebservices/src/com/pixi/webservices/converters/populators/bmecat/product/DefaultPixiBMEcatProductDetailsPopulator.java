package com.pixi.webservices.converters.populators.bmecat.product;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.pixi.core.strategies.PixiProductGetWeightStrategy;
import com.pixi.webservices.jaxb.product.export.Article;
import com.pixi.webservices.jaxb.product.export.ArticleDetails;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiBMEcatProductDetailsPopulator implements Populator<ProductModel, Article>
{
	private Logger LOG = Logger.getLogger(DefaultPixiBMEcatProductDetailsPopulator.class);
	
	@Resource
	private PixiProductGetWeightStrategy pixiProductGetWeightStrategy;
	
	@Override
	public void populate(ProductModel source, Article target) throws ConversionException 
	{
		LOG.info("populating");
		
		final ArticleDetails details = new ArticleDetails();
		details.setDESCRIPTIONSHORT(source.getName());
		details.setDESCRIPTIONLONG(source.getName());
		details.setEAN(source.getEan());
		details.setMANUFACTURERNAME(source.getManufacturerName());
		details.setSEGMENT(source.getSupercategories().iterator().next().getCode());
		details.setINTERNALITEMNUMBER(source.getCode());
		details.setWEIGHT(getPixiProductGetWeightStrategy().getWeight(source));
		
		target.setARTICLEDETAILS(details);
	}

	/**
	 * @return the pixiProductGetWeightStrategy
	 */
	public PixiProductGetWeightStrategy getPixiProductGetWeightStrategy() 
	{
		return pixiProductGetWeightStrategy;
	}

	/**
	 * @param pixiProductGetWeightStrategy the pixiProductGetWeightStrategy to set
	 */
	public void setPixiProductGetWeightStrategy(PixiProductGetWeightStrategy pixiProductGetWeightStrategy) 
	{
		this.pixiProductGetWeightStrategy = pixiProductGetWeightStrategy;
	}
}