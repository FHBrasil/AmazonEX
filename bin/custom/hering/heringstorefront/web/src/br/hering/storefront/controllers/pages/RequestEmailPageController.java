/**
 * 
 */
package br.hering.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.forms.ForgottenPwdForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import br.hering.facades.customer.HeringCustomerFacade;
import br.hering.storefront.controllers.ControllerConstants;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author rafael
 *
 */

@Controller
@Scope("tenant")
@RequestMapping(value = "/login/email")
public class RequestEmailPageController
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(PasswordResetPageController.class);
	
	@Resource
	private HeringCustomerFacade customerFacade;
	
	@RequestMapping(value = "/request", method = RequestMethod.GET)
	public String getEmailRequest(final Model model) throws CMSItemNotFoundException
	{
		model.addAttribute(new ForgottenPwdForm());
		return ControllerConstants.Views.Fragments.Email.EmailRequestPopup;
	}
	
	@RequestMapping(value = "/request", method = RequestMethod.POST)
	public String emailRequest(final ForgottenPwdForm form, final BindingResult bindingResult, final Model model)
			throws CMSItemNotFoundException
	{
		final String ER_CPF_CNPJ = "[0-9]{11}|[0-9]{14}";
		
		String email = null, cpfCnpj = null;
		
		if(form.getEmail().replaceAll("[.]", "").replaceAll("/", "")
				.replaceAll("-", "").matches(ER_CPF_CNPJ))
		{
			cpfCnpj = form.getEmail().replaceAll("[.]", "").replaceAll("/", "")
					.replaceAll("-", "");
		}
		else
		{
			bindingResult.rejectValue("email", "forgottenEmail.invalid");
		}
		
		if (bindingResult.hasErrors())
		{
			GlobalMessages.addErrorMessage(model, "forgottenEmail.invalid");
			return ControllerConstants.Views.Fragments.Email.EmailRequestPopup;
		}
		else
		{			
			try
			{
				final CustomerModel customer = customerFacade.cpfCnpjAlreadyExists(cpfCnpj);
				if(customer != null)
				{
					email = customer.getUid();
				}
				else
				{
					bindingResult.rejectValue("email", "forgottenPwd.invalid.cpfCnpj");
					return ControllerConstants.Views.Fragments.Email.EmailRequestPopup;
				}
				
				model.addAttribute("email", email);
			}
			catch (final UnknownIdentifierException unknownIdentifierException)
			{
				bindingResult.rejectValue("email", "forgottenPwd.invalid.notfound", new String[]{email}, null);
				return ControllerConstants.Views.Fragments.Email.EmailRequestPopup;
			}
			return ControllerConstants.Views.Fragments.Email.EmailRequestValidationMessage;
		}
	}
}
