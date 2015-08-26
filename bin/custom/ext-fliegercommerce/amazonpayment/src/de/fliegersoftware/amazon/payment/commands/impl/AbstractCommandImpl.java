package de.fliegersoftware.amazon.payment.commands.impl;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsService;
import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceClient;
import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceConfig;

import de.fliegersoftware.amazon.core.services.AmazonConfigService;
import de.fliegersoftware.amazon.core.services.impl.DefaultAmazonConfigService;

/**
 * @author taylor.savegnago
 * 
 */
@Component
public class AbstractCommandImpl {

	protected static final String NOT_SUPPORTED_MESSAGE = "Command is not supported by Amazon extension: ";
	
	@Resource
	protected AmazonConfigService amazonConfigService;
	
	protected String getSellerId() {
		return amazonConfigService.getSellerId();
	}
	
	protected OffAmazonPaymentsService getOffAmazonPaymentsService() {
		OffAmazonPaymentsServiceConfig config;
		OffAmazonPaymentsService service;
		
		config = new OffAmazonPaymentsServiceConfig(amazonConfigService.getAmazonProperties());
		service = new OffAmazonPaymentsServiceClient(config);
		
		return service;
	}

	protected AmazonConfigService getAmazonConfigService() {
		return amazonConfigService;
	}

	public void setAmazonConfigService(AmazonConfigService defaultAmazonConfigService) {
		this.amazonConfigService = defaultAmazonConfigService;
	}
}
