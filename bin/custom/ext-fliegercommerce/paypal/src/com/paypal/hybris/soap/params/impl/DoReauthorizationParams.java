/**
 * 
 */
package com.paypal.hybris.soap.params.impl;


import java.util.Map;

import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.soap.gen.BasicAmountType;
import com.paypal.hybris.soap.gen.CurrencyCodeType;
import com.paypal.hybris.soap.gen.DoReauthorizationRequestType;
import com.paypal.hybris.soap.gen.DoReauthorizationResponseType;
import com.paypal.hybris.util.StrUtil;


/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
public class DoReauthorizationParams
		extends
		AbstractPaypalOperationParams<DoReauthorizationRequestType, DoReauthorizationResponseType> {

{
	OPERATION_NAME = "DoReauthorization";
}


private String transactionId;
private BasicAmountType amount;


/**
 * @see com.paypal.hybris.soap.params.impl.AbstractPaypalOperationParams#setParamsFromMap(java.util.Map)
 */
@Override
public void setParamsFromMap(final Map<String, String[]> params) {

	// Amount
	final BasicAmountType amount = new BasicAmountType();
	amount.setValue(StrUtil.formatNumber(StrUtil.toDouble(StrUtil.getPar(params,
			PaypalConstants.PARAM_AMOUNT))));
	amount.setCurrencyID(CurrencyCodeType.valueOf(StrUtil.getPar(params,
			PaypalConstants.PARAM_CURRENCY)));
	setAmount(amount);

	// TransactionId
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
 * @return the amount
 */
public BasicAmountType getAmount() {

	return amount;
}


/**
 * @param amount
 *          the amount to set
 */
public void setAmount(final BasicAmountType amount) {

	this.amount = amount;
}

}
