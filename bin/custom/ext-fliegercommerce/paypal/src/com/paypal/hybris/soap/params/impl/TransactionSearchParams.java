package com.paypal.hybris.soap.params.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.jfree.util.Log;

import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.soap.gen.BasicAmountType;
import com.paypal.hybris.soap.gen.CurrencyCodeType;
import com.paypal.hybris.soap.gen.PaymentTransactionClassCodeType;
import com.paypal.hybris.soap.gen.PaymentTransactionStatusCodeType;
import com.paypal.hybris.soap.gen.PersonNameType;
import com.paypal.hybris.soap.gen.TransactionSearchRequestType;
import com.paypal.hybris.soap.gen.TransactionSearchResponseType;
import com.paypal.hybris.util.StrUtil;


/**
 * @author christina
 * 
 */
public class TransactionSearchParams
		extends
		AbstractPaypalOperationParams<TransactionSearchRequestType, TransactionSearchResponseType> {

{
	OPERATION_NAME = "TransactionSearch";
}


public final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss z";

private Calendar startDate;
private Calendar endDate;
private String transactionId;
private BasicAmountType amount;
private String payer;
private String receiver;
private String profileID;
private PersonNameType payerName;
private String receiptID;
private String invoiceID;
private String cardNumber;
private String auctionItemNumber;
private PaymentTransactionClassCodeType transactionClass;
private CurrencyCodeType currencyCode;
private PaymentTransactionStatusCodeType status;
private final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);


@Override
public void setParamsFromMap(final Map<String, String[]> params) {

	final String startDateString = StrUtil.getPar(params,
			PaypalConstants.PARAM_START_DATE);
	final String endDateString = StrUtil.getPar(params,
			PaypalConstants.PARAM_END_DATE);
	final String amountString = StrUtil.getPar(params,
			PaypalConstants.PARAM_AMOUNT);
	final String statusString = StrUtil.getPar(params,
			PaypalConstants.PARAM_STATUS);
	final String currencyCode = StrUtil.getPar(params,
			PaypalConstants.PARAM_CURRENCY);
	final String payerNameString = StrUtil.getPar(params,
			PaypalConstants.PARAM_PAYER_NAME);
	final String transactionClassString = StrUtil.getPar(params,
			PaypalConstants.PARAM_TRANSACTION_CLASS);
	final String transactionIdString = StrUtil.getPar(params,
			PaypalConstants.PARAM_TRANSACTION_ID);
	final String auctionItemNumberString = StrUtil.getPar(params,
			PaypalConstants.PARAM_AUCTION_ITEM_NUMBER);
	final String cardNumberString = StrUtil.getPar(params,
			PaypalConstants.PARAM_CARD_NUMBER);
	final String invoiceIdString = StrUtil.getPar(params,
			PaypalConstants.PARAM_INVOICE_ID);
	final String payerEmailString = StrUtil.getPar(params,
			PaypalConstants.PARAM_PAYER_EMAIL);
	final String profileIdString = StrUtil.getPar(params,
			PaypalConstants.PARAM_PROFILE_ID);
	final String receiptIdString = StrUtil.getPar(params,
			PaypalConstants.PARAM_RECEIPT_ID);
	final String receiverString = StrUtil.getPar(params,
			PaypalConstants.PARAM_RECEIVER_EMAIL);

	final BasicAmountType amountType = new BasicAmountType();
	amountType.setValue(StrUtil.formatNumber(StrUtil.toDouble(amountString)));

	if (currencyCode != null) {
		final CurrencyCodeType type = CurrencyCodeType.fromValue(currencyCode);
		setCurrencyCode(type);
		amountType.setCurrencyID(type);

	}
	if (statusString != null) {
		setStatus(PaymentTransactionStatusCodeType.valueOf(statusString));
	}

	if (startDateString != null) {
		final Calendar c = Calendar.getInstance();
		long start = 0;
		try {
			start = dateFormat.parse(startDateString).getTime();
		} catch (final ParseException e) {
			e.printStackTrace();
			Log.info("StartDate has been parsed incorrect!");
		}
		c.setTimeInMillis(start);
		setStartDate(c);
	}
	if (endDateString != null) {
		final Calendar c = Calendar.getInstance();
		long end = 0;
		try {
			end = dateFormat.parse(endDateString).getTime();
		} catch (final ParseException e) {
			e.printStackTrace();
			Log.info("EndDate has been parsed incorrect!");
		}
		c.setTimeInMillis(end);
		setEndDate(c);
	}
	if (payerNameString != null) {
		final PersonNameType type = new PersonNameType();
		type.setLastName(payerNameString);
		setPayerName(type);
	}
	if (transactionClassString != null) {
		setTransactionClass(PaymentTransactionClassCodeType
				.valueOf(transactionClassString));
	}

	setTransactionId(transactionIdString);
	setAmount(amountType);
	setAuctionItemNumber(auctionItemNumberString);
	setCardNumber(cardNumberString);
	setInvoiceID(invoiceIdString);
	setPayer(payerEmailString);
	setProfileID(profileIdString);
	setReceiptID(receiptIdString);
	setReceiver(receiverString);


}


