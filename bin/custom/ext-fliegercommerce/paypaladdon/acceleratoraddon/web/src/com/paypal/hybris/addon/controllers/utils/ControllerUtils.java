package com.paypal.hybris.addon.controllers.utils;

import com.paypal.hybris.data.AbstractResultData;
import com.paypal.hybris.data.ResultErrorData;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class ControllerUtils
{

	public static Map<String, String> getErrorsCodeToMessageMap(AbstractResultData resultData)
	{
		Map<String, String> errorCodeToMsgMap = new HashMap<>();

		List<ResultErrorData> errorDataList = resultData.getErrors();
		if (CollectionUtils.isNotEmpty(errorDataList))
		{
			for (ResultErrorData errorData: errorDataList)
			{
				String msg = errorData.getShortMessage() + ". " + errorData.getLongMessage();
				String code = errorData.getErrorCode();
				errorCodeToMsgMap.put(code, msg);
			}
		}

		return errorCodeToMsgMap;
	}

	public static List<String> getErrorCodeList(AbstractResultData resultData)
	{
		List<String> errorCodes = new ArrayList<>();
		List<ResultErrorData> errorList = resultData.getErrors();
		if (CollectionUtils.isNotEmpty(errorList))
		{
			for (ResultErrorData error: errorList)
			{
				errorCodes.add(error.getErrorCode());
			}
		}
		return errorCodes;
	}
}
