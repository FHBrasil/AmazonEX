package com.paypal.hybris.addon.facades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;

import com.paypal.hybris.facade.impl.PayPalPaymentFacade;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.ebay.api.AckCodeType;
import com.ebay.api.DoExpressCheckoutPaymentResponseType;
import com.ebay.api.GetExpressCheckoutDetailsResponseType;
import com.paypal.hybris.data.GetExpressCheckoutDetailsRequestData;
import com.paypal.hybris.data.GetExpressCheckoutDetailsResultData;
import com.paypal.hybris.data.SetExpressCheckoutRequestData;
import com.paypal.hybris.data.SetExpressCheckoutResultData;
import com.paypal.hybris.service.impl.DefaultPaypalPaymentService;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
@IntegrationTest
public class PayPalPaymentFacadeIntegrationTest extends ServicelayerTransactionalTest
{

	private static final String TEST_USER_ID = "cartTestUser";
	private static final String TEST_PRODUCT_A_ID = "pA";
	private static final String CURRENCY_ISO_CODE = "EUR";

	@Resource PayPalPaymentFacade payPalPaymentFacade;

	@Resource
	private DefaultPaypalPaymentService paypalPaymentService;
	@Resource
	private ModelService modelService;
	@Resource
	private UserService userService;
	@Resource
	private CommonI18NService commonI18NService;
	@Resource
	private CartService cartService;
	@Resource
	private ProductService productService;
	@Resource
	private CalculationService calculationService;

