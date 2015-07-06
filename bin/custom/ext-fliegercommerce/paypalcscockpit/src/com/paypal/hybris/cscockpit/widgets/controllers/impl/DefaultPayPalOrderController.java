package com.paypal.hybris.cscockpit.widgets.controllers.impl;

import com.paypal.hybris.cscockpit.widgets.controllers.PayPalOrderController;
import com.paypal.hybris.data.GetTransactionDetailsResultData;
import com.paypal.hybris.transaction.TransactionDetailsService;
import de.hybris.platform.cscockpit.widgets.controllers.impl.DefaultOrderController;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class DefaultPayPalOrderController extends DefaultOrderController implements PayPalOrderController
{

	private TransactionDetailsService transactionDetailsService;

	@Override
	public GetTransactionDetailsResultData getTransactionDetails(String transactionId)
	{
		return transactionDetailsService.getTransactionDetails(transactionId);
	}

	public TransactionDetailsService getTransactionDetailsService()
	{
		return transactionDetailsService;
	}

	public void setTransactionDetailsService(TransactionDetailsService transactionDetailsService)
	{
		this.transactionDetailsService = transactionDetailsService;
	}
}
