/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author getit GmbH
 */
package de.getit.hybris.klarna.payment.core.service.impl;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.store.BaseStoreModel;

import java.text.MessageFormat;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.klarna.api.KlarnaException;

import de.getit.hybris.klarna.payment.AbstractIntegrationTest;
import de.getit.hybris.klarna.payment.constants.KlarnapaymentTestConstants;
import de.getit.hybris.klarna.payment.core.calculation.MonthlyCostsInfo;
import de.getit.hybris.klarna.payment.core.dao.KlarnaDAO;
import de.getit.hybris.klarna.payment.core.exception.GetitKlarnaException;
import de.getit.hybris.klarna.payment.core.service.KlarnaMonthlyCostsCalculationService;
import de.getit.hybris.klarna.payment.core.service.KlarnaPClassService;
import de.getit.hybris.klarna.payment.core.strategy.impl.GetitKlarnaGetBaseParameterStrategy;
import de.getit.hybris.klarna.payment.klarnaapi.KlarnaCalcMonthlyCostTest;
import de.getit.hybris.klarna.payment.model.KlarnaCountryConfigModel;

/**
 * 
 * 
 * @author getit GmbH
 */
public class GetitKlarnaCalculationServiceTest extends AbstractIntegrationTest {

	public static Integer EID = 405;

	private static class TestParams {
		public enum TestType {
			PRODUCT, CHECKOUT;
		}

		private TestType mTestType;
		private BaseStoreModel mBaseStore;
		private CountryModel mCountry;
		private double mPrice;
		private boolean mShowMonthlyCosts;
		private double mMonthlyCosts;
		private int mEid;
		private int mPClassId;

		public TestType getTestType() {
			return mTestType;
		}

		public void setTestType(final TestType pTestType) {
			mTestType = pTestType;
		}

		public BaseStoreModel getBaseStore() {
			return mBaseStore;
		}

		public void setBaseStore(final BaseStoreModel pBaseStore) {
			mBaseStore = pBaseStore;
		}

		public CountryModel getCountry() {
			return mCountry;
		}

		public void setCountry(final CountryModel pCountry) {
			mCountry = pCountry;
		}

		public double getPrice() {
			return mPrice;
		}

		public void setPrice(final double pPrice) {
			mPrice = pPrice;
		}

		public boolean isShowMonthlyCosts() {
			return mShowMonthlyCosts;
		}

		public void setShowMonthlyCosts(final boolean pShowMonthlyCosts) {
			mShowMonthlyCosts = pShowMonthlyCosts;
		}

		public double getMonthlyCosts() {
			return mMonthlyCosts;
		}

		public void setMonthlyCosts(final double pMonthlyCosts) {
			mMonthlyCosts = pMonthlyCosts;
		}

		public int getEid() {
			return mEid;
		}

		public void setEid(final int pEid) {
			mEid = pEid;
		}

		public int getPClassId() {
			return mPClassId;
		}

		public void setPClassId(final int pPClassId) {
			mPClassId = pPClassId;
		}
	}

	private KlarnaMonthlyCostsCalculationService mKlarnaMonthlyCostsCalculationService;
	private KlarnaPClassService mKlarnaPClassService;
	private KlarnaDAO mKlarnaDAO;

	@Before
	public void setUp() throws Exception {

		mKlarnaMonthlyCostsCalculationService = (KlarnaMonthlyCostsCalculationService) getBean(KlarnaMonthlyCostsCalculationService.BEAN_NAME);
		mKlarnaPClassService = (KlarnaPClassService) getBean(KlarnaPClassService.BEAN_NAME);
		mKlarnaDAO = (KlarnaDAO) getBean("klarnaDAO");

		createDefaultCatalog();
		importCsv("/klarnapayment/test/klarnaCartEURTestData.csv", "utf-8");
		importCsv("/klarnapayment/test/klarnaCartDKKTestData.csv", "utf-8");
		importCsv("/klarnapayment/test/klarnaCartSEKTestData.csv", "utf-8");
		importCsv("/klarnapayment/test/klarnaCartNOKTestData.csv", "utf-8");
	}

