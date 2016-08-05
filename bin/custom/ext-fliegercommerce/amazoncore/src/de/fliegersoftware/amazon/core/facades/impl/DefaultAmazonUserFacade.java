package de.fliegersoftware.amazon.core.facades.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import de.fliegersoftware.amazon.core.facades.AmazonUserFacade;
import de.fliegersoftware.amazon.core.services.AmazonUserService;
import de.fliegersoftware.amazon.core.data.AmazonLoginRegisterData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.RegisterData;
import de.hybris.platform.commercefacades.user.impl.DefaultUserFacade;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.strategies.CustomerNameStrategy;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

public class DefaultAmazonUserFacade extends DefaultUserFacade implements AmazonUserFacade {
	
	private AmazonUserService amazonUserService;
	
	private UserService userService;
	
	private ModelService modelService;
	
	private CustomerNameStrategy customerNameStrategy;
	
	private CustomerAccountService customerAccountService;
	
	private CommonI18NService commonI18NService;
	
	private FlexibleSearchService flexibleSearchService;

	@Override
	public CustomerModel getAmazonCustomer(String customerId) 
	{	
		return getAmazonUserService().getAmazonCustomer(customerId);
	}
	
	@Override
	public void register(final AmazonLoginRegisterData registerData) throws DuplicateUidException 
	{
		validateParameterNotNullStandardMessage("registerData", registerData);
		
		Assert.hasText(registerData.getFirstName(), "The field [FirstName] cannot be empty");
		Assert.hasText(registerData.getLastName(), "The field [LastName] cannot be empty");

		final CustomerModel amazonCustomer = getModelService().create(CustomerModel.class);
		
		if (StringUtils.isNotBlank(registerData.getFirstName()) && StringUtils.isNotBlank(registerData.getLastName()))
		{
			amazonCustomer.setName(getCustomerNameStrategy().getName(registerData.getFirstName(), registerData.getLastName()));
		}
		
		setUidForRegister(registerData, amazonCustomer);
		
		amazonCustomer.setSessionLanguage(getCommonI18NService().getCurrentLanguage());
		amazonCustomer.setSessionCurrency(getCommonI18NService().getCurrentCurrency());
		
		amazonCustomer.setAmazonCustomerId(registerData.getAmazonCustomerId());
		
		getCustomerAccountService().register(amazonCustomer, registerData.getPassword());	
	}
	
	@Override
	public void registerGuestUser(final AmazonLoginRegisterData registerData) throws DuplicateUidException {
		final CustomerModel amazonCustomer = getModelService().create(CustomerModel.class);
		final String guid = UUID.randomUUID().toString();

		amazonCustomer.setUid(guid + "|" + registerData.getLogin());
		if (StringUtils.isNotBlank(registerData.getFirstName()) && StringUtils.isNotBlank(registerData.getLastName()))
		{
			amazonCustomer.setName(getCustomerNameStrategy().getName(registerData.getFirstName(), registerData.getLastName()));
		}
		amazonCustomer.setSessionLanguage(getCommonI18NService().getCurrentLanguage());
		amazonCustomer.setSessionCurrency(getCommonI18NService().getCurrentCurrency());
		amazonCustomer.setAmazonCustomerId(registerData.getAmazonCustomerId());
		
		getCustomerAccountService().register(amazonCustomer, guid);
	}
	
	@Override
	public void update(AmazonLoginRegisterData registerData)
	{
		CustomerModel customer = userService.getUserForUID(registerData.getLogin(), CustomerModel.class);
		customer.setAmazonCustomerId(registerData.getAmazonCustomerId());

		getModelService().save(customer);
	}
	
	@Override
	public void deleteAmazonCustomer(CustomerData customerData)
	{
		CustomerModel amazonCustomer = userService.getUserForUID(customerData.getUid(), CustomerModel.class);
		amazonCustomer.setAmazonCustomerId(null);

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
	
	@Override
	public void updateManualAddition(final AmazonLoginRegisterData registerData) {
		CustomerModel customer = userService.getUserForUID(registerData.getLogin(), CustomerModel.class);
		
		TitleModel title = new TitleModel();
		title.setCode(registerData.getTitleCode());
		customer.setTitle((TitleModel) getFlexibleSearchService().getModelByExample(title));

		getModelService().save(customer);
	}
	
	@Override
	public boolean isAmazonCustomer(String email) 
	{
		if(amazonUserService.isAmazonCustomer(email))
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
	
	protected FlexibleSearchService getFlexibleSearchService()
	 {
	   return this.flexibleSearchService;
	 }
	
	 @Required
	 public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService)
	 {
	   this.flexibleSearchService = flexibleSearchService;
	 }
}