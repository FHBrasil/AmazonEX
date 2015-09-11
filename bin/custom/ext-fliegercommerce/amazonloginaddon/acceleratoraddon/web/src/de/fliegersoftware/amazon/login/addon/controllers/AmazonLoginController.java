package de.fliegersoftware.amazon.login.addon.controllers;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.fliegersoftware.amazon.core.enums.AccountMatchingStrategyEnum;
import de.fliegersoftware.amazon.core.facades.AmazonUserFacade;
import de.fliegersoftware.amazon.core.services.AmazonConfigService;
import de.fliegersoftware.amazon.login.addon.constants.AmazonLoginAddonConstants;
import de.fliegersoftware.amazon.login.addon.data.AmazonLoginRegisterData;
import de.fliegersoftware.amazon.login.addon.forms.AmazonLoginForm;
import de.fliegersoftware.amazon.login.addon.security.AmazonAutoLoginStrategy;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.security.AutoLoginStrategy;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.model.user.CustomerModel;

@Controller
@Scope("tenant")
public class AmazonLoginController extends AbstractPageController {

	protected static final Logger LOG = Logger.getLogger(AmazonLoginController.class);

	public static final String REDIRECT_PREFIX = "redirect:";
	public static final String MY_ACCOUNT = "/my-account";
	private static final String AMAZON_ACCOUNT_CONFIRMATION_PAGE = "amazonAccountConfirmation";

	private AmazonUserFacade amazonUserFacade;

	@Resource
	private AmazonAutoLoginStrategy amazonAutoLoginStrategy;
	
	@Resource
	private AmazonConfigService amazonConfigService;
	
	@Resource(name = "customerFacade")
	private CustomerFacade customerFacade;
	
	@Resource(name = "autoLoginStrategy")
	private AutoLoginStrategy autoLoginStrategy;

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
		registerData.setFirstName(firstName(name));
		registerData.setLastName(lastName(name));
		registerData.setLogin(email);
		final String randomPass = UUID.randomUUID().toString();
		registerData.setPassword(randomPass);
		registerData.setAmazonCustomerId(customerId);
		
		if(getAmazonUserFacade().isAmazonCustomerExisting(customerId))
		{	
			CustomerModel amazonCustomerModel = getAmazonUserFacade().getAmazonCustomer(customerId);
			amazonAutoLoginStrategy.login(amazonCustomerModel.getUid(), customerId, request, response);
			return REDIRECT_PREFIX+MY_ACCOUNT;
		}
		
		if(!getAmazonUserFacade().isUserExisting(email))
		{			
			try 
			{				
				getAmazonUserFacade().register(registerData);
				amazonAutoLoginStrategy.login(email, customerId, request, response);
				
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
					getAmazonUserFacade().registerGuestUser(registerData);
					
					CustomerModel amazonCustomerModel = getAmazonUserFacade().getAmazonCustomer(customerId);
					
					amazonAutoLoginStrategy.login(amazonCustomerModel.getUid(), customerId, request, response);

					return REDIRECT_PREFIX+MY_ACCOUNT;
				} 
				catch (DuplicateUidException e) 
				{
					LOG.warn("DuplicateUidException for email " + email);
					e.printStackTrace();
				}	
			}
		}
		
		
		amazonAutoLoginStrategy.login(email, customerId, request, response);
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
			
			getAmazonUserFacade().update(registerData);
			
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
		registerData.setFirstName(firstName(amazonForm.getName()));
		registerData.setLastName(lastName(amazonForm.getName()));

		try 
		{				
			getAmazonUserFacade().registerGuestUser(registerData);
			
			CustomerModel amazonCustomerModel = getAmazonUserFacade().getAmazonCustomer(amazonForm.getAmazonId());
			
			amazonAutoLoginStrategy.login(amazonCustomerModel.getUid(), amazonForm.getAmazonId(), request, response);

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
			final CustomerData customerData = customerFacade.getCurrentCustomer();
			
			getAmazonUserFacade().deleteAmazonCustomer(customerData);
			
			return REDIRECT_PREFIX+MY_ACCOUNT;
		} 
		catch (AuthenticationException e)
		{
			LOG.warn(e.getMessage());
			return REDIRECT_PREFIX+MY_ACCOUNT;
		}
	}
	
	private String prepareAssociateAccount(Model model, String name, String email, String customerId)
	{
		AmazonLoginForm amazonLoginForm = new AmazonLoginForm();

		model.addAttribute("amazonLoginForm", amazonLoginForm);
		amazonLoginForm.setName(name);
		amazonLoginForm.setEmail(email);
		amazonLoginForm.setAmazonId(customerId);
		
		try 
		{
			storeCmsPageInModel(model, getContentPageForLabelOrId(AMAZON_ACCOUNT_CONFIRMATION_PAGE));
		} 
		catch (CMSItemNotFoundException e) 
		{
			LOG.warn(e.getMessage());
		}
		return AmazonLoginAddonConstants.Views.Pages.AmazonConfirmAccountPage;
	}

	private static String firstName(String name) 
	{
		String fn[] = name.split(" ", 2);
		
		return fn[0];
	}

	private static String lastName(String name) 
	{
		String ln[] = name.split(" ", 2);
		try 
		{
			return ln[1];
		} 
		catch (ArrayIndexOutOfBoundsException e) 
		{
			//
		}
		return "(Surname)";
	}

	public AmazonUserFacade getAmazonUserFacade() {
		return amazonUserFacade;
	}

	public void setAmazonUserFacade(AmazonUserFacade amazonUserFacade) {
		this.amazonUserFacade = amazonUserFacade;
	}

	public CustomerFacade getCustomerFacade() {
		return customerFacade;
	}

	public void setCustomerFacade(CustomerFacade customerFacade) {
		this.customerFacade = customerFacade;
	}
	
	public AutoLoginStrategy getAutoLoginStrategy() {
		return autoLoginStrategy;
	}
	
	public void setAutoLoginStrategy(AutoLoginStrategy autoLoginStrategy) {
		this.autoLoginStrategy = autoLoginStrategy;
	}
}
