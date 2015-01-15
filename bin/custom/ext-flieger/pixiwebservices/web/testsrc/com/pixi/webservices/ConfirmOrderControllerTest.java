package com.pixi.webservices;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.pixi.webservices.controller.order.ConfirmOrderController;
import com.pixi.webservices.jaxb.orderstatus.response.ImportOrderStatusResponse;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.enums.ExportStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

@UnitTest
public class ConfirmOrderControllerTest
{
	@Mock
	private BaseStoreService baseStoreService;
	
	@Mock
	private CustomerAccountService customerAccountService;
	
	@Mock
	private ModelService modelService;
	
	@Spy
	private ConfirmOrderController controller = new ConfirmOrderController();
	
	private OrderModel order;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		
		order = new OrderModel();
		order.setExportStatus(ExportStatus.NOTEXPORTED);
		
		BaseStoreModel baseStore = new BaseStoreModel();

		Mockito.when(baseStoreService.getCurrentBaseStore()).thenReturn(baseStore);
		Mockito.when(customerAccountService.getOrderForCode("existingOrder", baseStore)).thenReturn(order);
		
		Mockito.when(controller.getBaseStoreService()).thenReturn(baseStoreService);
		Mockito.when(controller.getCustomerAccountService()).thenReturn(customerAccountService);
		Mockito.when(controller.getModelService()).thenReturn(modelService);
	}
	
	@Test
	public void shouldConfirmExistingOrder()
	{
		ImportOrderStatusResponse result = controller.handle("existingOrder", "sessionID");
		
		Assert.assertNotNull("No result!", result);
		Assert.assertEquals("Unexpected order", "existingOrder", result.getORDERID());
		Assert.assertEquals("Unexpected sessionID", "sessionID", result.getSessionID());
		Assert.assertEquals("Unexpected status", "SUCCESS", result.getSTATUS());
		Assert.assertNull("Unexpected descripton", result.getDESCRIPTION());
		Assert.assertEquals("Wrong order export status", ExportStatus.EXPORTED, order.getExportStatus());
	}
	
	@Test
	public void shouldWarnOrderNotFound()
	{
		ImportOrderStatusResponse result = controller.handle("nonExistingOrder", "sessionID");
		
		Assert.assertNotNull("No result!", result);
		Assert.assertEquals("Unexpected order", "nonExistingOrder", result.getORDERID());
		Assert.assertEquals("Unexpected sessionID", "sessionID", result.getSessionID());
		Assert.assertEquals("Unexpected status", "ERROR", result.getSTATUS());
		Assert.assertEquals("Internal Error while updating exported status", result.getDESCRIPTION());
		Assert.assertEquals("Wrong order export status", ExportStatus.NOTEXPORTED, order.getExportStatus());
	}
}