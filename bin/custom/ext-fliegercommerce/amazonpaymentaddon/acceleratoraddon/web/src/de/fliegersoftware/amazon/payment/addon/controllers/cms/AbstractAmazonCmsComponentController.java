package de.fliegersoftware.amazon.payment.addon.controllers.cms;

import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import de.fliegersoftware.amazon.core.services.AmazonConfigService;
import de.fliegersoftware.amazon.payment.util.AmazonConfig;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;

public abstract class AbstractAmazonCmsComponentController<T extends AbstractCMSComponentModel> extends AbstractCMSAddOnComponentController<T> {

	@Resource
	protected AmazonConfigService amazonConfigService;

	protected static Properties properties = AmazonConfig.getProperties();

	@Override
	protected void fillModel(HttpServletRequest request, Model model, T component) {
	}
}