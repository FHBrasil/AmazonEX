package com.paypal.hybris.converters.populators.impl;

import com.ebay.api.PaymentTransactionSearchResultType;
import com.ebay.api.TransactionSearchResponseType;
import com.paypal.hybris.data.PaymentTransaction;
import com.paypal.hybris.data.TransactionSearchResultData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class TransactionSearchResultDataPopulator implements Populator<TransactionSearchResponseType, TransactionSearchResultData>
{
	/**
	 * Populate the target instance with values from the source instance.
	 *
	 * @param response the source object
	 * @param resultData   the target to fill
	 * @throws de.hybris.platform.servicelayer.dto.converter.ConversionException if an error occurs
	 */
	@Override public void populate(TransactionSearchResponseType response, TransactionSearchResultData resultData)
			throws ConversionException
	{
		List<PaymentTransactionSearchResultType> paymentTransactionList = response.getPaymentTransactions();
		List<PaymentTransaction> paymentTransactionDataList = new ArrayList<>();
		resultData.setTransactionList(paymentTransactionDataList);

		if (CollectionUtils.isNotEmpty(paymentTransactionList))
		{
			for (PaymentTransactionSearchResultType transaction: paymentTransactionList)
			{
				PaymentTransaction transactionData = new PaymentTransaction();
				transactionData.setTransactionId(transaction.getTransactionID());
				transactionData.setPayerEmail(transaction.getPayer());
				Calendar timestamp = transaction.getTimestamp().toGregorianCalendar();
				transactionData.setTimestamp(timestamp);
				transactionData.setTimezone(transaction.getTimezone());
				transactionData.setType(transaction.getType());
				paymentTransactionDataList.add(transactionData);
			}
		}
	}
}
