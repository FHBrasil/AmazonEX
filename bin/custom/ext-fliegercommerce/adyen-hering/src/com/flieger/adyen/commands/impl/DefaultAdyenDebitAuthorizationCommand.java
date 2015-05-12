/**
 * 
 */
package com.flieger.adyen.commands.impl;

import de.hybris.platform.acceleratorservices.urlresolver.SiteBaseUrlResolutionService;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.util.Config;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.adyen.services.common.Amount;
import com.adyen.services.common.BrowserInfo;
import com.adyen.services.payment.AdyenAuthorizationResult;
import com.adyen.services.payment.AdyenDebitAuthorizationRequest;
import com.adyen.services.payment.AdyenDebitAuthorizationResult;
import com.adyen.services.payment.AnyType2AnyTypeMapEntry;
import com.adyen.services.payment.Card;
import com.adyen.services.payment.PaymentRequest;
import com.adyen.services.payment.PaymentRequest3D;
import com.adyen.services.payment.PaymentResult;
import com.flieger.adyen.command.AdyenDebitAuthorizationCommand;
import com.flieger.constants.AdyenConstants;
import com.flieger.constants.AdyenTransactionStatus;
import com.flieger.main.Credentials;
import com.flieger.services.WebServicesConn;
import com.flieger.payment.model.HeringDebitPaymentInfoModel;

/**
 * @author franthescollymaneira
 */
public class DefaultAdyenDebitAuthorizationCommand implements AdyenDebitAuthorizationCommand
{
	private static final Logger LOG = Logger.getLogger(DefaultAdyenDebitAuthorizationCommand.class.getName());

	private static final String ECOMMERCE = "Ecommerce";

	@Resource
	private CMSSiteService cmsSiteService;

	@Resource
	private SiteBaseUrlResolutionService siteBaseUrlResolutionService;

