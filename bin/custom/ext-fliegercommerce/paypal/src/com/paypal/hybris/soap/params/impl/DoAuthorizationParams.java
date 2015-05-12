package com.paypal.hybris.soap.params.impl;


import de.hybris.platform.payment.commands.request.AuthorizationRequest;
import de.hybris.platform.payment.commands.result.AuthorizationResult;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.Map;

import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.soap.gen.BasicAmountType;
import com.paypal.hybris.soap.gen.CurrencyCodeType;
import com.paypal.hybris.soap.gen.DoAuthorizationRequestType;
import com.paypal.hybris.soap.gen.DoAuthorizationResponseType;
import com.paypal.hybris.util.StrUtil;


/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
public class DoAuthorizationParams
		extends
		AbstractPaypalOperationParams<DoAuthorizationRequestType, DoAuthorizationResponseType> {

{
	OPERATION_NAME = "DoAuthorization";
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
 * @param req
 */
public void setParamsFromRequest(final AuthorizationRequest req) {

	// Amount
	final BasicAmountType amount = new BasicAmountType();
	amount.setValue(StrUtil.formatNumber(req.getTotalAmount()));
	amount.setCurrencyID(CurrencyCodeType.valueOf(req.getCurrency()
			.getCurrencyCode()));
	setAmount(amount);
	setTransactionId(req.getMerchantTransactionCode()); // ?
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


public AuthorizationResult getConvertedResult() {

	final AuthorizationResult result = new AuthorizationResult();
	final BigDecimal amount = StrUtil.toBigDecimal(getResponse().getAmount()
			.getValue());
	result.setAccountBalance(amount);
	final String currency = getResponse().getAmount().getCurrencyID().name();
	result.setCurrency(Currency.getInstance(currency));
	result.setAuthorizationTime(new Date());
	result.setAuthorizationCode(getResponse().getTransactionID());
	setConvertedResultStatus(result);
	return result;
}

}
