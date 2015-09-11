package com.paypal.hybris.converters.populators.impl;

import com.ebay.api.*;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.List;

import com.paypal.hybris.converters.populators.AbstractRequestPaymentDetailsPopulator;
import com.paypal.hybris.data.DoExpressCheckoutPaymentRequestData;
import de.hybris.platform.commercefacades.order.data.CartData;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class DoExprCheckoutPaymentReqPaymentDetailsPopulator extends
		AbstractRequestPaymentDetailsPopulator<DoExpressCheckoutPaymentRequestData, DoExpressCheckoutPaymentRequestType>
{
	/**
	 * Populate the target instance with values from the source instance.
	 *
	 * @param requestData
	 *           the source object
	 * @param request
	 *           the target to fill
	 * @throws de.hybris.platform.servicelayer.dto.converter.ConversionException
	 *            if an error occurs
	 */
	@Override
	public void populate(DoExpressCheckoutPaymentRequestData requestData, DoExpressCheckoutPaymentRequestType request) throws ConversionException {
		CartData cartData = requestData.getSessionCart();
		List<PaymentDetailsType> paymentDetails = createPaymentDetailsList(requestData, cartData);
		DoExpressCheckoutPaymentRequestDetailsType requestDetails = request.getDoExpressCheckoutPaymentRequestDetails();
		if (requestDetails == null)
		{
			requestDetails = new DoExpressCheckoutPaymentRequestDetailsType();
			request.setDoExpressCheckoutPaymentRequestDetails(requestDetails);
		}
		requestDetails.getPaymentDetails().addAll(paymentDetails);

		setPaymentActionForAllPaymentDetails(requestData.getPaymentAction(), requestDetails.getPaymentDetails());

	}

}
