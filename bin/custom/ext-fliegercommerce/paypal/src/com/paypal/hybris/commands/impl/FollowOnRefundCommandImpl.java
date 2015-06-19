/**
 *
 */
package com.paypal.hybris.commands.impl;

import de.hybris.platform.payment.commands.FollowOnRefundCommand;
import de.hybris.platform.payment.commands.request.AbstractRequest;
import de.hybris.platform.payment.commands.result.RefundResult;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public class FollowOnRefundCommandImpl<T extends AbstractRequest> implements FollowOnRefundCommand<T>
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.payment.commands.FollowOnRefundCommand#perform(de.hybris.platform.payment.commands.request.
	 * AbstractRequest)
	 */
	@Override
	public RefundResult perform(final T arg0)
	{
		// TODO: Auto-generated method stub
		return null;
	}

}
