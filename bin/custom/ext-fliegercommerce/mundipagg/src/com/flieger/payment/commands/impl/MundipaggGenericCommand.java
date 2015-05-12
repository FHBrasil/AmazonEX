package com.flieger.payment.commands.impl;

import de.hybris.platform.payment.commands.request.AbstractRequest;
import de.hybris.platform.payment.commands.result.AbstractResult;
import de.hybris.platform.payment.dto.TransactionStatus;
import org.apache.log4j.Logger;

public abstract class MundipaggGenericCommand
{

	protected AbstractResult genericPerform(AbstractRequest request, AbstractResult result)
	{
		result.setMerchantTransactionCode(request.getMerchantTransactionCode());

		Logger.getLogger(getClass()).info(
				"Payment command: " + getClass() + " executed [status: " + result.getTransactionStatus().toString() + "]");

		return result;
	}
}
