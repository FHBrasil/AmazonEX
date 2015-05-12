/**
 * 
 */
package com.paypal.hybris.commands;


//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.xml.datatype.DatatypeConfigurationException;

import junit.framework.Assert;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import com.meterware.httpunit.HttpException;
import com.paypal.hybris.commands.impl.AuthorizationCommandImpl;
import com.paypal.hybris.commands.impl.CaptureCommandImpl;
import com.paypal.hybris.commands.impl.PartialCaptureCommandImpl;
import com.paypal.hybris.commands.impl.VoidCommandImpl;
import com.paypal.hybris.config.PaypalConfigManager;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.dao.PaypalConfigPropertyDao;
import com.paypal.hybris.model.PaypalConfigPropertyModel;
import com.paypal.hybris.service.PaypalService;
import com.paypal.hybris.soap.gen.AddressType;
import com.paypal.hybris.soap.gen.BasicAmountType;
import com.paypal.hybris.soap.gen.CountryCodeType;
import com.paypal.hybris.soap.gen.CurrencyCodeType;
import com.paypal.hybris.soap.gen.PaymentActionCodeType;
import com.paypal.hybris.soap.gen.PaymentDetailsItemType;
import com.paypal.hybris.soap.gen.PaymentDetailsType;
import com.paypal.hybris.soap.gen.PaymentInfoType;
import com.paypal.hybris.soap.gen.PaymentTransactionClassCodeType;
import com.paypal.hybris.soap.gen.PaymentTransactionSearchResultType;
import com.paypal.hybris.soap.gen.PaymentTransactionStatusCodeType;
import com.paypal.hybris.soap.gen.PersonNameType;
import com.paypal.hybris.soap.params.impl.DoExpressCheckoutPaymentParams;
import com.paypal.hybris.soap.params.impl.GetExpressCheckoutDetailsParams;
import com.paypal.hybris.soap.params.impl.GetTransactionDetailsParams;
import com.paypal.hybris.soap.params.impl.RefundTransactionParams;
import com.paypal.hybris.soap.params.impl.SetExpressCheckoutParams;
import com.paypal.hybris.soap.params.impl.TransactionSearchParams;
import com.paypal.hybris.util.AutoAccept;
import com.paypal.hybris.util.StrUtil;

import de.hybris.platform.core.Registry;
import de.hybris.platform.payment.commands.request.AuthorizationRequest;
import de.hybris.platform.payment.commands.request.CaptureRequest;
import de.hybris.platform.payment.commands.request.PartialCaptureRequest;
import de.hybris.platform.payment.commands.request.VoidRequest;
import de.hybris.platform.payment.commands.result.AuthorizationResult;
import de.hybris.platform.payment.commands.result.CaptureResult;
import de.hybris.platform.payment.commands.result.VoidResult;
import de.hybris.platform.payment.dto.BillingInfo;
import de.hybris.platform.payment.dto.CardInfo;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;


/**
 * @author christina romashchenko
 * 
 */
public class ComplexCommandTest extends ServicelayerTransactionalTest {

@Resource
private PaypalService paypalService;
@Resource
private AuthorizationCommandImpl authorizationCommand;
@Resource
private CaptureCommandImpl captureCommand;
@Resource
private PartialCaptureCommandImpl partialCaptureCommand;
@Resource
private VoidCommandImpl voidCommand;


private final Logger LOG = Logger.getLogger(ComplexCommandTest.class.getName());

private static final String EMAIL = "customertest@g.com";
private static final String PASS = "customertest@g.com";
private static final String URL = "customertest@g.com";
private static final String COMPLETE_TYPE = "NOT_COMPLETE";
private static String CURRENCY = CurrencyCodeType.USD.name();
private static final String PAYER_NAME = "customertest";
private static PaymentDetailsType paymentDetails;
private static AddressType paymentAddress;
private static long TIME_DELTA = 1000;

@Resource
private PaypalConfigManager paypalConfigManager;

@Mock
private PaypalConfigPropertyDao mockedDao;


@BeforeClass
public static void initMethod() {

	Registry.activateMasterTenant();

}


@Before
public void setUp() {

	LOG.info("setUp of ComplexCommandTest");
	paymentDetails = fillPaymentDetailsWithTestData();
	paymentAddress = fillAddressTypeWithTestData();

	Properties properties = null;
	try {
		final InputStream inputStream = PaypalConfigManager.class
				.getResourceAsStream(paypalConfigManager.getPaypalPropFileName());
		properties = new Properties();
		properties.load(inputStream);
	} catch (final IOException e) {
		throw new RuntimeException(e);
	}
	mockedDao = mock(PaypalConfigPropertyDao.class);
	for (final Object keyObj : properties.keySet()) {
		final String key = keyObj.toString();
		final PaypalConfigPropertyModel model = new PaypalConfigPropertyModel();
		model.setKey(key);
		model.setValue(properties.getProperty(key));
		when(mockedDao.getPropertyByKey(key)).thenReturn(model);
	}

	paypalConfigManager.setPaypalConfigPropertyDao(mockedDao);

}


@Test
public void testExpressCheckoutFlow() {

	LOG.info("testExpressCheckoutFlow...");
	final String paymentAction = paypalConfigManager
			.getProperty(PaypalConstants.PARAM_PAYMENT_ACTION);

	// SetExpressCheckout
	final SetExpressCheckoutParams paramsFromSetExpressCheckout = testSetExpressCheckoutCommand(paymentAction);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS,
			paramsFromSetExpressCheckout.getAck());

