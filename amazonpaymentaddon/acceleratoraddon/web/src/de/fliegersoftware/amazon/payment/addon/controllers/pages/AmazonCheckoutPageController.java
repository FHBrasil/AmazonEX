package de.fliegersoftware.amazon.payment.addon.controllers.pages;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.fliegersoftware.amazon.core.data.AmazonOrderReferenceAttributesData;
import de.fliegersoftware.amazon.core.data.AmazonOrderReferenceDetailsData;
import de.fliegersoftware.amazon.core.data.AmazonSellerOrderAttributesData;
import de.fliegersoftware.amazon.core.enums.CaptureModeEnum;
import de.fliegersoftware.amazon.core.services.AmazonConfigService;
import de.fliegersoftware.amazon.payment.addon.controllers.AmazonpaymentaddonControllerConstants;
import de.fliegersoftware.amazon.payment.addon.facades.AmazonCheckoutFacade;
import de.fliegersoftware.amazon.payment.addon.forms.AmazonAjaxResponse;
import de.fliegersoftware.amazon.payment.addon.forms.AmazonPlaceOrderForm;
import de.fliegersoftware.amazon.payment.constants.AmazonpaymentConstants;
import de.fliegersoftware.amazon.payment.services.AmazonPaymentService;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractCheckoutController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.UpdateQuantityForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.InvalidCartException;

@Controller
@RequestMapping("/checkout/amazon")
public class AmazonCheckoutPageController extends AbstractCheckoutController {

	private static final Logger LOG = LoggerFactory.getLogger(AmazonCheckoutPageController.class);
	private static final String AMAZON_CHECKOUT_CMS_PAGE_LABEL = "amazonCheckout";
	private static final String REDIRECT_URL_AMAZON_CHECKOUT = REDIRECT_PREFIX + "/checkout/amazon";
	private static final String REDIRECT_URL_CART = REDIRECT_PREFIX + "/cart";

	@Resource
	private AmazonConfigService amazonConfigService;

	@Resource
	private AmazonPaymentService amazonPaymentService;

	@Resource
	private AmazonCheckoutFacade amazonCheckoutFacade;

	@Resource
	private CartFacade cartFacade;

	@Resource(name="themeSource")
	private MessageSource messageSource;

	@Resource
	private CommerceCartService commerceCartService;

	@Resource(name = "cartService")
	private CartService cartService;

	@RequestMapping(method = RequestMethod.GET)
	@RequireHardLogIn
	public String checkoutPage(final Model model) throws CMSItemNotFoundException {
		// redirects to cart if not ready for checkout
		if (!hasValidCart() && !getCheckoutFacade().hasShippingItems()) {
			return REDIRECT_URL_CART;
		}
		LOG.info("AmazonCheckout - checkoutPage");

		getCheckoutFacade().setDeliveryModeIfAvailable();

		// sets checkout data
		CartData cartData = getCheckoutFacade().getCheckoutCart();
		model.addAttribute("cartData", cartData);
		model.addAttribute("deliveryMethods", getCheckoutFacade().getSupportedDeliveryModes());
		model.addAttribute("amazonPlaceOrderForm", new AmazonPlaceOrderForm());

		//sets form for update cart
		createProductList(model);

		// renders extra controls based on configuration
		model.addAttribute("sandboxSimulate", Boolean.valueOf(amazonConfigService.isSandboxSimulate()));
		model.addAttribute("chargeOnOrder", Boolean.valueOf(CaptureModeEnum.IMMEDIATE.equals(amazonConfigService.getCaptureMode())));

		// sets cms data and pagetype
		storeCmsPageInModel(model, getContentPageForLabelOrId(AMAZON_CHECKOUT_CMS_PAGE_LABEL));
		return AmazonpaymentaddonControllerConstants.Views.Pages.Checkout.AmazonCheckoutPage;
	}

	@RequestMapping(value = "/update-payment-amount", method = RequestMethod.POST)
	public @ResponseBody AmazonAjaxResponse doUpdatePaymentAmmount(@RequestParam("amazonOrderReferenceId") String amazonOrderReferenceId) {
		AmazonAjaxResponse response = new AmazonAjaxResponse();

		return response;
	}

