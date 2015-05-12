/**
 * 
 */
package com.paypal.hybris.commands.impl;


import de.hybris.platform.payment.commands.DeleteSubscriptionCommand;
import de.hybris.platform.payment.commands.request.DeleteSubscriptionRequest;
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
public class DeleteSubscriptionCommandImpl extends AbstractCommandImpl
		implements DeleteSubscriptionCommand {


private final static Logger LOG = Logger
		.getLogger(DeleteSubscriptionCommandImpl.class);


/**
 * @see de.hybris.platform.payment.commands.Command#perform(java.lang.Object)
 */
@Override
public SubscriptionResult perform(final DeleteSubscriptionRequest req) {

	LOG.info("-----------------------------------------------------");
	LOG.info("DeleteSubscriptionCommandImpl perform requested");
	LOG.info("-----------------------------------------------------");

	throw new PaypalException(NOT_SUPPORTED_MESSAGE + "DeleteSubscriptionCommand");
}

}