	final String token = paramsFromSetExpressCheckout.getResponse().getToken();

	// Accept from PayPal
	final String payerId = testAcceptCheckoutCommand(token);
	Assert.assertNotNull(payerId);

	// DoExpressCheckoutPayment
	final DoExpressCheckoutPaymentParams paramsFromDoExpressCheckoutPayment = testDoExpressCheckoutPaymentCommand(
			token, payerId, paymentAction);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS,
			paramsFromDoExpressCheckoutPayment.getAck());

	LOG.info("testExpressCheckoutFlow has gone SUCCESSFULLY!!!");
}


@Test
public void testBackOfficeFlowAuthorization() {

	LOG.info("testBackOfficeFlowAuthorization...");
	final String paymentAction = "ORDER";

	// SetExpressCheckout
	final SetExpressCheckoutParams paramsFromSetExpressCheckout = testSetExpressCheckoutCommand(paymentAction);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS,
			paramsFromSetExpressCheckout.getAck());

	final String token = paramsFromSetExpressCheckout.getResponse().getToken();

	// Accept from PayPal
	final String payerId = testAcceptCheckoutCommand(token);
	Assert.assertNotNull(payerId);

	// DoExpressCheckoutPayment
	final DoExpressCheckoutPaymentParams paramsFromDoExpressCheckoutPayment = testDoExpressCheckoutPaymentCommand(
			token, payerId, paymentAction);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS,
			paramsFromDoExpressCheckoutPayment.getAck());
	String transactionId = null;
	final List<PaymentInfoType> paymentInfo = paramsFromDoExpressCheckoutPayment
			.getResponse().getDoExpressCheckoutPaymentResponseDetails()
			.getPaymentInfo();
	if (paymentInfo.size() > 0) {
		transactionId = paymentInfo.get(0).getTransactionID();
	}

	// Authorization of 100 USD
	final AuthorizationResult authorizationResult = testAuthorizationCommand(
			transactionId, "100.00");
	final TransactionStatus resStatus = authorizationResult
			.getTransactionStatus();
	Assert.assertEquals(PaypalConstants.STATUS_ACCEPTED, resStatus.toString());

	// Void of the last authorization
	final VoidResult voidResult = testVoidCommand(transactionId);
	final TransactionStatus voidStatus = voidResult.getTransactionStatus();
	Assert.assertEquals(PaypalConstants.STATUS_ACCEPTED, voidStatus.toString());

	LOG.info("testBackOfficeFlowAuthorization has gone SUCCESSFULLY!!!");
}


@Test
public void testBackOfficeFlowDoCapture() {

	LOG.info("testBackOfficeFlowDoCapture...");
	final String paymentAction = "ORDER";

	// SetExpressCheckout
	final SetExpressCheckoutParams paramsFromSetExpressCheckout = testSetExpressCheckoutCommand(paymentAction);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS,
			paramsFromSetExpressCheckout.getAck());
	final String token = paramsFromSetExpressCheckout.getResponse().getToken();

	// Accept from PayPal
	final String payerId = testAcceptCheckoutCommand(token);
	Assert.assertNotNull(payerId);

	// DoExpressCheckoutPayment
	final DoExpressCheckoutPaymentParams paramsFromDoExpressCheckoutPayment = testDoExpressCheckoutPaymentCommand(
			token, payerId, paymentAction);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS,
			paramsFromDoExpressCheckoutPayment.getAck());
	String transactionId = null;
	final List<PaymentInfoType> paymentInfo = paramsFromDoExpressCheckoutPayment
			.getResponse().getDoExpressCheckoutPaymentResponseDetails()
			.getPaymentInfo();
	if (paymentInfo.size() > 0) {
		transactionId = paymentInfo.get(0).getTransactionID();
	}

	// Capture of 100 USD using transactionId
	final CaptureResult captureResult = testCaptureCommand(transactionId, "100",
			"COMPLETE");
	final TransactionStatus captureStatus = captureResult.getTransactionStatus();
	Assert
			.assertEquals(PaypalConstants.STATUS_ACCEPTED, captureStatus.toString());

	// Capture of 10 USD using transactionId
	final CaptureResult captureResult2 = testCaptureCommand(transactionId, "10",
			"COMPLETE");
	final TransactionStatus captureStatus2 = captureResult2
			.getTransactionStatus();
	Assert.assertEquals(PaypalConstants.STATUS_ERROR, captureStatus2.toString());

	LOG.info("testBackOfficeFlowDoCapture has gone SUCCESSFULLY!!!");
}


