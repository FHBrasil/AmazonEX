/**
 * 
 */
package de.fliegersoftware.amazon.payment.services;

import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.PaymentService;

import com.amazonservices.mws.offamazonpayments.model.AuthorizeRequest;
import com.amazonservices.mws.offamazonpayments.model.AuthorizeResult;
import com.amazonservices.mws.offamazonpayments.model.CaptureRequest;
import com.amazonservices.mws.offamazonpayments.model.CaptureResult;

/**
 * @author taylor.savegnago
 *
 */
public interface MWSAmazonPaymentService extends PaymentService
{
	
	public AuthorizeResult authorize(AuthorizeRequest request) throws AdapterException;
	
	public CaptureResult capture(CaptureRequest request) throws AdapterException;
	
}
