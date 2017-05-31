package de.fliegersoftware.amazon.payment.commands.impl;


import org.slf4j.Logger;  import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.ConfirmOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.ConfirmOrderReferenceResponse;

import de.fliegersoftware.amazon.payment.commands.ConfirmOrderReferenceCommand;
import de.hybris.platform.payment.commands.result.AbstractResult;

/**
 * @author taylor.savegnago
 * 
 */
@Component("confirmOrderReferenceCommand")
public class ConfirmOrderReferenceCommandImpl extends AbstractCommandImpl implements ConfirmOrderReferenceCommand {

	private final static Logger LOG = LoggerFactory.getLogger(ConfirmOrderReferenceCommandImpl.class);


	public AbstractResult perform(final ConfirmOrderReferenceRequest req) {
		try {
			LOG.info("-----------------------------------------------------");
			LOG.info("ConfirmOrderReferenceCommandImpl perform requested");
			LOG.info("-----------------------------------------------------");
			
			req.setSellerId(getSellerId());
			logXml(LOG, req);
			ConfirmOrderReferenceResponse response = getOffAmazonPaymentsService().confirmOrderReference(req);
			logXml(LOG, response);
			
		} catch (OffAmazonPaymentsServiceException ex) {
			LOG.error("Caught Exception: " + ex.getMessage());
			LOG.error("Response Status Code: " + ex.getStatusCode());
			LOG.error("Error Code: " + ex.getErrorCode());
			LOG.error("Error Type: " + ex.getErrorType());
			LOG.error("Request ID: " + ex.getRequestId());
			LOG.error("XML: " + ex.getXML());
			LOG.error("ResponseHeaderMetadata: " + ex.getResponseHeaderMetadata());
		}
		
		return null;
	}

}