@Test
public void testBackOfficeFlowDoPartialCapture() {

	LOG.info("testBackOfficeFlowDoPartialCapture...");
	final String paymentAction = "ORDER";

	// SetExpressCheckout
	final SetExpressCheckoutParams paramsFromSetExpressCheckout = testSetExpressCheckoutCommand(paymentAction);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS,
			paramsFromSetExpressCheckout.getAck());
	final String token = paramsFromSetExpressCheckout.getResponse().getToken();

	// Accept from PayPal
	final String payerId = testAcceptCheckoutCommand(token);
	Assert.assertNotNull(payerId);

	// DoExpressCheckoutPayment
	final DoExpressCheckoutPaymentParams paramsFromDoExpressCheckoutPayment = testDoExpressCheckoutPaymentCommand(
			token, payerId, paymentAction);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS,
			paramsFromDoExpressCheckoutPayment.getAck());
	String transactionId = null;
	final List<PaymentInfoType> paymentInfo = paramsFromDoExpressCheckoutPayment
			.getResponse().getDoExpressCheckoutPaymentResponseDetails()
			.getPaymentInfo();
	if (paymentInfo.size() > 0) {
		transactionId = paymentInfo.get(0).getTransactionID();
	}

	// Capture of 100 USD using transactionId
	final CaptureResult captureResult = testCaptureCommand(transactionId, "100",
			"NOT_COMPLETE");
	final TransactionStatus captureStatus = captureResult.getTransactionStatus();
	Assert
			.assertEquals(PaypalConstants.STATUS_ACCEPTED, captureStatus.toString());

	// Capture of 20 USD using transactionId
	final CaptureResult captureResult2 = testCaptureCommand(transactionId, "20",
			"NOT_COMPLETE");
	final TransactionStatus captureStatus2 = captureResult2
			.getTransactionStatus();
	Assert.assertEquals(PaypalConstants.STATUS_ACCEPTED,
			captureStatus2.toString());

	// Capture of 5 USD using transactionId
	final CaptureResult captureResult3 = testCaptureCommand(transactionId, "5",
			"NOT_COMPLETE");
	final TransactionStatus captureStatus3 = captureResult3
			.getTransactionStatus();
	Assert.assertEquals(PaypalConstants.STATUS_ERROR, captureStatus3.toString());

	// RefundTransaction
	final RefundTransactionParams refunndTransactionResult = testRefundTransaction(transactionId);
	Assert.assertEquals(PaypalConstants.STATUS_FAILURE,
			refunndTransactionResult.getAck());

	LOG.info("testBackOfficeFlowDoPartialCapture has gone SUCCESSFULLY!!!");
}


