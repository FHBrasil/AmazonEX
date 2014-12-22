package com.pixi.jalo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.pixi.core.services.PixiOrderService;
import com.pixi.webservices.jaxb.adapter.DateAdapter;
import com.pixi.webservices.jaxb.adapter.StringAdapter;
import com.pixi.webservices.jaxb.factory.MoxyJaxbContextFactoryImpl;
import com.pixi.webservices.jaxb.order.export.Order;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.order.price.DiscountModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.jalo.order.OrderEntry;
import de.hybris.platform.order.DiscountService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

@IntegrationTest
public class PixiwebservicesOrderExportTest extends ServicelayerTransactionalTest
{
	private static final Logger LOG = Logger.getLogger(PixiwebservicesOrderExportTest.class.getName());
	
	//private final ExportOrdersController controller = Mockito.spy(new ExportOrdersController());
	
	@Resource
	private Converter<OrderModel, Order> pixiOrderConverter;
	
	private PixiOrderService pixiOrderService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private CommonI18NService commonI18NService;
	
	@Resource
	private I18NService i18nService;

	@Resource
	private ModelService modelService;

	@Resource
	private CatalogVersionService catalogVersionService;
	
	@Resource
	private BaseSiteService baseSiteService;
	
	@Resource
	private DiscountService discountService;
	
	private MoxyJaxbContextFactoryImpl jaxbContextFactory;
	
	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		createTestEnvironment();
		mock();
		//createOrders();
		
		List<Class> typeAdapters = new ArrayList<Class>();
		typeAdapters.add(DateAdapter.class);
		typeAdapters.add(StringAdapter.class);
		
		jaxbContextFactory = new MoxyJaxbContextFactoryImpl();
		jaxbContextFactory.setWrapCollections(false);
		jaxbContextFactory.setTypeAdapters(typeAdapters );
	}

	private void mock() 
	{
		pixiOrderService = Mockito.mock(PixiOrderService.class);
		
		List<OrderModel> orders = new ArrayList<OrderModel>();
		
		OrderModel sample = new OrderModel();
		sample.setCode("acceptanceTestOrder1");
		
		OrderModel result = modelService.getByExample(sample);
		
		orders.add(result);
		
		Mockito.when(pixiOrderService.findNotExportedOrders()).thenReturn(orders);
	}

	private void createTestEnvironment() throws ImpExException 
	{
//		final BaseSiteModel baseSiteForUID = baseSiteService.getBaseSiteForUID("babyartikel");
//		baseSiteService.setCurrentBaseSite(baseSiteForUID, false);
//		
//		userService.setCurrentUser(userService.getAnonymousUser());
//		commonI18NService.setCurrentLanguage(commonI18NService.getLanguage("de"));
//		commonI18NService.setCurrentCurrency(commonI18NService.getCurrency("EUR"));
		
		//catalogVersionService.setSessionCatalogVersion("apparelProductCatalog", "Online");
		
		importCsv("/pixiwebservices/test/testOrderEnvironment.csv", "windows-1252");
	}

	private void printXML(Object obj) throws JAXBException
	{
		final JAXBContext jaxbContext = jaxbContextFactory.createJaxbContext(obj.getClass());
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		marshaller.marshal(obj, System.out);
	}

	@Test
	public void testPixiwebservices() throws JAXBException
	{
		LOG.info("testando o service");
		//Order order = controller.handle("fake_session");
		
		List<OrderModel> orders = pixiOrderService.findNotExportedOrders();
		
		OrderModel orderToExport = orders.iterator().next();
		Order wsOrder = pixiOrderConverter.convert(orderToExport);
		
		printXML(wsOrder);
		
		Assert.assertNotNull("retorno nulo", wsOrder);
	}
}
