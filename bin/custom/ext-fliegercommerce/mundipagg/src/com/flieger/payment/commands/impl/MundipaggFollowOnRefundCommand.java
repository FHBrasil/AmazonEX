package com.flieger.payment.commands.impl;

import de.hybris.platform.payment.commands.FollowOnRefundCommand;
import de.hybris.platform.payment.commands.request.FollowOnRefundRequest;
import de.hybris.platform.payment.commands.result.RefundResult;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import java.util.Date;

public class MundipaggFollowOnRefundCommand extends MundipaggGenericCommand
		implements FollowOnRefundCommand<FollowOnRefundRequest>
{

	public RefundResult perform(FollowOnRefundRequest request)
	{
		RefundResult result = new RefundResult();

		result.setCurrency(request.getCurrency());
		result.setTotalAmount(request.getTotalAmount());
		result.setRequestTime(new Date());

		result.setTransactionStatus(TransactionStatus.ACCEPTED);
		result.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL);

		genericPerform(request, result);

		return null;
	}
}
