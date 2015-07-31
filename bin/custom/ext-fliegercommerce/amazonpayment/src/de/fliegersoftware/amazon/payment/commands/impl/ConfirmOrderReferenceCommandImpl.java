package de.fliegersoftware.amazon.payment.commands.impl;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.ConfirmOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.ConfirmOrderReferenceResponse;

/**
 * @author taylor.savegnago
 * 
 */
@Component("confirmOrderReferenceCommand")
public class ConfirmOrderReferenceCommandImpl extends AbstractCommandImpl {

	private final static Logger LOG = Logger.getLogger(ConfirmOrderReferenceCommandImpl.class);


	public void perform(final ConfirmOrderReferenceRequest req) {
		try {
			LOG.info("-----------------------------------------------------");
			LOG.info("ConfirmOrderReferenceCommandImpl perform requested");
			LOG.info("-----------------------------------------------------");

			ConfirmOrderReferenceResponse confirm = offAmazonPaymentsService.confirmOrderReference(req);

		} catch (OffAmazonPaymentsServiceException ex) {
			System.out.println("Caught Exception: " + ex.getMessage());
			System.out.println("Response Status Code: " + ex.getStatusCode());
			System.out.println("Error Code: " + ex.getErrorCode());
			System.out.println("Error Type: " + ex.getErrorType());
			System.out.println("Request ID: " + ex.getRequestId());
			System.out.println("XML: " + ex.getXML());
			System.out.println("ResponseHeaderMetadata: " + ex.getResponseHeaderMetadata());
		}
	}

}
