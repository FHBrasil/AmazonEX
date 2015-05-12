package com.paypal.hybris.facades;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import br.hering.core.enums.TipoDeEndereco;

import com.paypal.hybris.config.PaypalConfigManager;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.exception.PaypalException;
import com.paypal.hybris.service.PaypalService;
import com.paypal.hybris.soap.gen.AddressType;
import com.paypal.hybris.soap.gen.PaymentActionCodeType;
import com.paypal.hybris.soap.gen.PaymentInfoType;
import com.paypal.hybris.soap.gen.PersonNameType;
import com.paypal.hybris.soap.params.impl.DoExpressCheckoutPaymentParams;
import com.paypal.hybris.soap.params.impl.GetExpressCheckoutDetailsParams;
import com.paypal.hybris.soap.params.impl.SetExpressCheckoutParams;
import com.paypal.hybris.util.StrUtil;

import de.hybris.platform.acceleratorservices.enums.UiExperienceLevel;
import de.hybris.platform.acceleratorservices.uiexperience.UiExperienceService;
import de.hybris.platform.commercefacades.i18n.I18NFacade;
import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.core.model.order.payment.PaypalPaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.order.Cart;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.services.BaseStoreService;

/**
 * Provides additional methods and helpers for using PayPal as a payment
 * provider.
 * 
 * @author Valentyn Markovych, Gorilla
 * 
 */
public abstract class PaypalFacade {

	@SuppressWarnings("unused")
	private final Logger LOG = Logger.getLogger(PaypalFacade.class);

	@Resource(name = "paypalService")
	private PaypalService paypalService;

	@Resource
	private ModelService modelService;

	@Resource
	private UserService userService;

	@Resource
	private CustomerAccountService customerAccountService;

	@Resource
	private BaseStoreService baseStoreService;

	@Resource(name = "calculationService")
	private CalculationService calculationService;

	@Resource(name = "flexibleSearchService")
	private FlexibleSearchService flexibleSearchService;

	@Resource
	private UiExperienceService uiExperienceService;
	
	@Resource
	private PaypalConfigManager paypalConfigManager;

	@Resource(name = "i18NFacade")
	private I18NFacade i18NFacade;
	
	protected UiExperienceService getUiExperienceService() {

		return uiExperienceService;
	}

	public void setUiExperienceService(
			final UiExperienceService uiExperienceService) {

		this.uiExperienceService = uiExperienceService;
	}

	public PaypalFacade() {

	}

	public void putToSession(final String key, final Object value) {

		// INFO maybe hybris 5 incompatible
		final JaloSession jaloSession = JaloSession.getCurrentSession();
		jaloSession.setAttribute(key, value);
	}

	public Object getFromSession(final String key) {

		// INFO maybe hybris 5 incompatible
		final JaloSession jaloSession = JaloSession.getCurrentSession();
		return jaloSession.getAttribute(key);
	}

	/**
	 * Adds fictive CCPaymentInfoData so frontend can use it's usual logic to
	 * render payment information.
	 * 
	 * Sets an accountHolderName as 'PayPal' to flag that this payment info is
	 * not credit card, but PayPal payment info.
	 * 
	 * @param orderData
	 */
	public void supplementOrder(final OrderData orderData) {

		CCPaymentInfoData ccPaymentInfoData = orderData.getPaymentInfo();
		if (ccPaymentInfoData == null) {
			// Check if we have another types of PaymentInfo using accelerator
			// services
			final OrderModel orderModel = customerAccountService
					.getOrderForCode(
							(CustomerModel) userService.getCurrentUser(),
							orderData.getCode(),
							baseStoreService.getCurrentBaseStore());

			final PaymentInfoModel paymentInfoModel = orderModel
					.getPaymentInfo();

			// Check if it's PayPal payment info
			if (paymentInfoModel != null
					&& (paymentInfoModel instanceof PaypalPaymentInfoModel)) {
				// Then create temporary CCPaymentInfoData with a flag for the
				// front-end
				ccPaymentInfoData = new CCPaymentInfoData();
				ccPaymentInfoData
						.setAccountHolderName(PaypalConstants.PAYMENT_PROVIDER_NAME);
			}
			orderData.setPaymentInfo(ccPaymentInfoData);
		}
	}

