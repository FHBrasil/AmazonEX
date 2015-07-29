package de.fliegersoftware.amazon.payment.commands.impl;
///**
// * 
// */
//package de.fliegersoftware.amazon.commands.impl;
//
//import de.hybris.platform.jalo.Item;
//import de.hybris.platform.jalo.c2l.C2LManager;
//import de.hybris.platform.jalo.c2l.Country;
//import de.hybris.platform.jalo.flexiblesearch.FlexibleSearch;
//import de.hybris.platform.jalo.order.AbstractOrder;
//import de.hybris.platform.jalo.order.Order;
//import de.hybris.platform.jalo.user.Address;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.lang.CharUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//
//import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceClient;
//import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
//import com.amazonservices.mws.offamazonpayments.model.AuthorizationDetails;
//import com.amazonservices.mws.offamazonpayments.model.AuthorizeRequest;
//import com.amazonservices.mws.offamazonpayments.model.AuthorizeResponse;
//import com.amazonservices.mws.offamazonpayments.model.Buyer;
//import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceRequest;
//import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceResponse;
//import com.amazonservices.mws.offamazonpayments.model.CaptureDetails;
//import com.amazonservices.mws.offamazonpayments.model.CaptureRequest;
//import com.amazonservices.mws.offamazonpayments.model.CaptureResponse;
//import com.amazonservices.mws.offamazonpayments.model.CaptureResult;
//import com.amazonservices.mws.offamazonpayments.model.CloseAuthorizationRequest;
//import com.amazonservices.mws.offamazonpayments.model.CloseAuthorizationResponse;
//import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceRequest;
//import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceResponse;
//import com.amazonservices.mws.offamazonpayments.model.ConfirmOrderReferenceRequest;
//import com.amazonservices.mws.offamazonpayments.model.Constraint;
//import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsRequest;
//import com.amazonservices.mws.offamazonpayments.model.GetAuthorizationDetailsResponse;
//import com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsRequest;
//import com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsResponse;
//import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsRequest;
//import com.amazonservices.mws.offamazonpayments.model.GetOrderReferenceDetailsResponse;
//import com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsRequest;
//import com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsResponse;
//import com.amazonservices.mws.offamazonpayments.model.OrderReferenceAttributes;
//import com.amazonservices.mws.offamazonpayments.model.OrderReferenceDetails;
//import com.amazonservices.mws.offamazonpayments.model.OrderTotal;
//import com.amazonservices.mws.offamazonpayments.model.Price;
//import com.amazonservices.mws.offamazonpayments.model.RefundDetails;
//import com.amazonservices.mws.offamazonpayments.model.RefundRequest;
//import com.amazonservices.mws.offamazonpayments.model.RefundResponse;
//import com.amazonservices.mws.offamazonpayments.model.RefundResult;
//import com.amazonservices.mws.offamazonpayments.model.SellerOrderAttributes;
//import com.amazonservices.mws.offamazonpayments.model.SetOrderReferenceDetailsRequest;
//import com.amazonservices.mws.offamazonpayments.model.SetOrderReferenceDetailsResponse;
//
//import de.fliegersoftware.amazon.exception.AmazonPaymentException;
//import de.fliegersoftware.amazon.jalo.AmazonOrder;
//import de.fliegersoftware.amazon.util.AmazonConfig;
//import de.fliegersoftware.jalo.AmazonManager;
//
///**
// * @author douglas.canalli
// *
// */
//public class AmazonPaymentCommand {
//	
//	private static final Logger log = Logger.getLogger(AmazonPaymentCommand.class);
//	
//	private static AmazonPaymentCommand command = null;
//	
//	private OffAmazonPaymentsServiceClient service = null;
//	
//	private static Properties properties;
//	
//	private static final String FULL_REFUND_CHECKOUT_NOTE = "FULL REFUND - PAYMENT PROCESS INTERRUPTED";
//	
//	public static final String PARAMETER_BUYER_NAME = "buyerName";
//	public static final String PARAMETER_BUYER_EMAIL = "buyerEmail";
//	public static final String PARAMETER_BUYER_PHONE = "buyerPhone";
//	
//	public static final String PARAMETER_ADDRESS_LINE1 = "AddressLine1";
//	public static final String PARAMETER_ADDRESS_LINE2 = "AddressLine2";
//	public static final String PARAMETER_ADDRESS_LINE3 = "AddressLine3";
//	public static final String PARAMETER_ADDRESS_CITY = "City";
//	public static final String PARAMETER_ADDRESS_COUNTRY_CODE = "CountryCode";
//	public static final String PARAMETER_ADDRESS_COUNTY = "County";
//	public static final String PARAMETER_ADDRESS_DISTRICT = "District";
//	public static final String PARAMETER_ADDRESS_NAME = "Name";
//	public static final String PARAMETER_ADDRESS_PHONE = "Phone";
//	public static final String PARAMETER_ADDRESS_POSTAL_CODE = "PostalCode";
//	public static final String PARAMETER_ADDRESS_STATE_OR_REGION = "StateOrRegion";
//	
//	/**
//	 * 
//	 */
//	private AmazonPaymentCommand() {
//		service = AmazonConfig.getAmazonPaymentService();
//		properties = AmazonConfig.getProperties();
//	}
//	
//	public static AmazonPaymentCommand getInstance() {
//		if(command == null) {
//			command = new AmazonPaymentCommand();
//		}
//		return command;
//	}
//
//	/* GETS - START */
//	
//	public OrderReferenceDetails getOrderReferenceDetails(final String amazonOrderReferenceId) throws AmazonPaymentException 
//	{
//		return getOrderReferenceDetails(amazonOrderReferenceId, null);
//	}
//	
//	public OrderReferenceDetails getOrderReferenceDetails(final String amazonOrderReferenceId, final String addConsentToken) throws AmazonPaymentException 
//	{
//		try 
//		{
//			final GetOrderReferenceDetailsRequest request = new GetOrderReferenceDetailsRequest();
//			request.setSellerId(getSellerId());
//			request.setAmazonOrderReferenceId(amazonOrderReferenceId);
//			
//			if(StringUtils.isNotBlank(addConsentToken))
//			{
//				request.setAddressConsentToken(addConsentToken);
//			}
//			
//			GetOrderReferenceDetailsResponse response = service.getOrderReferenceDetails(request);
//			OrderReferenceDetails details = response.getGetOrderReferenceDetailsResult().getOrderReferenceDetails();
//			return details;
//		} 
//		catch (OffAmazonPaymentsServiceException e) 
//		{
//			throw getAmazonPaymentException(e);
//		}
//	}
//	
//	public Map<String, String> getOrderReferenceDetailsMap(final String amazonOrderReferenceId, final String addConsentToken) throws AmazonPaymentException {
//		OrderReferenceDetails orderReferenceDetails = getOrderReferenceDetails(amazonOrderReferenceId, addConsentToken);
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("amazonOrderReferenceId", orderReferenceDetails.getAmazonOrderReferenceId());
//		if(orderReferenceDetails.getBuyer() != null) {
//			Buyer buyer = orderReferenceDetails.getBuyer();
//			map.put(PARAMETER_BUYER_EMAIL, buyer.getEmail());
//			map.put(PARAMETER_BUYER_NAME, buyer.getName());
//			map.put(PARAMETER_BUYER_PHONE, buyer.getPhone());
//		}
//		if(orderReferenceDetails.getDestination() != null && orderReferenceDetails.getDestination().getPhysicalDestination() != null) {
//			com.amazonservices.mws.offamazonpayments.model.Address physicalDestination = orderReferenceDetails.getDestination().getPhysicalDestination();
//			map.put(PARAMETER_ADDRESS_LINE1, StringUtils.isNotBlank(physicalDestination.getAddressLine1()) ? physicalDestination.getAddressLine1() : "");
//			map.put(PARAMETER_ADDRESS_LINE2, StringUtils.isNotBlank(physicalDestination.getAddressLine2()) ? physicalDestination.getAddressLine2() : "");
//			map.put(PARAMETER_ADDRESS_LINE3, StringUtils.isNotBlank(physicalDestination.getAddressLine3()) ? physicalDestination.getAddressLine3() : "");
//			map.put(PARAMETER_ADDRESS_CITY, physicalDestination.getCity());
//			map.put(PARAMETER_ADDRESS_COUNTRY_CODE, physicalDestination.getCountryCode());
//			map.put(PARAMETER_ADDRESS_COUNTY, physicalDestination.getCounty());
//			map.put(PARAMETER_ADDRESS_DISTRICT, physicalDestination.getDistrict());
//			map.put(PARAMETER_ADDRESS_NAME, physicalDestination.getName());
//			map.put(PARAMETER_ADDRESS_PHONE, physicalDestination.getPhone());
//			map.put(PARAMETER_ADDRESS_POSTAL_CODE, physicalDestination.getPostalCode());
//			map.put(PARAMETER_ADDRESS_STATE_OR_REGION, physicalDestination.getStateOrRegion());
//		}
//		return map;
//	}
//	
//	public Country getCountry(String findCountry) throws AmazonPaymentException {
//		OrderReferenceDetails orderReferenceDetails = getOrderReferenceDetails(findCountry);
//		if(	orderReferenceDetails == null || 
//			orderReferenceDetails.getDestination() == null ||
//			orderReferenceDetails.getDestination().getPhysicalDestination() == null ||
//			orderReferenceDetails.getDestination().getPhysicalDestination().getCountryCode() == null) {
//				return C2LManager.getInstance().getCountryByIsoCode("de");
//		}
//		return C2LManager.getInstance().getCountryByIsoCode(orderReferenceDetails.getDestination().getPhysicalDestination().getCountryCode().toLowerCase());
//	}	
//	
//	public void printOrderReferenceDetails(String amazonOrderReferenceId) throws AmazonPaymentException {
//		OrderReferenceDetails details = getOrderReferenceDetails(amazonOrderReferenceId);
//		System.out.println(details.getAmazonOrderReferenceId());
//		System.out.println(details.getSellerNote());
//		if(details.getBuyer() != null) {
//			System.out.println("getEmail " + details.getBuyer().getEmail());
//			System.out.println("getName " + details.getBuyer().getName());
//			System.out.println("getPhone " + details.getBuyer().getPhone());
//		}
//		if(details.getConstraints() != null && CollectionUtils.isNotEmpty(details.getConstraints().getConstraint())) {
//			for (Constraint constraint : details.getConstraints().getConstraint()) {
//				System.out.println("getConstraintID " + constraint.getConstraintID());
//				System.out.println("getDescription " + constraint.getDescription());
//			}
//		}
//		if(details.getDestination() != null) {
//			System.out.println("" + details.getDestination().getDestinationType());
//			if(details.getDestination().getPhysicalDestination() != null) {
//				System.out.println("getAddressLine1 " + details.getDestination().getPhysicalDestination().getAddressLine1());
//				System.out.println("getAddressLine2 " + details.getDestination().getPhysicalDestination().getAddressLine2());
//				System.out.println("getCity " + details.getDestination().getPhysicalDestination().getCity());
//				System.out.println("getCountryCode " + details.getDestination().getPhysicalDestination().getCountryCode());
//				System.out.println("getCounty " + details.getDestination().getPhysicalDestination().getCounty());
//				System.out.println("getDistrict " + details.getDestination().getPhysicalDestination().getDistrict());
//				System.out.println("getName " + details.getDestination().getPhysicalDestination().getName());
//				System.out.println("getPhone " + details.getDestination().getPhysicalDestination().getPhone());
//				System.out.println("getPostalCode " + details.getDestination().getPhysicalDestination().getPostalCode());
//				System.out.println("getStateOrRegion " + details.getDestination().getPhysicalDestination().getStateOrRegion());
//			}
//		}
//		if(details.getOrderReferenceStatus() != null) {
//			System.out.println("getReasonCode " + details.getOrderReferenceStatus().getReasonCode());
//			System.out.println("getReasonDescription " + details.getOrderReferenceStatus().getReasonDescription());
//			System.out.println("getState " + details.getOrderReferenceStatus().getState());
//		}
//		
//		if(details.getOrderTotal() != null) {
//			System.out.println("getAmount " + details.getOrderTotal().getAmount());
//			System.out.println("getCurrencyCode " + details.getOrderTotal().getCurrencyCode());
//		}
//		
//		if(details.getReleaseEnvironment() != null) {
//			System.out.println("getReleaseEnvironment " + details.getReleaseEnvironment().name());
//		}
//		
//		if(details.getSellerOrderAttributes() != null) {
//			System.out.println("getCustomInformation " + details.getSellerOrderAttributes().getCustomInformation());
//			System.out.println("getSellerOrderId " + details.getSellerOrderAttributes().getSellerOrderId());
//			System.out.println("getStoreName " + details.getSellerOrderAttributes().getStoreName());
//			if(details.getSellerOrderAttributes().getOrderItemCategories() != null && CollectionUtils.isNotEmpty(details.getSellerOrderAttributes().getOrderItemCategories().getOrderItemCategory()	)) {
//				System.out.println("getOrderItemCategory " + details.getSellerOrderAttributes().getOrderItemCategories().getOrderItemCategory().toString());
//			}
//		}
//		
//	}
//	
//	public AuthorizationDetails getAuthorizationDetails(String amazonAuthorizationId) throws AmazonPaymentException {
//		try {
//			GetAuthorizationDetailsRequest request = new GetAuthorizationDetailsRequest();
//			request.setAmazonAuthorizationId(amazonAuthorizationId);
//			request.setSellerId(getSellerId());
//			GetAuthorizationDetailsResponse response = service.getAuthorizationDetails(request);
//			return response.getGetAuthorizationDetailsResult().getAuthorizationDetails();
//		} catch (OffAmazonPaymentsServiceException e) {
//			throw getAmazonPaymentException(e);
//		}
//	}
//
//	public CaptureDetails getCaptureDetails(String amazonAuthorizationId) throws AmazonPaymentException {
//		try {
//			AuthorizationDetails authorizationDetails = getAuthorizationDetails(amazonAuthorizationId);
//			GetCaptureDetailsRequest request = new GetCaptureDetailsRequest();
//			request.setAmazonCaptureId(authorizationDetails.getIdList().getMember().get(0));
//			request.setSellerId(getSellerId());
//			GetCaptureDetailsResponse response = service.getCaptureDetails(request);
//			return response.getGetCaptureDetailsResult().getCaptureDetails();
//		} catch (OffAmazonPaymentsServiceException e) {
//			throw getAmazonPaymentException(e);
//		}
//	}
//	
//	public CaptureDetails getCaptureDetailsByCaptureId(String amazonCaptureId) throws AmazonPaymentException {
//		try {
//			GetCaptureDetailsRequest request = new GetCaptureDetailsRequest();
//			request.setAmazonCaptureId(amazonCaptureId);
//			request.setSellerId(getSellerId());
//			GetCaptureDetailsResponse response = service.getCaptureDetails(request);
//			return response.getGetCaptureDetailsResult().getCaptureDetails();
//		} catch (OffAmazonPaymentsServiceException e) {
//			throw getAmazonPaymentException(e);
//		}
//	}
//	
//	public boolean isRefundRequested(String amazonAuthorizationId) throws AmazonPaymentException {
//		boolean refundRequested = CollectionUtils.isNotEmpty(getAllRefundDetails(amazonAuthorizationId));
//		if(!refundRequested) {
//			AmazonOrder amazonOrder = findAmazonOrderByAmazonAuthorizationId(amazonAuthorizationId);
//			refundRequested = amazonOrder != null && (amazonOrder.isRefundRequestedAsPrimitive() || amazonOrder.isCancelRequestedAsPrimitive());
//		}
//		return refundRequested;
//	}
//	
//	public boolean isSuccessPaidOrder(String amazonAuthorizationId) throws AmazonPaymentException {
//		AuthorizationDetails checkAuthorizationDetails = getAuthorizationDetails(amazonAuthorizationId);
//		return isSuccessPaidOrder(checkAuthorizationDetails);
//	}
//	
//	public boolean isSuccessPaidOrder(AuthorizationDetails checkAuthorizationDetails) {
//		boolean isClosed = "CLOSED".equalsIgnoreCase(checkAuthorizationDetails.getAuthorizationStatus().getState());
//		boolean isMaxCapturesProcessed = "MaxCapturesProcessed".equalsIgnoreCase(checkAuthorizationDetails.getAuthorizationStatus().getReasonCode());
//		return isClosed && isMaxCapturesProcessed;
//	}
//	
//	/* GETS - END */
//	
//	/* FUNCTIONS - START */
//	
//	private OrderReferenceDetails setOrderReferenceDetails(String amazonOrderReferenceId, String orderCode, String amount) throws AmazonPaymentException {
//		try {
//			SetOrderReferenceDetailsRequest request = new SetOrderReferenceDetailsRequest();
//			request.setOrderReferenceAttributes(getOrderReferenceAttributes(orderCode, amount));
//			request.setSellerId(getSellerId());
//			request.setAmazonOrderReferenceId(amazonOrderReferenceId);
//			SetOrderReferenceDetailsResponse response = service.setOrderReferenceDetails(request);
//			return response.getSetOrderReferenceDetailsResult().getOrderReferenceDetails();
//		} catch (OffAmazonPaymentsServiceException e) {
//			throw getAmazonPaymentException(e);
//		}
//	}
//	
//	public String authorize(String amazonOrderReferenceId, String amount) throws AmazonPaymentException {
//		return authorize(amazonOrderReferenceId, amount, true);
//	}	
//	
//	public String authorizeTest(String amazonOrderReferenceId, String amount, String params) throws AmazonPaymentException {
//		return authorize(amazonOrderReferenceId, amount, params, 0);
//	}
//	
//	public String authorize(String amazonOrderReferenceId, String amount, boolean captureNow) throws AmazonPaymentException {
//		try {
//			AuthorizeRequest authorizeRequest = new AuthorizeRequest();
//			authorizeRequest.setSellerId(getSellerId());
//			authorizeRequest.setAuthorizationAmount(getAuthorizationAmount(amount));
//			authorizeRequest.setAuthorizationReferenceId(String.valueOf(System.currentTimeMillis()));
//			authorizeRequest.setAmazonOrderReferenceId(amazonOrderReferenceId);
//			authorizeRequest.setCaptureNow(captureNow);
//			AuthorizeResponse response = service.authorize(authorizeRequest);
//			AuthorizationDetails authorizationDetails = response.getAuthorizeResult().getAuthorizationDetails();
//			return authorizationDetails.getAmazonAuthorizationId();
//		} catch (OffAmazonPaymentsServiceException e) {
//			throw getAmazonPaymentException(e);
//		}
//	}
//	
//	public void setOrderDetails(String amazonOrderReferenceId, String orderCode, String amount) throws AmazonPaymentException {
//		setOrderReferenceDetails(amazonOrderReferenceId, orderCode, amount);
//	}
//	
//	public void confirmOrder(String amazonOrderReferenceId) throws AmazonPaymentException {
//		try {
//			ConfirmOrderReferenceRequest request = new ConfirmOrderReferenceRequest();
//			request.setSellerId(getSellerId());
//			request.setAmazonOrderReferenceId(amazonOrderReferenceId);
//			service.confirmOrderReference(request);
//		} catch (OffAmazonPaymentsServiceException e) {
//			throw getAmazonPaymentException(e);
//		}
//	}
//	
//	public void finalizeCheckout(String amazonOrderReferenceId, String orderCode, String amount) throws AmazonPaymentException {
//		setOrderReferenceDetails(amazonOrderReferenceId, orderCode, amount);
//		confirmOrder(amazonOrderReferenceId);
//	}
//	
//	public String closeOrder(String amazonOrderReferenceId, String closureReason) throws AmazonPaymentException  {
//		try {
//			CloseOrderReferenceRequest request = new CloseOrderReferenceRequest();
//			request.setAmazonOrderReferenceId(amazonOrderReferenceId);
//			request.setClosureReason(closureReason);
//			request.setSellerId(getSellerId());
//			CloseOrderReferenceResponse response = service.closeOrderReference(request);
//			return response.getCloseOrderReferenceResult().toXMLFragment();
//		} catch (OffAmazonPaymentsServiceException e) {
//			throw getAmazonPaymentException(e);
//		}
//	}
//	
//	public String closeAuthorizationOrder(String amazonAuthorizationId, String closureReason) throws AmazonPaymentException {
//		try {
//			CloseAuthorizationRequest request = new CloseAuthorizationRequest();
//			request.setAmazonAuthorizationId(amazonAuthorizationId);
//			request.setClosureReason(closureReason);
//			request.setSellerId(getSellerId());
//			CloseAuthorizationResponse response = service.closeAuthorization(request);
//			return response.getCloseAuthorizationResult().toXMLFragment();
//		} catch (OffAmazonPaymentsServiceException e) {
//			throw getAmazonPaymentException(e);
//		}
//	}
//	
//	public RefundResult refund(String amazonAuthorizationId) throws AmazonPaymentException {
//		return refund(amazonAuthorizationId, FULL_REFUND_CHECKOUT_NOTE);
//	}
//	
//	public RefundResult refund(String amazonAuthorizationId, String sellerRefundNote) throws AmazonPaymentException {
//		try {
//			CaptureDetails captureDetails = getCaptureDetails(amazonAuthorizationId);
//			RefundRequest request = new RefundRequest();
//			request.setAmazonCaptureId(captureDetails.getAmazonCaptureId());
//			request.setRefundAmount(captureDetails.getCaptureAmount());
//			request.setRefundReferenceId(captureDetails.getCaptureReferenceId() + System.currentTimeMillis());
//			request.setSellerId(getSellerId());
//			request.setSellerRefundNote(sellerRefundNote);
//			RefundResponse response = service.refund(request);
//			return response.getRefundResult();
//		} catch (OffAmazonPaymentsServiceException e) {
//			throw getAmazonPaymentException(e);
//		}
//	}
//	/* FUNCTIONS - END */
//	
//	/* METHODS AUX - START */
//	
//	private SellerOrderAttributes getSellerOrderAttributes(String code) {
//		SellerOrderAttributes sellerOrderAttributes = new SellerOrderAttributes();
//		sellerOrderAttributes.setSellerOrderId(code);
//		return sellerOrderAttributes;
//	}
//	
//	private OrderReferenceAttributes getOrderReferenceAttributes(String orderCode, String amount) {
//		OrderReferenceAttributes orderReferenceAttributes = new OrderReferenceAttributes();
//		orderReferenceAttributes.setSellerOrderAttributes(getSellerOrderAttributes(orderCode));
//		orderReferenceAttributes.setOrderTotal(getOrderTotal(amount));
//		return orderReferenceAttributes;
//	}
//	
//	private Price getAuthorizationAmount(String amount) {
//		Price authorizationAmount = new Price();
//		authorizationAmount.setAmount(amount);
//		authorizationAmount.setCurrencyCode(getCurrency());
//		return authorizationAmount;
//	}
//	
//	private OrderTotal getOrderTotal(String amount) {
//		OrderTotal orderTotal = new OrderTotal();
//		orderTotal.setAmount(amount);
//		orderTotal.setCurrencyCode(getCurrency());
//		return orderTotal;
//	}
//	
//	public String getCurrency() {
//		return properties.getProperty(AmazonConfig.CURRENCY);
//	}
//	
//	public String getSellerId() {
//		return properties.getProperty(AmazonConfig.SELLER_ID);
//	}
//	/* METHODS AUX - END */
//
//	/* HMC - START */
//	public AmazonOrder saveAmazonOrder(Order order, String amazonAuthorizationId) throws AmazonPaymentException {
//		AuthorizationDetails authorizationDetails = getAuthorizationDetails(amazonAuthorizationId);
//		AmazonOrder amazonOrder = findAmazonOrderByAmazonAuthorizationId(amazonAuthorizationId);
//		if(amazonOrder == null) {
//			amazonOrder = createAmazonOrder(order, authorizationDetails);
//		} else {
//			updateAmazonOrder(amazonOrder, order, authorizationDetails);
//		}
//		return amazonOrder;
//	}
//	
//	public void requestRefund(Order order, String amazonAuthorizationId) {
//		AmazonOrder amazonOrder = findAmazonOrderByAmazonAuthorizationId(amazonAuthorizationId);
//		if(amazonOrder != null) {
//			try {
//				amazonOrder.setAttribute(AmazonOrder.REFUNDREQUESTED, true);
//			} catch (Exception e) {
//				log.error("Error while setting request as cancel amazon order", e);
//			}
//		} else {
//			try {
//				amazonOrder = saveAmazonOrder(order, amazonAuthorizationId);
//				amazonOrder.setAttribute(AmazonOrder.REFUNDREQUESTED, true);
//			} catch (AmazonPaymentException e) {
//				e.logMessage();
//			} catch (Exception e) {
//				log.error("Error while setting attribute refund requested as true", e);
//			}
//		}
//		try {
//			refund(amazonAuthorizationId);
//		} catch (AmazonPaymentException e) {
//			e.logMessage();
//		}
//	}
//	
//	private AmazonOrder createAmazonOrder(AbstractOrder abstractOrder, AuthorizationDetails authorizationDetails) {
//		Map<String, Object> attributes = new HashMap<String, Object>();
//		
//		String amazonOrderReferenceId = authorizationDetails.getAmazonAuthorizationId();
//		amazonOrderReferenceId = amazonOrderReferenceId.split("-A")[0];
//		
//		attributes.put(AmazonOrder.INTERNALORDERREFERENCEID, abstractOrder.getCode() + "_" + String.valueOf(System.currentTimeMillis()));
//		attributes.put(AmazonOrder.AMAZONORDERREFERENCEID, amazonOrderReferenceId);
//		attributes.put(AmazonOrder.AMAZONAUTHORIZATIONID, authorizationDetails.getAmazonAuthorizationId());
//		attributes.put(AmazonOrder.AUTHORIZATIONAMOUNT, Double.valueOf(authorizationDetails.getAuthorizationAmount().getAmount()));
//		attributes.put(AmazonOrder.AUTHORIZATIONFEE, Double.valueOf(authorizationDetails.getAuthorizationFee().getAmount()));
//		attributes.put(AmazonOrder.EXPIRATIONTIME, authorizationDetails.getExpirationTimestamp().toGregorianCalendar().getTime());
//		attributes.put(AmazonOrder.ORDER, abstractOrder);
//		attributes.put(AmazonOrder.ORDERCODE, abstractOrder.getCode());
//		attributes.put(AmazonOrder.REASONCODE, authorizationDetails.getAuthorizationStatus().getReasonCode());
//		attributes.put(AmazonOrder.STATE, authorizationDetails.getAuthorizationStatus().getState());
//		attributes.put(AmazonOrder.CAPTUREREQUESTED, authorizationDetails.isCaptureNow());
//		return AmazonAPIManager.getInstance().createAmazonOrder(attributes);
//	}
//	
//	private void updateAmazonOrder(AmazonOrder amazonOrder, AbstractOrder abstractOrder, AuthorizationDetails authorizationDetails) {
//		try {
//			amazonOrder.setAttribute(AmazonOrder.AMAZONAUTHORIZATIONID, authorizationDetails.getAmazonAuthorizationId());
//			amazonOrder.setAttribute(AmazonOrder.AUTHORIZATIONAMOUNT, Double.valueOf(authorizationDetails.getAuthorizationAmount().getAmount()));
//			amazonOrder.setAttribute(AmazonOrder.AUTHORIZATIONFEE, Double.valueOf(authorizationDetails.getAuthorizationFee().getAmount()));
//			amazonOrder.setAttribute(AmazonOrder.CANCELREQUESTED, false);
//			amazonOrder.setAttribute(AmazonOrder.EXPIRATIONTIME, authorizationDetails.getExpirationTimestamp().toGregorianCalendar().getTime());
//			amazonOrder.setAttribute(AmazonOrder.ORDER, abstractOrder);
//			amazonOrder.setAttribute(AmazonOrder.REASONCODE, authorizationDetails.getAuthorizationStatus().getReasonCode());
//			amazonOrder.setAttribute(AmazonOrder.REFUNDREQUESTED, false);
//			amazonOrder.setAttribute(AmazonOrder.STATE, authorizationDetails.getAuthorizationStatus().getState());
//			amazonOrder.setAttribute(AmazonOrder.CAPTUREREQUESTED, authorizationDetails.isCaptureNow());
//		} catch (Exception e) {
//			log.error("Error while updating amazon order", e);
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public AmazonOrder findAmazonOrderByAmazonAuthorizationId(String amazonAuthorizationId) {
//		StringBuilder sql = new StringBuilder();
//		sql.append("SELECT {pk} FROM {AmazonOrder} WHERE {amazonAuthorizationId} = ?amazonAuthorizationId ");
//		Map<String, Object> parameters = new HashMap<String, Object>();
//		parameters.put(AmazonOrder.AMAZONAUTHORIZATIONID, amazonAuthorizationId);
//		List<AmazonOrder> result = FlexibleSearch.getInstance().search(sql.toString(), parameters, AmazonOrder.class).getResult();
//		return result.size() > 0 ? result.get(0) : null;
//	}
//
//	@SuppressWarnings("unchecked")
//	public AmazonOrder findAmazonOrderByOrderReferenceId(String orderCode) {
//		StringBuilder sql = new StringBuilder();
//		sql.append("SELECT {pk} FROM {AmazonOrder} WHERE {orderCode} = ?orderCode ");
//		Map<String, Object> parameters = new HashMap<String, Object>();
//		parameters.put(AmazonOrder.ORDERCODE, orderCode);
//		List<AmazonOrder> result = FlexibleSearch.getInstance().search(sql.toString(), parameters, AmazonOrder.class).getResult();
//		return result.size() > 0 ? result.get(0) : null;
//	}
//	
//	public List<RefundDetails> getAllRefundDetails(String amazonAuthorizationId) throws AmazonPaymentException {
//		List<RefundDetails> refundDetails = new ArrayList<RefundDetails>();
//		CaptureDetails captureDetails = getCaptureDetails(amazonAuthorizationId);
//		List<String> members = null;
//		if(captureDetails.getIdList() != null && CollectionUtils.isNotEmpty(members = captureDetails.getIdList().getMember())) {
//			for(String member : members) {
//				refundDetails.add(getRefundDetails(amazonAuthorizationId, member));
//			}
//		}
//		return refundDetails;
//	}
//	
//	public RefundDetails getRefundDetails(String amazonAuthorizationId, String amazonRefundId) throws AmazonPaymentException {
//		try {
//			GetRefundDetailsRequest request = new GetRefundDetailsRequest();
//			request.setAmazonRefundId(amazonRefundId);
//			request.setSellerId(getSellerId());
//			GetRefundDetailsResponse response = service.getRefundDetails(request);
//			return response.getGetRefundDetailsResult().getRefundDetails();
//		} catch (OffAmazonPaymentsServiceException e) {
//			throw getAmazonPaymentException(e);
//		}
//	}
//	
//	public void closeOrderIfFullRefundExists(String orderCode) throws AmazonPaymentException {
//		AuthorizationDetails authorizationDetails = findAuthorizationDetailsByOrderCode(orderCode);
//		if(authorizationDetails != null) {
//			List<RefundDetails> allRefundDetails = getAllRefundDetails(authorizationDetails.getAmazonAuthorizationId());
//			RefundDetails refundDetails = allRefundDetails.size() == 1 ? allRefundDetails.get(0) : null;
//			if(refundDetails != null && FULL_REFUND_CHECKOUT_NOTE.equals(refundDetails.getSellerRefundNote()) && refundDetails.getRefundAmount().getAmount().equals(authorizationDetails.getAuthorizationAmount().getAmount()) ) {
//				closeAuthorizationOrder(authorizationDetails.getAmazonAuthorizationId(), FULL_REFUND_CHECKOUT_NOTE);
//			}
//		}
//	}
//
//	public void closeOrderIfFullRefundExists(String orderCode, String closureReason) throws AmazonPaymentException {
//		AuthorizationDetails authorizationDetails = findAuthorizationDetailsByOrderCode(orderCode);
//		if(authorizationDetails != null) {
//			List<RefundDetails> allRefundDetails = getAllRefundDetails(authorizationDetails.getAmazonAuthorizationId());
//			RefundDetails refundDetails = allRefundDetails.size() == 1 ? allRefundDetails.get(0) : null;
//			if(refundDetails != null && FULL_REFUND_CHECKOUT_NOTE.equals(refundDetails.getSellerRefundNote()) && refundDetails.getRefundAmount().getAmount().equals(authorizationDetails.getAuthorizationAmount().getAmount()) ) {
//				closeAuthorizationOrder(authorizationDetails.getAmazonAuthorizationId(), closureReason);
//			}
//		}
//	}
//	
//	private AuthorizationDetails findAuthorizationDetailsByOrderCode(String orderCode) throws AmazonPaymentException {
//		AmazonOrder amazonOrder = findAmazonOrderByOrderReferenceId(orderCode);
//		return amazonOrder == null ? null : getAuthorizationDetails(amazonOrder.getAmazonAuthorizationId());
//	}
//	
//	/* HMC - END */
//
//	public AmazonPaymentException getAmazonPaymentException(OffAmazonPaymentsServiceException e) {
//		AmazonPaymentException amazonPaymentException = new AmazonPaymentException(e);
//		amazonPaymentException.logMessage();
//		return amazonPaymentException;
//	}
//
//	/**
//	 * @param orderCode
//	 * @return AmazonAuthorizationId
//	 */
//	public String getAuthorizationId(String orderCode) {
//		AmazonOrder AmazonOrder = findAmazonOrderByOrderReferenceId(orderCode);
//		return AmazonOrder == null ? null : AmazonOrder.getAmazonAuthorizationId();
//	}
//	
//
//	/**
//	 * @param address
//	 * @param authorizationBillingAddress
//	 */
//	public void updatePaymentAddress(Address address, com.amazonservices.mws.offamazonpayments.model.Address authorizationBillingAddress) {
//		if(authorizationBillingAddress == null) {
//			return;
//		}
//		Map<String, String> map = new HashMap<String, String>();
//		map.put(PARAMETER_ADDRESS_LINE1, StringUtils.isNotBlank(authorizationBillingAddress.getAddressLine1()) ? authorizationBillingAddress.getAddressLine1() : "");
//		map.put(PARAMETER_ADDRESS_LINE2, StringUtils.isNotBlank(authorizationBillingAddress.getAddressLine2()) ? authorizationBillingAddress.getAddressLine2() : "");
//		map.put(PARAMETER_ADDRESS_LINE3, StringUtils.isNotBlank(authorizationBillingAddress.getAddressLine3()) ? authorizationBillingAddress.getAddressLine3() : "");
//		map.put(PARAMETER_ADDRESS_CITY, authorizationBillingAddress.getCity());
//		map.put(PARAMETER_ADDRESS_COUNTRY_CODE, authorizationBillingAddress.getCountryCode());
//		map.put(PARAMETER_ADDRESS_COUNTY, authorizationBillingAddress.getCounty());
//		map.put(PARAMETER_ADDRESS_DISTRICT, authorizationBillingAddress.getDistrict());
//		map.put(PARAMETER_ADDRESS_NAME, authorizationBillingAddress.getName());
//		map.put(PARAMETER_ADDRESS_PHONE, authorizationBillingAddress.getPhone());
//		map.put(PARAMETER_ADDRESS_POSTAL_CODE, authorizationBillingAddress.getPostalCode());
//		map.put(PARAMETER_ADDRESS_STATE_OR_REGION, authorizationBillingAddress.getStateOrRegion());
//		
//		if(StringUtils.isNotBlank(map.get(AmazonPaymentCommand.PARAMETER_ADDRESS_NAME))) {
//			setFirstAndLastName(map.get(AmazonPaymentCommand.PARAMETER_ADDRESS_NAME), address);
//		}
//		
//		String addressLine1 = map.get(AmazonPaymentCommand.PARAMETER_ADDRESS_LINE1);
//		String addressLine2 = map.get(AmazonPaymentCommand.PARAMETER_ADDRESS_LINE2);
//		String addressLine3 = map.get(AmazonPaymentCommand.PARAMETER_ADDRESS_LINE3);
//		
//		if(StringUtils.isNotBlank(addressLine2)) {
//			String lastName = this.<String>getAttribute(address, Address.LASTNAME);
//			if(addressLine1 != null && StringUtils.isNotBlank(addressLine1)) {
//				if(StringUtils.isBlank(lastName) || "User".equals(lastName)) {
//					setAttribute(address, Address.LASTNAME, addressLine1);
//					setAttribute(address, Address.APPARTMENT, "");
//				} else {
//					setAttribute(address, Address.APPARTMENT, addressLine1);
//				}
//					
//			} else {
//				if(StringUtils.isBlank(lastName) || "User".equals(lastName)) {
//					setAttribute(address, Address.LASTNAME, "");
//				}
//				setAttribute(address, Address.APPARTMENT, "");
//			}
//			setAttribute(address, Address.STREETNAME, addressLine2);
//		} else {
//			setAttribute(address, Address.STREETNAME, addressLine1);
//		}
//
//		if(addressLine3 != null && StringUtils.isNotBlank(addressLine3)) {
//			setAttribute(address, Address.COMPANY, addressLine3);
//		}
//		
//		if(StringUtils.isNotBlank(this.<String>getAttribute(address, Address.STREETNAME))) {
////			String regex = "^(.+?)\\s((?:[\\da-z]+(?:\\s*-\\s*[\\da-z]+)?|\\d{1,2}(?:\\s*\\d{2})+))$";
//			String streetName = this.<String>getAttribute(address, Address.STREETNAME);
//			String streetNumber = "";
//			try {
////				if(Pattern.compile(regex).matcher(streetName).matches()) {
//					found : for (int i = streetName.length() - 1; i >= 0; i--) {
//						if(CharUtils.isAsciiNumeric(streetName.charAt(i))) {
//							streetNumber = streetName.charAt(i) + streetNumber;
//						} else if(streetNumber.isEmpty()) {
//							continue found;
//						} else {
//							setAttribute(address, Address.STREETNUMBER, StringUtils.trim(streetNumber));
//							setAttribute(address, Address.STREETNAME, streetName = streetName.replaceAll(streetNumber, ""));
//							break found;
//						}
//					}
////				}
//			} catch (Exception e) {
//				log.error("Error trying to get the street number from street name", e);
//			}
//		}
//		
//		if(StringUtils.isNotBlank(map.get(AmazonPaymentCommand.PARAMETER_BUYER_PHONE))) {
//			setAttribute(address, Address.PHONE1, map.get(AmazonPaymentCommand.PARAMETER_BUYER_PHONE));
//		}
//		if(StringUtils.isNotBlank(map.get(AmazonPaymentCommand.PARAMETER_ADDRESS_CITY))) {
//			setAttribute(address, Address.TOWN, map.get(AmazonPaymentCommand.PARAMETER_ADDRESS_CITY));
//		}
//		if(StringUtils.isNotBlank(map.get(AmazonPaymentCommand.PARAMETER_ADDRESS_POSTAL_CODE))) {
//			setAttribute(address, Address.POSTALCODE, map.get(AmazonPaymentCommand.PARAMETER_ADDRESS_POSTAL_CODE));
//		}
//		
//		try {
//			Country countryByIsoCode = C2LManager.getInstance().getCountryByIsoCode(map.get(AmazonPaymentCommand.PARAMETER_ADDRESS_COUNTRY_CODE).toLowerCase());
//			address.setCountry(countryByIsoCode);
//		} catch (Exception e) {
//			log.error("Error", e);
//		}
//		
//	}
//	
//	public void setFirstAndLastName(String addressName, Address address) {
//		//updating address name
//		if(addressName == null){
//			return;
//		}
//		int indexName = addressName.trim().indexOf(" ");
//		if(indexName > 0) {
//			try {
//				setAttribute(address, Address.FIRSTNAME, addressName.substring(0, indexName));
//				setAttribute(address, Address.LASTNAME, addressName.substring(indexName+1, addressName.length()));
//			} catch (Exception e) {
//				log.error("error with name: " + addressName + " and index number: " + indexName, e);
//				setAttribute(address, Address.FIRSTNAME, addressName);
//				setAttribute(address, Address.LASTNAME, "");
//			}
//		} else {
//			setAttribute(address, Address.FIRSTNAME, addressName);
//		}
//	}
//	
//	private void setAttribute(Item item, String qualifier, Object value) {
//		try {
//			item.setAttribute(qualifier, value);
//		} catch (Exception e) {
//			log.error("Error", e);
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	private <T> T getAttribute(Item item, String qualifier) {
//		try {
//			return (T)item.getAttribute(qualifier);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	/**
//	 * @param amazonOrderReferenceId
//	 * @param amount
//	 * @param sellerAuthorizationNote
//	 * @param code
//	 * @return AmazonAuthorizationId
//	 * @throws AmazonPaymentException 
//	 */
//	public String authorize(String amazonOrderReferenceId, String amount, String sellerAuthorizationNote, int code) throws AmazonPaymentException {
//		try {
//			AuthorizeRequest authorizeRequest = new AuthorizeRequest();
//			authorizeRequest.setSellerId(getSellerId());
//			authorizeRequest.setAuthorizationAmount(getAuthorizationAmount(amount));
//			authorizeRequest.setAuthorizationReferenceId(String.valueOf(System.currentTimeMillis()));
//			authorizeRequest.setAmazonOrderReferenceId(amazonOrderReferenceId);
//			if(code != 3) {
//				authorizeRequest.setSellerAuthorizationNote(sellerAuthorizationNote);
//			}
//			AuthorizeResponse response = service.authorize(authorizeRequest);
//			AuthorizationDetails authorizationDetails = response.getAuthorizeResult().getAuthorizationDetails();
//			
//			checkAuthorizationState: for (int i = 0; !authorizationDetails.getAuthorizationStatus().getState().equals("OPEN"); i++) {
//				try {
//					Thread.sleep(10000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				authorizationDetails = getAuthorizationDetails(authorizationDetails.getAmazonAuthorizationId());
//				if(i == 6) 
//					break checkAuthorizationState;
//			}
//			
//			if(code == 3) {
//				capture(authorizationDetails.getAmazonAuthorizationId(), authorizationDetails.getAuthorizationAmount(), sellerAuthorizationNote);
//			} else {
//				capture(authorizationDetails.getAmazonAuthorizationId(), authorizationDetails.getAuthorizationAmount(), "");
//			}
//			
//			return authorizationDetails.getAmazonAuthorizationId();
//		} catch (OffAmazonPaymentsServiceException e) {
//			throw getAmazonPaymentException(e);
//		}
//	}
//	
//	public RefundDetails getRefundDetails(String refundId) throws AmazonPaymentException {
//		try {
//			GetRefundDetailsRequest request = new GetRefundDetailsRequest();
//			request.setAmazonRefundId(refundId);
//			request.setSellerId(getSellerId());
//			GetRefundDetailsResponse response = service.getRefundDetails(request);
//			RefundDetails refundDetails = response.getGetRefundDetailsResult().getRefundDetails();
//			return refundDetails;
//		} catch (OffAmazonPaymentsServiceException e) {
//			throw getAmazonPaymentException(e); 
//		}
//	}
//	
//	public CaptureResult capture(String amazonAuthorizationId, Price captureAmount, String sellerCaptureNote) throws AmazonPaymentException {
//		try {
//			CaptureRequest request = new CaptureRequest();
//			request.setAmazonAuthorizationId(amazonAuthorizationId);
//			request.setCaptureAmount(captureAmount);
//			request.setCaptureReferenceId("C_" + System.currentTimeMillis());
//			request.setSellerCaptureNote(sellerCaptureNote);
//			request.setSellerId(getSellerId());
//			CaptureResponse capture = service.capture(request);
//			CaptureResult result = capture.getCaptureResult();
//			return result;
//		} catch (OffAmazonPaymentsServiceException e) {
//			throw getAmazonPaymentException(e); 
//		}
//	}
//	
//	public CancelOrderReferenceResponse cancelOrder(final String amazonOrderReferenceId, final String reason) throws AmazonPaymentException
//	{
//		try
//		{
//			final CancelOrderReferenceRequest request = new CancelOrderReferenceRequest();
//			request.setSellerId(getSellerId());
//			request.setCancelationReason(reason);
//			request.setAmazonOrderReferenceId(amazonOrderReferenceId);
//			
//			return service.cancelOrderReference(request);
//		}
//		catch (final OffAmazonPaymentsServiceException e)
//		{
//			throw new AmazonPaymentException(e);
//		}
//	}
//}