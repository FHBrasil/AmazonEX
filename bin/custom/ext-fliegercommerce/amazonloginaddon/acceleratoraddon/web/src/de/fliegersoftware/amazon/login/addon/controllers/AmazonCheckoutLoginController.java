package de.fliegersoftware.amazon.login.addon.controllers;

import javax.annotation.Resource;
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

import de.fliegersoftware.amazon.login.addon.facades.customer.AmazonCustomerFacade;
import de.fliegersoftware.amazon.login.addon.forms.AmazonGuestForm;
import de.fliegersoftware.amazon.login.addon.forms.validation.AmazonGuestValidator;
import de.fliegersoftware.amazon.login.addon.security.AmazonAutoLoginStrategy;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractLoginPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.LoginForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;

@Controller
@Scope("tenant")
@RequestMapping(value = "/login/checkout/amazon")
public class AmazonCheckoutLoginController extends AbstractLoginPageController {

	@Resource(name = "amazonGuestValidator")
	private AmazonGuestValidator amazonGuestValidator;

	@Resource
	private CheckoutFacade checkoutFacade;

	@Resource(name = "amazonCustomerFacade")
	private AmazonCustomerFacade amazonCustomerFacade;

	@Resource
	private AmazonAutoLoginStrategy amazonAutoLoginStrategy;
	
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
		return processAnonymousCheckoutUserRequest(form, bindingResult, model, request, response);
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
		CustomerData customer = getCustomerFacade().getCurrentCustomer();
		amazonAutoLoginStrategy.login(customer.getUid(), form.getAmazonGuestId(), request, response);
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

	@Override
	protected AmazonCustomerFacade getCustomerFacade() {
		return (AmazonCustomerFacade) amazonCustomerFacade;
	}
}