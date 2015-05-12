package com.flieger.payment.commands.impl;

import de.hybris.platform.payment.commands.EnrollmentCheckCommand;
import de.hybris.platform.payment.commands.request.EnrollmentCheckRequest;
import de.hybris.platform.payment.commands.result.EnrollmentCheckResult;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;

public class MundipaggEnrollmentCheckCommand extends MundipaggGenericCommand
		implements EnrollmentCheckCommand
{

	public EnrollmentCheckResult perform(EnrollmentCheckRequest request)
	{
		EnrollmentCheckResult result = new EnrollmentCheckResult(request.getMerchantTransactionCode());

		result.setTransactionStatus(TransactionStatus.ACCEPTED);
		result.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL);

		genericPerform(request, result);

		return result;
	}
}
