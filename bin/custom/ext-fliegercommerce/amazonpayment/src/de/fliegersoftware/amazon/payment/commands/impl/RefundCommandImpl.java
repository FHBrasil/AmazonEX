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
			RefundResponse refund = getOffAmazonPaymentsService().refund(req);
			final RefundResult result = refund.getRefundResult();

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
