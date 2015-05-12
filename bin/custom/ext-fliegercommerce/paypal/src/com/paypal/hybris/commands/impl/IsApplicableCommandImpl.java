/**
 * 
 */
package com.paypal.hybris.commands.impl;


import de.hybris.platform.payment.commands.IsApplicableCommand;
import de.hybris.platform.payment.commands.request.IsApplicableCommandReqest;
import de.hybris.platform.payment.commands.result.IsApplicableCommandResult;

import org.apache.log4j.Logger;


/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
public class IsApplicableCommandImpl extends AbstractCommandImpl implements
		IsApplicableCommand {


private final static Logger LOG = Logger
		.getLogger(IsApplicableCommandImpl.class);


/**
 * @see de.hybris.platform.payment.commands.Command#perform(java.lang.Object)
 */
@Override
public IsApplicableCommandResult perform(final IsApplicableCommandReqest req) {


	LOG.info("-----------------------------------------------------");
	LOG.info("IsApplicableCommandResult perform requested");
	LOG.info("-----------------------------------------------------");

	return new IsApplicableCommandResult(true);
}

}
