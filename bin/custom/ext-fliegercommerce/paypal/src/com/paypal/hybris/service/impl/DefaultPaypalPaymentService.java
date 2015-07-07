package com.paypal.hybris.service.impl;

import com.ebay.api.*;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;

import com.ebay.logging.SOAPLoggingHandler;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.service.PaypalPaymentService;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

/**
 * @author Aliaksei_Sery (EPAM Systems)
 */
public class DefaultPaypalPaymentService implements PaypalPaymentService {
    
    private final static Logger LOG = Logger.getLogger(DefaultPaypalPaymentService.class);
    private ConfigurationService configurationService;
    
    
    @Override
    public RefundTransactionResponseType
            refundTransaction(final RefundTransactionRequestType request) {
        RefundTransactionResponseType response;
        try {
            RefundTransactionReq req = new RefundTransactionReq();
            req.setRefundTransactionRequest(request);
            response = getInterface().refundTransaction(req, getCredentialsHolder());
        } catch (final Exception e) {
            LOG.error("Error during 'refundTransaction' web service call: " + e);
            response = new RefundTransactionResponseType();
            addGeneralErrorInfo(response);
        }
        return response;
    }
    
    
    protected PayPalAPIAAInterface getAAInterface() {
        final PayPalAPIInterfaceService pp = new PayPalAPIInterfaceService();
        final PayPalAPIAAInterface pinterface = pp.getPayPalAPIAA();
        // override the default WSDL end point
        ((BindingProvider) pinterface).getRequestContext().put(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                getConfigurationService().getConfiguration()
                        .getString(PaypalConstants.SETT_ENDPOINT));
        addLoggingHandler((BindingProvider) pinterface);
        return pinterface;
    }
    
    
    protected PayPalAPIInterface getInterface() {
        final PayPalAPIInterfaceService pp = new PayPalAPIInterfaceService();
        final PayPalAPIInterface pinterface = pp.getPayPalAPI();
        // override the default WSDL end point
        ((BindingProvider) pinterface).getRequestContext().put(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                getConfigurationService().getConfiguration()
                        .getString(PaypalConstants.SETT_ENDPOINT));
        addLoggingHandler((BindingProvider) pinterface);
        return pinterface;
    }
    
    
    private void addLoggingHandler(final BindingProvider pinterface) {
        final Binding binding = pinterface.getBinding();
        List handlerList = binding.getHandlerChain();
        if (handlerList == null) {
            handlerList = new ArrayList();
        }
        handlerList.add(new SOAPLoggingHandler());
        binding.setHandlerChain(handlerList);
    }
    
    
    /**
     * @see com.paypal.hybris.service.PaypalPaymentService#setExpressCheckout(SetExpressCheckoutRequestType)
     */
    @Override
    public SetExpressCheckoutResponseType setExpressCheckout(
            final SetExpressCheckoutRequestType request) {
        SetExpressCheckoutResponseType response;
        try {
            SetExpressCheckoutReq req = new SetExpressCheckoutReq();
            req.setSetExpressCheckoutRequest(request);
            response = getAAInterface().setExpressCheckout(req, getCredentialsHolder());
        } catch (final Exception e) {
            LOG.error("Error during 'setExpressCheckout' web service call: " + e);
            response = new SetExpressCheckoutResponseType();
            addGeneralErrorInfo(response);
        }
        return response;
    }
    
    
    /**
     * @see com.paypal.hybris.service.PaypalPaymentService#getExpressCheckoutDetails(GetExpressCheckoutDetailsRequestType)
     */
    @Override
    public GetExpressCheckoutDetailsResponseType getExpressCheckoutDetails(
            final GetExpressCheckoutDetailsRequestType request) {
        GetExpressCheckoutDetailsResponseType response;
        try {
            GetExpressCheckoutDetailsReq req = new GetExpressCheckoutDetailsReq();
            req.setGetExpressCheckoutDetailsRequest(request);
            response = getAAInterface().getExpressCheckoutDetails(req, getCredentialsHolder());
        } catch (final Exception e) {
            LOG.error("Error during 'getExpressCheckoutDetails' web service call: " + e);
            response = new GetExpressCheckoutDetailsResponseType();
            addGeneralErrorInfo(response);
        }
        return response;
    }
    
    
    /**
     * @see com.paypal.hybris.service.PaypalPaymentService#doExpressCheckoutPayment(com.ebay.api.DoExpressCheckoutPaymentRequestType)
     */
    @Override
    public DoExpressCheckoutPaymentResponseType doExpressCheckoutPayment(
            final DoExpressCheckoutPaymentRequestType request) {
        DoExpressCheckoutPaymentResponseType response;
        try {
            DoExpressCheckoutPaymentReq req = new DoExpressCheckoutPaymentReq();
            req.setDoExpressCheckoutPaymentRequest(request);
            response = getAAInterface().doExpressCheckoutPayment(req, getCredentialsHolder());
        } catch (final Exception e) {
            LOG.error("Error during 'getExpressCheckoutDetails' web service call: " + e);
            response = new DoExpressCheckoutPaymentResponseType();
            addGeneralErrorInfo(response);
        }
        return response;
    }
    
    
    /**
     * @see com.paypal.hybris.service.PaypalPaymentService#doAuthorization(DoAuthorizationRequestType)
     */
    @Override
    public DoAuthorizationResponseType doAuthorization(final DoAuthorizationRequestType request) {
        DoAuthorizationResponseType response;
        try {
            DoAuthorizationReq req = new DoAuthorizationReq();
            req.setDoAuthorizationRequest(request);
            response = getAAInterface().doAuthorization(req, getCredentialsHolder());
        } catch (final Exception e) {
            LOG.error("Error during 'doAuthorization' web service call: " + e);
            response = new DoAuthorizationResponseType();
            addGeneralErrorInfo(response);
        }
        return response;
    }
    
    
    /**
     * @see com.paypal.hybris.service.PaypalPaymentService#doReauthorization(DoReauthorizationRequestType)
     */
    @Override
    public DoReauthorizationResponseType
            doReauthorization(final DoReauthorizationRequestType request) {
        DoReauthorizationResponseType response = null;
        try {
            DoReauthorizationReq req = new DoReauthorizationReq();
            response = getAAInterface().doReauthorization(req, getCredentialsHolder());
        } catch (final Exception e) {
            LOG.error("Error during 'doReauthorization' web service call: " + e);
            response = new DoReauthorizationResponseType();
            addGeneralErrorInfo(response);
        }
        return response;
    }
    
    
    /**
     * @see com.paypal.hybris.service.PaypalPaymentService#transactionSearch(TransactionSearchRequestType)
     */
    @Override
    public TransactionSearchResponseType
            transactionSearch(final TransactionSearchRequestType request) {
        TransactionSearchResponseType response = null;
        try {
            TransactionSearchReq req = new TransactionSearchReq();
            req.setTransactionSearchRequest(request);
            response = getInterface().transactionSearch(req, getCredentialsHolder());
        } catch (final Exception e) {
            LOG.error("Error during 'transactionSearch' web service call: " + e);
            response = new TransactionSearchResponseType();
            addGeneralErrorInfo(response);
        }
        return response;
    }
    
    
    /**
     * @see com.paypal.hybris.service.PaypalPaymentService#getTransactionDetails(GetTransactionDetailsRequestType)
     */
    @Override
    public GetTransactionDetailsResponseType getTransactionDetails(
            final GetTransactionDetailsRequestType request) {
        GetTransactionDetailsResponseType response = null;
        try {
            GetTransactionDetailsReq req = new GetTransactionDetailsReq();
            req.setGetTransactionDetailsRequest(request);
            response = getInterface().getTransactionDetails(req, getCredentialsHolder());
        } catch (Exception e) {
            LOG.error("Error during 'getTransactionDetails' web service call: " + e);
            response = new GetTransactionDetailsResponseType();
            addGeneralErrorInfo(response);
        }
        return response;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.paypal.hybris.service.PaypalService#doVoid(com.paypal.hybris.soap.params
     * .impl.DoVoidParams)
     */
    @Override
    public DoVoidResponseType doVoid(final DoVoidRequestType request) {
        DoVoidResponseType response = null;
        try {
            DoVoidReq req = new DoVoidReq();
            req.setDoVoidRequest(request);
            response = getAAInterface().doVoid(req, getCredentialsHolder());
        } catch (final Exception e) {
            LOG.error("Error during 'doVoid' web service call: " + e);
            response = new DoVoidResponseType();
            addGeneralErrorInfo(response);
        }
        return response;
    }
    
    
    /**
     * @see com.paypal.hybris.service.PaypalPaymentService#doCapture(com.ebay.api.DoCaptureRequestType)
     */
    @Override
    public DoCaptureResponseType doCapture(final DoCaptureRequestType request) {
        DoCaptureResponseType response;
        try {
            DoCaptureReq req = new DoCaptureReq();
            req.setDoCaptureRequest(request);
            response = getAAInterface().doCapture(req, getCredentialsHolder());
        } catch (final Exception e) {
            LOG.error("Error during 'doCapture' web service call: " + e);
            response = new DoCaptureResponseType();
            addGeneralErrorInfo(response);
        }
        return response;
    }
    
    
    protected Holder<CustomSecurityHeaderType> getCredentialsHolder() {
        final Configuration configuration = getConfigurationService().getConfiguration();
        final UserIdPasswordType user = new UserIdPasswordType();
        user.setUsername(configuration.getString(PaypalConstants.SETT_USERNAME));
        user.setPassword(configuration.getString(PaypalConstants.SETT_PASSWORD));
        user.setSignature(configuration.getString(PaypalConstants.SETT_SIGNATURE));
        final CustomSecurityHeaderType securityHeader = new CustomSecurityHeaderType();
        securityHeader.setCredentials(user);
        final Holder<CustomSecurityHeaderType> holder =
                new Holder<CustomSecurityHeaderType>(securityHeader);
        return holder;
    }
    
    
    public ConfigurationService getConfigurationService() {
        return configurationService;
    }
    
    
    public void setConfigurationService(final ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
    
    
    private void addGeneralErrorInfo(final AbstractResponseType response) {
        final ErrorType generalError = new ErrorType();
        generalError.setErrorCode("00000");
        generalError.setSeverityCode(SeverityCodeType.ERROR);
        generalError.setShortMessage("Error during service method call");
        generalError.setLongMessage("Error during communication with service");
        response.getErrors().add(generalError);
        response.setTimestamp(new XMLGregorianCalendarImpl((GregorianCalendar) GregorianCalendar
                .getInstance()));
        response.setAck(AckCodeType.FAILURE);
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.paypal.hybris.service.PaypalPaymentService#doReferenceTransaction(com.ebay.api.
     * DoReferenceTransactionReq)
     */
    @Override
    public DoReferenceTransactionResponseType doReferenceTransaction(
            final DoReferenceTransactionRequestType request) {
        LOG.debug("Initiate payment with reference transaction");
        DoReferenceTransactionResponseType response = null;
        try {
            DoReferenceTransactionReq req = new DoReferenceTransactionReq();
            req.setDoReferenceTransactionRequest(request);
            response = getAAInterface().doReferenceTransaction(req, getCredentialsHolder());
        } catch (final Exception e) {
            LOG.error("Error during 'doReferenceTransaction' web service call: " + e);
            response = new DoReferenceTransactionResponseType();
            addGeneralErrorInfo(response);
        }
        return response;
    }
}
