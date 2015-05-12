/**
 * 
 */
package com.paypal.hybris.commands.impl;


import de.hybris.platform.payment.commands.GetSubscriptionDataCommand;
import de.hybris.platform.payment.commands.request.SubscriptionDataRequest;
import de.hybris.platform.payment.commands.result.SubscriptionDataResult;

import org.apache.log4j.Logger;

import com.paypal.hybris.exception.PaypalException;


/**
 * WARNING! This command is NOT supported by the PayPal extension.
 * 
 * @author Valentyn Markovych, Gorilla
 * 
 */
public class GetSubscriptionDataCommandImpl extends AbstractCommandImpl
		implements GetSubscriptionDataCommand {


private final static Logger LOG = Logger
		.getLogger(GetSubscriptionDataCommandImpl.class);


/**
 * @see de.hybris.platform.payment.commands.Command#perform(java.lang.Object)
 */
@Override
public SubscriptionDataResult perform(final SubscriptionDataRequest req) {

	LOG.info("-----------------------------------------------------");
	LOG.info("GetSubscriptionDataCommandImpl perform requested");
	LOG.info("-----------------------------------------------------");

	throw new PaypalException(NOT_SUPPORTED_MESSAGE
			+ "GetSubscriptionDataCommand");
}


}
