package br.hering.core.attributehandlers;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.google.common.collect.Iterables;

import de.hybris.platform.core.enums.PhoneContactInfoType;
import de.hybris.platform.core.model.user.AbstractContactInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.PhoneContactInfoModel;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

public class CustomerDefaultPhoneNumberAttributeHandler implements DynamicAttributeHandler<String, CustomerModel>{

	private ModelService modelService;
	
	private PhoneContactInfoModel phoneModel;
	
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
		
		if (CollectionUtils.isNotEmpty(contactInfosModel))
		{
			final PhoneContactInfoModel phoneModel = (PhoneContactInfoModel) Iterables.get(contactInfosModel, contactInfosModel.size()-1);
			final String phone = phoneModel.getPhoneNumber();
			
			return phone;
		}
		
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
		
		if (customerModel != null)
		{			

			if (get(customerModel) == null)
			{
				phoneModel = getModelService().create(PhoneContactInfoModel.class);	
			}
			else
			{
				Collection<AbstractContactInfoModel> contactInfosModel = customerModel.getContactInfos();
				phoneModel = (PhoneContactInfoModel) Iterables.get(contactInfosModel, contactInfosModel.size()-1);
			}

		}	
				
		phoneModel.setPhoneNumber(phone);
		phoneModel.setType(PhoneContactInfoType.HOME);
		phoneModel.setUser(customerModel);	
		modelService.save(phoneModel);
		modelService.refresh(phoneModel);
			
	}
		
	
}
