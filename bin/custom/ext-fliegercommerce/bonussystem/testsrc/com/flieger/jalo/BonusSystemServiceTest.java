/**
 * 
 */
package com.flieger.jalo;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.price.DiscountModel;
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

import com.flieger.model.user.BonusSystemLogModel;
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
		//		createHardwareCatalog();
		createDefaultUsers();

		userService.setCurrentUser(userService.getAdminUser());
		new CoreBasicDataCreator().createEssentialData(Collections.EMPTY_MAP, null);
		importCsv("/commerceservices/test/testCommerceCart.csv", "utf-8");

		baseSiteService.setCurrentBaseSite(baseSiteService.getBaseSiteForUID(TEST_BASESITE_UID), false);

		registeredCustomer = (CustomerModel) userService.getUserForUID("demo");

		anonymousCustomer = userService.getAnonymousUser();
	}

	@Test
	public void testCreateBonusSystem()
	{
		assertTrue("BS should be null", registeredCustomer.getBonusSystem() == null);

		bonusSystemService.createBonusSystem(registeredCustomer);

		assertTrue("BS should exist", registeredCustomer.getBonusSystem() != null);
	}

	@Test(expected = SystemException.class)
	public void testCreateBonusSystemWithException()
	{
		assertTrue("BS should be null", anonymousCustomer.getBonusSystem() == null);

		bonusSystemService.createBonusSystem(anonymousCustomer);
	}

	@Test
	public void testDeleteExistentBonusSystem()
	{
		testCreateBonusSystem();

		bonusSystemService.removeBonusSystem(registeredCustomer);

		assertTrue("BS should be null", registeredCustomer.getBonusSystem() == null);
	}

	@Test(expected = SystemException.class)
	public void testDeleteInexistentBonusSystem()
	{
		assertTrue("BS should be null", registeredCustomer.getBonusSystem() == null);

		bonusSystemService.removeBonusSystem(registeredCustomer);
	}

	@Test
	public void testCreateBonusSystemLog()
	{
		testCreateBonusSystem();

		final BonusSystemModel bonusSystem = registeredCustomer.getBonusSystem();

		assertTrue("BS should be empty", CollectionUtils.isEmpty(bonusSystem.getLog()));

		final double points = 500;

		final BonusSystemLogModel bsLog = modelService.create(BonusSystemLogModel.class);
		bsLog.setDate(new Date());
		bsLog.setDescription("description test");
		bsLog.setPoints(points);
		bsLog.setReference("reference bla bla bla");
		bsLog.setType("registration");

		bonusSystemService.addBonusSystemLog(bonusSystem, bsLog);

		assertTrue("BS should not be empty", CollectionUtils.isNotEmpty(bonusSystem.getLog()));
		assertEquals("BS Points Should be " + points, bonusSystem.getPoints(), points);
	}

	@Test
	public void testDeleteBonusSystemLog()
	{
		testCreateBonusSystemLog();

		final BonusSystemModel bonusSystem = registeredCustomer.getBonusSystem();

		assertTrue("BS should not be empty", CollectionUtils.isNotEmpty(bonusSystem.getLog()));

		final BonusSystemLogModel bsLog = bonusSystem.getLog().get(0);

		bonusSystemService.removeBonusSystemLog(bonusSystem, bsLog);

		assertTrue("BS should be empty", CollectionUtils.isEmpty(bonusSystem.getLog()));
	}

	@Test
	public void applyDiscout() throws CommerceCartModificationException, CalculationException
	{
		testCreateBonusSystemLog();

		userService.setCurrentUser(registeredCustomer);

		final BonusSystemModel bonusSystem = registeredCustomer.getBonusSystem();

		final ProductModel productModel = productService.getProductForCode("HW1210-3423");

		final CartModel cart = cartService.getSessionCart();

		commerceCartService.addToCart(cart, productModel, 1, productModel.getUnit(), false);

		Assert.assertEquals(1, cart.getEntries().size());

		bonusSystemService.applyDiscount(cart, bonusSystem);

		System.out.println(bonusSystem.getPoints());


		final List<DiscountModel> appliedBSDiscounts = bonusSystemService.getAppliedBSDiscounts(cart);

		Assert.assertEquals(1, appliedBSDiscounts.size());

		final BonusSystemLogModel bsLog = modelService.create(BonusSystemLogModel.class);
		bsLog.setDate(new Date());
		bsLog.setDescription("invalidation");
		bsLog.setPoints(100 * (appliedBSDiscounts.iterator().next().getValue() * -1));
		bsLog.setReference("cart: " + cart.getCode());
		bsLog.setType("order");

		bonusSystemService.addBonusSystemLog(bonusSystem, bsLog);

		System.out.println(bonusSystem.getPoints());

		for (final BonusSystemLogModel log : bonusSystem.getLog())
		{
			System.out.println(log.getDescription() + " - " + log.getPoints());
		}
	}
}