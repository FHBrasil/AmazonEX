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

import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.commercefacades.i18n.I18NFacade;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.order.CommerceCartRestorationException;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.Config;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.hering.core.customer.exceptions.CustomerAuthenticationException;
import br.hering.facades.customer.HeringCustomerFacade;
import br.hering.storefront.controllers.ControllerConstants;
import br.hering.storefront.forms.HeringRegisterForm;
import br.hering.storefront.forms.validation.HeringRegistrationValidator;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.forms.LoginForm;

import org.springframework.validation.Errors;


/**
 * Login Controller. Handles login and register for the account flow.
 */
@Controller
@Scope("tenant")
@RequestMapping(value = "/login")
public class LoginPageController extends AbstractHeringLoginController
{
	private static final Logger LOG = Logger.getLogger(LoginPageController.class);

	public static final String REDIRECT_PREFIX = "redirect:";
	public static final String REDIRECT_LOGIN = REDIRECT_PREFIX + "/login";
	private HttpSessionRequestCache httpSessionRequestCache;

	@Resource(name = "heringRegistrationValidator")
	private HeringRegistrationValidator registrationValidator;

	@Resource(name = "customerFacade")
	protected HeringCustomerFacade customerFacade;

	@Resource
	private UserService userService;

	@Resource
	private SessionService sessionService;

	@Resource
	private CartFacade cartFacade;

	@Resource
	private CartService cartService;
	
	@Resource
	private BaseSiteService baseSiteService;
	
	@Resource
	private I18NFacade i18NFacade;

	@Resource
	private BaseStoreService baseStoreService;

	/**
	 * @return the customerFacade
	 */
	public HeringCustomerFacade getCustomerFacade()
	{
		return customerFacade;
	}

	/**
	 * @param customerFacade
	 *           the customerFacade to set
	 */
	public void setCustomerFacade(HeringCustomerFacade customerFacade)
	{
		this.customerFacade = customerFacade;
	}

	@Override
	protected String getView()
	{
		return ControllerConstants.Views.Pages.Account.AccountLoginPage;
	}

	@Override
	protected String getSuccessRedirect(final HttpServletRequest request, final HttpServletResponse response)
	{
		if (httpSessionRequestCache.getRequest(request, response) != null)
		{
			return httpSessionRequestCache.getRequest(request, response).getRedirectUrl();
		}
		return "/my-account";
	}

	@Override
	protected AbstractPageModel getCmsPage() throws CMSItemNotFoundException
	{
		return getContentPageForLabelOrId("login");
	}


	@Resource(name = "httpSessionRequestCache")
	public void setHttpSessionRequestCache(final HttpSessionRequestCache accHttpSessionRequestCache)
	{
		this.httpSessionRequestCache = accHttpSessionRequestCache;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String doLogin(@RequestHeader(value = "referer", required = false) final String referer,
			@RequestParam(value = "error", defaultValue = "false") final boolean loginError,
			@RequestParam(value = "forgotPassword", defaultValue = "false") final String forgotPassword, final Model model,
			final HttpServletRequest request, final HttpServletResponse response, final HttpSession session)
			throws CMSItemNotFoundException
	{
		String result = "false";
		if (!StringUtils.isEmpty(forgotPassword))
		{
			if (forgotPassword.compareTo("true") == 0)
			{
				result = "true";
			}
			else
			{
				result = "false";
			}
		}
		model.addAttribute("forgotPassword", result);
		model.addAttribute("pageType", "LOGIN");
		model.addAttribute("regions", i18NFacade.getRegionsForCountryIso("DE"));
		model.addAttribute("pf", Boolean.TRUE);

		if (!loginError)
		{
			storeReferer(referer, request, response);
		}

		String returnPage = getDefaultLoginPage(loginError, session, model);

		return returnPage;
	}

	@RequestMapping(value = "/loginOver", method = RequestMethod.POST)
	public String login(final LoginForm loginForm, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes, final Model model, final HttpSession session, final BindingResult bindingResult)
	{
		LOG.info("loginOver");
		final String email = loginForm.getJ_username();
		final String password = loginForm.getJ_password();

		String redirect = getReturnRedirectUrl(request);

		LOG.info("redirecionando para: " + redirect);

		if (redirect.matches("redirect:http://([a-zA-Z0-9:/\\.])*store/"))
		{
			redirect = redirect + "pt/";
			LOG.info("matches redirecionando para: " + redirect);
		}
		else if (redirect.contains("/login/checkout"))
		{
			// from /login/checkout page, goes to /checkout
			redirect = "redirect:/checkout";
			LOG.info("matches redirecionando para: " + redirect);
		}
		else if (redirect.matches("([a-zA-Z0-9:/\\.-])*store/pt/login([?]error=true)*"))
		{
			// from /login page, goes to /my-account
			redirect = "redirect:/my-account";
			LOG.info("matches redirecionando para: " + redirect);
		}
		else if (redirect.matches("redirect:https://([a-zA-Z0-9:/\\.])*store/pt/login/loginOver"))
		{
			// from /login page, goes to /my-account
			redirect = "redirect:/my-account";
			LOG.info("matches redirecionando para: " + redirect);
		}
		else if (redirect.contains("forgotPassword=true"))
		{
				// from /login page, goes to /my-account
				redirect = "redirect:/my-account";
				LOG.info("matches redirecionando para: " + redirect);
		}
		else if (redirect.contains("/register"))
		{
			// from /login page, goes to /my-account
			redirect = "redirect:/my-account";
			LOG.info("matches redirecionando para: " + redirect);
		}

		LOG.info("Redirect definido: "+redirect);
		try
		{
			if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password))
			{
				return getDefaultLoginPage(true, session, model, redirect);
			}

			customerFacade.updatePasswordWithSalt(email, password);
			getAutoLoginStrategy().login(email.toLowerCase(), password, request, response);

			checkCartForUser(email);
			User user = UserManager.getInstance().getUserByLogin(email);
			JaloSession.getCurrentSession().setUser(user);
			sessionService.setAttribute(WebConstants.CART_RESTORATION, cartFacade.restoreSavedCart(null));

			CustomerData customer = customerFacade.getCurrentCustomer();
			LOG.info(customer.getDisplayUid());

			if (StringUtils.isEmpty(customer.getDisplayUid()))
			{
				return getDefaultLoginPage(true, session, model, redirect);
			}
		}
		catch (final CommerceCartRestorationException e)
		{
			sessionService.setAttribute(WebConstants.CART_RESTORATION, "basket.restoration.errorMsg");
		}
		catch (CustomerAuthenticationException e)
		{
			model.addAttribute("loginError", Boolean.valueOf(true));
			LOG.info("Login falhou!");
			//GlobalMessages.addErrorMessage(model, "login.error.account.not.found.title");
			return getDefaultLoginPage(true, session, model, redirect);
		}
		catch (Exception e)
		{
			return getDefaultLoginPage(true, session, model, redirect);
		}

