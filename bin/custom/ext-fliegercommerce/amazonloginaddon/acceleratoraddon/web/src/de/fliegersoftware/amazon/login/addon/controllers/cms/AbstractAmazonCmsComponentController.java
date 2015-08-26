package de.fliegersoftware.amazon.login.addon.controllers.cms;

import java.util.Properties;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import de.fliegersoftware.amazon.core.services.AmazonConfigService;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;

public abstract class AbstractAmazonCmsComponentController<T extends AbstractCMSComponentModel> extends AbstractCMSAddOnComponentController<T> {
	@Resource
	protected AmazonConfigService amazonConfigService;

	@Override
	protected void fillModel(HttpServletRequest request, Model model, T component) {
	}
}