@Test
public void testBackOfficeFlowGetDetailsAndSearch()
		throws DatatypeConfigurationException {

	LOG.info("testBackOfficeFlowDoCapture...");

	String paymentAction = "SALE";
	// Transaction 1
	// SetExpressCheckout expected failure
	final SetExpressCheckoutParams paramsFromSetExpressCheckout1 = testSetExpressCheckoutCommand(paymentAction);
	final String token1 = paramsFromSetExpressCheckout1.getResponse().getToken();
	final String payerId1 = testAcceptCheckoutCommand(token1);
	paymentAction = "ORDER";
	final DoExpressCheckoutPaymentParams paramsFromDoExpressCheckoutPayment = testDoExpressCheckoutPaymentCommand(
			token1, payerId1, paymentAction);
	String transactionId1 = null;
	final List<PaymentInfoType> paymentInfo1 = paramsFromDoExpressCheckoutPayment
			.getResponse().getDoExpressCheckoutPaymentResponseDetails()
			.getPaymentInfo();
	if (paymentInfo1.size() > 0) {
		transactionId1 = paymentInfo1.get(0).getTransactionID();
	}
	final GetTransactionDetailsParams details1 = testGetTransactionDetailsCommand(transactionId1);
	Assert.assertEquals(PaypalConstants.STATUS_FAILURE, details1.getAck());

	final Calendar date0 = Calendar.getInstance();
	final long dlong = paramsFromDoExpressCheckoutPayment.getResponse()
			.getTimestamp().toGregorianCalendar().getTimeInMillis();
	date0.setTime(new Date(dlong));

	// Transaction 2 expected test amount
	paymentAction = "ORDER";
	final BasicAmountType testAmount = new BasicAmountType();
	testAmount.setValue(StrUtil.formatNumber(104.87));
	Assert.assertEquals("104.87", testAmount.getValue());
	testAmount.setCurrencyID(CurrencyCodeType.valueOf(CURRENCY));
	paymentDetails.setOrderTotal(testAmount);
	paymentDetails.getShippingTotal().setValue("0.00");
	final SetExpressCheckoutParams paramsFromSetExpressCheckout2 = testSetExpressCheckoutCommand(paymentAction);
	final String token2 = paramsFromSetExpressCheckout2.getResponse().getToken();
	final String payerId2 = testAcceptCheckoutCommand(token2);
	final DoExpressCheckoutPaymentParams paramsFromDoExpressCheckoutPayment2 = testDoExpressCheckoutPaymentCommand(
			token2, payerId2, paymentAction);
	String transactionId2 = null;
	final List<PaymentInfoType> paymentInfo2 = paramsFromDoExpressCheckoutPayment2
			.getResponse().getDoExpressCheckoutPaymentResponseDetails()
			.getPaymentInfo();
	Assert.assertTrue(paymentInfo2.size() > 0);
	transactionId2 = paymentInfo2.get(0).getTransactionID();
	final GetTransactionDetailsParams details2 = testGetTransactionDetailsCommand(transactionId2);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS, details2.getAck());

	// Transaction 3 expected to be searched by time
	paymentAction = "SALE";
	CURRENCY = CurrencyCodeType.EUR.name();
	paymentDetails = fillPaymentDetailsWithTestData();
	final SetExpressCheckoutParams paramsFromSetExpressCheckout3 = testSetExpressCheckoutCommand(paymentAction);
	final String token3 = paramsFromSetExpressCheckout3.getResponse().getToken();
	final String payerId3 = testAcceptCheckoutCommand(token3);
	final DoExpressCheckoutPaymentParams paramsFromDoExpressCheckoutPayment3 = testDoExpressCheckoutPaymentCommand(
			token3, payerId3, paymentAction);
	String transactionId3 = null;
	final List<PaymentInfoType> paymentInfo3 = paramsFromDoExpressCheckoutPayment3
			.getResponse().getDoExpressCheckoutPaymentResponseDetails()
			.getPaymentInfo();
	Assert.assertTrue(paymentInfo3.size() > 0);
	transactionId3 = paymentInfo3.get(0).getTransactionID();
	final GetTransactionDetailsParams details3 = testGetTransactionDetailsCommand(transactionId3);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS, details3.getAck());
	CURRENCY = CurrencyCodeType.USD.name();

	// tests of different parameters

	// testing of startDate and endDate parameters of search
	final TransactionSearchParams searchParams = new TransactionSearchParams();
	final Calendar date1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
	final long startTime = paramsFromDoExpressCheckoutPayment2.getResponse()
			.getTimestamp().toGregorianCalendar().getTimeInMillis()
			- TIME_DELTA;
	date1.setTime(new Date(startTime)); // take time interval
	// I=[startTime-600; startTime+600]
	final long endTime = startTime + 2 * TIME_DELTA;
	final Calendar date2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
	date2.setTime(new Date(endTime));
	searchParams.setStartDate(date1);
	searchParams.setEndDate(date2);
	paypalService.transactionSearch(searchParams);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS, searchParams.getAck());
	final List<PaymentTransactionSearchResultType> resultList = searchParams
			.getResponse().getPaymentTransactions();
	Assert.assertNotNull(resultList);
	Assert.assertFalse(resultList.isEmpty());
	for (final PaymentTransactionSearchResultType t : resultList) { // checking
		// if
		// there
		// is
		// anyone
		// transaction
		// that
		// isn't
		// from
		// the
		// interval
		// I
		final long timestamp = t.getTimestamp().toGregorianCalendar()
				.getTimeInMillis();
		Assert.assertTrue(timestamp >= startTime);
		Assert.assertTrue(timestamp <= endTime);
	}

	// testing of search by transactionId
	final TransactionSearchParams searchParams2 = new TransactionSearchParams();
	searchParams2.setStartDate(date0);
	searchParams2.setTransactionId(transactionId2);
	paypalService.transactionSearch(searchParams2);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS, searchParams2.getAck());
	final List<PaymentTransactionSearchResultType> resultList2 = searchParams2
			.getResponse().getPaymentTransactions();
	Assert.assertNotNull(resultList2);
	Assert.assertTrue(resultList2.size() == 1);
	Assert.assertEquals(transactionId2, resultList2.get(0).getTransactionID());

	// search by status
	final TransactionSearchParams searchParams3 = new TransactionSearchParams();
	searchParams3.setStartDate(date0);
	searchParams3.setStatus(PaymentTransactionStatusCodeType.PENDING);
	paypalService.transactionSearch(searchParams3);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS, searchParams3.getAck());
	final List<PaymentTransactionSearchResultType> resultList3 = searchParams3
			.getResponse().getPaymentTransactions();
	Assert.assertNotNull(resultList3);

	// search by test amount and currency
	final TransactionSearchParams searchParams4 = new TransactionSearchParams();
	searchParams4.setStartDate(date0);
	searchParams4.setAmount(testAmount);
	searchParams4.setCurrencyCode(CurrencyCodeType.USD);
	paypalService.transactionSearch(searchParams4);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS, searchParams4.getAck());
	final List<PaymentTransactionSearchResultType> resultList4 = searchParams4
			.getResponse().getPaymentTransactions();
	Assert.assertNotNull(resultList4);
	Assert.assertTrue(resultList4.size() == 1); // there exists only one
	// transaction with the test
	// amount
	Assert.assertEquals(transactionId2, resultList4.get(0).getTransactionID());

	// search only by currency
	final TransactionSearchParams searchParams5 = new TransactionSearchParams();
	searchParams5.setStartDate(date1);
	searchParams5.setCurrencyCode(CurrencyCodeType.EUR);
	paypalService.transactionSearch(searchParams5);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS, searchParams5.getAck());
	final List<PaymentTransactionSearchResultType> resultList5 = searchParams5
			.getResponse().getPaymentTransactions();
	Assert.assertNotNull(resultList5);
	Assert.assertTrue(resultList5.size() > 0);
	for (final PaymentTransactionSearchResultType t : resultList5) { // checking
		// if
		// there
		// is
		// any
		// wrong
		// transaction
		Assert.assertEquals(CurrencyCodeType.EUR, t.getGrossAmount()
				.getCurrencyID());
	}

	// search by other parameters
	final TransactionSearchParams searchParams6 = new TransactionSearchParams();
	searchParams6.setStartDate(date0);
	searchParams6.setAuctionItemNumber("");
	searchParams6.setInvoiceID("");
	searchParams6.setReceiver("");
	searchParams6.setCardNumber("");
	searchParams6.setTransactionClass(PaymentTransactionClassCodeType.ALL);
	searchParams6.setProfileID("");
	searchParams6.setReceiptID("");
	paypalService.transactionSearch(searchParams6);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS, searchParams6.getAck());
	final List<PaymentTransactionSearchResultType> resultList6 = searchParams6
			.getResponse().getPaymentTransactions();
	Assert.assertNotNull(resultList6); // if status SUCCESS and list isn't
	// NULL => parameters have been sent
	// to PayPal correctly

	// search by payerName and email
	final TransactionSearchParams searchParams7 = new TransactionSearchParams();
	final PersonNameType testPersonName = new PersonNameType();
	testPersonName.setLastName(PAYER_NAME);
	searchParams7.setStartDate(date0);
	searchParams7.setPayer(EMAIL);
	searchParams7.setPayerName(testPersonName);
	paypalService.transactionSearch(searchParams7);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS, searchParams7.getAck());
	final List<PaymentTransactionSearchResultType> resultList7 = searchParams7
			.getResponse().getPaymentTransactions();
	final boolean isExistWrong2 = false;
	for (final PaymentTransactionSearchResultType t : resultList7) { // both
		// conditions
		// have
		// to
		// be
		// executed
		// for
		// every
		// transaction
		// from
		// resultList
		Assert.assertTrue(t.getPayer().contains(EMAIL));
		Assert.assertTrue(t.getPayerDisplayName().contains(PAYER_NAME));
	}

	LOG.info("testBackOfficeFlowGetDetailsAndSearch has gone SUCCESSFULLY!!!");
}


