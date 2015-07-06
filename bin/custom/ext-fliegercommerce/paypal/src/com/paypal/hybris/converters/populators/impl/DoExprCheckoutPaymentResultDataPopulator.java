package com.paypal.hybris.converters.populators.impl;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.ebay.api.DoExpressCheckoutPaymentResponseDetailsType;
import com.ebay.api.DoExpressCheckoutPaymentResponseType;
import com.ebay.api.PaymentInfoType;
import com.paypal.hybris.data.DoExpressCheckoutPaymentResultData;
import com.paypal.hybris.data.PaymentInfoData;
import com.paypal.hybris.data.PaymentStatus;
import com.paypal.hybris.data.PendingReason;


/**
 * Populate resultData dto object with data came from web-service call response object
 *
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class DoExprCheckoutPaymentResultDataPopulator implements
		Populator<DoExpressCheckoutPaymentResponseType, DoExpressCheckoutPaymentResultData>
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
	public void populate(final DoExpressCheckoutPaymentResponseType response, final DoExpressCheckoutPaymentResultData resultData)
			throws ConversionException
	{
		final DoExpressCheckoutPaymentResponseDetailsType details = response.getDoExpressCheckoutPaymentResponseDetails();
		if (details != null)
		{
			final List<PaymentInfoType> paymentInfoList = details.getPaymentInfo();
			final List<PaymentInfoData> paymentInfoDataList = new ArrayList<>();
			resultData.setPaymentInfoList(paymentInfoDataList);
			if (CollectionUtils.isNotEmpty(paymentInfoList))
			{
				for (final PaymentInfoType paymentInfo : paymentInfoList)
				{
					final PaymentInfoData paymentInfoData = new PaymentInfoData();
					paymentInfoData.setTransactionId(paymentInfo.getTransactionID());
					if (paymentInfo.getGrossAmount() != null)
					{
						paymentInfoData.setGrossAmount(NumberUtils.toDouble(paymentInfo.getGrossAmount().getValue()));
						paymentInfoData.setCurrencyIsoCode(paymentInfo.getGrossAmount().getCurrencyID().value());
					}
					paymentInfoData.setPaymentRequestId(paymentInfo.getPaymentRequestID());
					paymentInfoData.setPaymentDate(paymentInfo.getPaymentDate().toGregorianCalendar());
					paymentInfoData.setPaymentStatus(PaymentStatus.valueOf(paymentInfo.getPaymentStatus().name()));
					if (paymentInfo.getPendingReason() != null)
					{
						paymentInfoData.setPendingReason(PendingReason.valueOf(paymentInfo.getPendingReason().name()));
					}
					paymentInfoDataList.add(paymentInfoData);
				}
			}

			resultData.setBillingAgreementID(details.getBillingAgreementID());
		}
	}
}
