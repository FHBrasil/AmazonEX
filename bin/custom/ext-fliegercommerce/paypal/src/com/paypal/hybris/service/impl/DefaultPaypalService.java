package com.paypal.hybris.service.impl;


import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.paypal.hybris.config.PaypalConfigManager;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.model.PaypalOperationModel;
import com.paypal.hybris.service.PaypalService;
import com.paypal.hybris.soap.gen.CustomSecurityHeaderType;
import com.paypal.hybris.soap.gen.DoAuthorizationReq;
import com.paypal.hybris.soap.gen.DoAuthorizationRequestType;
import com.paypal.hybris.soap.gen.DoAuthorizationResponseType;
import com.paypal.hybris.soap.gen.DoCaptureReq;
import com.paypal.hybris.soap.gen.DoCaptureRequestType;
import com.paypal.hybris.soap.gen.DoCaptureResponseType;
import com.paypal.hybris.soap.gen.DoExpressCheckoutPaymentReq;
import com.paypal.hybris.soap.gen.DoExpressCheckoutPaymentRequestDetailsType;
import com.paypal.hybris.soap.gen.DoExpressCheckoutPaymentRequestType;
import com.paypal.hybris.soap.gen.DoExpressCheckoutPaymentResponseType;
import com.paypal.hybris.soap.gen.DoReauthorizationReq;
import com.paypal.hybris.soap.gen.DoReauthorizationRequestType;
import com.paypal.hybris.soap.gen.DoReauthorizationResponseType;
import com.paypal.hybris.soap.gen.DoVoidReq;
import com.paypal.hybris.soap.gen.DoVoidRequestType;
import com.paypal.hybris.soap.gen.DoVoidResponseType;
import com.paypal.hybris.soap.gen.GetExpressCheckoutDetailsReq;
import com.paypal.hybris.soap.gen.GetExpressCheckoutDetailsRequestType;
import com.paypal.hybris.soap.gen.GetExpressCheckoutDetailsResponseType;
import com.paypal.hybris.soap.gen.GetTransactionDetailsReq;
import com.paypal.hybris.soap.gen.GetTransactionDetailsRequestType;
import com.paypal.hybris.soap.gen.GetTransactionDetailsResponseType;
import com.paypal.hybris.soap.gen.PayPalAPIAAInterface;
import com.paypal.hybris.soap.gen.PayPalAPIInterface;
import com.paypal.hybris.soap.gen.PayPalAPIInterfaceService;
import com.paypal.hybris.soap.gen.PaymentActionCodeType;
import com.paypal.hybris.soap.gen.PaymentDetailsType;
import com.paypal.hybris.soap.gen.RefundTransactionReq;
import com.paypal.hybris.soap.gen.RefundTransactionRequestType;
import com.paypal.hybris.soap.gen.RefundTransactionResponseType;
import com.paypal.hybris.soap.gen.RefundType;
import com.paypal.hybris.soap.gen.SetExpressCheckoutReq;
import com.paypal.hybris.soap.gen.SetExpressCheckoutRequestDetailsType;
import com.paypal.hybris.soap.gen.SetExpressCheckoutRequestType;
import com.paypal.hybris.soap.gen.SetExpressCheckoutResponseType;
import com.paypal.hybris.soap.gen.TransactionSearchReq;
import com.paypal.hybris.soap.gen.TransactionSearchRequestType;
import com.paypal.hybris.soap.gen.TransactionSearchResponseType;
import com.paypal.hybris.soap.gen.UserIdPasswordType;
import com.paypal.hybris.soap.params.PaypalOperationParams;
import com.paypal.hybris.soap.params.impl.DoAuthorizationParams;
import com.paypal.hybris.soap.params.impl.DoCaptureParams;
import com.paypal.hybris.soap.params.impl.DoExpressCheckoutPaymentParams;
import com.paypal.hybris.soap.params.impl.DoReauthorizationParams;
import com.paypal.hybris.soap.params.impl.DoVoidParams;
import com.paypal.hybris.soap.params.impl.GetExpressCheckoutDetailsParams;
import com.paypal.hybris.soap.params.impl.GetTransactionDetailsParams;
import com.paypal.hybris.soap.params.impl.RefundTransactionParams;
import com.paypal.hybris.soap.params.impl.SetExpressCheckoutParams;
import com.paypal.hybris.soap.params.impl.TransactionSearchParams;
import com.paypal.hybris.util.StrUtil;


