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
   	SetOrderReferenceDetailsResponse orderReferenceDetail = getOffAmazonPaymentsService().setOrderReferenceDetails(req);
   	final SetOrderReferenceDetailsResult result = orderReferenceDetail.getSetOrderReferenceDetailsResult();
   	
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
