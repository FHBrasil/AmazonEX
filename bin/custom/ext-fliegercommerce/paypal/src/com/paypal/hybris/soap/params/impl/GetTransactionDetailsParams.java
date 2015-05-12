package com.paypal.hybris.soap.params.impl;


import java.util.Map;

import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.soap.gen.GetTransactionDetailsRequestType;
import com.paypal.hybris.soap.gen.GetTransactionDetailsResponseType;
import com.paypal.hybris.util.StrUtil;


/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
public class GetTransactionDetailsParams
		extends
		AbstractPaypalOperationParams<GetTransactionDetailsRequestType, GetTransactionDetailsResponseType> {

{
	OPERATION_NAME = "GetTransactionDetails";
}


private String transactionId;


/**
 * @see com.paypal.hybris.soap.params.impl.AbstractPaypalOperationParams#setParamsFromMap(java.util.Map)
 */
@Override
public void setParamsFromMap(final Map<String, String[]> params) {

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


}
