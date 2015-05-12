/**
 * 
 */
package com.flieger.adyen.commands.impl;

import de.hybris.platform.payment.commands.CreateSubscriptionCommand;
import de.hybris.platform.payment.commands.request.CreateSubscriptionRequest;
import de.hybris.platform.payment.commands.result.SubscriptionResult;
import de.hybris.platform.payment.dto.TransactionStatus;

import com.flieger.payment.jalo.AdyenCreateSubscriptionRequest;
import com.flieger.utils.SubscriptionIDCreator;


/**
 * @author flieger
 *
 */
public class AdyenCreateSubscriptionCommand implements CreateSubscriptionCommand
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.payment.commands.Command#perform(java.lang.Object)
	 */
	@Override
	public SubscriptionResult perform(CreateSubscriptionRequest request)
	{
		SubscriptionResult result = new SubscriptionResult();

		String uid = null;
		
		if (request.getCard() == null)
		{
			uid = request.getMerchantTransactionCode() + request.getBillingInfo();
		} else {
			uid = request.getCard().getCardHolderFullName() + request.getBillingInfo();
		}
		
		result.setSubscriptionID(SubscriptionIDCreator.createSubscriptionID(uid));
		result.setTransactionStatus(TransactionStatus.ACCEPTED);
		result.setMerchantTransactionCode(request.getMerchantTransactionCode());

		return result;
	}

}
