/**
 * 
 */
package br.hering.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.util.Config;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.hering.storefront.controllers.ControllerConstants;

import com.flieger.notificationservices.comparators.NotifyMeSimilarProductComparator;
import com.flieger.notificationservices.data.NotifymeData;
import com.flieger.notificationservices.facades.NotifyMeSimilarProductFacade;
import com.flieger.notificationservices.facades.NotifymeFacade;
import com.flieger.notificationservices.services.NotifyMeSimilarProductService;
import com.thoughtworks.xstream.io.binary.Token.Value;

/**
 * @author Vinicius de Souza
 *
 */
@Controller
@RequestMapping("/notifyme")
public class NotifymeController extends AbstractPageController
{
	@Resource
	private NotifymeFacade notifymeFacade;
	
	@Resource
	private NotifyMeSimilarProductFacade notifyMeSimilarProductFacade;
	
	@Resource
	private NotifyMeSimilarProductComparator notifyMeSimilarProductComparator;
	
	private static final Logger LOG = Logger.getLogger(NotifymeController.class);

	private static final Integer QTD_SIMILAR_PRODUCTS = new Integer(Config.getParameter("heringstorefront.qtd-similar-products"));
	
	@RequestMapping(value = "notifyme-register", method = RequestMethod.POST, produces = "application/json")
	public String notifymeRegister
	(
		HttpServletRequest request,
		@RequestParam(value = "nameNotify") final String name,
		@RequestParam(value = "emailNotify") final String email,
		@RequestParam(value = "daysNotify") final String days,
		@RequestParam(value = "code") final String code,
		@RequestParam(value = "baseStore") final String baseStore,
		final Model model
	){		
		try{
			
			NotifymeData notifymeData = notifymeFacade.find(email.toLowerCase(), code);
			
			if(notifymeData == null || notifymeData.getNotified() != null){
				
				notifymeData = new NotifymeData();
				notifymeData.setName(name.toUpperCase());
				notifymeData.setEmail(email.toLowerCase());
				notifymeData.setBaseStore(baseStore);
				notifymeData.setCode(code);
				notifymeData.setCreated(new Date(System.currentTimeMillis()));
				notifymeData.setNotified(null);
				notifymeData.setExpired(null);
				notifymeData.setDays(new Integer(days));
				
				notifymeFacade.notifyme(notifymeData);
				
				//notifyMeSimilarProductFacade.notifyMe(notifymeData, notifyMeSimilarProductComparator, QTD_SIMILAR_PRODUCTS, null);
				model.addAttribute("statusRegister", "registered");
			}
			else {

				model.addAttribute("statusRegister", "existent");
			}
		}
		catch(final Exception e){
			
			LOG.error(e);
			model.addAttribute("statusRegister", "error");
		}	
		
		return ControllerConstants.Views.Fragments.Product.OutOfStock;
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
}