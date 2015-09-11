/**
 * 
 */
package com.flieger.adyen.commands.impl;

import de.hybris.platform.payment.commands.IsApplicableCommand;
import de.hybris.platform.payment.commands.request.IsApplicableCommandReqest;
import de.hybris.platform.payment.commands.result.IsApplicableCommandResult;

/**
 * @author flieger
 *
 */
public class AdyenIsApplicableCommand implements IsApplicableCommand
{

	/* (non-Javadoc)
	 * @see de.hybris.platform.payment.commands.Command#perform(java.lang.Object)
	 */
	@Override
	public IsApplicableCommandResult perform(IsApplicableCommandReqest arg0)
	{
		return new IsApplicableCommandResult(true);
	}

}
