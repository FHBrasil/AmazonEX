package com.paypal.hybris.facades;


import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.paypal.hybris.config.PaypalConfigManager;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.cronjobs.PaypalOperationsCleanerJobPerformable;
import com.paypal.hybris.cronjobs.PaypalOperationsHelper;
import com.paypal.hybris.service.PaypalService;
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
import com.paypal.hybris.util.AutoAccept;
import com.paypal.hybris.util.StrUtil;


/**
 * The main receiver of the API commands from the test console to the Paypal
 * service.
 * 
 * Gets a map of the parameters and returns a result, usually as JSON string.
 * 
 * 
 * @author Valentyn Markovych, Gorilla
 * 
 */
@Component
@SuppressWarnings("rawtypes")
public class PaypalTestFacade {

//==================================================================== Constants
private final Logger LOG = Logger.getLogger("PaypalTestFacade");


//======================================================================= Fields

@Resource(name = "paypalService")
private PaypalService paypalService;

@Resource
private ModelService modelService;


@Resource(name = "paypalOperationsCleaner")
private PaypalOperationsCleanerJobPerformable job;

@Resource
private PaypalOperationsHelper paypalOperationsHelper;

@Resource
private PaypalConfigManager paypalConfigManager;

// WARNING! Stateful object!
private AutoAccept autoAccept;


//==================================================================== doCommand
public Object doCommand(final Map<String, String[]> pars) {

	Object result = null;

	try {
		// Recognize command
		final String c = StrUtil.getPar(pars, "c");
		if (c != null) {

			if ("setExpressCheckout".equals(c)) {
				result = setExpressCheckout(pars);
			} else if ("getExpressCheckoutDetails".equals(c)) {
				result = getExpressCheckoutDetails(pars);
			} else if ("doExpressCheckoutPayment".equals(c)) {
				result = doExpressCheckoutPayment(pars);
			} else if ("RefundTransaction".equals(c)) {
				result = refundTransaction(pars);
			} else if ("GetTransactionDetails".equals(c)) {
				result = getTransactionDetails(pars);
			} else if ("TransactionSearch".equals(c)) {
				result = transactionSearch(pars);
			} else if ("DoAuthorization".equals(c)) {
				result = doAuthorization(pars);
			} else if ("DoReauthorization".equals(c)) {
				result = doReauthorization(pars);
			} else if ("DoVoid".equals(c)) {
				result = doVoid(pars);
			} else if ("DoCapture".equals(c)) {
				result = doCapture(pars);
			} else if ("autoAccept".equals(c)) {
				autoAccept
						.autoAccept(StrUtil.getPar(pars, PaypalConstants.PARAM_TOKEN));
			} else if ("DoPropUpdate".equals(c)) {
				paypalConfigManager.updateDbProperties();
			} else if ("DoPropRewrite".equals(c)) {
				paypalConfigManager.rewriteDbProperties();
			} else if ("MakeRandomOperations".equals(c)) {
				result = makeRandomOperations(pars);
			}


		}

	} catch (final Exception e) {
		LOG.error("Error 500: Got an exception: " + e.toString());
	}

	//	if (LOG.isDebugEnabled()) {
	//		LOG.debug("Result: " + result.toJson());
	//	}
	return result;
}


/**
 * @param pars
 */
private String makeRandomOperations(final Map<String, String[]> pars) {

	final int i = paypalOperationsHelper.makeRandomOperations();
	return "" + i + " operations created";
}


private DoReauthorizationParams doReauthorization(
		final Map<String, String[]> webParams) {

	final DoReauthorizationParams params = new DoReauthorizationParams();
	params.setParamsFromMap(webParams);

	paypalService.doReauthorization(params);

	return params;
}


/**
 * @param pars
 * @return operation results
 */
private PaypalOperationParams doCapture(final Map<String, String[]> pars) {

	final DoCaptureParams params = new DoCaptureParams();
	params.setParamsFromMap(pars);
	paypalService.doCapture(params);
	return params;
}


/**
 * @param pars
 * @return operation results
 */
private PaypalOperationParams doVoid(final Map<String, String[]> pars) {

	final DoVoidParams params = new DoVoidParams();
	params.setParamsFromMap(pars);
	paypalService.doVoid(params);
	return params;
}


/**
 * @param pars
 * @return operation results
 */
private TransactionSearchParams transactionSearch(
		final Map<String, String[]> pars) {

	final TransactionSearchParams params = new TransactionSearchParams();
	params.setParamsFromMap(pars);
	paypalService.transactionSearch(params);
	return params;
}


/**
 * @param webParams
 * @return operation results
 */
private DoAuthorizationParams doAuthorization(
		final Map<String, String[]> webParams) {

	final DoAuthorizationParams params = new DoAuthorizationParams();
	params.setParamsFromMap(webParams);
	paypalService.doAuthorization(params);

	return params;
}


private PaypalOperationParams refundTransaction(
		final Map<String, String[]> webParams) {

	final RefundTransactionParams params = new RefundTransactionParams();
	params.setParamsFromMap(webParams);
	paypalService.refundTransaction(params);
	return params;
}


private PaypalOperationParams getTransactionDetails(
		final Map<String, String[]> webParams) {

	final GetTransactionDetailsParams params = new GetTransactionDetailsParams();
	params.setParamsFromMap(webParams);
	paypalService.getTransactionDetails(params);
	putToSession(PaypalConstants.SESS_KEY_GET_TRANSACTION_DETAILS, params);
	return params;
}


//==================================================== getExpressCheckoutDetails
/**
 * @param pars
 *          Parameters set
 * @return result as JSON
 */
private PaypalOperationParams getExpressCheckoutDetails(
		final Map<String, String[]> pars) {

	final GetExpressCheckoutDetailsParams params = new GetExpressCheckoutDetailsParams();
	params.setParamsFromMap(pars);
	params
			.setParamsFromChain((SetExpressCheckoutParams) getFromSession(PaypalConstants.SESS_KEY_SET_EXPRESS_CHECKOUT));
	paypalService.getExpressCheckoutDetails(params);
	putToSession(PaypalConstants.SESS_KEY_GET_EXPRESS_CHECKOUT_DETAILS, params);

	return params;
}


//=========================================================== setExpressCheckout
/**
 * Invokes PaypalService method "Pay with PayPal"
 * 
 * @param webParams
 *          Parameters set
 * @return result as JSON
 */
private PaypalOperationParams setExpressCheckout(
		final Map<String, String[]> webParams) {

	autoAccept = new AutoAccept();

	// Create a set of fictive params, then place parameters received from the console to their places
	final SetExpressCheckoutParams params = new SetExpressCheckoutParams();
	params.setParamsFromMap(webParams);
	params.setReturnUrl(paypalConfigManager
			.getProperty(PaypalConstants.TEST_RETURN_URL));
	params.setCancelUrl(paypalConfigManager
			.getProperty(PaypalConstants.TEST_CANCEL_URL));
	paypalService.setExpressCheckout(params);
	putToSession(PaypalConstants.SESS_KEY_SET_EXPRESS_CHECKOUT, params);
	return params;
}


//===================================================== doExpressCheckoutPayment
/**
 * Executes payment, that was created earlier and bounded to current session.
 * 
 * @param webParams
 * @return Operation results
 */
private DoExpressCheckoutPaymentParams doExpressCheckoutPayment(
		final Map<String, String[]> webParams) {

	final DoExpressCheckoutPaymentParams params = new DoExpressCheckoutPaymentParams();
	params
			.setParamsFromChain((GetExpressCheckoutDetailsParams) getFromSession(PaypalConstants.SESS_KEY_GET_EXPRESS_CHECKOUT_DETAILS));
	params
			.setParamsFromChain((SetExpressCheckoutParams) getFromSession(PaypalConstants.SESS_KEY_SET_EXPRESS_CHECKOUT));
	params.setParamsFromMap(webParams);
	paypalService.doExpressCheckoutPayment(params);

	putToSession(PaypalConstants.SESS_KEY_DO_EXPRESS_CHECKOUT_PAYMENT, params);

	return params;
}


//================================================================= putToSession
public void putToSession(final String key, final Object value) {

	// TODO maybe hybris 5 incompatible
	final JaloSession jaloSession = JaloSession.getCurrentSession();
	jaloSession.setAttribute(key, value);
}


//=============================================================== getFromSession
public Object getFromSession(final String key) {

	// TODO maybe hybris 5 incompatible
	final JaloSession jaloSession = JaloSession.getCurrentSession();
	return jaloSession.getAttribute(key);
}


//========================================================== getters and setters
/**
 * @param paypalService
 *          the paypalService to set
 */
public void setPaypalService(final PaypalService paypalService) {

	this.paypalService = paypalService;
}


}