@Test
public void testBackOfficeFlowGetExpressCheckoutDetails() {

	LOG.info("testBackOfficeFlowGetExpressCheckoutDetails...");
	final String paymentAction = "SALE";

	// SetExpressCheckout
	final SetExpressCheckoutParams paramsFromSetExpressCheckout = testSetExpressCheckoutCommand(paymentAction);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS,
			paramsFromSetExpressCheckout.getAck());
	final String token = paramsFromSetExpressCheckout.getResponse().getToken();

	// Accept from PayPal
	final String payerId = testAcceptCheckoutCommand(token);
	Assert.assertNotNull(payerId);

	// GetExpressCheckoutDetails
	final GetExpressCheckoutDetailsParams paramsFromGetExpressCheckoutDetails = testGetExpressCheckoutDetailsCommand(token);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS,
			paramsFromGetExpressCheckoutDetails.getAck());
	Assert.assertEquals(payerId, paramsFromGetExpressCheckoutDetails
			.getResponse().getGetExpressCheckoutDetailsResponseDetails()
			.getPayerInfo().getPayerID());

	// DoExpressCheckoutPayment
	final DoExpressCheckoutPaymentParams paramsFromDoExpressCheckoutPayment = testDoExpressCheckoutPaymentCommand(
			token, payerId, paymentAction);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS,
			paramsFromDoExpressCheckoutPayment.getAck());
	String transactionId = null;
	final List<PaymentInfoType> paymentInfo = paramsFromDoExpressCheckoutPayment
			.getResponse().getDoExpressCheckoutPaymentResponseDetails()
			.getPaymentInfo();
	Assert.assertTrue(paymentInfo.size() > 0);
	transactionId = paymentInfo.get(0).getTransactionID();

	// GetExpressCheckout (transactionId had to be set after
	// DoExpressCheckoutPayment)
	final GetExpressCheckoutDetailsParams paramsFromGetExpressCheckoutDetails2 = testGetExpressCheckoutDetailsCommand(token);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS,
			paramsFromGetExpressCheckoutDetails2.getAck());
	Assert.assertEquals(payerId, paramsFromGetExpressCheckoutDetails2
			.getResponse().getGetExpressCheckoutDetailsResponseDetails()
			.getPayerInfo().getPayerID());
	final List<PaymentDetailsType> details = paramsFromGetExpressCheckoutDetails2
			.getResponse().getGetExpressCheckoutDetailsResponseDetails()
			.getPaymentDetails();
	Assert.assertNotNull(details);
	Assert.assertFalse(details.isEmpty());
	Assert.assertNotNull(details.get(0).getTransactionId());

	LOG.info("testBackOfficeFlowGetExpressCheckoutDetails has gone SUCCESSFULLY!!!");
}


