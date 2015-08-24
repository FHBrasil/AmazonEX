package de.fliegersoftware.amazon.payment.addon.config.bundlesources;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import com.hybris.addon.common.config.bundlesources.JavaScriptMessageResourcesAccessor;

import de.fliegersoftware.amazon.core.services.AmazonConfigService;
import de.fliegersoftware.amazon.core.services.impl.DefaultAmazonConfigService;

public class AmazonConfigJavaScriptMessageResourceAccessor implements JavaScriptMessageResourcesAccessor {

	private AmazonConfigService amazonConfigService;
	private String addOnName;

	@Override
	public Map<String, String> getAllMessages(Locale locale) {
		Map<String, String> messages = new LinkedHashMap<String, String>();
		for(Entry<Object, Object> prop : amazonConfigService.getAmazonProperties().entrySet()) {
			messages.put((String)prop.getKey(), (String)prop.getValue());
		}
		messages.put("clientId", amazonConfigService.getClientId());

		// configure widgets url
		String amazonWidgetUrl;
		String region = amazonConfigService.getRegion();
		boolean sandboxMode = amazonConfigService.isSandboxMode();
		if("DE".equals(region) && sandboxMode) {
			amazonWidgetUrl = "https://static-eu.payments-amazon.com/OffAmazonPayments/de/sandbox/lpa/js/Widgets.js";
		} else if ("DE".equals(region) && !sandboxMode){
			amazonWidgetUrl = "https://static-eu.payments-amazon.com/OffAmazonPayments/de/lpa/js/Widgets.js";
		} else {
			amazonWidgetUrl = "https://static-eu.payments-amazon.com/OffAmazonPayments/uk/sandbox/lpa/js/Widgets.js";
		}
		messages.put("amazonWidgetsUrl", amazonWidgetUrl);
//		messages.put("amazonWidgetUrl", "https://static-eu.payments-amazon.com/OffAmazonPayments/de/sandbox/lpa/js/Widgets.js");
//		messages.put("clientId", "amzn1.application-oa2-client.3a8eb36356824cb4b58183861bbcb8d1");
//		messages.put("accessKeyId", "AKIAI6WXQHLXKDEUQPMA");
//		messages.put("secretAccessKey", "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");
//		messages.put("applicationName", "KPFamily Sandbox");
//		messages.put("applicationVersion", "");
//		messages.put("sellerId", "A3NZCWJS2BIERH");
//		messages.put("currency", "EUR");
//
//		messages.put("environment", "SANDBOX");
//		messages.put("region", "DE");
//		messages.put("placeOrderUrl", "http://localhost:9001/");
//		messages.put("certCN", "sns.amazonaws.com");
		return messages;
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
