package de.fliegersoftware.amazon.payment.addon.controllers.cms;

import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import de.fliegersoftware.amazon.core.services.impl.DefaultAmazonConfigService;
import de.fliegersoftware.amazon.payment.util.AmazonConfig;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;

public abstract class AbstractAmazonCmsComponentController<T extends AbstractCMSComponentModel> extends AbstractCMSAddOnComponentController<T> {
	
	@Resource
	protected DefaultAmazonConfigService defaultAmazonConfigService;
	
	protected static Properties properties = AmazonConfig.getProperties();

	protected String getSellerId() {
		return defaultAmazonConfigService.getSellerId();
	}

	protected String getRegion() {
		return defaultAmazonConfigService.getRegion();
	}

	protected boolean isSandboxMode() {
		return defaultAmazonConfigService.isSandboxMode();
	}

	@Override
	protected void fillModel(HttpServletRequest request, Model model, T component) {
		for(Entry<Object, Object> prop : properties.entrySet()) {
			model.addAttribute((String)prop.getKey(), prop.getValue());
		}
		model.addAttribute("clientId", defaultAmazonConfigService.getClientId());
	}
}