/**
 * 
 */
package br.hering.facades.customer.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;


import br.flieger.exacttarget.events.CustomerRegisterEvent;
//import br.hering.core.constants.GeneratedHeringCoreConstants.Enumerations.TipoDeEndereco;
import br.hering.core.customer.HeringCustomerAccountService;
import br.hering.core.customer.impl.KPCustomerAccountService;
import br.hering.facades.customer.HeringCustomerFacade;
import br.hering.core.enums.TipoDeEndereco;

import com.flieger.facades.checkout.data.GuestRegisterData;
import com.google.common.collect.Lists;

import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.commercefacades.i18n.I18NFacade;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.RegisterData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.PasswordEncoderService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.Config;

/**
 * @author sejunior Sergio-Prado-Junior
 *
 */
public class DefaultHeringCustomerFacade extends DefaultCustomerFacade implements HeringCustomerFacade
{
	@Resource
	private EventService eventService;
	
	@Resource
	private BaseStoreService baseStoreService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private PasswordEncoderService passwordEncoderService;
	
	@Resource
	private ModelService modelService;
	
	@Resource
	private HeringCustomerAccountService heringCustomerAccountService;
	
	@Resource
	private Converter<CustomerData, CustomerModel> customerReverseConverter;
	
	@Resource
	private Converter<CustomerModel, CustomerData> customerConverter;
		
	@Resource
	private Converter<AddressData, AddressModel> addressReverseConverter;
	
	@Resource
	private Populator<AddressData, AddressModel> addressReversePopulator;

	private static final Logger LOG = Logger.getLogger(DefaultHeringCustomerFacade.class);
	
	@Resource(name = "i18NFacade")
	private I18NFacade i18NFacade;
	
	protected I18NFacade getI18NFacade() {
		return i18NFacade;
	}

	
	@Override
	public void updateProfile(final CustomerData customerData) throws DuplicateUidException
	{
		validateDataBeforeUpdate(customerData);

		final String name = getCustomerNameStrategy().getName(customerData.getFirstName(), customerData.getLastName());
		final CustomerModel customer = getCurrentSessionCustomer();
		customer.setOriginalUid(customerData.getDisplayUid());
		customer.setName(name);
		
		if (Config.getBoolean("fliegercommerce.feature.enable.cpf", false))
		{
			customer.setCpfcnpj(customerData.getCpfcnpj());
			
			if(customerData.getCpfcnpj() != null && customerData.getCpfcnpj().length() == 14) {
	   		customer.setRgIe(customerData.getRgIe());
	   		customer.setUfIe(customerData.getUfIe());
			}
		}
		else
		{
			customer.setRgIe(customerData.getRgIe());
	   		customer.setUfIe(customerData.getUfIe());
		}
		
		customer.setGender(customerData.getGender());
		customer.setBirthday(customerData.getBirthday());
		
		getModelService().save(customer);
	}

	
	/**
	 * @author Luiza
	 */
	@Override
	public void deleteAccount()
	{
		
		final CustomerModel customerModel = getCurrentSessionCustomer();
		try
		{
			heringCustomerAccountService.deleteAccount(customerModel);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}		
		
	}
	
	
	/**
	 * @author 	Luiza
	 */
	@Override
	public CustomerData changePhoneNumber(final String phone)
	{

		CustomerModel customerModel = getCurrentSessionCustomer();		

		customerModel = heringCustomerAccountService.changePhoneNumber(customerModel, phone);

		CustomerData customerData = new CustomerData();
		customerConverter.convert(customerModel, customerData);
			
		return customerData;
		
	}
	
	
	/**
	 * @author Luiza
	 */
	public CustomerData updateCustomerSubscriptions(final Boolean tipsNewsletter)
	{
		
		CustomerModel customerModel = getCurrentSessionCustomer();		

		customerModel = heringCustomerAccountService.updateCustomerSubscriptions(customerModel, tipsNewsletter);

		CustomerData customerData = new CustomerData();
		customerConverter.convert(customerModel, customerData);
			
		return customerData;
		
	}
	
	
	@Override
	protected void validateDataBeforeUpdate(final CustomerData customerData)
	{
		validateParameterNotNullStandardMessage("customerData", customerData);
		Assert.hasText(customerData.getFirstName(), "The field [FirstName] cannot be empty");
		Assert.hasText(customerData.getLastName(), "The field [LastName] cannot be empty");
		
		if (Config.getBoolean("fliegercommerce.feature.enable.cpf", false))
		{
			Assert.hasText(customerData.getCpfcnpj(), "The field [Cpfcnpj] cannot be empty");
		}
		
		Assert.hasText(customerData.getUid(), "The field [Uid] cannot be empty");
		Assert.notNull(customerData.getBirthday(), "The field [Birthday] cannot be empty");
	}
	
	
	@Override
	public void register(final RegisterData registerData) throws DuplicateUidException
	{
		validateParameterNotNullStandardMessage("registerData", registerData);
		Assert.hasText(registerData.getFirstName(), "The field [FirstName] cannot be empty");
		Assert.hasText(registerData.getLastName(), "The field [LastName] cannot be empty");
		Assert.hasText(registerData.getLogin(), "The field [Login] cannot be empty");
		
		if (Config.getBoolean("fliegercommerce.feature.enable.cpf", false))
		{
			Assert.hasText(registerData.getCpfcnpj(), "The field [CPF] cannot be empty");
		}
		
//		Assert.notNull(registerData.getBirthday(), "The field [Birthday] cannot be empty");
		
		final CustomerModel newCustomer = getModelService().create(CustomerModel.class);
		newCustomer.setName(getCustomerNameStrategy().getName(registerData.getFirstName(), registerData.getLastName()));

		if (StringUtils.isNotBlank(registerData.getFirstName()) && StringUtils.isNotBlank(registerData.getLastName()))
		{
			newCustomer.setName(getCustomerNameStrategy().getName(registerData.getFirstName(), registerData.getLastName()));
		}
		
		setUidForRegister(registerData, newCustomer);
		newCustomer.setSessionLanguage(getCommonI18NService().getCurrentLanguage());
		newCustomer.setSessionCurrency(getCommonI18NService().getCurrentCurrency());
		
		if (Config.getBoolean("fliegercommerce.feature.enable.cpf", false))
		{
			newCustomer.setCpfcnpj(registerData.getCpfcnpj());
		}
		
		newCustomer.setGender(registerData.getGender());
//		newCustomer.setBirthday(registerData.getBirthday());
		newCustomer.setRgIe(registerData.getRgIe());
		newCustomer.setUfIe(registerData.getUfIe());
		
		final BaseStoreModel baseStore = baseStoreService.getCurrentBaseStore();
		registerData.setBaseStore(baseStore != null ? baseStore.getUid() : null);
		
		getCustomerAccountService().register(newCustomer, registerData.getPassword());
		LOG.info("DISPAROU o EVENTO DE REGISTER");
		eventService.publishEvent(new CustomerRegisterEvent(registerData));
	}
	
