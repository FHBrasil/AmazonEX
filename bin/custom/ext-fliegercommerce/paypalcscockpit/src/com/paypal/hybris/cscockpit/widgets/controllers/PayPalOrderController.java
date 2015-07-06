package com.paypal.hybris.cscockpit.widgets.controllers;

import com.paypal.hybris.data.GetTransactionDetailsResultData;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public interface PayPalOrderController
{
	public GetTransactionDetailsResultData getTransactionDetails(String transactionId);
}
