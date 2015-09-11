package de.fliegersoftware.amazon.payment.addon.config.bundlesources;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import de.fliegersoftware.amazon.core.enums.OperationModeEnum;
import de.fliegersoftware.amazon.core.services.AmazonConfigService;
import de.hybris.platform.addonsupport.config.bundlesources.JavaScriptMessageResourcesAccessor;

public class AmazonConfigJavaScriptMessageResourceAccessor implements JavaScriptMessageResourcesAccessor {

	private AmazonConfigService amazonConfigService;
	private String addOnName;

	@Override
	public Map<String, String> getAllMessages(Locale locale) {
		Map<String, String> amazonConfigs = new LinkedHashMap<String, String>();
		if (amazonConfigService.isEnabled()) {
			amazonConfigs.put("isAmazonEnabled", "true");
			
			if (amazonConfigService.isHiddenButtonsMode()) {
				amazonConfigs.put("isHiddenButtonsMode", "true");
			}
			if (OperationModeEnum.LOGINANDPAY.equals(amazonConfigService.getOperationMode()) || 
					OperationModeEnum.LOGINONLY.equals(amazonConfigService.getOperationMode())) {
				amazonConfigs.put("isAmazonLoginEnabled", "true");
			}
			
			if (OperationModeEnum.LOGINANDPAY.equals(amazonConfigService.getOperationMode()) || 
					OperationModeEnum.PAYONLY.equals(amazonConfigService.getOperationMode())) {
				amazonConfigs.put("isAmazonPayEnabled", "true");
			}
		
			amazonConfigs.put("clientId", amazonConfigService.getClientId());
			amazonConfigs.put("sellerId", amazonConfigService.getSellerId());
	
			// configure widgets url
			String amazonWidgetUrl;
			String region = amazonConfigService.getRegion();
			boolean sandboxMode = amazonConfigService.isSandboxMode();
			amazonConfigs.put("region", region);
			if("DE".equals(region) && sandboxMode) {
				amazonWidgetUrl = "https://static-eu.payments-amazon.com/OffAmazonPayments/de/sandbox/lpa/js/Widgets.js";
			} else if ("DE".equals(region) && !sandboxMode){
				amazonWidgetUrl = "https://static-eu.payments-amazon.com/OffAmazonPayments/de/lpa/js/Widgets.js";
			} else if(sandboxMode) {
				amazonWidgetUrl = "https://static-eu.payments-amazon.com/OffAmazonPayments/uk/sandbox/lpa/js/Widgets.js";
			} else {
				amazonWidgetUrl = "https://static-eu.payments-amazon.com/OffAmazonPayments/uk/lpa/js/Widgets.js";
			}
			amazonConfigs.put("amazonWidgetsUrl", amazonWidgetUrl);
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
