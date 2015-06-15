package com.paypal.hybris.converters.populators.impl;

import com.ebay.api.DoReferenceTransactionResponseDetailsType;
import com.ebay.api.DoReferenceTransactionResponseType;

import com.ebay.api.PaymentInfoType;
import com.paypal.hybris.data.DoReferenceTransactionResultData;
import com.paypal.hybris.data.PaymentStatus;
import com.paypal.hybris.data.PendingReason;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class DoRefTransactionResultDataPopulator implements Populator<DoReferenceTransactionResponseType, DoReferenceTransactionResultData>
{
	/**
	 * Populate the target instance with values from the source instance.
	 *
	 * @param response the source object
	 * @param resultData    the target to fill
	 * @throws de.hybris.platform.servicelayer.dto.converter.ConversionException if an error occurs
	 */
	@Override public void populate(DoReferenceTransactionResponseType response,
			DoReferenceTransactionResultData resultData) throws ConversionException
	{
		DoReferenceTransactionResponseDetailsType responseDetails = response.getDoReferenceTransactionResponseDetails();
		if (responseDetails != null)
		{
			resultData.setBillingAgreementId(responseDetails.getBillingAgreementID());

			PaymentInfoType paymentInfo = responseDetails.getPaymentInfo();
			if (paymentInfo != null)
			{
				resultData.setTransactionId(paymentInfo.getTransactionID());
				resultData.setPaymentStatus(PaymentStatus.valueOf(paymentInfo.getPaymentStatus().name()));
				resultData.setPendingReason(PendingReason.valueOf(paymentInfo.getPendingReason().name()));
			}
		}
	}
}
