package com.pixi.webservices;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.pixi.core.services.PixiOrderEntryService;
import com.pixi.webservices.controller.order.ImportOrderStatusController;
import com.pixi.webservices.jaxb.orderstatus.request.ImportOrderStatusRequest;
import com.pixi.webservices.jaxb.response.GlobalResponse;
import com.pixi.webservices.util.PixiXMLUtil;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.store.services.BaseStoreService;

@UnitTest
public class ImportOrderStatusControllerTest 
{
	private static final Logger LOG = Logger.getLogger(ImportOrderStatusControllerTest.class);
	
	@Mock
	private BaseStoreService baseStoreService;
	
	@Mock
	private ModelService modelService;
	
	@Mock
	private PixiOrderEntryService pixiOrderEntryService;
	
	@Spy
	private ImportOrderStatusController controller = new ImportOrderStatusController();
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(controller.getBaseStoreService()).thenReturn(baseStoreService);
		Mockito.when(controller.getModelService()).thenReturn(modelService);
		Mockito.when(controller.getJaxbContextFactory()).thenReturn(PixiXMLUtil.getFactory());
		Mockito.when(controller.getPixiOrderEntryService()).thenReturn(pixiOrderEntryService);
		
		final OrderEntryModel entry = new OrderEntryModel();
		
		Mockito.when(modelService.get(PK.parse("1234567890"))).thenReturn(entry);
	}
	
	@Test
	public void testUpdateOrderEntryStatus() throws Exception
	{
		try {
			GlobalResponse result = controller.handle(getData("1234567890", "status"), "sessionID");
			
			//TODO falta testar se o status mudou mesmo
			
			Assert.assertEquals("Unexpected code", "OK", result.getCode());
			Assert.assertNull("Unexpected message", result.getMessage());
		} catch (Throwable e) {
			LOG.error("error", e);
			throw e;
		}
	}
	
	@Test
	public void shouldWarnOrderEntryNotFound() throws Exception
	{
		try {
			GlobalResponse result = controller.handle(getData("0987654321", "status"), "sessionID");
			
			Assert.assertEquals("Unexpected code", "FAILURE", result.getCode());
			Assert.assertEquals("Unexpected message", "Entry not found for PK: 0987654321", result.getMessage());
		} catch (Throwable e) {
			LOG.error("error", e);
			throw e;
		}
	}
	
	public void testOrderStatus()
	{
		//TODO falta testar, inclui pixi api
	}
	
	public void testBonusSystem()
	{
		//TODO falta model de bs
	}
	
	private String getData(String entryID, String status) 
	{
		final ImportOrderStatusRequest request = new ImportOrderStatusRequest();
		request.setLINEITEMID(entryID);
		request.setSTATUS(status);
		request.setTRACKINGID("1234567890");
		request.setQUANTITY(1);
		request.setPRICEAMOUNT(BigDecimal.TEN);
		request.setITEMNOTE("item note");
		request.setFULLPRICE(BigDecimal.TEN);
		request.setDISCOUNTVALUE(BigDecimal.ZERO);
		
		return PixiXMLUtil.getXML(request);
	}
}