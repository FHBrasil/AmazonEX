/**
 * (c) copyright 2013 by getit GmbH
 */
package de.getit.hybris.klarna.payment.core.service.impl;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.price.TaxModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.util.TaxValue;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.klarna.api.KlarnaException;

import de.getit.hybris.klarna.payment.AbstractIntegrationTest;
import de.getit.hybris.klarna.payment.constants.KlarnapaymentConstants;
import de.getit.hybris.klarna.payment.constants.KlarnapaymentTestConstants;
import de.getit.hybris.klarna.payment.core.dao.KlarnaDAO;
import de.getit.hybris.klarna.payment.core.exception.GetitKlarnaException;
import de.getit.hybris.klarna.payment.core.service.KlarnaPClassService;
import de.getit.hybris.klarna.payment.core.service.KlarnaValidationService;
import de.getit.hybris.klarna.payment.core.strategy.impl.GetitKlarnaGetBaseParameterStrategy;
import de.getit.hybris.klarna.payment.model.KlarnaCountryConfigModel;

/**
 * This Test checks if the ValidationService works right. The test will be run for every supported Country
 * (DE,AT,NL,FI,NO,SE,DK).
 * 
 * @author Thomas Pflug, getit GmbH
 * @author Sonja Bouwers, getit GmbH
 */
@IntegrationTest
public class KlarnaValidationServiceTest extends AbstractIntegrationTest {

	private KlarnaValidationService klarnaValidationService;
	private KlarnaDAO klarnaDAO;

	@Before
	public void setUp() throws Exception {

		klarnaValidationService = (KlarnaValidationService) getBean(KlarnaValidationService.BEAN_NAME);

		createDefaultCatalog();
		importCsv(KlarnapaymentConstants.KLARNA_CART_EURO_TEST_DATA_PATH, "utf-8");
		LOG.info("JaloSession-Catalogversion  --> "
			+ JaloSession.getCurrentSession().getSessionContext().getAttribute("catalogversions"));

		klarnaDAO = (KlarnaDAO) getBean("klarnaDAO");

	}

	@Override
	@After
	public void tearDown() throws Exception {
		modelService.detachAll();
	}

	@Test
	public void testValidationServiceForCartDE() throws GetitKlarnaException, CalculationException, KlarnaException {
		final String countryCode = "DE";
		prepareValidationServiceForCart(countryCode);
	}

	@Test
	public void testValidationServiceForProductDE() throws GetitKlarnaException, CalculationException, KlarnaException {
		final String countryCode = "DE";
		prepareValidationServiceForProduct(countryCode, new Double(101.0d));
	}

	@Test
	public void testValidationServiceForCartDK() throws ImpExException, GetitKlarnaException, CalculationException, KlarnaException {
		final String countryCode = "DK";
		importCsv("/klarnapayment/test/klarnaCartDKKTestData.csv", "utf-8");
		prepareValidationServiceForCart(countryCode);
	}

	@Test
	public void testValidationServiceForProductDK() throws ImpExException, GetitKlarnaException, CalculationException, KlarnaException {
		final String countryCode = "DK";
		importCsv("/klarnapayment/test/klarnaCartDKKTestData.csv", "utf-8");
		prepareValidationServiceForProduct(countryCode, new Double(1001.0d));
	}

	@Test
	public void testValidationServiceForCartNL() throws GetitKlarnaException, CalculationException, KlarnaException {
		final String countryCode = "NL";
		prepareValidationServiceForCart(countryCode);
	}

	@Test
	public void testValidationServiceForProductNL() throws GetitKlarnaException, CalculationException, KlarnaException {
		final String countryCode = "NL";
		prepareValidationServiceForProduct(countryCode, new Double(101.0d));
	}

	@Test
	public void testValidationServiceForCartSE() throws ImpExException, GetitKlarnaException, CalculationException, KlarnaException {
		final String countryCode = "SE";
		importCsv("/klarnapayment/test/klarnaCartSEKTestData.csv", "utf-8");
		prepareValidationServiceForCart(countryCode);
	}

	@Test
	public void testValidationServiceForProductSE() throws ImpExException, GetitKlarnaException, CalculationException, KlarnaException {
		final String countryCode = "SE";
		importCsv("/klarnapayment/test/klarnaCartSEKTestData.csv", "utf-8");
		prepareValidationServiceForProduct(countryCode, new Double(1001.0d));
	}

