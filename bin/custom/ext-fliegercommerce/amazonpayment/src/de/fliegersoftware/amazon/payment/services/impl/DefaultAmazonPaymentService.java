package de.fliegersoftware.amazon.payment.services.impl;

import static de.fliegersoftware.amazon.payment.util.PaymentTransactionEntryUtil.setPaymentTransactionEntryStatus;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amazonservices.mws.offamazonpayments.model.Address;
import com.amazonservices.mws.offamazonpayments.model.AuthorizationDetails;
import com.amazonservices.mws.offamazonpayments.model.AuthorizeRequest;
import com.amazonservices.mws.offamazonpayments.model.AuthorizeResult;
import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.CaptureDetails;
import com.amazonservices.mws.offamazonpayments.model.CaptureRequest;
import com.amazonservices.mws.offamazonpayments.model.CaptureResult;
import com.amazonservices.mws.offamazonpayments.model.CloseAuthorizationRequest;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceRequest;
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
import com.amazonservices.mws.offamazonpayments.model.OrderTotal;
import com.amazonservices.mws.offamazonpayments.model.Price;
import com.amazonservices.mws.offamazonpayments.model.RefundDetails;
import com.amazonservices.mws.offamazonpayments.model.RefundRequest;
import com.amazonservices.mws.offamazonpayments.model.RefundResult;
import com.amazonservices.mws.offamazonpayments.model.SellerOrderAttributes;
import com.amazonservices.mws.offamazonpayments.model.SetOrderReferenceDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.SetOrderReferenceDetailsResult;

