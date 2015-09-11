/**
 * 
 */
package com.flieger.adyen.commands.impl;

import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.util.Config;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.adyen.services.common.Amount;
import com.adyen.services.payment.AdyenCaptureRequest;
import com.adyen.services.payment.AdyenCaptureResult;
import com.adyen.services.payment.AnyType2AnyTypeMapEntry;
import com.adyen.services.payment.ModificationRequest;
import com.adyen.services.payment.ModificationResult;
import com.flieger.adyen.command.CaptureCommand;
import com.flieger.constants.AdyenTransactionStatus;
import com.flieger.main.Credentials;
import com.flieger.services.WebServicesConn;

/**
 * @author flieger
 * @author felipe
 * 
 */
public class AdyenCaptureCommand implements CaptureCommand
{
	private static final Logger LOG = Logger.getLogger(AdyenCaptureCommand.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flieger.adyen.command.CaptureCommand#perform(com.adyen.services.payment.AdyenAuthorizationCaptureRequest)
	 */
	@Override
	public AdyenCaptureResult perform(AdyenCaptureRequest request)
	{	
		LOG.info("AdyenCaptureCommand.perform("+request+")");
	
		AdyenCaptureResult result = new AdyenCaptureResult();

		result.setCurrency(request.getCurrency());
		result.setTotalAmount(request.getValue());
		result.setRequestTime(new Date());
		result.setAdyenUniqueCode(request.getReference());

		ModificationRequest modRequest = new ModificationRequest();
		
		if (StringUtils.isBlank(request.getMerchantAccount()))
		{
			LOG.info("Merchant Account não está preenchido. Usando default dzarm");	
			request.setMerchantAccount(Config.getParameter(Credentials.MERCHANT_ACCOUNT + ".dzarm"));
			LOG.info(request.getMerchantAccount());
		} else {
			LOG.info("Merchant Account já está setada: " + request.getMerchantAccount());	
		}
		modRequest.setMerchantAccount(request.getMerchantAccount());
		Amount amount = new Amount();
		amount.setCurrency(request.getCurrency().getCurrencyCode());
		amount.setValue(request.getValue().multiply(new BigDecimal("100")).longValueExact());
		modRequest.setModificationAmount(amount);
		modRequest.setOriginalReference(request.getReference());
		modRequest.setAuthorisationCode(request.getAuthorizationCode());

		ModificationResult modResult = new ModificationResult();
		try {
			modResult = new WebServicesConn().capture(modRequest);
		} catch (Exception e) {	
			result.setTransactionStatus(TransactionStatus.ERROR);
			result.setTransactionStatusDetails(TransactionStatusDetails.COMMUNICATION_PROBLEM);
			LOG.error("Error: [" + e.getMessage() + "]", e);
			return result;
		}
		result.setAdyenReference(modResult.getPspReference());
		
		LOG.info("------------------------------");
		LOG.info("ModificationResult");
		LOG.info("Response: " + modResult.getResponse());
		LOG.info("PspReference: " + modResult.getPspReference());

		if (modResult.getAdditionalData() != null) {
			LOG.info("Entrys...");
			for (AnyType2AnyTypeMapEntry entry : modResult.getAdditionalData()) {
				LOG.info("Key: [" + entry.getKey() + "] Value:[" + entry.getValue() + "]");
			}
		}
		
		if (	AdyenTransactionStatus.CAPTURE_RECEIVED.equals(modResult.getResponse()) ) {
			result.setTransactionStatus(TransactionStatus.ACCEPTED);
			result.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL);
		} else {
			result.setTransactionStatus(TransactionStatus.REJECTED);
			result.setTransactionStatusDetails(TransactionStatusDetails.AUTHORIZATION_REJECTED_BY_PSP);
		}

		result.setMerchantTransactionCode(request.getMerchantTransactionCode());
		
		return result;
	}

}
