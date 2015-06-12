package com.paypal.hybris.converters.populators.impl;

import com.ebay.api.GetExpressCheckoutDetailsRequestType;
import com.paypal.hybris.data.GetExpressCheckoutDetailsRequestData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class GetExprCheckoutDetailsReqPopulator implements Populator<GetExpressCheckoutDetailsRequestData, GetExpressCheckoutDetailsRequestType> {
	/**
	 * Populate the target instance with values from the source instance.
	 *
	 * @param requestData the source object
	 * @param request         the target to fill
	 * @throws de.hybris.platform.servicelayer.dto.converter.ConversionException if an error occurs
	 */
	@Override
	public void populate(GetExpressCheckoutDetailsRequestData requestData, GetExpressCheckoutDetailsRequestType request) throws ConversionException
	{
		request.setToken(requestData.getToken());
	}
}
