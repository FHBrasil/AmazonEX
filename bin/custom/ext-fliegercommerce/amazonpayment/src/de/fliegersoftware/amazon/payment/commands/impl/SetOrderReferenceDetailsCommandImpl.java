/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands.impl;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.SetOrderReferenceDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.SetOrderReferenceDetailsResponse;
import com.amazonservices.mws.offamazonpayments.model.SetOrderReferenceDetailsResult;

import de.fliegersoftware.amazon.payment.commands.SetOrderReferenceDetailsCommand;

/**
 * @author taylor.savegnago
 * 
 */
@Component("setOrderReferenceDetailsCommand")
public class SetOrderReferenceDetailsCommandImpl extends AbstractCommandImpl implements SetOrderReferenceDetailsCommand {

private final static Logger LOG = Logger.getLogger(SetOrderReferenceDetailsCommandImpl.class);


@Override
public SetOrderReferenceDetailsResult perform(final SetOrderReferenceDetailsRequest req) {
	
	try {
   	LOG.info("-----------------------------------------------------");
   	LOG.info("SetOrderReferenceDetailsCommandImpl perform requested");
   	LOG.info("-----------------------------------------------------");
   	req.setSellerId(getSellerId());
	logXml(LOG, req);
   	SetOrderReferenceDetailsResponse orderReferenceDetail = getOffAmazonPaymentsService().setOrderReferenceDetails(req);
	logXml(LOG, orderReferenceDetail);
   	final SetOrderReferenceDetailsResult result = orderReferenceDetail.getSetOrderReferenceDetailsResult();
   	
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