	@RequestMapping(value = "/select-delivery-address", method = RequestMethod.POST
			, produces = MediaType.APPLICATION_JSON_VALUE)
	public String doSelectDeliveryAddress(@RequestParam("amazonOrderReferenceId") String amazonOrderReferenceId
			, @RequestParam("access_token") String accessToken, final Model model) {
		LOG.info("AmazonCheckout - doSelectDeliveryAddress");
		AmazonAjaxResponse response = new AmazonAjaxResponse();
		response.setSuccess(false);
		if (!hasValidCart()) {
			response.setRedirect(REDIRECT_URL_CART);
		}
		if (!getCheckoutFacade().hasShippingItems()) {
			response.setRedirect(REDIRECT_URL_CART);
		}
		if (StringUtils.isEmpty(accessToken)) {
			response.setRedirect(REDIRECT_URL_CART);
		}

		if(!StringUtils.isBlank(amazonOrderReferenceId)) {
			AmazonOrderReferenceDetailsData details = amazonPaymentService.getOrderReferenceDetails(amazonOrderReferenceId, accessToken);

			if (details != null) {
				if (details.getAddressData() == null) {
					getCheckoutFacade().removeDeliveryAddress();
					response.setShowMessage(getLocalizedMessage("amazon.address.select.failed"));
				} else if (!getCheckoutFacade().isDeliveryCountrySupported(details.getAddressData().getCountry())) {
					getCheckoutFacade().removeDeliveryAddress();
					response.setShowMessage(getLocalizedMessage("amazon.address.country.invalid"));
				} else if (!getCheckoutFacade().setDeliveryAddress(details.getAddressData())) {
					getCheckoutFacade().removeDeliveryAddress();
					response.setShowMessage(getLocalizedMessage("amazon.address.packstation.invalid"));
				} else if (!getCheckoutFacade().setDeliveryModeIfAvailable()) {
					getCheckoutFacade().removeDeliveryAddress();
					response.setShowMessage(getLocalizedMessage("amazon.address.select.failed"));
				} else {
					// successful! silent response
					response.setShowMessage(getLocalizedMessage("amazon.address.select.success"));
					model.addAttribute("deliveryMethods", getCheckoutFacade().getSupportedDeliveryModes());
					CartData cartData = getCheckoutFacade().getCheckoutCart();
					model.addAttribute("selectedDeliveryMethodId", cartData.getDeliveryMode().getCode());
					response.setSuccess(true);
				}
			}
			else
			{
				getCheckoutFacade().removeDeliveryAddress();
				response.setShowMessage(getLocalizedMessage("amazon.address.select.failed"));
			}			
			model.addAttribute("cartData", getCheckoutFacade().getCheckoutCart());
		}
		model.addAttribute("amazonAjaxResponse", response);
		return AmazonpaymentaddonControllerConstants.Views.Pages.Checkout.Fragments.AmazonCheckoutOrderDetails;
	}

