package de.fliegersoftware.amazon.login.addon.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import de.fliegersoftware.amazon.login.addon.controllers.AmazonloginaddonControllerConstants;
import de.fliegersoftware.amazon.login.addon.model.AmazonLoginComponentModel;

@Controller("AmazonLoginButtonComponentController")
@Scope("tenant")
@RequestMapping(value = AmazonloginaddonControllerConstants.Actions.Cms.AmazonLoginComponent)
public class AmazonLoginButtonComponentController extends AbstractAmazonCmsComponentController<AmazonLoginComponentModel>{

	@Override
	protected void fillModel(HttpServletRequest request, Model model, AmazonLoginComponentModel component) {
		super.fillModel(request, model, component);

	}
}