package com.pixi.webservices;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import com.pixi.core.services.PixiOrderService;
import com.pixi.webservices.jaxb.adapter.BooleanAdapter;
import com.pixi.webservices.jaxb.adapter.DateAdapter;
import com.pixi.webservices.jaxb.adapter.StringAdapter;
import com.pixi.webservices.jaxb.factory.MoxyJaxbContextFactoryImpl;
import com.pixi.webservices.jaxb.order.export.Order;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.basecommerce.enums.OrderEntryStatus;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.order.DiscountService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

@IntegrationTest
public class ExportOrdersControllerTest extends ServicelayerTransactionalTest
{
	private static final Logger LOG = Logger.getLogger(ExportOrdersControllerTest.class.getName());
	
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
	
	@Resource
	private EnumerationService enumerationService;
	
	@Resource
	private TypeService typeService;
	
	private MoxyJaxbContextFactoryImpl jaxbContextFactory;
	
	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		createTestEnvironment();
		mock();
		
		List<Class> typeAdapters = new ArrayList<Class>();
		typeAdapters.add(DateAdapter.class);
		typeAdapters.add(StringAdapter.class);
		typeAdapters.add(BooleanAdapter.class);
		
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
		importCsv("/pixiwebservices/test/testOrderEnvironment.impex", "windows-1252");
	}

	private void printXML(Object obj) throws JAXBException
	{
		final JAXBContext jaxbContext = jaxbContextFactory.createJaxbContext(obj.getClass());
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		marshaller.marshal(obj, System.out);
	}
	
	@Test
	public void testFindEntryByEX() throws Exception
	{
//		Class<?> enumClass = OrderEntryStatus.class;
//		
//		EnumerationMetaTypeModel enumMetaTypeModel = this.typeService
//				.getEnumerationTypeForCode(OrderEntryStatus._TYPECODE);
//
//		Collection<? extends ItemModel> valueModels = enumMetaTypeModel.getValues();
//
//		for (ItemModel evm : valueModels) 
//		{
//			System.out.println(evm.getClass());
//		}
//		
//		//
		
		List<OrderEntryStatus> values = enumerationService.getEnumerationValues(OrderEntryStatus.class);
		
		for(OrderEntryStatus enumValue : values)
		{
			EnumerationValue ev = modelService.getSource(enumValue);
			String externalCode = (String) ev.getAttribute("externalCode");
			System.out.println(ev.getCode() + " - "+ externalCode);
		}
	}

	@Test
	@Ignore
	public void testPixiwebservices() throws JAXBException
	{
		LOG.info("testando o service de pedidos");
		
		List<OrderModel> orders = pixiOrderService.findNotExportedOrders();
		
		OrderModel orderToExport = orders.iterator().next();
		Order wsOrder = pixiOrderConverter.convert(orderToExport);
		
		printXML(wsOrder);
		
		//TODO testar direito
		Assert.assertNotNull("retorno nulo", wsOrder);
	}
}
