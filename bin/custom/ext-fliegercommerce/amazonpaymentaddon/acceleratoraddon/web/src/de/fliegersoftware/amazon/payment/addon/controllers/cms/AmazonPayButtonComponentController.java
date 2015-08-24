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

		String amazonWidgetUrl;
		if(getRegion().equals("DE") && isSandboxMode()) {
			amazonWidgetUrl = "https://static-eu.payments-amazon.com/OffAmazonPayments/de/sandbox/lpa/js/Widgets.js";
		} else if (getRegion().equals("DE") && !isSandboxMode()){
			amazonWidgetUrl = "https://static-eu.payments-amazon.com/OffAmazonPayments/de/lpa/js/Widgets.js";
		} else {
			amazonWidgetUrl = "https://static-eu.payments-amazon.com/OffAmazonPayments/uk/sandbox/lpa/js/Widgets.js";
		}

		model.addAttribute("amazonWidgetsUrl", amazonWidgetUrl);
	}
}