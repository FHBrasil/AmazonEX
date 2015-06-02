package com.paypal.hybris.addon.facades;

import com.ebay.api.*;
import com.paypal.hybris.data.GetExpressCheckoutDetailsRequestData;
import com.paypal.hybris.data.GetExpressCheckoutDetailsResultData;
import com.paypal.hybris.jalo.PaypalPaymentInfo;
import com.paypal.hybris.model.PaypalPaymentInfoModel;
import com.paypal.hybris.service.impl.DefaultPaypalPaymentService;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.CustomerModel;
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
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class PayPalCheckoutFacadeIntegrationTest extends ServicelayerTransactionalTest {

    private static final String TEST_USER_ID = "cartTestUser";
    private static final String TEST_PRODUCT_A_ID = "pA";
    private static final String CURRENCY_ISO_CODE = "EUR";
    private static final String SECURITY_CODE = "3534534-DFFG";

    @Resource
    PayPalCheckoutFacade payPalCheckoutFacade;

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
    private DoExpressCheckoutPaymentResponseType doExpressCheckoutPaymentResponse;

    @Before
    public void setUp() throws Exception
    {
        createCoreData();
        createTestCatalog();
        createSessionCart();
        createMocks();
    }

    @Test
    public void testAuthorizePayment() {
        boolean isAuthorized = payPalCheckoutFacade.authorizePayment(SECURITY_CODE);
        assertTrue(isAuthorized);
    }

    private void createMocks()
    {
        /*doExpressCheckoutPaymentResponse = new DoExpressCheckoutPaymentResponseType();
        doExpressCheckoutPaymentResponse.setAck(AckCodeType.SUCCESS);
        doExpressCheckoutPaymentResponse.setTimestamp(new XMLGregorianCalendarImpl((GregorianCalendar) GregorianCalendar.getInstance()));

        paypalPaymentServicePartMock = spy(paypalPaymentService);
        doReturn(doExpressCheckoutPaymentResponse).when(paypalPaymentServicePartMock).doExpressCheckoutPayment(any(DoExpressCheckoutPaymentRequestType.class));

        payPalCheckoutFacade.setPayPalPaymentService(paypalPaymentServicePartMock);*/
    }

    private void createTestCatalog() throws ImpExException
    {
        importCsv("/paypaladdon/test/testOrderCalculations.csv", "utf-8");
    }

    /**
     * Utility method to create cart and set it as session cart
     */
    private void createSessionCart()
    {
        final CartModel cart = modelService.create(CartModel.class);

        final CustomerModel customer = modelService.create(CustomerModel.class);
        customer.setUid(TEST_USER_ID);
        customer.setSessionCurrency(commonI18NService.getCurrency(CURRENCY_ISO_CODE));
        customer.setSessionLanguage(commonI18NService.getLanguage("en"));
        customer.setGroups(Collections.<PrincipalGroupModel>singleton(userService.getUserGroupForUID("customergroup")));
        customer.setName("Test customer");
        modelService.save(customer);

        userService.setCurrentUser(customer);

        cart.setUser(customer);
        cart.setCurrency(commonI18NService.getCurrency(CURRENCY_ISO_CODE));
        cart.setDate(new Date());
        cart.setNet(Boolean.TRUE);

        PaypalPaymentInfoModel paypalPaymentInfo = modelService.create(PaypalPaymentInfoModel.class);
        paypalPaymentInfo.setToken("324234234-FD");
        modelService.save(paypalPaymentInfo);
        cart.setPaymentInfo(paypalPaymentInfo);

        modelService.save(cart);
        cartService.setSessionCart(cart);
    }

}
