package de.getit.hybris.klarna.payment.klarnaapi;

import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import org.junit.Ignore;

import com.klarna.api.Klarna;

@Ignore
public class AbstractKlarnaConfigurationProvider extends ServicelayerTransactionalTest { 
		
	private Integer getMerchantId() {		
		return getConfigurationService().getConfiguration().getInt("klarna.junittest.merchantId"); 
	}

	private String getSharedSecret() {
		return  getConfigurationService().getConfiguration().getString("klarna.junittest.sharedSecret"); 
	}
	
	private ConfigurationService getConfigurationService() {
		return (ConfigurationService) Registry.getGlobalApplicationContext().getBean("configurationService");
	}
	
	protected Klarna createKlarnaConfig(final String countryCode) {
		return KlarnaFactory.getKlarnaConfig(countryCode, getMerchantId(), getSharedSecret());
	}
	
}