	@Test
	public void testCalculateMonthlyCosts4Product_AT() throws KlarnaException,
			GetitKlarnaException {

		final TestParams testParams = new TestParams();
		testParams.setTestType(TestParams.TestType.PRODUCT);
		testParams
				.setBaseStore(getBaseStore(KlarnapaymentTestConstants.STORE.AT));
		testParams
				.setCountry(getCountry(KlarnapaymentTestConstants.COUNTRY.AT));
		testParams.setShowMonthlyCosts(false);
		testCalculateMonthlyCosts(testParams);
	}

	@Test
	public void testCalculateMonthlyCosts4Cart_AT() throws KlarnaException,
			GetitKlarnaException {

		final TestParams testParams = new TestParams();
		testParams.setTestType(TestParams.TestType.CHECKOUT);
		testParams
				.setBaseStore(getBaseStore(KlarnapaymentTestConstants.STORE.AT));
		testParams
				.setCountry(getCountry(KlarnapaymentTestConstants.COUNTRY.AT));
		testParams.setShowMonthlyCosts(false);
		testCalculateMonthlyCosts(testParams);
	}

	@Test
	public void testCalculateMonthlyCosts4Product_DE() throws KlarnaException,
			GetitKlarnaException {

		final TestParams testParams = new TestParams();
		testParams.setTestType(TestParams.TestType.PRODUCT);
		testParams
				.setBaseStore(getBaseStore(KlarnapaymentTestConstants.STORE.DE));
		testParams
				.setCountry(getCountry(KlarnapaymentTestConstants.COUNTRY.DE));
		testParams.setEid(EID);
		testParams.setPClassId(KlarnaCalcMonthlyCostTest.PCLASS_ID_DE);

		// lower
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(33.99d);
		testCalculateMonthlyCosts(testParams);

		// between
		testParams.setShowMonthlyCosts(true);
		testParams.setPrice(150.44d);
		testParams.setMonthlyCosts(8.1d);
		testCalculateMonthlyCosts(testParams);

		// higher
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(5233.44d);
		testCalculateMonthlyCosts(testParams);
	}

	@Test
	public void testCalculateMonthlyCosts4Cart_DE() throws KlarnaException,
			GetitKlarnaException {

		final TestParams testParams = new TestParams();
		testParams.setTestType(TestParams.TestType.CHECKOUT);
		testParams
				.setBaseStore(getBaseStore(KlarnapaymentTestConstants.STORE.DE));
		testParams
				.setCountry(getCountry(KlarnapaymentTestConstants.COUNTRY.DE));
		testParams.setEid(EID);
		testParams.setPClassId(KlarnaCalcMonthlyCostTest.PCLASS_ID_DE);

		// lower
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(33.99d);
		testCalculateMonthlyCosts(testParams);

		// between
		testParams.setShowMonthlyCosts(true);
		testParams.setPrice(150.44d);
		testParams.setMonthlyCosts(10.0d);
		testCalculateMonthlyCosts(testParams);

		// higher
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(5233.44d);
		testCalculateMonthlyCosts(testParams);
	}

	@Test
	public void testCalculateMonthlyCosts4Product_DK() throws KlarnaException,
			GetitKlarnaException {

		final TestParams testParams = new TestParams();
		testParams.setTestType(TestParams.TestType.PRODUCT);
		testParams
				.setBaseStore(getBaseStore(KlarnapaymentTestConstants.STORE.DK));
		testParams
				.setCountry(getCountry(KlarnapaymentTestConstants.COUNTRY.DK));
		testParams.setEid(EID);
		testParams.setPClassId(KlarnaCalcMonthlyCostTest.PCLASS_ID_DK);

		// lower
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(33.99d);
		testCalculateMonthlyCosts(testParams);

		// between
		testParams.setShowMonthlyCosts(true);
		testParams.setPrice(1150.44d);
		testParams.setMonthlyCosts(67.0d);
		testCalculateMonthlyCosts(testParams);

		// higher
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(15233.44d);
		testCalculateMonthlyCosts(testParams);
	}

	@Test
	public void testCalculateMonthlyCosts4Cart_DK() throws KlarnaException,
			GetitKlarnaException {

		final TestParams testParams = new TestParams();
		testParams.setTestType(TestParams.TestType.CHECKOUT);
		testParams
				.setBaseStore(getBaseStore(KlarnapaymentTestConstants.STORE.DK));
		testParams
				.setCountry(getCountry(KlarnapaymentTestConstants.COUNTRY.DK));
		testParams.setEid(EID);
		testParams.setPClassId(KlarnaCalcMonthlyCostTest.PCLASS_ID_DK);

		// lower
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(33.99d);
		testCalculateMonthlyCosts(testParams);

		// between
		testParams.setShowMonthlyCosts(true);
		testParams.setPrice(1150.44d);
		testParams.setMonthlyCosts(106.0d);
		testCalculateMonthlyCosts(testParams);

		// higher
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(15233.44d);
		testCalculateMonthlyCosts(testParams);
	}

