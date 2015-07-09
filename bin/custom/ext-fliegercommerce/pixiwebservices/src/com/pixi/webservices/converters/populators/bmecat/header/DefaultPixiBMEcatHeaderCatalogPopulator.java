package com.pixi.webservices.converters.populators.bmecat.header;

import java.util.Date;

import org.apache.log4j.Logger;

import com.pixi.webservices.constants.PixiwebservicesConstants;
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

		catalog.setLANGUAGE(source.getLanguages().iterator().next().getIsocode());
		catalog.setCATALOGID(source.getId());
		catalog.setCATALOGNAME(source.getName());
		catalog.setCATALOGVERSION(source.getVersion());
		
		catalog.setCURRENCY(source.getActiveCatalogVersion().getDefaultCurrency().getIsocode());

		catalog.setEXPORTDATE(new Date());
		
		catalog.setDATEEXPORT(getUnixTimestamp());
		
		catalog.setDATABASE(PixiwebservicesConstants.Pixi.DATABASE);
		catalog.setSHOPID(PixiwebservicesConstants.Pixi.SHOP_ID);
		
		target.setCATALOG(catalog);
	}

	private long getUnixTimestamp() 
	{
		return System.currentTimeMillis() / 1000L;
	}
}