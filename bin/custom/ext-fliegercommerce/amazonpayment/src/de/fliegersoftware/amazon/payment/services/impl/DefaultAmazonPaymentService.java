package de.fliegersoftware.amazon.payment.services.impl;

import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.dto.BillingInfo;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.impl.DefaultPaymentServiceImpl;
import de.hybris.platform.payment.methods.CardPaymentService;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.AuthorizationDetails;
import com.amazonservices.mws.offamazonpayments.model.AuthorizeRequest;
import com.amazonservices.mws.offamazonpayments.model.AuthorizeResult;
import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceResponse;
import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceResult;
import com.amazonservices.mws.offamazonpayments.model.CaptureDetails;
import com.amazonservices.mws.offamazonpayments.model.CaptureRequest;
import com.amazonservices.mws.offamazonpayments.model.CaptureResult;
import com.amazonservices.mws.offamazonpayments.model.CloseAuthorizationRequest;
import com.amazonservices.mws.offamazonpayments.model.CloseAuthorizationResult;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceResponse;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceResult;
import com.amazonservices.mws.offamazonpayments.model.ConfirmOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsResponse;
import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsResult;
import com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsResponse;
import com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsResult;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsResponse;
import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsResult;
import com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsResponse;
import com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsResult;
import com.amazonservices.mws.offamazonpayments.model.OrderReferenceAttributes;
import com.amazonservices.mws.offamazonpayments.model.OrderReferenceDetails;
import com.amazonservices.mws.offamazonpayments.model.OrderTotal;
import com.amazonservices.mws.offamazonpayments.model.Price;
import com.amazonservices.mws.offamazonpayments.model.RefundDetails;
import com.amazonservices.mws.offamazonpayments.model.RefundRequest;
import com.amazonservices.mws.offamazonpayments.model.RefundResult;
import com.amazonservices.mws.offamazonpayments.model.SellerOrderAttributes;
import com.amazonservices.mws.offamazonpayments.model.SetOrderReferenceDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.SetOrderReferenceDetailsResponse;
import com.amazonservices.mws.offamazonpayments.model.SetOrderReferenceDetailsResult;

import de.fliegersoftware.amazon.payment.exception.AmazonException;
import de.fliegersoftware.amazon.payment.services.AmazonPaymentService;
import de.fliegersoftware.amazon.payment.services.MWSAmazonPaymentService;

/**
 * @author taylor.savegnago
 */
public class DefaultAmazonPaymentService extends DefaultPaymentServiceImpl implements AmazonPaymentService
{	
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(DefaultAmazonPaymentService.class.getName());
	
	@Resource
	private ModelService modelService;
	
	@Resource
	private CommonI18NService commonI18NService;
	private CartService cartService;
	private SessionService sessionService;
	