	/**
	 * Operation of the authorize in the adyen sp
	 * */
	@Override
	public AdyenDebitAuthorizationResult perform(AdyenDebitAuthorizationRequest authorizationRequest)
	{
		PaymentResult payResult = null;
		AdyenDebitAuthorizationResult authorizationResult = new AdyenDebitAuthorizationResult();
		try
		{
			//assemble the PaymentRequest
			PaymentRequest paymentRequest = new PaymentRequest();

			HeringDebitPaymentInfoModel debitPaymentInfoModel = authorizationRequest.getHeringDebitPaymentInfoModel();

			BrowserInfo browserInfo = new BrowserInfo();
			browserInfo.setAcceptHeader(debitPaymentInfoModel.getAccept());
			browserInfo.setUserAgent(debitPaymentInfoModel.getUserAgent());

			AnyType2AnyTypeMapEntry[] additionalData =
				{	new AnyType2AnyTypeMapEntry("returnUrl", debitPaymentInfoModel.getReturnUrl()),
					new AnyType2AnyTypeMapEntry("overwriteBrand", "true") };

			paymentRequest.setBrowserInfo(browserInfo);
			paymentRequest.setAdditionalData(additionalData);
			paymentRequest.setSelectedBrand(authorizationRequest.getSelectedBrand());
			paymentRequest.setMerchantAccount(authorizationRequest.getMerchantAccount());

			Amount amount = new Amount();
			amount.setCurrency(debitPaymentInfoModel.getCurrency());
			amount.setValue(debitPaymentInfoModel.getAmount().multiply(new BigDecimal(100)).longValueExact());
			paymentRequest.setAmount(amount);

			Card card = new Card();
			card.setHolderName(debitPaymentInfoModel.getCardAccountHolderName());
			card.setCvc(debitPaymentInfoModel.getCardCvNumber());

			String month = debitPaymentInfoModel.getCardExpirationMonth().toString();
			if (month.length() < 2)
			{
				month = "0" + month;
			}

			card.setExpiryMonth(month);
			card.setExpiryYear(debitPaymentInfoModel.getCardExpirationYear().toString());
			card.setNumber(debitPaymentInfoModel.getAccountNumber());

			paymentRequest.setCard(card);
			paymentRequest.setReference(authorizationRequest.getReference());

			if (authorizationRequest.getBuyerEmail().contains("|"))
			{
				paymentRequest.setShopperEmail(authorizationRequest.getBuyerEmail().split("\\|")[1]);
				paymentRequest.setShopperReference(authorizationRequest.getUserUid().split("\\|")[1]);
			}
			else
			{
				paymentRequest.setShopperEmail(authorizationRequest.getBuyerEmail());
				paymentRequest.setShopperReference(authorizationRequest.getUserUid());
			}

			paymentRequest.setShopperInteraction(ECOMMERCE);

			try
			{
				printRequest(paymentRequest);
			
				payResult = new WebServicesConn().authorise(paymentRequest);
				if (payResult.getAdditionalData() != null)
				{
					for (AnyType2AnyTypeMapEntry entry : payResult.getAdditionalData())
					{
						if ("mpiImplementationType".equalsIgnoreCase((String) entry.getKey()))
						{
							authorizationResult.setMpiImplementationType((String) entry.getValue());
							break;
						}
					}
				}
			}
			catch (Exception e)
			{
				authorizationResult.setTransactionStatus(TransactionStatus.ERROR);
				authorizationResult.setTransactionStatusDetails(TransactionStatusDetails.COMMUNICATION_PROBLEM);
				LOG.error("Error: [" + e.getMessage() + "]", e);
				return authorizationResult;
			}
			finally
			{
				printResult(payResult);
			}

			if (!StringUtils.isEmpty(payResult.getRefusalReason()) && payResult.getRefusalReason().matches("(^[0-9]{3}).*"))
			{
				authorizationResult.setErrorCode(payResult.getRefusalReason().substring(0, 3));
			}

			authorizationResult.setIssuerUrl(payResult.getIssuerUrl());
			authorizationResult.setMd(payResult.getMd());
			authorizationResult.setResultCode(payResult.getResultCode());
			authorizationResult.setAmount(amount);
		}
		catch (Exception e)
		{
			authorizationResult.setErrorCode("00");
			LOG.error("Ocorreu um erro na autorização do débito online pelo Adyen [" + e.getMessage() + "]", e);

			authorizationResult.setTransactionStatus(TransactionStatus.ERROR);
			authorizationResult.setTransactionStatusDetails(TransactionStatusDetails.UNKNOWN_CODE);
		}

		if (payResult.getResultCode() != null && payResult.getResultCode().equals(AdyenTransactionStatus.REDIRECT_SHOPPER))
		{
			authorizationResult.setTransactionStatus(TransactionStatus.ACCEPTED);
			authorizationResult.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL);
		}
		else
		{
			authorizationResult.setTransactionStatus(TransactionStatus.REJECTED);
			authorizationResult.setTransactionStatusDetails(TransactionStatusDetails.AUTHORIZATION_REJECTED_BY_PSP);
		}

		authorizationResult.setMerchantTransactionCode(authorizationRequest.getMerchantTransactionCode());
		
