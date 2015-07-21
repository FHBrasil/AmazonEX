package com.pixi.webservices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.pixi.core.strategies.PixiProductUpdateNextDeliveryDateStrategy;
import com.pixi.webservices.controller.product.ImportProductController;
import com.pixi.webservices.jaxb.response.GlobalResponse;
import com.pixi.webservices.jaxb.stock.request.ImportProductStockRequest;
import com.pixi.webservices.util.PixiXMLUtil;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.basecommerce.enums.StockLevelUpdateType;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.stock.model.StockLevelHistoryEntryModel;
import de.hybris.platform.store.services.BaseStoreService;

@UnitTest
public class ImportProductControllerTest
{
	private static final Logger LOG = Logger.getLogger(ImportProductControllerTest.class);
	
	@Spy
	private ImportProductController controller = new ImportProductController();
	
	@Mock
	private BaseStoreService baseStoreService;
	
	@Mock
	private ModelService modelService;
	
	@Mock
	private ProductService productService;
	
	@Mock
	private StockService stockService;
	
	@Mock
	private PixiProductUpdateNextDeliveryDateStrategy pixiProductUpdateNextDeliveryDateStrategy; 
	
	@Mock
	private WarehouseService warehouseService;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(controller.getBaseStoreService()).thenReturn(baseStoreService);
		Mockito.when(controller.getModelService()).thenReturn(modelService);
		Mockito.when(controller.getProductService()).thenReturn(productService);
		Mockito.when(controller.getStockService()).thenReturn(stockService);
		Mockito.when(controller.getWarehouseService()).thenReturn(warehouseService);
		Mockito.when(controller.getJaxbContextFactory()).thenReturn(PixiXMLUtil.getFactory());
		Mockito.when(controller.getPixiProductUpdateNextDeliveryDateStrategy()).thenReturn(pixiProductUpdateNextDeliveryDateStrategy);
		
		
		Mockito.when(warehouseService.getWarehouseForCode("default")).thenReturn(new WarehouseModel());
		
		Mockito.when(modelService.create(StockLevelModel.class)).thenReturn(new StockLevelModel());
		Mockito.when(modelService.create(StockLevelHistoryEntryModel.class)).thenReturn(new StockLevelHistoryEntryModel());
		
		ProductModel product = new ProductModel();
		product.setEan("1234567890123");
		
