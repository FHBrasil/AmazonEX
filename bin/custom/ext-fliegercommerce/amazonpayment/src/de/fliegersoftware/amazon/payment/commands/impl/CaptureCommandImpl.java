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
			logXml(LOG, req);
			CaptureResponse capture = getOffAmazonPaymentsService().capture(req);
			logXml(LOG, capture);
			final CaptureResult result = capture.getCaptureResult();

			return result;

		} catch (OffAmazonPaymentsServiceException ex) {
			LOG.error("Caught Exception: " + ex.getMessage());
			LOG.error("Response Status Code: " + ex.getStatusCode());
			LOG.error("Error Code: " + ex.getErrorCode());
			LOG.error("Error Type: " + ex.getErrorType());
			LOG.error("Request ID: " + ex.getRequestId());
			LOG.error("XML: " + ex.getXML());
			LOG.error("ResponseHeaderMetadata: " + ex.getResponseHeaderMetadata());
			return null;
		}
	}
}
