package com.paypal.hybris.converters.populators.impl;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.ebay.api.AckCodeType;
import com.ebay.api.DoAuthorizationResponseType;
import com.ebay.api.PaymentStatusCodeType;
import com.ebay.api.PendingStatusCodeType;
import com.paypal.hybris.data.DoAuthorizationResultData;
import com.paypal.hybris.data.PaymentStatus;
import com.paypal.hybris.data.PendingReason;
import org.apache.commons.lang.StringUtils;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class DoAuthorizationResultDataPopulator implements Populator<DoAuthorizationResponseType, DoAuthorizationResultData>
{
	/**
	 * Populate the target instance with values from the source instance.
	 *
	 * @param response
	 *           the source object
	 * @param resultData
	 *           the target to fill
	 * @throws de.hybris.platform.servicelayer.dto.converter.ConversionException
	 *            if an error occurs
	 */
	@Override
	public void populate(final DoAuthorizationResponseType response, final DoAuthorizationResultData resultData)
			throws ConversionException
	{
		if (AckCodeType.SUCCESS == response.getAck())
		{
			resultData.setTransactionId(response.getTransactionID());
			resultData.setAmount(Double.valueOf(response.getAmount().getValue()));
			resultData.setCurrencyIsoCode(response.getAmount().getCurrencyID().value());
			final PaymentStatusCodeType paymentStatusCode = response.getAuthorizationInfo().getPaymentStatus();
			resultData.setPaymentStatus(PaymentStatus.valueOf(paymentStatusCode.value().toUpperCase()));
			if (PaymentStatusCodeType.PENDING == paymentStatusCode)
			{
				final PendingStatusCodeType pendingStatusCode = response.getAuthorizationInfo().getPendingReason();
				resultData.setPendingReason(PendingReason.valueOf(pendingStatusCode.name()));
			}
		}
	}
}
