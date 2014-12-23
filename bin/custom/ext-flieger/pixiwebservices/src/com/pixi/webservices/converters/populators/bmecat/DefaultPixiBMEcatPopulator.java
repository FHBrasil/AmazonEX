package com.pixi.webservices.converters.populators.bmecat;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.pixi.webservices.jaxb.product.export.Article;
import com.pixi.webservices.jaxb.product.export.BMECAT;
import com.pixi.webservices.jaxb.product.export.Header;

import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class DefaultPixiBMEcatPopulator implements Populator<List<ProductModel>, BMECAT>
{
	private Logger LOG = Logger.getLogger(DefaultPixiBMEcatPopulator.class);
	
	@Resource
	Converter<CatalogModel, Header> pixiBMEcatHeaderConverter;
	
	@Resource
	Converter<ProductModel, Article> pixiBMEcatProductConverter;
	
	@Override
	public void populate(List<ProductModel> source, BMECAT target) throws ConversionException 
	{
		LOG.info("populating");
		
		CatalogModel catalog = new CatalogModel();

		target.setVersion(BigDecimal.ONE);
		target.setHEADER(pixiBMEcatHeaderConverter.convert(catalog));//TODO converter
		
		List<Article> articles = target.getARTICLE();
		
		for(ProductModel product : source)
		{
			articles.add(pixiBMEcatProductConverter.convert(product));
		}
	}
}