@Test
public void testBackOfficeFlowRefundTransaction() {

	LOG.info("testBackOfficeFlowRefundTransaction...");
	final String paymentAction = "SALE";

	// SetExpressCheckout
	final SetExpressCheckoutParams paramsFromSetExpressCheckout = testSetExpressCheckoutCommand(paymentAction);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS,
			paramsFromSetExpressCheckout.getAck());
	final String token = paramsFromSetExpressCheckout.getResponse().getToken();

	// Accept from PayPal
	final String payerId = testAcceptCheckoutCommand(token);
	Assert.assertNotNull(payerId);

	// DoExpressCheckoutPayment
	final DoExpressCheckoutPaymentParams paramsFromDoExpressCheckoutPayment = testDoExpressCheckoutPaymentCommand(
			token, payerId, paymentAction);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS,
			paramsFromDoExpressCheckoutPayment.getAck());
	String transactionId = null;
	final List<PaymentInfoType> paymentInfo = paramsFromDoExpressCheckoutPayment
			.getResponse().getDoExpressCheckoutPaymentResponseDetails()
			.getPaymentInfo();
	if (paymentInfo.size() > 0) {
		transactionId = paymentInfo.get(0).getTransactionID();
	}

	// RefundTransaction
	final RefundTransactionParams refunndTransactionResult = testRefundTransaction(transactionId);
	Assert.assertEquals(PaypalConstants.STATUS_SUCCESS,
			refunndTransactionResult.getAck());

	LOG.info("testBackOfficeFlowRefundTransaction has gone SUCCESSFULLY!!!");
}


@Test
public void testStrUtilFormatterOfAmount() {

	LOG.info("testStrUtilFormatterOfAmount...");

	Assert.assertEquals("-1.00", StrUtil.formatNumber(-1.001234));
	Assert.assertEquals("-1.02", StrUtil.formatNumber(-1.02987));
	Assert.assertEquals("-0.91", StrUtil.formatNumber(-0.9123));
	Assert.assertEquals("-0.90", StrUtil.formatNumber(-0.9));
	Assert.assertEquals("-0.10", StrUtil.formatNumber(-0.1));
	Assert.assertEquals("0.00", StrUtil.formatNumber(0));

	Assert.assertEquals("1.01", StrUtil.formatNumber(1.001234));
	Assert.assertEquals("1.03", StrUtil.formatNumber(1.02987));
	Assert.assertEquals("0.92", StrUtil.formatNumber(0.9123));
	Assert.assertEquals("0.90", StrUtil.formatNumber(0.9));
	Assert.assertEquals("0.10", StrUtil.formatNumber(0.1));
	Assert.assertEquals("10.00", StrUtil.formatNumber(9.991));
	Assert.assertEquals("1.00", StrUtil.formatNumber(0.999));
	Assert.assertEquals("1.01",
			StrUtil.formatNumber(StrUtil.toBigDecimal("1.01")));
	Assert.assertEquals("1.01",
			StrUtil.formatNumber(StrUtil.toBigDecimal("1.0012")));

	Assert.assertEquals("12,345,678,910,112.02",
			StrUtil.formatNumber(StrUtil.toBigDecimal("12345678910112.0112345678")));
	Assert.assertEquals("123,456,789,101,234.02",
			StrUtil.formatNumber(StrUtil.toBigDecimal("123456789101234.0123456789")));
	Assert.assertEquals("12,345,678,910,123,456.01",
			StrUtil.formatNumber(StrUtil.toBigDecimal("12345678910123456.002")));
	Assert.assertEquals("-123,456,789,101,234.02", StrUtil.formatNumber(StrUtil
			.toBigDecimal("-123456789101234.02123456789")));
	Assert.assertEquals(null, StrUtil.formatNumber(StrUtil.toDouble("-")));

	LOG.info("testStrUtilFormatterOfAmount has gone SUCCESSFULLY!!!");
}


