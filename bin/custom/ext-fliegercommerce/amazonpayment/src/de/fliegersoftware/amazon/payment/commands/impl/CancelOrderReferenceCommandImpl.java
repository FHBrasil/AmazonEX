package de.fliegersoftware.amazon.payment.commands.impl;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceResponse;
import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceResult;

import de.fliegersoftware.amazon.payment.commands.CancelOrderReferenceCommand;

/**
 * @author taylor.savegnago
 * 
 */
@Component("cancelOrderReferenceCommand")
public class CancelOrderReferenceCommandImpl extends AbstractCommandImpl implements CancelOrderReferenceCommand {

	private final static Logger LOG = Logger.getLogger(CancelOrderReferenceCommandImpl.class);


	@Override
	public CancelOrderReferenceResult perform(final CancelOrderReferenceRequest req) {
		try {
			LOG.info("-----------------------------------------------------");
			LOG.info("CancelOrderReferenceCommandImpl perform requested");
			LOG.info("-----------------------------------------------------");

			req.setSellerId(getSellerId());
			logXml(LOG, req);
			CancelOrderReferenceResponse cancel = getOffAmazonPaymentsService().cancelOrderReference(req);
			logXml(LOG, cancel);
			final CancelOrderReferenceResult result = cancel.getCancelOrderReferenceResult();

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
