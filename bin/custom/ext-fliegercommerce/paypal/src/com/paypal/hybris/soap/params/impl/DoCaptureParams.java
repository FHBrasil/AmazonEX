/**
 * 
 */
package com.paypal.hybris.soap.params.impl;


import de.hybris.platform.payment.commands.request.CaptureRequest;
import de.hybris.platform.payment.commands.result.CaptureResult;

import java.util.Currency;
import java.util.Date;
import java.util.Map;

import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.soap.gen.BasicAmountType;
import com.paypal.hybris.soap.gen.CompleteCodeType;
import com.paypal.hybris.soap.gen.CurrencyCodeType;
import com.paypal.hybris.soap.gen.DoCaptureRequestType;
import com.paypal.hybris.soap.gen.DoCaptureResponseType;
import com.paypal.hybris.soap.gen.MerchantStoreDetailsType;
import com.paypal.hybris.util.StrUtil;


/**
 * @author christina
 * 
 */
public class DoCaptureParams extends
		AbstractPaypalOperationParams<DoCaptureRequestType, DoCaptureResponseType> {

{
	OPERATION_NAME = "DoCapture";
}


//required
private String authorizationId;
private BasicAmountType amount;
private CompleteCodeType completeType;


//optional params
private String note;
private String invoiceId;
private String softDescriptor;
private String msgSubId;
private MerchantStoreDetailsType merchantStoreDetails;


@Override
public void setParamsFromMap(final Map<String, String[]> params) {

	final String invoiceIdString = StrUtil.getPar(params,
			PaypalConstants.PARAM_INVOICE_ID);
	final String noteString = StrUtil.getPar(params, PaypalConstants.PARAM_NOTE);
	final String softDescriptorString = StrUtil.getPar(params,
			PaypalConstants.PARAM_SOFT_DESCRIPTOR);
	final String msgSubIdString = StrUtil.getPar(params,
			PaypalConstants.PARAM_MSG_SUB_ID);
	//	final String storeIdString = StrUtil.getPar(params,
	//			PaypalConstants.PARAM_STORE_ID);
	final String terminalIdString = StrUtil.getPar(params,
			PaypalConstants.PARAM_TERMINAL_ID);
	final String authorizationIdString = StrUtil.getPar(params,
			PaypalConstants.PARAM_AUTHORIZATION_ID);
	final String merchantStoreDetailsString = StrUtil.getPar(params,
			PaypalConstants.PARAM_MERCHANT_STORE_DETAILS);
	final String amountString = StrUtil.getPar(params,
			PaypalConstants.PARAM_AMOUNT);
	final String currencyCodeString = StrUtil.getPar(params,
			PaypalConstants.PARAM_CURRENCY);
	final String completeTypeString = StrUtil.getPar(params,
			PaypalConstants.PARAM_COMPLETE_TYPE);

	//setting String params
	setInvoiceId(invoiceIdString);
	setNote(noteString);
	setSoftDescriptor(softDescriptorString);
	setMsgSubId(msgSubIdString);
	setAuthorizationId(authorizationIdString);

	//setting object params
	final MerchantStoreDetailsType type = new MerchantStoreDetailsType();
	type.setStoreID(merchantStoreDetailsString);
	type.setTerminalID(terminalIdString);
	setMerchantStoreDetails(type);

	final BasicAmountType amountType = new BasicAmountType();
	amountType.setValue(StrUtil.formatNumber(StrUtil.toDouble(amountString))); //need to be checked
	amountType.setCurrencyID(CurrencyCodeType.fromValue(currencyCodeString));
	setAmount(amountType);

	setCompleteType(CompleteCodeType.fromValue(completeTypeString));


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


/**
 * @return the completeType
 */
public CompleteCodeType getCompleteType() {

	return completeType;
}


/**
 * @param completeType
 *          the completeType to set
 */
public void setCompleteType(final CompleteCodeType completeType) {

	this.completeType = completeType;
}


/**
 * @return the invoiceId
 */
public String getInvoiceId() {

	return invoiceId;
}


/**
 * @param invoiceId
 *          the invoiceId to set
 */
public void setInvoiceId(final String invoiceId) {

	this.invoiceId = invoiceId;
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
 * @return the softDescriptor
 */
public String getSoftDescriptor() {

	return softDescriptor;
}


/**
 * @param softDescriptor
 *          the softDescriptor to set
 */
public void setSoftDescriptor(final String softDescriptor) {

	this.softDescriptor = softDescriptor;
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


/**
 * @return the merchantStoreDetails
 */
public MerchantStoreDetailsType getMerchantStoreDetails() {

	return merchantStoreDetails;
}


/**
 * @param merchantStoreDetails
 *          the merchantStoreDetails to set
 */
public void setMerchantStoreDetails(
		final MerchantStoreDetailsType merchantStoreDetails) {

	this.merchantStoreDetails = merchantStoreDetails;
}


/**
 * @param req
 */
public void setParamsFromRequest(final CaptureRequest req) {

	final String amountString = StrUtil.formatNumber(req.getTotalAmount());
	final String currencyCodeString = req.getCurrency().getCurrencyCode();
	final BasicAmountType amountType = new BasicAmountType();
	amountType.setValue(StrUtil.formatNumber(StrUtil.toDouble(amountString)));
	amountType.setCurrencyID(CurrencyCodeType.fromValue(currencyCodeString));
	setAuthorizationId(req.getRequestId());
	setAmount(amountType);
}


/**
 * @return paypal response converted to a CaptureResult object
 */
public CaptureResult getConvertedResult() {

	final CaptureResult result = new CaptureResult();

	final BasicAmountType amount = getRequest().getAmount();

	result.setCurrency(Currency.getInstance(amount.getCurrencyID().name()));
	result.setTotalAmount(StrUtil.toBigDecimal(amount.getValue()));
	result.setRequestTime(new Date());
	setConvertedResultStatus(result);
	return result;
}


}