		return authorizationResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flieger.adyen.command.AdyenDebitAuthorizationCommand#perform3D(com.adyen.services.payment.
	 * AdyenDebitAuthorizationRequest)
	 */
	@Override
	public AdyenDebitAuthorizationResult perform3D(final AdyenDebitAuthorizationRequest authorizationRequest)
	{
		AdyenDebitAuthorizationResult authorizationResult = new AdyenDebitAuthorizationResult();
		PaymentRequest3D paymentRequest3D = new PaymentRequest3D();
		PaymentResult result3d = null;
		
		try
		{
			HeringDebitPaymentInfoModel debitPaymentInfoModel = authorizationRequest.getHeringDebitPaymentInfoModel();

			paymentRequest3D.setMerchantAccount(authorizationRequest.getMerchantAccount());

			BrowserInfo browserInfo = new BrowserInfo();
			browserInfo.setAcceptHeader(debitPaymentInfoModel.getAccept());
			browserInfo.setUserAgent(debitPaymentInfoModel.getUserAgent());
			paymentRequest3D.setBrowserInfo(browserInfo);
			
			paymentRequest3D.setMd(debitPaymentInfoModel.getMd());

			AnyType2AnyTypeMapEntry[] additionalData =
			{ new AnyType2AnyTypeMapEntry("mpiImplementationType", debitPaymentInfoModel.getMpiImplementationType()) };

			paymentRequest3D.setAdditionalData(additionalData);
			
			paymentRequest3D.setShopperIP(debitPaymentInfoModel.getShopperIP());

			try
			{
				result3d = new WebServicesConn().authorise3D(paymentRequest3D);
			}
			catch (Exception e)
			{
				authorizationResult.setTransactionStatus(TransactionStatus.ERROR);
				authorizationResult.setTransactionStatusDetails(TransactionStatusDetails.COMMUNICATION_PROBLEM);
				LOG.error("Error: [" + e.getMessage() + "]", e);
				return authorizationResult;
			}
			finally
			{
				printResult(result3d);
			}

			if (!StringUtils.isEmpty(result3d.getRefusalReason()) && result3d.getRefusalReason().matches("(^[0-9]{3}).*"))
			{
				authorizationResult.setErrorCode(result3d.getRefusalReason().substring(0, 3));
			}
			authorizationResult.setMd(result3d.getMd());
			authorizationResult.setResultCode(result3d.getResultCode());
		}
		catch (Exception e)
		{
			authorizationResult.setErrorCode("00");
			LOG.error("Ocorreu um erro na autorização do débito online pelo Adyen [" + e.getMessage() + "]", e);

			authorizationResult.setTransactionStatus(TransactionStatus.ERROR);
			authorizationResult.setTransactionStatusDetails(TransactionStatusDetails.UNKNOWN_CODE);
		}

		if (result3d.getResultCode() != null && result3d.getResultCode().equals(AdyenTransactionStatus.AUTHORIZED))
		{
			authorizationResult.setTransactionStatus(TransactionStatus.ACCEPTED);
			authorizationResult.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL);
		}
		else
		{
			authorizationResult.setTransactionStatus(TransactionStatus.REJECTED);
			authorizationResult.setTransactionStatusDetails(TransactionStatusDetails.AUTHORIZATION_REJECTED_BY_PSP);
		}

		authorizationResult.setMerchantTransactionCode(authorizationRequest.getMerchantTransactionCode());

		return authorizationResult;
	}

	/**
	 * @return
	 */
	private String getReturnURL()
	{
		final CMSSiteModel currentSite = cmsSiteService.getCurrentSite();
		final String path = Config.getParameter(AdyenConstants.ONLINE_DEBIT_RETURN_URL);
		return siteBaseUrlResolutionService.getWebsiteUrlForSite(currentSite, true, path);
	}

	private void printResult(PaymentResult payResult)
	{
		LOG.info("------------------------------");
		LOG.info("PaymentResult");
		LOG.info("PspReference: " + payResult.getPspReference());
		LOG.info("ResultCode: " + payResult.getResultCode());
		LOG.info("AuthCode: " + payResult.getAuthCode());
		LOG.info("RefusalReason: " + payResult.getRefusalReason());
		LOG.info("URL:" + payResult.getIssuerUrl());
		LOG.info("Code: " + payResult.getResultCode());
		LOG.info("MD: " + payResult.getMd());

		if (payResult.getAdditionalData() != null)
		{
			LOG.info("Entrys...");
			for (AnyType2AnyTypeMapEntry entry : payResult.getAdditionalData())
			{
				LOG.info("Key: [" + entry.getKey() + "] Value:[" + entry.getValue() + "]");
			}
		}
	}
	
	private void printRequest(PaymentRequest paymentRequest)
	{
		LOG.info("------------------------------");
		LOG.info("PaymentRequest");
		LOG.info("BrowserInfo.AcceptHeader: " + paymentRequest.getBrowserInfo().getAcceptHeader());
		LOG.info("BrowserInfo.UserAgent: " + paymentRequest.getBrowserInfo().getUserAgent());

		if (paymentRequest.getAdditionalData() != null)
		{
			LOG.info("Entrys...");
			for (AnyType2AnyTypeMapEntry entry : paymentRequest.getAdditionalData())
			{
				LOG.info("Key: [" + entry.getKey() + "] Value:[" + entry.getValue() + "]");
			}
		}
	}

}
