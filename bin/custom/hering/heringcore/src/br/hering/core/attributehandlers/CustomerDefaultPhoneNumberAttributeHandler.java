package br.hering.core.attributehandlers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
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

	private static final Logger LOG = Logger.getLogger(CustomerDefaultPhoneNumberAttributeHandler.class);

	
	@Override
	public String get(final CustomerModel customerModel) 
	{

		final AddressModel addressModel = customerModel.getDefaultPaymentAddress();
		
		if (addressModel != null)
		{
			return addressModel.getPhone1();
		}
		
		return null;
	}

	@Override
	public void set(final CustomerModel customerModel, final String phone) 
	{
		
		AddressModel addressModel = customerModel.getDefaultPaymentAddress();
		
		if (customerModel != null && phone != null)
		{
			addressModel.setPhone1(phone);
		}
		
		modelService.save(addressModel);
		modelService.refresh(addressModel);
		
	}

}
