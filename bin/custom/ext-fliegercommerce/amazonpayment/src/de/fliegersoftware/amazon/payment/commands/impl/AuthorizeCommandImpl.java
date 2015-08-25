/**
 * 
 */
package de.fliegersoftware.amazon.payment.commands.impl;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.amazonservices.mws.offamazonpayments.OffAmazonPaymentsServiceException;
import com.amazonservices.mws.offamazonpayments.model.AuthorizeRequest;
import com.amazonservices.mws.offamazonpayments.model.AuthorizeResponse;
import com.amazonservices.mws.offamazonpayments.model.AuthorizeResult;

import de.fliegersoftware.amazon.core.enums.AuthorizationModeEnum;
import de.fliegersoftware.amazon.core.enums.CaptureModeEnum;
import de.fliegersoftware.amazon.core.services.AmazonConfigService;
import de.fliegersoftware.amazon.payment.commands.AuthorizeCommand;
import de.fliegersoftware.amazon.payment.constants.AmazonpaymentConstants;
import de.fliegersoftware.amazon.payment.facades.AmazonSandboxSimulationFacade;
import de.hybris.platform.util.Config;

/**
 * @author taylor.savegnago
 * 
 */
@Component("authorizationCommand")
public class AuthorizeCommandImpl extends AbstractCommandImpl implements AuthorizeCommand {

	private final static Logger LOG = Logger.getLogger(AuthorizeCommandImpl.class);

	@Resource
	private AmazonSandboxSimulationFacade amazonSandboxSimulationFacade;

	@Override
	public AuthorizeResult perform(final AuthorizeRequest req) {

		try {
			LOG.info("-----------------------------------------------------");
			LOG.info("AuthorizationCommandImpl perform requested");
			LOG.info("-----------------------------------------------------");
			req.setSellerId(getSellerId());
//			if(Config.getBoolean(AmazonpaymentConstants.SYNCHRONIOUS_CHECKOUT_CONFIG, false)) {
//				req.setTransactionTimeout(Integer.valueOf(0));
//			}
//			if(Config.getBoolean(AmazonpaymentConstants.CHARGE_ON_ORDER_CONFIG, false)) {
//				req.setCaptureNow(true);
//			}
			if(AuthorizationModeEnum.AUTOMATICSYNCHRONOUS.equals(amazonConfigService.getAuthorizationMode())) {
				req.setTransactionTimeout(Integer.valueOf(0));
			}
			if(CaptureModeEnum.IMMEDIATE.equals(amazonConfigService.getCaptureMode())) {
				req.setCaptureNow(true);
			}
			if(amazonSandboxSimulationFacade.isSimulation()) {
				amazonSandboxSimulationFacade.decorate(req);
			}

			AuthorizeResponse authorize = getOffAmazonPaymentsService().authorize(req);
			final AuthorizeResult result = authorize.getAuthorizeResult();

			return result;

		} catch (OffAmazonPaymentsServiceException ex) {
			LOG.error("Caught Exception: " + ex.getMessage());
			LOG.error("Response Status Code: " + ex.getStatusCode());
			LOG.error("Error Code: " + ex.getErrorCode());
			LOG.error("Error Type: " + ex.getErrorType());
			LOG.error("Request ID: " + ex.getRequestId());
			LOG.error("XML: " + ex.getXML());
			LOG.error("ResponseHeaderMetadata: " + ex.getResponseHeaderMetadata());
			return null;
		}

	}

}