	@Test
	public void testValidationServicForCartNO() throws ImpExException, GetitKlarnaException, CalculationException, KlarnaException {
		final String countryCode = "NO";
		importCsv("/klarnapayment/test/klarnaCartNOKTestData.csv", "utf-8");
		prepareValidationServiceForCart(countryCode);
	}

	@Test
	public void testValidationServiceForProductNO() throws ImpExException, GetitKlarnaException, CalculationException, KlarnaException {
		final String countryCode = "NO";
		importCsv("/klarnapayment/test/klarnaCartNOKTestData.csv", "utf-8");
		prepareValidationServiceForProduct(countryCode, new Double(1001.0d));
	}

	@Test
	public void testValidationServiceForCartFI() throws GetitKlarnaException, CalculationException, KlarnaException {
		final String countryCode = "FI";
		prepareValidationServiceForCart(countryCode);
	}

	@Test
	public void testValidationServiceForProductFI() throws GetitKlarnaException, CalculationException, KlarnaException {
		final String countryCode = "FI";
		prepareValidationServiceForProduct(countryCode, new Double(101.0d));
	}

	@Test
	public void testValidationServiceForCartAT() throws GetitKlarnaException, CalculationException, KlarnaException {
		final String countryCode = "AT";
		prepareValidationServiceForCart(countryCode);
	}

	@Test
	public void testValidationServiceForProductAT() throws GetitKlarnaException, CalculationException, KlarnaException {
		final String countryCode = "AT";
		prepareValidationServiceForProduct(countryCode, new Double(101.0d));
	}

	/**
	 * Creates a {@link UserModel}, {@link BaseStoreModel}, {@link CountryModel}, {@link CartModel} and
	 * {@link ProductModel} for a assigned countryCode and runs the ValidationService for Carts.
	 * 
	 * @param countryCode
	 *            {@link String}
	 * @throws KlarnaException
	 * @throws GetitKlarnaException
	 * @throws CalculationException
	 */
	private void prepareValidationServiceForCart(final String countryCode) throws CalculationException, GetitKlarnaException, KlarnaException {
		final UserModel user = getUser("KlarnaPaymentTestUser_" + countryCode.toUpperCase() + "_Approved@test.com");
		userService.setCurrentUser(user);
		assertNotNull("Test Customer ist null.", userService.getCurrentUser());

		final BaseStoreModel baseStore = getBaseStore("klarna-" + countryCode.toLowerCase());
		sessionService.setAttribute("currentSite", baseStore.getCmsSites().iterator().next());
		sessionService.setAttribute(GetitKlarnaGetBaseParameterStrategy.GETITBASESTORESESSIONNAME, baseStore);

		final CountryModel country = getCountry(countryCode);
		assertNotNull("Test Country is null.", country);

		final KlarnaCountryConfigModel klarnaCountryConfig = klarnaDAO.getKlarnaCountryConfig4Country(country);
		commonI18NService.setCurrentCurrency(klarnaCountryConfig.getCurrency());
		commonI18NService.setCurrentLanguage(klarnaCountryConfig.getLanguage());

		commonI18NService.setCurrentCurrency(baseStore.getDefaultCurrency());
		commonI18NService.setCurrentLanguage(baseStore.getDefaultLanguage());

		// setup cart
		final CartModel cart = createCart(user);
		cartService.setSessionCart(cart);
		assertNotNull("Test Cart is null", cartService.getSessionCart());

		final ProductModel product = getProduct("product_01_" + country.getIsocode().toUpperCase());
		assertNotNull(product);
		addProductToCart(product, country.getIsocode().toUpperCase());

		final KlarnaPClassService pClassService = (KlarnaPClassService) Registry.getApplicationContext().getBean("klarnaPClassService");
		pClassService.fetchPClasses(baseStore, country, KlarnapaymentTestConstants.CLIENT_IP);

		validateResultValuesForCart(baseStore, country, cart);
	}

