package de.fliegersoftware.amazon.payment.addon.controllers.pages;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
import de.fliegersoftware.amazon.payment.addon.facades.customer.AmazonCustomerFacade;
import de.fliegersoftware.amazon.payment.addon.forms.AmazonAjaxResponse;
import de.fliegersoftware.amazon.payment.addon.forms.AmazonPlaceOrderForm;
import de.fliegersoftware.amazon.payment.constants.AmazonpaymentConstants;
import de.fliegersoftware.amazon.payment.services.AmazonPaymentService;
import de.hybris.platform.acceleratorservices.controllers.page.PageType;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractCheckoutController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.UpdateQuantityForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.util.Config;

@Controller
@RequestMapping("/checkout/amazon")
public class AmazonCheckoutPageController extends AbstractCheckoutController {

	private static final Logger LOG = Logger.getLogger(AmazonCheckoutPageController.class);
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
	private AmazonCustomerFacade amazonCustomerFacade;
	
	@Resource
	private CartFacade cartFacade;

	@Resource(name="themeSource")
    private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public String checkoutPage(final Model model) throws CMSItemNotFoundException {
		// redirects to cart if not ready for checkout
		if (!hasValidCart() && !getCheckoutFacade().hasShippingItems()) {
			return REDIRECT_URL_CART;
		}
		LOG.info("AmazonCheckout - checkoutPage");

		if(getCheckoutCustomerStrategy().isAnonymousCheckout()
				&& !Boolean.TRUE.equals(getSessionService().getAttribute(WebConstants.ANONYMOUS_CHECKOUT))) {
			model.addAttribute("sendGuestInformation", Boolean.TRUE);
		}

		// sets checkout data
		CartData cartData = getCheckoutFacade().getCheckoutCart();
		model.addAttribute("cartData", cartData);
		model.addAttribute("deliveryMethods", getCheckoutFacade().getSupportedDeliveryModes());
		model.addAttribute("amazonPlaceOrderForm", new AmazonPlaceOrderForm());
		
		//sets form for update cart
		createProductList(model);

		// renders extra controls based on configuration
		model.addAttribute("sandboxMode", Boolean.valueOf(amazonConfigService.isSandboxMode()));
		model.addAttribute("chargeOnOrder", Boolean.valueOf(CaptureModeEnum.IMMEDIATE.equals(amazonConfigService.getCaptureMode())));

		// sets cms data and pagetype
		storeCmsPageInModel(model, getContentPageForLabelOrId(AMAZON_CHECKOUT_CMS_PAGE_LABEL));
		model.addAttribute("pageType", PageType.CHECKOUTPAGE.name());
		return AmazonpaymentaddonControllerConstants.Views.Pages.Checkout.AmazonCheckoutPage;
	}

	@RequestMapping(value = "/update-payment-amount", method = RequestMethod.POST)
	public @ResponseBody AmazonAjaxResponse doUpdatePaymentAmmount(@RequestParam("amazonOrderReferenceId") String amazonOrderReferenceId) {
		AmazonAjaxResponse response = new AmazonAjaxResponse();

		return response;
	}

