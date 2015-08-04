package de.fliegersoftware.amazon.payment.addon.controllers.cms;

import java.util.Properties;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import de.fliegersoftware.amazon.payment.util.AmazonConfig;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;

public abstract class AbstractAmazonCmsComponentController<T extends AbstractCMSComponentModel> extends AbstractCMSAddOnComponentController<T> {
	protected static Properties properties = AmazonConfig.getProperties();

	protected String getSellerId() {
		return properties.getProperty(AmazonConfig.SELLER_ID);
	}

	protected String getRegion() {
		return properties.getProperty(AmazonConfig.REGION);
	}

	protected String getEnvironment() {
		return properties.getProperty(AmazonConfig.ENVIRONMENT);
	}

	@Override
	protected void fillModel(HttpServletRequest request, Model model, T component) {
		for(Entry<Object, Object> prop : properties.entrySet()) {
			model.addAttribute((String)prop.getKey(), prop.getValue());
		}
		model.addAttribute("clientId", "amzn1.application-oa2-client.3a8eb36356824cb4b58183861bbcb8d1");
	}
}