/**
 * @return the startDate
 */
public Calendar getStartDate() {

	return startDate;
}


/**
 * @param startDate
 *          the startDate to set
 */
public void setStartDate(final Calendar startDate) {

	this.startDate = startDate;
}


/**
 * @return the endDate
 */
public Calendar getEndDate() {

	return endDate;
}


/**
 * @param endDate
 *          the endDate to set
 */
public void setEndDate(final Calendar endDate) {

	this.endDate = endDate;
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


/**
 * @return the payer
 */
public String getPayer() {

	return payer;
}


/**
 * @param payer
 *          the payer to set
 */
public void setPayer(final String payer) {

	this.payer = payer;
}


/**
 * @return the receiver
 */
public String getReceiver() {

	return receiver;
}


/**
 * @param receiver
 *          the receiver to set
 */
public void setReceiver(final String receiver) {

	this.receiver = receiver;
}


/**
 * @return the profileId
 */
public String getProfileID() {

	return profileID;
}


/**
 * @param profileID
 *          the profileId to set
 */
public void setProfileID(final String profileID) {

	this.profileID = profileID;
}


/**
 * @return the payerName
 */
public PersonNameType getPayerName() {

	return payerName;
}


/**
 * @param payerName
 *          the payerName to set
 */
public void setPayerName(final PersonNameType payerName) {

	this.payerName = payerName;
}


/**
 * @return the receiptID
 */
public String getReceiptID() {

	return receiptID;
}


/**
 * @param receiptID
 *          the receiptID to set
 */
public void setReceiptID(final String receiptID) {

	this.receiptID = receiptID;
}


/**
 * @return the invoiceID
 */
public String getInvoiceID() {

	return invoiceID;
}


/**
 * @param invoiceID
 *          the invoiceID to set
 */
public void setInvoiceID(final String invoiceID) {

	this.invoiceID = invoiceID;
}


/**
 * @return the cardNumber
 */
public String getCardNumber() {

	return cardNumber;
}


/**
 * @param cardNumber
 *          the cardNumber to set
 */
public void setCardNumber(final String cardNumber) {

	this.cardNumber = cardNumber;
}


/**
 * @return the auctionItemNumber
 */
public String getAuctionItemNumber() {

	return auctionItemNumber;
}


/**
 * @param auctionItemNumber
 *          the auctionItemNumber to set
 */
public void setAuctionItemNumber(final String auctionItemNumber) {

	this.auctionItemNumber = auctionItemNumber;
}


/**
 * @return the transactionClass
 */
public PaymentTransactionClassCodeType getTransactionClass() {

	return transactionClass;
}


/**
 * @param transactionClass
 *          the transactionClass to set
 */
public void setTransactionClass(
		final PaymentTransactionClassCodeType transactionClass) {

	this.transactionClass = transactionClass;
}


/**
 * @return the currencyCode
 */
public CurrencyCodeType getCurrencyCode() {

	return currencyCode;
}


/**
 * @param currencyCode
 *          the currencyCode to set
 */
public void setCurrencyCode(final CurrencyCodeType currencyCode) {

	this.currencyCode = currencyCode;
}


/**
 * @return the status
 */
public PaymentTransactionStatusCodeType getStatus() {

	return status;
}


/**
 * @param status
 *          the status to set
 */
public void setStatus(final PaymentTransactionStatusCodeType status) {

	this.status = status;
}


}
