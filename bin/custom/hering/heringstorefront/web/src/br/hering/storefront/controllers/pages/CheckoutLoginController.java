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

import de.hybris.platform.acceleratorfacades.flow.CheckoutFlowFacade;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.LoginForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.GuestValidator;
import de.hybris.platform.acceleratorstorefrontcommons.security.GUIDCookieStrategy;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.i18n.CommerceCommonI18NService;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.commercefacades.i18n.I18NFacade;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.flieger.facades.checkout.data.GuestRegisterData;
import com.google.common.base.Strings;

import de.hybris.platform.core.enums.Gender;
import br.hering.facades.customer.HeringCustomerFacade;
import br.hering.heringstorefrontcommons.forms.HeringAddressForm;
import br.hering.heringstorefrontcommons.forms.HeringGuestForm;
import br.hering.heringstorefrontcommons.forms.HeringPaymentDetailsForm;
import br.hering.heringstorefrontcommons.validation.HeringGuestValidator;
import br.hering.storefront.controllers.ControllerConstants;
import br.hering.storefront.forms.validation.HeringRegistrationValidator;
import br.hering.storefront.forms.HeringRegisterForm;
import de.hybris.platform.util.Config;

/**
 * Checkout Login Controller. Handles login and register for the checkout flow.
 */
@Controller
@Scope("tenant")
@RequestMapping(value = "/login/checkout")
public class CheckoutLoginController extends AbstractHeringLoginController
{
	private static final Logger LOG = Logger.getLogger(CheckoutLoginController.class);
	
	@Resource(name = "checkoutFlowFacade")
	private CheckoutFlowFacade checkoutFlowFacade;

	@Resource(name = "guidCookieStrategy")
	private GUIDCookieStrategy guidCookieStrategy;

	@Resource(name = "authenticationManager")
	private AuthenticationManager authenticationManager;

	@Resource(name = "guestValidator")
	private HeringGuestValidator guestValidator;
	
	@Resource(name = "customerFacade")
	protected HeringCustomerFacade customerFacade;
	
	@Resource(name = "registrationValidator")
	protected HeringRegistrationValidator registrationValidator;
	
	@Resource
	protected BaseStoreService baseStoreService;
	
	@Resource
	private I18NFacade i18NFacade;
	
	@Resource
	private CommerceCommonI18NService commerceCommonI18NService;
	
	/**
	 * @return the customerFacade
	 */
	public HeringCustomerFacade getCustomerFacade()
	{
		return customerFacade;
	}

	/**
	 * @param customerFacade the customerFacade to set
	 */
	public void setCustomerFacade(HeringCustomerFacade customerFacade)
	{
		this.customerFacade = customerFacade;
	}

