/**
 * (c) copyright 2013 by getit GmbH
 *
 * @author Sonja Bouwers, getit GmbH
 */
package de.getit.hybris.klarna.payment;

import static org.junit.Assert.assertNotNull;

import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.delivery.DeliveryService;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.CartEntryService;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.PaymentModeService;
import de.hybris.platform.order.TaxService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

import de.getit.hybris.klarna.payment.model.KlarnaConfigModel;

/**
 * Baseclass for Integrations-/Servicelayer-Tests with JUnit-Tenant.
 * 
 * https://wiki.hybris.com/display/release4/Testing+in+the+hybris+Multichannel+Suite
 * 
 * TODOs before starting tests from eclipse:
 * <ul>
 * <li>Initialize JUnit-Tenant</li>
 * <li>add VM arguments to Run Configurations '-Xms2048m -Xmx10246m -XX:NewSize=513m -XX:MaxNewSize=712m
 * -XX:PermSize=512m -XX:MaxPermSize=712m'</li>
 * </ul>
 * 
 * @author Sonja Bouwers, getit GmbH
 */
@Ignore
public class AbstractIntegrationTest extends ServicelayerTransactionalTest {

	@Resource
	protected UserService userService;
	@Resource
	protected ModelService modelService;
	@Resource
	protected CommonI18NService commonI18NService;
	@Resource
	protected BaseStoreService baseStoreService;
	@Resource
	protected CatalogService catalogService;
	@Resource
	protected CatalogVersionService catalogVersionService;
	@Resource
	protected CategoryService categoryService;
	@Resource
	protected ProductService productService;
	@Resource
	protected SessionService sessionService;
	@Resource
	protected CartService cartService;
	@Resource
	protected CalculationService calculationService;
	@Resource
	protected CartEntryService cartEntryService;
	@Resource
	protected PaymentModeService paymentModeService;
	@Resource
	protected ConfigurationService configurationService;
	@Resource
	protected TaxService taxService;
	@Resource
	protected DeliveryService deliveryService;
	@Resource
	protected FlexibleSearchService flexibleSearchService;
	
	public static final Logger LOG = Logger.getLogger(AbstractIntegrationTest.class);

	@BeforeClass
	public static void setUpOnce() throws Exception {
		// Initialize stuff once for ALL tests (run once)
		LOG.info("Initialize/Setup test data");

		createCoreData();

		importCsv("/klarnapayment/test/klarnatestdata.csv", "utf-8");

		overrideMerchantIdAndSharedSecretForAllKlarnaConfigs(getConfiguration());
		
		LOG.info("Finished initialize/setup test data");
	}
	
	@After
	public void tearDown() throws Exception {
		// Do something after each test (run twice in this example)
		modelService.detachAll();
	}

	@AfterClass
	public static void tearDownClass() {
		// Do something after ALL tests have been run (run once)
	}

	public static <T> T getBean(final String pBeanname) {
		return (T) Registry.getGlobalApplicationContext().getBean(pBeanname);
	}

	public CountryModel getCountry(final String isoCode) {
		final CountryModel country = commonI18NService.getCountry(isoCode);
		assertNotNull("Testcountry '" + isoCode + "' not available.", country);
		return country;
	}

	public BaseStoreModel getBaseStore(final String uid) {

		final BaseStoreModel baseStore = findBaseStoresByUid(uid);		
		assertNotNull("Testbasestore '" + uid + "' not available.", baseStore);
		return baseStore;
	}

	public BaseStoreModel findBaseStoresByUid(final String uid) {
		final FlexibleSearchQuery query = new FlexibleSearchQuery(
				"SELECT {pk} FROM {BaseStore} WHERE {uid} =?uid");
		final Map<String, String> params = new HashMap<String, String>();
		params.put("uid", uid);
		query.addQueryParameters(params);
		
		final SearchResult<BaseStoreModel> result =  flexibleSearchService.search(query);
		return result.getResult().get(0);
	}
	
	
	
	public UserModel getUser(final String uid) {
		final UserModel user = userService.getUserForUID(uid);
		assertNotNull("Testuser '" + uid + "' not available.", user);
		return user;
	}

	public CatalogModel getCatalog(final String code) {
		final CatalogModel catalog = catalogService.getCatalogForId(code);
		assertNotNull("Testcatalog '" + code + "' not available.", catalog);
		return catalog;
	}

	public CategoryModel getCategory(final String code) {
		final CategoryModel category = categoryService.getCategoryForCode(code);
		assertNotNull("Testcategory '" + code + "' not available.", category);
		return category;

	}

	public ProductModel getProduct(final String code) {
		final ProductModel prod = productService.getProductForCode(code);
		assertNotNull("Testproduct '" + code + "' not available.", prod);
		return prod;
	}

	public PaymentModeModel getPaymentMode(final String code) {
		final PaymentModeModel paymentMode = paymentModeService.getPaymentModeForCode(code);
		assertNotNull("PaymentMode '" + code + "' not available.", paymentMode);
		return paymentMode;
	}

	public DeliveryModeModel getDeliveryMode(final String code) {
		final DeliveryModeModel deliveryMode = deliveryService.getDeliveryModeForCode(code);
		assertNotNull("DeliveryMode '" + code + "' not available.", deliveryMode);
		return deliveryMode;
	}

	private static void overrideMerchantIdAndSharedSecretForAllKlarnaConfigs(final Configuration configuration ) {	
		
		final Integer merchantId = configuration.getInteger("klarna.junittest.merchantId", null);
		final String sharedSecret = configuration.getString("klarna.junittest.sharedSecret", null);
		
		assertNotNull("klarna.junittest.merchantId '" + merchantId + "' not configured.", merchantId);
		assertNotNull("klarna.junittest.sharedSecret '" + sharedSecret + "' not configured.", sharedSecret);
		
		final List<KlarnaConfigModel> klarnaConfigList = findAllKlarnaConfigModels();
		
		for(KlarnaConfigModel klarnaConfig:klarnaConfigList) {
			klarnaConfig.setMerchantId(merchantId);
			klarnaConfig.setSharedSecret(sharedSecret);
		}
		
		final ModelService modelService = (ModelService) getBean("modelService");
		modelService.saveAll(klarnaConfigList);

	}
	
	private static List<KlarnaConfigModel> findAllKlarnaConfigModels() {			
		final FlexibleSearchService flexibleSearchService = (FlexibleSearchService)getBean("flexibleSearchService");
		final SearchResult<KlarnaConfigModel> result = flexibleSearchService.search("SELECT {pk} FROM {KlarnaConfig}");		
		return result.getResult();
	}
	
	protected static Configuration getConfiguration() {	
		return ((ConfigurationService) getBean("configurationService")).getConfiguration();		
	}
	
}
