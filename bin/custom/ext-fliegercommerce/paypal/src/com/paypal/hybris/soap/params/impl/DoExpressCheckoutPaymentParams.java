package com.paypal.hybris.soap.params.impl;


import java.util.Map;

import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.soap.gen.DoExpressCheckoutPaymentRequestType;
import com.paypal.hybris.soap.gen.DoExpressCheckoutPaymentResponseType;
import com.paypal.hybris.soap.gen.GetExpressCheckoutDetailsResponseDetailsType;
import com.paypal.hybris.soap.gen.PaymentActionCodeType;
import com.paypal.hybris.util.StrUtil;


/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
public class DoExpressCheckoutPaymentParams
		extends
		AbstractPaypalPaymentOperationParams<DoExpressCheckoutPaymentRequestType, DoExpressCheckoutPaymentResponseType> {

{
	OPERATION_NAME = "DoExpressCheckoutPayment";
}


private String token;
private String payerId;
private PaymentActionCodeType paymentAction;


/**
 * @see com.paypal.hybris.soap.params.impl.AbstractPaypalOperationParams#setParamsFromMap(java.util.Map)
 */
@Override
public void setParamsFromMap(final Map<String, String[]> params) {

	setToken(StrUtil.getPar(params, PaypalConstants.PARAM_TOKEN));
	setPaymentAction(PaymentActionCodeType.valueOf(StrUtil.getPar(params,
			PaypalConstants.PARAM_PAYMENT_ACTION)));
	setPayerId(StrUtil.getPar(params, PaypalConstants.PARAM_PAYER_ID));

}


public void setParamsFromChain(final GetExpressCheckoutDetailsParams params) {

	final GetExpressCheckoutDetailsResponseDetailsType respDetails = params
			.getResponse().getGetExpressCheckoutDetailsResponseDetails();
	setToken(respDetails.getToken());
	setPayerId(respDetails.getPayerInfo().getPayerID());
	setDetails(respDetails.getPaymentDetails().get(0));
}


public void setParamsFromChain(final SetExpressCheckoutParams secParams) {

	setDetails(secParams.getDetails());
	setPaymentAction(secParams.getRequest().getSetExpressCheckoutRequestDetails()
			.getPaymentAction());
}


/**
 * @return the token
 */
public String getToken() {

	return token;
}


/**
 * @param token
 *          the token to set
 */
public void setToken(final String token) {

	this.token = token;
}


/**
 * @return the payerId
 */
public String getPayerId() {

	return payerId;
}


/**
 * @param payerId
 *          the payerId to set
 */
public void setPayerId(final String payerId) {

	this.payerId = payerId;
}


/**
 * @return the paymentAction
 */
public PaymentActionCodeType getPaymentAction() {

	return paymentAction;
}


/**
 * @param paymentAction
 *          the paymentAction to set
 */
public void setPaymentAction(final PaymentActionCodeType paymentAction) {

	this.paymentAction = paymentAction;
}


}
