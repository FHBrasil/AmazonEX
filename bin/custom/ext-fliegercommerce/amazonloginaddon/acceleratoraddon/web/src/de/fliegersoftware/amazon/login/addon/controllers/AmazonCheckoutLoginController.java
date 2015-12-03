package de.fliegersoftware.amazon.login.addon.controllers;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.CookieGenerator;

import de.fliegersoftware.amazon.core.enums.AccountMatchingStrategyEnum;
import de.fliegersoftware.amazon.login.addon.data.AmazonLoginRegisterData;
import de.fliegersoftware.amazon.login.addon.forms.AmazonGuestForm;
import de.fliegersoftware.amazon.login.addon.forms.validation.AmazonGuestValidator;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.strategies.CheckoutCustomerStrategy;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.yacceleratorstorefront.constants.WebConstants;
import de.hybris.platform.yacceleratorstorefront.controllers.util.GlobalMessages;
import de.hybris.platform.yacceleratorstorefront.forms.LoginForm;
import de.hybris.platform.yacceleratorstorefront.forms.RegisterForm;

@Controller
@Scope("tenant")
@RequestMapping(value = "/login/checkout/amazon")
public class AmazonCheckoutLoginController extends AbstractAmazonLoginController {
	
	public static final String REQUEST_MODEL_ATTRIBUTE_NAME = "request";
	public static final String SECURE_GUID_SESSION_KEY = "acceleratorSecureGUID";
	
	@Resource(name = "amazonGuestValidator")
	private AmazonGuestValidator amazonGuestValidator;

	@Resource
	private CheckoutFacade checkoutFacade;

	@Resource(name = "guidCookieGenerator")
	private CookieGenerator cookieGenerator;
	
	@Resource(name = "checkoutCustomerStrategy")
	private CheckoutCustomerStrategy checkoutCustomerStrategy;

	@RequestMapping(method = RequestMethod.GET)
	public String doCheckoutLogin(@RequestParam(value = "error", defaultValue = "false") final boolean loginError,
			final HttpSession session, final Model model, final HttpServletRequest request) throws CMSItemNotFoundException
	{
		return getDefaultLoginPage(loginError, session, model);
	}

	@RequestMapping(value = "/guest", method = RequestMethod.GET)
	public String doAnonymousCheckout(@RequestParam(value = "error", defaultValue = "false") final boolean loginError,
					final HttpSession session, final Model model, final HttpServletRequest request) throws CMSItemNotFoundException
	{
		return doCheckoutLogin(loginError, session, model, request);
	}

