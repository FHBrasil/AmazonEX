package com.paypal.hybris.commands.impl;


import de.hybris.platform.payment.commands.SubscriptionAuthorizationCommand;
import de.hybris.platform.payment.commands.request.SubscriptionAuthorizationRequest;
import de.hybris.platform.payment.commands.result.AuthorizationResult;

import org.apache.log4j.Logger;

import com.paypal.hybris.exception.PaypalException;


/**
 * WARNING! This command is NOT supported by the PayPal extension.
 * 
 * @author Valentyn Markovych, Gorilla
 * 
 */
public class SubscriptionAuthorizationCommandImpl extends AbstractCommandImpl
		implements SubscriptionAuthorizationCommand {


private final static Logger LOG = Logger
		.getLogger(SubscriptionAuthorizationCommandImpl.class);


/**
 * @see de.hybris.platform.payment.commands.Command#perform(java.lang.Object)
 */
@Override
public AuthorizationResult perform(final SubscriptionAuthorizationRequest req) {

	LOG.info("-----------------------------------------------------");
	LOG.info("SubscriptionAuthorizationCommandImpl perform requested");
	LOG.info("-----------------------------------------------------");

	throw new PaypalException(NOT_SUPPORTED_MESSAGE
			+ "SubscriptionAuthorizationCommand");
}

}
