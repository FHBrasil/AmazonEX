package com.paypal.hybris.soap.params.impl;


import de.hybris.platform.payment.commands.result.AbstractResult;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.soap.gen.AbstractRequestType;
import com.paypal.hybris.soap.gen.AbstractResponseType;
import com.paypal.hybris.soap.gen.ErrorType;
import com.paypal.hybris.soap.params.PaypalOperationParams;
import com.paypal.hybris.util.StrUtil;


/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
public abstract class AbstractPaypalOperationParams<TI extends AbstractRequestType, TO extends AbstractResponseType>
		implements PaypalOperationParams<TI, TO> {

public static final String NAME = "name";
public static final String REQUEST = "request";
public static final String RESPONSE = "response";

protected String OPERATION_NAME = "AbstractOperation";


/**
 * PayPal request of the given type.
 */
private TI request;

/**
 * PayPal reponse of the given type.
 */
private TO response;


@Override
public String getOperationName() {

	return OPERATION_NAME;
}


/**
 * @see com.paypal.hybris.soap.params.PaypalOperationParams#getAck()
 */
@Override
public String getAck() {

	return response.getAck().toString();
}


/**
 * @see com.paypal.hybris.soap.params.PaypalOperationParams#getErrors()
 */
@Override
public List<ErrorType> getErrors() {

	// YTODO Auto-generated method stub
	return null;
}


/**
 * @see com.paypal.hybris.soap.params.PaypalOperationParams#toJson()
 */
@Override
public String toJson() {

	final Map<String, Object> itself = new HashMap<String, Object>();
	itself.put(NAME, getOperationName());
	itself.put(REQUEST, getRequest());
	itself.put(RESPONSE, getResponse());

	return StrUtil.toJson(itself);
}


/**
 * @see com.paypal.hybris.soap.params.PaypalOperationParams#getRequest()
 */
@Override
public TI getRequest() {

	return request;
}


/**
 * @see com.paypal.hybris.soap.params.PaypalOperationParams#getRequestAsJson()
 */
@Override
public String getRequestAsJson() {

	return StrUtil.toJson(request);
}


/**
 * @see com.paypal.hybris.soap.params.PaypalOperationParams#setRequest(java.lang.Object)
 */
@Override
public void setRequest(final TI paypalRequest) {

	this.request = paypalRequest;
}


/**
 * @see com.paypal.hybris.soap.params.PaypalOperationParams#getResponse()
 */
@Override
public TO getResponse() {

	return response;
}


/**
 * @see com.paypal.hybris.soap.params.PaypalOperationParams#getResponseAsJson()
 */
@Override
public String getResponseAsJson() {

	return StrUtil.toJson(response);
}


/**
 * @see com.paypal.hybris.soap.params.PaypalOperationParams#setResponse(java.lang.Object
 *      )
 */
@Override
public void setResponse(final TO paypalResponse) {

	response = paypalResponse;
}


/**
 * @see com.paypal.hybris.soap.params.PaypalOperationParams#setParamsFromMap(java.util
 *      .Map)
 */
@Override
public abstract void setParamsFromMap(final Map<String, String[]> params);


/**
 * @see com.paypal.hybris.soap.params.PaypalOperationParams#getRequestParam(java.lang
 *      .String)
 */
@Override
public String getRequestParam(final String key) {

	// YTODO Auto-generated method stub
	return null;
}


/**
 * @see com.paypal.hybris.soap.params.PaypalOperationParams#getResponseParam(java.lang
 *      .String)
 */
@Override
public String getResponseParam(final String key) {

	// YTODO Auto-generated method stub
	return null;
}


public boolean isResultSuccess() {

	boolean isSuccess = false;

	try {
		isSuccess = getResponse().getAck().toString()
				.contains(PaypalConstants.STATUS_SUCCESS);
	} catch (final NullPointerException e) {
		// We don't need to process NullPointerExceptions
	}

	return isSuccess;
}


public void setConvertedResultStatus(final AbstractResult result) {

	if (isResultSuccess()) {
		result.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL);
		result.setTransactionStatus(TransactionStatus.ACCEPTED);
	} else {
		result
				.setTransactionStatusDetails(TransactionStatusDetails.PROCESSOR_DECLINE);
		result.setTransactionStatus(TransactionStatus.ERROR);
	}
}


}
