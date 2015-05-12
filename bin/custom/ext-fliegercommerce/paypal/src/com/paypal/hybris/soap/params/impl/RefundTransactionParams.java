/**
 * 
 */
package com.paypal.hybris.soap.params.impl;


import java.util.Map;

import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.soap.gen.RefundTransactionRequestType;
import com.paypal.hybris.soap.gen.RefundTransactionResponseType;
import com.paypal.hybris.util.StrUtil;


/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
public class RefundTransactionParams
		extends
		AbstractPaypalOperationParams<RefundTransactionRequestType, RefundTransactionResponseType> {

{
	OPERATION_NAME = "RefundTransaction";
}


private String transactionId;
private String payerId;


/**
 * @see com.paypal.hybris.soap.params.impl.AbstractPaypalOperationParams#setParamsFromMap(java.util.Map)
 */
@Override
public void setParamsFromMap(final Map<String, String[]> params) {

	setPayerId(StrUtil.getPar(params, PaypalConstants.PARAM_PAYER_ID));
	setTransactionId(StrUtil.getPar(params, PaypalConstants.PARAM_TRANSACTION_ID));
}


/**
 * @return the transactionId
 */
public String getTransactionId() {

	return transactionId;
}


/**
 * @param transactionId
 *          the transactionId to set
 */
public void setTransactionId(final String transactionId) {

	this.transactionId = transactionId;
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

}
