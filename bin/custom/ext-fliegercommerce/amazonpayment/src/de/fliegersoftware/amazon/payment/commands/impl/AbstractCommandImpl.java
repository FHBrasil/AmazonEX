package de.fliegersoftware.amazon.payment.commands.impl;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsService;
import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceClient;
import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceConfig;

import de.fliegersoftware.amazon.core.services.impl.DefaultAmazonConfigService;

/**
 * @author taylor.savegnago
 * 
 */
@Component
public class AbstractCommandImpl {

	protected static final String NOT_SUPPORTED_MESSAGE = "Command is not supported by Amazon extension: ";
	
	@Resource
	public DefaultAmazonConfigService defaultAmazonConfigService;
	
	protected String getSellerId() {
		return defaultAmazonConfigService.getSellerId();
	}
	
	protected OffAmazonPaymentsService getOffAmazonPaymentsService() {
		OffAmazonPaymentsServiceConfig config;
		OffAmazonPaymentsService service;
		
		config = new OffAmazonPaymentsServiceConfig(defaultAmazonConfigService.getAmazonProperties());
		service = new OffAmazonPaymentsServiceClient(config);
		
		return service;
	}

	public DefaultAmazonConfigService getDefaultAmazonConfigService() {
		return defaultAmazonConfigService;
	}

	public void setDefaultAmazonConfigService(DefaultAmazonConfigService defaultAmazonConfigService) {
		this.defaultAmazonConfigService = defaultAmazonConfigService;
	}
}
