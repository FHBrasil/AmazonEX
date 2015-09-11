package com.fliegersoftware.addons.newsletteraddon.controllers.cms;

import java.util.List;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
import com.fliegersoftware.newslettersubscription.enums.SubscriptionType;
import com.fliegersoftware.newslettersubscription.exceptions.DuplicatedNewsletterSubscriptionException;
import com.fliegersoftware.newslettersubscription.exceptions.NewsletterSubscriptionNotFound;
import com.fliegersoftware.newslettersubscription.facades.NewsletterSubscriptionFacade;
import com.fliegersoftware.newslettersubscription.jalo.NewsletterSubscription;

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

	
	private static final Logger LOG = Logger.getLogger(NewsletterSubscriptionAddOnController.class);

	@RequestMapping(value = "/manage-subscription", method = RequestMethod.GET)
	public @ResponseBody String manageSubscription(
			HttpServletRequest request,
			RedirectAttributes redirectAttributes,
			@RequestParam (defaultValue = "false") final boolean subscription)
	{
		//LOG.info("NewsletterAddon Controller: " +subscription);		
		
		final CustomerData customer = getCustomerFacade().getCurrentCustomer();
		final NewsletterSubscriptionData data = new NewsletterSubscriptionData();
		
		if (customer != null)
		{
			
			data.setFirstName(customer.getFirstName());
			data.setLastName(customer.getLastName());
			
			if (customer.getUid()!=null)
			{
				data.setEmail(customer.getUid());
			}
			
			
			if (customer.getGender()!=null)
			{
				data.setGenderCode(customer.getGender().getCode());
			}
			
			data.setTitleCode("Mr");
			//data.setTitleCode(currentCustomerData.getTitleCode());
			
			final String storeCode = getNewsletterSubscriptionFacade().getCurrentBaseStoreCode();
			data.setStoreCode(storeCode);
			
			final String languageIsoCode = getCurrentLanguage().getIsocode();
			data.setLanguageIsoCode(languageIsoCode);

			data.setCustomer(customer);
						
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
			@RequestParam(value = "genderCode") final String genderCode,
			@RequestParam(value = "birthDay") String birthDay)
	{

		final NewsletterSubscriptionData data = new NewsletterSubscriptionData();
	
		data.setFirstName(firstName);
		data.setLastName(lastName);
		data.setEmail(email);
		data.setGenderCode(genderCode);
		data.setTitleCode((titleCode != null && !titleCode.isEmpty()) ? titleCode : "ms");
		data.setCustomer(customerFacade.getCurrentCustomer());
		
		if(birthDay != null && !birthDay.isEmpty())
		{
			birthDay = birthDay.replace(".", "/");
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				Date birthDayDate = sdf.parse(birthDay);
				data.setBirthDay(birthDayDate);
			}
			catch(ParseException e){
				e.printStackTrace();
			}			
		}
						
		final String storeCode = getNewsletterSubscriptionFacade().getCurrentBaseStoreCode();
		data.setStoreCode(storeCode);
		
		final String languageIsoCode = getCurrentLanguage().getIsocode();
		data.setLanguageIsoCode(languageIsoCode);
		
		//final CustomerData customer = getCustomerFacade().getCurrentCustomer();
		//data.setCustomer(customer);
		
		
		LOG.info(" NewsletterAddon Controller: " +firstName+" - "+lastName+" - "+email+" - "+titleCode+" - "+genderCode);

		try
		{
			getNewsletterSubscriptionFacade().subscribe(data);
			
			final String message = getMessageSource().getMessage("text.fliegercommerce.texto125", null, getI18nService().getCurrentLocale());
			//Cadastrado com sucesso!
			return message;
		}
		catch (DuplicatedNewsletterSubscriptionException e)
		{
			final String message = getMessageSource().getMessage("text.fliegercommerce.texto124", null, getI18nService().getCurrentLocale());
			//E-mail ja cadastrado
			return message;		
		}	
		
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
