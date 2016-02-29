package de.fliegersoftware.amazon.login.addon.controllers;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.fliegersoftware.amazon.core.enums.AccountMatchingStrategyEnum;
import de.fliegersoftware.amazon.login.addon.constants.AmazonLoginAddonConstants;
import de.fliegersoftware.amazon.core.data.AmazonLoginRegisterData;
import de.fliegersoftware.amazon.login.addon.forms.AmazonLoginForm;
import de.fliegersoftware.amazon.login.addon.forms.AmazonManualAdditionFirstLoginForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.model.user.CustomerModel;

@Controller
@Scope("tenant")
public class AmazonLoginPageController extends AbstractAmazonLoginController {

	protected static final Logger LOG = Logger.getLogger(AmazonLoginPageController.class);

	public static final String REDIRECT_PREFIX = "redirect:";
	public static final String MY_ACCOUNT = "/my-account";
	private static final String AMAZON_MANUAL_ADDITION_FIRST_LOGIN = "amazonManualAdditionOnFirstLogin";
	private HttpSessionRequestCache httpSessionRequestCache;
	
	@RequestMapping(value = "/register/login-with-amazon", method = RequestMethod.POST)
	public String loginWithAmazon (
			@RequestParam(value = "name") final String name,
			@RequestParam(value = "primaryEmail") final String email,
			@RequestParam(value = "customerId") final String customerId,
			final HttpServletRequest request,
			final HttpServletResponse response, 
			Model model)
			throws CMSItemNotFoundException 
	{
		final AmazonLoginRegisterData registerData = new AmazonLoginRegisterData();
		registerData.setFirstName(getFirstNameByName(name));
		registerData.setLastName(getLastNameByName(name));
		registerData.setLogin(email);
		final String randomPass = UUID.randomUUID().toString();
		registerData.setPassword(randomPass);
		registerData.setAmazonCustomerId(customerId);
		
		if(getUserFacade().isAmazonCustomerExisting(customerId))
		{	
			amazonAutoLoginStrategy.login(customerId, request, response);
			return REDIRECT_PREFIX+MY_ACCOUNT;
		}
		
		if(!getUserFacade().isUserExisting(email))
		{			
			try 
			{				
				getUserFacade().register(registerData);
				amazonAutoLoginStrategy.login(customerId, request, response);
				
				return REDIRECT_PREFIX+MY_ACCOUNT;
			} 
			catch (DuplicateUidException e) 
			{
				LOG.warn("DuplicateUidException for email " + email);
				e.printStackTrace();
			}	
		} else 
		{			
			if (AccountMatchingStrategyEnum.EMAILADDRESSBASED.equals(amazonConfigService.getAccountMatchingStrategy())) {
				
				return prepareAssociateAccount(model, name, email, customerId);
			
			} else if (AccountMatchingStrategyEnum.NOMATCHING.equals(amazonConfigService.getAccountMatchingStrategy())) 
			{
				try 
				{				
					getUserFacade().registerGuestUser(registerData);
					
					amazonAutoLoginStrategy.login(customerId, request, response);

					return REDIRECT_PREFIX+MY_ACCOUNT;
				} 
				catch (DuplicateUidException e) 
				{
					LOG.warn("DuplicateUidException for email " + email);
					e.printStackTrace();
				}	
			}
		}
		
		
		amazonAutoLoginStrategy.login(customerId, request, response);
		return REDIRECT_PREFIX+MY_ACCOUNT;
	}

	@RequestMapping(value = "/register/confirm-associate-account", method = RequestMethod.POST)
	public String loginWithAmazon(final AmazonLoginForm amazonForm,
			final BindingResult bindingResult,
			final HttpServletRequest request,
			final HttpServletResponse response,
			final Model model) 
	{		
		final AmazonLoginRegisterData registerData = new AmazonLoginRegisterData();
		registerData.setAmazonCustomerId(amazonForm.getAmazonId());
		registerData.setLogin(amazonForm.getEmail());
		
		try
		{
			getAutoLoginStrategy().login(amazonForm.getEmail(), amazonForm.getPwd(), request, response);
			
			getUserFacade().update(registerData);
			
			return REDIRECT_PREFIX+MY_ACCOUNT;
		} 
		catch (AuthenticationException e)
		{
			LOG.warn(e.getMessage());	
			return prepareAssociateAccount(model, amazonForm.getName(), amazonForm.getEmail(), amazonForm.getAmazonId());
		}
	}
	
