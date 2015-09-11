package com.flieger.adyen.commands.impl;

import de.hybris.platform.payment.commands.SubscriptionAuthorizationCommand;
import de.hybris.platform.payment.commands.impl.GenericMockCommand;
import de.hybris.platform.payment.commands.request.SubscriptionAuthorizationRequest;
import de.hybris.platform.payment.commands.result.AuthorizationResult;
import de.hybris.platform.payment.dto.AvsStatus;
import de.hybris.platform.payment.dto.CvnStatus;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;

import java.util.Date;


/**
 * * A mockup Implementation for {@link SubscriptionAuthorizationCommand}. Set *
 * {@link AdyenSubscriptionAuthorizationCommand#INVALID} as subscription id, if you need your transaction rejected. *
 * Otherwise it will be accepted.
 */
public class AdyenSubscriptionAuthorizationCommand extends GenericMockCommand implements SubscriptionAuthorizationCommand
{

	public static final String INVALID = "invalid";

	@Override
	public AuthorizationResult perform(final SubscriptionAuthorizationRequest request)
	{
		final AuthorizationResult result = new AuthorizationResult();

		// Let's be as much polite as possible and return the requested data where it makes sense
		result.setCurrency(request.getCurrency());
		result.setTotalAmount(request.getTotalAmount());

		result.setAvsStatus(AvsStatus.NO_RESULT);
		result.setCvnStatus(CvnStatus.NOT_PROCESSED);

		result.setAuthorizationTime(new Date());

		// And the most important is the authorization algorithm ;)
		if (request.getSubscriptionID().equalsIgnoreCase(INVALID))
		{
			result.setTransactionStatus(TransactionStatus.REJECTED);
			result.setTransactionStatusDetails(TransactionStatusDetails.INVALID_SUBSCRIPTION_ID);
		}
		else
		{
			if (request.getCv2() == null)
			{
				// using HOP
				result.setTransactionStatus(TransactionStatus.ACCEPTED);
				result.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL);
			}
			else
			{
				//fail if cv2 number is odd
				try
				{
					final int i = Integer.parseInt(request.getCv2());
					if (i % 2 == 0)
					{
						result.setTransactionStatus(TransactionStatus.ACCEPTED);
						result.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL);
						result.setCvnStatus(CvnStatus.MATCHED);
					}
					else
					{
						result.setTransactionStatus(TransactionStatus.REJECTED);
						result.setTransactionStatusDetails(TransactionStatusDetails.INVALID_CVN);
						result.setCvnStatus(CvnStatus.REJECTED);
					}
				}
				catch (final Exception e)
				{
					result.setTransactionStatus(TransactionStatus.REJECTED);
					result.setTransactionStatusDetails(TransactionStatusDetails.UNKNOWN_CODE);
					result.setCvnStatus(CvnStatus.UNRECOGNIZED_RESULT);
				}
			}

		}

		genericPerform(request, result);

		return result;
	}
}