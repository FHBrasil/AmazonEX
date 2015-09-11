package com.pixi.webservices.converters.populators.bmecat.header;

import org.apache.log4j.Logger;

import com.pixi.webservices.constants.PixiwebservicesConstants;
import com.pixi.webservices.jaxb.product.export.Header;

import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiBMEcatHeaderPopulator implements Populator<CatalogModel, Header>
{
	private Logger LOG = Logger.getLogger(DefaultPixiBMEcatHeaderPopulator.class);
	
	@Override
	public void populate(CatalogModel source, Header target) throws ConversionException 
	{
		LOG.info("populating");
		
		String generatorInfo = PixiwebservicesConstants.Product.GENERATOR_INFO;
		
		target.setGENERATORINFO(generatorInfo);
	}
}