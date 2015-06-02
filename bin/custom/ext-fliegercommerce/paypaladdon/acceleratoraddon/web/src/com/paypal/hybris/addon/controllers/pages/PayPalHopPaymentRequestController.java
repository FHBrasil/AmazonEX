/**
 *
 */
package com.paypal.hybris.addon.controllers.pages;

import com.paypal.hybris.addon.controllers.utils.PayPalUserHelper;
import de.hybris.platform.acceleratorservices.uiexperience.UiExperienceService;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commerceservices.enums.UiExperienceLevel;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.storefront.controllers.pages.checkout.steps.AbstractCheckoutStepController;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paypal.hybris.addon.controllers.PaypaladdonControllerConstants;
import com.paypal.hybris.addon.controllers.utils.ControllerUtils;
import com.paypal.hybris.facade.impl.PayPalPaymentFacade;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.data.AbstractResultData;
import com.paypal.hybris.data.SetExpressCheckoutRequestData;
import com.paypal.hybris.data.SetExpressCheckoutResultData;
import org.springframework.web.util.CookieGenerator;




/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
@Controller
public class PayPalHopPaymentRequestController extends AbstractCheckoutStepController
{
	public static final String FORWARD_CART_URL = FORWARD_PREFIX + "/cart";
	public static final String REQUEST_MODEL_ATTRIBUTE_NAME = "request";
	public static final String SECURE_GUID_SESSION_KEY = "acceleratorSecureGUID";

