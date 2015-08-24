/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands.impl;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsResponse;
import com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsResult;

import de.fliegersoftware.amazon.payment.commands.GetRefundDetailsCommand;

/**
 * @author taylor.savegnago
 * 
 */
@Component("getRefundDetailsCommand")
public class GetRefundDetailsCommandImpl extends AbstractCommandImpl implements GetRefundDetailsCommand {

private final static Logger LOG = Logger.getLogger(GetRefundDetailsCommandImpl.class);


@Override
public GetRefundDetailsResult perform(final GetRefundDetailsRequest req) {
	
	try {
   	LOG.info("-----------------------------------------------------");
   	LOG.info("GetRefundDetailsCommandImpl perform requested");
   	LOG.info("-----------------------------------------------------");
   	req.setSellerId(getSellerId());
   	GetRefundDetailsResponse refundDetail = getOffAmazonPaymentsService().getRefundDetails(req);
   	final GetRefundDetailsResult result = refundDetail.getGetRefundDetailsResult();
   	
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