	/* (non-Javadoc)
	 * @see br.hering.facades.customer.HeringCustomerFacade#createGuestUserForAnonymousCheckout(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void createGuestUserForAnonymousCheckout(String email, String cpfcnpj, String name, Date birthday, Gender gender) throws DuplicateUidException
	{
		validateParameterNotNullStandardMessage("email", email);
		
//		if (Config.getBoolean("fliegercommerce.feature.enable.cpf", false))
//		{
//			validateParameterNotNullStandardMessage("cpfcnpj", cpfcnpj);
//		}
		
//		validateParameterNotNullStandardMessage("birthday", birthday);
		
		final CustomerModel guestCustomer = getModelService().create(CustomerModel.class);
		
		final String guid = generateGUID();

		//takes care of localizing the name based on the site language
		guestCustomer.setUid(guid + "|" + email);
		guestCustomer.setName(name);
		
//		if (Config.getBoolean("fliegercommerce.feature.enable.cpf", false))
//		{
//			guestCustomer.setCpfcnpj(cpfcnpj);
//		}
		
		guestCustomer.setType(CustomerType.valueOf(CustomerType.GUEST.getCode()));
		guestCustomer.setSessionLanguage(getCommonI18NService().getCurrentLanguage());
		guestCustomer.setSessionCurrency(getCommonI18NService().getCurrentCurrency());
		guestCustomer.setBirthday(birthday);
		guestCustomer.setGender(gender);
		
		getCustomerAccountService().registerGuestForAnonymousCheckout(guestCustomer, guid);
		updateCartWithGuestForAnonymousCheckout(getCustomerConverter().convert(guestCustomer));
	}


	/* (non-Javadoc)
	 * @see de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade#createGuestUserForAnonymousCheckout(java.lang.String, java.lang.String)
	 */
	@Override
	public void createGuestUserForAnonymousCheckout(String email, String name) throws DuplicateUidException
	{
		createGuestUserForAnonymousCheckout(email, null, name, new Date(), null);
	}


	/* (non-Javadoc)
	 * @see br.hering.facades.customer.HeringCustomerFacade#cpfCnpjAlreadyExists(java.lang.String)
	 */
	@Override
	public CustomerModel cpfCnpjAlreadyExists(String cpfCnpj)
	{
		
		KPCustomerAccountService customerAccountService = (KPCustomerAccountService) getCustomerAccountService();
		CustomerModel customer = customerAccountService.getCustomerByCpfCnpj(cpfCnpj);
		
		return customer;

	}


