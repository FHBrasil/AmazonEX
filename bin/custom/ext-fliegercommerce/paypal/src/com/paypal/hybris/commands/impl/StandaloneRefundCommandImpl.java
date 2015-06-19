/**
 *
 */
package com.paypal.hybris.commands.impl;

import de.hybris.platform.payment.commands.StandaloneRefundCommand;
import de.hybris.platform.payment.commands.request.AbstractRequest;
import de.hybris.platform.payment.commands.result.RefundResult;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public class StandaloneRefundCommandImpl<T extends AbstractRequest> implements StandaloneRefundCommand<T>
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.payment.commands.StandaloneRefundCommand#perform(de.hybris.platform.payment.commands.request
	 * .AbstractRequest)
	 */
	@Override
	public RefundResult perform(final T arg0)
	{
		// TODO: Auto-generated method stub
		return null;
	}

}
