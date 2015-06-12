package com.paypal.hybris.service.impl;

import com.ebay.api.*;
import org.junit.Before;
import org.junit.Test;

import javax.xml.ws.Holder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class DefaultPaypalPaymentServiceUnitTest {

    private PayPalAPIAAInterface paypalAPIAAInterfaceMock;
    private DefaultPaypalPaymentService paypalPaymentService;

    private Holder<CustomSecurityHeaderType> headers;

    @Before
    public void setUp() {
        headers = new Holder<CustomSecurityHeaderType>();

        //create mock for web service interface calls
        paypalAPIAAInterfaceMock = mock(PayPalAPIAAInterface.class);

        //mock getAAInterface and getCredentialsHolder method to
        //return mock and stub object respectively
        paypalPaymentService = spy(new DefaultPaypalPaymentService());
        doReturn(paypalAPIAAInterfaceMock).when(paypalPaymentService).getAAInterface();
        doReturn(headers).when(paypalPaymentService).getCredentialsHolder();
    }

    @Test
    public void testSetExpressCheckout()
	 {
        //create operation request and response objects
        SetExpressCheckoutReq setExpressCheckoutReq = new SetExpressCheckoutReq();
		  SetExpressCheckoutRequestType setExpressCheckoutRequest = new SetExpressCheckoutRequestType();
		  setExpressCheckoutReq.setSetExpressCheckoutRequest(setExpressCheckoutRequest);
        SetExpressCheckoutResponseType setExpressCheckoutRes = new SetExpressCheckoutResponseType();

        when(paypalAPIAAInterfaceMock.setExpressCheckout(setExpressCheckoutReq, headers)).thenReturn(setExpressCheckoutRes);

        SetExpressCheckoutResponseType response = paypalPaymentService.setExpressCheckout(setExpressCheckoutRequest);
        assertNotNull("Response should not be null", response);
        assertEquals("Response objects must be equels", response, setExpressCheckoutRes);
        verify(paypalAPIAAInterfaceMock, times(1)).setExpressCheckout(setExpressCheckoutReq, headers);
    }

    /*@Test
    public void testGetExpressCheckoutDetails() {
        //create operation request and response objects
        GetExpressCheckoutDetailsReq getExpressCheckoutDetailsReq = new GetExpressCheckoutDetailsReq();
        GetExpressCheckoutDetailsResponseType getExpressCheckoutDetailsRes = new GetExpressCheckoutDetailsResponseType();

        when(paypalAPIAAInterfaceMock.getExpressCheckoutDetails(getExpressCheckoutDetailsReq, headers))
                .thenReturn(getExpressCheckoutDetailsRes);

        GetExpressCheckoutDetailsResponseType response = paypalPaymentService.getExpressCheckoutDetails(getExpressCheckoutDetailsReq);
        assertNotNull("Response should not be null", response);
        assertEquals("Response objects must be equels", response, getExpressCheckoutDetailsRes);
        verify(paypalAPIAAInterfaceMock, times(1)).getExpressCheckoutDetails(getExpressCheckoutDetailsReq, headers);
    }*/

    /*@Test
    public void testDoExpressCheckoutPayment() {
        //create operation request and response objects
        DoExpressCheckoutPaymentReq doExpressCheckoutPaymentReq = new DoExpressCheckoutPaymentReq();
        DoExpressCheckoutPaymentResponseType doExpressCheckoutPaymentRes = new DoExpressCheckoutPaymentResponseType();

        when(paypalAPIAAInterfaceMock.doExpressCheckoutPayment(doExpressCheckoutPaymentReq, headers))
                .thenReturn(doExpressCheckoutPaymentRes);

        DoExpressCheckoutPaymentResponseType response = paypalPaymentService.doExpressCheckoutPayment(doExpressCheckoutPaymentReq);
        assertNotNull("Response should not be null", response);
        assertEquals("Response objects must be equels", response, doExpressCheckoutPaymentRes);
        verify(paypalAPIAAInterfaceMock, times(1)).doExpressCheckoutPayment(doExpressCheckoutPaymentReq, headers);
    }*/

    /*@Test
    public void testDoCapture() {
        DoCaptureReq doCaptureReq = new DoCaptureReq();
        DoCaptureResponseType doCaptureRes = new DoCaptureResponseType();

        when(paypalAPIAAInterfaceMock.doCapture(doCaptureReq, headers)).thenReturn(doCaptureRes);

        DoCaptureResponseType response = paypalPaymentService.doCapture(doCaptureReq);
        assertNotNull("Response should not be null", response);
        assertEquals("Response objects must be equals", response, doCaptureRes);
        verify(paypalAPIAAInterfaceMock, times(1)).doCapture(doCaptureReq, headers);
    }*/

    /*@Test
    public void testDoAuthorization() {
        DoAuthorizationReq doAuthorizationReq = new DoAuthorizationReq();
        DoAuthorizationResponseType doAuthorizationRes = new DoAuthorizationResponseType();

        when(paypalAPIAAInterfaceMock.doAuthorization(doAuthorizationReq, headers)).thenReturn(doAuthorizationRes);

        DoAuthorizationResponseType response = paypalPaymentService.doAuthorization(doAuthorizationReq);
        assertNotNull("Response should not be null", response);
        assertEquals("Response objects must be equals", response, doAuthorizationRes);
        verify(paypalAPIAAInterfaceMock, times(1)).doAuthorization(doAuthorizationReq, headers);
    }*/

}
