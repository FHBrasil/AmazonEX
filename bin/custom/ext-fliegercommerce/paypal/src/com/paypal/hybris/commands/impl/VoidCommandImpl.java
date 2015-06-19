/**
 *
 */
package com.paypal.hybris.commands.impl;

import com.ebay.api.DoVoidRequestType;
import de.hybris.platform.payment.commands.VoidCommand;
import de.hybris.platform.payment.commands.request.VoidRequest;
import de.hybris.platform.payment.commands.result.VoidResult;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import com.ebay.api.DoVoidReq;
import com.ebay.api.DoVoidResponseType;
import com.paypal.hybris.service.PaypalPaymentService;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public class VoidCommandImpl implements VoidCommand
{
	private PaypalPaymentService paypalPaymentService;
	private Converter<VoidRequest, DoVoidRequestType> voidRequestConverter;
	private Converter<DoVoidResponseType, VoidResult> doVoidResponseConverter;

	/**
	 * @return the paypalPaymentService
	 */
	public PaypalPaymentService getPaypalPaymentService()
	{
		return paypalPaymentService;
	}


	/**
	 * @param paypalPaymentService
	 *           the paypalPaymentService to set
	 */
	public void setPaypalPaymentService(final PaypalPaymentService paypalPaymentService)
	{
		this.paypalPaymentService = paypalPaymentService;
	}


	/**
	 * @return the voidRequestConverter
	 */
	public Converter<VoidRequest, DoVoidRequestType> getVoidRequestConverter()
	{
		return voidRequestConverter;
	}


	/**
	 * @param voidRequestConverter
	 *           the voidRequestConverter to set
	 */
	public void setVoidRequestConverter(final Converter<VoidRequest, DoVoidRequestType> voidRequestConverter)
	{
		this.voidRequestConverter = voidRequestConverter;
	}


	/**
	 * @return the doVoidResponseConverter
	 */
	public Converter<DoVoidResponseType, VoidResult> getDoVoidResponseConverter()
	{
		return doVoidResponseConverter;
	}


	/**
	 * @param doVoidResponseConverter
	 *           the doVoidResponseConverter to set
	 */
	public void setDoVoidResponseConverter(final Converter<DoVoidResponseType, VoidResult> doVoidResponseConverter)
	{
		this.doVoidResponseConverter = doVoidResponseConverter;
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.payment.commands.Command#perform(java.lang.Object)
	 */
	@Override
	public VoidResult perform(final VoidRequest voidRequest)
	{
		VoidResult voidRes = null;
		DoVoidResponseType voidResp = null;
		final DoVoidRequestType voidRequestType = voidRequestConverter.convert(voidRequest);

		if (voidRequestType != null)
		{
			DoVoidReq req = new DoVoidReq();
			req.setDoVoidRequest(voidRequestType);
			voidResp = paypalPaymentService.doVoid(voidRequestType);
		}

		if (voidResp != null)
		{
			voidRes = doVoidResponseConverter.convert(voidResp);
		}

		return voidRes;
	}

}
