package com.pixi.webservices.converters.populators.bmecat;

import org.apache.log4j.Logger;

import com.pixi.webservices.jaxb.product.export.BMECAT;

import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiBMEcatCatalogPopulator implements Populator<CatalogModel, BMECAT>
{
	private Logger LOG = Logger.getLogger(DefaultPixiBMEcatCatalogPopulator.class);
	
	@Override
	public void populate(CatalogModel source, BMECAT target) throws ConversionException 
	{
		LOG.info("populating");
	}
}