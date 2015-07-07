package com.paypal.hybris.converters.populators.impl;

import com.ebay.api.*;

import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.converters.populators.AbstractRequestPaymentDetailsPopulator;
import com.paypal.hybris.data.DoReferenceTransactionRequestData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class DoRefTransactionReqPopulator extends AbstractRequestPaymentDetailsPopulator<DoReferenceTransactionRequestData, DoReferenceTransactionRequestType>
{
	/**
	 * Populate the target instance with values from the source instance.
	 *
	 * @param requestData the source object
	 * @param request the target to fill
	 * @throws de.hybris.platform.servicelayer.dto.converter.ConversionException if an error occurs
	 */
	@Override public void populate(DoReferenceTransactionRequestData requestData,
			DoReferenceTransactionRequestType request) throws ConversionException
	{
		final DoReferenceTransactionRequestDetailsType requestDetails = new DoReferenceTransactionRequestDetailsType();

		OrderData orderData = requestData.getOrderData();

		requestDetails.setReferenceID(requestData.getReferenceId());
		requestDetails.setPaymentAction(PaymentActionCodeType.SALE);

		final List<PaymentDetailsType> paymentDetailsList = createPaymentDetailsList(requestData, orderData);
		if (CollectionUtils.isNotEmpty(paymentDetailsList) && paymentDetailsList.size() != 1)
		{
			throw new ConversionException("Can't make doReferenceTransaction call on order with multiple shipping.");
		}

		final PaymentDetailsType paymentDetails = paymentDetailsList.get(0);

		boolean isCredit = requestData.isCredit();
		if(isCredit)
		{
			paymentDetails.setButtonSource(PaypalConstants.BUTTON_SOURCE_CREDIT);
		}
		else
		{
			paymentDetails.setButtonSource(PaypalConstants.BUTTON_SOURCE);
		}

		requestDetails.setPaymentDetails(paymentDetails);

		request.setDoReferenceTransactionRequestDetails(requestDetails);
	}
}