	public String paypalPreparePayment(final SetExpressCheckoutParams params)
			throws PaypalException {

		final CartModel cartModel = getCurrentCart();

		// Perform SetExpressCheckout call to PayPal
		paypalService.setExpressCheckout(params);
		if (params.getAck() != null
				&& !params.getAck().equals(PaypalConstants.STATUS_FAILURE)) {
			putToSession(PaypalConstants.PAYPAL_DISABLE_BUTTON, true);
		} else {
			putToSession(PaypalConstants.PAYPAL_DISABLE_BUTTON, false);
			throw new PaypalException("paypal.setExpressCheckout.failure");
		}

		// Store token
		final PaymentInfoModel paymentInfoModel = cartModel.getPaymentInfo();
		final PaypalPaymentInfoModel paypalPaymentInfoModel;
		if (paymentInfoModel == null
				|| !(paymentInfoModel instanceof PaypalPaymentInfoModel)) {
			paypalPaymentInfoModel = modelService
					.create(PaypalPaymentInfoModel.class);
			paypalPaymentInfoModel
					.setCode(PaypalConstants.PAYMENT_PROVIDER_NAME + '_'
							+ System.currentTimeMillis());
			final User currentUser = JaloSession.getCurrentSession().getUser();
			final UserModel userModel = modelService.get(currentUser);
			paypalPaymentInfoModel.setUser(userModel);
		} else {
			paypalPaymentInfoModel = (PaypalPaymentInfoModel) paymentInfoModel;
		}
		paypalPaymentInfoModel.setToken(params.getResponse().getToken());
		cartModel.setPaymentInfo(paypalPaymentInfoModel);
		modelService.save(paypalPaymentInfoModel);
		modelService.save(cartModel);

		String redirectUrlString = null;
		final UiExperienceLevel uiExperienceLevel = getUiExperienceLevel();
		if (uiExperienceLevel != null
				&& UiExperienceLevel.MOBILE.equals(uiExperienceLevel)) {
			redirectUrlString = PaypalConstants.SETT_REDIRECT_URL_MOBILE;
		} else {
			redirectUrlString = PaypalConstants.SETT_REDIRECT_URL_DESKTOP;
		}
		final StringBuffer redirectUrl = new StringBuffer();
		redirectUrl.append("redirect:");
		redirectUrl.append(paypalConfigManager.getProperty(redirectUrlString));
		final String token = params.getResponse().getToken();
		redirectUrl.append(token);

		return redirectUrl.toString();
	}

	public GetExpressCheckoutDetailsParams getExpressCheckoutDetails() {

		final CartModel cartModel = getCurrentCart();
		final PaypalPaymentInfoModel paypalPaymentInfo = (PaypalPaymentInfoModel) cartModel
				.getPaymentInfo();
		final String token = paypalPaymentInfo.getToken();

		// GetExpressCheckoutDetails
		final GetExpressCheckoutDetailsParams getParams = new GetExpressCheckoutDetailsParams();
		getParams.setToken(token);
		paypalService.getExpressCheckoutDetails(getParams);
		final String payerId = getParams.getResponse()
				.getGetExpressCheckoutDetailsResponseDetails().getPayerInfo()
				.getPayerID();
		paypalPaymentInfo.setPayerId(payerId);
		modelService.save(paypalPaymentInfo);
		modelService.save(getCurrentCart());

		if (payerId == null) {
			putToSession(PaypalConstants.PAYPAL_DISABLE_BUTTON, false);
		}
		return getParams;
	}

