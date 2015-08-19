package de.fliegersoftware.amazon.payment.addon.controllers.pages;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.fliegersoftware.amazon.core.data.AmazonOrderReferenceAttributesData;
import de.fliegersoftware.amazon.core.data.AmazonOrderReferenceDetailsData;
import de.fliegersoftware.amazon.core.data.AmazonSellerOrderAttributesData;
import de.fliegersoftware.amazon.payment.addon.controllers.AmazonpaymentaddonControllerConstants;
import de.fliegersoftware.amazon.payment.addon.facades.AmazonCheckoutFacade;
import de.fliegersoftware.amazon.payment.addon.facades.customer.AmazonCustomerFacade;
import de.fliegersoftware.amazon.payment.addon.forms.AmazonPlaceOrderForm;
import de.fliegersoftware.amazon.payment.constants.AmazonpaymentConstants;
import de.fliegersoftware.amazon.payment.services.AmazonPaymentService;
import de.hybris.platform.acceleratorservices.controllers.page.PageType;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractCheckoutController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.user.UserFacade;
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
	private AmazonPaymentService amazonPaymentService;

	@Resource
	private AmazonCheckoutFacade amazonCheckoutFacade;

	@Resource
	private AmazonCustomerFacade amazonCustomerFacade;

	@RequestMapping(method = RequestMethod.GET)
	public String checkoutPage(final Model model) throws CMSItemNotFoundException {
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

		// renders extra controls based on configuration
		model.addAttribute("sandboxMode", Config.getBoolean(AmazonpaymentConstants.SANDBOX_MODE_CONFIG, false));
		model.addAttribute("chargeOnOrder", Config.getBoolean(AmazonpaymentConstants.CHARGE_ON_ORDER_CONFIG, false));

		// sets cms data and pagetype
		storeCmsPageInModel(model, getContentPageForLabelOrId(AMAZON_CHECKOUT_CMS_PAGE_LABEL));
		model.addAttribute("pageType", PageType.CHECKOUTPAGE.name());
		return AmazonpaymentaddonControllerConstants.Views.Pages.Checkout.AmazonCheckoutPage;
	}

	@RequestMapping(value = "/update-payment-amount", method = RequestMethod.POST)
	public String doUpdatePaymentAmmount(@RequestParam("amazonOrderReferenceId") String amazonOrderReferenceId) {
		
		return REDIRECT_URL_AMAZON_CHECKOUT;
	}

	@RequestMapping(value = "/select-delivery-address", method = RequestMethod.POST)
	public String doSelectDeliveryAddress(@RequestParam("amazonOrderReferenceId") String amazonOrderReferenceId, final RedirectAttributes model) {
		LOG.info("AmazonCheckout - doSelectDeliveryAddress");
		if (!hasValidCart()) {
			return REDIRECT_URL_CART;
		}
		if (!getCheckoutFacade().hasShippingItems()) {
			return REDIRECT_URL_CART;
		}
		if(!StringUtils.isBlank(amazonOrderReferenceId)) {
			AmazonOrderReferenceDetailsData details = amazonPaymentService.getOrderReferenceDetails(amazonOrderReferenceId, null);

			if(getCheckoutFacade().setDeliveryAddress(details.getAddressData())) {
				GlobalMessages.addInfoMessage(model, "amazonpaymentaddon.address.select-success");
			} else {
				GlobalMessages.addErrorMessage(model, "amazonpaymentaddon.address.select-failed");
			}
		}
		return REDIRECT_URL_AMAZON_CHECKOUT;
	}

	@RequestMapping(value = "/select-payment-method", method = RequestMethod.POST)
	public String doSelectPaymentMethod(@RequestParam("amazonOrderReferenceId") String amazonOrderReferenceId, final RedirectAttributes model) {
		LOG.info("AmazonCheckout - doSelectDeliveryAddress");
		if (!hasValidCart()) {
			return REDIRECT_URL_CART;
		}
		if (!getCheckoutFacade().hasShippingItems()) {
			return REDIRECT_URL_CART;
		}
		if(!StringUtils.isBlank(amazonOrderReferenceId)) {
			
		}
		return REDIRECT_URL_AMAZON_CHECKOUT;
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
					GlobalMessages.addErrorMessage(redirectModel, "checkout.placeOrder.failed");
					return REDIRECT_URL_AMAZON_CHECKOUT;
				}
				return redirectToOrderConfirmationPage(orderData);
			} else {
				LOG.info("AmazonCheckout - payment failed");
			}
		}
		return REDIRECT_URL_AMAZON_CHECKOUT;
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