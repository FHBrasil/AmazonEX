/**
 *
 */
package com.paypal.hybris.commands.impl;

import com.ebay.api.DoCaptureRequestType;
import de.hybris.platform.payment.commands.CaptureCommand;
import de.hybris.platform.payment.commands.request.CaptureRequest;
import de.hybris.platform.payment.commands.result.CaptureResult;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.util.GregorianCalendar;

import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;

import com.ebay.api.DoCaptureResponseType;
import com.paypal.hybris.commands.strategy.CaptureStrategy;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.service.PaypalPaymentService;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public class CaptureCommandImpl implements CaptureCommand
{
	private PaypalPaymentService paypalPaymentService;
	private Converter<CaptureRequest, DoCaptureRequestType> captureRequestConverter;
	private Converter<DoCaptureResponseType, CaptureResult> doCaptureResponseConverter;
	private CaptureStrategy captureStrategy;
	private ConfigurationService configurationService;


	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.payment.commands.Command#perform(java.lang.Object)
	 */
	@Override
	public CaptureResult perform(final CaptureRequest captureRequest)
	{
		CaptureResult captureResult = null;

		if (captureStrategy.allowCapture(captureRequest)
				&& !Boolean.TRUE.toString().equalsIgnoreCase(
						getConfigurationService().getConfiguration().getString(PaypalConstants.USE_REFERENCE_TRANSCATION)))
		{
			// convert to web service request
			final DoCaptureRequestType request = captureRequestConverter.convert(captureRequest);

			// call web service capture method
			DoCaptureResponseType response = null;
			if (request != null)
			{
				response = paypalPaymentService.doCapture(request);
			}

			// convert from web service response to result data
			if (response != null)
			{
				captureResult = doCaptureResponseConverter.convert(response);
			}
		}

		if (captureResult == null)
		{
			captureResult = new CaptureResult();
			captureResult.setTransactionStatus(TransactionStatus.REVIEW);
			captureResult.setTransactionStatusDetails(TransactionStatusDetails.REVIEW_NEEDED);
			captureResult.setRequestTime(GregorianCalendar.getInstance().getTime());
		}

		return captureResult;
	}

	@Required
	public void setPaypalPaymentService(final PaypalPaymentService paypalPaymentService)
	{
		this.paypalPaymentService = paypalPaymentService;
	}

	@Required
	public void setCaptureRequestConverter(final Converter<CaptureRequest, DoCaptureRequestType> captureRequestConverter)
	{
		this.captureRequestConverter = captureRequestConverter;
	}

	@Required
	public void setDoCaptureResponseConverter(final Converter<DoCaptureResponseType, CaptureResult> doCaptureResponseConverter)
	{
		this.doCaptureResponseConverter = doCaptureResponseConverter;
	}

	/**
	 * @param captureStrategy
	 *           the captureStrategy to set
	 */
	@Required
	public void setCaptureStrategy(final CaptureStrategy captureStrategy)
	{
		this.captureStrategy = captureStrategy;
	}

	/**
	 * @return the configurationService
	 */
	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	/**
	 * @param configurationService
	 *           the configurationService to set
	 */
	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}



}