	@Test
	public void testCalculateMonthlyCosts4Product_FI() throws KlarnaException,
			GetitKlarnaException {

		final TestParams testParams = new TestParams();
		testParams.setTestType(TestParams.TestType.PRODUCT);
		testParams
				.setBaseStore(getBaseStore(KlarnapaymentTestConstants.STORE.FI));
		testParams
				.setCountry(getCountry(KlarnapaymentTestConstants.COUNTRY.FI));
		testParams.setEid(EID);
		testParams.setPClassId(KlarnaCalcMonthlyCostTest.PCLASS_ID_FI);

		// lower
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(33.99d);
		testCalculateMonthlyCosts(testParams);

		// between
		testParams.setShowMonthlyCosts(true);
		testParams.setPrice(150.44d);
		testParams.setMonthlyCosts(6.3d); // TODO check vs. old value 8.8
		testCalculateMonthlyCosts(testParams);

		// higher
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(15233.44d);
		testCalculateMonthlyCosts(testParams);
	}

	@Test
	public void testCalculateMonthlyCosts4Cart_FI() throws KlarnaException,
			GetitKlarnaException {

		final TestParams testParams = new TestParams();
		testParams.setTestType(TestParams.TestType.CHECKOUT);
		testParams
				.setBaseStore(getBaseStore(KlarnapaymentTestConstants.STORE.FI));
		testParams
				.setCountry(getCountry(KlarnapaymentTestConstants.COUNTRY.FI));
		testParams.setEid(EID);
		testParams.setPClassId(KlarnaCalcMonthlyCostTest.PCLASS_ID_FI);

		// lower
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(33.99d);
		testCalculateMonthlyCosts(testParams);

		// between
		testParams.setShowMonthlyCosts(true);
		testParams.setPrice(150.44d);
		testParams.setMonthlyCosts(9.0d); // TODO check vs. old value 12.0
		testCalculateMonthlyCosts(testParams);

		// higher
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(15233.44d);
		testCalculateMonthlyCosts(testParams);
	}

	@Test
	public void testCalculateMonthlyCosts4Product_NL() throws KlarnaException,
			GetitKlarnaException {

		final TestParams testParams = new TestParams();
		testParams.setTestType(TestParams.TestType.PRODUCT);
		testParams
				.setBaseStore(getBaseStore(KlarnapaymentTestConstants.STORE.NL));
		testParams
				.setCountry(getCountry(KlarnapaymentTestConstants.COUNTRY.NL));
		testParams.setEid(EID);
		testParams.setPClassId(KlarnaCalcMonthlyCostTest.PCLASS_ID_NL);

		// lower
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(33.99d);
		testCalculateMonthlyCosts(testParams);

		// between
		testParams.setShowMonthlyCosts(true);
		testParams.setPrice(150.44d);
		testParams.setMonthlyCosts(8.1d);
		testCalculateMonthlyCosts(testParams);

		// higher
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(15233.44d);
		testCalculateMonthlyCosts(testParams);
	}

	@Test
	public void testCalculateMonthlyCosts4Cart_NL() throws KlarnaException,
			GetitKlarnaException {

		final TestParams testParams = new TestParams();
		testParams.setTestType(TestParams.TestType.CHECKOUT);
		testParams
				.setBaseStore(getBaseStore(KlarnapaymentTestConstants.STORE.NL));
		testParams
				.setCountry(getCountry(KlarnapaymentTestConstants.COUNTRY.NL));
		testParams.setEid(EID);
		testParams.setPClassId(KlarnaCalcMonthlyCostTest.PCLASS_ID_NL);

		// lower
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(33.99d);
		testCalculateMonthlyCosts(testParams);

		// between
		testParams.setShowMonthlyCosts(true);
		testParams.setPrice(150.44d);
		testParams.setMonthlyCosts(8.0d); // TODO chekc vs. old 10.0
		testCalculateMonthlyCosts(testParams);

		// higher
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(15233.44d);
		testCalculateMonthlyCosts(testParams);
	}

