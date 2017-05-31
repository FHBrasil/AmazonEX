/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands.impl;


import org.slf4j.Logger;  import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsResponse;
import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsResult;

import de.fliegersoftware.amazon.payment.commands.GetAuthorizationDetailsCommand;

/**
 * @author taylor.savegnago
 * 
 */
@Component("getAuthorizationDetailsCommand")
public class GetAuthorizationDetailsCommandImpl extends AbstractCommandImpl implements GetAuthorizationDetailsCommand {

private final static Logger LOG = LoggerFactory.getLogger(GetAuthorizationDetailsCommandImpl.class);


@Override
public GetAuthorizationDetailsResult perform(final GetAuthorizationDetailsRequest req) {
	
	try {
   	LOG.info("-----------------------------------------------------");
   	LOG.info("GetAuthorizationDetailsCommandImpl perform requested");
   	LOG.info("-----------------------------------------------------");
   	req.setSellerId(getSellerId());
	logXml(LOG, req);
   	GetAuthorizationDetailsResponse authorizationDetail = getOffAmazonPaymentsService().getAuthorizationDetails(req);
	logXml(LOG, authorizationDetail);
   	final GetAuthorizationDetailsResult result = authorizationDetail.getGetAuthorizationDetailsResult();
   	
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
