package com.fliegersoftware.addons.newsletteraddon.component.renderer;

import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.cms2.servicelayer.services.CMSComponentService;
import de.hybris.platform.servicelayer.exceptions.AttributeNotSupportedException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.fliegersoftware.addons.newsletteraddon.model.NewsletterSubscriptionManagementParagraphComponentModel;

/**
 * @author luiza
 *
 */

public class NewsletterSubscriptionManagementParagraphComponentRenderer<C extends NewsletterSubscriptionManagementParagraphComponentModel> extends
		DefaultAddOnCMSComponentRenderer<C>
{

	CMSComponentService cmsComponentService;
	
   	ModelService modelService;
   	
   	private static final Logger LOG = Logger.getLogger(NewsletterSubscriptionManagementParagraphComponentRenderer.class);

   @Required
   public void setCmsComponentService(final CMSComponentService cmsComponentService)
   {
       this.cmsComponentService = cmsComponentService;
   }

   @Required
   public void setModelService(final ModelService modelService)
   {
       this.modelService = modelService;
   }

   protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final C component)
   {
       final Map<String, Object> variables = new HashMap<String, Object>();
       for (final String property : cmsComponentService.getEditorProperties(component))
       {
           try
           {
               final Object value = modelService.getAttributeValue(component, property);
               variables.put(property, value);

           }
           catch (final AttributeNotSupportedException ignore)
           {
               // ignore

           }
       }
       return variables;
   }
}
