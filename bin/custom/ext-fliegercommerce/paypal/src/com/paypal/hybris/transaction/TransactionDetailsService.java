package com.paypal.hybris.transaction;

import com.paypal.hybris.data.GetTransactionDetailsResultData;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public interface TransactionDetailsService
{
	GetTransactionDetailsResultData getTransactionDetails(String transactionId);
}
