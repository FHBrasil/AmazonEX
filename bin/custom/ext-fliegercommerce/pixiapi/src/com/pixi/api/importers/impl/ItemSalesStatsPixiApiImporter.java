package com.pixi.api.importers.impl;

import java.security.InvalidParameterException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
import com.pixi.api.result.ItemInfoResult;
import com.pixi.api.result.ItemSalesStatsResult;

/**
 * Instantiate this class you should not! See
 * {@link com.pixi.api.PixiApiFactory} you must. <br/>
 * <br/>
 * 
 * @author jfelipe
 */
// @UnitTest
public class ItemSalesStatsPixiApiImporter extends AbstractPixiApiImporter {

	private static final Logger LOG = Logger
			.getLogger(ItemSalesStatsPixiApiImporter.class);
	//
	private static final String OPEN_1_SS = "Open1SS";
	private static final String DAYS_FOR_STATS = "DaysForStats";
	private static final String SALES_SINCE_DAYS = "SalesSinceDays";
	private static final String ABC = "ABC";
	private static final String SALES_30 = "Sales30";
	private static final String SALES_120 = "Sales120";
	private static final String SALES_365 = "Sales365";

	/**
     * 
     */
	public ItemSalesStatsPixiApiImporter() {
		super();
		validParameters.add(PixiParameterType.EAN_UPC);
		validParameters.add(PixiParameterType.ITEM_NUMBER_INTERNAL);
	}

	/**
	 * Imports the item info from Pixi. <br/>
	 * 
	 * @param functionParameters
	 *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
	 *            - {@link PixiParameterType#EAN_UPC}<br/>
	 *            - {@link PixiParameterType#ITEM_NUMBER_INTERNAL}<br/>
	 * @return A List of {@link ItemInfoResult} containing the results from Pixi
	 *         API
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
	public List<ItemSalesStatsResult> importData(
			List<PixiFunctionParameter> functionParameters)
			throws SOAPResponseErrorException, SOAPException,
			KeyManagementException, NoSuchAlgorithmException {
		if (functionParameters == null || functionParameters.isEmpty()) {
			throw new InvalidParameterException(
					"The PixiAPI function parameter should not be null.");
		}
		return importItemSalesStats(functionParameters);
	}

	/**
	 * Imports the item info from Pixi. <br/>
	 * 
	 * @param functionParameters
	 *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
	 *            - {@link PixiParameterType#EAN_UPC}<br/>
	 *            - {@link PixiParameterType#ITEM_NUMBER_INTERNAL}<br/>
	 * @return A List of {@link ItemInfoResult} containing the results from Pixi
	 *         API
	 * @throws SOAPException
	 *             in case errors occurred during the Pixi API request
	 * @throws SOAPResponseErrorException
	 *             in case errors ocurred during the Pixi API response
	 *
	 * @author jfelipe
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	private List<ItemSalesStatsResult> importItemSalesStats(
			List<PixiFunctionParameter> functionParameters)
			throws SOAPResponseErrorException, SOAPException,
			KeyManagementException, NoSuchAlgorithmException {
		List<ItemSalesStatsResult> results = new ArrayList<ItemSalesStatsResult>();
		ItemSalesStatsResult result = null;
		checkValidParameters(functionParameters);
		SOAPMessage request = getPixiSoapApi().buildMessage(
				PixiFunction.GET_ITEM_SALES_STATS, functionParameters);
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
					result = new ItemSalesStatsResult();
					for (int j = 0; j < row.getChildNodes().getLength(); j++) {
						Node currentTag = row.getChildNodes().item(j);
						String currentTagValue = currentTag.getTextContent()
								.trim();
						switch (currentTag.getNodeName()) {
						case OPEN_1_SS:
							int open1SS = Integer.parseInt(currentTagValue);
							result.setOpen1SS(open1SS);
							break;
						case DAYS_FOR_STATS:
							int daysForStats = Integer
									.parseInt(currentTagValue);
							result.setDaysForStats(daysForStats);
							break;
						case SALES_SINCE_DAYS:
							int salesSinceDays = Integer
									.parseInt(currentTagValue);
							result.setSalesSinceDays(salesSinceDays);
							break;
						case ABC:
							result.setAbc(currentTagValue);
							break;
						case SALES_30:
							int sales30 = Integer.parseInt(currentTagValue);
							result.setSales30(sales30);
							break;
						case SALES_120:
							int sales120 = Integer.parseInt(currentTagValue);
							result.setSales120(sales120);
							break;
						case SALES_365:
							int sales365 = Integer.parseInt(currentTagValue);
							result.setSales365(sales365);
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
