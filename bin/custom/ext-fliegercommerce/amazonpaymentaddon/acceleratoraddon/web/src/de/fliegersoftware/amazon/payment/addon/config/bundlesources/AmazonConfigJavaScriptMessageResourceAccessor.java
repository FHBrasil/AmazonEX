package de.fliegersoftware.amazon.payment.addon.config.bundlesources;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceConfig;
import com.hybris.addon.common.config.bundlesources.JavaScriptMessageResourcesAccessor;

import de.fliegersoftware.amazon.core.enums.OperationModeEnum;
import de.fliegersoftware.amazon.core.services.AmazonConfigService;
import de.fliegersoftware.amazon.core.services.impl.DefaultAmazonConfigService;

public class AmazonConfigJavaScriptMessageResourceAccessor implements JavaScriptMessageResourcesAccessor {

	private AmazonConfigService amazonConfigService;
	private String addOnName;

	@Override
	public Map<String, String> getAllMessages(Locale locale) {
		Map<String, String> amazonConfigs = new LinkedHashMap<String, String>();
		if (amazonConfigService.isEnabled()) {
			amazonConfigs.put("isAmazonEnabled", "true");
			
			if (OperationModeEnum.LOGINANDPAY.equals(amazonConfigService.getOperationMode()) || 
					OperationModeEnum.LOGINONLY.equals(amazonConfigService.getOperationMode())) {
				amazonConfigs.put("isAmazonLoginEnabled", "true");
			} else {
				amazonConfigs.put("isAmazonLoginEnabled", "false");
			}
			
			if (OperationModeEnum.LOGINANDPAY.equals(amazonConfigService.getOperationMode()) || 
					OperationModeEnum.PAYONLY.equals(amazonConfigService.getOperationMode())) {
				amazonConfigs.put("isAmazonPayEnabled", "true");
			} else {
				amazonConfigs.put("isAmazonPayEnabled", "false");
			}
			if (amazonConfigService.isHiddenButtonsMode()) {
				amazonConfigs.put("isHiddenButtonsMode", "true");
			} else {
				amazonConfigs.put("isHiddenButtonsMode", "false");
			}
		
			amazonConfigs.put("clientId", amazonConfigService.getClientId());
			amazonConfigs.put("sellerId", amazonConfigService.getSellerId());
	
			// configure widgets url
			String amazonWidgetUrl;
			String region = amazonConfigService.getRegion();
			amazonConfigs.put("region", region);
			
			boolean sandboxMode = amazonConfigService.isSandboxMode();
			if (sandboxMode) {
				amazonConfigs.put("isSandboxMode", "true");
			} else {
				amazonConfigs.put("isSandboxMode", "false");
			}
			
			OffAmazonPaymentsServiceConfig config = new OffAmazonPaymentsServiceConfig(amazonConfigService.getAmazonProperties());
			amazonWidgetUrl = config.getWidgetURL();
			amazonConfigs.put("amazonWidgetsUrl", amazonWidgetUrl);
			
			amazonConfigs.put("loginButtonColor", amazonConfigService.getLoginButtonColor());
			amazonConfigs.put("loginButtonSize", amazonConfigService.getLoginButtonSize());
			amazonConfigs.put("payButtonColor", amazonConfigService.getPayButtonColor());
			amazonConfigs.put("payButtonSize", amazonConfigService.getPayButtonSize());
			amazonConfigs.put("paymentWidgetHeight", String.valueOf(amazonConfigService.getPaymentWidgetHeight()));
			amazonConfigs.put("paymentWidgetWidth", String.valueOf(amazonConfigService.getPaymentWidgetWidth()));
			amazonConfigs.put("addressWidgetHeight", String.valueOf(amazonConfigService.getAddressWidgetHeight()));
			amazonConfigs.put("addressWidgetWidth", String.valueOf(amazonConfigService.getAddressWidgetWidth()));
		} else {
			amazonConfigs.put("isAmazonEnabled", "false");
		}
		return amazonConfigs;
	}

	@Override
	public String getAddOnName() {
		return addOnName;
	}

	public void setAddOnName(String addOnName) {
		this.addOnName = addOnName;
	}

	public AmazonConfigService getAmazonConfigService() {
		return amazonConfigService;
	}

	public void setAmazonConfigService(AmazonConfigService amazonConfigService) {
		this.amazonConfigService = amazonConfigService;
	}
}
