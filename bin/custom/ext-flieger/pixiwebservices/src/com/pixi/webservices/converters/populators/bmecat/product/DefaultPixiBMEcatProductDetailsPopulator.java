package com.pixi.webservices.converters.populators.bmecat.product;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.pixi.webservices.jaxb.product.export.Article;
import com.pixi.webservices.jaxb.product.export.ArticleDetails;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.kpfamily.core.model.BabyartikelProductModel;

public class DefaultPixiBMEcatProductDetailsPopulator implements Populator<ProductModel, Article>
{
	private Logger LOG = Logger.getLogger(DefaultPixiBMEcatProductDetailsPopulator.class);
	
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
		
		if(source instanceof BabyartikelProductModel)
		{
			double weight = ((BabyartikelProductModel) source).getWeight();
			details.setWEIGHT(BigDecimal.valueOf(weight));
		}
		
		target.setARTICLEDETAILS(details);
	}
}