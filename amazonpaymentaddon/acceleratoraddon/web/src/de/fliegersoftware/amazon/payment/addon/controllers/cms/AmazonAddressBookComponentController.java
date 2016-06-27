package de.fliegersoftware.amazon.payment.addon.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import de.fliegersoftware.amazon.payment.addon.controllers.AmazonpaymentaddonControllerConstants;
import de.fliegersoftware.amazon.payment.addon.model.AmazonAddressBookComponentModel;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;

@Controller("AmazonAddressBookComponentController")
@Scope("tenant")
@RequestMapping(value = AmazonpaymentaddonControllerConstants.Actions.Cms.AmazonAddressBookComponent)
public class AmazonAddressBookComponentController extends AbstractAmazonCmsComponentController<AmazonAddressBookComponentModel>{

	@Override
	protected void fillModel(HttpServletRequest request, Model model, AmazonAddressBookComponentModel component) {
		super.fillModel(request, model, component);
		
		model.addAttribute("addressWidgetHeight", String.valueOf(amazonConfigService.getAddressWidgetHeight()));
		model.addAttribute("addressWidgetWidth", String.valueOf(amazonConfigService.getAddressWidgetWidth()));
	}

}
