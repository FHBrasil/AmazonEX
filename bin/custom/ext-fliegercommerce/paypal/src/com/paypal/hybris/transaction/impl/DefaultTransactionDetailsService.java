package com.paypal.hybris.transaction.impl;

import com.ebay.api.GetTransactionDetailsRequestType;
import com.ebay.api.GetTransactionDetailsResponseType;
import com.paypal.hybris.converters.impl.PayPalRequestDataConverter;
import com.paypal.hybris.converters.impl.PayPalResponseConverter;
import com.paypal.hybris.data.GetTransactionDetailsRequestData;
import com.paypal.hybris.data.GetTransactionDetailsResultData;
import com.paypal.hybris.service.PaypalPaymentService;
import com.paypal.hybris.transaction.TransactionDetailsService;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class DefaultTransactionDetailsService implements TransactionDetailsService
{
	private PaypalPaymentService paypalPaymentService;
	private PayPalRequestDataConverter getTransactionDetailsReqDataConverter;
	private PayPalResponseConverter getTransactionDetailsResConverter;

	@Override
	public GetTransactionDetailsResultData getTransactionDetails(String transactionId)
	{
		GetTransactionDetailsRequestData requestData = new GetTransactionDetailsRequestData();
		requestData.setTransactionId(transactionId);

		GetTransactionDetailsRequestType request = (GetTransactionDetailsRequestType) getTransactionDetailsReqDataConverter.convert(requestData);
		GetTransactionDetailsResponseType responseType = paypalPaymentService.getTransactionDetails(request);
		GetTransactionDetailsResultData resultData = (GetTransactionDetailsResultData) getTransactionDetailsResConverter.convert(responseType);

		return resultData;
	}

	public PaypalPaymentService getPaypalPaymentService()
	{
		return paypalPaymentService;
	}

	public void setPaypalPaymentService(PaypalPaymentService paypalPaymentService)
	{
		this.paypalPaymentService = paypalPaymentService;
	}

	public PayPalRequestDataConverter getGetTransactionDetailsReqDataConverter()
	{
		return getTransactionDetailsReqDataConverter;
	}

	public void setGetTransactionDetailsReqDataConverter(
			PayPalRequestDataConverter getTransactionDetailsReqDataConverter)
	{
		this.getTransactionDetailsReqDataConverter = getTransactionDetailsReqDataConverter;
	}

	public PayPalResponseConverter getGetTransactionDetailsResConverter()
	{
		return getTransactionDetailsResConverter;
	}

	public void setGetTransactionDetailsResConverter(PayPalResponseConverter getTransactionDetailsResConverter)
	{
		this.getTransactionDetailsResConverter = getTransactionDetailsResConverter;
	}
}