	// public DoExpressCheckoutPaymentParams doPayment(final CartData cartData,
	// final PaymentInfoModel paymentInfoModel) throws CalculationException {
	//
	// final DoExpressCheckoutPaymentParams doParams = new
	// DoExpressCheckoutPaymentParams();
	// doParams.setToken(((PaypalPaymentInfoModel)
	// paymentInfoModel).getToken());
	// final BasicAmountType orderTotal = new BasicAmountType();
	// orderTotal
	// .setValue(StrUtil.formatNumber(cartData.getTotalPrice().getValue()));
	// final String currencyCode = cartData.getTotalPrice().getCurrencyIso();
	// orderTotal.setCurrencyID(CurrencyCodeType.valueOf(currencyCode));
	// doParams.setOrderTotal(orderTotal);
	// doParams.setPaymentAction(PaymentActionCodeType.ORDER);
	// doParams.setPayerId(((PaypalPaymentInfoModel)
	// paymentInfoModel).getPayerId());
	// paypalService.doExpressCheckoutPayment(doParams);
	// //setPaymentInfo(doParams);
	// return doParams;
	// }

	public DoExpressCheckoutPaymentParams doPayment(final CartModel cart,
			final PaymentInfoModel paymentInfoModel)
			throws CalculationException {

		final DoExpressCheckoutPaymentParams doParams = new DoExpressCheckoutPaymentParams();
		doParams.setParamsFromCart(cart);
		doParams.setToken(((PaypalPaymentInfoModel) paymentInfoModel)
				.getToken());
		doParams.setPaymentAction(PaymentActionCodeType.ORDER);
		doParams.setPayerId(((PaypalPaymentInfoModel) paymentInfoModel)
				.getPayerId());
		paypalService.doExpressCheckoutPayment(doParams);
		return doParams;
	}

	public void setPaymentInfo(final DoExpressCheckoutPaymentParams doParams)
			throws CalculationException {

		final CartModel cartModel = getCurrentCart();
		final List<PaymentInfoType> resPaymentInfo = doParams.getResponse()
				.getDoExpressCheckoutPaymentResponseDetails().getPaymentInfo();
		((PaypalPaymentInfoModel) cartModel.getPaymentInfo())
				.setTransactionId(resPaymentInfo.get(0).getTransactionID());

		// TransactionEntry
		final PaymentTransactionEntryModel paymentTransactionEntry = modelService
				.create(PaymentTransactionEntryModel.class);
		paymentTransactionEntry.setType(PaymentTransactionType.AUTHORIZATION);
		paymentTransactionEntry.setCode(PaypalConstants.PAYMENT_PROVIDER_NAME
				+ "_cart_" + cartModel.getCode() + "_stamp_"
				+ System.currentTimeMillis());

		final String currencyId = doParams.getDetails().getOrderTotal()
				.getCurrencyID().value();
		CurrencyModel currencyModel = new CurrencyModel();
		currencyModel.setIsocode(currencyId);
		currencyModel = flexibleSearchService.getModelByExample(currencyModel);

		paymentTransactionEntry.setCurrency(currencyModel);
		paymentTransactionEntry.setAmount(StrUtil.toBigDecimal(doParams
				.getDetails().getOrderTotal().getValue()));

		final List<PaymentTransactionEntryModel> paymentTransactionEcntries = new ArrayList<PaymentTransactionEntryModel>();
		paymentTransactionEcntries.add(paymentTransactionEntry);

		// Transaction
		final PaymentTransactionModel paymentTransaction = modelService
				.create(PaymentTransactionModel.class);
		paymentTransaction.setEntries(paymentTransactionEcntries);
		paymentTransaction.setRequestId(((PaypalPaymentInfoModel) cartModel
				.getPaymentInfo()).getTransactionId());
		paymentTransaction.setRequestToken(((PaypalPaymentInfoModel) cartModel
				.getPaymentInfo()).getToken());
		paymentTransaction
				.setPaymentProvider(PaypalConstants.PAYMENT_PROVIDER_NAME);

		final List<PaymentTransactionModel> paymentTransactions = new ArrayList<PaymentTransactionModel>();
		paymentTransactions.add(paymentTransaction);

		cartModel.setPaymentTransactions(paymentTransactions);

		PaymentModeModel paymentMode = new PaymentModeModel();
		paymentMode.setCode(PaypalConstants.PAYMENT_MODE_CODE);
		paymentMode = flexibleSearchService.getModelByExample(paymentMode);
		cartModel.setPaymentMode(paymentMode);

		modelService.save(cartModel);
		calculationService.calculate(cartModel);
		modelService.refresh(cartModel);
	}

