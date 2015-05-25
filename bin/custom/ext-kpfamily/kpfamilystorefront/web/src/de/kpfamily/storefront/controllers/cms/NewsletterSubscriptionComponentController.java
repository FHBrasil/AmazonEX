/*package de.kpfamily.storefront.controllers.cms;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fliegersoftware.newslettersubscription.data.NewsletterSubscriptionData;
import com.fliegersoftware.newslettersubscription.exceptions.DuplicatedNewsletterSubscriptionException;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
//import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.addonsupport.controllers.AbstractAddOnController;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.store.services.BaseStoreService;

import com.fliegersoftware.newslettersubscription.facades.NewsletterSubscriptionFacade;

*//**
 * @author luiza
 *
 *//*
@Controller
@RequestMapping("/newsletter")
public class NewsletterSubscriptionComponentController extends AbstractAddOnPageController
{
		
	@Resource(name = "newsletterSubscriptionFacade")
	private NewsletterSubscriptionFacade newsletterSubscriptionFacade;
	
	@Resource
	private BaseStoreService baseStoreService;
	
	private static final Logger LOG = Logger.getLogger(NewsletterSubscriptionComponentController.class);

	
	@RequestMapping(value = "manage-subscription", method = RequestMethod.GET)
	public @ResponseBody String manageSubscription(
			HttpServletRequest request,
			RedirectAttributes redirectAttributes,
			@RequestParam (defaultValue = "false") final boolean subscription)
	{
		LOG.info(" Controller do storefront: " +subscription);
		
		return "Cadastrado com sucesso!";
	}
	
	
	@RequestMapping(value = "newsletter-register", method = RequestMethod.POST)
	public @ResponseBody String registerNewsletter(
			HttpServletRequest request,
			RedirectAttributes redirectAttributes,
			@RequestParam(value = "firstName") final String firstName,
			@RequestParam(value = "lastName") final String lastName,
			@RequestParam(value = "email") final String email)
	{
		LOG.info(" Controller do storefront: " +firstName+"/"+lastName+"/"+email);
		final NewsletterSubscriptionData data = new NewsletterSubscriptionData();

		data.setFirstName(firstName);
		data.setLastName(lastName);
		data.setEmail(email);
		data.setGenderCode("male");
		data.setTitleCode("mr");
		data.setStoreCode("babyartikel");
		data.setLanguageIsoCode("de");

		
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
		LOG.info("Redirect "+redirect);
		if(redirect.matches("redirect:http://([a-zA-Z0-9:/\\.])*store/")) {
			redirect = redirect+"pt/";
		}

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
	
	*//**
	 * @return the newsletterSubscriptionFacade
	 *//*
	public NewsletterSubscriptionFacade getNewsletterSubscriptionFacade()
	{
		return newsletterSubscriptionFacade;
	}

	*//**
	 * @param newsletterSubscriptionFacade the newsletterSubscriptionFacade to set
	 *//*
	public void setNewsletterSubscriptionFacade(NewsletterSubscriptionFacade newsletterSubscriptionFacade)
	{
		this.newsletterSubscriptionFacade = newsletterSubscriptionFacade;
	}

}
*/