import de.fliegersoftware.amazon.core.data.AmazonAuthorizationDetailsData;
import de.fliegersoftware.amazon.core.data.AmazonCaptureDetailsData;
import de.fliegersoftware.amazon.core.data.AmazonOrderReferenceAttributesData;
import de.fliegersoftware.amazon.core.data.AmazonOrderReferenceDetailsData;
import de.fliegersoftware.amazon.core.data.AmazonRefundDetailsData;
import de.fliegersoftware.amazon.core.model.AmazonPaymentInfoModel;
import de.fliegersoftware.amazon.payment.constants.AmazonpaymentConstants;
import de.fliegersoftware.amazon.payment.dto.AmazonTransactionStatus;
import de.fliegersoftware.amazon.payment.services.AmazonPaymentService;
import de.fliegersoftware.amazon.payment.services.MWSAmazonPaymentService;
import de.hybris.platform.commercefacades.i18n.I18NFacade;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.order.CartService;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.dto.BillingInfo;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.impl.DefaultPaymentServiceImpl;
import de.hybris.platform.payment.methods.CardPaymentService;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;

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
	
	@Resource(name = "i18NFacade")
	private I18NFacade i18NFacade;
	
	@Resource
	private MWSAmazonPaymentService mwsAmazonPaymentService;
	
	@Resource
    private Converter<OrderReferenceDetails, AmazonOrderReferenceDetailsData> amazonOrderReferenceDetailsConverter;
	
	@Resource
    private Converter<CaptureDetails, AmazonCaptureDetailsData> amazonCaptureDetailsConverter;
	
	@Resource
    private Converter<RefundDetails, AmazonRefundDetailsData> amazonRefundDetailsConverter;
	
	@Resource
    private Converter<AuthorizationDetails, AmazonAuthorizationDetailsData> amazonAuthorizationDetailsConverter;
	
	@Resource
    private Converter<AmazonOrderReferenceAttributesData, OrderReferenceAttributes> amazonOrderReferenceAttributesReverseConverter;
	
	@Resource
    private Converter<Address, AddressData> amazonAddressConverter;
	
	private Populator<AddressData, AddressModel> addressReversePopulator;
	
	
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

	public Converter<OrderReferenceDetails, AmazonOrderReferenceDetailsData> getAmazonOrderReferenceDetailsConverter() {
		return amazonOrderReferenceDetailsConverter;
	}

	public void setAmazonOrderReferenceDetailsConverter(
			Converter<OrderReferenceDetails, AmazonOrderReferenceDetailsData> amazonOrderReferenceDetailsConverter) {
		this.amazonOrderReferenceDetailsConverter = amazonOrderReferenceDetailsConverter;
	}

	public Converter<AmazonOrderReferenceAttributesData, OrderReferenceAttributes> getAmazonOrderReferenceAttributesReverseConverter() {
		return amazonOrderReferenceAttributesReverseConverter;
	}

	@Required
	public void setAmazonOrderReferenceAttributesReverseConverter(
			Converter<AmazonOrderReferenceAttributesData, OrderReferenceAttributes> amazonOrderReferenceAttributesReverseConverter) {
		this.amazonOrderReferenceAttributesReverseConverter = amazonOrderReferenceAttributesReverseConverter;
	}
	
	public Converter<CaptureDetails, AmazonCaptureDetailsData> getAmazonCaptureDetailsConverter() {
		return amazonCaptureDetailsConverter;
	}

	public void setAmazonCaptureDetailsConverter(
			Converter<CaptureDetails, AmazonCaptureDetailsData> amazonCaptureDetailsConverter) {
		this.amazonCaptureDetailsConverter = amazonCaptureDetailsConverter;
	}

	public Converter<RefundDetails, AmazonRefundDetailsData> getAmazonRefundDetailsConverter() {
		return amazonRefundDetailsConverter;
	}

	public void setAmazonRefundDetailsConverter(
			Converter<RefundDetails, AmazonRefundDetailsData> amazonRefundDetailsConverter) {
		this.amazonRefundDetailsConverter = amazonRefundDetailsConverter;
	}

	public Converter<AuthorizationDetails, AmazonAuthorizationDetailsData> getAmazonAuthorizationDetailsConverter() {
		return amazonAuthorizationDetailsConverter;
	}

	public void setAmazonAuthorizationDetailsConverter(
			Converter<AuthorizationDetails, AmazonAuthorizationDetailsData> amazonAuthorizationDetailsConverter) {
		this.amazonAuthorizationDetailsConverter = amazonAuthorizationDetailsConverter;
	}

	protected I18NFacade getI18NFacade() {
		return i18NFacade;
	}

	public Converter<Address, AddressData> getAmazonAddressConverter() {
		return amazonAddressConverter;
	}

	public void setAmazonAddressConverter(
			Converter<Address, AddressData> amazonAddressConverter) {
		this.amazonAddressConverter = amazonAddressConverter;
	}

	public Populator<AddressData, AddressModel> getAddressReversePopulator() {
		return addressReversePopulator;
	}

	public void setAddressReversePopulator(
			Populator<AddressData, AddressModel> addressReversePopulator) {
		this.addressReversePopulator = addressReversePopulator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.payment.impl.DefaultPaymentServiceImpl#authorize(java.lang.String, java.math.BigDecimal,
	 * java.util.Currency, de.hybris.platform.core.model.user.AddressModel, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public PaymentTransactionEntryModel authorize(final AmazonPaymentInfoModel paymentInfo
			, final String merchantTransactionCode 
			, final BigDecimal amount
			, Currency currency
			, final String paymentProvider) throws AdapterException
	{
		PaymentTransactionModel transaction = (PaymentTransactionModel) modelService.create(PaymentTransactionModel.class);
		transaction.setCode(merchantTransactionCode);

		return authorizeInternal(paymentInfo, transaction, amount, currency, paymentProvider);
	}

	private PaymentTransactionEntryModel authorizeInternal(final AmazonPaymentInfoModel paymentInfo
			, PaymentTransactionModel transaction
			, BigDecimal amount
			, Currency currency
			, String paymentProvider)
			throws AdapterException {
		String newEntryCode = getNewPaymentTransactionEntryCode(transaction, PaymentTransactionType.AUTHORIZATION);
		
		AuthorizeRequest authorizeRequest = new AuthorizeRequest();
		authorizeRequest.setAuthorizationAmount(getAmount(String.valueOf(amount), currency.getCurrencyCode()));
		authorizeRequest.setAmazonOrderReferenceId(paymentInfo.getAmazonOrderReferenceId());
		authorizeRequest.setAuthorizationReferenceId(String.valueOf(System.currentTimeMillis()));
		AuthorizeResult result = this.mwsAmazonPaymentService.authorize(authorizeRequest);
		
		if(result != null) {
			AuthorizationDetails details = result.getAuthorizationDetails();
			transaction.setRequestId(details.getAuthorizationReferenceId());
			transaction.setRequestToken(details.getAmazonAuthorizationId());
			transaction.setPaymentProvider(paymentProvider);
			this.modelService.save(transaction);
	
			PaymentTransactionEntryModel entry = (PaymentTransactionEntryModel) this.modelService
					.create(PaymentTransactionEntryModel.class);
			entry.setAmount(BigDecimal.valueOf(Double.parseDouble(details.getAuthorizationAmount().getAmount())));
			if (details.getAuthorizationAmount() != null) {
				entry.setCurrency(this.commonI18NService.getCurrency(details.getAuthorizationAmount().getCurrencyCode()));
			}
			entry.setType(PaymentTransactionType.AUTHORIZATION);
			entry.setTime((details.getAuthorizationStatus().getLastUpdateTimestamp() == null) ? new Date()
					: details.getAuthorizationStatus().getLastUpdateTimestamp().toGregorianCalendar().getTime());
			entry.setPaymentTransaction(transaction);
			entry.setRequestId(details.getAuthorizationReferenceId());
			entry.setRequestToken(details.getAmazonAuthorizationId());
			entry.setTransactionStatusDetails(details.getAuthorizationStatus().getReasonDescription());
			entry.setCode(newEntryCode);

			setPaymentTransactionEntryStatus(entry, AmazonTransactionStatus.valueOf(details.getAuthorizationStatus().getState()), details.getAuthorizationStatus().getReasonCode());

			if (!TransactionStatus.ACCEPTED.name().equals(entry)) {
				getSessionService().setAttribute(AmazonpaymentConstants.AMAZON_ERROR_CODE, details.getAuthorizationStatus().getReasonCode());
			}
			getModelService().save(entry);
			getModelService().refresh(transaction);

			paymentInfo.setAmazonLastAuthorizationId(details.getAuthorizationReferenceId());
			paymentInfo.setAmazonAuthorizationStatus(details.getAuthorizationStatus().getState());
			paymentInfo.setAmazonAuthorizationReasonCode(details.getAuthorizationStatus().getReasonCode());
//			AddressData addressData = amazonAddressConverter.convert(details.getAuthorizationBillingAddress());
//			
//			final AddressModel addressModel = getModelService().create(AddressModel.class);
//			getAddressReversePopulator().populate(addressData, addressModel);
//			
//			getModelService().save(addressModel);
//			
//			paymentInfo.setBillingAddress(addressModel);
			getModelService().save(paymentInfo);

			return entry;
		}
		return null;
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
		String newEntryCode = getNewPaymentTransactionEntryCode(transaction, PaymentTransactionType.CAPTURE);
		
		CaptureRequest captureRequest = new CaptureRequest();
		captureRequest.setAmazonAuthorizationId(auth.getRequestToken());
		captureRequest.setCaptureReferenceId(String.valueOf(System.currentTimeMillis()));
		
		captureRequest.setCaptureAmount(getAmount(String.valueOf(auth.getAmount()), auth.getCurrency().getIsocode()));
		
		CaptureResult result = this.mwsAmazonPaymentService.capture(captureRequest);

		if(result != null) {
			PaymentTransactionEntryModel entry = (PaymentTransactionEntryModel) this.modelService.create(PaymentTransactionEntryModel.class);
			CaptureDetails details = result.getCaptureDetails();
			entry.setAmount(BigDecimal.valueOf(Double.parseDouble(details.getCaptureAmount().getAmount())));
			if (details.getCaptureAmount() != null) {
				entry.setCurrency(this.commonI18NService.getCurrency(details.getCaptureAmount().getCurrencyCode()));
			}
			entry.setType(PaymentTransactionType.CAPTURE);
			entry.setTime((details.getCaptureStatus().getLastUpdateTimestamp() == null) ? new Date()
					: details.getCaptureStatus().getLastUpdateTimestamp().toGregorianCalendar().getTime());
			entry.setPaymentTransaction(transaction);
			entry.setRequestId(details.getCaptureReferenceId());
			entry.setRequestToken(details.getAmazonCaptureId());

			setPaymentTransactionEntryStatus(entry, AmazonTransactionStatus.valueOf(details.getCaptureStatus().getState()), details.getCaptureStatus().getReasonCode());

			entry.setCode(newEntryCode);

			this.modelService.save(entry);
			this.modelService.refresh(transaction);
			return entry;
		}
		return null;
	}
	
	@Override
	public PaymentTransactionEntryModel refund(PaymentTransactionModel transaction, BigDecimal amount,
			Currency currency, String sellerRefundNote, String softDescriptor) {
		PaymentTransactionEntryModel capture = null;
		for (PaymentTransactionEntryModel pte : transaction.getEntries()) {
			if (!(pte.getType().equals(PaymentTransactionType.CAPTURE)))
				continue;
			capture = pte;
			break;
		}

		if (capture == null) {
			throw new AdapterException(
					"Could not refund without capture");
		}
		String newEntryCode = getNewPaymentTransactionEntryCode(transaction, PaymentTransactionType.REFUND);

		RefundRequest request = new RefundRequest();
		request.setAmazonCaptureId(capture.getRequestToken());
		request.setRefundReferenceId(String.valueOf(System.currentTimeMillis()));
		request.setRefundAmount(getAmount(String.valueOf(amount), currency.getCurrencyCode()));
		request.setSellerRefundNote(sellerRefundNote);
		request.setSoftDescriptor(softDescriptor);
		
		RefundResult result = mwsAmazonPaymentService.refund(request);
		if(result != null) {
			PaymentTransactionEntryModel entry = (PaymentTransactionEntryModel) this.modelService.create(PaymentTransactionEntryModel.class);
			RefundDetails details = result.getRefundDetails();
			entry.setAmount(BigDecimal.valueOf(Double.parseDouble(details.getRefundAmount().getAmount())));
			if (details.getRefundAmount() != null) {
				entry.setCurrency(this.commonI18NService.getCurrency(details.getRefundAmount().getCurrencyCode()));
			}
			entry.setType(PaymentTransactionType.REFUND);
			entry.setTime((details.getRefundStatus().getLastUpdateTimestamp() == null) ? new Date()
					: details.getRefundStatus().getLastUpdateTimestamp().toGregorianCalendar().getTime());
			entry.setPaymentTransaction(transaction);
			entry.setRequestId(details.getRefundReferenceId());
			entry.setRequestToken(details.getAmazonRefundId());

			setPaymentTransactionEntryStatus(entry, AmazonTransactionStatus.valueOf(details.getRefundStatus().getState()), details.getRefundStatus().getReasonCode());

			entry.setCode(newEntryCode);

			this.modelService.save(entry);
			this.modelService.refresh(transaction);
			return entry;
		}
		return null;
	}
	
	@Override
	public AmazonOrderReferenceDetailsData getOrderReferenceDetails(final String amazonOrderReferenceId, final String addressConsentToken) throws AdapterException 
	{
		final GetOrderReferenceDetailsRequest request = new GetOrderReferenceDetailsRequest();
		request.setAmazonOrderReferenceId(amazonOrderReferenceId);

		if(StringUtils.isNotBlank(addressConsentToken))
		{
			request.setAddressConsentToken(addressConsentToken);
		}

		GetOrderReferenceDetailsResult result = mwsAmazonPaymentService.getOrderReferenceDetails(request);
		OrderReferenceDetails details = result.getOrderReferenceDetails();
		return amazonOrderReferenceDetailsConverter.convert(details);
	}

	@Override
	public AmazonCaptureDetailsData getCaptureDetails(String amazonCaptureId) throws AdapterException {
		GetCaptureDetailsRequest request = new GetCaptureDetailsRequest();
		request.setAmazonCaptureId(amazonCaptureId);
		GetCaptureDetailsResult result = mwsAmazonPaymentService.getCaptureDetails(request);
		return amazonCaptureDetailsConverter.convert(result.getCaptureDetails());
	}

	@Override
	public AmazonAuthorizationDetailsData getAuthorizationDetails(String amazonAuthorizationId) throws AdapterException {
		GetAuthorizationDetailsRequest request = new GetAuthorizationDetailsRequest();
		request.setAmazonAuthorizationId(amazonAuthorizationId);
		GetAuthorizationDetailsResult result = mwsAmazonPaymentService.getAuthorizationDetails(request);
		return amazonAuthorizationDetailsConverter.convert(result.getAuthorizationDetails());
	}
	
	@Override
	public AmazonRefundDetailsData getRefundDetails(String amazonRefundId) throws AdapterException {
		GetRefundDetailsRequest request = new GetRefundDetailsRequest();
		request.setAmazonRefundId(amazonRefundId);
		GetRefundDetailsResult result = mwsAmazonPaymentService.getRefundDetails(request);
		return amazonRefundDetailsConverter.convert(result.getRefundDetails());
	}
	
	@Override
	public AmazonOrderReferenceDetailsData setOrderReferenceDetails(String amazonOrderReferenceId, AmazonOrderReferenceAttributesData amazonOrderReferenceAttributesData) throws AdapterException {
			SetOrderReferenceDetailsRequest request = new SetOrderReferenceDetailsRequest();
			request.setOrderReferenceAttributes(amazonOrderReferenceAttributesReverseConverter.convert(amazonOrderReferenceAttributesData));
			request.setAmazonOrderReferenceId(amazonOrderReferenceId);
			SetOrderReferenceDetailsResult result = mwsAmazonPaymentService.setOrderReferenceDetails(request);
			return amazonOrderReferenceDetailsConverter.convert(result.getOrderReferenceDetails());
	}
	
	@Override
	public void confirmOrderReference(String amazonOrderReferenceId) throws AdapterException {
		ConfirmOrderReferenceRequest request = new ConfirmOrderReferenceRequest();
		request.setAmazonOrderReferenceId(amazonOrderReferenceId);
		
		mwsAmazonPaymentService.confirmOrderReferenceCommand(request);
	}
	
	@Override
	public void cancelOrderReference(final String amazonOrderReferenceId, final String cancelationReason) throws AdapterException
	{
		final CancelOrderReferenceRequest request = new CancelOrderReferenceRequest();
		request.setCancelationReason(cancelationReason);
		request.setAmazonOrderReferenceId(amazonOrderReferenceId);

		mwsAmazonPaymentService.cancelOrderReference(request);
	}
	
	@Override
	public void closeOrderReference(final String amazonOrderReferenceId, final String closureReason) throws AdapterException
	{
		CloseOrderReferenceRequest request = new CloseOrderReferenceRequest();
		request.setAmazonOrderReferenceId(amazonOrderReferenceId);
		request.setClosureReason(closureReason);
		mwsAmazonPaymentService.closeOrderReference(request);
	}
	
	@Override
	public void closeAuthorization(final String amazonAuthorizationId, final String closureReason) throws AdapterException
	{
		CloseAuthorizationRequest request = new CloseAuthorizationRequest();
		request.setAmazonAuthorizationId(amazonAuthorizationId);
		request.setClosureReason(closureReason);
		mwsAmazonPaymentService.closeAuthorization(request);
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
	
	public CountryData getCountry(final String amazonOrderReferenceId) {
		AmazonOrderReferenceDetailsData amazonOrderReferenceDetailsData = getOrderReferenceDetails(amazonOrderReferenceId, null);
		if(amazonOrderReferenceDetailsData != null && 
				amazonOrderReferenceDetailsData.getAddressData() == null && 
					amazonOrderReferenceDetailsData.getAddressData().getCountry() != null) {
				return getI18NFacade().getCountryForIsocode(amazonOrderReferenceDetailsData.getAddressData().getCountry().getIsocode());
		}
		return getI18NFacade().getCountryForIsocode("DE");
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
