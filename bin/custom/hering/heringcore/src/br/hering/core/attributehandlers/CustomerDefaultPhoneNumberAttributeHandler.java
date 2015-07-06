package br.hering.core.attributehandlers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import br.hering.core.customer.impl.DefaultHeringCustomerAccountService;
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
		
		try 
		{
			return addressModel.getPhone1();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
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
			LOG.info("setando phone");
		}
		
		modelService.save(addressModel);
		modelService.refresh(addressModel);
		
	}


}
