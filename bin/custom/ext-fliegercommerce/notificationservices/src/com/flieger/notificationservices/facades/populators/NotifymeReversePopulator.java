/**
 * 
 */
package com.flieger.notificationservices.facades.populators;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.Config;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.flieger.notificationservices.data.NotifymeData;
import com.flieger.notificationservices.model.NotifymeModel;

/**
 * @author Vinicius de Souza
 *
 */
public class NotifymeReversePopulator implements Populator<NotifymeData, NotifymeModel>
{
	private final Logger LOG = Logger.getLogger(NotifymeReversePopulator.class);
	
	private static final String SUFIX_CATALOG = "ProductCatalog";

	private static final String VERSION = "notificationservices.notifyme.product.vertion";

	@Resource
	private BaseStoreService baseStoreService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private CatalogVersionService catalogVersionService;
	
	@Override
	public void populate(NotifymeData source, NotifymeModel target) throws ConversionException
	{
		target.setProduct(getProduct(source.getCode(), source.getBaseStore()));
		target.setBaseStore(baseStoreService.getBaseStoreForUid(source.getBaseStore()));
		target.setName(source.getName());
		target.setEmail(source.getEmail());
		target.setCreated(source.getCreated());
		target.setNotified(source.getNotified());		
		target.setExpired(source.getExpired());
		target.setDays(source.getDays());
	}

	/**
	 * @param code
	 * @param baseStore
	 * @return HeringProduct
	 */
	private ProductModel getProduct(String code, String baseStore)
	{
		final CatalogVersionModel cv = catalogVersionService.getCatalogVersion(baseStore+SUFIX_CATALOG, Config.getParameter(VERSION));
		LOG.info(cv.getCatalog().getName());
		LOG.info(cv.getVersion());
		return productService.getProductForCode(cv, code);
	}
}