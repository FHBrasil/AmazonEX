package de.fliegersoftware.amazon.payment.commands.impl;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceResponse;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceResult;

import de.fliegersoftware.amazon.payment.commands.CloseOrderReferenceCommand;

/**
 * @author taylor.savegnago
 * 
 */
@Component("closeOrderReferenceCommand")
public class CloseOrderReferenceCommandImpl extends AbstractCommandImpl implements CloseOrderReferenceCommand {

	private final static Logger LOG = Logger.getLogger(CloseOrderReferenceCommandImpl.class);


	@Override
	public CloseOrderReferenceResult perform(final CloseOrderReferenceRequest req) {
		try {
			LOG.info("-----------------------------------------------------");
			LOG.info("CloseOrderReferenceCommandImpl perform requested");
			LOG.info("-----------------------------------------------------");

			req.setSellerId(getSellerId());
			logXml(LOG, req);
			CloseOrderReferenceResponse closeOrderReference = getOffAmazonPaymentsService().closeOrderReference(req);
			logXml(LOG, closeOrderReference);
			final CloseOrderReferenceResult result = closeOrderReference.getCloseOrderReferenceResult();

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
