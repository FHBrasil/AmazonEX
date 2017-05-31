/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands.impl;


import org.slf4j.Logger;  import org.slf4j.LoggerFactory;
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

private final static Logger LOG = LoggerFactory.getLogger(GetCaptureDetailsCommandImpl.class);


@Override
public GetCaptureDetailsResult perform(final GetCaptureDetailsRequest req) {
	
	try {
   	LOG.info("-----------------------------------------------------");
   	LOG.info("GetCaptureDetailsCommandImpl perform requested");
   	LOG.info("-----------------------------------------------------");
   	req.setSellerId(getSellerId());
	logXml(LOG, req);
   	GetCaptureDetailsResponse captureDetail = getOffAmazonPaymentsService().getCaptureDetails(req);
	logXml(LOG, captureDetail);
   	final GetCaptureDetailsResult result = captureDetail.getGetCaptureDetailsResult();
   	
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
