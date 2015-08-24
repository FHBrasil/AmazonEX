package de.fliegersoftware.amazon.payment.addon.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import de.fliegersoftware.amazon.payment.addon.controllers.AmazonpaymentaddonControllerConstants;
import de.fliegersoftware.amazon.payment.addon.model.AmazonPayButtonComponentModel;

@Controller("AmazonPayButtonComponentController")
@Scope("tenant")
@RequestMapping(value = AmazonpaymentaddonControllerConstants.Actions.Cms.AmazonPayButtonComponent)
public class AmazonPayButtonComponentController extends AbstractAmazonCmsComponentController<AmazonPayButtonComponentModel>{

	@Override
	protected void fillModel(HttpServletRequest request, Model model, AmazonPayButtonComponentModel component) {
		super.fillModel(request, model, component);

		model.addAttribute("buttonColor", amazonConfigService.getPayButtonColor());
		model.addAttribute("buttonSize", amazonConfigService.getPayButtonSize());
	}
}