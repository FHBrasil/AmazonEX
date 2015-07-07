package com.paypal.hybris.converters.populators.impl;

import com.ebay.api.SetExpressCheckoutResponseType;
import com.paypal.hybris.data.SetExpressCheckoutResultData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class SetExprCheckoutResultDataPopulator implements Populator<SetExpressCheckoutResponseType, SetExpressCheckoutResultData> {
	/**
	 * Populate the target instance with values from the source instance.
	 *
	 * @param response the source object
	 * @param resultData   the target to fill
	 * @throws de.hybris.platform.servicelayer.dto.converter.ConversionException if an error occurs
	 */
	@Override
	public void populate(SetExpressCheckoutResponseType response, SetExpressCheckoutResultData resultData) throws ConversionException {
		resultData.setToken(response.getToken());
	}
}
