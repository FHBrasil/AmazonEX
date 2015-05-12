/**
 * 
 */
package com.paypal.hybris.soap.params.impl;


import de.hybris.platform.payment.commands.request.VoidRequest;
import de.hybris.platform.payment.commands.result.VoidResult;

import java.util.Date;
import java.util.Map;

import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.soap.gen.DoVoidRequestType;
import com.paypal.hybris.soap.gen.DoVoidResponseType;
import com.paypal.hybris.util.StrUtil;


/**
 * @author christina romashchenko=)
 * 
 */
public class DoVoidParams extends
		AbstractPaypalOperationParams<DoVoidRequestType, DoVoidResponseType> {

{
	OPERATION_NAME = "DoVoid";
}


//required
private String authorizationId;
//optional
private String note;
private String msgSubId;


@Override
public void setParamsFromMap(final Map<String, String[]> params) {

	setAuthorizationId(StrUtil.getPar(params,
			PaypalConstants.PARAM_AUTHORIZATION_ID));
	setNote(StrUtil.getPar(params, PaypalConstants.PARAM_NOTE));
	setMsgSubId(StrUtil.getPar(params, PaypalConstants.PARAM_MSG_SUB_ID));

}


public void setParamsFromRequest(final VoidRequest req) {

	//authorizationId <=> merchantTransactionCode
	setAuthorizationId(req.getRequestId());
	// note     <=> ?   	reqType.setNote(params.getNote());
	// msgSubId <=> ? 			reqType.setMsgSubID(params.getMsgSubId());
}


public VoidResult getConvertedResult() {

	final VoidResult result = new VoidResult();
	result.setRequestTime(new Date());
	result.setRequestId(getAuthorizationId());
	setConvertedResultStatus(result);
	return result;
}


/**
 * @return the authorizationId
 */
public String getAuthorizationId() {

	return authorizationId;
}


/**
 * @param authorizationId
 *          the authorizationId to set
 */
public void setAuthorizationId(final String authorizationId) {

	this.authorizationId = authorizationId;
}


/**
 * @return the note
 */
public String getNote() {

	return note;
}


/**
 * @param note
 *          the note to set
 */
public void setNote(final String note) {

	this.note = note;
}


/**
 * @return the msgSubId
 */
public String getMsgSubId() {

	return msgSubId;
}


/**
 * @param msgSubId
 *          the msgSubId to set
 */
public void setMsgSubId(final String msgSubId) {

	this.msgSubId = msgSubId;
}


}