	/**
	 * @param model
	 * @param selectedDeliveryMethod
	 * @return
	 */
	// @Override
	@RequestMapping(value = "/select-delivery-method", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@RequireHardLogIn
	public String doSelectDeliveryMode(@RequestParam("selectedDeliveryMethod") final String selectedDeliveryMethod, final Model model) {

		AmazonAjaxResponse response = new AmazonAjaxResponse();
		if (!hasValidCart()) {
			response.setRedirect(REDIRECT_URL_CART);
		}
		if (!getCheckoutFacade().hasShippingItems()) {
			response.setRedirect(REDIRECT_URL_CART);
		}
		if (StringUtils.isNotEmpty(selectedDeliveryMethod)) {
			getCheckoutFacade().setDeliveryMode(selectedDeliveryMethod);
			model.addAttribute("cartData", getCheckoutFacade().getCheckoutCart());
			response.setSuccess(true);
		}
		model.addAttribute("amazonAjaxResponse", response);
		return AmazonpaymentaddonControllerConstants.Views.Pages.Checkout.Fragments.AmazonCheckoutOrderDetails;
	}

	@RequestMapping(value = "/select-payment-method", method = RequestMethod.POST)
	public @ResponseBody AmazonAjaxResponse doSelectPaymentMethod(@RequestParam("amazonOrderReferenceId") String amazonOrderReferenceId, final RedirectAttributes model) {
		LOG.info("AmazonCheckout - doSelectDeliveryAddress");
		AmazonAjaxResponse response = new AmazonAjaxResponse();
		if (!hasValidCart()) {
			response.setRedirect(REDIRECT_URL_CART);
		}
		if (!getCheckoutFacade().hasShippingItems()) {
			response.setRedirect(REDIRECT_URL_CART);
		}
		if(!StringUtils.isBlank(amazonOrderReferenceId)) {

		}
		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<Map<String, CartData>> updateCartQuantities(@RequestParam("productCode") final String productCode,
			final Model model, @Valid final UpdateQuantityForm form,
			final BindingResult bindingResult, final HttpServletRequest request,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException {
		Map results = new HashMap();
		long entryNumber = 0;
		if(!productCode.isEmpty())
		{
			for(final OrderEntryData entry : getCheckoutFacade().getCheckoutCart().getEntries())
			{
				if(entry.getProduct().getCode().equals(productCode))
				{
					entryNumber = entry.getEntryNumber();
				}
			}
		}
		if (bindingResult.hasErrors()) {
			for (final ObjectError error : bindingResult.getAllErrors()) {
				if (error.getCode().equals("typeMismatch")) {
					GlobalMessages.addErrorMessage(model, "basket.error.quantity.invalid");
				} else {
					GlobalMessages.addErrorMessage(model, error.getDefaultMessage());
				}
			}
		} else if (cartFacade.getSessionCart().getEntries() != null) {
			try {
				final CartModificationData cartModification =
						cartFacade.updateCartEntry(entryNumber, form.getQuantity().longValue());
				String msgKey = "";
				if (cartModification.getQuantity() == form.getQuantity().longValue()) {
					// Success
					if (cartModification.getQuantity() == 0) {
						// Success in removing entry
						msgKey = "basket.page.message.remove";
						GlobalMessages.addFlashMessage(redirectModel,
								GlobalMessages.CONF_MESSAGES_HOLDER, msgKey);
						results.put("message", getMessageSource().getMessage(msgKey, null, getI18nService().getCurrentLocale()));
					} else {
						// Success in update quantity
						msgKey = "basket.page.message.update";
						GlobalMessages.addFlashMessage(redirectModel,
								GlobalMessages.CONF_MESSAGES_HOLDER, msgKey);
						results.put("message", getMessageSource().getMessage(msgKey, null, getI18nService().getCurrentLocale()));
					}
				} else if (cartModification.getQuantity() > 0) {
					// Less than successful
					Object[] params = new Object[] {
							cartModification.getEntry().getProduct().getName(),
							cartModification.getQuantity(),
							form.getQuantity(),
							request.getRequestURL().append(
									cartModification.getEntry().getProduct().getUrl()) };
					msgKey = "basket.page.message.update.reducedNumberOfItemsAdded.lowStock";
					GlobalMessages.addFlashMessage(
							redirectModel,
							GlobalMessages.ERROR_MESSAGES_HOLDER,
							msgKey,
							params);
					results.put("message", getMessageSource().getMessage(msgKey, params, getI18nService().getCurrentLocale()));
				} else {
					// No more stock available
					Object[] params = new Object[] {
							cartModification.getEntry().getProduct().getName(),
							request.getRequestURL().append(
									cartModification.getEntry().getProduct().getUrl()) };
					msgKey = "basket.page.message.update.reducedNumberOfItemsAdded.noStock";
					GlobalMessages.addFlashMessage(
							redirectModel,
							GlobalMessages.ERROR_MESSAGES_HOLDER,
							msgKey,
							params);
					results.put("message", getMessageSource().getMessage(msgKey, params, getI18nService().getCurrentLocale()));
				}
				// Redirect to the cart page on update success so that the browser doesn't re-post
				// again
				final CartData cartData = getCheckoutFacade().getCheckoutCart();
				results.put("cartData", cartData);
				final String referer = request.getHeader("Referer");
				if (StringUtils.isNotBlank(referer)) {
					return new ResponseEntity(results, HttpStatus.OK);
				}
				return new ResponseEntity(results, HttpStatus.OK);
			} catch (final CommerceCartModificationException ex) {
				LOG.warn("Couldn't update product with the entry number: " + entryNumber + ".", ex);
			}
		}
		return new ResponseEntity(results, HttpStatus.OK);
	}

	protected void createProductList(final Model model) {
		final CartData cartData = getCheckoutFacade().getCheckoutCart();
		boolean hasPickUpCartEntries = false;
		if (cartData.getEntries() != null && !cartData.getEntries().isEmpty()) {
			for (final OrderEntryData entry : cartData.getEntries()) {
				if (!hasPickUpCartEntries && entry.getDeliveryPointOfService() != null) {
					hasPickUpCartEntries = true;
				}
				final UpdateQuantityForm uqf = new UpdateQuantityForm();
				uqf.setQuantity(entry.getQuantity());
				model.addAttribute("updateQuantityForm" + entry.getProduct().getCode(), uqf);
			}
		}
	}

	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	@RequireHardLogIn
	public String placeOrder(@RequestHeader(value = "referer", required = false) final String referer
			, final Model model, final AmazonPlaceOrderForm amazonPlaceOrderForm
			, final RedirectAttributes redirectModel) throws CMSItemNotFoundException {
		LOG.info("AmazonCheckout - placeOrder");
		CartData cartData = null;
		if(getCheckoutCustomerStrategy().isAnonymousCheckout()
				&& !CustomerType.GUEST.equals(getCheckoutCustomerStrategy().getCurrentUserForCheckout().getType())) {
			// XXX: temporary set user type as guest
			CustomerType userType = getCheckoutCustomerStrategy().getCurrentUserForCheckout().getType();
			try {
				getCheckoutCustomerStrategy().getCurrentUserForCheckout().setType(CustomerType.GUEST);
				cartData = getCheckoutFacade().getCheckoutCart();
				if (validateCart(redirectModel)) {
					return REDIRECT_URL_CART;
				}
			} finally {
				getCheckoutCustomerStrategy().getCurrentUserForCheckout().setType(userType);
			}
		} else {
			if (validateCart(redirectModel)) {
				return REDIRECT_URL_CART;
			}
		}
		if (cartData == null) {
			cartData = getCheckoutFacade().getCheckoutCart();
		}
		if (cartData.getDeliveryAddress() == null) {
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, "amazon.address.select.failed");
			if(StringUtils.isBlank(referer))
				return REDIRECT_URL_CART;
			else
				return REDIRECT_PREFIX + referer;
		}

		if(getCheckoutFacade().setPaymentDetails(amazonPlaceOrderForm.getAmazonOrderReferenceId())) {
			AmazonOrderReferenceDetailsData details = amazonPaymentService.getOrderReferenceDetails(amazonPlaceOrderForm.getAmazonOrderReferenceId(), null);
			if (StringUtils.equalsIgnoreCase(details.getAmazonOrderReferenceStatus(), "Draft")) {
				String orderCode = getCheckoutFacade().createOrderCodeFromCart();
				AmazonOrderReferenceAttributesData orderReferenceAttributesData = new AmazonOrderReferenceAttributesData();
				orderReferenceAttributesData.setOrderTotal(cartData.getTotalPrice());
				AmazonSellerOrderAttributesData sellerOrderAttributes = new AmazonSellerOrderAttributesData();
				sellerOrderAttributes.setSellerOrderId(orderCode);
				orderReferenceAttributesData.setSellerOrderAttributes(sellerOrderAttributes);
			
				amazonPaymentService.setOrderReferenceDetails(amazonPlaceOrderForm.getAmazonOrderReferenceId(), orderReferenceAttributesData);
			} else {
				getCheckoutFacade().setSellerOrderId(details.getSellerOrderId());
			}
			amazonPaymentService.confirmOrderReference(amazonPlaceOrderForm.getAmazonOrderReferenceId());
			if(getCheckoutFacade().authorizePayment(null)) {
				LOG.info("AmazonCheckout - payment ok");
				try {
					OrderData orderData = getCheckoutFacade().placeOrder();
					return redirectToOrderConfirmationPage(orderData);
				} catch (InvalidCartException e) {
					LOG.error("Failed to place Order", e);
					GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, "checkout.placeOrder.failed");
					return REDIRECT_URL_AMAZON_CHECKOUT;
				}
			} else {
				LOG.info("AmazonCheckout - payment failed");
				GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, getAmazonErrorMessage());
				if (getSessionService().getAttribute(AmazonpaymentConstants.AMAZON_ERROR_CODE).equals("AmazonRejected") || 
						getSessionService().getAttribute(AmazonpaymentConstants.AMAZON_ERROR_CODE).equals("TransactionTimedOut")) {
					//quando retornar este erro fazer o logout da amazon em amazonpaymentaddon.js
					return REDIRECT_URL_CART;
				}
			}
		}
		model.addAttribute("amazonOrderReferenceId", amazonPlaceOrderForm.getAmazonOrderReferenceId());
		return checkoutPage(model);
	}
	
