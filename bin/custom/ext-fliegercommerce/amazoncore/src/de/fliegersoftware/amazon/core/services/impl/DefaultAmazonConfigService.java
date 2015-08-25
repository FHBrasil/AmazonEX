package de.fliegersoftware.amazon.core.services.impl;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;

import de.fliegersoftware.amazon.core.enums.AuthorizationModeEnum;
import de.fliegersoftware.amazon.core.enums.CaptureModeEnum;
import de.fliegersoftware.amazon.core.model.config.AmazonConfigModel;
import de.fliegersoftware.amazon.core.services.AmazonConfigService;
import de.hybris.platform.store.services.BaseStoreService;

public class DefaultAmazonConfigService implements AmazonConfigService {
	
	protected BaseStoreService baseStoreService;
	
	@Override
	public Properties getAmazonProperties() {
		Properties properties = new Properties();
		if (baseStoreService.getCurrentBaseStore().getAmazonConfig() != null) {
			AmazonConfigModel amazonConfig = baseStoreService.getCurrentBaseStore().getAmazonConfig();
			properties.put(ACCESS_KEY_ID, amazonConfig.getMwsAccessKey());
			properties.put(SECRET_ACCESS_KEY, amazonConfig.getMwsSecretKey());
			properties.put(APPLICATION_VERSION, "");
			properties.put(SELLER_ID, amazonConfig.getMerchantId());
			properties.put(REGION, amazonConfig.getAmazonConfigCountry().getCode());
			properties.put(CURRENCY, "EUR");
			properties.put(ENVIRONMENT, "");
	
			if (amazonConfig.isSandboxMode())
			{
				properties.put(APPLICATION_NAME, "KPFamily Sandbox");
				properties.put(ENVIRONMENT, "SANDBOX");
				properties.put(PLACE_ORDER_URL, "http://localhost:9001/");
			}
			else
			{
				properties.put(ENVIRONMENT, "LIVE");
				properties.put(PLACE_ORDER_URL, "http://www.babyartikel.de/");
			}
			properties.put(CERT_CN, "sns.amazonaws.com");
		}
		return properties;
	}
	
	@Override
	public String getClientId() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getClientId();
	}
	
	@Override
	public String getSellerId() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getMerchantId();
	}
	
	@Override
	public String getRegion() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getAmazonConfigCountry().getCode();
	}

	@Override
	public boolean isSandboxMode() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().isSandboxMode();
	}
	
	@Override
	public int getAddressWidgetWidth() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getAddressWidgetWidth();
	}
	
	@Override
	public int getAddressWidgetHeight() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getAddressWidgetHeight();
	}
	
	@Override
	public String getLoginButtonColor() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getLoginButtonColor().getCode();
	}
	
	@Override
	public String getLoginButtonSize() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getLoginButtonSize().getCode();
	}
	
	@Override
	public String getPayButtonColor() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getPayButtonColor().getCode();
	}
	
	@Override
	public String getPayButtonSize() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getPayButtonSize().getCode();
	}
	
	@Override
	public int getPaymentWidgetHeight() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getPaymentWidgetHeight();
	}
	
	@Override
	public int getPaymentWidgetWidth() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getPaymentWidgetWidth();
	}

	@Override
	public AuthorizationModeEnum getAuthorizationMode() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getAuthorizationMode();
	}

	@Override
	public CaptureModeEnum getCaptureMode() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getCaptureMode();
	}
	
	protected BaseStoreService getBaseStoreService() {
		return baseStoreService;
	}
	
	@Required
	public void setBaseStoreService(BaseStoreService baseStoreService) {
		this.baseStoreService = baseStoreService;
	}
}