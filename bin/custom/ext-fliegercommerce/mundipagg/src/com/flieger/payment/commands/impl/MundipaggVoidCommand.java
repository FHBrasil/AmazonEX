package com.flieger.payment.commands.impl;

import de.hybris.platform.payment.commands.VoidCommand;
import de.hybris.platform.payment.commands.request.VoidRequest;
import de.hybris.platform.payment.commands.result.VoidResult;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import java.util.Date;

public class MundipaggVoidCommand extends MundipaggGenericCommand
		implements VoidCommand
{

	public VoidResult perform(VoidRequest request)
	{
		VoidResult result = new VoidResult();

		result.setRequestTime(new Date());

		result.setTransactionStatus(TransactionStatus.ACCEPTED);
		result.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL);

		genericPerform(request, result);

		return result;
	}
}