	@Override
	protected AbstractPageModel getCmsPage() throws CMSItemNotFoundException
	{
		return getContentPageForLabelOrId("checkout-login");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String doCheckoutLogin(@RequestParam(value = "error", defaultValue = "false") final boolean loginError,
			final HttpSession session, final Model model, final HttpServletRequest request) throws CMSItemNotFoundException
	{
		final CartData cartData = getCheckoutFlowFacade().getCheckoutCart();
		
		model.addAttribute("cartData",cartData);
		model.addAttribute("pageType", "CHECKOUT");
		model.addAttribute("expressCheckoutAllowed", Boolean.valueOf(checkoutFlowFacade.isExpressCheckoutEnabledForStore()));
		model.addAttribute("regions", i18NFacade.getRegionsForCountryIso("DE"));
		model.addAttribute("pf", Boolean.TRUE);
		model.addAttribute("countries", getCommerceCommonI18NService().getAllCountries());
		
		return getDefaultLoginPage(loginError, session, model);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doCheckoutRegister(final HeringRegisterForm form, final BindingResult bindingResult, final Model model,
			final HttpServletRequest request, final HttpServletResponse response, final RedirectAttributes redirectModel)
			throws CMSItemNotFoundException
	{
		getRegistrationValidator().validate(form, bindingResult, model);
		
		boolean cpfCnpjAlreadyRegistered = customerFacade.cpfCnpjAlreadyExists(form.getCpfcnpj()) != null;
		
		model.addAttribute("pageType", "CHECKOUT");
		
		if(cpfCnpjAlreadyRegistered)
		{
			GlobalMessages.addErrorMessage(model, "register.cpfexists");
			bindingResult.rejectValue("cpfcnpj", "register.cpfexists");
		}
		
		model.addAttribute("regions", i18NFacade.getRegionsForCountryIso("DE"));
		model.addAttribute("pf", Boolean.parseBoolean(form.getPessoaFisica()));
		
		if(!Boolean.parseBoolean(form.getPessoaFisica()))
		{
			form.setGender(Gender.UNDEFINED.name());
		}
		
		return processRegisterUserRequest(null, form, bindingResult, model, request, response, redirectModel);
	}

	@RequestMapping(value = "/guest", method = RequestMethod.POST)
	public String doAnonymousCheckout(final HeringGuestForm form, final BindingResult bindingResult, final Model model,
			final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException
	{
		model.addAttribute("user", null);
		
		getGuestValidator().validate(form, bindingResult, model);
//		
//		boolean cpfCnpjAlreadyRegistered = customerFacade.cpfCnpjAlreadyExists(form.getCpfcnpj()) != null;

		model.addAttribute("pageType", "CHECKOUT");

//		
//		
//		if(cpfCnpjAlreadyRegistered)
//		{
//			//SPJ 23-06-2014 disable cpf cnpj validation on guest checkout 
//		//	bindingResult.rejectValue("cpfcnpj", "register.cpfexists");
//		}
//		
		return processAnonymousCheckoutUserRequest(form, bindingResult, model, request, response);
	}
	
	protected String processAnonymousCheckoutUserRequest(final HeringGuestForm form,final BindingResult bindingResult, final Model model,
			final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException
	{
		try
		{
			if (bindingResult.hasErrors())
			{
				model.addAttribute(form);
				model.addAttribute(new LoginForm());
				model.addAttribute(new HeringRegisterForm());
//				GlobalMessages.addErrorMessage(model, "form.global.error");
				return handleRegistrationError(model);
			}
			
			final GuestRegisterData guestData = new GuestRegisterData();
			guestData.setTitleCode(form.getTitleCode());
			guestData.setFirstName(form.getFirstName());
			guestData.setLastName(form.getLastName());
			guestData.setComplement(form.getComplement());
			guestData.setAddress(form.getStreet());
			guestData.setNumber(form.getNumber());
			guestData.setZipCode(form.getZipCode());
			guestData.setCity(form.getPlace());
			guestData.setCountry(form.getCountry());
			guestData.setPhone(form.getTelephone());
			guestData.setEmail(form.getEmail());
			
			((HeringCustomerFacade) getCustomerFacade()).registerGuest(guestData);
			
//			((HeringCustomerFacade) getCustomerFacade()).createGuestUserForAnonymousCheckout(form.getEmail(), form.getCpfcnpj(), 
//						getMessageSource().getMessage("text.guest.customer",null,getI18nService().getCurrentLocale()), form.getDateBirthday(), form.getGender());
//			getGuidCookieStrategy().setCookie(request, response);
//			getSessionService().setAttribute(WebConstants.ANONYMOUS_CHECKOUT, Boolean.TRUE);
		}
		catch (final DuplicateUidException e)
		{
			LOG.warn("guest registration failed: " + e);
			GlobalMessages.addErrorMessage(model, "form.global.error");
			return handleRegistrationError(model);
		}

		return REDIRECT_PREFIX + getSuccessRedirect(request, response);

	}


	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String checkoutRegister(@RequestParam(value = "error", defaultValue = "false") final boolean loginError,
				final HttpSession session, final Model model, final HttpServletRequest request)
			throws CMSItemNotFoundException
	{
		return doCheckoutLogin(loginError, session, model, request);
	}

	@RequestMapping(value = "/guest", method = RequestMethod.GET)
	public String doAnonymousCheckout(@RequestParam(value = "error", defaultValue = "false") final boolean loginError,
					final HttpSession session, final Model model, final HttpServletRequest request) throws CMSItemNotFoundException
	{
		return doCheckoutLogin(loginError, session, model, request);
	}

	@Override
	protected String getView()
	{
		return ControllerConstants.Views.Pages.Checkout.CheckoutLoginPage;
	}

	@Override
	protected String getSuccessRedirect(final HttpServletRequest request, final HttpServletResponse response)
	{
		if (hasItemsInCart())
		{
			return getCheckoutUrl();
		}
		// Redirect to the main checkout controller to handle checkout.
		return "/checkout";
	}

	/**
	 * Checks if there are any items in the cart.
	 * 
	 * @return returns true if items found in cart.
	 */
	protected boolean hasItemsInCart()
	{
		final CartData cartData = getCheckoutFlowFacade().getCheckoutCart();

		return (cartData.getEntries() != null && !cartData.getEntries().isEmpty());
	}

	protected String getCheckoutUrl()
	{
		// Default to the multi-step checkout
		if("dzarm".equals(baseStoreService.getCurrentBaseStore().getUid())) {
			return "/checkout/multi";
		}
		return "/checkout/single";
	}

	protected HeringGuestValidator getGuestValidator()
	{
		return guestValidator;
	}

	protected CheckoutFlowFacade getCheckoutFlowFacade()
	{
		return checkoutFlowFacade;
	}

	@Override
	protected GUIDCookieStrategy getGuidCookieStrategy()
	{
		return guidCookieStrategy;
	}

	protected AuthenticationManager getAuthenticationManager()
	{
		return authenticationManager;
	}

	/**
	 * @return the registrationValidator
	 */
	public HeringRegistrationValidator getRegistrationValidator()
	{
		return registrationValidator;
	}
	
	protected CommerceCommonI18NService getCommerceCommonI18NService()
	{
		return commerceCommonI18NService;
	}
	
}