	@Resource
	private MWSAmazonPaymentService mwsAmazonPaymentService;
	
	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Override
	public void setModelService(ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the commonI18NService
	 */
	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	/**
	 * @param commonI18NService
	 *           the commonI18NService to set
	 */
	@Override
	public void setCommonI18NService(CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	/**
	 * @return the cartService
	 */
	public CartService getCartService()
	{
		return cartService;
	}

	/**
	 * @param cartService
	 *           the cartService to set
	 */
	public void setCartService(CartService cartService)
	{
		this.cartService = cartService;
	}

	/* (non-Javadoc)
	 * @see de.hybris.platform.payment.impl.DefaultPaymentServiceImpl#getCardPaymentService()
	 */
	@Override
	public CardPaymentService getCardPaymentService()
	{
		return super.getCardPaymentService();
	}

	/**
	 * @return the sessionService
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * @param sessionService
	 *           the sessionService to set
	 */
	public void setSessionService(SessionService sessionService)
	{
		this.sessionService = sessionService;
	}
	
	/**
	 * @return the mwsAmazonPaymentService
	 */
	public MWSAmazonPaymentService getMwsAmazonPaymentService()
	{
		return mwsAmazonPaymentService;
	}

	/**
	 * @param mwsAmazonPaymentService the mwsAmazonPaymentService to set
	 */
	public void setMwsAmazonPaymentService(MWSAmazonPaymentService mwsAmazonPaymentService)
	{
		this.mwsAmazonPaymentService = mwsAmazonPaymentService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.payment.impl.DefaultPaymentServiceImpl#authorize(java.lang.String, java.math.BigDecimal,
	 * java.util.Currency, de.hybris.platform.core.model.user.AddressModel, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public PaymentTransactionEntryModel authorize(
			final String merchantTransactionCode, 
			final BigDecimal amount, Currency currency,
			final String subscriptionID, 
			final String paymentProvider) throws AdapterException
	{
		PaymentTransactionModel transaction = (PaymentTransactionModel) modelService.create(PaymentTransactionModel.class);
		transaction.setCode(merchantTransactionCode);

		return authorizeInternal(transaction, amount, currency, subscriptionID, paymentProvider);
	}

	private PaymentTransactionEntryModel authorizeInternal(
			PaymentTransactionModel transaction, BigDecimal amount,
			Currency currency,
			String subscriptionID, String paymentProvider)
			throws AdapterException {
		String newEntryCode = getNewEntryCode(transaction);
		
		AuthorizeRequest authorizeRequest = new AuthorizeRequest();
		authorizeRequest.setAuthorizationAmount(getAmount(String.valueOf(amount), currency.getCurrencyCode()));
		authorizeRequest.setAmazonOrderReferenceId(subscriptionID);
		AuthorizeResult result = this.mwsAmazonPaymentService.authorize(authorizeRequest);
		
		transaction.setRequestId(result.getAuthorizationDetails().getAuthorizationReferenceId());
		transaction.setRequestToken(result.getAuthorizationDetails().getAmazonAuthorizationId());
		transaction.setPaymentProvider(paymentProvider);
		this.modelService.save(transaction);

		PaymentTransactionEntryModel entry = (PaymentTransactionEntryModel) this.modelService
				.create(PaymentTransactionEntryModel.class);
		entry.setAmount(BigDecimal.valueOf(Double.parseDouble(result.getAuthorizationDetails().getAuthorizationAmount().getAmount())));
		if (result.getAuthorizationDetails().getAuthorizationAmount() != null) {
			entry.setCurrency(this.commonI18NService.getCurrency(result.getAuthorizationDetails().getAuthorizationAmount().getCurrencyCode()));
		}
		entry.setType(PaymentTransactionType.AUTHORIZATION);
		entry.setTime((result.getAuthorizationDetails().getAuthorizationStatus().getLastUpdateTimestamp() == null) ? new Date()
				: result.getAuthorizationDetails().getAuthorizationStatus().getLastUpdateTimestamp().toGregorianCalendar().getTime());
		entry.setPaymentTransaction(transaction);
		entry.setRequestId(result.getAuthorizationDetails().getAuthorizationReferenceId());
		entry.setRequestToken(result.getAuthorizationDetails().getAmazonAuthorizationId());
		entry.setTransactionStatus(result.getAuthorizationDetails().getAuthorizationStatus().getState());
		entry.setTransactionStatusDetails(result.getAuthorizationDetails().getAuthorizationStatus().getReasonDescription());
		entry.setCode(newEntryCode);
		if (subscriptionID != null) {
			entry.setSubscriptionID(subscriptionID);
		}
		this.modelService.save(entry);
		this.modelService.refresh(transaction);
		return entry;
	}
	
	@Override
	public PaymentTransactionEntryModel capture(PaymentTransactionModel transaction) throws AdapterException {
		PaymentTransactionEntryModel auth = null;
		for (PaymentTransactionEntryModel pte : transaction.getEntries()) {
			if (!(pte.getType().equals(PaymentTransactionType.AUTHORIZATION)))
				continue;
			auth = pte;
			break;
		}

		if (auth == null) {
			throw new AdapterException(
					"Could not capture without authorization");
		}
		String newEntryCode = getNewEntryCode(transaction);
		
		CaptureRequest captureRequest = new CaptureRequest();
		captureRequest.setAmazonAuthorizationId(transaction.getRequestToken());
		captureRequest.setCaptureReferenceId(transaction.getRequestId());
		
		captureRequest.setCaptureAmount(getAmount(String.valueOf(auth.getAmount()), auth.getCurrency().getIsocode()));
		
		CaptureResult result = this.mwsAmazonPaymentService.capture(captureRequest);

		PaymentTransactionEntryModel entry = (PaymentTransactionEntryModel) this.modelService.create(PaymentTransactionEntryModel.class);
		entry.setAmount(BigDecimal.valueOf(Double.parseDouble(result.getCaptureDetails().getCaptureAmount().getAmount())));
		if (result.getCaptureDetails().getCaptureAmount() != null) {
			entry.setCurrency(this.commonI18NService.getCurrency(result.getCaptureDetails().getCaptureAmount().getCurrencyCode()));
		}
		entry.setType(PaymentTransactionType.CAPTURE);
		entry.setTime((result.getCaptureDetails().getCaptureStatus().getLastUpdateTimestamp() == null) ? new Date()
				: result.getCaptureDetails().getCaptureStatus().getLastUpdateTimestamp().toGregorianCalendar().getTime());
		entry.setPaymentTransaction(transaction);
		entry.setRequestId(result.getCaptureDetails().getCaptureReferenceId());
		entry.setRequestToken(result.getCaptureDetails().getAmazonCaptureId());
		entry.setTransactionStatus(result.getCaptureDetails().getCaptureStatus().getState());
		entry.setTransactionStatusDetails(result.getCaptureDetails().getCaptureStatus().getReasonDescription());
		entry.setCode(newEntryCode);

		this.modelService.save(entry);
		return entry;
	}
	
	public OrderReferenceDetails getOrderReferenceDetails(final String amazonOrderReferenceId, final String addressConsentToken) throws AdapterException 
	{
		final GetOrderReferenceDetailsRequest request = new GetOrderReferenceDetailsRequest();
		request.setAmazonOrderReferenceId(amazonOrderReferenceId);

		if(StringUtils.isNotBlank(addressConsentToken))
		{
			request.setAddressConsentToken(addressConsentToken);
		}

		GetOrderReferenceDetailsResult result = mwsAmazonPaymentService.getOrderReferenceDetails(request);
		OrderReferenceDetails details = result.getOrderReferenceDetails();
		return details;
	}

	public CaptureDetails getCaptureDetails(String amazonCaptureId) throws AdapterException {
		GetCaptureDetailsRequest request = new GetCaptureDetailsRequest();
		request.setAmazonCaptureId(amazonCaptureId);
		GetCaptureDetailsResult result = mwsAmazonPaymentService.getCaptureDetails(request);
		return result.getCaptureDetails();
	}

	public AuthorizationDetails getAuthorizationDetails(String amazonAuthorizationId) throws AdapterException {
		GetAuthorizationDetailsRequest request = new GetAuthorizationDetailsRequest();
		request.setAmazonAuthorizationId(amazonAuthorizationId);
		GetAuthorizationDetailsResult result = mwsAmazonPaymentService.getAuthorizationDetails(request);
		return result.getAuthorizationDetails();
	}
	
	public RefundDetails getRefundDetails(String amazonRefundId) throws AdapterException {
		GetRefundDetailsRequest request = new GetRefundDetailsRequest();
		request.setAmazonRefundId(amazonRefundId);
		GetRefundDetailsResult result = mwsAmazonPaymentService.getRefundDetails(request);
		return result.getRefundDetails();
	}
	
	private OrderReferenceDetails setOrderReferenceDetails(String amazonOrderReferenceId, OrderReferenceAttributes orderReferenceAttributes) throws AdapterException {
			SetOrderReferenceDetailsRequest request = new SetOrderReferenceDetailsRequest();
			request.setOrderReferenceAttributes(orderReferenceAttributes);
			request.setAmazonOrderReferenceId(amazonOrderReferenceId);
			SetOrderReferenceDetailsResult result = mwsAmazonPaymentService.setOrderReferenceDetails(request);
			return result.getOrderReferenceDetails();
	}
	
	public void confirmOrder(String amazonOrderReferenceId) throws AdapterException {
		ConfirmOrderReferenceRequest request = new ConfirmOrderReferenceRequest();
		request.setAmazonOrderReferenceId(amazonOrderReferenceId);
		
		// TODO
//		mwsAmazonPaymentService.confirmOrderReference(request);
	}
	
	public CancelOrderReferenceResult cancelOrder(final String amazonOrderReferenceId, final String cancelationReason) throws AdapterException
	{
		final CancelOrderReferenceRequest request = new CancelOrderReferenceRequest();
		request.setCancelationReason(cancelationReason);
		request.setAmazonOrderReferenceId(amazonOrderReferenceId);

		return mwsAmazonPaymentService.cancelOrderReference(request);
	}
	
	public CloseOrderReferenceResult closeOrderReference(final String amazonOrderReferenceId, final String closureReason) throws AdapterException
	{
		CloseOrderReferenceRequest request = new CloseOrderReferenceRequest();
		request.setAmazonOrderReferenceId(amazonOrderReferenceId);
		request.setClosureReason(closureReason);
		CloseOrderReferenceResult result = mwsAmazonPaymentService.closeOrderReference(request);
		return result;
	}
	
	public CloseAuthorizationResult closeAuthorization(final String amazonAuthorizationId, final String closureReason) throws AdapterException
	{
		CloseAuthorizationRequest request = new CloseAuthorizationRequest();
		request.setAmazonAuthorizationId(amazonAuthorizationId);
		request.setClosureReason(closureReason);
		CloseAuthorizationResult result = mwsAmazonPaymentService.closeAuthorization(request);
		return result;
	}
	
	public RefundResult refund(final String amazonCaptureId, final String refundReferenceId, BigDecimal amount,
			Currency currency, String sellerRefundNote, String softDescriptor) {
		RefundRequest request = new RefundRequest();
		request.setAmazonCaptureId(amazonCaptureId);
		request.setRefundReferenceId(refundReferenceId);
		request.setRefundAmount(getAmount(String.valueOf(amount), currency.getCurrencyCode()));
		request.setSellerRefundNote(sellerRefundNote);
		request.setSoftDescriptor(softDescriptor);
		
		RefundResult result = mwsAmazonPaymentService.refund(request);
		return result;
	}
	
	private BillingInfo createBillingInfo(AddressModel address) {
		if (address == null) {
			return null;
		}

		BillingInfo billingInfo = new BillingInfo();

		billingInfo.setCity(address.getTown());
		if (address.getCountry() != null) {
			billingInfo.setCountry(address.getCountry().getIsocode());
		}
		billingInfo.setEmail(address.getEmail());
		billingInfo.setFirstName(address.getFirstname());
		billingInfo.setLastName(address.getLastname());
		billingInfo.setPhoneNumber(address.getPhone1());
		billingInfo.setPostalCode(address.getPostalcode());
		if (address.getRegion() != null) {
			billingInfo.setState(address.getRegion().getName());
		}
		billingInfo.setStreet1(address.getStreetname());
		billingInfo.setStreet2(address.getStreetnumber());

		return billingInfo;
	}
	
	private OrderReferenceAttributes getOrderReferenceAttributes(String orderCode, String amount, String currency) {
		OrderReferenceAttributes orderReferenceAttributes = new OrderReferenceAttributes();
		orderReferenceAttributes.setSellerOrderAttributes(getSellerOrderAttributes(orderCode));
		orderReferenceAttributes.setOrderTotal(getOrderTotal(amount, currency));
		return orderReferenceAttributes;
	}
	
	private OrderTotal getOrderTotal(String amount, String currency) {
		OrderTotal orderTotal = new OrderTotal();
		orderTotal.setAmount(amount);
		orderTotal.setCurrencyCode(currency);
		return orderTotal;
	}
	
	private Price getAmount(String amount, String currency) {
		Price authorizationAmount = new Price();
		authorizationAmount.setAmount(amount);
		authorizationAmount.setCurrencyCode(currency);
		return authorizationAmount;
	}
	
	private SellerOrderAttributes getSellerOrderAttributes(String code) {
		SellerOrderAttributes sellerOrderAttributes = new SellerOrderAttributes();
		sellerOrderAttributes.setSellerOrderId(code);
		return sellerOrderAttributes;
	}
	
}
