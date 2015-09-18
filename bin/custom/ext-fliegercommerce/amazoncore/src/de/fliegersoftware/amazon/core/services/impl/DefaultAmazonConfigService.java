package de.fliegersoftware.amazon.core.services.impl;

import java.util.Currency;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import de.fliegersoftware.amazon.core.enums.AuthorizationModeEnum;
import de.fliegersoftware.amazon.core.enums.AccountMatchingStrategyEnum;
import de.fliegersoftware.amazon.core.enums.CaptureModeEnum;
import de.fliegersoftware.amazon.core.enums.OperationModeEnum;
import de.fliegersoftware.amazon.core.jalo.config.AmazonConfig;
import de.fliegersoftware.amazon.core.model.config.AmazonConfigModel;
import de.fliegersoftware.amazon.core.services.AmazonConfigService;
import de.hybris.platform.store.services.BaseStoreService;

public class DefaultAmazonConfigService implements AmazonConfigService {
	private static final Logger LOG = Logger.getLogger(DefaultAmazonConfigService.class);
	
	protected BaseStoreService baseStoreService;
	
	@Override
	public boolean hasAmazonConfig() {
		return getBaseStoreService().getCurrentBaseStore().getAmazonConfig() != null;
	}
	
	@Override
	public Properties getAmazonProperties() {
		Properties properties = new Properties();
		if (hasAmazonConfig()) {
			AmazonConfigModel amazonConfig = getBaseStoreService().getCurrentBaseStore().getAmazonConfig();
			properties.put(ACCESS_KEY_ID, amazonConfig.getMwsAccessKey());
			properties.put(SECRET_ACCESS_KEY, amazonConfig.getMwsSecretKey());
			properties.put(SELLER_ID, amazonConfig.getMerchantId());
			properties.put(REGION, getCountryCode(amazonConfig));
			properties.put(CURRENCY, getCurrencyByRegion(amazonConfig));
			properties.put(APPLICATION_NAME, StringUtils.defaultString(amazonConfig.getApplicationName()));
			properties.put(APPLICATION_VERSION, StringUtils.defaultString(amazonConfig.getApplicationVersion()));
			properties.put(ENVIRONMENT, getEnvironment(amazonConfig.isSandboxMode()));
			properties.put(CERT_CN, AMAZONWS);
		}
		return properties;
	}
	
	@Override
	public boolean isEnabled() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().isEnabled();
		else
			return false;
	}
	
	@Override
	public boolean isEnableERPMode() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().isEnableERPMode();
		else
			return false;
	}
	
	@Override
	public String getClientId() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().getClientId();
		else
			return null;
	}
	
	@Override
	public String getSellerId() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().getMerchantId();
		else
			return null;
	}
	
	@Override
	public String getRegion() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().getAmazonConfigCountry().getCode();
		else
			return null;
	}

	@Override
	public boolean isSandboxMode() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().isSandboxMode();
		else
			return false;
	}

	@Override
	public boolean isHiddenButtonsMode() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().isHiddenButtonsMode();
		else
			return false;
	}

	@Override
	public OperationModeEnum getOperationMode() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().getOperationMode();
		else
			return null;
	}
	
	@Override
	public int getAddressWidgetWidth() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().getAddressWidgetWidth();
		else
			return 0;
	}
	
	@Override
	public int getAddressWidgetHeight() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().getAddressWidgetHeight();
		else
			return 0;
	}
	
	@Override
	public String getLoginButtonColor() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().getLoginButtonColor().getCode();
		else
			return null;
	}
	
	@Override
	public String getLoginButtonSize() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().getLoginButtonSize().getCode();
		else
			return null;
	}
	
	@Override
	public String getPayButtonColor() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().getPayButtonColor().getCode();
		else
			return null;
	}
	
	@Override
	public String getPayButtonSize() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().getPayButtonSize().getCode();
		else
			return null;
	}
	
	@Override
	public int getPaymentWidgetHeight() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().getPaymentWidgetHeight();
		else
			return 0;
	}
	
	@Override
	public int getPaymentWidgetWidth() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().getPaymentWidgetWidth();
		else
			return 0;
	}

	@Override
	public AuthorizationModeEnum getAuthorizationMode() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().getAuthorizationMode();
		else
			return null;
	}

	@Override
	public CaptureModeEnum getCaptureMode() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().getCaptureMode();
		else
			return null;
	}

	@Override
	public AccountMatchingStrategyEnum getAccountMatchingStrategy() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().getAccountMatchingStrategy();
		else
			return null;
	}
	
	@Override
	public boolean isExcludePackstationDelivery() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().isExcludePackstationDelivery();
		else
			return false;
	}
	
	@Override
	public boolean isManualAdditionOnFirstLogin() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().isManualAddOnFirstLogin();
		else
			return false;
	}
	
	@Override
	public String getOrderStatusOnSuccessfullShipping() {
		if(hasAmazonConfig())
			return getBaseStoreService().getCurrentBaseStore().getAmazonConfig().getOrderStatusOnSuccessfullShipping();
		else
			return null;
	}
	
	protected BaseStoreService getBaseStoreService() {
		return baseStoreService;
	}
	
	@Required
	public void setBaseStoreService(BaseStoreService baseStoreService) {
		this.baseStoreService = baseStoreService;
	}
	
	/**
	 * @param amazonConfig
	 * @return currency code
	 */
	private String getCurrencyByRegion(final AmazonConfigModel amazonConfig)
	{
		if ("Other".equals(amazonConfig.getAmazonConfigCountry().getCode()))
		{
			return amazonConfig.getOtherCountryCurrency();
		}

		String countryCode = getCountryCode(amazonConfig);
		try
		{
			if ("uk".equalsIgnoreCase(countryCode))
			{
				countryCode = "gb";
			}
			final Locale locale = new Locale("", countryCode);
			final Currency currency = Currency.getInstance(locale);
			return currency.getCurrencyCode();
		}
		catch (final Exception e)
		{
			LOG.error("Currency could not found for region", e);
		}
		return null;
	}
	
	/**
	 * Get the country code from amazon configuration
	 * 
	 * @param amazonConfig
	 * @return country code
	 */
	private String getCountryCode(final AmazonConfigModel amazonConfig)
	{
		if ("Other".equalsIgnoreCase(amazonConfig.getAmazonConfigCountry().getCode()))
		{
			return amazonConfig.getOtherCountry();
		}
		return amazonConfig.getAmazonConfigCountry().getCode();
	}

	private String getEnvironment(final Boolean sandbox)
	{
		return sandbox != null && sandbox.booleanValue() ? SANDBOX : LIVE;
	}
}