package com.pixi.webservices.converters.populators.bmecat.header;

import org.apache.log4j.Logger;

import com.pixi.webservices.constants.PixiwebservicesConstants;
import com.pixi.webservices.jaxb.product.export.Header;
import com.pixi.webservices.jaxb.product.export.Supplier;

import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiBMEcatHeaderSupplierPopulator implements Populator<CatalogModel, Header>
{
	private Logger LOG = Logger.getLogger(DefaultPixiBMEcatHeaderSupplierPopulator.class);
	
	@Override
	public void populate(CatalogModel source, Header target) throws ConversionException 
	{
		LOG.info("populating");
		
		final Supplier supplier = new Supplier();
		supplier.setSUPPLIERNAME(PixiwebservicesConstants.Pixi.COMPANY);
		
		target.setSUPPLIER(supplier);
	}
}