	@RequestMapping(value = "/guest", method = RequestMethod.POST)
	public String doAnonymousCheckout(final AmazonGuestForm form, final BindingResult bindingResult, final Model model,
			final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException
	{
		getGuestValidator().validate(form, bindingResult);
		if(getCheckoutCustomerStrategy().isAnonymousCheckout()) {
			if(!Boolean.TRUE.equals(getSessionService().getAttribute(WebConstants.ANONYMOUS_CHECKOUT))) {
				if (amazonConfigService.allowGuestCheckout()) {
					return processAnonymousCheckoutUserRequest(form, bindingResult, model, request, response);
				} else {
					return processLoginWithAmazon(form, bindingResult, model, request, response);
				}
			}
		} else if (!isHardLogin(model)) {
			return processLoginWithAmazon(form, bindingResult, model, request, response);
		}
		return REDIRECT_PREFIX + getSuccessRedirect(request, response);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String doCheckoutLogin(final AmazonGuestForm form, final BindingResult bindingResult, final Model model,
			final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException
	{
		getGuestValidator().validate(form, bindingResult);
		return processCheckoutLogin(form, bindingResult, model, request, response);
	}

	protected String processCheckoutLogin(AmazonGuestForm form,
			BindingResult bindingResult, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		amazonAutoLoginStrategy.login(form.getAmazonGuestId(), request, response);
		return REDIRECT_PREFIX + getSuccessRedirect(request, response);
	}
	
	protected String processAnonymousCheckoutUserRequest(AmazonGuestForm form,
			BindingResult bindingResult, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws CMSItemNotFoundException {
		try {
			if (bindingResult.hasErrors())
			{
				model.addAttribute(form);
				model.addAttribute(new LoginForm());
				model.addAttribute(new RegisterForm());
				GlobalMessages.addErrorMessage(model, "form.global.error");
				return handleRegistrationError(model);
			}
			String name = form.getAmazonGuestName();
			String email = form.getAmazonGuestEmail();
			String amazonCustomerId = form.getAmazonGuestId();
			getCustomerFacade().updateCartWithUserForAnonymousCheckout(amazonCustomerId, email, name);
			getGuidCookieStrategy().setCookie(request, response);
			getSessionService().setAttribute(WebConstants.ANONYMOUS_CHECKOUT, Boolean.TRUE);
		} catch (DuplicateUidException e) {
			LOG.warn("guest registration failed: " + e);
			GlobalMessages.addErrorMessage(model, "form.global.error");
			return handleRegistrationError(model);
		}

		return REDIRECT_PREFIX + getSuccessRedirect(request, response);
	}
	
	protected String processLoginWithAmazon(AmazonGuestForm form,
			BindingResult bindingResult, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		
		final AmazonLoginRegisterData registerData = new AmazonLoginRegisterData();
		registerData.setFirstName(getFirstNameByName(form.getAmazonGuestName()));
		registerData.setLastName(getLastNameByName(form.getAmazonGuestName()));
		registerData.setLogin(form.getAmazonGuestEmail());
		final String randomPass = UUID.randomUUID().toString();
		registerData.setPassword(randomPass);
		registerData.setAmazonCustomerId(form.getAmazonGuestId());
		
		if(getUserFacade().isAmazonCustomerExisting(form.getAmazonGuestId()))
		{	
			amazonAutoLoginStrategy.login(form.getAmazonGuestId(), request, response);
			return REDIRECT_PREFIX + getSuccessRedirect(request, response);
		}
		
		if(!getUserFacade().isUserExisting(form.getAmazonGuestEmail()))
		{			
			try 
			{				
				getUserFacade().register(registerData);
				amazonAutoLoginStrategy.login(form.getAmazonGuestId(), request, response);
				
				return REDIRECT_PREFIX + getSuccessRedirect(request, response);
			} 
			catch (DuplicateUidException e) 
			{
				LOG.warn("DuplicateUidException for email " + form.getAmazonGuestEmail());
				e.printStackTrace();
			}	
		} else 
		{			
			if (AccountMatchingStrategyEnum.EMAILADDRESSBASED.equals(amazonConfigService.getAccountMatchingStrategy())) {
				
				return prepareAssociateAccount(model, form.getAmazonGuestName(), form.getAmazonGuestEmail(), form.getAmazonGuestId());
			
			} else if (AccountMatchingStrategyEnum.NOMATCHING.equals(amazonConfigService.getAccountMatchingStrategy())) 
			{
				try 
				{				
					getUserFacade().registerGuestUser(registerData);
					
					amazonAutoLoginStrategy.login(form.getAmazonGuestId(), request, response);

					return REDIRECT_PREFIX + getSuccessRedirect(request, response);
				} 
				catch (DuplicateUidException e) 
				{
					LOG.warn("DuplicateUidException for email " + form.getAmazonGuestEmail());
					e.printStackTrace();
				}	
			}
		}
		
		return REDIRECT_PREFIX + getSuccessRedirect(request, response);
	}
	
	protected boolean isHardLogin(final Model model) {
		HttpServletRequest request = (HttpServletRequest) model.asMap().get(REQUEST_MODEL_ATTRIBUTE_NAME);
		final String guid = (String)request.getSession().getAttribute(SECURE_GUID_SESSION_KEY);
		if (checkForGUIDCookie(request, guid)) {
			return true;
		}
		return false;
	}

	protected boolean checkForGUIDCookie(final HttpServletRequest request,
			final String guid) {
		if (guid != null && request.getCookies() != null) {
			final String guidCookieName = cookieGenerator.getCookieName();
			if (guidCookieName != null) {
				for (final Cookie cookie : request.getCookies()) {
					if (guidCookieName.equals(cookie.getName())) {
						if (guid.equals(cookie.getValue())) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	@Override
	protected AbstractPageModel getCmsPage() throws CMSItemNotFoundException {
		return getContentPageForLabelOrId("checkout-login");
	}

	/**
	 * Checks if there are any items in the cart.
	 * 
	 * @return returns true if items found in cart.
	 */
	protected boolean hasItemsInCart()
	{
		final CartData cartData = getCheckoutFacade().getCheckoutCart();

		return (cartData.getEntries() != null && !cartData.getEntries().isEmpty());
	}

	protected String getCheckoutUrl()
	{
		return "/checkout/amazon";
	}

	@Override
	protected String getSuccessRedirect(HttpServletRequest request,
			HttpServletResponse response) {
		if (hasItemsInCart())
		{
			return getCheckoutUrl();
		}
		// Redirect to the main checkout controller to handle checkout.
		return "/checkout";
	}

	@Override
	protected String getView() {
		return AmazonloginaddonControllerConstants.Views.Pages.Checkout.CheckoutLoginPage;
	}

	protected AmazonGuestValidator getGuestValidator() {
		return amazonGuestValidator;
	}

	protected CheckoutFacade getCheckoutFacade() {
		return checkoutFacade;
	}

	public CheckoutCustomerStrategy getCheckoutCustomerStrategy() {
		return checkoutCustomerStrategy;
	}

	public void setCheckoutCustomerStrategy(CheckoutCustomerStrategy checkoutCustomerStrategy) {
		this.checkoutCustomerStrategy = checkoutCustomerStrategy;
	}
}