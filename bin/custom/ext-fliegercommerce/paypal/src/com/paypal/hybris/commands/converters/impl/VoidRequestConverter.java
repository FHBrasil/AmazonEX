/**
 *
 */
package com.paypal.hybris.commands.converters.impl;

import de.hybris.platform.payment.commands.request.VoidRequest;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import com.ebay.api.DoVoidRequestType;
import com.paypal.hybris.constants.PaypalConstants;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public class VoidRequestConverter implements Converter<VoidRequest, DoVoidRequestType>
{

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.servicelayer.dto.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public DoVoidRequestType convert(final VoidRequest voidRequest) throws ConversionException
	{
		final DoVoidRequestType doVoidRequest = new DoVoidRequestType();
		return convert(voidRequest, doVoidRequest);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.servicelayer.dto.converter.Converter#convert(java.lang.Object, java.lang.Object)
	 */
	@Override
	public DoVoidRequestType convert(final VoidRequest voidRequest, final DoVoidRequestType doVoidRequest) throws ConversionException
	{
		doVoidRequest.setAuthorizationID(voidRequest.getRequestId());
		doVoidRequest.setVersion(PaypalConstants.SOAP_API_VERSION);
		return doVoidRequest;
	}

}