	@Test
	public void testCalculateMonthlyCosts4Product_NO() throws KlarnaException,
			GetitKlarnaException {

		final TestParams testParams = new TestParams();
		testParams.setTestType(TestParams.TestType.PRODUCT);
		testParams
				.setBaseStore(getBaseStore(KlarnapaymentTestConstants.STORE.NO));
		testParams
				.setCountry(getCountry(KlarnapaymentTestConstants.COUNTRY.NO));
		testParams.setEid(EID);
		testParams.setPClassId(KlarnaCalcMonthlyCostTest.PCLASS_ID_NO);

		// lower
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(33.99d);
		testCalculateMonthlyCosts(testParams);

		// between
		testParams.setShowMonthlyCosts(true);
		testParams.setPrice(1150.44d);
		testParams.setMonthlyCosts(69.0d);
		testCalculateMonthlyCosts(testParams);

		// higher
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(15233.44d);
		testCalculateMonthlyCosts(testParams);
	}

	@Test
	public void testCalculateMonthlyCosts4Cart_NO() throws KlarnaException,
			GetitKlarnaException {

		final TestParams testParams = new TestParams();
		testParams.setTestType(TestParams.TestType.CHECKOUT);
		testParams
				.setBaseStore(getBaseStore(KlarnapaymentTestConstants.STORE.NO));
		testParams
				.setCountry(getCountry(KlarnapaymentTestConstants.COUNTRY.NO));
		testParams.setEid(EID);
		testParams.setPClassId(KlarnaCalcMonthlyCostTest.PCLASS_ID_NO);

		// lower
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(33.99d);
		testCalculateMonthlyCosts(testParams);

		// between
		testParams.setShowMonthlyCosts(true);
		testParams.setPrice(1150.44d);
		testParams.setMonthlyCosts(114.0d);
		testCalculateMonthlyCosts(testParams);

		// higher
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(15233.44d);
		testCalculateMonthlyCosts(testParams);
	}

	@Test
	public void testCalculateMonthlyCosts4Product_SE() throws KlarnaException,
			GetitKlarnaException {

		final TestParams testParams = new TestParams();
		testParams.setTestType(TestParams.TestType.PRODUCT);
		testParams
				.setBaseStore(getBaseStore(KlarnapaymentTestConstants.STORE.SE));
		testParams
				.setCountry(getCountry(KlarnapaymentTestConstants.COUNTRY.SE));
		testParams.setEid(EID);
		testParams.setPClassId(KlarnaCalcMonthlyCostTest.PCLASS_ID_SE);

		// lower
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(33.99d);
		testCalculateMonthlyCosts(testParams);

		// between
		testParams.setShowMonthlyCosts(true);
		testParams.setPrice(1150.44d);
		testParams.setMonthlyCosts(67.0d);
		testCalculateMonthlyCosts(testParams);

		// higher
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(15233.44d);
		testCalculateMonthlyCosts(testParams);
	}

	@Test
	public void testCalculateMonthlyCosts4Cart_SE() throws KlarnaException,
			GetitKlarnaException {

		final TestParams testParams = new TestParams();
		testParams.setTestType(TestParams.TestType.CHECKOUT);
		testParams
				.setBaseStore(getBaseStore(KlarnapaymentTestConstants.STORE.SE));
		testParams
				.setCountry(getCountry(KlarnapaymentTestConstants.COUNTRY.SE));
		testParams.setEid(EID);
		testParams.setPClassId(KlarnaCalcMonthlyCostTest.PCLASS_ID_SE);

		// lower
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(33.99d);
		testCalculateMonthlyCosts(testParams);

		// between
		testParams.setShowMonthlyCosts(true);
		testParams.setPrice(1150.44d);
		testParams.setMonthlyCosts(96.0d); // TODO vs. old 106.0
		testCalculateMonthlyCosts(testParams);

		// higher
		testParams.setShowMonthlyCosts(false);
		testParams.setPrice(15233.44d);
		testCalculateMonthlyCosts(testParams);
	}

