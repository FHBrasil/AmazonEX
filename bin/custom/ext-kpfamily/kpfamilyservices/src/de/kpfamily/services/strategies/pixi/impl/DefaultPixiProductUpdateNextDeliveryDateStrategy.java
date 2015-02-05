package de.kpfamily.services.strategies.pixi.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.pixi.core.strategies.PixiProductUpdateNextDeliveryDateStrategy;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.kpfamily.core.model.BabyartikelProductModel;

public class DefaultPixiProductUpdateNextDeliveryDateStrategy implements PixiProductUpdateNextDeliveryDateStrategy 
{
	private static final Logger LOG = Logger.getLogger(DefaultPixiProductUpdateNextDeliveryDateStrategy.class);

	@Resource
	private ModelService modelService;
	
	@Override
	public boolean updateNextDeliveryDate(final ProductModel product, final Date nextDeliveryDate) 
	{
		Assert.notNull(product);
		Assert.isInstanceOf(BabyartikelProductModel.class, product);
		
		BabyartikelProductModel babyartikelProduct = (BabyartikelProductModel) product;
		
		LOG.debug("Updating " + product.getCode() + " nextDeliveryDate to " + nextDeliveryDate);
		
		babyartikelProduct.setNextDeliveryDate(nextDeliveryDate);
		
		getModelService().save(product);
		
		return true;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService() 
	{
		return modelService;
	}

	/**
	 * @param modelService the modelService to set
	 */
	public void setModelService(ModelService modelService) 
	{
		this.modelService = modelService;
	}
}