private SetExpressCheckoutParams testSetExpressCheckoutCommand(
		final String paymentAction) {

	LOG.info("testSetExpressCheckoutCommand...");
	final SetExpressCheckoutParams params = new SetExpressCheckoutParams();
	params.setDetails(paymentDetails);
	params.setAddr(paymentAddress);
	params.setPaymentAction(PaymentActionCodeType.valueOf(paymentAction));
	params.setReturnUrl(paypalConfigManager
			.getProperty(PaypalConstants.TEST_RETURN_URL));
	params.setCancelUrl(paypalConfigManager
			.getProperty(PaypalConstants.TEST_CANCEL_URL));
	paypalService.setExpressCheckout(params);
	return params;

}


private String testAcceptCheckoutCommand(final String token) {

	String payerId = null;
	boolean result = false;
	LOG.info("testAcceptCheckoutCommand...");
	final AutoAccept accept = new AutoAccept();
	try {
		result = accept.autoAccept(token);
		if (result) {
			payerId = accept.getPayerId();
		}
	} catch (final Exception ex) {
		ex.printStackTrace();
	}
	return payerId;
}


private GetExpressCheckoutDetailsParams testGetExpressCheckoutDetailsCommand(
		final String token) {

	LOG.info("testGetExpressCheckoutDetailsCommand...");
	final GetExpressCheckoutDetailsParams params = new GetExpressCheckoutDetailsParams();
	params.setToken(token);
	paypalService.getExpressCheckoutDetails(params);
	return params;

}


private GetTransactionDetailsParams testGetTransactionDetailsCommand(
		final String transactionId) {

	LOG.info("testGetTransactionDetailsCommand...");
	final GetTransactionDetailsParams params = new GetTransactionDetailsParams();
	params.setTransactionId(transactionId);
	paypalService.getTransactionDetails(params);
	return params;

}


private DoExpressCheckoutPaymentParams testDoExpressCheckoutPaymentCommand(
		final String token, final String payerId, final String paymentAction) {

	LOG.info("testDoExpressCheckoutPaymentCommand...");
	final DoExpressCheckoutPaymentParams params = new DoExpressCheckoutPaymentParams();
	params.setPayerId(payerId);
	params.setToken(token);
	params.setPaymentAction(PaymentActionCodeType.valueOf(paymentAction));
	params.setDetails(paymentDetails);
	paypalService.doExpressCheckoutPayment(params);
	return params;

}


private RefundTransactionParams testRefundTransaction(final String transactionId) {

	LOG.info("testRefundTransaction...");
	final RefundTransactionParams params = new RefundTransactionParams();
	params.setTransactionId(transactionId);
	paypalService.refundTransaction(params);
	return params;
}


private AuthorizationResult testAuthorizationCommand(
		final String transactionId, final String amountString) {

	LOG.info("testAuthorizationCommand...");
	AuthorizationResult result = new AuthorizationResult();
	final Currency currency = Currency.getInstance(CURRENCY);
	final CardInfo cardInfo = null;
	final BigDecimal totalAmount = StrUtil.toBigDecimal(amountString);
	final BillingInfo shippingInfo = null;
	final AuthorizationRequest request = new AuthorizationRequest(transactionId,
			cardInfo, currency, totalAmount, shippingInfo);
	result = authorizationCommand.perform(request);
	return result;

}


private CaptureResult testCaptureCommand(final String id,

final String amountString, final String completeType) {

	LOG.info("testCaptureCommand...");
	CaptureResult result = new CaptureResult();
	final Currency currency = Currency.getInstance(CURRENCY);
	final BigDecimal amount = StrUtil.toBigDecimal(amountString);
	final BillingInfo shippingInfo = null;
	if (completeType.contains("NOT_COMPLETE")) {
		final PartialCaptureRequest request = new PartialCaptureRequest(null, id,
				null, currency, amount, null, "paypalProvider");
		result = partialCaptureCommand.perform(request);
	} else {
		final CaptureRequest request = new CaptureRequest(null, id, null, currency,
				amount, "paypalProvider");
		result = captureCommand.perform(request);
	}
	return result;

}


private VoidResult testVoidCommand(final String transactionId) {

	LOG.info("testVoidCommand...");
	VoidResult result = new VoidResult();
	final VoidRequest request = new VoidRequest(null, transactionId, null, null);
	result = voidCommand.perform(request);
	return result;

}


