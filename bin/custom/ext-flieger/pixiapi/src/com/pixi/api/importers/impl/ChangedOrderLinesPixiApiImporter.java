package com.pixi.api.importers.impl;

import java.security.InvalidParameterException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import com.pixi.api.core.PixiApiResponseTags;
import com.pixi.api.core.PixiFunction;
import com.pixi.api.core.PixiFunctionParameter;
import com.pixi.api.core.PixiParameterType;
import com.pixi.api.exceptions.SOAPResponseErrorException;
import com.pixi.api.importers.AbstractPixiApiImporter;
import com.pixi.api.result.ChangedOrderLinesResult;

/**
 * Instantiate this class you should not! See
 * {@link com.pixi.api.PixiApiFactory} you must. <br/>
 * <br/>
 * 
 * @author jfelipe
 */
public class ChangedOrderLinesPixiApiImporter extends AbstractPixiApiImporter {

	private static final Logger LOG = Logger
			.getLogger(ChangedOrderLinesPixiApiImporter.class);
	//
	private static final String ITEM_NUMBER_INTERNAL = "ItemNrInt";
	private static final String ORDER_NUMBER_EXTERNAL = "OrderNrExternal";
	private static final String STATUS = "Status";
	private static final String ITEM_NUMBER_EXTERNAL = "ItemNrExternal";
	private static final String SHOP_ID = "ShopID";
	private static final String ORDER_LINE_KEY = "OrderlineKey";
	private static final String STATUS_CHANGE_DATE = "StatusChangeDate";

	/**
     * 
     */
	public ChangedOrderLinesPixiApiImporter() {
		super();
		validParameters.add(PixiParameterType.SINCE_DATE);
		validParameters.add(PixiParameterType.WHOLE_HISTORY);
		validParameters.add(PixiParameterType.ROWS);
	}

	/**
	 * Imports the changed order lines from Pixi. <br/>
	 * 
	 * @param functionParameters
	 *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
	 *            - {@link PixiParameterType#SINCE_DATE}<br/>
	 *            - {@link PixiParameterType#WHOLE_HISTORY}<br/>
	 *            - {@link PixiParameterType#ROWS}<br/>
	 * @return A List of {@link ChangedOrderLinesResult} containing the results
	 *         from Pixi API.
	 * @throws SOAPException
	 *             in case errors occurred during the Pixi API request
	 * @throws SOAPResponseErrorException
	 *             in case errors ocurred during the Pixi API response
	 *
	 * @author jfelipe
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	@Override
	public List<ChangedOrderLinesResult> importData(
			List<PixiFunctionParameter> functionParameters)
			throws SOAPResponseErrorException, SOAPException,
			KeyManagementException, NoSuchAlgorithmException {
		if (functionParameters == null || functionParameters.isEmpty()) {
			throw new InvalidParameterException(
					"The PixiAPI function parameter should not be null.");
		}
		return importChangesOrderLines(functionParameters);
	}

	/**
	 * Imports the changed order lines from Pixi. <br/>
	 * 
	 * @param functionParameters
	 *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
	 *            - {@link PixiParameterType#SINCE_DATE}<br/>
	 *            - {@link PixiParameterType#WHOLE_HISTORY}<br/>
	 *            - {@link PixiParameterType#ROWS}<br/>
	 * @return A List of {@link ChangedOrderLinesResult} containing the results
	 *         from Pixi API.
	 * @throws SOAPException
	 *             in case errors occurred during the Pixi API request
	 * @throws SOAPResponseErrorException
	 *             in case errors ocurred during the Pixi API response
	 *
	 * @author jfelipe
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	private List<ChangedOrderLinesResult> importChangesOrderLines(
			List<PixiFunctionParameter> functionParameters)
			throws SOAPResponseErrorException, SOAPException,
			KeyManagementException, NoSuchAlgorithmException {
		checkValidParameters(functionParameters);
		List<ChangedOrderLinesResult> results = new ArrayList<ChangedOrderLinesResult>();
		SOAPMessage request = getPixiSoapApi().buildMessage(
				PixiFunction.GET_CHANGED_ORDER_LINES, functionParameters);
		SOAPMessage response = getPixiSoapApi().sendPixiWebServiceRequest(
				request);
		if (response != null) {
			Node responseXml = null;
			responseXml = response
					.getSOAPBody()
					.getElementsByTagName(
							PixiApiResponseTags.SQLROWSET1.getValue()).item(0);
			if (responseXml != null) {
				for (int i = 0; i < responseXml.getChildNodes().getLength(); i++) {
					Node row = responseXml.getChildNodes().item(i);
					ChangedOrderLinesResult result = new ChangedOrderLinesResult();
					for (int j = 0; j < row.getChildNodes().getLength(); j++) {
						Node currentTag = row.getChildNodes().item(j);
						String currentTagValue = currentTag.getTextContent()
								.trim();
						switch (currentTag.getNodeName()) {
						case ITEM_NUMBER_INTERNAL:
							result.setItemNrInt(currentTagValue);
							break;
						case ORDER_NUMBER_EXTERNAL:
							result.setOrderNrExternal(currentTagValue);
							break;
						case STATUS:
							result.setStatus(currentTagValue);
							break;
						case ITEM_NUMBER_EXTERNAL:
							result.setItemNrExternal(currentTagValue);
							break;
						case SHOP_ID:
							result.setShopID(currentTagValue);
							break;
						case ORDER_LINE_KEY:
							result.setOrderlineKey(currentTagValue);
							break;
						case STATUS_CHANGE_DATE:
							Date statusChangeDate = parseStringToDate(currentTagValue);
							result.setStatusChangeDate(statusChangeDate);
							break;
						}
					}
					results.add(result);
				}
			}
		}
		return results;
	}
}
