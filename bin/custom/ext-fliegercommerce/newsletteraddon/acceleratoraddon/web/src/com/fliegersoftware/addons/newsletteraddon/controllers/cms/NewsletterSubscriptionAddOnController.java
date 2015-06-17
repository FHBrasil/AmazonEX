package com.fliegersoftware.addons.newsletteraddon.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fliegersoftware.newslettersubscription.data.NewsletterSubscriptionData;
import com.fliegersoftware.newslettersubscription.exceptions.DuplicatedNewsletterSubscriptionException;
import com.fliegersoftware.newslettersubscription.exceptions.NewsletterSubscriptionNotFound;
import com.fliegersoftware.newslettersubscription.facades.NewsletterSubscriptionFacade;

import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.user.UserService;


/**
 * @author luiza
 *
 */


@Controller
@RequestMapping("/newsletter")
public class NewsletterSubscriptionAddOnController extends AbstractAddOnPageController
{

	private NewsletterSubscriptionFacade newsletterSubscriptionFacade;

	private UserService userService;
	
	private I18NService i18nService;
		
	private CustomerFacade customerFacade;
	
	//private UserFacade userFacade;

	
	private static final Logger LOG = Logger.getLogger(NewsletterSubscriptionAddOnController.class);

	@RequestMapping(value = "/manage-subscription", method = RequestMethod.GET)
	public @ResponseBody String manageSubscription(
			HttpServletRequest request,
			RedirectAttributes redirectAttributes,
			@RequestParam (defaultValue = "false") final boolean subscription)
	{
		LOG.info("NewsletterAddon Controller: " +subscription);
		
		final CustomerData currentCustomerData = customerFacade.getCurrentCustomer();		
		final NewsletterSubscriptionData data = new NewsletterSubscriptionData();
		
		data.setFirstName(currentCustomerData.getFirstName());
		data.setLastName(currentCustomerData.getLastName());
		data.setEmail(currentCustomerData.getUid());
		data.setGenderCode(currentCustomerData.getGender().getCode());
		data.setTitleCode("Mr");
		//data.setTitleCode(currentCustomerData.getTitleCode());
		
		
		//arrumar estado do checkbox
		if (subscription)
		{	
			try
			{
				getNewsletterSubscriptionFacade().subscribe(data);

				final String message = getMessageSource().getMessage("text.fliegercommerce.texto127", null, getI18nService().getCurrentLocale());
				//Você se cadastrou na newsletter
				return message;
			}
			catch (DuplicatedNewsletterSubscriptionException e)
			{
				//do nothing
			}
								
		}		
		
		else
		{		
			try
			{
				getNewsletterSubscriptionFacade().unsubscribe(data);	
				
				final String message = getMessageSource().getMessage("text.fliegercommerce.texto126", null, getI18nService().getCurrentLocale());
				//Você não se cadastrou na newsletter
				return message;
			}
			catch (NewsletterSubscriptionNotFound e)
			{
				//do nothing
			}
			
		}
			
		return null;
	}
	
	
	@RequestMapping(value = "/newsletter-register", method = RequestMethod.POST)
	public @ResponseBody String registerNewsletter(
			HttpServletRequest request,
			RedirectAttributes redirectAttributes,
			@RequestParam(value = "firstName") final String firstName,
			@RequestParam(value = "lastName") final String lastName,
			@RequestParam(value = "email") final String email,
			@RequestParam(value = "titleCode") final String titleCode,
			@RequestParam(value = "genderCode") final String genderCode)
	{

		final NewsletterSubscriptionData data = new NewsletterSubscriptionData();
	
		data.setFirstName(firstName);
		data.setLastName(lastName);
		data.setEmail(email);
		data.setGenderCode(genderCode);
		data.setTitleCode(titleCode);
		
		LOG.info(" NewsletterAddon Controller: " +firstName+" - "+lastName+" - "+email+" - "+titleCode+" - "+genderCode);
		
		try
		{
			getNewsletterSubscriptionFacade().subscribe(data);
			redirectAttributes.addFlashAttribute("newsletterregistration", Boolean.TRUE);
			redirectAttributes.addFlashAttribute("newsletterregistrationnegative", Boolean.FALSE);
		}
		catch (DuplicatedNewsletterSubscriptionException e)
		{
			redirectAttributes.addFlashAttribute("newsletterregistration", Boolean.TRUE);
			redirectAttributes.addFlashAttribute("newsletterregistrationnegative", Boolean.TRUE);
			final String message = getMessageSource().getMessage("text.fliegercommerce.texto124", null, getI18nService().getCurrentLocale());
			//E-mail ja cadastrado
			return message;
		}
		
		String redirect = getReturnRedirectUrl(request);
		//LOG.info("Base Store = "+data.getBaseStore());
		////LOG.info("Redirect "+redirect);

		final String message = getMessageSource().getMessage("text.fliegercommerce.texto125", null, getI18nService().getCurrentLocale());
		//Cadastrado com sucesso!
		return message;
	}

	
	protected String getReturnRedirectUrl(final HttpServletRequest request)
	{
		final String referer = request.getHeader("Referer");
		if (StringUtils.isNotBlank(referer))
		{
			return REDIRECT_PREFIX + referer;
		}
		
		return REDIRECT_PREFIX + '/';
	}
	
	
	/**
	 * @return the newsletterSubscriptionFacade
	 */
	public NewsletterSubscriptionFacade getNewsletterSubscriptionFacade()
	{
		return newsletterSubscriptionFacade;
	}


	/**
	 * @param newsletterSubscriptionFacade the newsletterSubscriptionFacade to set
	 */
	@Required
	public void setNewsletterSubscriptionFacade(NewsletterSubscriptionFacade newsletterSubscriptionFacade)
	{
		this.newsletterSubscriptionFacade = newsletterSubscriptionFacade;
	}

	public UserService getUserService() {
		return userService;
	}
	
	@Required
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public I18NService getI18nService() {
		return i18nService;
	}

	@Required
	public void setI18nService(I18NService i18nService) {
		this.i18nService = i18nService;
	}

	public CustomerFacade getCustomerFacade() {
		return customerFacade;
	}
	
	@Required
	public void setCustomerFacade(CustomerFacade customerFacade) {
		this.customerFacade = customerFacade;
	}

}