	private void testCalculateMonthlyCosts(final TestParams pTestParams)
			throws GetitKlarnaException {

		final BaseStoreModel baseStore = pTestParams.getBaseStore();
		final CountryModel country = pTestParams.getCountry();
		sessionService.setAttribute("currentSite", baseStore.getCmsSites()
				.iterator().next());
		sessionService.setAttribute(
				GetitKlarnaGetBaseParameterStrategy.GETITBASESTORESESSIONNAME,
				baseStore);

		final KlarnaCountryConfigModel klarnaCountryConfig = mKlarnaDAO
				.getKlarnaCountryConfig4Country(country);
		commonI18NService.setCurrentCurrency(klarnaCountryConfig.getCurrency());
		commonI18NService.setCurrentLanguage(klarnaCountryConfig.getLanguage());

		final String username = MessageFormat.format(
				KlarnapaymentTestConstants.USER.APPROVED, country.getIsocode());
		final UserModel user = getUser(username);
		userService.setCurrentUser(user);

		try {
			mKlarnaPClassService.fetchPClasses(baseStore, country,
					KlarnapaymentTestConstants.CLIENT_IP);
		} catch (final GetitKlarnaException e) {
			e.printStackTrace();
			fail("fetchPClasses: " + e.getMessage());
		} catch (final KlarnaException e) {
			fail("fetchPClasses: " + e.getMessage());
		}

		ProductModel product = null;
		CartModel cart = null;

		switch (pTestParams.getTestType()) {

		case PRODUCT:
			product = createProduct(pTestParams.getPrice(),
					country.getIsocode());
			break;

		case CHECKOUT:
			cart = createCart(pTestParams.getPrice(),
					klarnaCountryConfig.getCurrency());
			break;

		default:
			fail("Wrong Type");
		}

		MonthlyCostsInfo monthlyCostsInfo = null;
		try {
			if (pTestParams.getTestType() == TestParams.TestType.PRODUCT)
				monthlyCostsInfo = mKlarnaMonthlyCostsCalculationService
						.calcMonthlyCosts(product,
								KlarnapaymentTestConstants.CLIENT_IP);
			else
				monthlyCostsInfo = mKlarnaMonthlyCostsCalculationService
						.calcMonthlyCosts(cart,
								KlarnapaymentTestConstants.CLIENT_IP);
		} catch (final GetitKlarnaException e) {
			e.printStackTrace();
			fail("calcMonthlyCosts: " + e.getMessage());
		} catch (final KlarnaException e) {
			e.printStackTrace();
			fail("calcMonthlyCosts: " + e.getMessage());
		}

		if (pTestParams.isShowMonthlyCosts()) {
			assertTrue(monthlyCostsInfo.isShowMonthlyCosts());
			
			

			String debugInfo = "ShowMonthlyCosts: "
					+ monthlyCostsInfo.isShowMonthlyCosts()
					+ ", MonthlyCosts: " + monthlyCostsInfo.getMonthlyCosts()
					+ ", Country: "
					+ monthlyCostsInfo.getCountry().getIsocode()
					+ ", Currency: "
					+ monthlyCostsInfo.getCurrency().getIsocode()
					+ ", Language: "
					+ monthlyCostsInfo.getLanguage().getIsocode() + ", EID: "
					+ monthlyCostsInfo.getEid() + ", PClassID: "
					+ monthlyCostsInfo.getPClassId();
			if (pTestParams.getTestType() == TestParams.TestType.PRODUCT){
				debugInfo = debugInfo + ", product: " + product.getCode();
			}

			assertEquals(debugInfo, pTestParams.getMonthlyCosts(),
					monthlyCostsInfo.getMonthlyCosts(), 0.1d);
			assertEquals(pTestParams.getEid(), monthlyCostsInfo.getEid());
			assertEquals(pTestParams.getPClassId(),
					monthlyCostsInfo.getPClassId());
		} else {
			assertFalse(monthlyCostsInfo.isShowMonthlyCosts());
		}
	}

	private ProductModel createProduct(final double pPrice,
			final String pCountryCode) {

		final ProductModel product = getProduct("product_01_" + pCountryCode);
		assertNotNull(product);
		final Collection<PriceRowModel> priceRowModels = product
				.getEurope1Prices();
		final PriceRowModel priceRowModel = priceRowModels.iterator().next();
		priceRowModel.setPrice(pPrice);
		modelService.saveAll(priceRowModel, product);

		return product;
	}

	private CartModel createCart(final double pPrice,
			final CurrencyModel pCurrency) {

		final CartModel cart = modelService.create(CartModel.class);
		cart.setTotalPrice(pPrice);
		cart.setCurrency(pCurrency);
		return cart;
	}
}
