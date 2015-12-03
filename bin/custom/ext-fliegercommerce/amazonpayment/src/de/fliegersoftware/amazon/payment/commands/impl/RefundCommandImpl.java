package de.fliegersoftware.amazon.payment.commands.impl;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.RefundRequest;
import com.amazonservices.mws.offamazonpayments.model.RefundResponse;
import com.amazonservices.mws.offamazonpayments.model.RefundResult;

import de.fliegersoftware.amazon.payment.commands.RefundCommand;

/**
 * @author taylor.savegnago
 * 
 */
@Component("refundCommand")
public class RefundCommandImpl extends AbstractCommandImpl implements RefundCommand {

	private final static Logger LOG = Logger.getLogger(RefundCommandImpl.class);


	@Override
	public RefundResult perform(final RefundRequest req) {
		try {
			LOG.info("-----------------------------------------------------");
			LOG.info("RefundCommandImpl perform requested");
			LOG.info("-----------------------------------------------------");
			
			req.setSellerId(getSellerId());
			logXml(LOG, req);
			RefundResponse refund = getOffAmazonPaymentsService().refund(req);
			logXml(LOG, refund);
			final RefundResult result = refund.getRefundResult();

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
