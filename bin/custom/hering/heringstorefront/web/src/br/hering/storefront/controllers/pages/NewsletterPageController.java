/**
 * 
 */
package br.hering.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.flieger.data.NewsletterSubscriberData;
import com.flieger.facades.NewsletterSubscriberFacade;

/**
 * Controller for Newsletter.
 * 
 * @author Vinicius de Souza
 *
 */
@Controller
@RequestMapping("/newsletter")
public class NewsletterPageController extends AbstractPageController
{
		
	@Resource(name = "newsletterSubscriptionFacade")
	private NewsletterSubscriberFacade newsletterSubscriptionFacade;
	
	@Resource
	private BaseStoreService baseStoreService;
	
	private static final Logger LOG = Logger.getLogger(NewsletterPageController.class);

	@RequestMapping(value = "newsletter-register", method = RequestMethod.POST)
	public @ResponseBody String registerNewsletter(
			HttpServletRequest request,
			RedirectAttributes redirectAttributes,
			@RequestParam(value = "name") final String name,
			@RequestParam(value = "email") final String email,
			@RequestParam(value = "opcaoSexo") final String gender,
			@RequestParam(value = "baseStore") final String baseStore)
	{
		LOG.info("Entrou no Controller: "+name+"/"+email+"/"+gender+"/"+baseStore);
		final NewsletterSubscriberData data = new NewsletterSubscriberData();

		data.setName(name);
		data.setEmail(email);
		data.setGender(Gender.valueOf(gender));
		data.setReceive(Boolean.TRUE);
		data.setBaseStore(baseStore);
		//data.setBaseStore(baseStoreService.getCurrentBaseStore().getUid());
		
		try
		{
			newsletterSubscriptionFacade.subscribeNewsletter(data);
			redirectAttributes.addFlashAttribute("newsletterregistration", Boolean.TRUE);

			redirectAttributes.addFlashAttribute("newsletterregistrationnegative", Boolean.FALSE);
		}
		catch (DuplicateUidException e)
		{
			redirectAttributes.addFlashAttribute("newsletterregistration", Boolean.TRUE);
			redirectAttributes.addFlashAttribute("newsletterregistrationnegative", Boolean.TRUE);
			//if(!"dzarm".equalsIgnoreCase(baseStore)){
				return "E-mail já cadastrado";
			//}
		}
		
		String redirect = getReturnRedirectUrl(request);
		LOG.info("Base Store = "+data.getBaseStore());
		LOG.info("Redirect "+redirect);
		if(redirect.matches("redirect:http://([a-zA-Z0-9:/\\.])*store/")) {
			redirect = redirect+"pt/";
		}
		
//		if("dzarm".equalsIgnoreCase(data.getBaseStore()) || "dzarm-bf-2014".equalsIgnoreCase(data.getBaseStore())) {
//			return redirect;
//		}
		
		return "Cadastrado com sucesso!";
	}
	
	@RequestMapping(value = "newsletter-register-dzarm", method = RequestMethod.POST)
	public String registerNewsletterDzarm(
			HttpServletRequest request,
			RedirectAttributes redirectAttributes,
			@RequestParam(value = "name") final String name,
			@RequestParam(value = "email") final String email,
			@RequestParam(value = "opcaoSexo") final String gender,
			@RequestParam(value = "baseStore") final String baseStore)
	{
		return registerNewsletter(request, redirectAttributes, name, email, gender, baseStore);
	}
	
	@RequestMapping(value = "newsletter-register-foryou", method = RequestMethod.POST)
	public String registerNewsletterForyou(
			HttpServletRequest request,
			RedirectAttributes redirectAttributes,
			@RequestParam(value = "name") final String name,
			@RequestParam(value = "email") final String email,
			@RequestParam(value = "baseStore") final String baseStore)
	{
		final String gender = "FEMALE";
		return registerNewsletter(request, redirectAttributes, name, email, gender, baseStore);
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
	public NewsletterSubscriberFacade getNewsletterSubscriptionFacade()
	{
		return newsletterSubscriptionFacade;
	}

	/**
	 * @param newsletterSubscriptionFacade the newsletterSubscriptionFacade to set
	 */
	public void setNewsletterSubscriptionFacade(NewsletterSubscriberFacade newsletterSubscriptionFacade)
	{
		this.newsletterSubscriptionFacade = newsletterSubscriptionFacade;
	}

}