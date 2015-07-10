package br.hering.test.poc.order.split;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.OrderSplittingService;
import de.hybris.platform.ordersplitting.impl.DefaultOrderSplittingService;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.VendorModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.ordersplitting.strategy.SplittingStrategy;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.testframework.Transactional;

@Transactional
public class OrderSplittingTest extends ServicelayerTransactionalTest
{
	private final static Logger LOG = Logger.getLogger(OrderSplittingTest.class);
	
	@Resource
	private OrderSplittingService orderSplittingService;
	
	@Resource
	private ModelService modelService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private ProductService productService;
	
	private AbstractOrderModel order1;

	private DeliveryModeModel deliveryMode1;

	private Date namedDeliveryDate1;
	private Date namedDeliveryDate2;

	private VendorModel vendor;

	private ProductModel product;
	
	@Override
	@Before
	public void init()
	{
		super.init();
		try
		{
			createCoreData();
			createDefaultCatalog();
			createHardwareCatalog();
			createDefaultUsers();

			final WarehouseModel warehouse = modelService.create(WarehouseModel.class);
			warehouse.setDefault(Boolean.TRUE);
			warehouse.setCode("w1");
			vendor = modelService.create(VendorModel.class);
			vendor.setCode("v1");
			warehouse.setVendor(vendor);

			modelService.save(warehouse);

			order1 = modelService.create(CartModel.class);
			final CurrencyModel currency = new CurrencyModel();
			currency.setIsocode("EUR1");
			currency.setSymbol("EUR1");
			currency.setBase(Boolean.TRUE);
			currency.setActive(Boolean.TRUE);
			currency.setConversion(Double.valueOf(1));
			modelService.save(currency);
			order1.setCurrency(currency);
			order1.setNet(Boolean.TRUE);
			order1.setDate(new Date());
			order1.setUser(userService.getUser("ariel"));

			deliveryMode1 = modelService.create(DeliveryModeModel.class);
			deliveryMode1.setCode("deliveryMode1");

			namedDeliveryDate1 = new Date();
			namedDeliveryDate2 = new Date(new Date().getTime() + 10000);//shift in time

			final CatalogModel catalog = new CatalogModel();
			catalog.setId("catalog");

			final CatalogVersionModel catalogVersion = new CatalogVersionModel();
			catalogVersion.setCatalog(catalog);
			catalogVersion.setVersion("version");

			product = new ProductModel();
			product.setCode("Product1");
			product.setCatalogVersion(catalogVersion);

			modelService.save(product);

		}
		catch (final Exception e)
		{
			throw new RuntimeException(e); //NOPMD
		}
	}
	
	@Test
	public void tesCartSplit() throws Exception
	{
		LOG.debug("Class under test : " + orderSplittingService.getClass().getName());
		
		final List<AbstractOrderEntryModel> orderEntryList = new ArrayList<AbstractOrderEntryModel>();

		orderEntryList.add(getNewOrderEntryModel(deliveryMode1, order1, namedDeliveryDate1));
		orderEntryList.add(getNewOrderEntryModel(deliveryMode1, order1, namedDeliveryDate1));

		orderEntryList.add(getNewOrderEntryModel(deliveryMode1, order1, namedDeliveryDate1));
		orderEntryList.add(getNewOrderEntryModel(deliveryMode1, order1, namedDeliveryDate1));

		logTestEntries(orderEntryList);
		final List<ConsignmentModel> consignmentModelList = orderSplittingService.splitOrderForConsignmentNotPersist(order1,
				orderEntryList);

		Method method = ReflectionUtils.findMethod(DefaultOrderSplittingService.class, "getStrategiesList");
		List<SplittingStrategy> strategiesList = (List<SplittingStrategy>) method.invoke(orderSplittingService, null);
		
		assertEquals("Split should return 1 consignment", 1, consignmentModelList.size());

		for (final ConsignmentModel consignment : consignmentModelList)
		{
			assertEquals("Split should return 1 consignmentEntries for consignment " + consignmentModelList.indexOf(consignment), 4,
					consignment.getConsignmentEntries().size());
		}
		
	}
	
	private AbstractOrderEntryModel getNewOrderEntryModel(final DeliveryModeModel deliveryMode, final AbstractOrderModel order,
			final Date NamedDeliveryDate)
	{
		final AbstractOrderEntryModel orderEntryModel = modelService.create(CartEntryModel.class);
		orderEntryModel.setDeliveryMode(deliveryMode);
		orderEntryModel.setOrder(order);
		orderEntryModel.setNamedDeliveryDate(NamedDeliveryDate);

		orderEntryModel.setChosenVendor(vendor);
		orderEntryModel.setProduct(product);
		orderEntryModel.setUnit(productService.getUnit("pieces"));
		orderEntryModel.setQuantity(Long.valueOf(10));
		modelService.save(orderEntryModel);
		
		return orderEntryModel;
	}


	private void logTestEntries(final List<AbstractOrderEntryModel> orderEntryList)
	{
		if (LOG.isDebugEnabled())
		{
			for (final AbstractOrderEntryModel currentOrderEntry : orderEntryList)
			{
				LOG.debug("OrderEntry [" + currentOrderEntry.getPk() + "] with delivery mode (" + currentOrderEntry.getDeliveryMode()
						+ ") and delivery date (" + currentOrderEntry.getNamedDeliveryDate() + ")");
				currentOrderEntry.setNamedDeliveryDate(namedDeliveryDate2);
				modelService.save(currentOrderEntry);
			}
		}
	}
}