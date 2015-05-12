package com.paypal.hybris.commands.impl;


import de.hybris.platform.payment.commands.PartialCaptureCommand;
import de.hybris.platform.payment.commands.request.PartialCaptureRequest;
import de.hybris.platform.payment.commands.result.CaptureResult;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.paypal.hybris.soap.gen.CompleteCodeType;
import com.paypal.hybris.soap.params.impl.DoCaptureParams;


/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
@Component("partialCaptureCommand")
public class PartialCaptureCommandImpl extends AbstractCommandImpl implements
		PartialCaptureCommand {

private final static Logger LOG = Logger
		.getLogger(PartialCaptureCommandImpl.class);


/**
 * The same as Capture, but with CompleteType = NOT_COMPLETED
 * 
 * @see de.hybris.platform.payment.commands.PartialCaptureCommand
 */
@Override
public CaptureResult perform(final PartialCaptureRequest req) {

	LOG.info("-----------------------------------------------------");
	LOG.info("PartialCaptureCommandImpl perform requested");
	LOG.info("-----------------------------------------------------");

	final DoCaptureParams params = new DoCaptureParams();
	params.setParamsFromRequest(req);
	params.setCompleteType(CompleteCodeType.NOT_COMPLETE);
	paypalService.doCapture(params);
	final CaptureResult result = params.getConvertedResult();

	return result;
}

}
