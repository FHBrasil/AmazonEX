/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands.impl;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.AuthorizeRequest;
import com.amazonservices.mws.offamazonpayments.model.AuthorizeResponse;
import com.amazonservices.mws.offamazonpayments.model.AuthorizeResult;

import de.fliegersoftware.amazon.payment.commands.AuthorizeCommand;
import de.hybris.platform.util.Config;

/**
 * @author taylor.savegnago
 * 
 */
@Component("authorizationCommand")
public class AuthorizeCommandImpl extends AbstractCommandImpl implements AuthorizeCommand {

private final static Logger LOG = Logger.getLogger(AuthorizeCommandImpl.class);


@Override
public AuthorizeResult perform(final AuthorizeRequest req) {
	
	try {
   	LOG.info("-----------------------------------------------------");
   	LOG.info("AuthorizationCommandImpl perform requested");
   	LOG.info("-----------------------------------------------------");
   	req.setSellerId(getSellerId());
   	req.setTransactionTimeout(Integer.valueOf(Config.getParameter("amazonpaymentaddon.authorization.timeout")));
   	AuthorizeResponse authorize = offAmazonPaymentsService.authorize(req);
   	final AuthorizeResult result = authorize.getAuthorizeResult();
   	
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
