package com.pixi.webservices.converters.populators.bmecat.product;

import org.apache.log4j.Logger;

import com.pixi.webservices.jaxb.product.export.Article;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiBMEcatProductPopulator implements Populator<ProductModel, Article>
{
	private Logger LOG = Logger.getLogger(DefaultPixiBMEcatProductPopulator.class);
	
	@Override
	public void populate(ProductModel source, Article target) throws ConversionException 
	{
		LOG.info("populating");
		
		final Double unitDouble = source.getNumberContentUnits();

		if(unitDouble == null || unitDouble == 0) {
			target.setORDERUNIT("1");
		} else {
			target.setORDERUNIT(new Long(Math.round(unitDouble)).toString());
		}
		
		target.setSUPPLIERAID(source.getCode());
	}
}