	protected String redirectToOrderConfirmationPage(final OrderData orderData)
	{
		return REDIRECT_URL_ORDER_CONFIRMATION
				+ (getCheckoutCustomerStrategy().isAnonymousCheckout() ? orderData.getGuid() + "?amazonLogout=true" : orderData.getCode());
	}

	protected String getLocalizedMessage(String code, Object... args) {
		Locale locale = LocaleContextHolder.getLocale();
		try {
			return messageSource.getMessage(code, args, locale);
		} catch (NoSuchMessageException e) {
			// do nothing
		}
		return code;
	}

	protected String getAmazonErrorMessage() {
		String prefix = "amazon.authorization.error.";
		String amazonErrorCode = getSessionService().getAttribute(AmazonpaymentConstants.AMAZON_ERROR_CODE);
		if(!StringUtils.isEmpty(amazonErrorCode)) {
			amazonErrorCode = prefix + amazonErrorCode;
			String message = getLocalizedMessage(amazonErrorCode);
			if(!StringUtils.isEmpty(message) && !amazonErrorCode.equals(message))
				return amazonErrorCode;
		}
		return "amazon.authorization.error.no.description";
	}

	@Override
	protected AmazonCheckoutFacade getCheckoutFacade() {
		return amazonCheckoutFacade;
	}
	
	/**
	 * Checks if there are any items in the cart.
	 * 
	 * @return returns true if items found in cart.
	 */
	protected boolean hasValidCart()
	{
		final CartData cartData = getCheckoutFlowFacade().getCheckoutCart();
		final boolean validCart = cartData.getEntries() != null && !cartData.getEntries().isEmpty();

		return validCart;
	}
}