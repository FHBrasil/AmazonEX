/**
 * 
 */
package de.fliegersoftware.amazon.payment.services;

import de.fliegersoftware.amazon.core.data.AmazonOrderReferenceDetailsData;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.PaymentService;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

import java.math.BigDecimal;
import java.util.Currency;

import org.apache.commons.lang.StringUtils;

import com.amazonservices.mws.offamazonpayments.model.AuthorizationDetails;
import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceResult;
import com.amazonservices.mws.offamazonpayments.model.CaptureDetails;
import com.amazonservices.mws.offamazonpayments.model.CloseAuthorizationRequest;
import com.amazonservices.mws.offamazonpayments.model.CloseAuthorizationResult;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceResult;
import com.amazonservices.mws.offamazonpayments.model.ConfirmOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsResult;
import com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsResult;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsResult;
import com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsResult;
import com.amazonservices.mws.offamazonpayments.model.OrderReferenceAttributes;
import com.amazonservices.mws.offamazonpayments.model.OrderReferenceDetails;
import com.amazonservices.mws.offamazonpayments.model.RefundDetails;
import com.amazonservices.mws.offamazonpayments.model.RefundRequest;
import com.amazonservices.mws.offamazonpayments.model.RefundResult;
import com.amazonservices.mws.offamazonpayments.model.SetOrderReferenceDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.SetOrderReferenceDetailsResult;

/**
 * @author taylor.savegnago 
 */
public interface AmazonPaymentService extends PaymentService
{
	PaymentTransactionEntryModel authorize(final String merchantTransactionCode, 
			final BigDecimal amount, 
			final Currency currency,
			final String paymentProvider,
			final String merchantAccount) throws AdapterException;
	
	public PaymentTransactionEntryModel capture(PaymentTransactionModel transaction) throws AdapterException;
	
	public AmazonOrderReferenceDetailsData getOrderReferenceDetails(final String amazonOrderReferenceId, final String addressConsentToken) throws AdapterException;

	public CaptureDetails getCaptureDetails(String amazonCaptureId) throws AdapterException;

	public AuthorizationDetails getAuthorizationDetails(String amazonAuthorizationId) throws AdapterException;
	
	public RefundDetails getRefundDetails(String amazonRefundId) throws AdapterException;
	
	public OrderReferenceDetails setOrderReferenceDetails(String amazonOrderReferenceId, OrderReferenceAttributes orderReferenceAttributes) throws AdapterException;
	
	public void confirmOrder(String amazonOrderReferenceId) throws AdapterException;
	
	public CancelOrderReferenceResult cancelOrder(final String amazonOrderReferenceId, final String cancelationReason) throws AdapterException;
	
	public CloseOrderReferenceResult closeOrderReference(final String amazonOrderReferenceId, final String closureReason) throws AdapterException;
	
	public CloseAuthorizationResult closeAuthorization(final String amazonAuthorizationId, final String closureReason) throws AdapterException;
	
	public RefundResult refund(final String amazonCaptureId, final String refundReferenceId, BigDecimal amount,
			Currency currency, String sellerRefundNote, String softDescriptor);
}