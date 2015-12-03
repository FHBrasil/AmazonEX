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
			logXml(LOG, req);
			CloseAuthorizationResponse closeAuthorization = getOffAmazonPaymentsService().closeAuthorization(req);
			logXml(LOG, closeAuthorization);
			final CloseAuthorizationResult result = closeAuthorization.getCloseAuthorizationResult();

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
