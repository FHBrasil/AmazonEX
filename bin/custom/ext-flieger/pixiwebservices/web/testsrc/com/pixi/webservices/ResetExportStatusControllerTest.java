package com.pixi.webservices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.pixi.core.services.PixiProductService;
import com.pixi.webservices.controller.product.ResetExportStatusController;
import com.pixi.webservices.jaxb.stock.response.ImportProductStockResponse;
import com.pixi.webservices.util.PixiXMLUtil;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.store.services.BaseStoreService;

@UnitTest
public class ResetExportStatusControllerTest 
{
	@Spy
	private ResetExportStatusController controller = new ResetExportStatusController();
	
	@Mock
	private PixiProductService pixiProductService;
	
	@Mock
	private BaseStoreService baseStoreService;
	
	@Mock
	private ModelService modelService;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(controller.getBaseStoreService()).thenReturn(baseStoreService);
		Mockito.when(controller.getModelService()).thenReturn(modelService);
		Mockito.when(controller.getPixiProductService()).thenReturn(pixiProductService);
		Mockito.when(controller.getJaxbContextFactory()).thenReturn(PixiXMLUtil.getFactory());
	}
	
	@Test
	public void shouldResetProductsByStore()  throws Exception 
	{
		//prepare the store products
		//
		List<String> productsByStore = new ArrayList<String>();
		productsByStore.add("byStore_1");
		productsByStore.add("byStore_2");
		productsByStore.add("byStore_3");
		productsByStore.add("byStore_4");
		productsByStore.add("byStore_5");
		Mockito.when(pixiProductService.findAllProductsByStore("babyartikel")).thenReturn(productsByStore);
		
		ImportProductStockResponse result = controller.handle(0 , "sessionID");

		//assert result
		//
		Assert.assertEquals("Unexpected session", "sessionID", result.getSessionID());
		Assert.assertEquals("Unexpected status", "SUCCESS", result.getSTATUS());
		Assert.assertEquals("Unexpected description", "The date was changed", result.getDESCRIPTION());
		Assert.assertNull("Export date should be null", result.getEXPORTDATE());
		
		//assert order of execution
		//
		InOrder reserByStoreOrder = Mockito.inOrder(pixiProductService);
		
		reserByStoreOrder.verify(pixiProductService).findAllProductsByStore("babyartikel");
		
		reserByStoreOrder.verify(pixiProductService).saveExportConfirmationDate("byStore_1", null, null);
		reserByStoreOrder.verify(pixiProductService).saveExportConfirmationDate("byStore_2", null, null);
		reserByStoreOrder.verify(pixiProductService).saveExportConfirmationDate("byStore_3", null, null);
		reserByStoreOrder.verify(pixiProductService).saveExportConfirmationDate("byStore_4", null, null);
		reserByStoreOrder.verify(pixiProductService).saveExportConfirmationDate("byStore_5", null, null);
		
		reserByStoreOrder.verify(pixiProductService).releaseProductsFromSession("sessionID");
	}
	
	@Test
	public void shouldResetProductsBySession()  throws Exception 
	{
		//prepare the session products
		//
		List<String> productsBySession = new ArrayList<String>();
		productsBySession.add("bySession_1");
		productsBySession.add("bySession_2");
		productsBySession.add("bySession_3");

		Mockito.when(pixiProductService.findExportedProductsBySessionID("sessionID")).thenReturn(productsBySession);
		
		long date = System.currentTimeMillis() / 1000;
		Date exportDate = new Date(date * 1000);

		final Date beforeCalculating = new Date(System.currentTimeMillis());
		ImportProductStockResponse result = controller.handle(date, "sessionID");
		final Date afterCalculating = new Date(beforeCalculating.getTime() + (System.currentTimeMillis() - beforeCalculating.getTime()) + 10000);

		//assert result
		//
		Assert.assertEquals("Unexpected session", "sessionID", result.getSessionID());
		Assert.assertEquals("Unexpected status", "SUCCESS", result.getSTATUS());
		Assert.assertEquals("Unexpected description", "The date was changed", result.getDESCRIPTION());
		Assert.assertEquals("Unexpected export date", exportDate, result.getEXPORTDATE());
		
		//assert order of execution
		//
		InOrder reserBySessionOrder = Mockito.inOrder(pixiProductService);
		
		reserBySessionOrder.verify(pixiProductService).findExportedProductsBySessionID("sessionID");
		
		final ArgumentMatcher<Date> matcher = new ArgumentMatcher<Date>()
		{
			@Override
			public boolean matches(final Object argument)
			{
				if(!(argument instanceof Date))
				{
					return false;
				}
				
				Date givenDate = (Date) argument;
				
				return givenDate.after(beforeCalculating) && (givenDate.equals(afterCalculating) || givenDate.before(afterCalculating));
			}
		};
		
		reserBySessionOrder.verify(pixiProductService).saveExportConfirmationDate(Mockito.eq("bySession_1"), Mockito.eq(exportDate), Mockito.argThat(matcher));
		reserBySessionOrder.verify(pixiProductService).saveExportConfirmationDate(Mockito.eq("bySession_2"), Mockito.eq(exportDate), Mockito.argThat(matcher));
		reserBySessionOrder.verify(pixiProductService).saveExportConfirmationDate(Mockito.eq("bySession_3"), Mockito.eq(exportDate), Mockito.argThat(matcher));
		
		reserBySessionOrder.verify(pixiProductService).releaseProductsFromSession("sessionID");
	}
	
	@Test
	public void shouldWarnNoProductsByStoreToReset() throws Exception
	{
		ImportProductStockResponse result = controller.handle(0 , "sessionID");
		
		//assert result
		//
		Assert.assertEquals("Unexpected session", "sessionID", result.getSessionID());
		Assert.assertEquals("Unexpected status", "SUCCESS", result.getSTATUS());
		Assert.assertEquals("Unexpected description", "No articles found for confirmation", result.getDESCRIPTION());
		Assert.assertNull("Export date should be null", result.getEXPORTDATE());
		
		//assert order of execution
		//
		InOrder reserByStoreOrder = Mockito.inOrder(pixiProductService);
		
		reserByStoreOrder.verify(pixiProductService).findAllProductsByStore("babyartikel");
		
		reserByStoreOrder.verify(pixiProductService, Mockito.never()).saveExportConfirmationDate(Mockito.anyString(), Mockito.any(Date.class), Mockito.any(Date.class));
		reserByStoreOrder.verify(pixiProductService, Mockito.never()).releaseProductsFromSession(Mockito.anyString());
	}
	
	@Test
	public void shouldWarnNoProductsBySessionToReset() throws Exception
	{
		long date = System.currentTimeMillis() / 1000;
		Date exportDate = new Date(date * 1000);

		ImportProductStockResponse result = controller.handle(date, "sessionID");
		
		//assert result
		//
		Assert.assertEquals("Unexpected session", "sessionID", result.getSessionID());
		Assert.assertEquals("Unexpected status", "SUCCESS", result.getSTATUS());
		Assert.assertEquals("Unexpected description", "No articles found for confirmation", result.getDESCRIPTION());
		Assert.assertEquals("Unexpected export date", exportDate, result.getEXPORTDATE());
		
		//assert order of execution
		//
		InOrder reserBySessionOrder = Mockito.inOrder(pixiProductService);
		
		reserBySessionOrder.verify(pixiProductService).findExportedProductsBySessionID("sessionID");
		
		reserBySessionOrder.verify(pixiProductService, Mockito.never()).saveExportConfirmationDate(Mockito.anyString(), Mockito.any(Date.class), Mockito.any(Date.class));
		reserBySessionOrder.verify(pixiProductService, Mockito.never()).releaseProductsFromSession(Mockito.anyString());
	}
}