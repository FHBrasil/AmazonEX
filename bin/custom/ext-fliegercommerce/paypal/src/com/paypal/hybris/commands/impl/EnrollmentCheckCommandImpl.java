/**
 * 
 */
package com.paypal.hybris.commands.impl;


import de.hybris.platform.payment.commands.EnrollmentCheckCommand;
import de.hybris.platform.payment.commands.request.EnrollmentCheckRequest;
import de.hybris.platform.payment.commands.result.EnrollmentCheckResult;
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
public class EnrollmentCheckCommandImpl extends AbstractCommandImpl implements
		EnrollmentCheckCommand {


private final static Logger LOG = Logger
		.getLogger(EnrollmentCheckCommandImpl.class);


/**
 * @see de.hybris.platform.payment.commands.Command#perform(java.lang.Object)
 */
@Override
public EnrollmentCheckResult perform(final EnrollmentCheckRequest req) {

	LOG.info("-----------------------------------------------------");
	LOG.info("EnrollmentCheckCommandImpl perform requested");
	LOG.info("-----------------------------------------------------");

	throw new PaypalException(NOT_SUPPORTED_MESSAGE + "EnrollmentCheckCommand");
}

}