		return redirect;
	}
	
	protected String getDefaultLoginPage(boolean loginError, HttpSession session, Model model, String redirect)
	{
		final String baseStore = baseStoreService.getCurrentBaseStore().getUid();
		
//		if("dzarm".equals(baseStore))
//		{
//			try
//			{
//				return super.getDefaultLoginPage(loginError, session, model);
//			}
//			catch (CMSItemNotFoundException c)
//			{
//				LOG.error("***************ERRO CONTROLLER LOGIN", c);
//			}
//		}
		
		return "redirect:/login"+(loginError?"?error=true":"");		
	}

	public void checkCartForUser(String username)
	{
		// check if the user of the cart matches the current user and if the
		// user is not anonymous. If otherwise, remove delete the session cart as it might
		// be stolen / from another user
		final String sessionCartUserId = cartService.getSessionCart().getUser().getUid();

		if (!username.equals(sessionCartUserId) && !sessionCartUserId.equals(userService.getAnonymousUser().getUid()))
		{
			cartService.setSessionCart(null);
		}
	}

	protected void storeReferer(final String referer, final HttpServletRequest request, final HttpServletResponse response)
	{
		if (StringUtils.isNotBlank(referer) && !StringUtils.endsWith(referer, "/login"))
		{
			httpSessionRequestCache.saveRequest(request, response);
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(@RequestHeader(value = "referer", required = false) final String referer,
			final HeringRegisterForm form, final BindingResult bindingResult, final Model model, final HttpServletRequest request,
			HttpServletResponse response, final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		getRegistrationValidator().validate(form, bindingResult, model);
		boolean cpfCnpjAlreadyRegistered = customerFacade.cpfCnpjAlreadyExists(form.getCpfcnpj()) != null;

		if (cpfCnpjAlreadyRegistered)
		{
			GlobalMessages.addErrorMessage(model, "register.cpfexists");
			bindingResult.rejectValue("cpfcnpj", "register.cpfexists");
		}

		model.addAttribute("pageType", "LOGIN");
		model.addAttribute("regions", i18NFacade.getRegionsForCountryIso("DE"));
		model.addAttribute("pf", Boolean.parseBoolean(form.getPessoaFisica()));
		
		if(!Boolean.parseBoolean(form.getPessoaFisica()))
		{
			form.setGender(Gender.UNDEFINED.name());
		}

		return processRegisterUserRequest(referer, form, bindingResult, model, request, response, redirectModel);
	}

	/**
	 * @return the registrationValidator
	 */
	public HeringRegistrationValidator getRegistrationValidator()
	{
		return registrationValidator;
	}
}