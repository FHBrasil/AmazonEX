/**
 *
 */
package com.paypal.hybris.commands.impl;

import de.hybris.platform.payment.commands.AuthorizationCommand;
import de.hybris.platform.payment.commands.request.AuthorizationRequest;
import de.hybris.platform.payment.commands.result.AuthorizationResult;

import org.apache.log4j.Logger;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public class AuthorizationCommandImpl implements AuthorizationCommand
{
	private final static Logger LOG = Logger.getLogger(AuthorizationCommandImpl.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.payment.commands.Command#perform(java.lang.Object)
	 */
	@Override
	public AuthorizationResult perform(final AuthorizationRequest arg0)
	{
		LOG.debug("Command is not supported");
		// TODO: Auto-generated method stub
		return null;
	}
}
