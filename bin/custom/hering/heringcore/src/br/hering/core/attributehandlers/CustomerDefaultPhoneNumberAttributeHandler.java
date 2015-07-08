package br.hering.core.attributehandlers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Required;

import com.google.common.collect.Iterables;

import de.hybris.platform.core.enums.PhoneContactInfoType;
import de.hybris.platform.core.model.user.AbstractContactInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.PhoneContactInfoModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

public class CustomerDefaultPhoneNumberAttributeHandler implements DynamicAttributeHandler<String, CustomerModel>{

	private ModelService modelService;
	
	
	public ModelService getModelService() {
		return modelService;
	}

	@Required
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	
	@Override
	public String get(final CustomerModel customerModel) 
	{

		Collection<AbstractContactInfoModel> contactInfosModel = customerModel.getContactInfos();
		
		if (contactInfosModel != null)
		{
			if (!contactInfosModel.isEmpty())
			{
				final PhoneContactInfoModel phoneModel = (PhoneContactInfoModel) Iterables.get(contactInfosModel, contactInfosModel.size()-1);
				final String phone = phoneModel.getPhoneNumber();
				
				return phone;
			}
		}
			
		return null;
		
	}

	@Override
	public void set(final CustomerModel customerModel, final String phone) 
	{
		
		final PhoneContactInfoModel phoneModel = getModelService().create(PhoneContactInfoModel.class);		
		
		if (customerModel != null && phone != null)
		{
			phoneModel.setPhoneNumber(phone);
			phoneModel.setType(PhoneContactInfoType.HOME);
			phoneModel.setUser(customerModel);
		}
			
		modelService.save(phoneModel);
		modelService.refresh(phoneModel);
		
	}

}
