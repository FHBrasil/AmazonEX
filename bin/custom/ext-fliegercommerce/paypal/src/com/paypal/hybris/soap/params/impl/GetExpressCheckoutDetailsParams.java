/**
 * 
 */
package com.paypal.hybris.soap.params.impl;


import de.hybris.platform.core.model.user.AddressModel;

import java.util.Map;

import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.soap.gen.GetExpressCheckoutDetailsRequestType;
import com.paypal.hybris.soap.gen.GetExpressCheckoutDetailsResponseType;
import com.paypal.hybris.util.StrUtil;


/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
public class GetExpressCheckoutDetailsParams
		extends
		AbstractPaypalOperationParams<GetExpressCheckoutDetailsRequestType, GetExpressCheckoutDetailsResponseType> {

{
	OPERATION_NAME = "GetExpressCheckoutDetails";
}


private String token;


/**
 * @see com.paypal.hybris.soap.params.impl.AbstractPaypalOperationParams#setParamsFromMap(java.util.Map)
 */
@Override
public void setParamsFromMap(final Map<String, String[]> params) {

	setToken(StrUtil.getPar(params, PaypalConstants.PARAM_TOKEN));
}


public void setParamsFromChain(final SetExpressCheckoutParams params) {

	setToken(params.getResponse().getToken());
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
 * @param addressModel
 */
public void fillFromResult(final AddressModel addressModel) {

}


@Override
public String getAck() {

	final String ack = super.getAck();
	String payerId = null;
	try {
		payerId = getResponse().getGetExpressCheckoutDetailsResponseDetails()
				.getPayerInfo().getPayerID();
	} catch (final NullPointerException ex) {
		return PaypalConstants.STATUS_FAILURE;
	}
	if (ack == null || ack.startsWith(PaypalConstants.STATUS_FAILURE)
			|| payerId == null || "".equals(payerId)) {
		return PaypalConstants.STATUS_FAILURE;
	}
	return ack;
}


}