	private DefaultPaypalPaymentService paypalPaymentServicePartMock;
	private GetExpressCheckoutDetailsResponseType getExpressCheckoutDetailsResponse;
	private DoExpressCheckoutPaymentResponseType doExpressCheckoutPaymentResponse;

	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		createTestCatalog();
		createTestUser();
		createSessionCart();
		addProductToSessionCart();
		createMocks();
	}

	@Test
	public void testPreparePaypalPayment()
	{
		final SetExpressCheckoutRequestData requestData = new SetExpressCheckoutRequestData();
		requestData.setPaymentAction("Sale");
		requestData.setReturnUrl("https://localhost:9002/success");
		requestData.setCancelUrl("https://localhost:9002/cancel");
		requestData.setSolutionType("Mark");
		requestData.setUid(UUID.randomUUID().toString());
		final SetExpressCheckoutResultData resultData = payPalPaymentFacade.preparePaypalPayment(requestData);
		assertNotNull(resultData);
		assertEquals(resultData.getAck(), AckCodeType.SUCCESS.value());
		assertNotNull("Token can't be null", resultData.getToken());
		assertTrue("Token can't be empty string", StringUtils.isNotBlank(resultData.getToken()));
	}

	@Test
	public void testPrepareCreditPayment()
	{
		final SetExpressCheckoutRequestData requestData = new SetExpressCheckoutRequestData();
		requestData.setPaymentAction("Sale");
		requestData.setReturnUrl("https://localhost:9002/success");
		requestData.setCancelUrl("https://localhost:9002/cancel");
		requestData.setSolutionType("Sole");
		requestData.setFundingSource("BML");
		requestData.setUid(UUID.randomUUID().toString());
		final SetExpressCheckoutResultData resultData = payPalPaymentFacade.preparePaypalPayment(requestData);
		assertNotNull(resultData);
		assertEquals(resultData.getAck(), AckCodeType.SUCCESS.value());
		assertNotNull("Token can't be null", resultData.getToken());
		assertTrue("Token can't be empty string", StringUtils.isNotBlank(resultData.getToken()));
	}

	@Test
	public void testMissReqUrlsPreparePaypalPayment()
	{
		final SetExpressCheckoutRequestData requestData = new SetExpressCheckoutRequestData();
		requestData.setPaymentAction("Order");
		requestData.setSolutionType("Mark");
		requestData.setUid(UUID.randomUUID().toString());
		final SetExpressCheckoutResultData resultData = payPalPaymentFacade.preparePaypalPayment(requestData);
		assertNotNull(resultData);
		assertEquals(resultData.getAck(), AckCodeType.FAILURE.value());
	}

	@Test
	public void testGetExpressCheckoutDetails()
	{
		final GetExpressCheckoutDetailsRequestData requestData = new GetExpressCheckoutDetailsRequestData();
		requestData.setToken("3424355-DG");
		final GetExpressCheckoutDetailsResultData resultData = payPalPaymentFacade.getExpressCheckoutDetails(requestData);
		assertNotNull(resultData);
		assertEquals(resultData.getAck(), AckCodeType.SUCCESS.value());
	}

	private void createTestCatalog() throws ImpExException
	{
		importCsv("/paypaladdon/test/testOrderCalculations.csv", "utf-8");
	}

	private void createMocks()
	{
		/*getExpressCheckoutDetailsResponse = new GetExpressCheckoutDetailsResponseType();
		getExpressCheckoutDetailsResponse.setAck(AckCodeType.SUCCESS);
		getExpressCheckoutDetailsResponse.setTimestamp(new XMLGregorianCalendarImpl((GregorianCalendar) GregorianCalendar
				.getInstance()));

		doExpressCheckoutPaymentResponse = new DoExpressCheckoutPaymentResponseType();
		doExpressCheckoutPaymentResponse.setAck(AckCodeType.SUCCESS);
		doExpressCheckoutPaymentResponse.setTimestamp(new XMLGregorianCalendarImpl((GregorianCalendar) GregorianCalendar
				.getInstance()));

		paypalPaymentServicePartMock = spy(paypalPaymentService);
		doReturn(getExpressCheckoutDetailsResponse).when(paypalPaymentServicePartMock).getExpressCheckoutDetails(
				any(GetExpressCheckoutDetailsReq.class));
		doReturn(doExpressCheckoutPaymentResponse).when(paypalPaymentServicePartMock).doExpressCheckoutPayment(
				any(DoExpressCheckoutPaymentReq.class));

		payPalPaymentFacade.setPaypalPaymentService(paypalPaymentServicePartMock);*/
	}

	/**
	 * Utility method to create cart and set it as session cart
	 */
	private void createSessionCart()
	{
		final CartModel cart = modelService.create(CartModel.class);
		final UserModel customer = userService.getUserForUID(TEST_USER_ID);
		cart.setUser(customer);
		cart.setCurrency(commonI18NService.getCurrency(CURRENCY_ISO_CODE));
		cart.setDate(new Date());
		cart.setNet(Boolean.TRUE);
		modelService.save(cart);
		cartService.setSessionCart(cart);
	}

	/**
	 * Utility method to add entry to cart and recalculate it
	 *
	 * @throws CalculationException
	 */
	private void addProductToSessionCart() throws CalculationException
	{
		final CartModel cart = cartService.getSessionCart();
		final ProductModel productA = productService.getProductForCode(TEST_PRODUCT_A_ID);
		final UnitModel unitModel = productA.getUnit();
		final CartEntryModel cartEntry = cartService.addNewEntry(cart, productA, 2, unitModel);
		cartEntry.setBasePrice(5.989);
		cartEntry.setQuantity(2L);
		modelService.save(cartEntry);
		cartService.saveOrder(cart);
		calculationService.calculateTotals(cart, false);
	}

	/**
	 * test user for changing the current cart user
	 */
	private void createTestUser()
	{
		final UserModel newCustomer = modelService.create(UserModel.class);
		newCustomer.setUid(TEST_USER_ID);
		//newCustomer.setCustomerID(TEST_USER_ID);
		newCustomer.setSessionCurrency(commonI18NService.getCurrency(CURRENCY_ISO_CODE));
		newCustomer.setSessionLanguage(commonI18NService.getLanguage("en"));
		newCustomer.setGroups(Collections.<PrincipalGroupModel> singleton(userService.getUserGroupForUID("customergroup")));
		modelService.save(newCustomer);
	}
}
