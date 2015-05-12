package com.flieger.payment.commands.impl;

import de.hybris.platform.core.enums.CreditCardType;
import de.hybris.platform.payment.commands.GetSubscriptionDataCommand;
import de.hybris.platform.payment.commands.request.SubscriptionDataRequest;
import de.hybris.platform.payment.commands.result.SubscriptionDataResult;
import de.hybris.platform.payment.dto.BillingInfo;
import de.hybris.platform.payment.dto.CardInfo;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;

public class MundipaggGetSubscriptionDataCommand extends MundipaggGenericCommand
		implements GetSubscriptionDataCommand
{

	public SubscriptionDataResult perform(SubscriptionDataRequest request)
	{
		SubscriptionDataResult result = new SubscriptionDataResult();

		result.setSubscriptionID(request.getSubscriptionID());

		BillingInfo billingInfo = new BillingInfo();
		billingInfo.setFirstName("Quentin");
		billingInfo.setLastName("Tarantino");
		billingInfo.setStreet1("1234 N Holywood Avenue");
		billingInfo.setStreet2("Apt No #4321");
		billingInfo.setCity("The city of Angels");
		billingInfo.setPostalCode("12321");
		billingInfo.setState("Golden State");
		billingInfo.setCountry("USA");
		result.setBillingInfo(billingInfo);

		CardInfo cardInfo = new CardInfo();
		cardInfo.setCardType(CreditCardType.MASTER);
		cardInfo.setCardNumber("1111222233334444");
		cardInfo.setExpirationMonth(Integer.valueOf(6));
		cardInfo.setExpirationYear(Integer.valueOf(6));
		result.setCard(cardInfo);

		result.setTransactionStatus(TransactionStatus.ACCEPTED);
		result.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL);

		genericPerform(request, result);

		return result;
	}
}
