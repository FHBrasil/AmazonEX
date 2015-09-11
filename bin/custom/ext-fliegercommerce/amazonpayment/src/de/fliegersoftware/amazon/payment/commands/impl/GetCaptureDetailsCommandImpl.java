/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands.impl;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsResponse;
import com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsResult;

import de.fliegersoftware.amazon.payment.commands.GetCaptureDetailsCommand;

/**
 * @author taylor.savegnago
 * 
 */
@Component("getCaptureDetailsCommand")
public class GetCaptureDetailsCommandImpl extends AbstractCommandImpl implements GetCaptureDetailsCommand {

private final static Logger LOG = Logger.getLogger(GetCaptureDetailsCommandImpl.class);


@Override
public GetCaptureDetailsResult perform(final GetCaptureDetailsRequest req) {
	
	try {
   	LOG.info("-----------------------------------------------------");
   	LOG.info("GetCaptureDetailsCommandImpl perform requested");
   	LOG.info("-----------------------------------------------------");
   	req.setSellerId(getSellerId());
   	GetCaptureDetailsResponse captureDetail = getOffAmazonPaymentsService().getCaptureDetails(req);
   	final GetCaptureDetailsResult result = captureDetail.getGetCaptureDetailsResult();
   	
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
