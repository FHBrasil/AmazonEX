/**
 * 
 */
package de.fliegersoftware.amazon.payment.services;

import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.PaymentService;

import com.amazonservices.mws.offamazonpayments.model.AuthorizeRequest;
import com.amazonservices.mws.offamazonpayments.model.AuthorizeResult;
import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceResult;
import com.amazonservices.mws.offamazonpayments.model.CaptureRequest;
import com.amazonservices.mws.offamazonpayments.model.CaptureResult;
import com.amazonservices.mws.offamazonpayments.model.CloseAuthorizationRequest;
import com.amazonservices.mws.offamazonpayments.model.CloseAuthorizationResult;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceResult;
import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsResult;
import com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsResult;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsResult;
import com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsResult;
import com.amazonservices.mws.offamazonpayments.model.RefundRequest;
import com.amazonservices.mws.offamazonpayments.model.RefundResult;
import com.amazonservices.mws.offamazonpayments.model.SetOrderReferenceDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.SetOrderReferenceDetailsResult;

/**
 * @author taylor.savegnago
 *
 */
public interface MWSAmazonPaymentService extends PaymentService
{
	
	public AuthorizeResult authorize(AuthorizeRequest request) throws AdapterException;
	
	public CancelOrderReferenceResult cancelOrderReference(CancelOrderReferenceRequest request) throws AdapterException;
	
	public CaptureResult capture(CaptureRequest request) throws AdapterException;
	
	public CloseAuthorizationResult closeAuthorization(CloseAuthorizationRequest request) throws AdapterException;
	
	public CloseOrderReferenceResult closeOrderReference(CloseOrderReferenceRequest request) throws AdapterException;
	
	public GetAuthorizationDetailsResult getAuthorizationDetails(GetAuthorizationDetailsRequest request) throws AdapterException;
	
	public GetCaptureDetailsResult getCaptureDetails(GetCaptureDetailsRequest request) throws AdapterException;
	
	public GetOrderReferenceDetailsResult getOrderReferenceDetails(GetOrderReferenceDetailsRequest request) throws AdapterException;
	
	public GetRefundDetailsResult getRefundDetails(GetRefundDetailsRequest request) throws AdapterException;
	
	public RefundResult refund(RefundRequest request) throws AdapterException;
	
	public SetOrderReferenceDetailsResult setOrderReferenceDetails(SetOrderReferenceDetailsRequest request) throws AdapterException;
	
}
