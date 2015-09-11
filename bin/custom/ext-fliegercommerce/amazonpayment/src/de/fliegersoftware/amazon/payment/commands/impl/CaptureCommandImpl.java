package de.fliegersoftware.amazon.payment.commands.impl;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.CaptureRequest;
import com.amazonservices.mws.offamazonpayments.model.CaptureResponse;
import com.amazonservices.mws.offamazonpayments.model.CaptureResult;

import de.fliegersoftware.amazon.payment.commands.CaptureCommand;

/**
 * @author taylor.savegnago
 * 
 */
@Component("captureCommand")
public class CaptureCommandImpl extends AbstractCommandImpl implements CaptureCommand {

	private final static Logger LOG = Logger.getLogger(CaptureCommandImpl.class);


	@Override
	public CaptureResult perform(final CaptureRequest req) {
		try {
			LOG.info("-----------------------------------------------------");
			LOG.info("CaptureCommandImpl perform requested");
			LOG.info("-----------------------------------------------------");
			
			req.setSellerId(getSellerId());
			CaptureResponse capture = getOffAmazonPaymentsService().capture(req);
			final CaptureResult result = capture.getCaptureResult();

			return result;

		} catch (OffAmazonPaymentsServiceException ex) {
			System.out.println("Caught Exception: " + ex.getMessage());
			System.out.println("Response Status Code: " + ex.getStatusCode());
			System.out.println("Error Code: " + ex.getErrorCode());
			System.out.println("Error Type: " + ex.getErrorType());
			System.out.println("Request ID: " + ex.getRequestId());
			System.out.println("XML: " + ex.getXML());
			System.out.println("ResponseHeaderMetadata: " + ex.getResponseHeaderMetadata());
			return null;
		}
	}
}
