/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package br.hering.storefront.controllers.pages;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.hering.core.customer.exceptions.CustomerAuthenticationException;
import br.hering.facades.flow.impl.SessionOverrideCheckoutFlowFacade;
import br.hering.facades.order.HeringOrderFacade;
import br.hering.facades.customer.HeringCustomerFacade;
import br.hering.storefront.controllers.ControllerConstants;
import br.hering.storefront.util.HeringPageType;

import com.flieger.bonussystem.facade.BonusSystemFacade;
import com.flieger.payment.data.BoletoPaymentInfoData;
import com.fliegersoftware.newslettersubscription.data.NewsletterSubscriptionData;
import com.fliegersoftware.newslettersubscription.exceptions.DuplicatedNewsletterSubscriptionException;
import com.fliegersoftware.newslettersubscription.facades.NewsletterSubscriptionFacade;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractCheckoutController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.GuestRegisterForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.GuestRegisterValidator;
import de.hybris.platform.acceleratorstorefrontcommons.security.AutoLoginStrategy;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.store.services.BaseStoreService;


/**
 * CheckoutController
 */
@Controller
@Scope("tenant")
@RequestMapping(value = "/checkout")
public class CheckoutController extends AbstractCheckoutController
{
	protected static final Logger LOG = Logger.getLogger(CheckoutController.class);
	/**
	 * We use this suffix pattern because of an issue with Spring 3.1 where a Uri value is incorrectly extracted if it
	 * contains on or more '.' characters. Please see https://jira.springsource.org/browse/SPR-6164 for a discussion on
	 * the issue and future resolution.
	 */
	private static final String ORDER_CODE_PATH_VARIABLE_PATTERN = "{orderCode:.*}";

	private static final String CHECKOUT_ORDER_CONFIRMATION_CMS_PAGE_LABEL = "orderConfirmation";

	@Resource(name = "productFacade")
	private ProductFacade productFacade;

	@Resource(name = "checkoutFacade")
	private CheckoutFacade checkoutFacade;

	@Resource(name = "guestRegisterValidator")
	private GuestRegisterValidator guestRegisterValidator;

	@Resource(name = "autoLoginStrategy")
	private AutoLoginStrategy autoLoginStrategy;
	
	@Resource(name = "heringOrderFacade")
	private HeringOrderFacade heringOrderFacade;
	
	@Resource(name = "heringCustomerFacade")
	private HeringCustomerFacade heringCustomerFacade;

	@Resource
	private BaseStoreService baseStoreService;
	
	@Resource
	private BonusSystemFacade bonusSystemFacade;
	
	@Resource
	private NewsletterSubscriptionFacade newsletterSubscriptionFacade;
	
	@Resource
	private CMSSiteService cmsSiteService;
	
