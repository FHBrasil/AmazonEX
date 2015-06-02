package com.paypal.hybris.addon.controllers.cms;

import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paypal.hybris.addon.controllers.PaypaladdonControllerConstants;
import com.paypal.hybris.addon.model.PayPalExpressCheckoutShortcutModel;
import com.paypal.hybris.constants.PaypalConstants;


/**
 * @author Dzmitry_Rasolka (EPAM Systems)
 */
@Controller("PayPalExpressCheckoutShortcutController")
@Scope("tenant")
@RequestMapping(value = PaypaladdonControllerConstants.Actions.Cms.PayPalExpressCheckoutShortcut)
public class PayPalExpressCheckoutShortcutController extends
		AbstractCMSAddOnComponentController<PayPalExpressCheckoutShortcutModel>
{

	private ConfigurationService configurationService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController#fillModel(javax.servlet.http
	 * .HttpServletRequest, org.springframework.ui.Model,
	 * de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel)
	 */
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
			final PayPalExpressCheckoutShortcutModel component)
	{
		model.addAttribute("component", component);
		final boolean inContextCheckoutEnabled = getConfigurationService().getConfiguration().getBoolean(
				PaypalConstants.IN_CONTEXT_CHECKOUT_ENABLED);
		if (inContextCheckoutEnabled)
		{
			model.addAttribute("inContextCheckoutEnabled", inContextCheckoutEnabled);
			model.addAttribute("merchantId",
					getConfigurationService().getConfiguration().getString(PaypalConstants.PAYPAL_SELLER_EMAIL));
		}
	}

	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	@Resource
	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}
}
