/**
 * 
 */
package com.flieger.adyen.commands.impl;

import org.apache.log4j.Logger;

import com.adyen.services.payment.Recurring;
import com.adyen.services.recurring.AdyenRecurringDetailsResult;
import com.adyen.services.recurring.AdyenRecurringListDetailsRequest;
import com.adyen.services.recurring.RecurringDetail;
import com.adyen.services.recurring.RecurringDetailsRequest;
import com.adyen.services.recurring.RecurringDetailsResult;
import com.adyen.services.recurring.RecurringPortTypeProxy;
import com.flieger.adyen.command.RecurringDetailsCommand;

/**
 * @author flieger
 * 
 */
public class AdyenRecurringDetailsCommand implements RecurringDetailsCommand
{
	private static final String ONECLICK = "ONECLICK";
	private static final Logger LOG = Logger.getLogger(AdyenRecurringDetailsCommand.class.getName());

	@Override
	public AdyenRecurringDetailsResult perform(AdyenRecurringListDetailsRequest request)
	{
		AdyenRecurringDetailsResult result = new AdyenRecurringDetailsResult();
		RecurringDetailsResult recurResult = null;
		try
		{
			RecurringDetailsRequest recurringRequest = new RecurringDetailsRequest();
			recurringRequest.setMerchantAccount(request.getMerchantAccount());
			recurringRequest.setShopperReference(request.getShopperReference());
			recurringRequest.setRecurring(new Recurring(ONECLICK, request.getShopperId()));
			
			try
			{
				RecurringPortTypeProxy proxy = new RecurringPortTypeProxy();
				recurResult = proxy.listRecurringDetails(recurringRequest);
			}
			catch (Exception e)
			{
				LOG.error("Error: [" + e.getMessage() + "]", e);
				return result;
			}
			result.setLastKnownShopperEmail(recurResult.getLastKnownShopperEmail());
			result.setShopperReference(recurResult.getShopperReference());
			if(recurResult.getDetails() != null) {
				for(RecurringDetail entry : recurResult.getDetails()){
					result.setRecurringDetailReference(entry.getRecurringDetailReference());
				}
			}
		}
		catch (Exception ex)
		{
			LOG.error("Error: [" + ex.getMessage() + "]", ex);
			return new AdyenRecurringDetailsResult();
		}
		
		return result;
	}

}