	@RequestMapping(value = "/select-delivery-address", method = RequestMethod.POST
			, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AmazonAjaxResponse doSelectDeliveryAddress(@RequestParam("amazonOrderReferenceId") String amazonOrderReferenceId, final RedirectAttributes model) {
		LOG.info("AmazonCheckout - doSelectDeliveryAddress");
		AmazonAjaxResponse response = new AmazonAjaxResponse();
		if (!hasValidCart()) {
			response.setRedirect(REDIRECT_URL_CART);
		}
		if (!getCheckoutFacade().hasShippingItems()) {
			response.setRedirect(REDIRECT_URL_CART);
		}
		if(!StringUtils.isBlank(amazonOrderReferenceId)) {
			AmazonOrderReferenceDetailsData details = amazonPaymentService.getOrderReferenceDetails(amazonOrderReferenceId, null);

			if(getCheckoutFacade().setDeliveryAddress(details.getAddressData()) //
				&& getCheckoutFacade().setDeliveryModeIfAvailable()) {
				// silent response
				// response.setShowMessage(getLocalizedMessage("amazon.address.select.success"));
			} else {
				response.setShowMessage(getLocalizedMessage("amazon.address.select.failed"));
			}
		}
		return response;
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
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateCartQuantities(@RequestParam("entryNumber") final long entryNumber,
            final Model model, @Valid final UpdateQuantityForm form,
            final BindingResult bindingResult, final HttpServletRequest request,
            final RedirectAttributes redirectModel) throws CMSItemNotFoundException {
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
                if (cartModification.getQuantity() == form.getQuantity().longValue()) {
                    // Success
                    if (cartModification.getQuantity() == 0) {
                        // Success in removing entry
                        GlobalMessages.addFlashMessage(redirectModel,
                                GlobalMessages.CONF_MESSAGES_HOLDER, "basket.page.message.remove");
                    } else {
                        // Success in update quantity
                        GlobalMessages.addFlashMessage(redirectModel,
                                GlobalMessages.CONF_MESSAGES_HOLDER, "basket.page.message.update");
                    }
                } else if (cartModification.getQuantity() > 0) {
                    // Less than successful
                    GlobalMessages.addFlashMessage(
                            redirectModel,
                            GlobalMessages.ERROR_MESSAGES_HOLDER,
                            "basket.page.message.update.reducedNumberOfItemsAdded.lowStock",
                            new Object[] {
                                    cartModification.getEntry().getProduct().getName(),
                                    cartModification.getQuantity(),
                                    form.getQuantity(),
                                    request.getRequestURL().append(
                                            cartModification.getEntry().getProduct().getUrl()) });
                } else {
                    // No more stock available
                    GlobalMessages.addFlashMessage(
                            redirectModel,
                            GlobalMessages.ERROR_MESSAGES_HOLDER,
                            "basket.page.message.update.reducedNumberOfItemsAdded.noStock",
                            new Object[] {
                                    cartModification.getEntry().getProduct().getName(),
                                    request.getRequestURL().append(
                                            cartModification.getEntry().getProduct().getUrl()) });
                }
                // Redirect to the cart page on update success so that the browser doesn't re-post
                // again
                final String referer = request.getHeader("Referer");
                if (StringUtils.isNotBlank(referer)) {
                	return REDIRECT_URL_AMAZON_CHECKOUT;
                }
                return REDIRECT_URL_AMAZON_CHECKOUT;
            } catch (final CommerceCartModificationException ex) {
                LOG.warn("Couldn't update product with the entry number: " + entryNumber + ".", ex);
            }
        }
        prepareDataForPage(model);
        return REDIRECT_URL_AMAZON_CHECKOUT;
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
                model.addAttribute("updateQuantityForm" + entry.getEntryNumber(), uqf);
            }
        }
    }
	
	protected void prepareDataForPage(final Model model) throws CMSItemNotFoundException
	{
		model.addAttribute("isOmsEnabled", Boolean.valueOf(getSiteConfigService().getBoolean("oms.enabled", false)));
		model.addAttribute("supportedCountries", getCheckoutFacade().getDeliveryCountries());
		model.addAttribute("expressCheckoutAllowed", Boolean.valueOf(getCheckoutFacade().isExpressCheckoutAllowedForCart()));
		model.addAttribute("taxEstimationEnabled", Boolean.valueOf(getCheckoutFacade().isTaxEstimationEnabledForCart()));

		model.addAttribute("regions", getI18NFacade().getRegionsForCountryIso("DE"));
	}

	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	@RequireHardLogIn
	public String placeOrder(final Model model, final AmazonPlaceOrderForm amazonPlaceOrderForm
			, final RedirectAttributes redirectModel) {
		LOG.info("AmazonCheckout - placeOrder");
		if(getCheckoutFacade().setPaymentDetails(amazonPlaceOrderForm.getAmazonOrderReferenceId())) {
			CartData cartData = getCheckoutFacade().getCheckoutCart();
			OrderData orderData = getCheckoutFacade().createOrderFromCart();
			AmazonOrderReferenceAttributesData orderReferenceAttributesData = new AmazonOrderReferenceAttributesData();
			orderReferenceAttributesData.setOrderTotal(cartData.getTotalPrice());
			AmazonSellerOrderAttributesData sellerOrderAttributes = new AmazonSellerOrderAttributesData();
			sellerOrderAttributes.setSellerOrderId(orderData.getCode());
			orderReferenceAttributesData.setSellerOrderAttributes(sellerOrderAttributes);
			amazonPaymentService.setOrderReferenceDetails(amazonPlaceOrderForm.getAmazonOrderReferenceId(), orderReferenceAttributesData);
			amazonPaymentService.confirmOrderReference(amazonPlaceOrderForm.getAmazonOrderReferenceId());
			if(getCheckoutFacade().authorizePayment(null)) {
				LOG.info("AmazonCheckout - payment ok");
				try {
					orderData = getCheckoutFacade().placeOrder();
				} catch (InvalidCartException e) {
					LOG.error("Failed to place Order", e);
					GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, "checkout.placeOrder.failed");
					return REDIRECT_URL_AMAZON_CHECKOUT;
				}
				return redirectToOrderConfirmationPage(orderData);
			} else {
				LOG.info("AmazonCheckout - payment failed");
				GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, getAmazonErrorMessage());
			}
		}
		return REDIRECT_URL_AMAZON_CHECKOUT;
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

	@Override
	protected AmazonCustomerFacade getCustomerFacade() {
		return (AmazonCustomerFacade) super.getCustomerFacade();
	}
}