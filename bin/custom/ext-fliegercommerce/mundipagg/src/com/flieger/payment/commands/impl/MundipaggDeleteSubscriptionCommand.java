package com.flieger.payment.commands.impl;

import de.hybris.platform.payment.commands.DeleteSubscriptionCommand;
import de.hybris.platform.payment.commands.request.DeleteSubscriptionRequest;
import de.hybris.platform.payment.commands.result.SubscriptionResult;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;

public class MundipaggDeleteSubscriptionCommand extends MundipaggGenericCommand
		implements DeleteSubscriptionCommand
{

	public SubscriptionResult perform(DeleteSubscriptionRequest request)
	{
		SubscriptionResult result = new SubscriptionResult();

		result.setSubscriptionID(request.getSubscriptionID());

		result.setTransactionStatus(TransactionStatus.ACCEPTED);
		result.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL);

		genericPerform(request, result);

		return result;
	}
}
