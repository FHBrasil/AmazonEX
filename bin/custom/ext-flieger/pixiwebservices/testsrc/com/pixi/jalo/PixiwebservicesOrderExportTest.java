package com.pixi.jalo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pixi.core.services.PixiOrderService;
import com.pixi.webservices.jaxb.adapter.DateAdapter;
import com.pixi.webservices.jaxb.adapter.StringAdapter;
import com.pixi.webservices.jaxb.factory.MoxyJaxbContextFactoryImpl;
import com.pixi.webservices.jaxb.order.export.Order;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

@IntegrationTest
public class PixiwebservicesOrderExportTest extends ServicelayerTransactionalTest
{
	private static final Logger LOG = Logger.getLogger(PixiwebservicesOrderExportTest.class.getName());
	
	//private final ExportOrdersController controller = Mockito.spy(new ExportOrdersController());
	
	@Resource
	private Converter<OrderModel, Order> pixiOrderConverter;
	
	@Resource
	private PixiOrderService pixiOrderService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private CommonI18NService commonI18NService;

	@Resource
	private ModelService modelService;
	
	private MoxyJaxbContextFactoryImpl jaxbContextFactory;
	
	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		
		createOrders();
		
		List<Class> typeAdapters = new ArrayList<Class>();
		typeAdapters.add(DateAdapter.class);
		typeAdapters.add(StringAdapter.class);
		
		jaxbContextFactory = new MoxyJaxbContextFactoryImpl();
		jaxbContextFactory.setWrapCollections(false);
		jaxbContextFactory.setTypeAdapters(typeAdapters );
	}

	private void createOrders() 
	{
		OrderModel testOrder = modelService.create(OrderModel.class);
		UserModel user = userService.getCurrentUser();
		CurrencyModel curr = commonI18NService.getBaseCurrency();
		
		testOrder.setCode("order calc test");
		testOrder.setUser(user);
		testOrder.setCurrency(curr);
		testOrder.setDate(new Date());
		testOrder.setNet(Boolean.FALSE);
		
		modelService.save(testOrder);
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
