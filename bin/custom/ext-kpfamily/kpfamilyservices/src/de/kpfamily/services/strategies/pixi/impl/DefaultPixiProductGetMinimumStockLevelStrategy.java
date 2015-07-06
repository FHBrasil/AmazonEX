package de.kpfamily.services.strategies.pixi.impl;

import javax.annotation.Resource;

import org.springframework.util.Assert;

import com.pixi.core.strategies.PixiProductGetMinimumStockLevelStrategy;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.stock.StockService;

public class DefaultPixiProductGetMinimumStockLevelStrategy implements PixiProductGetMinimumStockLevelStrategy 
{
	@Resource
	private StockService stockService;
	
	@Resource
	private WarehouseService warehouseService;
	
	@Override
	public int getMinimumStockLevel(final ProductModel product) 
	{
		Assert.notNull(product);
		
		final WarehouseModel warehouse = getWarehouseService().getWarehouseForCode("default");
		
		final StockLevelModel stockLevel = getStockService().getStockLevel(product, warehouse);
		
		if(stockLevel == null)
		{
			return 0;
		}
		
		return stockLevel.getMinimumStockLevel();
	}

	/**
	 * @return the warehouseService
	 */
	public WarehouseService getWarehouseService() 
	{
		return warehouseService;
	}

	/**
	 * @param warehouseService the warehouseService to set
	 */
	public void setWarehouseService(WarehouseService warehouseService) 
	{
		this.warehouseService = warehouseService;
	}

	/**
	 * @return the stockService
	 */
	public StockService getStockService() 
	{
		return stockService;
	}

	/**
	 * @param stockService the stockService to set
	 */
	public void setStockService(StockService stockService) 
	{
		this.stockService = stockService;
	}
}