	@ExceptionHandler(ModelNotFoundException.class)
	public String handleModelNotFoundException(final ModelNotFoundException exception, final HttpServletRequest request)
	{
		request.setAttribute("message", exception.getMessage());
		return FORWARD_PREFIX + "/404";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String checkout(final RedirectAttributes redirectModel)
	{
		if (hasValidCart())
		{
			if (validateCart(redirectModel))
			{
				return REDIRECT_PREFIX + "/cart";
			}
			else
			{
				checkoutFacade.prepareCartForCheckout();
				return getCheckoutRedirectUrl();
			}
		}

		// No session cart or empty session cart. Bounce back to the cart page.
		return REDIRECT_PREFIX + "/cart";
	}

	@RequestMapping(value = "/orderConfirmation/" + ORDER_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	@RequireHardLogIn
	public String orderConfirmation(@PathVariable("orderCode") final String orderCode, final HttpServletRequest request,
			final Model model) throws CMSItemNotFoundException
	{
		SessionOverrideCheckoutFlowFacade.resetSessionOverrides();
		return processOrderCode(orderCode, model, request);
	}


//	@RequestMapping(value = "/orderConfirmation/" + ORDER_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.POST)
//	public String orderConfirmation(final GuestRegisterForm form, final BindingResult bindingResult, final Model model,
//			final HttpServletRequest request, final HttpServletResponse response, final RedirectAttributes redirectModel)
//			throws CMSItemNotFoundException
//	{
//		getGuestRegisterValidator().validate(form, bindingResult);
//		return processRegisterGuestUserRequest(form, bindingResult, model, request, response, redirectModel);
//	}
	
	@RequestMapping(value = "/guestConvert" , method = RequestMethod.POST, produces="text/plain")	
	public @ResponseBody String guestConvert(final GuestRegisterForm form, final BindingResult bindingResult, final Model model,
			final HttpServletRequest request, final HttpServletResponse response, final RedirectAttributes redirectModel)
			throws CMSItemNotFoundException
	{
		getGuestRegisterValidator().validate(form, bindingResult);
		return processRegisterGuestUserRequest(form, bindingResult, model, request, response, redirectModel);
	}

	/**
	 * Method used to determine the checkout redirect URL that will handle the checkout process.
	 * 
	 * @return A <code>String</code> object of the URL to redirect to.
	 */
	protected String getCheckoutRedirectUrl()
	{
		if (getUserFacade().isAnonymousUser())
		{
			return REDIRECT_PREFIX + "/login/checkout";
		}

		// Default to the multi-step checkout
//		if("dzarm".equals(baseStoreService.getCurrentBaseStore().getUid())) {
//			return REDIRECT_PREFIX + "checkout/multi";
//		}
		return REDIRECT_PREFIX + "/checkout/single";
	}

	protected String processRegisterGuestUserRequest(final GuestRegisterForm form, final BindingResult bindingResult,
			final Model model, final HttpServletRequest request, final HttpServletResponse response,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		if (bindingResult.hasErrors())
		{
			GlobalMessages.addErrorMessage(model, "form.guest.fieldMismatch");
			return getMessageSource().getMessage("form.guest.fieldMismatch", null, getI18nService().getCurrentLocale());
		}
		
		final boolean checkedNewsletterSubscription = request.getParameter("registerNewsletter") != null;		
		
		try
		{
			getCustomerFacade().changeGuestToCustomer(form.getPwd(), form.getOrderCode());
			try
			{
				getAutoLoginStrategy().login(getCustomerFacade().getCurrentCustomer().getUid(), form.getPwd(), request, response);
			}
			catch(CustomerAuthenticationException e)
			{
				LOG.error("Login Error", e);
			}
			getSessionService().removeAttribute(WebConstants.ANONYMOUS_CHECKOUT);
		}
		catch (final DuplicateUidException e)
		{
			// User already exists
			LOG.warn("guest registration failed: " + e);
			model.addAttribute(new GuestRegisterForm());
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
					"guest.checkout.existingaccount.register.error", new Object[]
					{ form.getUid() });
			return getMessageSource().getMessage("guest.checkout.existingaccount.register.error", null, getI18nService().getCurrentLocale());
		}		
		
		if(checkedNewsletterSubscription)
		{
			final NewsletterSubscriptionData data = new NewsletterSubscriptionData();
			final CustomerData customerData = heringCustomerFacade.getCurrentCustomer();
			
			data.setFirstName(customerData.getFirstName());
			data.setLastName(customerData.getLastName());
			data.setEmail(customerData.getUid());
			data.setGenderCode(customerData.getGender() != null ? customerData.getGender().getCode() : "FEMALE");
			data.setTitleCode((customerData.getTitleCode() != null && !customerData.getTitleCode().isEmpty()) ? customerData.getTitleCode() : "ms");
			data.setCustomer(customerData);
			data.setStoreCode(newsletterSubscriptionFacade.getCurrentBaseStoreCode());
			data.setLanguageIsoCode(customerData.getLanguage().getIsocode());
			
			try
			{
				newsletterSubscriptionFacade.subscribe(data);
			}
			catch (DuplicatedNewsletterSubscriptionException e)
			{
				e.printStackTrace();
			}
		}

		return Boolean.TRUE.toString();
	}

	protected String processOrderCode(final String orderCode, final Model model, final HttpServletRequest request)
			throws CMSItemNotFoundException
	{
		final OrderData orderDetails = heringOrderFacade.getOrderDetailsForCode(orderCode);

		if(orderDetails.isGuestCustomer() && !StringUtils.substringBefore(orderDetails.getUser().getUid(),"|").
				equals(getSessionService().getAttribute(WebConstants.ANONYMOUS_CHECKOUT_GUID)))
		{
			return getCheckoutRedirectUrl();
		}
		
		final String uid = orderDetails.getUser().getUid().replaceAll("[a-z0-9\\-]*\\|", "");
		final boolean alreadyHasAccount = heringCustomerFacade.emailAlreadyExists(uid) != null;
		boolean alreadyNewsletterSubscription = false;
		if(alreadyHasAccount)
			alreadyNewsletterSubscription = !heringCustomerFacade.emailAlreadyExists(uid).getNewsletterSubscriptions().isEmpty();
		
		model.addAttribute("orderCode", orderCode);
		model.addAttribute("orderData", orderDetails);
		model.addAttribute("orderPoints", bonusSystemFacade.getOrderConfirmationPoints(heringOrderFacade.getOrderModelByCode(orderCode)).intValue());
		model.addAttribute("allItems", orderDetails.getEntries());
		model.addAttribute("deliveryAddress", orderDetails.getDeliveryAddress());
		model.addAttribute("deliveryMode", orderDetails.getDeliveryMode());
		model.addAttribute("paymentInfo", orderDetails.getPaymentInfo());		
		model.addAttribute("pageType", HeringPageType.ORDERCONFIRMATION.name());		
		model.addAttribute("email", uid);
		model.addAttribute("customerData", alreadyHasAccount ? heringCustomerFacade.getCurrentCustomer() : null);
		model.addAttribute("alreadyHasAccount", alreadyHasAccount);
		model.addAttribute("alreadyNewsletterSubscription", alreadyNewsletterSubscription);		

		final String boletoUrl = "/boleto-img/jpg/boleto-" + orderCode + ".jpg";
		
		model.addAttribute("boletoUrl", boletoUrl);
		
//		model.addAttribute("boletoUrl",
//				Config.getParameter("website.dzarm.http").substring(0, Config.getParameter("website.dzarm.http").indexOf("/store"))
//						+ "/boleto-img/jpg/boleto-" + orderCode + ".jpg");

		if (orderDetails.isGuestCustomer() && !model.containsAttribute("guestRegisterForm"))
		{
			final GuestRegisterForm guestRegisterForm = new GuestRegisterForm();
			guestRegisterForm.setOrderCode(orderDetails.getGuid());
			guestRegisterForm.setUid(uid);
			model.addAttribute("guestRegisterForm", guestRegisterForm);
		}

		final AbstractPageModel cmsPage = getContentPageForLabelOrId(CHECKOUT_ORDER_CONFIRMATION_CMS_PAGE_LABEL);
		storeCmsPageInModel(model, cmsPage);
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(CHECKOUT_ORDER_CONFIRMATION_CMS_PAGE_LABEL));
		model.addAttribute("metaRobots", "no-index,no-follow");
		
		//Servico para gerar a imagem do boleto
		if(orderDetails.getCustomPaymentInfo() != null){
			if(orderDetails.getCustomPaymentInfo() instanceof BoletoPaymentInfoData){
				heringOrderFacade.generateBoleto(orderCode);
			}
		}
		
		return ControllerConstants.Views.Pages.Checkout.CheckoutConfirmationPage;
	}

	protected GuestRegisterValidator getGuestRegisterValidator()
	{
		return guestRegisterValidator;
	}

	protected AutoLoginStrategy getAutoLoginStrategy()
	{
		return autoLoginStrategy;
	}

}
