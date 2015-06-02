package com.paypal.hybris.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import com.ebay.api.*;
import com.ebay.utils.PaypalStringUtils;
import com.paypal.hybris.constants.PaypalConstants;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.order.Cart;
import de.hybris.platform.order.CartService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.DiscountValue;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import javax.xml.ws.Holder;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class DefaultPaypalPaymentServiceIntegrationTest extends ServicelayerTransactionalTest {

    private static final String TEST_USER_ID = "cartTestUser";

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

    private Holder<CustomSecurityHeaderType> headers;
    private PayPalAPIAAInterface paypalAPIAAInterfaceMock;
    private DefaultPaypalPaymentService paypalPaymentServicePartMock;
    private GetExpressCheckoutDetailsResponseType getExpressCheckoutDetailsResponse;
    private DoExpressCheckoutPaymentResponseType doExpressCheckoutPaymentResponse;

    @Before
    public void setUp() throws Exception {
        createCoreData();
        createDefaultCatalog();
        createTestUser();
        createSessionCart();
        addProductToSessionCart();
        createMocks();
    }

    @Test
    public void testSetExpressCheckout() {
        SetExpressCheckoutRequestType request = createSetExprCheckRequest();
        SetExpressCheckoutResponseType response = paypalPaymentService.setExpressCheckout(request);

        assertNotNull("Response from web service must not be null", response);
        assertEquals("Response must be successful", response.getAck().value(), PaypalConstants.STATUS_SUCCESS);
        assertNotNull("Token can't be null", response.getToken());
        assertTrue("Token can't be empty", StringUtils.isNotBlank(response.getToken()));
    }

    @Test
    public void testMissReqArgsSetExpressCheckout() {
        SetExpressCheckoutRequestType request = createSetExprCheckRequest();
        //set null to required argument
        request.getSetExpressCheckoutRequestDetails().setCancelURL(null);
        SetExpressCheckoutResponseType response = paypalPaymentService.setExpressCheckout(request);

        assertNotNull("Response from web service must not be null", response);
        assertEquals("Response must fail", response.getAck().value(), PaypalConstants.STATUS_FAILURE);
    }

    /*@Test
    public void testGetExpressCheckoutDetails() {
        GetExpressCheckoutDetailsReq req = createGetExprCheckDetRequest();
		  GetExpressCheckoutDetailsRequestType request = new GetExpressCheckoutDetailsRequestType();
		  req.setGetExpressCheckoutDetailsRequest(request);
        when(paypalAPIAAInterfaceMock.getExpressCheckoutDetails(req, headers))
                .thenReturn(getExpressCheckoutDetailsResponse);

        GetExpressCheckoutDetailsResponseType response = paypalPaymentServicePartMock.getExpressCheckoutDetails(request);
        assertEquals("Responses must be equals", getExpressCheckoutDetailsResponse, response);
        verify(paypalAPIAAInterfaceMock, times(1)).getExpressCheckoutDetails(req, headers);
    }*/

    /*@Test
    public void testDoExpressCheckoutPayment() {
        DoExpressCheckoutPaymentReq req = createDoExprCheckRequest();
		  DoExpressCheckoutPaymentRequestType request = new DoExpressCheckoutPaymentRequestType();
		  req.setDoExpressCheckoutPaymentRequest(request);
        when(paypalAPIAAInterfaceMock.doExpressCheckoutPayment(req, headers))
                .thenReturn(doExpressCheckoutPaymentResponse);
        DoExpressCheckoutPaymentResponseType response = paypalPaymentServicePartMock.doExpressCheckoutPayment(request);
        assertEquals("Responses must be equals", doExpressCheckoutPaymentResponse, response);
        verify(paypalAPIAAInterfaceMock, times(1)).doExpressCheckoutPayment(req, headers);
    }*/

    private GetExpressCheckoutDetailsReq createGetExprCheckDetRequest() {
        GetExpressCheckoutDetailsReq request = new GetExpressCheckoutDetailsReq();
        GetExpressCheckoutDetailsRequestType reqType = new GetExpressCheckoutDetailsRequestType();
        reqType.setVersion(PaypalConstants.SOAP_API_VERSION);
        reqType.setToken("4535989-FG");

        request.setGetExpressCheckoutDetailsRequest(reqType);
        return request;
    }

    private DoExpressCheckoutPaymentReq createDoExprCheckRequest() {
        DoExpressCheckoutPaymentReq request = new DoExpressCheckoutPaymentReq();

        DoExpressCheckoutPaymentRequestType reqType = new DoExpressCheckoutPaymentRequestType();
        reqType.setVersion(PaypalConstants.SOAP_API_VERSION);

        DoExpressCheckoutPaymentRequestDetailsType details = new DoExpressCheckoutPaymentRequestDetailsType();
        details.setToken("4535989-FG");
        details.setPayerID("4342342");
        PaymentDetailsType paymentDetails = new PaymentDetailsType();
        details.getPaymentDetails().add(paymentDetails);

        reqType.setDoExpressCheckoutPaymentRequestDetails(details);
        request.setDoExpressCheckoutPaymentRequest(reqType);
        return request;
    }

    private void createMocks() {
        headers = new Holder<CustomSecurityHeaderType>();

        //create mock for web service interface calls
        paypalAPIAAInterfaceMock = mock(PayPalAPIAAInterface.class);

        //mock getAAInterface and getCredentialsHolder method to
        //return mock and stub object respectively
        paypalPaymentServicePartMock = spy(paypalPaymentService);
        doReturn(paypalAPIAAInterfaceMock).when(paypalPaymentServicePartMock).getAAInterface();
        doReturn(headers).when(paypalPaymentServicePartMock).getCredentialsHolder();

        getExpressCheckoutDetailsResponse = new GetExpressCheckoutDetailsResponseType();
        getExpressCheckoutDetailsResponse.setAck(AckCodeType.SUCCESS);
        //add fields
        doExpressCheckoutPaymentResponse = new DoExpressCheckoutPaymentResponseType();
        doExpressCheckoutPaymentResponse.setAck(AckCodeType.SUCCESS);
    }

    /**
     * Utility method to create cart and set it as session cart
     */
    private void createSessionCart() {
        final CartModel cart = modelService.create(CartModel.class);
        cart.setUser(userService.getCurrentUser());
        cart.setCurrency(commonI18NService.getCurrentCurrency());
        cart.setDate(new Date());
        cart.setNet(Boolean.TRUE);
        modelService.save(cart);
        cartService.setSessionCart(cart);
    }

    private void addProductToSessionCart() {
        CartModel cart = cartService.getSessionCart();
        ProductModel product0 = productService.getProductForCode("testProduct0");
        cartService.addNewEntry(cart, product0, 2, null);
        cartService.saveOrder(cart);
    }

    /**
     * test user for changing the current cart user
     */
    private void createTestUser() {
        final UserModel newCustomer = modelService.create(UserModel.class);
        newCustomer.setUid(TEST_USER_ID);
        //newCustomer.setCustomerID(TEST_USER_ID);
        newCustomer.setSessionCurrency(commonI18NService.getCurrency("USD"));
        newCustomer.setSessionLanguage(commonI18NService.getLanguage("en"));
        newCustomer.setGroups(Collections.<PrincipalGroupModel>singleton(userService.getUserGroupForUID("customergroup")));
        modelService.save(newCustomer);
    }

    private SetExpressCheckoutRequestType createSetExprCheckRequest() {
        SetExpressCheckoutRequestType request = new SetExpressCheckoutRequestType();

        request.setVersion(PaypalConstants.SOAP_API_VERSION);
        SetExpressCheckoutRequestDetailsType details = new SetExpressCheckoutRequestDetailsType();

        details.setSolutionType(SolutionTypeType.fromValue(PaypalConstants.DEFAULT_SOLUTION_TYPE_NAME));
        details.setPaymentAction(PaymentActionCodeType.fromValue(PaypalConstants.DEFAULT_PAYMENT_ACTION_NAME));

        details.setReturnURL("http://store.com/success-url");
        details.setCancelURL("http://store.com/cancel-url");

        CartModel cart = getCurrentCart();
        PaymentDetailsType paymentDetails = createPaymentDetails(cart);
        details.getPaymentDetails().add(paymentDetails);

        ///add payment details
        request.setSetExpressCheckoutRequestDetails(details);

        return request;
    }

    private PaymentDetailsType createPaymentDetails(final CartModel cart) {
        final String currencyIsoCode = cart.getCurrency().getIsocode();
        final CurrencyCodeType currencyCode = CurrencyCodeType.valueOf(currencyIsoCode);

        double totalDiscount = 0;
        double itemTotalPriceBase = 0;

        final PaymentDetailsType details = new PaymentDetailsType();
        final List<AbstractOrderEntryModel> entries = cart.getEntries();
        for (final AbstractOrderEntryModel entry : entries) {

            final PaymentDetailsItemType detailsItem = createPaymentDetailsItem(entry, currencyCode);
            itemTotalPriceBase += entry.getBasePrice() * entry.getQuantity();

            //adding item to list of items
            details.getPaymentDetailsItem().add(detailsItem);
            final List<DiscountValue> discounts = entry.getDiscountValues();
            if (discounts != null && !discounts.isEmpty()) {
                totalDiscount += discounts.get(0).getAppliedValue();
            }
        }

        //setting known params
        final BasicAmountType itemTotal = createBasicAmountType(itemTotalPriceBase, currencyCode);
        final BasicAmountType orderTotal = createBasicAmountType(cart.getTotalPrice(), currencyCode);

        // Tax excluded according to
        // https://wiki.hybris.com/display/forum/Total+Price+doesn%27t+include+tax+when+using+Net+pricing
        //	final BasicAmountType taxTotal = createBasicAmountType(cart.getTotalTax(), currencyCode);

        final BasicAmountType shippingTotal = createBasicAmountType(cart.getDeliveryCost(), currencyCode);

        //shippingDiscount has to be checked on correct fillment
        final BasicAmountType shippingDiscount = createBasicAmountType(-totalDiscount, currencyCode);
        //handlingTotal isn't set yet
        final BasicAmountType handlingTotal = createBasicAmountType(Double.valueOf(0), currencyCode);
        //insuranceTotal isn't set yet
        final BasicAmountType insuranceTotal = createBasicAmountType(Double.valueOf(0), currencyCode);

        final String allowNote = "1";

        details.setItemTotal(itemTotal);
        //	details.setTaxTotal(taxTotal);
        details.setShippingTotal(shippingTotal);
        details.setShippingDiscount(shippingDiscount);
        details.setHandlingTotal(handlingTotal);
        details.setInsuranceTotal(insuranceTotal);
        details.setOrderTotal(orderTotal);
        details.setNoteText(allowNote);

        return details;
    }

    protected PaymentDetailsItemType createPaymentDetailsItem(AbstractOrderEntryModel entry, CurrencyCodeType currencyCode) {
        //getting item info from entry
        final String name = entry.getProduct().getName();
        final String number = entry.getProduct().getCode();
        final Long quantity = entry.getQuantity();
        final String description = entry.getInfo();
        final Double unitPrice = entry.getBasePrice();

        final BasicAmountType amount = new BasicAmountType();
        amount.setValue(PaypalStringUtils.formatNumber(unitPrice));
        amount.setCurrencyID(currencyCode);

        final PaymentDetailsItemType detailsItem = new PaymentDetailsItemType();
        detailsItem.setName(name);
        detailsItem.setNumber(number);
        detailsItem.setQuantity(BigInteger.valueOf(quantity));
        detailsItem.setDescription(description);
        detailsItem.setAmount(amount);

        return detailsItem;
    }

    protected BasicAmountType createBasicAmountType(double amount, CurrencyCodeType currencyCode) {
        final BasicAmountType basicAmount = new BasicAmountType();
        basicAmount.setValue(PaypalStringUtils.formatNumber(amount));
        basicAmount.setCurrencyID(currencyCode);
        return basicAmount;
    }

    protected CartModel getCurrentCart() {
        Cart cart = JaloSession.getCurrentSession().getCart();
        return modelService.get(cart);
    }

}
