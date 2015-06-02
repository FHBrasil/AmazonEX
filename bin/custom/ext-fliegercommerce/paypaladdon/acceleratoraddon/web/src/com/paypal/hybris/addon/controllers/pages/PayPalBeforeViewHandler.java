/**
 *
 */
package com.paypal.hybris.addon.controllers.pages;

import com.paypal.hybris.addon.controllers.utils.PayPalUserHelper;
import com.paypal.hybris.constants.PaypalConstants;
import de.hybris.platform.addonsupport.interceptors.BeforeViewHandlerAdaptee;
import de.hybris.platform.cms2.servicelayer.services.CMSComponentService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.storefront.controllers.ControllerConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;

import com.paypal.hybris.addon.constants.PaypaladdonConstants;
import com.paypal.hybris.addon.controllers.PaypaladdonControllerConstants;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 */
public class PayPalBeforeViewHandler implements BeforeViewHandlerAdaptee
{
	private static final String B2C_CHECKOUT_CONFIRMATION_PAGE = "pages/checkout/checkoutConfirmationPage";
	private static final String B2C_ADD_TO_CART_POPUP_PAGE = "fragments/cart/addToCartPopup";
	private static final String CART_PAGE = "pages/cart/cartPage";

	private SessionService sessionService;
	private CMSComponentService cmsComponentService;
	private PayPalUserHelper payPalUserHelper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.addonsupport.interceptors.BeforeViewHandlerAdaptee#beforeView(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap, java.lang.String)
	 */
	@Override
	public String beforeView(final HttpServletRequest request, final HttpServletResponse response, final ModelMap model,
			final String viewName) throws Exception
	{
		if (ControllerConstants.Views.Pages.MultiStepCheckout.SilentOrderPostPage.equals(viewName))
		{
			return PaypaladdonControllerConstants.Views.Pages.MultiStepCheckout.SilentOrderPostPage;
		}
		else if (ControllerConstants.Views.Pages.MultiStepCheckout.CheckoutSummaryPage.equals(viewName))
		{
			final String repeatRedirectUrl = getSessionService().getAttribute((PaypaladdonConstants.PAY_PAL_REPEAT_REDIRECT_URL));
			if (StringUtils.isNotBlank(repeatRedirectUrl))
			{
				getSessionService().removeAttribute(PaypaladdonConstants.PAY_PAL_REPEAT_REDIRECT_URL);
				return "redirect:" + repeatRedirectUrl;
			}
			else
			{
				return PaypaladdonControllerConstants.Views.Pages.MultiStepCheckout.CheckoutSummaryPage;
			}
		}
		else if (B2C_CHECKOUT_CONFIRMATION_PAGE.equals(viewName))
		{
			return PaypaladdonControllerConstants.Views.Pages.Checkout.CheckoutConfirmationPage;
		}
		else if (B2C_ADD_TO_CART_POPUP_PAGE.equals(viewName))
		{
			model.addAttribute("payPalExpressCheckoutShortcut",
					getCmsComponentService().getSimpleCMSComponent("PayPalExpressCheckoutShortcutSmall"));
			model.addAttribute("payPalCreditShortcut", getCmsComponentService().getSimpleCMSComponent("PayPalCreditShortcutSmall"));
			return PaypaladdonControllerConstants.Views.Fragments.Cart.AddToCartPopup;
		}
		else if (isCheckoutPage(viewName)  ||  isPayPalCheckoutRedirect(viewName, request))
		{
			final String payPalHopUrl = getSessionService().getAttribute(PaypaladdonControllerConstants.PAY_PAL_HOP_URL_ATTR);
			if (StringUtils.isNotBlank(payPalHopUrl))
			{
				getSessionService().removeAttribute(PaypaladdonControllerConstants.PAY_PAL_HOP_URL_ATTR);
				return "redirect:" + payPalHopUrl;
			}
		}
		if(!StringUtils.contains(viewName, PaypaladdonControllerConstants.Views.Pages.Checkout.CheckoutLoginPage)
				&& !StringUtils.contains(viewName, CART_PAGE)){
			getSessionService().removeAttribute(PaypaladdonControllerConstants.PAY_PAL_HOP_URL_ATTR);

		}
		return viewName;
	}

	private boolean isCheckoutPage(String viewName)
	{
		return !PaypaladdonControllerConstants.Views.Pages.Checkout.CheckoutLoginPage.equals(viewName)
				&& StringUtils.contains(viewName, PaypaladdonControllerConstants.CHECKOUT_PAGE_VIEW_NAME_FRAGMENT);
	}

	private boolean isPayPalCheckoutRedirect(String viewName, final HttpServletRequest request){
		return StringUtils.contains(viewName, CART_PAGE) &&  payPalUserHelper.isHardLogin(request, true);
	}



	public SessionService getSessionService()
	{
		return sessionService;
	}

	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	public CMSComponentService getCmsComponentService()
	{
		return cmsComponentService;
	}

	public void setCmsComponentService(final CMSComponentService cmsComponentService)
	{
		this.cmsComponentService = cmsComponentService;
	}

	public PayPalUserHelper getPayPalUserHelper() {
		return payPalUserHelper;
	}

	public void setPayPalUserHelper(PayPalUserHelper payPalUserHelper) {
		this.payPalUserHelper = payPalUserHelper;
	}
}
