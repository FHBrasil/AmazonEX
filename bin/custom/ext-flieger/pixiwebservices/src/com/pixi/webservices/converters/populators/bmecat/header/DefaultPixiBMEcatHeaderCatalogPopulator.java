package com.pixi.webservices.converters.populators.bmecat.header;

import java.util.Date;

import org.apache.log4j.Logger;

import com.pixi.webservices.jaxb.product.export.Catalog;
import com.pixi.webservices.jaxb.product.export.Header;

import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiBMEcatHeaderCatalogPopulator implements Populator<CatalogModel, Header>
{
	private Logger LOG = Logger.getLogger(DefaultPixiBMEcatHeaderCatalogPopulator.class);
	
	@Override
	public void populate(CatalogModel source, Header target) throws ConversionException 
	{
		LOG.info("populating");
		
		final Catalog catalog = new Catalog();
		catalog.setCATALOGID("catalogID");
		catalog.setCATALOGNAME("catalogName");
		catalog.setCATALOGVERSION("version");
		catalog.setCURRENCY("EUR");
		catalog.setDATABASE("database");
		catalog.setDATEEXPORT(new Date());
		catalog.setEXPORTDATE(new Date());
		catalog.setLANGUAGE("de-DE");
		catalog.setSHOPID("shop id");
		
		target.setCATALOG(catalog);
	}
}