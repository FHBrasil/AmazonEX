package com.paypal.hybris.commands.impl;


import de.hybris.platform.payment.commands.CaptureCommand;
import de.hybris.platform.payment.commands.request.CaptureRequest;
import de.hybris.platform.payment.commands.result.CaptureResult;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.paypal.hybris.soap.gen.CompleteCodeType;
import com.paypal.hybris.soap.params.impl.DoCaptureParams;


/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
@Component("captureCommand")
public class CaptureCommandImpl extends AbstractCommandImpl implements
		CaptureCommand {

private final static Logger LOG = Logger.getLogger(CaptureCommandImpl.class);


/**
 * @see de.hybris.platform.payment.commands.Command#perform(java.lang.Object)
 */
@Override
public CaptureResult perform(final CaptureRequest req) {

	LOG.info("-----------------------------------------------------");
	LOG.info("CaptureCommandImpl perform requested");
	LOG.info("-----------------------------------------------------");
	LOG.info("token: " + req.getRequestToken());
	LOG.info("transactionId: " + req.getRequestId());


	final DoCaptureParams params = new DoCaptureParams();
	params.setParamsFromRequest(req);
	params.setCompleteType(CompleteCodeType.COMPLETE);
	paypalService.doCapture(params);
	final CaptureResult result = params.getConvertedResult();

	LOG.info("result: " + params.getResponse().getAck().toString());


	return result;
}

}
