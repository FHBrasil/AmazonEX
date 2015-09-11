package de.fliegersoftware.amazon.payment.commands.impl;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceResponse;
import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceResult;

import de.fliegersoftware.amazon.payment.commands.CancelCommand;

/**
 * @author taylor.savegnago
 * 
 */
@Component("cancelCommand")
public class CancelCommandImpl extends AbstractCommandImpl implements CancelCommand {

	private final static Logger LOG = Logger.getLogger(CancelCommandImpl.class);


	@Override
	public CancelOrderReferenceResult perform(final CancelOrderReferenceRequest req) {
		try {
			LOG.info("-----------------------------------------------------");
			LOG.info("CancelCommandImpl perform requested");
			LOG.info("-----------------------------------------------------");

			req.setSellerId(getSellerId());
			CancelOrderReferenceResponse cancel = getOffAmazonPaymentsService().cancelOrderReference(req);
			final CancelOrderReferenceResult result = cancel.getCancelOrderReferenceResult();

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
