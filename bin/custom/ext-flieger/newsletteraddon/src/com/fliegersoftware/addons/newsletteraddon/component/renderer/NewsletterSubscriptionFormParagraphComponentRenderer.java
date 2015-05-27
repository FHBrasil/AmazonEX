package com.fliegersoftware.addons.newsletteraddon.component.renderer;

import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.cms2.servicelayer.services.CMSComponentService;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commercefacades.user.data.TitleData;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.servicelayer.exceptions.AttributeNotSupportedException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.type.TypeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Required;

import com.fliegersoftware.addons.newsletteraddon.model.NewsletterSubscriptionFormParagraphComponentModel;
import com.fliegersoftware.addons.newsletteraddon.selectoption.AbstractSelectOption.SelectOption;

/**
 * @author luiza
 *
 */

public class NewsletterSubscriptionFormParagraphComponentRenderer<C extends NewsletterSubscriptionFormParagraphComponentModel> extends
		DefaultAddOnCMSComponentRenderer<C>
{
	
	private UserFacade userFacade;
   
	private TypeService typeService;

	CMSComponentService cmsComponentService;
	
	ModelService modelService;


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

   
   public List<SelectOption> getTitles()
   {
   	
   	final List<SelectOption> titles = new ArrayList<SelectOption>();

   	List <TitleData> title = new ArrayList<TitleData>();
   	title.addAll(getUserFacade().getTitles());
   	
   	for (int i=0; i<title.size(); i++)
   	{
   		final String code = title.get(i).getCode();
   		final String name = title.get(i).getName();
   		titles.add(new SelectOption(code, name));
   	}
 
 		return titles;
   	
   }
   
   
   public List<SelectOption> getGenders()
   {
   		 
   	final List<SelectOption> genders = new ArrayList<SelectOption>();
   	   	
   	final Gender[] gender = Gender.values();
   	
   	for (Gender g : gender)
   	{
   		String genderCode = g.getCode();
   		String genderName = getTypeService().getEnumerationValue(g).getName();
   		genders.add(new SelectOption(genderCode, genderName));  		
   	}
   	
 		return genders;
 		
   }  	
	
   
      
   protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final C component)
   {
		 	
		List<SelectOption> titles = getTitles();
		
		List<SelectOption> genders = getGenders();
   	
       final Map<String, Object> variables = new HashMap<String, Object>();
       
       variables.put("titles", titles); 
       variables.put("genders", genders);
       
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
   
   
   /**
	 * @return the userFacade
	 */
	public UserFacade getUserFacade()
	{
		return userFacade;
	}

	/**
	 * @param userFacade the userFacade to set
	 */
	@Required
	public void setUserFacade(UserFacade userFacade)
	{
		this.userFacade = userFacade;
	}

	/**
	 * @return the typeService
	 */
	public TypeService getTypeService()
	{
		return typeService;
	}

	/**
	 * @param typeService the typeService to set
	 */
	@Required
	public void setTypeService(TypeService typeService)
	{
		this.typeService = typeService;
	}
	
   
}
