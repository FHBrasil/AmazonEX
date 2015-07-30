package de.fliegersoftware.amazon.payment.addon.controllers.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.fliegersoftware.amazon.payment.addon.controllers.AmazonpaymentaddonControllerConstants;
import de.hybris.platform.acceleratorservices.controllers.page.PageType;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractCheckoutController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;

@Controller
@RequestMapping("/checkout/amazon")
public class AmazonCheckoutPageController extends AbstractCheckoutController {

	private static final String AMAZON_CHECKOUT_CMS_PAGE_LABEL = "amazonCheckout";

	@RequestMapping(method = RequestMethod.GET)
	@RequireHardLogIn
	public String checkoutPage(final Model model) throws CMSItemNotFoundException {
		storeCmsPageInModel(model, getContentPageForLabelOrId(AMAZON_CHECKOUT_CMS_PAGE_LABEL));

		model.addAttribute("pageType", PageType.CHECKOUTPAGE.name());

		return AmazonpaymentaddonControllerConstants.Views.Pages.Checkout.AmazonCheckoutPage;
	}
}