package com.paypal.hybris.converters.populators.impl;

import com.ebay.api.PayerInfoType;
import com.ebay.api.TransactionSearchReq;
import com.ebay.api.TransactionSearchRequestType;
import com.paypal.hybris.data.TransactionSearchRequestData;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.lang.StringUtils;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class TransactionSearchReqPopulator implements Populator<TransactionSearchRequestData, TransactionSearchRequestType>
{
	/**
	 * Populate the target instance with values from the source instance.
	 *
	 * @param requestData the source object
	 * @param request     the target to fill
	 * @throws de.hybris.platform.servicelayer.dto.converter.ConversionException if an error occurs
	 */
	@Override public void populate(TransactionSearchRequestData requestData, TransactionSearchRequestType request)
			throws ConversionException
	{
		if (requestData.getStartDate() == null)
		{
			throw new ConversionException("Start date is required attribute and can't be null");
		}
		request.setStartDate(new XMLGregorianCalendarImpl((GregorianCalendar) requestData.getStartDate()));
		if (requestData.getEndDate() != null)
		{
			request.setEndDate(new XMLGregorianCalendarImpl((GregorianCalendar) requestData.getEndDate()));
		}

		String payerEmail = requestData.getPayerEmail();
		if (StringUtils.isNotBlank(payerEmail))
		{
			request.setPayer(requestData.getPayerEmail());
		}

		String transactionId = requestData.getTransactionId();
		if (StringUtils.isNotBlank(transactionId))
		{
			request.setTransactionID(transactionId);
		}
	}
}
