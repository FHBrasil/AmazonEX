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
import de.fliegersoftware.amazon.payment.addon.controllers.AmazonpaymentaddonControllerConstants;
import de.fliegersoftware.amazon.payment.addon.facades.AmazonCheckoutFacade;
import de.fliegersoftware.amazon.payment.addon.form.AmazonPlaceOrderForm;
import de.fliegersoftware.amazon.payment.services.AmazonPaymentService;
import de.hybris.platform.acceleratorservices.controllers.page.PageType;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractCheckoutController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.order.data.CartData;

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

	@RequestMapping(method = RequestMethod.GET)
	public String checkoutPage(final Model model) throws CMSItemNotFoundException {
		LOG.info("AmazonCheckout - checkoutPage");
		// sets checkout data
		CartData cartData = getCheckoutFacade().getCheckoutCart();
		model.addAttribute("cartData", cartData);
		model.addAttribute("deliveryMethods", getCheckoutFacade().getSupportedDeliveryModes());
		model.addAttribute("amazonPlaceOrderForm", new AmazonPlaceOrderForm());

		// sets cms data and pagetype
		storeCmsPageInModel(model, getContentPageForLabelOrId(AMAZON_CHECKOUT_CMS_PAGE_LABEL));
		model.addAttribute("pageType", PageType.CHECKOUTPAGE.name());
		return AmazonpaymentaddonControllerConstants.Views.Pages.Checkout.AmazonCheckoutPage;
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

	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	public String placeOrder(final Model model, final AmazonPlaceOrderForm amazonPlaceOrderForm) {
		LOG.info("AmazonCheckout - placeOrder");
		CartData cartData = getCheckoutFacade().getCheckoutCart();
		AmazonOrderReferenceAttributesData orderReferenceAttributesData = new AmazonOrderReferenceAttributesData();
		orderReferenceAttributesData.setOrderTotal(cartData.getTotalPrice());
		amazonPaymentService.setOrderReferenceDetails(amazonPlaceOrderForm.getAmazonOrderReferenceId(), orderReferenceAttributesData);
		if(getCheckoutFacade().authorizePayment(null)) {
			LOG.info("AmazonCheckout - payment ok");
		} else {
			LOG.info("AmazonCheckout - payment failed");
		}
		return REDIRECT_URL_AMAZON_CHECKOUT;
	}

	@Override
	protected AmazonCheckoutFacade getCheckoutFacade() {
		return amazonCheckoutFacade;
	}
}