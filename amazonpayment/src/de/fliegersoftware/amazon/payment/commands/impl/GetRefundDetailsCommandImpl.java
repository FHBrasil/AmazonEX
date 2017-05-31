/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands.impl;


import org.slf4j.Logger;  import org.slf4j.LoggerFactory;
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

private final static Logger LOG = LoggerFactory.getLogger(GetRefundDetailsCommandImpl.class);


@Override
public GetRefundDetailsResult perform(final GetRefundDetailsRequest req) {
	
	try {
   	LOG.info("-----------------------------------------------------");
   	LOG.info("GetRefundDetailsCommandImpl perform requested");
   	LOG.info("-----------------------------------------------------");
   	req.setSellerId(getSellerId());
	logXml(LOG, req);
   	GetRefundDetailsResponse refundDetail = getOffAmazonPaymentsService().getRefundDetails(req);
	logXml(LOG, refundDetail);
   	final GetRefundDetailsResult result = refundDetail.getGetRefundDetailsResult();
   	
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
