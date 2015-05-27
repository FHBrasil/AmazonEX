package com.fliegersoftware.addons.newsletteraddon.controllers.cms;

import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;

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
import com.fliegersoftware.newslettersubscription.facades.NewsletterSubscriptionFacade;

/**
 * @author luiza
 *
 */


@Controller
@RequestMapping("/newsletter")
public class NewsletterSubscriptionAddOnController extends AbstractAddOnPageController
{

	private NewsletterSubscriptionFacade newsletterSubscriptionFacade;

	private static final Logger LOG = Logger.getLogger(NewsletterSubscriptionAddOnController.class);

	@RequestMapping(value = "/manage-subscription", method = RequestMethod.GET)
	public @ResponseBody String manageSubscription(
			HttpServletRequest request,
			RedirectAttributes redirectAttributes,
			@RequestParam (defaultValue = "false") final boolean subscription)
	{
		LOG.info("Controller do addon: " +subscription);
		
		return "Cadastrado com sucesso!";
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
		
		LOG.info(" Controller do addon: " +firstName+" - "+lastName+" - "+email+" - "+titleCode+" - "+genderCode);
		
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
			return "E-mail já cadastrado";
		}
		
		String redirect = getReturnRedirectUrl(request);
		//LOG.info("Base Store = "+data.getBaseStore());
		////LOG.info("Redirect "+redirect);

		return "Cadastrado com sucesso!";
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


}