	/**
	 * Creates a {@link UserModel}, {@link BaseStoreModel}, {@link CountryModel} and {@link ProductModel} for a assigned
	 * countryCode and productPrice and runs the ValidationService for Products.
	 * 
	 * @param countryCode
	 *            {@link String}
	 * @param productPrice
	 *            {@link Double}
	 * @throws KlarnaException
	 * @throws GetitKlarnaException
	 * @throws CalculationException
	 */
	private void prepareValidationServiceForProduct(final String countryCode, final Double productPrice) throws CalculationException, GetitKlarnaException, KlarnaException {

		final UserModel user = getUser("KlarnaPaymentTestUser_" + countryCode.toUpperCase() + "_Approved@test.com");
		userService.setCurrentUser(user);
		assertNotNull("Test Customer ist null.", userService.getCurrentUser());

		final BaseStoreModel baseStore = getBaseStore("klarna-" + countryCode.toLowerCase());
		sessionService.setAttribute("currentSite", baseStore.getCmsSites().iterator().next());
		sessionService.setAttribute(GetitKlarnaGetBaseParameterStrategy.GETITBASESTORESESSIONNAME, baseStore);

		final CountryModel country = getCountry(countryCode);
		assertNotNull("Test Country is null.", country);

		commonI18NService.setCurrentCurrency(baseStore.getDefaultCurrency());
		commonI18NService.setCurrentLanguage(baseStore.getDefaultLanguage());
		final ProductModel product = getProduct("product_01_" + country.getIsocode().toUpperCase());
		assertNotNull(product);
		
		final Collection<PriceRowModel> priceRowModels = product.getEurope1Prices();
		final PriceRowModel priceRowModel = priceRowModels.iterator().next();
		priceRowModel.setPrice(productPrice);
		modelService.saveAll(priceRowModel, product);

		final KlarnaPClassService pClassService = (KlarnaPClassService) Registry.getApplicationContext().getBean("klarnaPClassService");
		pClassService.fetchPClasses(baseStore, country, KlarnapaymentTestConstants.CLIENT_IP);

		validateResultValuesForProduct(baseStore, country, product);
	}

	/**
	 * Runs the validation service for the assigned {@link BaseStoreModel}, {@link CountryModel} and {@link CartModel}
	 * for all Klarna-Paymentmodes (Invoice, Account and Campaign).
	 * 
	 * @param pBaseStore
	 *            {@link BaseStoreModel}
	 * @param pCountry
	 *            {@link CountryModel}
	 * @param pCart
	 *            {@link CartModel}
	 * @throws GetitKlarnaException
	 */
	private void validateResultValuesForCart(final BaseStoreModel pBaseStore, final CountryModel pCountry, final CartModel pCart) throws GetitKlarnaException {

		pCart.setPaymentMode(getPaymentMode(KlarnapaymentConstants.KlarnaPaymentModes.KLARNA_INVOICE));
		assertTrue("Klarna Payment Mode Invoice must be allowed for country: " + pCountry.getIsocode(), klarnaValidationService.isInvoiceAllowed());

		pCart.setPaymentMode(getPaymentMode(KlarnapaymentConstants.KlarnaPaymentModes.KLARNA_ACCOUNT));
		if (StringUtils.equalsIgnoreCase(pCountry.getIsocode(), "AT")) {
			assertFalse("Klarna Payment Mode Account must not be allowed for country: " + pCountry.getIsocode(), klarnaValidationService.isAccountAllowed(pCart));
		} else {
			assertTrue("Klarna Payment Mode Account must be allowed for country: " + pCountry.getIsocode(), klarnaValidationService.isAccountAllowed(pCart));
		}

		pCart.setPaymentMode(getPaymentMode(KlarnapaymentConstants.KlarnaPaymentModes.KLARNA_CAMPAIGN));
		if (StringUtils.equalsIgnoreCase(pCountry.getIsocode(), "AT") || StringUtils.equalsIgnoreCase(pCountry.getIsocode(), "DK")
			|| StringUtils.equalsIgnoreCase(pCountry.getIsocode(), "NL") || StringUtils.equalsIgnoreCase(pCountry.getIsocode(), "DE")) {
			assertFalse("Klarna Payment Mode Campaign must not be allowed for country: " + pCountry.getIsocode(), klarnaValidationService.isCampaignAllowed(pCart));
		} else {
			assertTrue("Klarna Payment Mode Campaign must be allowed for country: " + pCountry.getIsocode(), klarnaValidationService.isCampaignAllowed(pCart));
		}
	}

