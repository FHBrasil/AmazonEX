package br.hering.core.hmc.attribute;

import de.hybris.platform.acceleratorcms.utils.SpringHelper;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.hmc.attribute.AbstractAttributeDisplayChip;
import de.hybris.platform.hmc.webchips.Chip;
import de.hybris.platform.hmc.webchips.DisplayState;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.stock.StockService;

import javax.servlet.http.HttpServletRequest;

public class StockLevelDisplayChip extends AbstractAttributeDisplayChip
{
	private WarehouseService warehouseService = null;
	private StockService stockService = null;
	private ModelService modelService = null;
	
	/**
	 * @param displayState
	 * @param parent
	 * @param jspURI
	 * @param item
	 * @param qualifier
	 */
	public StockLevelDisplayChip(DisplayState displayState, Chip parent, String jspURI, Item item, String qualifier)
	{
		super(displayState, parent, jspURI, item, qualifier);
		warehouseService = getService("warehouseService", WarehouseService.class);
		stockService = getService("stockService", StockService.class);
		modelService = getService("modelService", ModelService.class);
	}
	
	public StockLevelDisplayChip(DisplayState displayState, Chip parent)
	{
		this(displayState, parent, "", null, "");
	}
	
	

	/* (non-Javadoc)
	 * @see de.hybris.platform.hmc.attribute.AbstractAttributeValueChip#getDisplayValue()
	 */
	@Override
	public String getDisplayValue()
	{
		try
		{
			ProductModel product = modelService.get(getItem());
			
			WarehouseModel warehouse = warehouseService.getWarehouseForCode("hering_estoque_default");
			
			StockLevelModel stockLevel = stockService.getStockLevel(product, warehouse);
			
			if(stockLevel != null)
			{
				return String.valueOf(stockLevel.getAvailable());
			}
		}
		catch (Exception e)
		{
			//
		}
		return String.valueOf("0");
	}

	/**
	 * @param string
	 * @return
	 */
	private <T> T getService(String beanName, Class<T> beanClass)
	{
		HttpServletRequest request = getDisplayState().getRequest();
		return SpringHelper.getSpringBean(request, beanName, beanClass, true);
	}
}