package de.fliegersoftware.amazon.core.facades.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import de.fliegersoftware.amazon.core.AmazonUserService;
import de.fliegersoftware.amazon.core.facades.AmazonUserFacade;
import de.fliegersoftware.amazon.core.model.AmazonCustomerModel;
import de.fliegersoftware.amazon.login.addon.data.AmazonLoginRegisterData;
import de.hybris.platform.commercefacades.user.data.RegisterData;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.strategies.CustomerNameStrategy;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

public class DefaultAmazonUserFacade implements AmazonUserFacade {
	
	private AmazonUserService amazonUserService;
	
	private UserService userService;
	
	private ModelService modelService;
	
	private CustomerNameStrategy customerNameStrategy;
	
	private CustomerAccountService customerAccountService;
	
	private CommonI18NService commonI18NService;

	@Override
	public AmazonCustomerModel getAmazonCustomer(String customerId) 
	{	
		return getAmazonUserService().getAmazonCustomer(customerId);
	}
	
	@Override
	public void register(AmazonLoginRegisterData registerData) throws DuplicateUidException 
	{
		validateParameterNotNullStandardMessage("registerData", registerData);
		
		Assert.hasText(registerData.getFirstName(), "The field [FirstName] cannot be empty");
		Assert.hasText(registerData.getLastName(), "The field [LastName] cannot be empty");

		final CustomerModel newCustomer = getModelService().create(CustomerModel.class);
		
		if (StringUtils.isNotBlank(registerData.getFirstName()) && StringUtils.isNotBlank(registerData.getLastName()))
		{
			newCustomer.setName(getCustomerNameStrategy().getName(registerData.getFirstName(), registerData.getLastName()));
		}
		
		final TitleModel title = getUserService().getTitleForCode(registerData.getTitleCode());
		newCustomer.setTitle(title);
		setUidForRegister(registerData, newCustomer);
		
		newCustomer.setSessionLanguage(getCommonI18NService().getCurrentLanguage());
		newCustomer.setSessionCurrency(getCommonI18NService().getCurrentCurrency());
		
		getCustomerAccountService().register(newCustomer, registerData.getPassword());	
		
		AmazonCustomerModel amazonCustomer = getModelService().create(AmazonCustomerModel.class);
		amazonCustomer.setCustomer(newCustomer);
		amazonCustomer.setAmazonCustomerId(registerData.getAmazonCustomerId());
		
		getModelService().save(amazonCustomer);
		
	}
	
	@Override
	public void update(AmazonLoginRegisterData registerData)
	{
		CustomerModel customer = userService.getUserForUID(registerData.getLogin(), CustomerModel.class);
		AmazonCustomerModel amazonCustomer = new AmazonCustomerModel();
		amazonCustomer.setCustomer(customer);
		amazonCustomer.setAmazonCustomerId(registerData.getAmazonCustomerId());

		getModelService().save(amazonCustomer);
	}
	
	@Override
	public boolean isUserExisting(String email) 
	{
			if(userService.isUserExisting(email))
			{
				return true;
			}
			return false;
	}
	
	@Override
	public boolean isAmazonCustomerExisting(String customerId) 
	{
		if(amazonUserService.isAmazonCustomerExisting(customerId))
		{
			return true;
		}
		return false;
	}

	/**
	 * Initializes a customer with given registerData
	 */
	protected void setUidForRegister(final RegisterData registerData, final CustomerModel customer)
	{
		customer.setUid(registerData.getLogin().toLowerCase());
		customer.setOriginalUid(registerData.getLogin());
	}
	
	public UserService getUserService() {
		return userService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public ModelService getModelService() {
		return modelService;
	}
	
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}
	
	public CustomerNameStrategy getCustomerNameStrategy() {
		return customerNameStrategy;
	}
	
	public void setCustomerNameStrategy(CustomerNameStrategy customerNameStrategy) {
		this.customerNameStrategy = customerNameStrategy;
	}
	
	public CommonI18NService getCommonI18NService() {
		return commonI18NService;
	}
	
	public void setCommonI18NService(CommonI18NService commonI18NService) {
		this.commonI18NService = commonI18NService;
	}
	
	public CustomerAccountService getCustomerAccountService() {
		return customerAccountService;
	}
	
	public void setCustomerAccountService(CustomerAccountService customerAccountService) {
		this.customerAccountService = customerAccountService;
	}	
	
	public AmazonUserService getAmazonUserService() {
		return amazonUserService;
	}
	
	public void setAmazonUserService(AmazonUserService amazonUserService) {
		this.amazonUserService = amazonUserService;
	}
}