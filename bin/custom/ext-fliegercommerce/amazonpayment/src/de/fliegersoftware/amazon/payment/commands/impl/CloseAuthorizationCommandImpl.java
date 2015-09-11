package de.fliegersoftware.amazon.payment.commands.impl;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.CloseAuthorizationRequest;
import com.amazonservices.mws.offamazonpayments.model.CloseAuthorizationResponse;
import com.amazonservices.mws.offamazonpayments.model.CloseAuthorizationResult;

import de.fliegersoftware.amazon.payment.commands.CloseAuthorizationCommand;

/**
 * @author taylor.savegnago
 * 
 */
@Component("closeAuthorizationCommand")
public class CloseAuthorizationCommandImpl extends AbstractCommandImpl implements CloseAuthorizationCommand {

	private final static Logger LOG = Logger.getLogger(CloseAuthorizationCommandImpl.class);


	@Override
	public CloseAuthorizationResult perform(final CloseAuthorizationRequest req) {
		try {
			LOG.info("-----------------------------------------------------");
			LOG.info("CloseAuthorizationCommandImpl perform requested");
			LOG.info("-----------------------------------------------------");

			req.setSellerId(getSellerId());
			CloseAuthorizationResponse closeAuthorization = getOffAmazonPaymentsService().closeAuthorization(req);
			final CloseAuthorizationResult result = closeAuthorization.getCloseAuthorizationResult();

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
