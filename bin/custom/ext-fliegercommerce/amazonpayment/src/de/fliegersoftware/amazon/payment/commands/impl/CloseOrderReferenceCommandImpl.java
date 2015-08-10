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
			CloseOrderReferenceResponse closeOrderReference = offAmazonPaymentsService.closeOrderReference(req);
			final CloseOrderReferenceResult result = closeOrderReference.getCloseOrderReferenceResult();

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
