/**
 * 
 */
package br.hering.storefront.controllers.pages;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.order.CommerceCartRestorationException;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.acceleratorstorefrontcommons.forms.LoginForm;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.user.Customer;
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.PasswordEncoderService;
import de.hybris.platform.servicelayer.user.UserService;

import br.hering.core.customer.exceptions.CustomerAuthenticationException;
import br.hering.facades.customer.HeringCustomerFacade;
import br.hering.storefront.controllers.ControllerConstants;

import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.jalo.user.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Vinicius de Souza
 *
 */
@Controller
@RequestMapping("/login")
public class LoginHeaderPageController extends AbstractHeringLoginController
{
	@Resource(name = "customerFacade")
	protected HeringCustomerFacade customerFacade;
	
	private static final Logger LOG = Logger.getLogger(LoginHeaderPageController.class);
	private HttpSessionRequestCache httpSessionRequestCache;
	
	@Resource
	private CartService cartService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private SessionService sessionService;
	
	@Resource
	private CartFacade cartFacade;
	
//	@RequestMapping(value = "loginOver", method = RequestMethod.POST)
	public String login(final LoginForm loginForm, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
			final Model model, final HttpSession session, final BindingResult bindingResult, 
			@RequestParam(value = "email") final String email,
			@RequestParam(value = "password") final String password)
	{
		LOG.info("loginOver");
		LOG.info(email);
		LOG.info(password);
		
		String redirect = getReturnRedirectUrl(request);
		
		LOG.info("redirecionando para: "  +redirect);

		if(redirect.matches("redirect:http://([a-zA-Z0-9:/\\.])*store/")) {
			redirect = redirect+"pt/";
			LOG.info("matches redirecionando para: "  +redirect);
		}
		
		try 
		{
			if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password))
			{				
				return getDefaultLoginPage(true, session, model);
			}
			
			customerFacade.updatePasswordWithSalt(email, password);
			getAutoLoginStrategy().login(email.toLowerCase(), password, request, response);
						
			checkCartForUser(email);
			User user = UserManager.getInstance().getUserByLogin(email);
			JaloSession.getCurrentSession().setUser(user);
			sessionService.setAttribute(WebConstants.CART_RESTORATION, cartFacade.restoreSavedCart(null));
			
			CustomerData customer = customerFacade.getCurrentCustomer();
			LOG.info(customer.getDisplayUid());
			
			if(StringUtils.isEmpty(customer.getDisplayUid()))
			{
				return getDefaultLoginPage(true, session, model);
			}
		}
		catch (final CommerceCartRestorationException e)
		{
			sessionService.setAttribute(WebConstants.CART_RESTORATION, "basket.restoration.errorMsg");
		}
		catch (CustomerAuthenticationException e) {
			model.addAttribute("loginError", Boolean.valueOf(true));
			GlobalMessages.addErrorMessage(model, "login.error.account.not.found.title");
			return redirect+"login?error=true";
		}
		catch (Exception e)
		{
			LOG.error("***************ERRO CONTROLLER LOGIN", e);
			try
			{
				return getDefaultLoginPage(true, session, model);	
			}
			catch(CMSItemNotFoundException c)
			{
				LOG.error("***************ERRO CONTROLLER LOGIN", c);
			}
		}
		
		return redirect;
	}
	
	@Override
	protected AbstractPageModel getCmsPage() throws CMSItemNotFoundException
	{
		return getContentPageForLabelOrId("login");
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
	protected String getView()
	{
		return ControllerConstants.Views.Pages.Account.AccountLoginPage;
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
}