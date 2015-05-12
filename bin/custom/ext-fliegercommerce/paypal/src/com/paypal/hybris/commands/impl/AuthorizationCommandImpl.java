/**
 * 
 */
package com.paypal.hybris.commands.impl;


import de.hybris.platform.payment.commands.AuthorizationCommand;
import de.hybris.platform.payment.commands.request.AuthorizationRequest;
import de.hybris.platform.payment.commands.result.AuthorizationResult;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.paypal.hybris.soap.params.impl.DoAuthorizationParams;


/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
@Component("authorizationCommand")
public class AuthorizationCommandImpl extends AbstractCommandImpl implements
		AuthorizationCommand {

private final static Logger LOG = Logger
		.getLogger(AuthorizationCommandImpl.class);


/**
 * @see de.hybris.platform.payment.commands.Command#perform(java.lang.Object)
 */
@Override
public AuthorizationResult perform(final AuthorizationRequest req) {

	LOG.info("-----------------------------------------------------");
	LOG.info("AuthorizationCommandImpl perform requested");
	LOG.info("-----------------------------------------------------");

	// Convert parameters
	final DoAuthorizationParams params = new DoAuthorizationParams();
	params.setParamsFromRequest(req);
	paypalService.doAuthorization(params);
	final AuthorizationResult res = params.getConvertedResult();
	res.setCurrency(req.getCurrency());
	res.setTotalAmount(req.getTotalAmount());
	res.setAuthorizationTime(new Date());

	return res;
}

}
