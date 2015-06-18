package com.fliegersoftware.addons.newsletteraddon.component.renderer;

import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.cms2.servicelayer.services.CMSComponentService;
import de.hybris.platform.servicelayer.exceptions.AttributeNotSupportedException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.fliegersoftware.addons.newsletteraddon.model.NewsletterSubscriptionManagementParagraphComponentModel;
import com.fliegersoftware.newslettersubscription.data.NewsletterSubscriptionData;
import com.fliegersoftware.newslettersubscription.model.NewsletterSubscriptionModel;

/**
 * @author luiza
 *
 */

public class NewsletterSubscriptionManagementParagraphComponentRenderer<C extends NewsletterSubscriptionManagementParagraphComponentModel> extends
		DefaultAddOnCMSComponentRenderer<C>
{

	CMSComponentService cmsComponentService;
	
   	ModelService modelService;
   	
   	private BaseStoreService baseStoreService;
   	
	private CustomerFacade customerFacade;

	private static final Logger LOG = Logger.getLogger(NewsletterSubscriptionManagementParagraphComponentRenderer.class);

	
	
	private String getNewsletterChecked()
    {		
		String newsletterChecked = " ";
			
		CustomerData customer = getCustomerFacade().getCurrentCustomer();
		
		String nome = customer.getFirstName();
		String sobrenome = customer.getLastName();
		LOG.info("Nome: " + nome + " lastname: " + sobrenome);
		List<NewsletterSubscriptionData> subscriptions = customer.getNewsletterSubscriptions();
		
				
    	///////////////////List<NewsletterSubscriptionData> subscriptions = getCustomerData().getNewsletterSubscriptions();		
    	///////////////////String name = getCustomerData().getFirstName();
    	///////////////////String last = getCustomerData().getLastName();
    	///////////////////LOG.info("Nome: " + name + " lastname: " + last);
    	
    	if (subscriptions==null){
    		LOG.info("null subscription");
    	}

    	final String currentStore = getBaseStoreService().getCurrentBaseStore().getUid();   	
    	
    	//if customer has at least one subscription
    	if (subscriptions!=null)
    	{
    		if (!subscriptions.isEmpty())
    		{
    			for (NewsletterSubscriptionData subs : subscriptions)
            	{ 				
            		final String subscriptionStore = subs.getStoreCode();
            		
            		if (subscriptionStore == currentStore)
            		{
            			LOG.info("retorna checked");
            			newsletterChecked = "checked";
            			return newsletterChecked;
            		}
            		//return newsletterChecked;
            	}
    		}
    		
    		//return newsletterChecked;
    	}
    	
    	//else
    	//{
    	return newsletterChecked;
    	//}
  		
    }  


   
   protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final C component)
   {
	   
	   String newsletterChecked = getNewsletterChecked();
	   LOG.info("DENTRO DE GETVARIABLES");
	   
       final Map<String, Object> variables = new HashMap<String, Object>();

       variables.put("newsletterChecked", newsletterChecked);
       
       for (final String property : cmsComponentService.getEditorProperties(component))
       {
           try
           {
               final Object value = modelService.getAttributeValue(component, property);
               variables.put(property, value);

           }
           catch (final AttributeNotSupportedException ignore)
           {
        	   //

           }
       }
       return variables;
   }
   
   
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
   
	public CustomerFacade getCustomerFacade() {
		return customerFacade;
	}

	@Required
	public void setCustomerFacade(CustomerFacade customerFacade) {
		this.customerFacade = customerFacade;
	}
    
	public BaseStoreService getBaseStoreService() {
		return baseStoreService;
	}

	@Required
	public void setBaseStoreService(BaseStoreService baseStoreService) {
		this.baseStoreService = baseStoreService;
	}
   
}
