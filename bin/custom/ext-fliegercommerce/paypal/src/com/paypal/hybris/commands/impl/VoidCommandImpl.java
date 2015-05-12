/**
 * 
 */
package com.paypal.hybris.commands.impl;


import de.hybris.platform.payment.commands.VoidCommand;
import de.hybris.platform.payment.commands.request.VoidRequest;
import de.hybris.platform.payment.commands.result.VoidResult;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.paypal.hybris.soap.params.impl.DoVoidParams;


/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
@Component("voidCommand")
public class VoidCommandImpl extends AbstractCommandImpl implements VoidCommand {


private final static Logger LOG = Logger.getLogger(VoidCommandImpl.class);


/**
 * @see de.hybris.platform.payment.commands.Command#perform(java.lang.Object)
 */
@Override
public VoidResult perform(final VoidRequest req) {

	LOG.info("-----------------------------------------------------");
	LOG.info("VoidCommandImpl perform requested");
	LOG.info("-----------------------------------------------------");


	final DoVoidParams params = new DoVoidParams();
	params.setParamsFromRequest(req);
	paypalService.doVoid(params);
	final VoidResult result = params.getConvertedResult();
	return result;
}

}
