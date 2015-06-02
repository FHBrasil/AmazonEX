package com.paypal.hybris.converters.populators.impl;

import com.ebay.api.DoExpressCheckoutPaymentRequestDetailsType;
import com.ebay.api.DoExpressCheckoutPaymentRequestType;
import com.ebay.api.PaymentActionCodeType;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.data.DoExpressCheckoutPaymentRequestData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.session.SessionService;

/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class DoExprCheckoutPaymentReqMainPopulator implements Populator<DoExpressCheckoutPaymentRequestData, DoExpressCheckoutPaymentRequestType> {


	private SessionService sessionService;

	/**
	 * Populate the target instance with values from the source instance.
	 *
	 * @param requestData the source object
	 * @param request         the target to fill
	 * @throws de.hybris.platform.servicelayer.dto.converter.ConversionException if an error occurs
	 */
	@Override
	public void populate(DoExpressCheckoutPaymentRequestData requestData, DoExpressCheckoutPaymentRequestType request) throws ConversionException {
		DoExpressCheckoutPaymentRequestDetailsType details = request.getDoExpressCheckoutPaymentRequestDetails();
		if (details == null)
		{
			details = new DoExpressCheckoutPaymentRequestDetailsType();
			request.setDoExpressCheckoutPaymentRequestDetails(details);
		}

		details.setPayerID(requestData.getPayerId());
		details.setToken(requestData.getToken());
		Boolean isCredit = sessionService.getAttribute(PaypalConstants.IS_PAYPAL_CREDIT);
		if(isCredit != null && isCredit){
			details.setButtonSource(PaypalConstants.BUTTON_SOURCE_CREDIT);
		} else {
			details.setButtonSource(PaypalConstants.BUTTON_SOURCE);
		}

	}

	public SessionService getSessionService() {
		return sessionService;
	}

	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}
}
