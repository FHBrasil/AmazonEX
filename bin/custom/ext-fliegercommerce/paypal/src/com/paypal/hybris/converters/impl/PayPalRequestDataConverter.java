package com.paypal.hybris.converters.impl;

import com.ebay.api.AbstractRequestType;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.data.AbstractRequestData;
import de.hybris.platform.converters.impl.AbstractPopulatingConverter;
import org.apache.commons.lang.StringUtils;

import java.util.Locale;

/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class PayPalRequestDataConverter extends
		AbstractPopulatingConverter<AbstractRequestData, AbstractRequestType> {
	
	/**
	 * Populate the target instance from the source instance. Calls a list
	 * of injected populators to populate the
	 * instance.
	 *
	 * @param requestData
	 *            the source item
	 * @param request
	 */
	@Override
	public void populate(AbstractRequestData requestData,
			AbstractRequestType request) {
		String apiVersion = requestData.getVersion();
		if (StringUtils.isBlank(apiVersion)) {
			request.setVersion(PaypalConstants.SOAP_API_VERSION);
		} else {
			request.setVersion(apiVersion);
		}
		Locale userLocale = requestData.getLocale();
		if (userLocale != null) {
			request.setErrorLanguage(userLocale.getLanguage());
		}
		super.populate(requestData, request);
	}
	
	
	@Override
	protected AbstractRequestType createTarget() {
		return null;
	}
}
