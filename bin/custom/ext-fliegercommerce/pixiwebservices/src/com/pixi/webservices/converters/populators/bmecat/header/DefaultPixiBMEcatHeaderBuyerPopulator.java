package com.pixi.webservices.converters.populators.bmecat.header;

import org.apache.log4j.Logger;

import com.pixi.webservices.jaxb.product.export.ArticleAddress;
import com.pixi.webservices.jaxb.product.export.Buyer;
import com.pixi.webservices.jaxb.product.export.Header;

import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiBMEcatHeaderBuyerPopulator implements Populator<CatalogModel, Header>
{
	private Logger LOG = Logger.getLogger(DefaultPixiBMEcatHeaderBuyerPopulator.class);
	
	@Override
	public void populate(CatalogModel source, Header target) throws ConversionException 
	{
		LOG.info("populating");
		
		final ArticleAddress address = new ArticleAddress();
		address.setCITY("city");
		address.setCOUNTRY("country");
		address.setNAME("name");
		address.setNAME2("name 2");
		address.setSTREET("street");
		address.setType("type");
		address.setZIP("zip");

		final Buyer buyer = new Buyer();
		buyer.setADDRESS(address);
		buyer.setBUYERID("buyer id");
		buyer.setBUYERNAME("buyer name");
		
		target.setBUYER(buyer);
	}
}