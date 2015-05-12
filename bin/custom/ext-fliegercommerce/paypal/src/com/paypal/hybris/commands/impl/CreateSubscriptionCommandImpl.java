package com.paypal.hybris.commands.impl;


import de.hybris.platform.payment.commands.CreateSubscriptionCommand;
import de.hybris.platform.payment.commands.request.CreateSubscriptionRequest;
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
public class CreateSubscriptionCommandImpl extends AbstractCommandImpl
		implements CreateSubscriptionCommand {


private final static Logger LOG = Logger
		.getLogger(CreateSubscriptionCommandImpl.class);


/**
 * @see de.hybris.platform.payment.commands.Command#perform(java.lang.Object)
 */
@Override
public SubscriptionResult perform(final CreateSubscriptionRequest req) {

	LOG.info("-----------------------------------------------------");
	LOG.info("CreateSubscriptionCommandImpl perform requested");
	LOG.info("-----------------------------------------------------");

	throw new PaypalException(NOT_SUPPORTED_MESSAGE + "CreateSubscriptionCommand");
}

}
