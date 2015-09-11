/**
 * 
 */
package br.hering.storefront.controllers.pages;

import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;

import br.hering.storefront.controllers.ControllerConstants;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;

import br.hering.facades.order.HeringOrderFacade;
import br.hering.fulfilmentprocess.services.HeringOrderService;

/**
 * @author ghayashi
 *
 */
@Controller
@Scope("tenant")
@RequestMapping(value = "/boleto")
public class BoletoPageController extends AbstractPageController
{
	private static final String ORDER_CODE = "{orderCode:.*}";
	
	protected static final Logger LOG = Logger.getLogger(BoletoPageController.class);
	
	@Resource
	private BaseStoreService baseStoreService;
	
	@Resource
	private CustomerAccountService customerAccountService;
	
	@Resource
	private UserService userService;
	
	@Resource
   private ServletContext servletContext;

	@Resource
	private HeringOrderFacade heringOrderFacade;
	 
	@RequestMapping(value = "/" + ORDER_CODE, method = RequestMethod.GET)
	@RequireHardLogIn
	public String getBoletoJpgView(@PathVariable("orderCode") final String orderCode, final Model model,
			final HttpServletRequest request)
			throws CMSItemNotFoundException
	{
		try{
			heringOrderFacade.validateBoleto(orderCode);
			LOG.info("Iniciando geração do boleto");
			String urlBoleto = heringOrderFacade.getBoletoURL(orderCode);
			LOG.info("URL do boleto na adyen: " + urlBoleto);
			if(heringOrderFacade.generateBoleto(orderCode)){
				model.addAttribute("urlBoleto", "/boleto-" + orderCode + ".jpg");
				LOG.info("Finalizando geração do boleto");
				return ControllerConstants.Views.Pages.Boleto.BoletoPage;
			}else{
				LOG.info("Redirecionando para download do boleto");
				return "redirect:" + urlBoleto;
			}
		}
		catch(Exception e){
			LOG.error("Error getting url from boleto", e);
			return "redirect:/login";
		}
	}
	
}
