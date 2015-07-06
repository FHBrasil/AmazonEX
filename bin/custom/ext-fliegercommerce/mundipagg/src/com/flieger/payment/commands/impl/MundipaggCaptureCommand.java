package com.flieger.payment.commands.impl;

import de.hybris.platform.payment.commands.CaptureCommand;
import de.hybris.platform.payment.commands.request.CaptureRequest;
import de.hybris.platform.payment.commands.result.CaptureResult;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import java.util.Date;

public class MundipaggCaptureCommand extends MundipaggGenericCommand
		implements CaptureCommand
{

	@Override
	public CaptureResult perform(CaptureRequest request)
	{
		CaptureResult result = new CaptureResult();

		result.setCurrency(request.getCurrency());
		result.setTotalAmount(request.getTotalAmount());
		result.setRequestTime(new Date());

		result.setTransactionStatus(TransactionStatus.ACCEPTED);
		result.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL);

		genericPerform(request, result);

		return result;
	}
}
