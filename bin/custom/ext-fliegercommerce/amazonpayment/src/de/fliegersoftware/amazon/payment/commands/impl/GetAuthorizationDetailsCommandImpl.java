/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands.impl;


import org.apache.log4j.Logger;
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

private final static Logger LOG = Logger.getLogger(GetAuthorizationDetailsCommandImpl.class);


@Override
public GetAuthorizationDetailsResult perform(final GetAuthorizationDetailsRequest req) {
	
	try {
   	LOG.info("-----------------------------------------------------");
   	LOG.info("GetAuthorizationDetailsCommandImpl perform requested");
   	LOG.info("-----------------------------------------------------");
   	req.setSellerId(getSellerId());
   	GetAuthorizationDetailsResponse authorizationDetail = offAmazonPaymentsService.getAuthorizationDetails(req);
   	final GetAuthorizationDetailsResult result = authorizationDetail.getGetAuthorizationDetailsResult();
   	
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