	/**
	 * Runs the validation service for the assigned {@link BaseStoreModel}, {@link CountryModel} and
	 * {@link ProductModel} for all Klarna-Paymentmodes (Invoice, Account and Campaign).
	 * 
	 * @param pBaseStore
	 *            {@link BaseStoreModel}
	 * @param pCountry
	 *            {@link CountryModel}
	 * @param pProduct
	 *            {@link ProductModel}
	 * @throws GetitKlarnaException
	 */
	private void validateResultValuesForProduct(final BaseStoreModel pBaseStore, final CountryModel pCountry, final ProductModel pProduct) throws GetitKlarnaException {

		if (StringUtils.equalsIgnoreCase(pCountry.getIsocode(), "AT")) {
			assertFalse("Klarna Payment Mode Account must not be allowed for country: " + pCountry.getIsocode(),
				klarnaValidationService.isAccountAllowed(pProduct));
		} else {
			assertTrue("Klarna Payment Mode Account must be allowed for country: " + pCountry.getIsocode(),
				klarnaValidationService.isAccountAllowed(pProduct));
		}

		if (StringUtils.equalsIgnoreCase(pCountry.getIsocode(), "AT") ||
			StringUtils.equalsIgnoreCase(pCountry.getIsocode(), "DK")
			|| StringUtils.equalsIgnoreCase(pCountry.getIsocode(), "NL") ||
			StringUtils.equalsIgnoreCase(pCountry.getIsocode(), "DE")) {
			assertFalse("Klarna Payment Mode Campaign must not be allowed for country: " + pCountry.getIsocode(),
				klarnaValidationService.isCampaignAllowed(pProduct));
		} else {
			assertTrue("Klarna Payment Mode Campaign must be allowed for country: " + pCountry.getIsocode(),
				klarnaValidationService.isCampaignAllowed(pProduct));
		}
	}

	/**
	 * Creates a Cart for the given {@link UserModel}.
	 * 
	 * @param user
	 *            {@link UserModel}
	 * @return CartModel
	 */
	private CartModel createCart(final UserModel user) {

		final CartModel cart = modelService.create(CartModel.class);
		assertNotNull("The cart must not be null.", cart);
		cart.setUser(userService.getCurrentUser());
		cart.setCurrency(commonI18NService.getCurrentCurrency());
		cart.setDate(new Date());
		cart.setNet(Boolean.FALSE);
		cart.setPaymentAddress(user.getDefaultPaymentAddress());
		cart.setDeliveryAddress(user.getDefaultShipmentAddress());
		modelService.save(cart);
		modelService.refresh(cart);

		assertEquals("Cart.getUser an current user have got to be the same.", user, cart.getUser());
		assertNotNull("Cart currency have to be nut null.", cart.getCurrency());
		assertEquals("Cart currency have to be nut null.", user.getDefaultShipmentAddress(), cart.getDeliveryAddress());
		assertEquals("Cart currency have to be nut null.", user.getDefaultPaymentAddress(), cart.getPaymentAddress());

		return cart;
	}

	/**
	 * 
	 * Adds product to the session-cart.
	 * 
	 * @param product
	 *            {@link ProductModel}
	 * @param countryCode
	 *            String
	 */
	private void addProductToCart(final ProductModel product, final String countryCode) throws CalculationException {

		final CartModel cart = cartService.getSessionCart();
		// quantity = 15 to achieve minAmount in cart
		final CartEntryModel entry = cartService.addNewEntry(cart, product, 15, null, -1, true);
		entry.setCalculated(false);
		final List<AbstractOrderEntryModel> entries = cart.getEntries();
		assertEquals("Number of entries", 1, entries.size());

		// add price from product_02_<countryIsoCode> PriceRow
		final Collection<PriceRowModel> priceRowModels = product.getEurope1Prices();
		assertNotNull(priceRowModels);
		final PriceRowModel priceRowModel = priceRowModels.iterator().next();
		final double price = priceRowModel.getPrice();
		entry.setBasePrice(Double.valueOf(price));

		// add new entry does not persists or calculates cart/order
		assertTrue(modelService.isNew(entry));
		assertFalse(modelService.isUpToDate(cart));

		// add country specific Tax to both entries
		final TaxModel tax = taxService.getTaxForCode(KlarnapaymentTestConstants.getTaxCodeForCountry(countryCode.toUpperCase()));
		final TaxValue taxValue = new TaxValue(tax.getCode(), tax.getValue(), false, cart.getCurrency().getIsocode());
		cartEntryService.addTaxValue(entry, taxValue);

		// save cart and entry and refresh. The same could be achieved by cartService.saveOrder(cart)
		modelService.saveAll(cart, entry);
		modelService.refresh(cart);
		modelService.refresh(entry);

		// check calculate flag - before calculate() method
		assertFalse(cart.getCalculated().booleanValue());
		assertFalse(entry.getCalculated().booleanValue());
		// calculateCart - only calculate, do not fetch base prices, discounts and taxes from price factory
		calculationService.calculateTotals(cart, true);

		// check calculate flag - after calculate() method
		assertTrue(cart.getCalculated().booleanValue());
		assertTrue(entry.getCalculated().booleanValue());

		assertFalse(entry.getTaxValues().isEmpty());
	}

}