	/* (non-Javadoc)
	 * @see br.hering.facades.customer.HeringCustomerFacade#updatePasswordWithSalt(java.lang.String)
	 */
	@Override
	public void updatePasswordWithSalt(String email, String password)
	{
		UserModel userModel = userService.getUserForUID(email);
		LOG.info(userModel.getEncodedPassword());
		
		if(userModel.getEncodedPassword().contains(":")){
			LOG.info("Usuário com senha não atualizada");
			
			String[] passwordWithSalt = userModel.getEncodedPassword().split(":");
			String saltPassword = passwordWithSalt[1] + password;
//			LOG.info("salt + password: " + passwordWithSalt[1] + password);
			
			String encodedCurrentPassword = getMd5Hash(saltPassword);
			LOG.info("encodedCurrentPassword: " + encodedCurrentPassword);
			
			if (encodedCurrentPassword.equals(passwordWithSalt[0])) {
				LOG.info("Atualização de senha");
				
				userService.setPassword(userModel, password, userModel.getPasswordEncoding());
				modelService.save(userModel);
				LOG.info("Senha atualizada");
			}
		}
	}
	
	private String getMd5Hash(String password){
		
		try{ 
         MessageDigest md = MessageDigest.getInstance("MD5");
         md.update(password.getBytes());
   
         byte byteData[] = md.digest();
   
         //convert the byte to hex format method 1
         StringBuffer sb = new StringBuffer();
         for (int i = 0; i < byteData.length; i++) {
          sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
         }
   
         System.out.println("Digest(in hex format):: " + sb.toString());
   
         //convert the byte to hex format method 2
         StringBuffer hexString = new StringBuffer();
        	for (int i=0;i<byteData.length;i++) {
        		String hex=Integer.toHexString(0xff & byteData[i]);
       	     	if(hex.length()==1) hexString.append('0');
       	     	hexString.append(hex);
        	}
     		System.out.println("Digest(in hex format):: " + hexString.toString());
     		return hexString.toString();
  		
		}catch(NoSuchAlgorithmException e){
			e.getStackTrace();
		}
		return "";
	}
	
	public void registerGuest(GuestRegisterData guestData) throws DuplicateUidException
	{
		
		String email = guestData.getEmail();
		String name = guestData.getFirstName()+" "+guestData.getLastName();
		
		validateParameterNotNullStandardMessage("email", email);
		
		final AddressData newAddress = new AddressData();
		newAddress.setTitleCode(guestData.getTitleCode());
		newAddress.setFirstName(guestData.getFirstName());
		newAddress.setLastName(guestData.getLastName());
		newAddress.setComplement(guestData.getComplement());
		newAddress.setLine1(guestData.getAddress());
		newAddress.setNumber(guestData.getNumber());
		newAddress.setPostalCode(guestData.getZipCode());
		newAddress.setTown(guestData.getCity());
		newAddress.setCountry(i18NFacade.getCountryForIsocode(guestData.getCountry()));
		newAddress.setPhone(guestData.getPhone());
		newAddress.setDefaultAddress(true);
		newAddress.setBillingAddress(true);
		newAddress.setShippingAddress(true);
		newAddress.setVisibleInAddressBook(true);
		newAddress.setType(TipoDeEndereco.valueOf("RESIDENCIAL"));
		
		
		final CustomerModel guestCustomer = getModelService().create(CustomerModel.class);
		
		final String guid = generateGUID();

		
		final AddressModel addressModel = getModelService().create(AddressModel.class);
		getAddressReversePopulator().populate(newAddress, addressModel);
		
		addressModel.setOwner(guestCustomer);
		
		//takes care of localizing the name based on the site language
		guestCustomer.setUid(guid + "|" + email);
		guestCustomer.setName(name);
		guestCustomer.setAddresses(Collections.singletonList(addressModel));
		
		guestCustomer.setType(CustomerType.valueOf(CustomerType.GUEST.getCode()));
		guestCustomer.setSessionLanguage(getCommonI18NService().getCurrentLanguage());
		guestCustomer.setSessionCurrency(getCommonI18NService().getCurrentCurrency());

		getCustomerAccountService().registerGuestForAnonymousCheckout(guestCustomer, guid);
		updateCartWithGuestForAnonymousCheckout(getCustomerConverter().convert(guestCustomer));
		
	}


	public Populator<AddressData, AddressModel> getAddressReversePopulator() {
		return addressReversePopulator;
	}


	public void setAddressReversePopulator(
			Populator<AddressData, AddressModel> addressReversePopulator) {
		this.addressReversePopulator = addressReversePopulator;
	}

}