package com.paypal.hybris.converters;

import com.ebay.api.AbstractResponseType;
import com.ebay.api.ErrorType;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.data.AbstractResultData;
import com.paypal.hybris.data.ResultErrorData;
import com.paypal.hybris.data.SeverityCode;
import de.hybris.platform.converters.impl.AbstractPopulatingConverter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.CharUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public abstract class AbstractResultConverter<SOURCE extends AbstractResponseType, TARGET extends AbstractResultData> extends AbstractPopulatingConverter<SOURCE, TARGET> {

	/**
	 * Populate the target instance from the source instance. Calls a list of injected populators to populate the
	 * instance.
	 *
	 * @param response the source item
	 * @param resultData
	 */
	@Override
	public void populate(SOURCE response, TARGET resultData) {
		resultData.setBuild(response.getBuild());
		resultData.setVersion(response.getVersion());
		resultData.setCorrelationId(response.getCorrelationID());
		resultData.setDateTime(response.getTimestamp().toGregorianCalendar());
		resultData.setErrors(new ArrayList<ResultErrorData>());
		resultData.setAck(response.getAck().value());

		List<ResultErrorData> errorsData = resultData.getErrors();
		List<String> errorMessagesList = new ArrayList<>();
		resultData.setErrorMessagesList(errorMessagesList);
		List<ErrorType> errors = response.getErrors();
		if (CollectionUtils.isNotEmpty(errors)) {
			for (ErrorType error: errors) {
				ResultErrorData errorData = new ResultErrorData();
				errorData.setErrorCode(error.getErrorCode());
				errorData.setShortMessage(error.getShortMessage());
				errorData.setLongMessage(error.getLongMessage());

				StringBuilder errorMessageBuilder = new StringBuilder();
				errorMessageBuilder.append(error.getErrorCode()).append(PaypalConstants.ERROR_MESSAGE_CODE_SEPARATOR);
				errorMessageBuilder.append(error.getShortMessage()).append(PaypalConstants.ERROR_MESSAGE_SHORT_MESSAGE_SEPARATOR);
				errorMessageBuilder.append(error.getLongMessage());
            errorMessagesList.add(errorMessageBuilder.toString());

				String severityCodeValue = error.getSeverityCode().value().toUpperCase();
				errorData.setSeverityCode(SeverityCode.valueOf(severityCodeValue));

				errorsData.add(errorData);
			}
		}

		super.populate(response, resultData);
	}
}