// methods for testing setExpressCheckout
public static PaymentDetailsType fillPaymentDetailsWithTestData() {

	final PaymentDetailsType det = new PaymentDetailsType();
	final BasicAmountType amount1 = new BasicAmountType();
	amount1.setValue(StrUtil.formatNumber(9.9467));
	Assert.assertEquals("9.95", amount1.getValue());
	amount1.setCurrencyID(CurrencyCodeType.valueOf(CURRENCY));
	final PaymentDetailsItemType item1 = new PaymentDetailsItemType();
	item1.setName("itemName1");
	item1.setNumber("itemNumber1");
	item1.setQuantity(BigInteger.valueOf(2L));
	Assert.assertEquals("2", item1.getQuantity().toString());
	item1.setDescription("description");
	item1.setAmount(amount1);

	final BasicAmountType amount2 = new BasicAmountType();
	amount2.setValue(StrUtil.formatNumber(39.7));
	Assert.assertEquals("39.70", amount2.getValue());
	amount2.setCurrencyID(CurrencyCodeType.valueOf(CURRENCY));
	final PaymentDetailsItemType item2 = new PaymentDetailsItemType();
	item2.setName("itemName2");
	item2.setNumber("itemNumber2");
	item2.setQuantity(BigInteger.valueOf(2L));
	Assert.assertEquals("2", item2.getQuantity().toString());
	item2.setDescription("description");
	item2.setAmount(amount2);
	det.getPaymentDetailsItem().add(item1);
	det.getPaymentDetailsItem().add(item2);

	final BasicAmountType itemTotal = new BasicAmountType();
	itemTotal.setValue(StrUtil.formatNumber(99.299));
	Assert.assertEquals("99.30", itemTotal.getValue());
	itemTotal.setCurrencyID(CurrencyCodeType.valueOf(CURRENCY));
	final BasicAmountType taxTotal = new BasicAmountType();
	taxTotal.setValue(StrUtil.formatNumber(2.573565));
	Assert.assertEquals("2.58", taxTotal.getValue());
	taxTotal.setCurrencyID(CurrencyCodeType.valueOf(CURRENCY));
	final BasicAmountType shippingTotal = new BasicAmountType();
	shippingTotal.setValue(StrUtil.formatNumber(2.99999));
	Assert.assertEquals("3.00", shippingTotal.getValue());
	shippingTotal.setCurrencyID(CurrencyCodeType.valueOf(CURRENCY));
	final BasicAmountType shippingDiscount = new BasicAmountType();
	shippingDiscount.setValue(StrUtil.formatNumber(-1));
	Assert.assertEquals("-1.00", shippingDiscount.getValue());
	shippingDiscount.setCurrencyID(CurrencyCodeType.valueOf(CURRENCY));
	final BasicAmountType handlingTotal = new BasicAmountType();
	handlingTotal.setValue(StrUtil.formatNumber(2.9888123));
	Assert.assertEquals("2.99", handlingTotal.getValue());
	handlingTotal.setCurrencyID(CurrencyCodeType.valueOf(CURRENCY));
	final BasicAmountType insuranceTotal = new BasicAmountType();
	insuranceTotal.setValue(StrUtil.formatNumber(0.992));
	Assert.assertEquals("1.00", insuranceTotal.getValue());
	insuranceTotal.setCurrencyID(CurrencyCodeType.valueOf(CURRENCY));
	final BasicAmountType orderTotal = new BasicAmountType();
	orderTotal.setValue(StrUtil.formatNumber(107.861));
	Assert.assertEquals("107.87", orderTotal.getValue());
	orderTotal.setCurrencyID(CurrencyCodeType.valueOf(CURRENCY));
	final String allowNote = "1";

	det.setItemTotal(itemTotal);
	det.setTaxTotal(taxTotal);
	det.setShippingTotal(shippingTotal);
	det.setShippingDiscount(shippingDiscount);
	det.setHandlingTotal(handlingTotal);
	det.setInsuranceTotal(insuranceTotal);
	det.setOrderTotal(orderTotal);
	det.setNoteText(allowNote);

	return det;
}


public PaypalService getPaypalService() {

	return paypalService;
}


public void setPaypalService(final PaypalService paypalService) {

	this.paypalService = paypalService;
}


public static AddressType fillAddressTypeWithTestData() {

	final AddressType addr = new AddressType();
	addr.setCityName("omaha");
	addr.setStreet1("123 main");
	addr.setCountry(CountryCodeType.US);
	addr.setName("joe tester");
	return addr;
}


private void sendReq(final String url, final String email, final String fname)
		throws IOException {

	final HttpClient httpClient = new HttpClient();
	final PostMethod postMethod = new PostMethod(URL);
	postMethod.addParameter("Email", EMAIL);
	postMethod.addParameter("fname", PASS);
	try {
		httpClient.executeMethod(postMethod);
	} catch (final HttpException e) {
		e.printStackTrace();
	} catch (final IOException e) {
		e.printStackTrace();
	}

	if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
		final String resp = postMethod.getResponseBodyAsString();
	} else {
		// ...postMethod.getStatusLine();
	}
}
}
