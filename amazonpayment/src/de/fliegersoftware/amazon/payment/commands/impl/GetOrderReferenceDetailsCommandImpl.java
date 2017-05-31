/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands.impl;


import org.slf4j.Logger;  import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsResponse;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsResult;

import de.fliegersoftware.amazon.payment.commands.GetOrderReferenceDetailsCommand;

/**
 * @author taylor.savegnago
 * 
 */
@Component("getOrderReferenceDetailsCommand")
public class GetOrderReferenceDetailsCommandImpl extends AbstractCommandImpl implements GetOrderReferenceDetailsCommand {

private final static Logger LOG = LoggerFactory.getLogger(GetOrderReferenceDetailsCommandImpl.class);


@Override
public GetOrderReferenceDetailsResult perform(final GetOrderReferenceDetailsRequest req) {
	
	try {
   	LOG.info("-----------------------------------------------------");
   	LOG.info("GetOrderReferenceDetailsCommandImpl perform requested");
   	LOG.info("-----------------------------------------------------");
   	req.setSellerId(getSellerId());
	logXml(LOG, req);
   	GetOrderReferenceDetailsResponse orderReferenceDetail = getOffAmazonPaymentsService().getOrderReferenceDetails(req);
	logXml(LOG, orderReferenceDetail);
   	final GetOrderReferenceDetailsResult result = orderReferenceDetail.getGetOrderReferenceDetailsResult();
   	
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