	@Resource(name = "payPalPaymentFacade")
	private PayPalPaymentFacade paypalFacade;
	@Resource
	private UiExperienceService uiExperienceService;
	@Resource(name = "userFacade")
	private UserFacade userFacade;
	@Resource
	private ConfigurationService configurationService;
	@Resource(name = "checkoutFacade")
	private CheckoutFacade checkoutFacade;
	@Resource(name = "guidCookieGenerator")
	private CookieGenerator cookieGenerator;
	@Resource(name = "payPalUserHelper")
	private PayPalUserHelper payPalUserHelper;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.storefront.controllers.pages.checkout.steps.CheckoutStepController#enterStep(org.springframework
	 * .ui.Model, de.hybris.platform.storefront.controllers.pages.checkout.steps.RedirectAttributes)
	 */
	@Override
	@RequestMapping(value = "/paypal/checkout/hop/expressCheckoutShortcut", method = RequestMethod.GET)
	public String enterStep(final Model model, final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException,
			CommerceCartModificationException
	{
		getSessionService().setAttribute(PaypalConstants.IS_PAYPAL_CREDIT, Boolean.FALSE);

		if (redirectToLoginPage(model))
		{
			getSessionService().setAttribute(PaypaladdonControllerConstants.PAY_PAL_HOP_URL_ATTR,
					PaypaladdonControllerConstants.PAY_PAL_HOP_EXPRESS_CHECKOUT_SHORTCUT_URL);
			return REDIRECT_PREFIX + "/login/checkout";
		}

		removeDeliveryAddressFromCart();

		return prepareExpressCheckout(model, "/paypal/checkout/hop/response/?shortcut=true", "/cart", PaypalConstants.CREDIT_CARD,
				PaypalConstants.DEFAULT_SOLUTION_TYPE_NAME, true);
	}

	@RequestMapping(value = "/checkout/multi/payment-method/expressCheckoutMark")
	public String expressCheckoutMark(final Model model) throws CMSItemNotFoundException
	{
		getSessionService().setAttribute(PaypalConstants.IS_PAYPAL_CREDIT, Boolean.FALSE);
		return prepareExpressCheckout(model, "/paypal/checkout/hop/response/", "/checkout/multi/payment-method/add",
				PaypalConstants.CREDIT_CARD, PaypalConstants.DEFAULT_SOLUTION_TYPE_NAME, false);
	}

	@RequestMapping(value = "/paypal/checkout/hop/creditCartShortcut")
	public String creditCartShortcut(final Model model) throws CMSItemNotFoundException
	{
		getSessionService().setAttribute(PaypalConstants.IS_PAYPAL_CREDIT, Boolean.TRUE);
		if (redirectToLoginPage(model))
		{
			getSessionService().setAttribute(PaypaladdonControllerConstants.PAY_PAL_HOP_URL_ATTR,
					PaypaladdonControllerConstants.PAY_PAL_HOP_CREDIT_CART_SHORTCUT_URL);
			return REDIRECT_PREFIX + "/login/checkout";
		}

		return prepareExpressCheckout(model, "/paypal/checkout/hop/response/?shortcut=true", "/cart", PaypalConstants.BML,
				PaypalConstants.SOLE_SOLUTION_TYPE_NAME, false);
	}

	public String prepareExpressCheckout(final Model model, final String returnUrl, final String cancelUrl,
			final String fundingSource, final String solutionType, final boolean isInContextCheckoutAvailable)
			throws CMSItemNotFoundException
	{
		if(checkoutFacade.getCheckoutCart() == null || CollectionUtils.isEmpty(checkoutFacade.getCheckoutCart().getEntries())){
			return REDIRECT_URL_CART ;
		}
		final Configuration configuration = configurationService.getConfiguration();
		Locale userLocale = JaloSession.getCurrentSession().getSessionContext().getLocale();
		final SetExpressCheckoutRequestData requestData = new SetExpressCheckoutRequestData();
		requestData.setPaymentAction(configuration.getString(PaypalConstants.PAYMENT_ACTION));
		requestData.setSolutionType(solutionType);
		final UiExperienceLevel uiExperienceLevel = uiExperienceService.getUiExperienceLevel();
		requestData.setLocale(userLocale);
		requestData.setUiExperienceLevel(uiExperienceLevel);
		if (PaypalConstants.BML.equals(fundingSource))
		{
			requestData.setFundingSource(fundingSource);
		}
		requestData.setCancelUrl(paypalFacade.getFullResponseUrl(cancelUrl, true));
		requestData.setReturnUrl(paypalFacade.getFullResponseUrl(returnUrl, true));
		requestData.setSessionCart(checkoutFacade.getCheckoutCart());

		final SetExpressCheckoutResultData resultData = paypalFacade.preparePaypalPayment(requestData);

		final StringBuilder redirectUrl = new StringBuilder();
		if (PaypalConstants.STATUS_SUCCESS.equalsIgnoreCase(resultData.getAck()))
		{
			LOG.info("PayPal express checkout token: " + resultData.getToken());

			getSessionService().setAttribute("PAYPAL_TOKEN", resultData.getToken());
			redirectUrl.append(REDIRECT_PREFIX);
			if (uiExperienceLevel == UiExperienceLevel.DESKTOP)
			{
				if (isInContextCheckoutAvailable && configuration.getBoolean(PaypalConstants.IN_CONTEXT_CHECKOUT_ENABLED))
				{
					redirectUrl.append(configuration.getString(PaypalConstants.IN_CONTEXT_CHECKOUT_REDIRECT_URL));
				}
				else
				{
					redirectUrl.append(configuration.getString(PaypalConstants.SETT_REDIRECT_URL_DESKTOP));
				}
			}
			else if (uiExperienceLevel == UiExperienceLevel.MOBILE)
			{
				redirectUrl.append(configuration.getString(PaypalConstants.SETT_REDIRECT_URL_MOBILE));
			}
			redirectUrl.append(resultData.getToken());
		}
		else
		{
			handleErrors(resultData);
			final List<String> errorCodes = ControllerUtils.getErrorCodeList(resultData);
			return REDIRECT_PREFIX + "/paypal/hop/error" + "/?decision=" + resultData.getAck() + "&reasonCodes="
					+ StringUtils.join(errorCodes, ',');
		}

		return redirectUrl.toString();
	}

	/**
	 * Calculates is user should be redirected to login page.
	 * He will see login page in case paypal guest redirect option is set to false and he isn't
	 * hard login to site or is anonymous. Otherwise user will go to login page only if he has account
	 * and is not hard login.
	 *
	 * @param model model with parameter
	 * @return true if redirect is needed, false otherwise
	 */
	private boolean redirectToLoginPage(Model model)
	{
		boolean redirectToLoginPage;
		boolean isGuestUserRedirect = configurationService.getConfiguration().getBoolean(PaypalConstants.PAYPAL_GUEST_REDIRECT);
		if (isGuestUserRedirect)
		{
			redirectToLoginPage = !userFacade.isAnonymousUser() && !payPalUserHelper.isHardLogin(model);
		}
		else
		{
			redirectToLoginPage = !payPalUserHelper.isHardLogin(model);
		}

		return redirectToLoginPage;
	}

	private void handleErrors(final AbstractResultData resultData)
	{
		final Map<String, String> errorCodeToMsgMap = ControllerUtils.getErrorsCodeToMessageMap(resultData);
		getSessionService().setAttribute(PaypaladdonControllerConstants.PAY_PAL_ERRORS_DETAILS, errorCodeToMsgMap);
		for (final String code : errorCodeToMsgMap.keySet())
		{
			LOG.error(code);
			LOG.error(errorCodeToMsgMap.get(code));
		}
	}

	@RequestMapping(value = "/paypal/hop/error", method = RequestMethod.GET)
	public String doPayPalPageError(@RequestParam(required = true) final String decision,
			@RequestParam(required = true) final String[] reasonCodes, final Model model, final RedirectAttributes redirectAttributes)
			throws CMSItemNotFoundException
	{
		final Map<String, String> errorsDetails = getSessionService().getAttribute(
				PaypaladdonControllerConstants.PAY_PAL_ERRORS_DETAILS);

		final String redirectUrl = REDIRECT_URL_CART;
		model.addAttribute("decision", decision);
		model.addAttribute("reasonCodes", reasonCodes);
		model.addAttribute("errorsDetails", errorsDetails);
		model.addAttribute("redirectUrl", redirectUrl.replace(REDIRECT_PREFIX, ""));
		model.addAttribute(WebConstants.BREADCRUMBS_KEY,
				getResourceBreadcrumbBuilder().getBreadcrumbs("checkout.multi.hostedOrderPageError.breadcrumb"));
		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));

		GlobalMessages.addErrorMessage(model, "paypal.general.error.header");

		return PaypaladdonControllerConstants.Views.Pages.MultiStepCheckout.PayPalHostedOrderPageErrorPage;
	}

	private void removeDeliveryAddressFromCart()
	{
		final AddressData previousSelectedAddress = getCheckoutFacade().getCheckoutCart().getDeliveryAddress();
		if (previousSelectedAddress != null && !previousSelectedAddress.isVisibleInAddressBook())
		{ // temporary address should be removed
			getUserFacade().removeAddress(previousSelectedAddress);
		}
		getCheckoutFacade().removeDeliveryAddress();
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.storefront.controllers.pages.checkout.steps.CheckoutStepController#back(de.hybris.platform.
	 * storefront.controllers.pages.checkout.steps.RedirectAttributes)
	 */
	@Override
	public String back(final RedirectAttributes redirectAttributes)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.storefront.controllers.pages.checkout.steps.CheckoutStepController#next(de.hybris.platform.
	 * storefront.controllers.pages.checkout.steps.RedirectAttributes)
	 */
	@Override
	public String next(final RedirectAttributes redirectAttributes)
	{
		// YTODO Auto-generated method stub
		return null;
	}

}
