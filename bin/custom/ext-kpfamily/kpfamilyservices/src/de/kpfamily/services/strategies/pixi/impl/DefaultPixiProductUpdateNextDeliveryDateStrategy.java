package de.kpfamily.services.strategies.pixi.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.pixi.core.strategies.PixiProductUpdateNextDeliveryDateStrategy;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.servicelayer.model.ModelService;

public class DefaultPixiProductUpdateNextDeliveryDateStrategy implements PixiProductUpdateNextDeliveryDateStrategy 
{
	private static final Logger LOG = Logger.getLogger(DefaultPixiProductUpdateNextDeliveryDateStrategy.class);

	@Resource
	private ModelService modelService;
	
	@Override
	public boolean updateNextDeliveryDate(final ProductModel product, final Date arrivalDate) 
	{
		Assert.notNull(product);
		
		LOG.debug("Updating " + product.getCode() + " nextDeliveryDate to " + arrivalDate);

		final StockLevelModel defaultStockLevel = product.getDefaultStockLevel();
		
		if(defaultStockLevel != null)
		{
			defaultStockLevel.setWarehouseArrivalDate(arrivalDate);
			getModelService().save(defaultStockLevel);
		}
		
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