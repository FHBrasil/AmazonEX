package com.fliegersoftware.addon.paypal.controllers.pages;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.hering.facades.facades.order.HeringCheckoutFacade;

import com.ebay.api.PaymentActionCodeType;
import com.fliegersoftware.addon.paypal.controllers.PaypaladdonControllerConstants;
import com.fliegersoftware.addon.paypal.controllers.utils.ControllerUtils;
import com.fliegersoftware.addon.paypal.controllers.utils.PayPalUserHelper;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.data.AbstractResultData;
import com.paypal.hybris.data.SetExpressCheckoutRequestData;
import com.paypal.hybris.data.SetExpressCheckoutResultData;
import com.paypal.hybris.facade.impl.PayPalPaymentFacade;

import de.hybris.platform.acceleratorservices.enums.UiExperienceLevel;
import de.hybris.platform.acceleratorservices.uiexperience.UiExperienceService;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractCheckoutController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.util.Config;

/**
 * @author jfelipe
 */
@Controller
@RequestMapping(value = "/paypal/request/")
public class PaypalPaymentRequestController extends AbstractCheckoutController {
    
    private static final Logger LOG = Logger.getLogger(PaypalPaymentRequestController.class);
    private static final String REDIRECT_URL_CART = REDIRECT_PREFIX + "/cart";
    private static final String REDIRECT_CANCEL_URL = Config.getParameter(
            "website.babyartikel.https") + "/de/checkout/single/single-step-checkout";
    private static final String REDIRECT_RETURN_URL = Config.getParameter(
            "website.babyartikel.https") + "/de/paypal/response/";
    @Resource
    private PayPalPaymentFacade paypalPaymentFacade;
    @Resource
    private UserFacade userFacade;
    @Resource
    private ConfigurationService configurationService;
    @Resource
    private PayPalUserHelper payPalUserHelper;
    @Resource
    private UiExperienceService uiExperienceService;
    
    
    /**
     * @param model
     * @param redirectAttributes
     * @return
     * @throws CMSItemNotFoundException
     * @throws CommerceCartModificationException
     */
    @Deprecated
    @RequestMapping(value = "/expressCheckoutShortcut", method = RequestMethod.GET)
    public String doExpressCheckout(final Model model, final RedirectAttributes redirectAttributes)
            throws CMSItemNotFoundException, CommerceCartModificationException {
        getSessionService().setAttribute(PaypalConstants.IS_PAYPAL_CREDIT, Boolean.FALSE);
        if (redirectToLoginPage(model)) {
            return REDIRECT_PREFIX + "/login/checkout";
        }
        // removeDeliveryAddressFromCart();
        return prepareExpressCheckout(model, "/paypal/response/?shortcut=true", "/cart",
                PaypalConstants.CREDIT_CARD, PaypalConstants.DEFAULT_SOLUTION_TYPE_NAME, true);
    }
    
    
    /**
     * Redirects to the paypal website to complete the order, sending the payment information
     * (billing address, total prices and other pipipitchus).
     * "Mark" is the type of PayPal Checkout that users choose in the checkout page.
     * 
     * @param model
     * @return
     * @throws CMSItemNotFoundException
     */
    @RequestMapping(value = "/payment-method/expressCheckoutMark")
    public String expressCheckoutMark(final Model model) throws CMSItemNotFoundException {
        getSessionService().setAttribute(PaypalConstants.IS_PAYPAL_CREDIT, Boolean.FALSE);
        return prepareExpressCheckout(model, REDIRECT_RETURN_URL, REDIRECT_CANCEL_URL,
                PaypalConstants.CREDIT_CARD, PaypalConstants.DEFAULT_SOLUTION_TYPE_NAME, false);
    }
    
    
    /**
     * @param model
     * @return
     * @throws CMSItemNotFoundException
     */
    @RequestMapping(value = "/creditCartShortcut")
    public String creditCartShortcut(final Model model) throws CMSItemNotFoundException {
        getSessionService().setAttribute(PaypalConstants.IS_PAYPAL_CREDIT, Boolean.TRUE);
        if (redirectToLoginPage(model)) {
            getSessionService().setAttribute(PaypaladdonControllerConstants.PAY_PAL_HOP_URL_ATTR,
                    PaypaladdonControllerConstants.PAY_PAL_HOP_CREDIT_CART_SHORTCUT_URL);
            return REDIRECT_PREFIX + "/login/checkout";
        }
        return prepareExpressCheckout(model, "/paypal/response/?shortcut=true", "/cart",
                PaypalConstants.BML, PaypalConstants.SOLE_SOLUTION_TYPE_NAME, false);
    }
    
    
    /**
     * Calculates if user should be redirected to login page. He will see login page in case
     * paypal guest redirect option
     * is set to false and he isn't hard login to site or is anonymous. Otherwise user will go to
     * login page only if he
     * has account and is not hard login.
     *
     * @param model
     *            model with parameter
     * @return true if redirect is needed, false otherwise
     */
    private boolean redirectToLoginPage(final Model model) {
        boolean redirectToLoginPage;
        final boolean isGuestUserRedirect =
                configurationService.getConfiguration().getBoolean(
                        PaypalConstants.PAYPAL_GUEST_REDIRECT);
        if (isGuestUserRedirect) {
            redirectToLoginPage =
                    !userFacade.isAnonymousUser() && !payPalUserHelper.isHardLogin(model);
        } else {
            redirectToLoginPage = !payPalUserHelper.isHardLogin(model);
        }
        return redirectToLoginPage;
    }
    
    
    /**
     * @param model
     * @param returnUrl
     * @param cancelUrl
     * @param fundingSource
     * @param solutionType
     * @param isInContextCheckoutAvailable
     * @return
     * @throws CMSItemNotFoundException
     */
    public String prepareExpressCheckout(Model model, final String returnUrl,
            final String cancelUrl, final String fundingSource, final String solutionType,
            final boolean isInContextCheckoutAvailable) throws CMSItemNotFoundException {
        if (getCheckoutFacade().getCheckoutCart() == null
                || CollectionUtils.isEmpty(getCheckoutFacade().getCheckoutCart().getEntries())) {
            return REDIRECT_URL_CART;
        }
        SetExpressCheckoutRequestData requestData = new SetExpressCheckoutRequestData();
        requestData.setReturnUrl(returnUrl);
        requestData.setCancelUrl(cancelUrl);
        requestData.setPaymentAction(PaymentActionCodeType.SALE.value());
        requestData.setSolutionType(solutionType);
        Locale userLocale = JaloSession.getCurrentSession().getSessionContext().getLocale();
        final UiExperienceLevel uiExperienceLevel = uiExperienceService.getUiExperienceLevel();
        requestData.setLocale(userLocale);
        if (PaypalConstants.BML.equals(fundingSource)) {
            requestData.setFundingSource(fundingSource);
        }
        requestData.setSessionCart(getCheckoutFacade().getCheckoutCart());
        final SetExpressCheckoutResultData resultData =
                paypalPaymentFacade.preparePaypalPayment(requestData);
        StringBuilder redirectUrl = new StringBuilder();
        if (PaypalConstants.STATUS_SUCCESS.equals(resultData.getAck())) {
            LOG.info("PayPal express checkout token: " + resultData.getToken());
            getSessionService().setAttribute("PAYPAL_TOKEN", resultData.getToken());
            redirectUrl.append(REDIRECT_PREFIX);
            if (uiExperienceLevel == UiExperienceLevel.DESKTOP) {
                if (isInContextCheckoutAvailable
                        && Boolean.valueOf(
                                Config.getParameter(PaypalConstants.IN_CONTEXT_CHECKOUT_ENABLED))
                                .booleanValue()) {
                    redirectUrl.append(Config
                            .getParameter(PaypalConstants.IN_CONTEXT_CHECKOUT_REDIRECT_URL));
                } else {
                    redirectUrl.append(Config
                            .getParameter(PaypalConstants.SETT_REDIRECT_URL_DESKTOP));
                }
            } else if (uiExperienceLevel == UiExperienceLevel.MOBILE) {
                redirectUrl.append(Config.getParameter(PaypalConstants.SETT_REDIRECT_URL_MOBILE));
            }
            redirectUrl.append(resultData.getToken());
        } else {
            handleErrors(resultData);
            final List<String> errorCodes = ControllerUtils.getErrorCodeList(resultData);
            return REDIRECT_PREFIX + "/paypal/error" + "/?decision=" + resultData.getAck()
                    + "&reasonCodes=" + StringUtils.join(errorCodes, ',');
        }
        return redirectUrl.toString();
    }
    
    
    /**
     * @param resultData
     */
    private void handleErrors(final AbstractResultData resultData) {
        // First of all, we need to clear the request information, because when paypal refuses
        // the payment and the customer tries again, it sends a parallel payment, which is not the
        // case.
        final Map<String, String> errorCodeToMsgMap =
                ControllerUtils.getErrorsCodeToMessageMap(resultData);
        getSessionService().setAttribute(PaypaladdonControllerConstants.PAY_PAL_ERRORS_DETAILS,
                errorCodeToMsgMap);
        for (final String code : errorCodeToMsgMap.keySet()) {
            LOG.error(code);
            LOG.error(errorCodeToMsgMap.get(code));
        }
    }
    
    
    /**
     * @param decision
     * @param reasonCodes
     * @param model
     * @param redirectAttributes
     * @return
     * @throws CMSItemNotFoundException
     */
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String doPayPalPageError(@RequestParam(required = true)
    final String decision, @RequestParam(required = true)
    final String[] reasonCodes, final Model model, final RedirectAttributes redirectAttributes)
            throws CMSItemNotFoundException {
        final Map<String, String> errorsDetails =
                getSessionService().getAttribute(
                        PaypaladdonControllerConstants.PAY_PAL_ERRORS_DETAILS);
        final String redirectUrl = REDIRECT_URL_CART;
        model.addAttribute("decision", decision);
        model.addAttribute("reasonCodes", reasonCodes);
        model.addAttribute("errorsDetails", errorsDetails);
        model.addAttribute("redirectUrl", redirectUrl.replace(REDIRECT_PREFIX, ""));
        // model.addAttribute(WebConstants.BREADCRUMBS_KEY, getResourceBreadcrumbBuilder()
        // .getBreadcrumbs("checkout.multi.hostedOrderPageError.breadcrumb"));
        // storeCmsPageInModel(model,
        // getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
        // setUpMetaDataForContentPage(model,
        // getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
        GlobalMessages.addErrorMessage(model, "paypal.general.error.header");
        return PaypaladdonControllerConstants.Views.Pages.MultiStepCheckout.PayPalHostedOrderPageErrorPage;
    }
    
    
    /**
     * @return
     */
    public PayPalPaymentFacade getPaypalPaymentFacade() {
        return paypalPaymentFacade;
    }
    
    
    /**
     * @return
     */
    public void setPaypalPaymentFacade(PayPalPaymentFacade paypalPaymentFacade) {
        this.paypalPaymentFacade = paypalPaymentFacade;
    }
    
    
    /**
     * @return
     */
    public UserFacade getUserFacade() {
        return userFacade;
    }
    
    
    /**
     * @return
     */
    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }
    
    
    /**
     * @return
     */
    public ConfigurationService getConfigurationService() {
        return configurationService;
    }
    
    
    /**
     * @return
     */
    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
    
    
    /**
     * @return
     */
    public PayPalUserHelper getPayPalUserHelper() {
        return payPalUserHelper;
    }
    
    
    /**
     * @return
     */
    public void setPayPalUserHelper(PayPalUserHelper payPalUserHelper) {
        this.payPalUserHelper = payPalUserHelper;
    }
    
    
    /**
     * Casts the checkoutFacade object to HeringCheckoutFacade
     */
    @Override
    protected HeringCheckoutFacade getCheckoutFacade() {
        return (HeringCheckoutFacade) super.getCheckoutFacade();
    }
}
