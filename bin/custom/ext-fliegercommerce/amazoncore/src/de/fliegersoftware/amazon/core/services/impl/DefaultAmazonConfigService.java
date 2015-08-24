package de.fliegersoftware.amazon.core.services.impl;

import java.util.Properties;

import javax.annotation.Resource;

import de.fliegersoftware.amazon.core.model.config.AmazonConfigModel;
import de.hybris.platform.store.services.BaseStoreService;

public class DefaultAmazonConfigService {
	
	public static final String ACCESS_KEY_ID = "accessKeyId";
	public static final String CURRENCY = "currency";
	public static final String SECRET_ACCESS_KEY = "secretAccessKey";
	public static final String APPLICATION_NAME = "applicationName";
	public static final String APPLICATION_VERSION = "applicationVersion";
	public static final String SELLER_ID = "sellerId";
	public static final String ENVIRONMENT = "environment";
	public static final String REGION = "region";
	public static final String PLACE_ORDER_URL = "placeOrderUrl";
	public static final String CERT_CN = "certCN";
	
	@Resource
	protected BaseStoreService baseStoreService;
	
	private Properties properties;

	public Properties getAmazonProperties() {
		
		if (properties == null) {
			properties = new Properties();
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
		}
		return properties;
	}

	public BaseStoreService getBaseStoreService() {
		return baseStoreService;
	}

	public void setBaseStoreService(BaseStoreService baseStoreService) {
		this.baseStoreService = baseStoreService;
	}
	
	public String getClientId() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getClientId();
	}
	
	public String getSellerId() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getMerchantId();
	}
	
	public String getRegion() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getAmazonConfigCountry().getCode();
	}

	public boolean isSandboxMode() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().isSandboxMode();
	}
	
	public int getAddressWidgetHeight() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getAddressWidgetHeight();
	}
	
	public int getAddressWidgetWidth() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getAddressWidgetWidth();
	}
	
	public String getLoginButtonColor() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getLoginButtonColor().getCode();
	}
	
	public String getLoginButtonSize() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getLoginButtonSize().getCode();
	}
	
	public String getPayButtonColor() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getPayButtonColor().getCode();
	}
	
	public String getPayButtonSize() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getPayButtonSize().getCode();
	}
	
	public int getPaymentWidgetHeight() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getPaymentWidgetHeight();
	}
	
	public int getPaymentWidgetWidth() {
		return baseStoreService.getCurrentBaseStore().getAmazonConfig().getPaymentWidgetWidth();
	}
	
}