		Mockito.when(productService.getProductForCode("1234567890")).thenReturn(product);
	}
	
	@Test
	public void shouldWarnProductNotFound() throws Exception
	{
		try {
			GlobalResponse result = controller.handle(getData("0987654321", "1234567890123", new Date(), 15, 10, 0), "sessionId");
			
			Assert.assertEquals("Unexpected code", "FAILURE", result.getCode());
			Assert.assertEquals("Unexpected message", "not found: 0987654321", result.getMessage());
		} catch (Throwable e) {
			LOG.error("error", e);
			throw e;
		}
	}
	
	@Test
	public void shouldCreateANewStockLevel() throws Exception
	{
		try {
			//prepare
			//
			ProductModel product = productService.getProductForCode("1234567890");
			
			StockLevelModel currentStockLevel = new StockLevelModel();
			Mockito.when(modelService.create(StockLevelModel.class)).thenReturn(currentStockLevel);
			
			//execute controller
			//
			GlobalResponse result = controller.handle(getData("1234567890", "3210987654321", new Date(), 100, 0, 0), "sessionId");
			
			//assert result
			//
			Assert.assertEquals("Unexpected code", "OK", result.getCode());
			Assert.assertNull("Unexpected message", result.getMessage());
			Assert.assertEquals("Not the same product code", product.getCode(), currentStockLevel.getProductCode());
			
			Assert.assertEquals("Available quantity is wrong", 100, currentStockLevel.getAvailable());
			
			List<StockLevelHistoryEntryModel> stockLevelHistoryEntries = currentStockLevel.getStockLevelHistoryEntries();
			Assert.assertEquals("Wrong size of the history!", 1, stockLevelHistoryEntries.size());
			
			StockLevelHistoryEntryModel firstHistoryEntry = stockLevelHistoryEntries.get(0);
			Assert.assertEquals("Wrong first comment", "First stock import", firstHistoryEntry.getComment());
			Assert.assertEquals("Wrong first actual quantity", 100, firstHistoryEntry.getActual());
		} catch (Throwable e) {
			LOG.error("error", e);
			throw e;
		}
	}

	@Test
	public void shouldUpdateExistingStockLevel() throws Exception
	{
		try {
			//prepare
			//
			ProductModel product = productService.getProductForCode("1234567890");
			WarehouseModel warehouse = warehouseService.getWarehouseForCode("default");
			
			StockLevelModel stockLevel = new StockLevelModel();
			stockLevel.setAvailable(2);
			stockLevel.setReserved(0);
			stockLevel.setProductCode(product.getCode());
			stockLevel.setWarehouse(warehouse);
			
			StockLevelHistoryEntryModel historyEntry = new StockLevelHistoryEntryModel();
			historyEntry.setActual(100);
			historyEntry.setComment("First stock import");
			historyEntry.setReserved(0);
			historyEntry.setStockLevel(stockLevel);
			historyEntry.setUpdateDate(new Date());
			historyEntry.setUpdateType(StockLevelUpdateType.WAREHOUSE);

			ArrayList<StockLevelHistoryEntryModel> entries = new ArrayList<StockLevelHistoryEntryModel>();
			entries.add(historyEntry);
			
			stockLevel.setStockLevelHistoryEntries(entries);
			
			Mockito.when(stockService.getStockLevel(product, warehouse)).thenReturn(stockLevel);
			
			//execute controller
			//
			GlobalResponse result = controller.handle(getData("1234567890", "3210987654321", new Date(), 20, 10, 0), "sessionId");
			
			//assert result
			//
			StockLevelModel currentStockLevel = stockService.getStockLevel(product, warehouse);
			
			Assert.assertEquals("Unexpected code", "OK", result.getCode());
			Assert.assertNull("Unexpected message", result.getMessage());
			Assert.assertEquals("Not the same product code", product.getCode(), currentStockLevel.getProductCode());
			
			Assert.assertEquals("Available quantity is wrong", 20, currentStockLevel.getAvailable());
			
			List<StockLevelHistoryEntryModel> stockLevelHistoryEntries = currentStockLevel.getStockLevelHistoryEntries();
			Assert.assertEquals("Wrong size of the history!", 2, stockLevelHistoryEntries.size());
			
			StockLevelHistoryEntryModel firstHistoryEntry = stockLevelHistoryEntries.get(0);
			Assert.assertEquals("Wrong first comment", "First stock import", firstHistoryEntry.getComment());
			Assert.assertEquals("Wrong first actual quantity", 100, firstHistoryEntry.getActual());
			
			StockLevelHistoryEntryModel secondHistoryEntry = stockLevelHistoryEntries.get(1);
			Assert.assertEquals("Wrong updated comment", "Stock update", secondHistoryEntry.getComment());
			Assert.assertEquals("Wrong updated actual quantity", 20, secondHistoryEntry.getActual());
		} catch (Throwable e) {
			LOG.error("error", e);
			throw e;
		}
	}
	
	@Test
	public void shouldUpdateEAN() throws Exception
	{
		try {
			GlobalResponse result = controller.handle(getData("1234567890", "3210987654321", new Date(), 15, 10, 0), "sessionId");
			
			ProductModel product = productService.getProductForCode("1234567890");
			
			Assert.assertEquals("Unexpected code", "OK", result.getCode());
			Assert.assertNull("Unexpected message", result.getMessage());
			Assert.assertEquals("EAN was not updated!", "3210987654321", product.getEan());
		} catch (Throwable e) {
			LOG.error("error", e);
			throw e;
		}
	}
	
	@Test
	public void shouldNotUpdateEANWhenEquals() throws Exception
	{
		try {
			String ean = productService.getProductForCode("1234567890").getEan();
			
			GlobalResponse result = controller.handle(getData("1234567890", "1234567890123", new Date(), 15, 10, 0), "sessionId");
			
			ProductModel product = productService.getProductForCode("1234567890");
			
			Assert.assertEquals("Unexpected code", "OK", result.getCode());
			Assert.assertNull("Unexpected message", result.getMessage());
			Assert.assertEquals("EAN was updated and it should not be!", ean, product.getEan());
		} catch (Throwable e) {
			LOG.error("error", e);
			throw e;
		}
	}
	
	@Test
	public void shouldNotUpdateEANWhenEmpty() throws Exception
	{
		try {
			String ean = productService.getProductForCode("1234567890").getEan();
			
			GlobalResponse result = controller.handle(getData("1234567890", "", new Date(), 15, 10, 0), "sessionId");

			ProductModel product = productService.getProductForCode("1234567890");
			
			Assert.assertEquals("Unexpected code", "OK", result.getCode());
			Assert.assertNull("Unexpected message", result.getMessage());
			Assert.assertEquals("EAN was updated and it should not be!", ean, product.getEan());
		} catch (Throwable e) {
			LOG.error("error", e);
			throw e;
		}
	}
	
	@Test
	public void shouldUpdateDeliverySchedule() throws Exception
	{
		//TODO testar atualizar delivery schedule
	}
	
	@Test
	public void shouldUpdateOpenSuppliers() throws Exception
	{
		//TODO testar atualizar open supplier orders
	}
	
	private String getData(String itemId, String ean, Date deliveryDate, int quantity, int minStock, int openSupplierOrders) 
	{
		final ImportProductStockRequest request = new ImportProductStockRequest();
		request.setARTICLEITEMID(itemId);
		request.setEAN(ean);
		request.setACTIVE(true);
		request.setDELIVERYDATE(deliveryDate);
		request.setQUANTITY(quantity);
		request.setMINSTOCKQTY(minStock);
		request.setOPENSUPPLORDERS(openSupplierOrders);
		
		return PixiXMLUtil.getXML(request);
	}
}