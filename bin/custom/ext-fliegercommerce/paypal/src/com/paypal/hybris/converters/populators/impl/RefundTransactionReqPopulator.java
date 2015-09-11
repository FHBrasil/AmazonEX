package com.paypal.hybris.converters.populators.impl;

import com.ebay.api.RefundTransactionRequestType;
import com.ebay.api.RefundType;
import com.paypal.hybris.data.RefundTransactionRequestData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class RefundTransactionReqPopulator implements Populator<RefundTransactionRequestData, RefundTransactionRequestType>
{
	private static final Logger LOG = Logger.getLogger(RefundTransactionReqPopulator.class);

	/**
	 * Populate the target instance with values from the source instance.
	 *
	 * @param requestData                  the source object
	 * @param request the target to fill
	 * @throws de.hybris.platform.servicelayer.dto.converter.ConversionException if an error occurs
	 */
	@Override public void populate(RefundTransactionRequestData requestData,
			RefundTransactionRequestType request) throws ConversionException
	{
		request.setRefundType(RefundType.FULL);
		String transactionId = requestData.getTransactionId();
		if (StringUtils.isBlank(transactionId))
		{
			LOG.error("Transaction id can't be null or empty");
			throw new ConversionException("Transaction id is empty or null");
		}
		request.setTransactionID(requestData.getTransactionId());
	}
}
