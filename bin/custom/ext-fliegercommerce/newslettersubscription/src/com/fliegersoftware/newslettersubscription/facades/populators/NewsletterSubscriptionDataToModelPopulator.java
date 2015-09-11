/**
 * 
 */
package com.fliegersoftware.newslettersubscription.facades.populators;

import org.springframework.beans.factory.annotation.Required;

import br.hering.facades.product.data.GenderData;

import com.fliegersoftware.newslettersubscription.data.NewsletterSubscriptionData;
import com.fliegersoftware.newslettersubscription.data.SubscriptionTypeData;
import com.fliegersoftware.newslettersubscription.enums.SubscriptionType;
import com.fliegersoftware.newslettersubscription.model.NewsletterSubscriptionModel;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.enumeration.EnumerationMetaTypeModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;


/**
 * @author luiza
 *
 */
public class NewsletterSubscriptionDataToModelPopulator implements Populator<NewsletterSubscriptionData, NewsletterSubscriptionModel>
{

	private BaseStoreService baseStoreService;
	
	private CommonI18NService commonI18NService;

	private UserService userService;
	
	private EnumerationService enumerationService;
	
	private TypeService typeService;
			
	
	@Override
	public void populate(NewsletterSubscriptionData source, NewsletterSubscriptionModel target) throws ConversionException
	{
		
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setEmail(source.getEmail());
		target.setBirthDay(source.getBirthDay());
			
		final String genderCode = source.getGenderCode();	
		final Gender genderEnumValue = getEnumerationService().getEnumerationValue(Gender.class, genderCode);
		if (genderEnumValue != null)
		{		
			target.setGender(genderEnumValue);
		}
		
		final String titleCode = source.getTitleCode();		
		final TitleModel titleModel = getUserService().getTitleForCode(titleCode);
		if (titleModel != null)
		{
			target.setTitle(titleModel);
		}
		
		final String languageIsoCode = source.getLanguageIsoCode();
		final LanguageModel languageModel = getCommonI18NService().getLanguage(languageIsoCode);
		if (languageModel != null)
		{
			target.setLanguage(languageModel);
		}

		final String baseStoreUid = source.getStoreCode();
		final BaseStoreModel baseStoreModel = getBaseStoreService().getBaseStoreForUid(baseStoreUid);
		if (baseStoreModel != null)
		{
			target.setStore(baseStoreModel);
		}
		
		final CustomerData customer = source.getCustomer();
		final String customerUid = customer.getUid();
		UserModel customerModel = getUserService().getUserForUID(customerUid);
		if (customerModel != null)
		{
			target.setCustomer((CustomerModel) customerModel);
		}
		
	
		final SubscriptionTypeData subscriptionTypeData = source.getSubscriptionType();
		if (subscriptionTypeData != null)
		{
			final String subscriptionTypeCode = subscriptionTypeData.getCode();	
			SubscriptionType subscriptionType = getEnumerationService().getEnumerationValue(SubscriptionType.class, subscriptionTypeCode);
			target.setSubscriptionType(subscriptionType);
		}
		
	
	}

	
	/**
	 * @return the baseStoreService
	 */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	
	/**
	 * @param baseStoreService the baseStoreService to set
	 */
	@Required
	public void setBaseStoreService(BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	
	/**
	 * @return the commonI18NService
	 */
	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	
	/**
	 * @param commonI18NService the commonI18NService to set
	 */
	@Required
	public void setCommonI18NService(CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	
	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	
	/**
	 * @param userService the userService to set
	 */
	@Required
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	
	/**
	 * @return the enumerationService
	 */
	public EnumerationService getEnumerationService()
	{
		return enumerationService;
	}

	
	/**
	 * @param enumerationService the enumerationService to set
	 */
	@Required
	public void setEnumerationService(EnumerationService enumerationService)
	{
		this.enumerationService = enumerationService;
	}

	
}
