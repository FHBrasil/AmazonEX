package de.fliegersoftware.amazon.payment.commands.impl;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsService;

import de.fliegersoftware.amazon.payment.util.AmazonConfig;

/**
 * @author taylor.savegnago
 * 
 */
@Component
public class AbstractCommandImpl {

	protected static final String NOT_SUPPORTED_MESSAGE = "Command is not supported by Amazon extension: ";

	protected OffAmazonPaymentsService offAmazonPaymentsService = AmazonConfig.getAmazonPaymentService();
	
	protected static Properties properties = AmazonConfig.getProperties();
	
	
	protected String getSellerId() {
		return properties.getProperty(AmazonConfig.SELLER_ID);
	}

}
