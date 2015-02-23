package com.pixi.webservices.converters.populators.bmecat.product;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.pixi.core.strategies.PixiProductGetItemTagsStrategy;
import com.pixi.webservices.jaxb.product.export.Article;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiBMEcatProductTagPopulator implements Populator<ProductModel, Article>
{
	private Logger LOG = Logger.getLogger(DefaultPixiBMEcatProductTagPopulator.class);
	
	@Resource
	private PixiProductGetItemTagsStrategy pixiProductGetItemTagsStrategy;
	
	@Override
	public void populate(ProductModel source, Article target) throws ConversionException 
	{
		LOG.info("populating");
		
		final List<String> itemTags = pixiProductGetItemTagsStrategy.getItemTagsByProduct(source);
		
		target.getITEMTAG().addAll(itemTags);
	}

	/**
	 * @return the pixiProductGetItemTagsStrategy
	 */
	public PixiProductGetItemTagsStrategy getPixiProductGetItemTagsStrategy() 
	{
		return pixiProductGetItemTagsStrategy;
	}

	/**
	 * @param pixiProductGetItemTagsStrategy the pixiProductGetItemTagsStrategy to set
	 */
	public void setPixiProductGetItemTagsStrategy(PixiProductGetItemTagsStrategy pixiProductGetItemTagsStrategy) 
	{
		this.pixiProductGetItemTagsStrategy = pixiProductGetItemTagsStrategy;
	}
}