	public CartModel getCurrentCart() {

		final JaloSession session = JaloSession.getCurrentSession();
		final Cart cart = session.getCart();
		return modelService.get(cart);
	}

	public AddressData convertAddress(
			final GetExpressCheckoutDetailsParams params,
			final CheckoutFacade checkoutFacade) {
		
		final AddressType addressType = params.getResponse()
				.getGetExpressCheckoutDetailsResponseDetails()
				.getPaymentDetails().get(0).getShipToAddress();
		
		final AddressData addressData = new AddressData();
		addressData.setId(addressType.getAddressID());
		
		PersonNameType payerName = params.getResponse().getGetExpressCheckoutDetailsResponseDetails().getPayerInfo().getPayerName();
		addressData.setFirstName(payerName.getFirstName());
		addressData.setLastName(payerName.getLastName());

		addressData.setLine1(addressType.getStreet1());
		addressData.setTown(addressType.getCityName());
		addressData.setPostalCode(addressType.getPostalCode());

		final CountryData countryData = checkoutFacade.getCountryForIsocode(addressType.getCountry().name());
		addressData.setCountry(countryData);

		addressData.setShippingAddress(true);
		addressData.setBillingAddress(false);
		
		//XXX PAYPAL especifico da hering
		addressData.setRegion(i18NFacade.getRegion(countryData.getIsocode(), countryData.getIsocode() + "-" + addressType.getStateOrProvince())); //TODO melhorar o parsing
		addressData.setDistrict(addressType.getStreet2());
		addressData.setPhone(addressType.getPhone());
		addressData.setPhone("98277666");//TODO temporario
		addressData.setType(TipoDeEndereco.RESIDENCIAL);
		addressData.setReceiver(addressType.getName());

		return addressData;
	}

	public void setPaymentInfo(final CartData cartData) {

		CCPaymentInfoData ccPaymentInfo = cartData.getPaymentInfo();
		if (ccPaymentInfo == null) {
			final CartModel cartModel = getCurrentCart();

			// Check if it's PayPal payment
			if (cartModel.getPaymentInfo() instanceof PaypalPaymentInfoModel) {
				// Create CCPaymentInfo data and set accountHolderName as a flag
				ccPaymentInfo = new CCPaymentInfoData();
				ccPaymentInfo.setAccountHolderName("PayPal");
			}
		}
		cartData.setPaymentInfo(ccPaymentInfo);
	}

	public UiExperienceLevel getUiExperienceLevel() {

		return getUiExperienceService().getUiExperienceLevel();
	}

	/**
	 * Deletes paymentInfo from current cart, but only if it was of type
	 * PaypalPaymentInfo. Useful on payment errors.
	 */
	public void resetPaypalPaymentInfo() {

		// Delete payment info from cart if it was PayPal payment info
		final CartModel cartModel = getCurrentCart();
		if (cartModel.getPaymentInfo() instanceof PaypalPaymentInfoModel) {
			cartModel.setPaymentInfo(null);
		}
		modelService.save(cartModel);
	}

	public abstract SetExpressCheckoutParams createExpressCheckoutParams();
}