/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
@Component("paypalService")
public class DefaultPaypalService implements PaypalService {

private static final Logger LOG = Logger.getLogger("DefaultPaypalService");

@Resource
private ModelService modelService;

@Resource
private PaypalConfigManager paypalConfigManager;

@Override
public void refundTransaction(final RefundTransactionParams params) {

	final RefundTransactionRequestType reqType = new RefundTransactionRequestType();
	reqType.setTransactionID(params.getTransactionId());
	// reqType.setPayerID(params.getPayerId());
	reqType.setRefundType(RefundType.FULL);
	reqType.setVersion(PaypalConstants.SOAP_API_VERSION);
	final RefundTransactionReq refundTransactionRequest = new RefundTransactionReq();
	refundTransactionRequest.setRefundTransactionRequest(reqType);

	final PayPalAPIInterface pinterface = getInterface();

	final RefundTransactionResponseType response = pinterface.refundTransaction(
			refundTransactionRequest, getCredentialsHolder());

	params.setRequest(reqType);
	params.setResponse(response);

	saveOperationParams(params);
}


private void saveOperationParams(final PaypalOperationParams params) {

	LOG.info(params.toJson());

	if (StrUtil.isTrue(paypalConfigManager
			.getProperty(PaypalConstants.PAYPAL_OPERATION_PERSIST))) {

		// Create and save operation parameters model
		final PaypalOperationModel operation = modelService
				.create(PaypalOperationModel.class);
		operation.setOperationName(params.getOperationName());
		operation.setRequest(params.getRequestAsJson());
		operation.setResponse(params.getResponseAsJson());
		modelService.save(operation);
	}
}


@Override
public void getTransactionDetails(final GetTransactionDetailsParams params) {

	final GetTransactionDetailsRequestType reqType = new GetTransactionDetailsRequestType();
	reqType.setTransactionID(params.getTransactionId());
	reqType.setVersion(PaypalConstants.SOAP_API_VERSION);
	final GetTransactionDetailsReq getTransactionDetailsRequest = new GetTransactionDetailsReq();
	getTransactionDetailsRequest.setGetTransactionDetailsRequest(reqType);

	final PayPalAPIInterface pinterface = getInterface();
	final GetTransactionDetailsResponseType response = pinterface
			.getTransactionDetails(getTransactionDetailsRequest,
					getCredentialsHolder());

	params.setRequest(reqType);
	params.setResponse(response);

	saveOperationParams(params);
}


private PayPalAPIAAInterface getAAInterface() {

	final PayPalAPIInterfaceService pp = new PayPalAPIInterfaceService();
	final PayPalAPIAAInterface pinterface = pp.getPayPalAPIAA();

	// override the default WSDL end point
	((BindingProvider) pinterface).getRequestContext().put(
			BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
			paypalConfigManager.getProperty(PaypalConstants.SETT_ENDPOINT));

	return pinterface;
}


private PayPalAPIInterface getInterface() {

	final PayPalAPIInterfaceService pp = new PayPalAPIInterfaceService();
	final PayPalAPIInterface pinterface = pp.getPayPalAPI();

	// override the default WSDL end point
	((BindingProvider) pinterface).getRequestContext().put(
			BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
			paypalConfigManager.getProperty(PaypalConstants.SETT_ENDPOINT));

	return pinterface;
}


/**
 * @see com.paypal.hybris.service.PaypalService#setExpressCheckout(SetExpressCheckoutParams)
 */
@Override
public void setExpressCheckout(final SetExpressCheckoutParams params) {

	final SetExpressCheckoutReq req = new SetExpressCheckoutReq();
	final SetExpressCheckoutRequestType reqType = new SetExpressCheckoutRequestType();
	final SetExpressCheckoutRequestDetailsType details = new SetExpressCheckoutRequestDetailsType();

	details.setAddress(params.getAddr());
	details.setSolutionType(params.getSolutionType());
	details.setPaymentAction(params.getPaymentAction());
	details.getPaymentDetails().add(params.getDetails());


	final String uid = params.getUid();
	details.setReturnURL(params.getReturnUrl() + uid);
	details.setCancelURL(params.getCancelUrl() + uid);


	reqType.setVersion(PaypalConstants.SOAP_API_VERSION);
	reqType.setSetExpressCheckoutRequestDetails(details);
	req.setSetExpressCheckoutRequest(reqType);

	final SetExpressCheckoutResponseType resp = getAAInterface()
			.setExpressCheckout(req, getCredentialsHolder());

	params.setRequest(reqType);
	params.setResponse(resp);

	saveOperationParams(params);
}


private Holder<CustomSecurityHeaderType> getCredentialsHolder() {

	final UserIdPasswordType user = new UserIdPasswordType();

	user.setUsername(paypalConfigManager
			.getProperty(PaypalConstants.SETT_USERNAME));
	user.setPassword(paypalConfigManager
			.getProperty(PaypalConstants.SETT_PASSWORD));
	user.setSignature(paypalConfigManager
			.getProperty(PaypalConstants.SETT_SIGNATURE));

	final CustomSecurityHeaderType securityHeader = new CustomSecurityHeaderType();
	securityHeader.setCredentials(user);

	final Holder<CustomSecurityHeaderType> holder = new Holder<CustomSecurityHeaderType>(
			securityHeader);

	return holder;
}


/**
 * @see com.paypal.hybris.service.PaypalService#getExpressCheckoutDetails(GetExpressCheckoutDetailsParams)
 */
@Override
public void getExpressCheckoutDetails(
		final GetExpressCheckoutDetailsParams params) {

	final GetExpressCheckoutDetailsReq req = new GetExpressCheckoutDetailsReq();
	final GetExpressCheckoutDetailsRequestType reqType = new GetExpressCheckoutDetailsRequestType();

	reqType.setVersion(PaypalConstants.SOAP_API_VERSION);
	req.setGetExpressCheckoutDetailsRequest(reqType);

	reqType.setToken(params.getToken());

	final GetExpressCheckoutDetailsResponseType resp = getAAInterface()
			.getExpressCheckoutDetails(req, getCredentialsHolder());

	params.setRequest(reqType);
	params.setResponse(resp);

	saveOperationParams(params);
}


@Override
public void doExpressCheckoutPayment(final DoExpressCheckoutPaymentParams params) {

	final DoExpressCheckoutPaymentReq req = new DoExpressCheckoutPaymentReq();
	final DoExpressCheckoutPaymentRequestType reqType = new DoExpressCheckoutPaymentRequestType();
	final DoExpressCheckoutPaymentRequestDetailsType reqDetails = new DoExpressCheckoutPaymentRequestDetailsType();

	final PaymentActionCodeType paymentAction = params.getPaymentAction();

	final PaymentDetailsType paymentDetails = params.getDetails();
	paymentDetails.setPaymentAction(paymentAction);

	reqDetails.setToken(params.getToken());
	reqDetails.setPayerID(params.getPayerId());
	reqDetails.setPaymentAction(paymentAction);
	reqDetails.getPaymentDetails().add(paymentDetails);

	reqDetails.setButtonSource(PaypalConstants.BUTTON_SOURCE);

	reqType.setVersion(PaypalConstants.SOAP_API_VERSION);
	reqType.setDoExpressCheckoutPaymentRequestDetails(reqDetails);

	req.setDoExpressCheckoutPaymentRequest(reqType);

	final DoExpressCheckoutPaymentResponseType resp = getAAInterface()
			.doExpressCheckoutPayment(req, getCredentialsHolder());

	params.setRequest(reqType);
	params.setResponse(resp);

	saveOperationParams(params);
}


/**
 * @see com.paypal.hybris.service.PaypalService#doAuthorization(DoAuthorizationParams)
 */
@Override
public void doAuthorization(final DoAuthorizationParams params) {

	final DoAuthorizationReq req = new DoAuthorizationReq();
	final DoAuthorizationRequestType reqType = new DoAuthorizationRequestType();
	req.setDoAuthorizationRequest(reqType);

	reqType.setTransactionID(params.getTransactionId());
	reqType.setAmount(params.getAmount());
	reqType.setVersion(PaypalConstants.SOAP_API_VERSION);


	final DoAuthorizationResponseType resp = getAAInterface().doAuthorization(
			req, getCredentialsHolder());

	params.setRequest(reqType);
	params.setResponse(resp);

	saveOperationParams(params);
}


/**
 * @see com.paypal.hybris.service.PaypalService#doReauthorization(DoReauthorizationParams)
 */
@Override
public void doReauthorization(final DoReauthorizationParams params) {

	final DoReauthorizationReq req = new DoReauthorizationReq();
	final DoReauthorizationRequestType reqType = new DoReauthorizationRequestType();
	req.setDoReauthorizationRequest(reqType);

	reqType.setAuthorizationID(params.getTransactionId());
	reqType.setAmount(params.getAmount());
	reqType.setVersion(PaypalConstants.SOAP_API_VERSION);


	final DoReauthorizationResponseType resp = getAAInterface()
			.doReauthorization(req, getCredentialsHolder());

	params.setRequest(reqType);
	params.setResponse(resp);

	saveOperationParams(params);
}


/**
 * @see com.paypal.hybris.service.PaypalService#transactionSearch(com.paypal.hybris
 *      .soap.params.impl.TransactionSearchParams)
 */
@Override
public void transactionSearch(final TransactionSearchParams params) {

	final TransactionSearchRequestType reqType = new TransactionSearchRequestType();
	final Calendar startDate = params.getStartDate();
	if (startDate != null) {
		final GregorianCalendar gs1 = new GregorianCalendar(
				TimeZone.getTimeZone("UTC"));
		gs1.setTimeInMillis(startDate.getTimeInMillis());
		try {
			final XMLGregorianCalendar xgs1 = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(gs1);
			//			xgs1.setTimezone(0);
			reqType.setStartDate(xgs1);
		} catch (final DatatypeConfigurationException e) {
			e.printStackTrace();
			LOG.info("StartDate has been converted incorrect!");
		}
	}
	final Calendar endDate = params.getEndDate();
	if (endDate != null) {
		final GregorianCalendar gs2 = new GregorianCalendar(
				TimeZone.getTimeZone("UTC"));
		gs2.setTimeInMillis(endDate.getTimeInMillis());
		try {
			final XMLGregorianCalendar xgs2 = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(gs2);
			reqType.setEndDate(xgs2);
		} catch (final DatatypeConfigurationException e) {
			e.printStackTrace();
			LOG.info("EndDate has been converted incorrect!");
		}
	}
	reqType.setPayer(params.getPayer());
	reqType.setStatus(params.getStatus());
	reqType.setTransactionID(params.getTransactionId());
	reqType.setAmount(params.getAmount());
	reqType.setPayer(params.getPayer());
	reqType.setAuctionItemNumber(params.getAuctionItemNumber());
	reqType.setCardNumber(params.getCardNumber());
	reqType.setCurrencyCode(params.getCurrencyCode());
	reqType.setInvoiceID(params.getInvoiceID());
	reqType.setPayerName(params.getPayerName());
	reqType.setProfileID(params.getProfileID());
	reqType.setReceiptID(params.getReceiptID());
	reqType.setReceiver(params.getReceiver());
	reqType.setTransactionClass(params.getTransactionClass());


	reqType.setVersion(PaypalConstants.SOAP_API_VERSION);
	final TransactionSearchReq req = new TransactionSearchReq();
	req.setTransactionSearchRequest(reqType);

	final TransactionSearchResponseType response = getInterface()
			.transactionSearch(req, getCredentialsHolder());
	params.setRequest(reqType);
	params.setResponse(response);

	saveOperationParams(params);
}


/*
 * (non-Javadoc)
 * 
 * @see
 * com.paypal.hybris.service.PaypalService#doVoid(com.paypal.hybris.soap.params
 * .impl.DoVoidParams)
 */
@Override
public void doVoid(final DoVoidParams params) {

	final DoVoidRequestType reqType = new DoVoidRequestType();
	reqType.setAuthorizationID(params.getAuthorizationId());
	reqType.setNote(params.getNote());
	reqType.setMsgSubID(params.getMsgSubId());


	reqType.setVersion(PaypalConstants.SOAP_API_VERSION);
	final DoVoidReq req = new DoVoidReq();
	req.setDoVoidRequest(reqType);

	final DoVoidResponseType response = getAAInterface().doVoid(req,
			getCredentialsHolder());
	params.setRequest(reqType);
	params.setResponse(response);

	saveOperationParams(params);
}


/*
 * (non-Javadoc)
 * 
 * @see
 * com.paypal.hybris.service.PaypalService#doCapture(com.paypal.hybris.soap.
 * params.impl.DoCaptureParams)
 */
@Override
public void doCapture(final DoCaptureParams params) {

	final DoCaptureRequestType reqType = new DoCaptureRequestType();
	reqType.setInvoiceID(params.getInvoiceId());
	reqType.setNote(params.getNote());
	reqType.setDescriptor(params.getSoftDescriptor());
	reqType.setMsgSubID(params.getMsgSubId());
	reqType.setMerchantStoreDetails(params.getMerchantStoreDetails());
	reqType.setAuthorizationID(params.getAuthorizationId());
	reqType.setMerchantStoreDetails(params.getMerchantStoreDetails());
	reqType.setAmount(params.getAmount());
	reqType.setCompleteType(params.getCompleteType());


	reqType.setVersion(PaypalConstants.SOAP_API_VERSION);
	final DoCaptureReq req = new DoCaptureReq();
	req.setDoCaptureRequest(reqType);

	final DoCaptureResponseType response = getAAInterface().doCapture(req,
			getCredentialsHolder());
	params.setRequest(reqType);
	params.setResponse(response);

	saveOperationParams(params);
}

}
