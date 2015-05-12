/**
 * 
 */
package com.paypal.hybris.commands.impl;


import de.hybris.platform.payment.commands.UpdateSubscriptionCommand;
import de.hybris.platform.payment.commands.request.UpdateSubscriptionRequest;
import de.hybris.platform.payment.commands.result.SubscriptionResult;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;

import org.apache.log4j.Logger;

import com.paypal.hybris.exception.PaypalException;


/**
 * WARNING! This command is NOT supported by the PayPal extension.
 * 
 * @author Valentyn Markovych, Gorilla
 * 
 */
public class UpdateSubscriptionCommandImpl extends AbstractCommandImpl
		implements UpdateSubscriptionCommand {


private final static Logger LOG = Logger
		.getLogger(UpdateSubscriptionCommandImpl.class);


/**
 * @see de.hybris.platform.payment.commands.Command#perform(java.lang.Object)
 */
@Override
public SubscriptionResult perform(final UpdateSubscriptionRequest req) {

	LOG.info("-----------------------------------------------------");
	LOG.info("UpdateSubscriptionCommandImpl perform requested");
	LOG.info("-----------------------------------------------------");

	throw new PaypalException(NOT_SUPPORTED_MESSAGE + "CreateSubscriptionCommand");
}

}
