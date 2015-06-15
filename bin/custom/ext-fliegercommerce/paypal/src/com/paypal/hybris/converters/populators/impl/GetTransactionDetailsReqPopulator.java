package com.paypal.hybris.converters.populators.impl;

import com.ebay.api.GetTransactionDetailsRequestType;
import com.paypal.hybris.data.GetTransactionDetailsRequestData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.lang.StringUtils;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class GetTransactionDetailsReqPopulator implements Populator<GetTransactionDetailsRequestData, GetTransactionDetailsRequestType>
{
	/**
	 * Populate the target instance with values from the source instance.
	 *
	 * @param requestData  the source object
	 * @param request the target to fill
	 * @throws ConversionException if an error occurs
	 */
	@Override
	public void populate(GetTransactionDetailsRequestData requestData,
			GetTransactionDetailsRequestType request) throws ConversionException
	{
		String transactionId = requestData.getTransactionId();
		if (StringUtils.isBlank(transactionId))
		{
			throw new ConversionException("Transaction id is required attribute and must be set");
		}
		request.setTransactionID(requestData.getTransactionId());
	}
}
