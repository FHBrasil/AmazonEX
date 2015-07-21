package com.pixi.webservices.controller.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pixi.core.strategies.PixiProductUpdateNextDeliveryDateStrategy;
import com.pixi.webservices.controller.AbstractPixiSecuredController;
import com.pixi.webservices.jaxb.response.GlobalResponse;
import com.pixi.webservices.jaxb.stock.request.ImportProductStockRequest;

import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.basecommerce.enums.StockLevelUpdateType;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.stock.model.StockLevelHistoryEntryModel;

@Controller
public class ImportProductController extends AbstractPixiSecuredController
{
	private static final String ACTION = "import_stock";

	@Resource
	private ProductService productService;
	
	@Resource
	private StockService stockService;
	
	@Resource
	private WarehouseService warehouseService;
	
	@Resource
	private PixiProductUpdateNextDeliveryDateStrategy pixiProductUpdateNextDeliveryDateStrategy; 
	
	@RequestMapping(method = RequestMethod.GET, produces = "text/xml", params="action=" + ACTION)
	public @ResponseBody GlobalResponse handle(@RequestParam final String data, @RequestParam final String session) throws JAXBException
	{
		final ImportProductStockRequest request = convert(ImportProductStockRequest.class, data);
		
		final ProductModel product = getProductService().getProductForCode(request.getARTICLEITEMID());
		
		if(product == null)
		{
			return createGlobalResponse(false, "not found: " + request.getARTICLEITEMID());
		}
		
		updateStockLevel(request, product);
		
		updateProductEAN(request, product);
		
		updateDeliverySchedule(request, product);
		
		return createGlobalResponse(true, null);
	}

	/**
	 * 
	 * @param request
	 * @param product
	 */
	private void updateStockLevel(ImportProductStockRequest request, ProductModel product) 
	{
		final int available = request.getQUANTITY();

		final WarehouseModel warehouse = getWarehouseService().getWarehouseForCode("default");
		
		createOrUpdateStockLevel(product, warehouse, available);
	}

	/**
	 * 
	 * @param request
	 * @param product
	 */
	private void updateProductEAN(final ImportProductStockRequest request, final ProductModel product) 
	{
		final String ean = request.getEAN();
		
		if(StringUtils.isBlank(ean))
		{
			return;
		}
		
		if(ean.compareToIgnoreCase(product.getEan()) == 0)
		{
			return;
		}
		
		product.setEan(ean);
		
		getModelService().save(product);
	}
	
	/**
	 * 
	 * @param request
	 * @param product
	 */
	private void updateDeliverySchedule(ImportProductStockRequest request, ProductModel product) 
	{
		getPixiProductUpdateNextDeliveryDateStrategy().updateNextDeliveryDate(product, request.getDELIVERYDATE());
	}

	/**
	 * 
	 * @param product
	 * @param warehouse
	 * @param available
	 * @return
	 */
	private StockLevelModel createOrUpdateStockLevel(final ProductModel product, final WarehouseModel warehouse, final int available)
	{
		try
		{
			StockLevelModel stockLevel = getStockService().getStockLevel(product, warehouse);
			String comment;
			final List<StockLevelHistoryEntryModel> historyEntries = new ArrayList<StockLevelHistoryEntryModel>();

			if (stockLevel != null)
			{
				comment = "Stock update";

				historyEntries.addAll(stockLevel.getStockLevelHistoryEntries());
			}
			else
			{
				comment = "First stock import";

				stockLevel = getModelService().create(StockLevelModel.class);
				stockLevel.setProductCode(product.getCode());
				stockLevel.setWarehouse(warehouse);
				stockLevel.setOverSelling(0);
				stockLevel.setReserved(0);
				stockLevel.setInStockStatus(InStockStatus.NOTSPECIFIED);
				stockLevel.setMaxStockLevelHistoryCount(-1);
				stockLevel.setTreatNegativeAsZero(true);
			}

			stockLevel.setAvailable(available);

			final StockLevelHistoryEntryModel entry = createHistoryEntry(stockLevel, available, StockLevelUpdateType.WAREHOUSE,
					comment);

			historyEntries.add(entry);

			stockLevel.setStockLevelHistoryEntries(historyEntries);

			getModelService().save(stockLevel);

			return stockLevel;
		}
		catch (final Exception e)
		{
			LOG.error("error", e);
		}

		return null;
	}

	/**
	 * 
	 * @param stockLevel
	 * @param actual
	 * @param updateType
	 * @param comment
	 * @return
	 */
	private StockLevelHistoryEntryModel createHistoryEntry(final StockLevelModel stockLevel, final int actual,
			final StockLevelUpdateType updateType, final String comment)
	{

		final StockLevelHistoryEntryModel historyEntry = getModelService().create(StockLevelHistoryEntryModel.class);
		historyEntry.setStockLevel(stockLevel);
		historyEntry.setActual(actual);
		historyEntry.setUpdateType(updateType);
		historyEntry.setComment(comment);
		historyEntry.setUpdateDate(new Date());

		try
		{
			getModelService().save(historyEntry);
		}
		catch (final Exception e)
		{
			LOG.error("error", e);
		}

		return historyEntry;
	}
	
	public ProductService getProductService() 
	{
		return productService;
	}
	
	public StockService getStockService() 
	{
		return stockService;
	}
	
	public WarehouseService getWarehouseService() 
	{
		return warehouseService;
	}

	/**
	 * @return the pixiProductUpdateNextDeliveryDateStrategy
	 */
	public PixiProductUpdateNextDeliveryDateStrategy getPixiProductUpdateNextDeliveryDateStrategy() {
		return pixiProductUpdateNextDeliveryDateStrategy;
	}

	/**
	 * @param pixiProductUpdateNextDeliveryDateStrategy the pixiProductUpdateNextDeliveryDateStrategy to set
	 */
	public void setPixiProductUpdateNextDeliveryDateStrategy(
			PixiProductUpdateNextDeliveryDateStrategy pixiProductUpdateNextDeliveryDateStrategy) {
		this.pixiProductUpdateNextDeliveryDateStrategy = pixiProductUpdateNextDeliveryDateStrategy;
	}
}