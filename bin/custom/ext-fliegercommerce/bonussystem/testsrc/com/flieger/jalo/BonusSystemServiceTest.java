/**
 * 
 */
package com.flieger.jalo;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.jalo.CoreBasicDataCreator;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.product.UnitService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.SystemException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.flieger.model.user.BonusSystemConfigModel;
import com.flieger.model.user.BonusSystemDiscountModel;
import com.flieger.model.user.BonusSystemEntryModel;
import com.flieger.model.user.BonusSystemModel;
import com.flieger.services.BonusSystemService;


/**
 * @author franthescollymaneira
 * 
 */
public class BonusSystemServiceTest extends ServicelayerTransactionalTest
{
	@Resource
	private CatalogVersionService catalogVersionService;

	@Resource
	private UnitService unitService;

	@Resource
	private BonusSystemService bonusSystemService;

	@Resource
	private UserService userService;

	@Resource
	private ModelService modelService;

	private CustomerModel registeredCustomer;

	private CustomerModel anonymousCustomer;
	
	private BonusSystemConfigModel bsConfiguration;

	@Resource
	private ProductService productService;

	@Resource
	private CartService cartService;

	@Resource
	private CommerceCartService commerceCartService;

	@Resource
	private BaseSiteService baseSiteService;

	private static final String TEST_BASESITE_UID = "testSite";

	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		createDefaultUsers();

		userService.setCurrentUser(userService.getAdminUser());
		new CoreBasicDataCreator().createEssentialData(Collections.EMPTY_MAP, null);
		importCsv("/commerceservices/test/testCommerceCart.csv", "utf-8");

		baseSiteService.setCurrentBaseSite(baseSiteService.getBaseSiteForUID(TEST_BASESITE_UID), false);

		registeredCustomer = (CustomerModel) userService.getUserForUID("demo");

		anonymousCustomer = userService.getAnonymousUser();
		
		bsConfiguration = modelService.create(BonusSystemConfigModel.class);
		modelService.save(bsConfiguration);
	}

	@Test
	public void testCreateBonusSystem()
	{
		assertTrue("BS should be null", registeredCustomer.getBonusSystem() == null);

		bonusSystemService.createBonusSystem(registeredCustomer, bsConfiguration);

		assertTrue("BS should exist", registeredCustomer.getBonusSystem() != null);
	}

	@Test(expected = SystemException.class)
	public void testCreateBonusSystemWithException()
	{
		assertTrue("BS should be null", anonymousCustomer.getBonusSystem() == null);

		bonusSystemService.createBonusSystem(anonymousCustomer, bsConfiguration);
	}

	@Test
	public void testDeleteExistentBonusSystem()
	{
		testCreateBonusSystem();

		bonusSystemService.removeBonusSystem(registeredCustomer.getBonusSystem());

		assertTrue("BS should be null", registeredCustomer.getBonusSystem() == null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteInexistentBonusSystem()
	{
		assertTrue("BS should be null", registeredCustomer.getBonusSystem() == null);

		bonusSystemService.removeBonusSystem(registeredCustomer.getBonusSystem());
	}

	@Test
	public void testCreateBonusSystemEntry()
	{
		testCreateBonusSystem();

		final BonusSystemModel bonusSystem = registeredCustomer.getBonusSystem();

		assertTrue("BS should be empty", CollectionUtils.isEmpty(bonusSystem.getLogEntries()));

		final double points = 500;

		final BonusSystemEntryModel bsEntry = modelService.create(BonusSystemEntryModel.class);
		bsEntry.setDate(new Date());
		bsEntry.setDescription("description test");
		bsEntry.setPoints(points);
		bsEntry.setReference("reference bla bla bla");
		bsEntry.setType("registration");

		bonusSystemService.addBonusSystemEntry(bonusSystem, bsEntry);

		assertTrue("BS should not be empty", CollectionUtils.isNotEmpty(bonusSystem.getLogEntries()));
		assertEquals("BS Points Should be " + points, bonusSystem.getAvailablePoints(), points);
	}

	@Test
	public void testDeleteBonusSystemEntry()
	{
		testCreateBonusSystemEntry();

		final BonusSystemModel bonusSystem = registeredCustomer.getBonusSystem();

		assertTrue("BS should not be empty", CollectionUtils.isNotEmpty(bonusSystem.getLogEntries()));

		final BonusSystemEntryModel bsLog = bonusSystem.getLogEntries().get(0);

		bonusSystemService.removeBonusSystemEntry(bonusSystem, bsLog);

		assertTrue("BS should be empty", CollectionUtils.isEmpty(bonusSystem.getLogEntries()));
	}

	@Test
	public void applyDiscout() throws CommerceCartModificationException, CalculationException
	{
		testCreateBonusSystemEntry();

		userService.setCurrentUser(registeredCustomer);

		final BonusSystemModel bonusSystem = registeredCustomer.getBonusSystem();

		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		final ProductModel productModel = productService.getProductForCode(catalogVersionModel, "HW1210-3423");

		final CartModel cart = cartService.getSessionCart();

		commerceCartService.addToCart(cart, productModel, 1, productModel.getUnit(), false);

		Assert.assertEquals(1, cart.getEntries().size());

		bonusSystemService.applyDiscount(cart, bonusSystem, 10);

		System.out.println(bonusSystem.getAvailablePoints());


		final List<BonusSystemDiscountModel> appliedBSDiscounts = bonusSystemService.getAppliedBonusSystemDiscounts(cart);

		Assert.assertEquals(1, appliedBSDiscounts.size());

		final BonusSystemEntryModel bsLog = modelService.create(BonusSystemEntryModel.class);
		bsLog.setDate(new Date());
		bsLog.setDescription("invalidation");
		bsLog.setPoints(100 * (appliedBSDiscounts.iterator().next().getValue() * -1));
		bsLog.setReference("cart: " + cart.getCode());
		bsLog.setType("order");

		bonusSystemService.addBonusSystemEntry(bonusSystem, bsLog);

		System.out.println(bonusSystem.getAvailablePoints());

		for (final BonusSystemEntryModel log : bonusSystem.getLogEntries())
		{
			System.out.println(log.getDescription() + " - " + log.getPoints());
		}
	}
}