	@RequestMapping(value = "/register/no-merge-account", method = RequestMethod.POST)
	public String noMergeAccount(final AmazonLoginForm amazonForm,
			final BindingResult bindingResult,
			final HttpServletRequest request,
			final HttpServletResponse response,
			final Model model) 
	{		
		final AmazonLoginRegisterData registerData = new AmazonLoginRegisterData();
		registerData.setAmazonCustomerId(amazonForm.getAmazonId());
		registerData.setLogin(amazonForm.getEmail());
		registerData.setFirstName(getFirstNameByName(amazonForm.getName()));
		registerData.setLastName(getLastNameByName(amazonForm.getName()));

		try 
		{				
			getUserFacade().registerGuestUser(registerData);
			
			amazonAutoLoginStrategy.login(amazonForm.getAmazonId(), request, response);

			return REDIRECT_PREFIX+MY_ACCOUNT;
		} 
		catch (DuplicateUidException e) 
		{
			LOG.warn("DuplicateUidException for email "+amazonForm.getEmail());
			return prepareAssociateAccount(model, amazonForm.getName(), amazonForm.getEmail(), amazonForm.getAmazonId());
		}	
	}
	
	@RequestMapping(value = "/unmerge-amazon-account", method = RequestMethod.GET)
	public String unmergeAmazonAccount(
			final HttpServletRequest request,
			final HttpServletResponse response,
			final Model model) 
	{		
		
		try
		{
			final CustomerData customerData = getCustomerFacade().getCurrentCustomer();
			
			getUserFacade().deleteAmazonCustomer(customerData);
			
			return REDIRECT_PREFIX+MY_ACCOUNT;
		} 
		catch (AuthenticationException e)
		{
			LOG.warn(e.getMessage());
			return REDIRECT_PREFIX+MY_ACCOUNT;
		}
	}
	
	@RequestMapping(value = "/register/manual-addition", method = RequestMethod.POST)
    public String manualAdditionFirstLogin(
            final AmazonManualAdditionFirstLoginForm amazonManualAdditionFirstLoginForm,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Model model) 
    {   
        final AmazonLoginRegisterData registerData = new AmazonLoginRegisterData();
        registerData.setLogin(amazonManualAdditionFirstLoginForm.getEmail());
        registerData.setTitleCode(amazonManualAdditionFirstLoginForm.getSalutation());
        
        getUserFacade().updateManualAddition(registerData);
        
        return REDIRECT_PREFIX+MY_ACCOUNT;      
    }
 
    private String prepareManualAdditionFirstLogin(Model model, String email)
    {
        AmazonManualAdditionFirstLoginForm amazonManualAdditionFirstLoginForm = new AmazonManualAdditionFirstLoginForm();
        amazonManualAdditionFirstLoginForm.setEmail(email);
        model.addAttribute("amazonManualAdditionFirstLoginForm", amazonManualAdditionFirstLoginForm);
        model.addAttribute("titles", getUserFacade().getTitles());        
                
        try 
        {
            storeCmsPageInModel(model, getContentPageForLabelOrId(AMAZON_MANUAL_ADDITION_FIRST_LOGIN));
        } 
        catch (CMSItemNotFoundException e) 
        {
            LOG.warn(e.getMessage());
        }
        return AmazonLoginAddonConstants.Views.Pages.AmazonManualAdditionFirstLoginPage;
    }

	@Override
	protected AbstractPageModel getCmsPage() throws CMSItemNotFoundException {
		return getContentPageForLabelOrId("login");
	}

	@Override
	protected String getSuccessRedirect(HttpServletRequest request, HttpServletResponse response) {
		if (httpSessionRequestCache.getRequest(request, response) != null)
		{
			return httpSessionRequestCache.getRequest(request, response).getRedirectUrl();
		}
		
		return REDIRECT_PREFIX+MY_ACCOUNT;
	}

	@Override
	protected String getView() {
		return AmazonloginaddonControllerConstants.Views.Pages.Account.AccountLoginPage;
	}
}
