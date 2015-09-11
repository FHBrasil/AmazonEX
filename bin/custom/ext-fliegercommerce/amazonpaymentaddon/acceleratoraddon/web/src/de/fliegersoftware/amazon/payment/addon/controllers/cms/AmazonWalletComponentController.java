package de.fliegersoftware.amazon.payment.addon.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import de.fliegersoftware.amazon.payment.addon.controllers.AmazonpaymentaddonControllerConstants;
import de.fliegersoftware.amazon.payment.addon.model.AmazonWalletComponentModel;

@Controller("AmazonWalletComponentController")
@Scope("tenant")
@RequestMapping(value = AmazonpaymentaddonControllerConstants.Actions.Cms.AmazonWalletComponent)
public class AmazonWalletComponentController extends AbstractAmazonCmsComponentController<AmazonWalletComponentModel> {

	@Override
	protected void fillModel(HttpServletRequest request, Model model, AmazonWalletComponentModel component) {
		super.fillModel(request, model, component);
	}
}