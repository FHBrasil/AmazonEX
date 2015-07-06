package com.paypal.hybris.converters.populators.impl;

import de.hybris.platform.acceleratorservices.enums.UiExperienceLevel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;

import com.ebay.api.SetExpressCheckoutReq;
import com.ebay.api.SetExpressCheckoutRequestDetailsType;
import com.ebay.api.SetExpressCheckoutRequestType;
import com.ebay.api.SolutionTypeType;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.data.SetExpressCheckoutRequestData;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class SetExprCheckoutReqMainPopulator implements Populator<SetExpressCheckoutRequestData, SetExpressCheckoutRequestType>
{

	private ConfigurationService configurationService;

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
	public void populate(final SetExpressCheckoutRequestData requestData, final SetExpressCheckoutRequestType request)
			throws ConversionException
	{
		final Configuration configuration = getConfigurationService().getConfiguration();
		SetExpressCheckoutRequestDetailsType details = request.getSetExpressCheckoutRequestDetails();
		if (details == null)
		{
			details = new SetExpressCheckoutRequestDetailsType();
			request.setSetExpressCheckoutRequestDetails(details);
		}

		String solutionType = requestData.getSolutionType();
		if (StringUtils.isBlank(solutionType))
		{
			solutionType = configuration.getString(PaypalConstants.DEFAULT_SOLUTION_TYPE_NAME);
		}
		details.setSolutionType(SolutionTypeType.fromValue(solutionType));

		details.setReturnURL(requestData.getReturnUrl());
		details.setCancelURL(requestData.getCancelUrl());

		if (requestData.getUiExperienceLevel() == UiExperienceLevel.DESKTOP)
		{
			details.setCppHeaderImage(configuration.getString(PaypalConstants.HEADER_IMAGE));
			details.setCppPayflowColor(configuration.getString(PaypalConstants.PAYFLOW_COLOR));
		}
	}

	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}
}
