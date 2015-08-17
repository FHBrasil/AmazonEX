package de.fliegersoftware.amazon.login.addon.controllers.cms;

import java.util.Map.Entry;
import java.util.Properties;

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

		String amazonWidgetUrl;
		if(getRegion().equals("DE") && getEnvironment().equals("SANDBOX")) {
			amazonWidgetUrl = "https://static-eu.payments-amazon.com/OffAmazonPayments/de/sandbox/lpa/js/Widgets.js";
		} else if (getRegion().equals("DE") && getEnvironment().equals("LIVE")){
			amazonWidgetUrl = "https://static-eu.payments-amazon.com/OffAmazonPayments/de/lpa/js/Widgets.js";
		} else {
			amazonWidgetUrl = "https://static-eu.payments-amazon.com/OffAmazonPayments/uk/sandbox/lpa/js/Widgets.js";
		}

		model.addAttribute("amazonWidgetsUrl", amazonWidgetUrl);
	}
}